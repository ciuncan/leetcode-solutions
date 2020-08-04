#!/bin/bash

files=$(find src -name '*.scala' | tr '\n' ' ')
classpath=$(sbt 'export test:fullClasspath' | tail -n 1)

# shellcheck disable=SC2086
dotc \
  -feature \
  -rewrite \
  -indent \
  -language:implicitConversions \
  -classpath "$classpath" \
  $files

# shellcheck disable=SC2086
dotc \
  -feature \
  -rewrite \
  -new-syntax \
  -language:implicitConversions \
  -classpath "$classpath" \
  $files

rm -rf ciuncan
