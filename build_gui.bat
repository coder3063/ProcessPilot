@echo off
REM ProcessPilot - GUI Build Script for Windows
REM This script compiles and runs the JavaFX GUI version

setlocal enabledelayedexpansion

REM Set paths - MODIFY THESE if JavaFX is in a different location
set JAVAFX_SDK=C:\javafx-sdk-21
set BUILD_DIR=build
set SRC_DIR=src
set RESOURCES_DIR=resources

echo.
echo ========================================
echo   ProcessPilot GUI - Build & Run
echo ========================================
echo.

REM Check if JavaFX SDK exists
if not exist "%JAVAFX_SDK%\lib\javafx.base.jar" (
    echo ERROR: JavaFX SDK not found at %JAVAFX_SDK%
    echo Please download JavaFX SDK from https://gluonhq.com/products/javafx/
    echo and update the JAVAFX_SDK variable in this script.
    echo.
    pause
    exit /b 1
)

echo [1/3] Creating build directory...
if not exist "%BUILD_DIR%" mkdir "%BUILD_DIR%"
echo        ✓ Build directory ready

echo.
echo [2/3] Compiling source files with JavaFX...
echo        Command: javac --module-path "%JAVAFX_SDK%\lib" --add-modules javafx.controls,javafx.fxml -d "%BUILD_DIR%" ...
javac --module-path "%JAVAFX_SDK%\lib" --add-modules javafx.controls,javafx.fxml -d "%BUILD_DIR%" ^
    "%SRC_DIR%\models\*.java" ^
    "%SRC_DIR%\algorithms\*.java" ^
    "%SRC_DIR%\visualization\*.java" ^
    "%SRC_DIR%\utils\*.java" ^
    "%SRC_DIR%\ui\*.java"

if errorlevel 1 (
    echo.
    echo ERROR: Compilation failed!
    echo Make sure all source files are in the correct directories.
    pause
    exit /b 1
)
echo        ✓ Compilation successful

echo.
echo [3/3] Copying resources...
if not exist "%BUILD_DIR%\resources" mkdir "%BUILD_DIR%\resources"
copy "%RESOURCES_DIR%\main.fxml" "%BUILD_DIR%\resources\" >nul 2>&1
copy "%RESOURCES_DIR%\style.css" "%BUILD_DIR%\resources\" >nul 2>&1
echo        ✓ Resources copied

echo.
echo ========================================
echo   Build Complete! Starting application...
echo ========================================
echo.

REM Run the application
java --module-path "%JAVAFX_SDK%\lib" --add-modules javafx.controls,javafx.fxml -cp "%BUILD_DIR%;%BUILD_DIR%\resources" ui.MainApp

if errorlevel 1 (
    echo.
    echo ERROR: Application failed to start!
    echo Check that:
    echo   1. JavaFX SDK path is correct
    echo   2. All required files are in place
    echo   3. Java version is 11 or higher
    pause
    exit /b 1
)

endlocal
