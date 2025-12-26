#!/bin/bash
# ProcessPilot Build Script for Linux/Mac

# Colors
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Create build directory
if [ ! -d "build" ]; then
    echo "Creating build directory..."
    mkdir build
fi

echo ""
echo "╔════════════════════════════════════════════════════════════════════╗"
echo "║                    PROCESSPILOT BUILD SCRIPT                       ║"
echo "╚════════════════════════════════════════════════════════════════════╝"
echo ""

echo "Compiling source files..."
echo ""

# Compile models
javac -d build src/models/*.java
if [ $? -ne 0 ]; then
    echo -e "${RED}✗ Error compiling models${NC}"
    exit 1
fi

# Compile algorithms
javac -d build -cp build src/algorithms/*.java
if [ $? -ne 0 ]; then
    echo -e "${RED}✗ Error compiling algorithms${NC}"
    exit 1
fi

# Compile visualization
javac -d build -cp build src/visualization/*.java
if [ $? -ne 0 ]; then
    echo -e "${RED}✗ Error compiling visualization${NC}"
    exit 1
fi

# Compile utils
javac -d build -cp build src/utils/*.java
if [ $? -ne 0 ]; then
    echo -e "${RED}✗ Error compiling utils${NC}"
    exit 1
fi

# Compile main class
javac -d build -cp build src/ProcessPilot.java
if [ $? -ne 0 ]; then
    echo -e "${RED}✗ Error compiling ProcessPilot${NC}"
    exit 1
fi

echo ""
echo "╔════════════════════════════════════════════════════════════════════╗"
echo "║                  ✓ BUILD SUCCESSFUL!                              ║"
echo "╚════════════════════════════════════════════════════════════════════╝"
echo ""
echo "To run the application, execute: ./run.sh"
echo ""
