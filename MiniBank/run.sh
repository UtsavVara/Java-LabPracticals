#!/usr/bin/env sh
set -eu
mkdir -p out
javac -d out $(find src -name '*.java')
java -cp out minibank.MiniBank
