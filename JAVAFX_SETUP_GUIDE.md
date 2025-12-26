# ProcessPilot GUI - JavaFX Setup Guide

## 🖥 Complete JavaFX Implementation Guide

---

## 📋 What's Been Added to ProcessPilot

### New Files Created:
```
src/ui/
├── MainApp.java          ← JavaFX Application entry point
└── Controller.java       ← Main controller for all UI logic

resources/
├── main.fxml            ← UI layout file (drag & drop designed)
└── style.css            ← Professional styling
```

### Files Modified:
None - all new files added to existing structure

---

## 🎯 Setup Instructions for Your IDE

### Option 1: IntelliJ IDEA (Recommended)

#### Step 1: Add JavaFX SDK
1. **Download JavaFX SDK**
   - Go to [javafx.io](https://gluonhq.com/products/javafx/)
   - Download "JavaFX SDK" (NOT the Maven version)
   - Extract to a folder (e.g., `C:\javafx-sdk-21` or `~/javafx-sdk-21`)

2. **Add to IntelliJ**
   - File → Project Structure → Libraries
   - Click `+` button → Java
   - Select JavaFX SDK folder
   - Name it "JavaFX"
   - Click OK

#### Step 2: Add Library to Modules
   - File → Project Structure → Modules
   - Select "ProcessPilot"
   - Go to Dependencies tab
   - Click `+` → Library
   - Select JavaFX library
   - Click OK

#### Step 3: Configure VM Options
   - Run → Edit Configurations
   - Click `+` to create new configuration
   - Select "Application"
   - Name: "ProcessPilot GUI"
   - Main class: `ui.MainApp`
   - VM options:
     ```
     --module-path "path/to/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics
     ```
   - Replace "path/to/javafx-sdk-21" with your actual JavaFX path
   - Click OK

#### Step 4: Run the Application
   - Select "ProcessPilot GUI" from run configurations dropdown
   - Click Run (green play button)

---

### Option 2: Eclipse IDE

#### Step 1: Download JavaFX SDK
   - Go to [javafx.io](https://gluonhq.com/products/javafx/)
   - Download "JavaFX SDK"
   - Extract to a folder

#### Step 2: Create User Library
   - Window → Preferences → Java → Build Path → User Libraries
   - Click "New..."
   - Name: "JavaFX"
   - Check "System library"
   - Click "Next"
   - Click "Add JARs..."
   - Navigate to JavaFX SDK lib folder
   - Select all JAR files
   - Click "Open"
   - Click "Finish"

#### Step 3: Add Library to Project
   - Right-click project → Build Path → Configure Build Path
   - Libraries tab
   - Click "Add Library..."
   - Select "User Library"
   - Check "JavaFX"
   - Click "Finish"

#### Step 4: Configure Run Configuration
   - Run → Run Configurations
   - Select Java Application
   - Click "New"
   - Name: "ProcessPilot GUI"
   - Project: ProcessPilot
   - Main class: ui.MainApp
   - Arguments tab:
     - VM arguments:
       ```
       --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml,javafx.graphics
       ```
   - Click "Run"

---

### Option 3: VS Code (Advanced)

#### Step 1: Install Extensions
   - Install "Extension Pack for Java"
   - Install "JavaFX Scene Builder" (optional, for UI design)

#### Step 2: Download JavaFX SDK
   - Download from [javafx.io](https://gluonhq.com/products/javafx/)
   - Extract to a known location

#### Step 3: Create .vscode/settings.json
```json
{
    "java.project.referencedLibraries": {
        "include": [
            "/path/to/javafx-sdk-21/lib/*.jar"
        ],
        "exclude": []
    }
}
```

#### Step 4: Create launch.json
Create `.vscode/launch.json`:
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch ProcessPilot GUI",
            "request": "launch",
            "mainClass": "ui.MainApp",
            "preLaunchTask": "java: build",
            "args": "",
            "vmArgs": "--module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml,javafx.graphics"
        }
    ]
}
```

---

### Option 4: Maven Project Setup (Advanced)

If you want to convert to Maven:

Create `pom.xml`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.processpilot</groupId>
    <artifactId>processpilot</artifactId>
    <version>1.0</version>
    <packaging>jar</packaging>

    <properties>
        <javafx.version>21</javafx.version>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-graphics</artifactId>
            <version>${javafx.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
            </plugin>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <configuration>
                    <mainClass>ui.MainApp</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

Then run:
```bash
mvn clean javafx:run
```

---

## 🚀 Running the GUI Application

### After Setup is Complete:

#### Using IDE:
```
Simply click the Run button in your IDE
The ProcessPilot GUI window will open
```

#### Using Command Line:

**Windows:**
```batch
cd D:\ProcessPilot
javac --module-path "path\to\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -d build src\ui\*.java src\models\*.java src\algorithms\*.java src\visualization\*.java src\utils\*.java src\ProcessPilot.java
java --module-path "path\to\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -cp build ui.MainApp
```

**Linux/Mac:**
```bash
cd ProcessPilot
javac --module-path "path/to/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.fxml -d build src/ui/*.java src/models/*.java src/algorithms/*.java src/visualization/*.java src/utils/*.java src/ProcessPilot.java
java --module-path "path/to/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.fxml -cp build ui.MainApp
```

---

## 🎨 GUI Features Explained

### Tab 1: 📥 Process Input
- **Load Sample Data:** Choose from 4 pre-configured datasets
- **Manual Input Table:** Add/edit/delete processes
- Columns: Process ID, Arrival Time, Burst Time, Priority

### Tab 2: ⚙️ Scheduler
- **Algorithm Selection:** Choose from 7 algorithms
- **Time Quantum:** Configure for Round Robin
- **Run Scheduler:** Execute selected algorithm

### Tab 3: 📊 Results
- **Performance Metrics:** Shows Avg WT, Avg TAT, CPU Utilization
- **Gantt Chart:** Visual timeline of process execution
- **Process Details:** Table showing individual process metrics

### Tab 4: 📊 Compare All
- **Run All Algorithms:** Compare 7 algorithms simultaneously
- **Comparison Table:** Side-by-side metrics
- **Detailed Analysis:** Formatted text output

---

## 🐛 Troubleshooting

### Error: "Could not find or load main class ui.MainApp"
**Solution:**
- Ensure JavaFX is properly added to classpath
- Check VM options are set correctly
- Build project first (Build → Build Project)

### Error: "javafx.fxml" module not found
**Solution:**
- Add `javafx.fxml` to `--add-modules` VM option:
  ```
  --add-modules javafx.controls,javafx.fxml,javafx.graphics
  ```

### Error: FXML file not found
**Solution:**
- Ensure `resources/main.fxml` exists
- In IDE, mark `resources` folder as "Resources Root"
  - Right-click resources folder
  - Mark Directory as → Resources Root

### Error: CSS not loading
**Solution:**
- CSS is optional, application works without it
- If you want styling, ensure `resources/style.css` exists
- In IDE, mark `resources` folder as "Resources Root"

### GUI window appears blank
**Solution:**
- Check Java version (should be 11+)
- Try rebuilding project
- Clear build folder and rebuild

---

## 📝 File Structure After Setup

```
ProcessPilot/
├── src/
│   ├── models/
│   │   ├── Process.java
│   │   └── SchedulingResult.java
│   ├── algorithms/
│   │   ├── Scheduler.java
│   │   ├── FCFSScheduler.java
│   │   ├── SJFScheduler.java
│   │   ├── PriorityScheduler.java
│   │   ├── RoundRobinScheduler.java
│   │   └── MultilevelQueueScheduler.java
│   ├── visualization/
│   │   └── GanttChart.java
│   ├── utils/
│   │   ├── ProcessGenerator.java
│   │   └── SchedulingComparator.java
│   ├── ui/              ← NEW
│   │   ├── MainApp.java ← NEW (Entry point)
│   │   └── Controller.java ← NEW (GUI logic)
│   └── ProcessPilot.java (CLI version still works)
│
└── resources/           ← NEW
    ├── main.fxml       ← NEW (UI layout)
    └── style.css       ← NEW (Styling)
```

---

## ✨ What You Get with GUI

✅ Professional-looking interface
✅ Interactive process input
✅ Real-time scheduling simulation
✅ Beautiful Gantt chart display
✅ Comprehensive metrics display
✅ Algorithm comparison with visual tables
✅ Sample datasets included
✅ Responsive UI with progress indication

---

## 🎓 Educational Benefits

1. **Software Engineering:** Learn GUI design patterns
2. **JavaFX:** Learn modern Java UI framework
3. **MVC Pattern:** Understand separation of concerns
4. **FXML:** Declarative UI design
5. **Threading:** Background task execution
6. **Data Binding:** UI updates from data changes

---

## 🔧 Next Steps

1. **Download JavaFX SDK** from [javafx.io](https://gluonhq.com/products/javafx/)
2. **Follow setup instructions** for your IDE above
3. **Run ui.MainApp** from your IDE
4. **Enjoy the professional GUI!**

---

## 📞 Common Questions

**Q: Do I need SceneBuilder?**
A: No, FXML is already created. SceneBuilder is optional for editing the UI visually.

**Q: Can I use the CLI version too?**
A: Yes! ProcessPilot.java (CLI) still works. Both versions coexist.

**Q: What if I can't install JavaFX?**
A: Use the CLI version (ProcessPilot.java) which doesn't require JavaFX.

**Q: Can I modify the GUI?**
A: Yes! Edit `resources/main.fxml` and `src/ui/Controller.java`

**Q: What Java version do I need?**
A: Java 11 or higher

---

**Happy coding! Your GUI is ready to impress! 🚀**
