package uk.co.birchlabs;

import com.jogamp.common.util.cache.TempJarCache;
import com.live2d.cubism.CECubismEditorApp;
import com.jogamp.common.jvm.JNILibLoaderBase;
import uk.co.birchlabs.license_bridge.client.CheckReachability;
import uk.co.birchlabs.license_bridge.client.CheckReachability.ReachResult;
import uk.co.birchlabs.license_bridge.server_launcher.BridgeServerLauncher;

import java.io.IOException;
import java.nio.file.Path;

import static java.lang.String.format;
import static uk.co.birchlabs.license_bridge.client.CheckReachability.ReachResult.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (TempJarCache.initSingleton() && TempJarCache.isInitialized(true)) {
            // this is effectively a way to say
            //   System.loadLibrary("gluegen_rt");
            // without having to extract libgluegen_rt.dylib to a directory on your java.lib.path
            JNILibLoaderBase.addNativeJarLibs(new Class<?>[] { jogamp.common.Debug.class }, null);
        } else {
            System.err.println("Failed to initialize TempJarCache. This isn't a problem, but we were hoping to make use of it to prevent a (harmless) UnsatisfiedLinkError.");
        }
        System.out.println("Checking reachability of x86_64 -> aarch64 bridge server for license-handling...");
        ReachResult result = CheckReachability.checkReachability();
        switch (result.outcome) {
            case Success -> {
            }
            case MalformedResponse -> throw new IllegalStateException(format("Malformed response: %s", result.malformedResponse));
            case NotBound -> {
                System.out.println("Launching x86_64 -> aarch64 bridge server for license-handling...");
                final var process = BridgeServerLauncher.launch(Path.of("../license-bridge-server-launcher"));
                Runtime.getRuntime().addShutdownHook(new Thread(process::destroy));
                System.out.println("License bridge server launched. Awaiting readiness...");
                BridgeServerLauncher.getServerReadyFuture().join();
            }
            case Error -> throw result.error;
        }
        System.out.println("License bridge server ready!");
        CECubismEditorApp.main(args);
    }
}
