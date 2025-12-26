#!/bin/bash
# ProcessPilot - GUI Build Script for Linux/Mac
# This script compiles and runs the JavaFX GUI version

# Set paths - MODIFY THESE if JavaFX is in a different location
JAVAFX_SDK="~/javafx-sdk-21"  # or /opt/javafx-sdk-21 on Linux
BUILD_DIR="build"
SRC_DIR="src"
RESOURCES_DIR="resources"

echo ""
echo "========================================"
echo "   ProcessPilot GUI - Build & Run"
echo "========================================"
echo ""

# Expand ~ to home directory
JAVAFX_SDK="${JAVAFX_SDK/\~/$HOME}"

# Check if JavaFX SDK exists
if [ ! -f "$JAVAFX_SDK/lib/javafx.base.jar" ]; then
    echo "ERROR: JavaFX SDK not found at $JAVAFX_SDK"
    echo "Please download JavaFX SDK from https://gluonhq.com/products/javafx/"
    echo "and update the JAVAFX_SDK variable in this script."
    echo ""
    exit 1
fi

echo "[1/3] Creating build directory..."
mkdir -p "$BUILD_DIR"
echo "        ✓ Build directory ready"

echo ""
echo "[2/3] Compiling source files with JavaFX..."
echo "        Compiling models..."
javac --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -d "$BUILD_DIR" \
    "$SRC_DIR"/models/*.java

if [ $? -ne 0 ]; then
    echo ""
    echo "ERROR: Compilation of models failed!"
    exit 1
fi

echo "        Compiling algorithms..."
javac --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -d "$BUILD_DIR" \
    -cp "$BUILD_DIR" \
    "$SRC_DIR"/algorithms/*.java

if [ $? -ne 0 ]; then
    echo ""
    echo "ERROR: Compilation of algorithms failed!"
    exit 1
fi

echo "        Compiling visualization..."
javac --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -d "$BUILD_DIR" \
    -cp "$BUILD_DIR" \
    "$SRC_DIR"/visualization/*.java

if [ $? -ne 0 ]; then
    echo ""
    echo "ERROR: Compilation of visualization failed!"
    exit 1
fi

echo "        Compiling utilities..."
javac --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -d "$BUILD_DIR" \
    -cp "$BUILD_DIR" \
    "$SRC_DIR"/utils/*.java

if [ $? -ne 0 ]; then
    echo ""
    echo "ERROR: Compilation of utilities failed!"
    exit 1
fi

echo "        Compiling UI..."
javac --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -d "$BUILD_DIR" \
    -cp "$BUILD_DIR" \
    "$SRC_DIR"/ui/*.java

if [ $? -ne 0 ]; then
    echo ""
    echo "ERROR: Compilation of UI failed!"
    exit 1
fi

echo "        ✓ Compilation successful"

echo ""
echo "[3/3] Copying resources..."
mkdir -p "$BUILD_DIR/resources"
cp "$RESOURCES_DIR/main.fxml" "$BUILD_DIR/resources/" 2>/dev/null
cp "$RESOURCES_DIR/style.css" "$BUILD_DIR/resources/" 2>/dev/null
echo "        ✓ Resources copied"

echo ""
echo "========================================"
echo "   Build Complete! Starting application..."
echo "========================================"
echo ""

# Run the application
java --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -cp "$BUILD_DIR:$BUILD_DIR/resources" ui.MainApp

if [ $? -ne 0 ]; then
    echo ""
    echo "ERROR: Application failed to start!"
    echo "Check that:"
    echo "   1. JavaFX SDK path is correct"
    echo "   2. All required files are in place"
    echo "   3. Java version is 11 or higher"
    exit 1
fi
