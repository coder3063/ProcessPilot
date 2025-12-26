@echo off
REM ProcessPilot GUI - Build and Run Script
echo Building GUI Application...
javac --module-path "i:/javafx-sdk-20.0.2/lib" --add-modules javafx.controls,javafx.fxml -cp resources -d build ^
    src/ui/MainApp.java src/ui/Controller.java src/models/*.java src/algorithms/*.java src/utils/*.java src/visualization/*.java

if errorlevel 1 (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Build successful! Launching GUI...
java --module-path "i:/javafx-sdk-20.0.2/lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics -cp "build;resources" ui.MainApp
