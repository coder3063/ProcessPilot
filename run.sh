#!/bin/bash
# ProcessPilot Run Script for Linux/Mac

# Colors
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

if [ ! -d "build" ]; then
    echo -e "${RED}✗ Build directory not found. Please run build.sh first.${NC}"
    exit 1
fi

echo ""
echo "╔════════════════════════════════════════════════════════════════════╗"
echo "║                   LAUNCHING PROCESSPILOT                           ║"
echo "╚════════════════════════════════════════════════════════════════════╝"
echo ""

java -cp build ProcessPilot
