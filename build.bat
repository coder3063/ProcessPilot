@echo off
REM ProcessPilot Build Script for Windows
REM Compiles all Java source files

setlocal enabledelayedexpansion

if not exist build (
    echo Creating build directory...
    mkdir build
)

echo.
echo ╔════════════════════════════════════════════════════════════════════╗
echo ║                    PROCESSPILOT BUILD SCRIPT                       ║
echo ╚════════════════════════════════════════════════════════════════════╝
echo.

echo Compiling source files...
echo.

cd /d "%~dp0"

REM Compile all Java files
javac -d build src/models/*.java
if errorlevel 1 (
    echo ✗ Error compiling models
    exit /b 1
)

javac -d build -cp build src/algorithms/*.java
if errorlevel 1 (
    echo ✗ Error compiling algorithms
    exit /b 1
)

javac -d build -cp build src/visualization/*.java
if errorlevel 1 (
    echo ✗ Error compiling visualization
    exit /b 1
)

javac -d build -cp build src/utils/*.java
if errorlevel 1 (
    echo ✗ Error compiling utils
    exit /b 1
)

javac -d build -cp build src/ProcessPilot.java
if errorlevel 1 (
    echo ✗ Error compiling ProcessPilot main class
    exit /b 1
)

echo.
echo ╔════════════════════════════════════════════════════════════════════╗
echo ║                  ✓ BUILD SUCCESSFUL!                              ║
echo ╚════════════════════════════════════════════════════════════════════╝
echo.
echo To run the application, execute: run.bat
echo.

pause
