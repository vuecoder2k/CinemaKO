#!/bin/bash

set -e

PROJECT_ROOT="$(cd "$(dirname "$0")" && pwd)"
OUT_DIR="$PROJECT_ROOT/out"

if [ ! -d "$OUT_DIR" ]; then
    echo "Build directory not found."
    echo "Running build first..."
    "$PROJECT_ROOT/build.sh"
fi

echo "Starting Cinema Application..."
echo

java -cp "$OUT_DIR" com.cinemako.Main
