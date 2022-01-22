#!/usr/bin/env bash

# see:
# https://github.com/jzy3d/jogl-maven-deployer/blob/main/maven-install.sh

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
JAVA_HOME="$HOME/Downloads/zulu17.30.51-ca-fx-jdk17.0.1-macosx_aarch64" ./install-jogl.sh
"
}

echo "$JAVA_HOME"

MVN_HOME="${MVN_HOME:-"${DIR}/mvn"}"
MVN="$MVN_HOME/bin/mvn"

if [ -z "$JAVA_HOME" ]; then
  >&2 echo -e "${Red}JAVA_HOME env var not set${NC}"
  print_help
  exit 1
fi

OUT="jogamp-rc4"
ARCHIVE=jogamp-all-platforms-rc4-bigsur
JAR_DIR="$ARCHIVE/jar"

curl -o jogamp-rc4.zip "https://download.jzy3d.org/jogl/$ARCHIVE.zip"
unzip jogamp-rc4.zip "$JAR_DIR/*" -d jogamp-rc4

LIB="$OUT/$JAR_DIR"

version="v2.4.0-rc4"

"$MVN" install:install-file -DgroupId=org.jogamp.gluegen -DartifactId=gluegen-rt                                       -Dversion="$version" -Dfile="${LIB}/gluegen-rt.jar"                          -Dpackaging=jar
"$MVN" install:install-file -DgroupId=org.jogamp.gluegen -DartifactId=gluegen-rt -Dclassifier=natives-macosx-universal -Dversion="$version" -Dfile="${LIB}/gluegen-rt-natives-macosx-universal.jar" -Dpackaging=jar
"$MVN" install:install-file -DgroupId=org.jogamp.jogl -DartifactId=jogl-all                                            -Dversion="$version" -Dfile="${LIB}/jogl-all.jar"                            -Dpackaging=jar
"$MVN" install:install-file -DgroupId=org.jogamp.jogl -DartifactId=jogl-all      -Dclassifier=natives-macosx-universal -Dversion="$version" -Dfile="${LIB}/jogl-all-natives-macosx-universal.jar"   -Dpackaging=jar