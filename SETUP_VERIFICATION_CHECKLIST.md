# ✅ ProcessPilot Setup Verification Checklist

Use this checklist to verify your ProcessPilot GUI setup is complete and working.

---

## 📋 Pre-Setup Checklist

### System Requirements
- [ ] Running Windows, Linux, or macOS
- [ ] Have internet connection to download JavaFX
- [ ] At least 500MB free disk space
- [ ] 2GB RAM available

### Software Requirements
- [ ] Java Development Kit (JDK) 11+ installed
  - Verify: `java -version` in terminal (should show Java 11+)
  - Not JRE! Must be JDK
  - Not older than Java 11
- [ ] IDE installed (IntelliJ, Eclipse, VS Code, or other)
- [ ] Git installed (optional, for version control)

---

## 🔧 Setup Checklist

### Step 1: Download JavaFX SDK
- [ ] Visited https://gluonhq.com/products/javafx/
- [ ] Clicked "Download" button
- [ ] Selected "JavaFX SDK" (not JavaFX Mods or Samples)
- [ ] Selected correct OS (Windows/Linux/macOS)
- [ ] Downloaded latest version (21 or similar)
- [ ] Extracted to folder (noted the path!)
  - Example: `C:\javafx-sdk-21` (Windows)
  - Example: `~/javafx-sdk-21` (Linux/Mac)

### Step 2: Configure IDE (Pick One)

#### Option A: IntelliJ IDEA
- [ ] Opened ProcessPilot project
- [ ] File → Project Structure → Libraries
- [ ] Clicked `+` button
- [ ] Selected Java
- [ ] Navigated to JavaFX SDK lib folder
- [ ] Selected all JAR files
- [ ] Named library "JavaFX"
- [ ] File → Project Structure → Modules
- [ ] Dependencies tab
- [ ] Added JavaFX library
- [ ] Applied changes

#### Option B: Eclipse
- [ ] Window → Preferences opened
- [ ] Java → Build Path → User Libraries
- [ ] Created new library named "JavaFX"
- [ ] Added JARs from JavaFX lib/ folder
- [ ] Right-click project → Properties
- [ ] Java Build Path → Libraries
- [ ] Added User Library "JavaFX"
- [ ] Applied changes

#### Option C: VS Code
- [ ] Created `.vscode/settings.json` file
- [ ] Added JavaFX JAR paths to java.project.referencedLibraries
- [ ] Saved file
- [ ] Reloaded VS Code

#### Option D: Command Line
- [ ] Noted JavaFX SDK path
- [ ] Edited `build_gui.bat` (Windows) or `build_gui.sh` (Linux/Mac)
- [ ] Updated JAVAFX_SDK variable with correct path
- [ ] Saved file

---

## 🏗️ Build Checklist

### Pre-Build Verification
- [ ] Project structure looks correct (see below)
- [ ] `src/` directory exists with subdirectories:
  - [ ] `models/` (with Process.java, SchedulingResult.java)
  - [ ] `algorithms/` (with 6 scheduler files)
  - [ ] `ui/` (with MainApp.java, Controller.java)
  - [ ] `visualization/` (with GanttChart.java)
  - [ ] `utils/` (with ProcessGenerator.java, SchedulingComparator.java)
- [ ] `resources/` directory exists with:
  - [ ] `main.fxml`
  - [ ] `style.css`
- [ ] ProcessPilot.java exists in src/

### Build Using IDE
- [ ] Opened ProcessPilot project in IDE
- [ ] Cleaned project (Build → Clean or Project → Clean)
- [ ] Rebuilt project (Build → Build Project or similar)
- [ ] **No compilation errors** shown
  - If errors appear:
    - [ ] Check JavaFX library is added to project
    - [ ] Check Java version is 11+
    - [ ] Check all source files are present
    - [ ] Check `resources/` folder is marked as "Resources Root"

### Build Using Command Line
- [ ] Opened terminal/command prompt
- [ ] Navigated to ProcessPilot directory: `cd D:\ProcessPilot` (or your path)
- [ ] Ran compile command successfully
  - [ ] `build_gui.bat` (Windows) or `./build_gui.sh` (Linux/Mac)
  - Or manually: `javac --module-path ... (full command from COMMAND_LINE_GUIDE.md)`
- [ ] **No compilation errors**
- [ ] `build/` directory created with .class files
- [ ] `build/resources/` contains main.fxml and style.css

---

## 🚀 Run Checklist

### Run Using IDE
- [ ] Set Main Class to `ui.MainApp`
  - [ ] NOT `ProcessPilot` (that's console)
  - [ ] NOT `models.Process` or other classes
- [ ] Set VM options: `--module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml`
- [ ] Clicked Run (Green play button)
- [ ] **GUI window appeared** within 5 seconds
- [ ] Window title shows "ProcessPilot"

### Run Using Command Line
- [ ] Navigated to ProcessPilot directory
- [ ] Ran: `java --module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml -cp "build;build\resources" ui.MainApp`
- [ ] **GUI window appeared** within 5 seconds
- [ ] No errors in terminal/command prompt

---

## 🎨 GUI Functionality Checklist

### Window & Appearance
- [ ] Main window opened (1400x900 resolution or resizable)
- [ ] Title bar shows "ProcessPilot - Advanced CPU Scheduling Simulator"
- [ ] Header text visible at top
- [ ] 4 tabs visible: Input, Scheduler, Results, Compare All
- [ ] Footer shows version info at bottom
- [ ] Close, minimize, maximize buttons work
- [ ] Window is resizable

### Input Tab
- [ ] Sample data dropdown visible and clickable
- [ ] 4 sample options available in dropdown:
  - [ ] Dataset 1
  - [ ] Dataset 2
  - [ ] Dataset 3
  - [ ] Random Dataset
- [ ] "Load Sample" button visible and clickable
- [ ] Load button works (click Load Sample → processes appear in table)
- [ ] Process table shows 4 columns: ID, Arrival Time, Burst Time, Priority
- [ ] Sample data appears in table (at least 4 processes)
- [ ] "Add Process" button visible and clickable
- [ ] "Clear All" button visible and clickable
- [ ] Clear button clears the table

### Scheduler Tab
- [ ] Algorithm dropdown visible with all options:
  - [ ] FCFS
  - [ ] SJF Non-Preemptive
  - [ ] SJF Preemptive
  - [ ] Priority Non-Preemptive
  - [ ] Priority Preemptive
  - [ ] Round Robin
  - [ ] Multilevel Queue
- [ ] Can select different algorithms
- [ ] Time Quantum spinner visible
- [ ] Time Quantum only enabled for Round Robin
- [ ] "Run Scheduler" button visible and clickable
- [ ] Progress bar visible
- [ ] Can click "Run Scheduler"
- [ ] Progress bar shows activity (or completes quickly)
- [ ] Status message shows in UI

### Results Tab
- [ ] Three metric cards visible:
  - [ ] Average Waiting Time
  - [ ] Average Turnaround Time
  - [ ] CPU Utilization %
- [ ] Gantt Chart visible (ASCII text representation)
- [ ] Process Details table shows:
  - [ ] Process ID
  - [ ] Arrival Time
  - [ ] Burst Time
  - [ ] Waiting Time
  - [ ] Turnaround Time
  - [ ] (possibly Completion Time)
- [ ] Table has at least 4 rows (one per process)
- [ ] Metrics show reasonable values (numbers, not errors)

### Compare All Tab
- [ ] "Run All Algorithms" button visible
- [ ] Progress bar for comparison visible
- [ ] Comparison table visible after running:
  - [ ] Algorithm column (lists all 7 algorithms)
  - [ ] Waiting Time column
  - [ ] Turnaround Time column
  - [ ] CPU Utilization column
  - [ ] (optional) Ranking column
- [ ] All 7 algorithms listed in table
- [ ] Metrics show for each algorithm
- [ ] Detailed Analysis text visible below table
- [ ] Text shows algorithm rankings and recommendations

### Styling & Polish
- [ ] Colors look professional (blues, grays, whites)
- [ ] Buttons have hover effects (color changes on mouse over)
- [ ] Text is readable (good contrast)
- [ ] Tables are well-formatted
- [ ] No obvious UI glitches or overlapping text
- [ ] Fonts are consistent
- [ ] Spacing is even and organized

---

## ✅ Functional Testing Checklist

### Test: Load and View Data
1. [ ] In Input tab, select "Dataset 1"
2. [ ] Click "Load Sample"
3. [ ] Table populates with processes
4. [ ] Each process has valid ID, Arrival Time, Burst Time, Priority

### Test: Run FCFS Algorithm
1. [ ] Go to Scheduler tab
2. [ ] Select "FCFS" from dropdown
3. [ ] Click "Run Scheduler"
4. [ ] Progress bar shows activity
5. [ ] Go to Results tab
6. [ ] Metrics are displayed (numbers visible)
7. [ ] Gantt chart shows process timeline
8. [ ] Process table shows details

### Test: Run Round Robin with Different Quantum
1. [ ] Go to Scheduler tab
2. [ ] Select "Round Robin"
3. [ ] Change Time Quantum to 2 (instead of 3)
4. [ ] Click "Run Scheduler"
5. [ ] Go to Results tab
6. [ ] Metrics updated for Round Robin

### Test: Compare All Algorithms
1. [ ] Go to Compare All tab
2. [ ] Click "Run All Algorithms"
3. [ ] Progress bar shows all processing
4. [ ] Comparison table appears with 7 rows
5. [ ] All metrics visible for each algorithm
6. [ ] Detailed analysis text displayed

### Test: Switch Tabs
1. [ ] Click each tab (Input, Scheduler, Results, Compare All)
2. [ ] Content changes correctly for each tab
3. [ ] No errors or missing elements
4. [ ] Previous data persists when switching tabs

### Test: Different Data Sets
1. [ ] Load Dataset 1, run FCFS, verify results
2. [ ] Clear all, load Dataset 2, run SJF, verify results
3. [ ] Clear all, load Dataset 3, run Priority, verify results
4. [ ] Load Random, run Round Robin, verify results

---

## 🐛 Troubleshooting Checklist

### If GUI Won't Start

**Error: "Could not find main class ui.MainApp"**
- [ ] Check main class is set to `ui.MainApp` (not ProcessPilot)
- [ ] Check `build/ui/MainApp.class` exists
- [ ] Check classpath includes `build/` directory
- [ ] Rebuild project

**Error: "No JavaFX module found"**
- [ ] Check `--add-modules javafx.controls,javafx.fxml` in VM options
- [ ] Check `--module-path` points to correct JavaFX lib folder
- [ ] Check JavaFX SDK is downloaded (not just demo)

**Error: "resources/main.fxml not found"**
- [ ] Check `resources/main.fxml` file exists
- [ ] Check `resources/` is marked as Resources Root in IDE
- [ ] Check build copies to `build/resources/`
- [ ] Rebuild project

**Error: "Java version not supported"**
- [ ] Check Java version: `java -version`
- [ ] Should be Java 11 or higher
- [ ] Download newer JDK if needed

### If GUI Appears But Is Blank

- [ ] Check console for error messages
- [ ] Wait 10 seconds (may still be loading)
- [ ] Try resizing window
- [ ] Check for JavaScript errors in browser console (N/A - desktop app)
- [ ] Try rebuilding project
- [ ] Delete `build/` folder and rebuild

### If Buttons Don't Work

- [ ] Check Controller.java is in `src/ui/`
- [ ] Check FXML has `fx:controller="ui.Controller"` at top
- [ ] Check @FXML annotations on event handlers
- [ ] Rebuild project and run again

### If Metrics Show Errors

- [ ] Check at least one process is loaded
- [ ] Check Time Quantum is valid number for Round Robin
- [ ] Check algorithms are compiled in `build/algorithms/`
- [ ] Try running with simpler dataset first

---

## 📊 Performance Checklist

### Responsiveness
- [ ] Tab switching is instant (< 1 second)
- [ ] GUI responds immediately to button clicks
- [ ] No freezing or "not responding" messages
- [ ] Hover effects work smoothly

### Algorithm Execution
- [ ] FCFS completes in < 1 second
- [ ] SJF completes in < 1 second
- [ ] All algorithms complete in < 2 seconds
- [ ] Compare All completes in < 5 seconds
- [ ] Results appear without delay

### Memory Usage
- [ ] Application doesn't use excessive memory
- [ ] Window doesn't lag when resized
- [ ] No memory leak warnings

---

## 🎓 Educational Verification Checklist

### Learning Outcomes
- [ ] Can explain FCFS algorithm (by looking at results)
- [ ] Can explain SJF vs preemptive SJF (by comparing results)
- [ ] Can explain Priority scheduling (by running test)
- [ ] Can explain Round Robin (by adjusting time quantum)
- [ ] Can explain Multilevel Queue (by running test)
- [ ] Understands waiting time calculation
- [ ] Understands turnaround time calculation
- [ ] Can identify best algorithm for different scenarios

### Teacher's Assessment
- [ ] All 7 algorithms implemented and working
- [ ] Metrics calculated correctly
- [ ] Gantt chart displays correctly
- [ ] Comparison feature works
- [ ] GUI is professional and user-friendly
- [ ] Code is well-organized
- [ ] Documentation is comprehensive
- [ ] Ready for submission/grading

---

## ✨ Final Verification

### Complete Setup Check
- [ ] All prerequisites met
- [ ] JavaFX SDK downloaded and path noted
- [ ] IDE configured with JavaFX
- [ ] Project compiles without errors
- [ ] Application runs without errors
- [ ] All 4 tabs functional
- [ ] All algorithms run successfully
- [ ] Metrics display correctly
- [ ] GUI looks professional

### Quality Assurance
- [ ] No UI glitches or overlapping text
- [ ] All buttons functional
- [ ] All tables display data correctly
- [ ] Color scheme is professional
- [ ] Performance is acceptable (< 2 second response time)
- [ ] No error messages during normal use

### Documentation Check
- [ ] Found and read relevant documentation
- [ ] Understand how to use GUI
- [ ] Understand how algorithms work
- [ ] Understand file structure
- [ ] Know where to go for help

---

## 🎉 Success Criteria

### You can check off ALL items above if:

✅ JavaFX SDK is installed and configured
✅ Project compiles without errors  
✅ GUI window opens when you run `ui.MainApp`
✅ All 4 tabs are visible and functional
✅ Sample data loads into process table
✅ FCFS algorithm runs and shows results
✅ All 7 algorithms appear in dropdown
✅ Compare All shows all 7 algorithms
✅ Metrics display with numbers
✅ Gantt chart is visible
✅ No error messages in console
✅ GUI responds to all user actions
✅ Application looks professional

**If you can check all of these, your setup is complete!** 🎊

---

## 📞 Still Having Issues?

1. Check [GUI_QUICK_START.md](GUI_QUICK_START.md) troubleshooting
2. Check [JAVAFX_SETUP_GUIDE.md](JAVAFX_SETUP_GUIDE.md) troubleshooting
3. Check [COMMAND_LINE_GUIDE.md](COMMAND_LINE_GUIDE.md) troubleshooting
4. Re-read the relevant setup guide for your IDE
5. Try the build script instead of IDE
6. Try command line instead of IDE

---

**Congratulations on setting up ProcessPilot!** 🚀

*You've successfully deployed a professional JavaFX application with 7 CPU scheduling algorithms, complete metrics, visual Gantt charts, and comprehensive comparison tools.*

**Now go impress your teachers!** 👩‍🎓👨‍🎓
