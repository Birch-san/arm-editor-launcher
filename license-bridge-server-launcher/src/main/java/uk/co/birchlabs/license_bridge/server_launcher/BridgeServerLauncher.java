package uk.co.birchlabs.license_bridge.server_launcher;

import uk.co.birchlabs.license_bridge.client.CheckReachability;
import uk.co.birchlabs.license_bridge.client.CheckReachability.ReachResult;
import uk.co.birchlabs.license_bridge.common.BridgeServerAPI;
import uk.co.birchlabs.license_bridge.common.BridgeServerConstants;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class BridgeServerLauncher {
    public static Path getInstallDir() {
        return getInstallDir("4.2");
    }
    public static Path getInstallDir(String version) {
        return Path.of(format("/Applications/Live2D Cubism %s", version));
    }

    public static Path getBundledJreHome(Path installDir) {
        return installDir.resolve("jre/Contents/Home/jre");
    }

    public static Path getJavaBinary(Path javaHome) {
        return javaHome.resolve("bin/java");
    }

    public static Path getResDir(Path installDir) {
        return installDir.resolve("res");
    }

    public static Path getBundledJar(Path resDir, String jar) {
        return resDir.resolve(format("%s.jar", jar));
    }

    public static Path getServerDependency(Path bridgeServerLauncherDir, String jar) {
        return bridgeServerLauncherDir.resolve(format("target/dependency/%s.jar", jar));
    }

    public static Process launch() throws IOException {
        return launch(Path.of("."));
    }

    public static Process launch(Path bridgeServerLauncherDir) throws IOException {
        return launch(bridgeServerLauncherDir, getInstallDir());
    }

    public static Process launch(Path bridgeServerLauncherDir, Path installDir) throws IOException {
        return launch(bridgeServerLauncherDir, installDir, getJavaBinary(getBundledJreHome(installDir)));
    }

    public static Process launch(Path bridgeServerLauncherDir, Path installDir, Path javaBinary) throws IOException {
        final var pb = new ProcessBuilder(
                javaBinary.toString(),
                "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006",
                format("-Djava.library.path=%s", getResDir(installDir)),
                "-cp",
                new StringJoiner(":")
                        .add(getServerDependency(bridgeServerLauncherDir, "license-bridge-common").toString())
                        .add(getServerDependency(bridgeServerLauncherDir, "license-bridge-server").toString())
                        .add(getBundledJar(getResDir(installDir), "rlm1221").toString())
                        .toString(),
                "uk.co.birchlabs.license_bridge.server.BridgeServerMain"
        )
                .inheritIO();
        return pb.start();
    }

    public static CompletableFuture<Void> getServerReadyFuture() {
        final var executor = Executors.newSingleThreadScheduledExecutor();
        final var completionFuture = new CompletableFuture<Void>();
        final var checkFuture = executor.scheduleAtFixedRate(() -> {
            ReachResult result = CheckReachability.checkReachability();
            switch (result.outcome) {
                case Success -> completionFuture.complete(null);
                case MalformedResponse -> completionFuture.completeExceptionally(new IllegalStateException(format("Malformed response: %s", result.malformedResponse)));
                case NotBound -> System.out.println("License bridge server not yet reachable. Will poll again.");
                case Error -> completionFuture.completeExceptionally(result.error);
                default -> throw new IllegalArgumentException(format("not implemented outcome: %s", result.outcome.name()));
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
        completionFuture.whenComplete((result, thrown) -> checkFuture.cancel(true));
        return completionFuture;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Launching x86_64 -> aarch64 bridge server for license-handling...");
        launch();
        System.out.println("License bridge server launched. Awaiting readiness...");
        getServerReadyFuture().join();
        System.out.println("License bridge server ready!");
    }
}
