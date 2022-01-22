package uk.co.birchlabs;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.nullability.MaybeNull;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

public class LocateAppleEawtClassesAgent {
    /**
     * As soon as the JVM initializes, This  method will be called.
     * @param agentArgs       The list of agent arguments
     * @param instrumentation The instrumentation object
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .type(named("com.live2d.util.f.h"))
                .transform((DynamicType.Builder<?> builder,
                            TypeDescription typeDescription,
                            @MaybeNull ClassLoader classLoader,
                            @MaybeNull JavaModule module) ->
                                builder.method(takesArguments(String.class))
                                        .intercept(
                                                MethodDelegation.to(ClassLocator.class)
                                        )
                ).installOn(instrumentation);
    }

    public static class ClassLocator {
        private static final Set<String> whitelist = Set.of("com.apple.eawt.Application");
        private static final Map<String, Class<?>> delegates = new HashMap<>();
        static {
            try {
                delegates.put("com.apple.eawt.QuitHandler", java.awt.desktop.QuitHandler.class);
                delegates.put("com.apple.eawt.QuitResponse", Class.forName("com.apple.eawt.MacQuitResponse"));
                for (String className : whitelist) {
                    delegates.put(className, Class.forName(className));
                }
            } catch(ClassNotFoundException err) {
                throw new RuntimeException(err);
            }
        }
        public static Class<?> a(String x) {
            if (delegates.containsKey(x)) {
                return delegates.get(x);
            }
            throw new RuntimeException("No delegate for class: " + x);
        }
    }
}
