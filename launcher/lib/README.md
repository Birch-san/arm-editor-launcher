# Jpen for ARM macOS

This is the questionable arm64 build I made of libjpen. See branch:  
https://github.com/Birch-san/jpen/tree/arm64

It still looks pretty unhappy:

```
ERROR [ Jan 21, 2022 12:45:05 AM jpen.provider.NativeLibraryLoader$4 run ] at jp.noids.ui.c.f$a (f$a.kt:-1) write()
ERROR [ INFO: loading JPen 2.1-SNAPSHOT JNI library: jpen-2-4 ... ] at jp.noids.ui.c.f$a (f$a.kt:-1) write()
ERROR [ Jan 21, 2022 12:45:05 AM jpen.provider.NativeLibraryLoader$4 run ] at jp.noids.ui.c.f$a (f$a.kt:-1) write()
ERROR [ INFO: jpen-2-4 loaded ] at jp.noids.ui.c.f$a (f$a.kt:-1) write()
ERROR [ Jan 21, 2022 12:45:05 AM jpen.provider.NativeLibraryLoader$3 run ] at jp.noids.ui.c.f$a (f$a.kt:-1) write()
ERROR [ INFO: preferred architecture set ] at jp.noids.ui.c.f$a (f$a.kt:-1) write()
```

But at least it says "loaded", which is better than what you get if you give it the x86_64 distribution.

[Jpen](http://jpen.sourceforge.net/html-home/index.html) is [LGPL-licensed](http://jpen.sourceforge.net/html-home/node49.html).

Did you know that if libjpen fails to load: Live2D Cubism Editor runs **far** faster?  
This is why I provide two run configurations (default does not enable libjpen).

You can mitigate this somewhat by hitting the "fullscreen" button.