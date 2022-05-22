# Unofficial M1 macOS Launcher for Live2D Cubism Editor

_This project is not affiliated with Live2D._    
_For personal use. Please adhere to the license terms of Live2D Cubism Editor._

JDK17. arm64-native. Metal. Graal JIT.  
Fast.

This **unofficial** launcher improves the performance of [Live2D Cubism Editor](https://www.live2d.com/en/download/cubism/) on M1 macOS.

## Install dependencies

Install [Live2D Cubism Editor](https://www.live2d.com/en/download/cubism/) such that the following directory exists:

```
/Applications/Live2D Cubism 4.1/res
```

Install [Azul JDK17](https://www.azul.com/downloads/?version=java-17-lts&os=macos&architecture=arm-64-bit&package=jdk-fx) (ARM 64-bit, with Java FX).  
Get the `.tar.gz` distribution. Extract it such that a folder with a name like the following exists:

```
~/Downloads/zulu17.30.51-ca-fx-jdk17.0.1-macosx_aarch64/zulu-17.jdk/Contents/Home
```

Clone this repository:

```bash
git clone https://github.com/Birch-san/arm-editor-launcher.git
cd arm-editor-launcher
```

Download a maven executable and the jogamp libraries; install them to your local maven registry:

```bash
./install-maven.sh
JAVA_HOME="$HOME/Downloads/zulu17.30.51-ca-fx-jdk17.0.1-macosx_aarch64" ./install-jogl.sh
```

### Option 1. Install & run with IntelliJ

Open this repository in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=mac) (Community Edition is fine).  
Set the Project JDK to the JDK17 distribution you just downloaded.

Use the Maven toolwindow to run a root-level `mvn install`.

Hit "Reload All Maven projects".

Then run the "Unofficial Launcher for Live2D Cubism Editor aarch64" run configuration.

<img src="https://user-images.githubusercontent.com/6141784/151722190-18e16caf-205f-4b40-83ed-a44af3a3aa26.png" width="500px">

Congratulations! You are now running natively.

### Option 2. Install & run from command-line

```bash
export JAVA_HOME="$HOME/Downloads/zulu17.30.51-ca-fx-jdk17.0.1-macosx_aarch64"
MVN_HOME="${MVN_HOME:-"./mvn"}"
MVN="$MVN_HOME/bin/mvn"
"$MVN" install
./launcher.sh
```