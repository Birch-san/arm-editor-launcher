#!/usr/bin/env bash

set -eo pipefail
DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

OUT="$DIR/mvn"
mkdir -p "$OUT"
curl -Ss https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz | tar -xvz -C "$OUT" --strip-components 1