#!/usr/bin/env bash
set -eo pipefail
DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

CTRL='\033' # begin escape sequence
BC="${CTRL}[0;" # begin colour modification
Red="${BC}31m"
Purple="${BC}35m"
Cyan="${BC}36m"
NC="${BC}0m" # No Color

function print_help {
  >&2 echo -e "${Cyan}Example invocation:${NC}
${Purple}# this is an *example* filepath; update it to match the JDK17 folder you extracted${NC}
JAVA_HOME=\"\$HOME/Downloads/zulu17-macosx_aarch64\" ./launcher.sh
"
}

if [ -z "$JAVA_HOME" ]; then
  >&2 echo -e "${Red}JAVA_HOME env var not set${NC}"
  print_help
  exit 1
fi

JAVA="$JAVA_HOME/bin/java"

CUBISM_EDITOR_DIR="/Applications/Live2D Cubism 4.2"
CUBISM_EDITOR_LIB_DIR="$CUBISM_EDITOR_DIR/res"
LAUNCHER_DIR="$DIR/launcher"
LAUNCHER_LIB_DIR="$LAUNCHER_DIR/lib"
LAUNCHER_TARGET="$LAUNCHER_DIR/target"
M2_REPO="$HOME/.m2/repository"

CUBISM_EDITOR_LIBS=(
'Live2DCubismCore.jar'
'Live2D_Cubism.jar'
'annotations-13.0.jar'
'basicplayer3.0.jar'
'commons-beanutils-1.9.4.jar'
'commons-collections4-4.4.jar'
'commons-lang3-3.9.jar'
'commons-logging-api.jar'
'commons-text-1.8.jar'
'jdom-1.1.jar'
'jl1.0.jar'
'jna-5.6.0.jar'
'jna-platform-5.6.0.jar'
'jogg-0.0.7.jar'
'jorbis-0.0.15.jar'
'json-simple-1.1.jar'
'jsonic-1.3.10.jar'
'kotlin-reflect-1.3.72.jar'
'kotlin-stdlib-1.3.72.jar'
'kotlin-stdlib-common-1.3.72.jar'
'kotlin-stdlib-jdk7-1.3.72.jar'
'kotlin-stdlib-jdk8-1.3.72.jar'
'log4j-api-2.17.0.jar'
'log4j-core-2.17.0.jar'
'log4j-iostreams-2.17.0.jar'
'mp3spi1.9.4.jar'
'opencsv-5.2.jar'
'rlm1221.jar'
'tritonus_share.jar'
'vorbisspi1.0.2.jar'
)
MVN_LIBS=(
'org/jogamp/jogl/jogl-all/v2.4.0-rc4/jogl-all-v2.4.0-rc4.jar'
'org/jogamp/jogl/jogl-all/v2.4.0-rc4/jogl-all-v2.4.0-rc4-natives-macosx-universal.jar'
'net/bytebuddy/byte-buddy/1.12.7/byte-buddy-1.12.7.jar'
'org/jogamp/gluegen/gluegen-rt/v2.4.0-rc4/gluegen-rt-v2.4.0-rc4.jar'
'org/jogamp/gluegen/gluegen-rt/v2.4.0-rc4/gluegen-rt-v2.4.0-rc4-natives-macosx-universal.jar'
'javax/activation/activation/1.1.1/activation-1.1.1.jar'
)
CLASSPATH=(
"$DIR/license-bridge-common/target/license-bridge-common-1.0-SNAPSHOT.JAR"
# deliberately *before* rlm1221.jar; replaces some classes with ones that forward calls to an Intel JVM hosting librlm1221.jnilib
"$DIR/license-bridge-client/target/license-bridge-client-1.0-SNAPSHOT.JAR"
"$DIR/license-bridge-server-launcher/target/license-bridge-server-launcher-1.0-SNAPSHOT.JAR"
"${CUBISM_EDITOR_LIBS[@]/#/$CUBISM_EDITOR_LIB_DIR/}"
"${MVN_LIBS[@]/#/$M2_REPO/}"
"$LAUNCHER_LIB_DIR/jpen-2.1-SNAPSHOT.jar"
"$LAUNCHER_TARGET/launcher-1.0-SNAPSHOT.jar"
)
CLASSPATH_STR=$(IFS=: ; echo "${CLASSPATH[*]}")

NATIVE_LIB_DIRS=(
# uncomment to load libjpen (may reduce performance):
#"$LAUNCHER_LIB_DIR"
)
NATIVE_LIB_STR=$(IFS=: ; echo "${NATIVE_LIB_DIRS[*]}")

# change working directory to launcher, because it locates license-bridge-server-launcher/target/dependency/license-bridge-*.jar
# relative to working directory (i.e. expects to be run from this monorepo, with all sibling artifacts packaged)
# yes I'm aware it would be better to just expose a CLI argument to parameterize this but I dislike making CLIs
cd "$LAUNCHER_DIR"

exec "$JAVA" \
-cp "$CLASSPATH_STR" \
-javaagent:"$LAUNCHER_TARGET/dependency/agent.jar" \
--patch-module jdk.unsupported="$LAUNCHER_TARGET/dependency/jdk-unsupported.jar" \
--add-exports java.desktop/com.apple.eawt=ALL-UNNAMED \
--module-path="$LAUNCHER_TARGET/graal/graal-sdk.jar:$LAUNCHER_TARGET/graal/truffle-api.jar" \
--upgrade-module-path="$LAUNCHER_TARGET/graal/compiler.jar:$LAUNCHER_TARGET/graal/compiler-management.jar" \
-XX:+UnlockExperimentalVMOptions \
-XX:+EnableJVMCI \
-XX:+UseJVMCICompiler \
-XX:+EagerJVMCI \
-Dgraal.ShowConfiguration=info \
-Dsun.java2d.metal=true \
-Xdock:name="Unofficial Launcher for Live2D Cubism Editor" \
-Djava.library.path="$NATIVE_LIB_STR" \
uk.co.birchlabs.Main