#!/bin/bash

# Dừng ngay nếu có lỗi
set -e

PROJECT_ROOT="$(cd "$(dirname "$0")" && pwd)"
SRC_DIR="$PROJECT_ROOT/src"
OUT_DIR="$PROJECT_ROOT/out"

echo "================================"
echo "       Cinema Project Build"
echo "================================"

# Xóa thư mục build cũ
echo "[1/3] Cleaning..."
rm -rf "$OUT_DIR"

# Tạo thư mục output
mkdir -p "$OUT_DIR"

# Tìm tất cả file .java
echo "[2/3] Compiling..."

JAVA_FILES=$(find "$SRC_DIR" -name "*.java")

if [ -z "$JAVA_FILES" ]; then
    echo "ERROR: No Java source files found!"
    exit 1
fi

javac -d "$OUT_DIR" $JAVA_FILES

echo "[3/3] Build successful!"

echo
echo "Output directory:"
echo "$OUT_DIR"

echo
echo "Run application:"
echo "java -cp out com.cinemako.Main"
