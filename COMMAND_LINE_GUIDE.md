# ProcessPilot GUI - Command Line Compilation & Execution

## 📋 Prerequisites

1. **Java Development Kit (JDK)** version 11 or higher
   - Check: `java -version`
   - Download: https://www.oracle.com/java/technologies/javase-downloads.html

2. **JavaFX SDK** version 21 (or compatible)
   - Download: https://gluonhq.com/products/javafx/
   - Extract to a folder (e.g., `C:\javafx-sdk-21`)

---

## 🪟 Windows Instructions

### Step 1: Set Environment Variables (Optional but Recommended)

Open Command Prompt as Administrator:
```batch
setx JAVAFX_SDK "C:\javafx-sdk-21"
setx JAVA_HOME "C:\Program Files\Java\jdk-21"
```

Then restart Command Prompt for changes to take effect.

### Step 2: Navigate to Project

```batch
cd D:\ProcessPilot
```

### Step 3: Compile All Files

**Option A: Using set JAVAFX_SDK**

If you ran the setx commands above:
```batch
javac --module-path "%JAVAFX_SDK%\lib" --add-modules javafx.controls,javafx.fxml -d build src\models\*.java src\algorithms\*.java src\visualization\*.java src\utils\*.java src\ui\*.java
```

**Option B: Using absolute path (no environment variable)**

```batch
javac --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -d build src\models\*.java src\algorithms\*.java src\visualization\*.java src\utils\*.java src\ui\*.java
```

### Step 4: Copy Resources

```batch
if not exist "build\resources" mkdir "build\resources"
copy resources\main.fxml build\resources\
copy resources\style.css build\resources\
```

### Step 5: Run the Application

```batch
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -cp "build;build\resources" ui.MainApp
```

### Simplified Script (Save as `run_gui.bat`)

```batch
@echo off
set JAVAFX_SDK=C:\javafx-sdk-21

REM Compile
echo Compiling...
javac --module-path "%JAVAFX_SDK%\lib" --add-modules javafx.controls,javafx.fxml -d build src\models\*.java src\algorithms\*.java src\visualization\*.java src\utils\*.java src\ui\*.java

if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b 1
)

REM Copy resources
if not exist "build\resources" mkdir "build\resources"
copy resources\main.fxml build\resources\ >nul 2>&1
copy resources\style.css build\resources\ >nul 2>&1

REM Run
echo Running ProcessPilot GUI...
java --module-path "%JAVAFX_SDK%\lib" --add-modules javafx.controls,javafx.fxml -cp "build;build\resources" ui.MainApp
```

---

## 🐧 Linux Instructions

### Step 1: Set Environment Variables (Optional but Recommended)

Edit `~/.bashrc`:
```bash
export JAVAFX_SDK="$HOME/javafx-sdk-21"
export JAVA_HOME="/usr/lib/jvm/java-21-openjdk"
```

Apply changes:
```bash
source ~/.bashrc
```

### Step 2: Navigate to Project

```bash
cd ~/ProcessPilot
# or wherever your project is located
```

### Step 3: Create Build Directory

```bash
mkdir -p build/resources
```

### Step 4: Compile All Files

**Option A: Using environment variable**

```bash
javac --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -d build \
    src/models/*.java \
    src/algorithms/*.java \
    src/visualization/*.java \
    src/utils/*.java \
    src/ui/*.java
```

**Option B: Using absolute path**

```bash
javac --module-path "$HOME/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.fxml \
    -d build \
    src/models/*.java \
    src/algorithms/*.java \
    src/visualization/*.java \
    src/utils/*.java \
    src/ui/*.java
```

### Step 5: Copy Resources

```bash
cp resources/main.fxml build/resources/
cp resources/style.css build/resources/
```

### Step 6: Run the Application

```bash
java --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -cp "build:build/resources" ui.MainApp
```

### Simplified Script (Save as `run_gui.sh`)

```bash
#!/bin/bash

export JAVAFX_SDK="$HOME/javafx-sdk-21"

# Create build directory
mkdir -p build/resources

# Compile
echo "Compiling..."
javac --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -d build \
    src/models/*.java \
    src/algorithms/*.java \
    src/visualization/*.java \
    src/utils/*.java \
    src/ui/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

# Copy resources
cp resources/main.fxml build/resources/
cp resources/style.css build/resources/

# Run
echo "Running ProcessPilot GUI..."
java --module-path "$JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml \
    -cp "build:build/resources" ui.MainApp
```

Make executable:
```bash
chmod +x run_gui.sh
```

Run:
```bash
./run_gui.sh
```

---

## 🍎 macOS Instructions

### Step 1: Install JavaFX

Using Homebrew (recommended):
```bash
brew tap javafxports/tap
brew install javafx-sdk
```

Or download manually from https://gluonhq.com/products/javafx/

### Step 2: Set Environment Variables

Edit `~/.zshrc` (newer Macs) or `~/.bash_profile`:
```bash
export JAVAFX_SDK="/Library/Frameworks/JavaFX/lib"
```

Or if installed via Homebrew:
```bash
export JAVAFX_SDK="/usr/local/opt/javafx-sdk/lib"
```

Apply:
```bash
source ~/.zshrc
# or
source ~/.bash_profile
```

### Step 3: Navigate and Compile

```bash
cd ~/ProcessPilot
mkdir -p build/resources

javac --module-path "$JAVAFX_SDK" --add-modules javafx.controls,javafx.fxml \
    -d build \
    src/models/*.java \
    src/algorithms/*.java \
    src/visualization/*.java \
    src/utils/*.java \
    src/ui/*.java
```

### Step 4: Copy Resources

```bash
cp resources/main.fxml build/resources/
cp resources/style.css build/resources/
```

### Step 5: Run

```bash
java --module-path "$JAVAFX_SDK" --add-modules javafx.controls,javafx.fxml \
    -cp "build:build/resources" ui.MainApp
```

---

## ❓ Troubleshooting

### Error: "javac: command not found"
**Solution:**
- Install JDK (not just JRE)
- Add Java to PATH:
  - Windows: `setx PATH "%JAVA_HOME%\bin;%PATH%"`
  - Linux/Mac: `export PATH="$JAVA_HOME/bin:$PATH"`

### Error: "module javafx.controls not found"
**Solution:**
- Verify JavaFX SDK is downloaded
- Check path is correct
- Use full path if not in environment variable

### Error: "main.fxml: No such file or directory"
**Solution:**
- Make sure you're in project root directory
- `resources/main.fxml` must exist
- Copy command must copy to `build/resources/`

### Error: "Could not find or load main class ui.MainApp"
**Solution:**
- Compile successful? Check `build/` directory exists
- Classpath must include `build` and `build/resources`
- Use correct separators: `;` on Windows, `:` on Linux/Mac

### Error: "Window appears blank"
**Solution:**
- Check Java version: `java -version` (should be 11+)
- Try running with: `java -XmxV1024m` (more memory)
- Check console for error messages

---

## 🔧 Advanced Compilation Options

### Compile with Warnings as Errors
```bash
javac -Werror --module-path "$JAVAFX_SDK/lib" ...
```

### Compile with Verbose Output
```bash
javac -verbose --module-path "$JAVAFX_SDK/lib" ...
```

### Compile with Optimizations
```bash
javac -O --module-path "$JAVAFX_SDK/lib" ...
```

### Full Compile with All Options (Linux/Mac)
```bash
javac \
    --module-path "$JAVAFX_SDK/lib" \
    --add-modules javafx.controls,javafx.fxml,javafx.graphics \
    --source 11 \
    --target 11 \
    -d build \
    -Xlint:all \
    src/models/*.java \
    src/algorithms/*.java \
    src/visualization/*.java \
    src/utils/*.java \
    src/ui/*.java
```

---

## 📊 Compilation Process Explained

### What Happens When You Compile

1. **Source Files Read**
   - `src/models/*.java` → Data models
   - `src/algorithms/*.java` → Scheduling algorithms
   - `src/visualization/*.java` → Gantt chart
   - `src/utils/*.java` → Helper utilities
   - `src/ui/*.java` → GUI components (MainApp, Controller)

2. **Dependencies Resolved**
   - Compiler checks all `import` statements
   - Makes sure all referenced classes exist
   - Links to JavaFX modules via `--module-path`

3. **Bytecode Generated**
   - `.class` files created in `build/` directory
   - One `.class` file per Java class
   - Directory structure preserved

4. **Resources Copied**
   - `main.fxml` (UI layout)
   - `style.css` (styling)
   - Copied to `build/resources/`

### Why Modules Are Needed

```
--module-path "$JAVAFX_SDK/lib"     ← Where to find JavaFX
--add-modules javafx.controls       ← UI controls module
--add-modules javafx.fxml           ← FXML parsing module
--add-modules javafx.graphics       ← Graphics module
```

JavaFX is a modular library (Java 9+ feature). We tell the compiler:
1. Where to find the modules (`--module-path`)
2. Which modules to use (`--add-modules`)

---

## 🚀 Running the Application Explained

When you run: `java --module-path ... -cp ... ui.MainApp`

1. **JVM Starts**
2. **Modules Loaded** (javafx.controls, javafx.fxml, etc.)
3. **Classpath Configured** (build/ and build/resources/)
4. **Main Class Found** (ui.MainApp)
5. **Application.start() Called**
   - Loads FXML from resources/main.fxml
   - Creates Scene with 1400x900 resolution
   - Shows window
6. **GUI Appears**
   - Ready for user interaction
   - Controller listens for events
   - Can now load data and run algorithms

---

## 💡 Tips & Tricks

### Faster Compilation
```bash
# Compile only Java files (skip already compiled)
javac *.java  # Only compiles changed files
```

### Check Java Version
```bash
java -version
javac -version
```

### Find JavaFX Location
```bash
# Windows
where javafx
# Linux
which javafx
# Mac
which javafx
```

### List Modules in Build
```bash
jar tf build/javafx-controls.jar | grep -i "\.class$" | wc -l
```

---

## 📝 Quick Reference

### Windows
```batch
cd D:\ProcessPilot
javac --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -d build src\models\*.java src\algorithms\*.java src\visualization\*.java src\utils\*.java src\ui\*.java
copy resources\*.* build\resources\
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -cp "build;build\resources" ui.MainApp
```

### Linux/Mac
```bash
cd ~/ProcessPilot
javac --module-path "$HOME/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.fxml -d build src/models/*.java src/algorithms/*.java src/visualization/*.java src/utils/*.java src/ui/*.java
cp resources/* build/resources/
java --module-path "$HOME/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.fxml -cp "build:build/resources" ui.MainApp
```

---

## ✅ Verification Checklist

After compilation, verify:
- ✅ `build/` directory exists
- ✅ `build/ui/` contains `MainApp.class` and `Controller.class`
- ✅ `build/resources/` contains `main.fxml` and `style.css`
- ✅ Java command runs without errors
- ✅ GUI window appears
- ✅ All 4 tabs visible and clickable

---

## 🎓 What You Just Did

You successfully:
1. Set up JavaFX development environment
2. Compiled JavaFX GUI application
3. Copied resource files
4. Executed JavaFX application

This process demonstrates:
- Java module system (Java 9+)
- Classpath configuration
- Build process
- Application deployment

---

**Happy coding!** 🚀
