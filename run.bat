@echo off
REM ProcessPilot Run Script for Windows

setlocal enabledelayedexpansion

cd /d "%~dp0"

if not exist build (
    echo ✗ Build directory not found. Please run build.bat first.
    pause
    exit /b 1
)

echo.
echo ╔════════════════════════════════════════════════════════════════════╗
echo ║                   LAUNCHING PROCESSPILOT                           ║
echo ╚════════════════════════════════════════════════════════════════════╝
echo.

java -cp build ProcessPilot

pause
