package uk.co.birchlabs;

import com.jogamp.common.util.cache.TempJarCache;
import com.live2d.cubism.CECubismEditorApp;
import com.jogamp.common.jvm.JNILibLoaderBase;

public class Main {
    public static void main(String[] args) {
        if (TempJarCache.initSingleton() && TempJarCache.isInitialized(true)) {
            // this is effectively a way to say
            //   System.loadLibrary("gluegen_rt");
            // without having to extract libgluegen_rt.dylib to a directory on your java.lib.path
            JNILibLoaderBase.addNativeJarLibs(new Class<?>[] { jogamp.common.Debug.class }, null);
        } else {
            System.err.println("Failed to initialize TempJarCache. This isn't a problem, but we were hoping to make use of it to prevent a (harmless) UnsatisfiedLinkError.");
        }
        CECubismEditorApp.main(args);
    }
}
