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
${Purple}# this is an *example* filepath; update it to match the JDK17 folder you extracted${NC}
JAVA_HOME=\"\$HOME/Downloads/zulu17-macosx_aarch64\" ./launcher.sh
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

# leave our root directory, because the presence of its pom.xml causes install:install-file to do more than we intended
cd "$OUT"

version="v2.4.0-rc4"

"$MVN" install:install-file -DgroupId=org.jogamp.gluegen -DartifactId=gluegen-rt                                       -Dversion="$version" -Dfile="${JAR_DIR}/gluegen-rt.jar"                          -Dpackaging=jar
"$MVN" install:install-file -DgroupId=org.jogamp.gluegen -DartifactId=gluegen-rt -Dclassifier=natives-macosx-universal -Dversion="$version" -Dfile="${JAR_DIR}/gluegen-rt-natives-macosx-universal.jar" -Dpackaging=jar
"$MVN" install:install-file -DgroupId=org.jogamp.jogl -DartifactId=jogl-all                                            -Dversion="$version" -Dfile="${JAR_DIR}/jogl-all.jar"                            -Dpackaging=jar
"$MVN" install:install-file -DgroupId=org.jogamp.jogl -DartifactId=jogl-all      -Dclassifier=natives-macosx-universal -Dversion="$version" -Dfile="${JAR_DIR}/jogl-all-natives-macosx-universal.jar"   -Dpackaging=jar