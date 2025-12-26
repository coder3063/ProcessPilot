# ProcessPilot Architecture Overview

## 🏗️ System Architecture

### High-Level Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                   ProcessPilot Application                  │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ┌──────────────────────────────────────────────────────┐  │
│  │          UI Layer (JavaFX GUI)                       │  │
│  │  ┌─────────┬──────────┬─────────┬─────────────────┐  │
│  │  │ Input   │ Scheduler│ Results │ Compare All     │  │
│  │  │ Tab     │ Tab      │ Tab     │ Tab             │  │
│  │  └─────────┴──────────┴─────────┴─────────────────┘  │
│  │                                                     │  │
│  └─────────────────────────────────────────────────────┘  │
│           ↓ (User interactions) ↓                         │
│  ┌────────────────────────────────────────────────────┐  │
│  │       Controller Layer (Business Logic)            │  │
│  │  src/ui/Controller.java (500+ lines)               │  │
│  │  - Event handling                                  │  │
│  │  - Algorithm invocation                            │  │
│  │  - Results display                                 │  │
│  │  - Comparison logic                                │  │
│  └────────────────────────────────────────────────────┘  │
│           ↓ (Creates & calls) ↓                          │
│  ┌────────────────────────────────────────────────────┐  │
│  │       Algorithm Layer (Scheduling Logic)           │  │
│  │  ┌─────────┬─────────┬──────────┬────────────────┐ │  │
│  │  │ FCFS    │ SJF     │ Priority │ Round Robin    │ │  │
│  │  │ Sch.    │ Sch.    │ Sch.     │ Sch.           │ │  │
│  │  └─────────┴─────────┴──────────┴────────────────┘ │  │
│  │  ┌─────────────────────────────────────────────┐   │  │
│  │  │ Multilevel Queue Scheduler                  │   │  │
│  │  └─────────────────────────────────────────────┘   │  │
│  └────────────────────────────────────────────────────┘  │
│           ↓ (Process data) ↓                             │
│  ┌────────────────────────────────────────────────────┐  │
│  │       Model Layer (Data Storage)                   │  │
│  │  ┌──────────────────┬──────────────────────┐       │  │
│  │  │ Process          │ SchedulingResult      │       │  │
│  │  │ (ID, burst, etc) │ (metrics, timeline)  │       │  │
│  │  └──────────────────┴──────────────────────┘       │  │
│  └────────────────────────────────────────────────────┘  │
│           ↓ (Utilities) ↓                                │
│  ┌────────────────────────────────────────────────────┐  │
│  │       Utility Layer (Helper Functions)             │  │
│  │  ┌──────────────┬──────────────────────────┐       │  │
│  │  │ ProcessGen   │ Comparator    │ GanttChart      │  │
│  │  └──────────────┴──────────────────────────┘       │  │
│  └────────────────────────────────────────────────────┘  │
│                                                          │
└──────────────────────────────────────────────────────────┘
```

---

## 📦 Package Structure

```
ProcessPilot/
│
├── src/models/
│   ├── Process.java
│   │   ├── Attributes: ID, arrival, burst, priority, etc.
│   │   └── Methods: getters, setters, copy(), toString()
│   │
│   └── SchedulingResult.java
│       ├── Stores: algorithm name, process timeline, metrics
│       └── Provides: getter methods for results
│
├── src/algorithms/
│   ├── Scheduler.java (interface)
│   │   └── schedule(List<Process>): SchedulingResult
│   │
│   ├── FCFSScheduler.java
│   │   └── Non-preemptive, order by arrival time
│   │
│   ├── SJFScheduler.java
│   │   ├── Non-preemptive mode (flag: preemptive=false)
│   │   └── Preemptive mode (flag: preemptive=true)
│   │
│   ├── PriorityScheduler.java
│   │   ├── Non-preemptive mode (flag: preemptive=false)
│   │   └── Preemptive mode (flag: preemptive=true)
│   │
│   ├── RoundRobinScheduler.java
│   │   ├── Constructor: RoundRobinScheduler(quantum)
│   │   └── Uses time slices of quantum duration
│   │
│   └── MultilevelQueueScheduler.java
│       ├── System Queue (quantum 1)
│       ├── Interactive Queue (quantum 2)
│       └── Batch Queue (quantum 4)
│
├── src/ui/
│   ├── MainApp.java (extends Application)
│   │   ├── Loads FXML from resources/main.fxml
│   │   ├── Sets up scene (1400x900)
│   │   └── Applies CSS styling
│   │
│   └── Controller.java (handles all UI events)
│       ├── Input Tab Logic
│       │   ├── Load sample data
│       │   ├── Manage process table
│       │   └── Validate input
│       │
│       ├── Scheduler Tab Logic
│       │   ├── Algorithm selection
│       │   ├── Time quantum config
│       │   └── Execute scheduler
│       │
│       ├── Results Tab Logic
│       │   ├── Display metrics
│       │   ├── Show Gantt chart
│       │   └── Process details table
│       │
│       ├── Compare Tab Logic
│       │   ├── Run all algorithms
│       │   ├── Build comparison table
│       │   └── Generate analysis
│       │
│       └── Inner Classes
│           ├── ProcessInput (for table binding)
│           ├── ProcessResult (for results table)
│           └── ComparisonResult (for comparison table)
│
├── src/visualization/
│   └── GanttChart.java
│       ├── Input: SchedulingResult
│       ├── Output: ASCII art Gantt chart
│       └── Methods: display(), exportAsASCII()
│
├── src/utils/
│   ├── ProcessGenerator.java
│   │   ├── generateSampleProcesses() - 4 datasets
│   │   └── generateRandomProcesses() - custom
│   │
│   └── SchedulingComparator.java
│       ├── compareResults() - side-by-side table
│       ├── rankByMetric() - best algorithm
│       └── printDetailedComparison() - verbose
│
├── src/ProcessPilot.java (console version)
│   ├── Menu-driven interface
│   ├── Manual process input
│   ├── Algorithm selection
│   ├── Result display
│   └── Comparison output
│
└── resources/
    ├── main.fxml (UI layout - 200 lines)
    │   ├── BorderPane (root)
    │   ├── Top: Header
    │   ├── Center: TabPane (4 tabs)
    │   └── Bottom: Footer
    │
    └── style.css (styling - 180 lines)
        ├── Button styles
        ├── Table styles
        ├── Tab styles
        └── Color definitions
```

---

## 🔄 Data Flow Diagram

### From User Input to Results

```
User Input (Input Tab)
    ↓
    └─→ Processes loaded into table
        └─→ User selects "Run Scheduler"
            ↓
    [SCHEDULER TAB]
    - User selects algorithm
    - Optionally sets time quantum
    - Clicks "Run Scheduler"
        ↓
    Controller.java invokes scheduler
        ↓
    Scheduler.schedule(List<Process>)
        ├─→ Creates copy of processes
        ├─→ Executes algorithm logic
        ├─→ Calculates metrics
        └─→ Returns SchedulingResult
            ↓
    SchedulingResult object contains:
    ├─ Algorithm name
    ├─ Process list with timings
    ├─ Gantt chart data (timeline)
    ├─ Avg waiting time
    ├─ Avg turnaround time
    └─ CPU utilization %
        ↓
    Controller displays results
        ├─→ [RESULTS TAB]
        │   ├─ Show metrics cards
        │   ├─ Display Gantt chart
        │   └─ Populate process table
        │
        └─→ [COMPARE TAB] (if "Compare All" clicked)
            ├─ Run all 7 algorithms
            ├─ Build comparison table
            └─ Generate recommendations
```

---

## 🎯 Control Flow for FCFS Example

```
User loads sample data (Process P1, P2, P3, P4)
    ↓
User selects FCFS from dropdown
    ↓
User clicks "Run Scheduler"
    ↓
Controller.java:
    1. Get selected algorithm index
    2. Create copies of processes (for independent execution)
    3. Instantiate: Scheduler scheduler = new FCFSScheduler();
    4. Call: SchedulingResult result = scheduler.schedule(processes);
        ↓
    FCFSScheduler.java:
    1. Sort processes by arrival time
    2. Initialize: currentTime = 0
    3. For each process in order:
       - Set start time = max(currentTime, process.arrival)
       - Calculate waiting time = start - arrival
       - Calculate turnaround time = (finish - arrival)
       - Update currentTime = finish time
    4. Calculate average metrics
    5. Create SchedulingResult object
    6. Return result
        ↓
    Back in Controller.java:
    1. Platform.runLater(() -> {
    2.   Update metrics labels with result
    3.   Display Gantt chart from result
    4.   Populate process table with individual metrics
    5. })
    6. Return to UI (Results tab)
        ↓
User sees:
- Avg Waiting Time: X ms
- Avg Turnaround Time: Y ms  
- CPU Utilization: Z %
- Gantt chart visualization
- Per-process details in table
```

---

## 🧩 Component Interactions

### 1. Input Tab → Scheduler Tab
```
Process Data (Table) 
    ↓
Scheduler Tab loads this data
    ↓
Controller keeps reference to process list
    ↓
When "Run Scheduler" clicked:
- Copies are made (preserves original)
- Scheduler processes the copies
```

### 2. Scheduler Tab → Results Tab
```
Algorithm Selection
    ↓
Scheduler executes
    ↓
SchedulingResult returned
    ↓
Results Tab displays
- Metrics from result.getAverageWaitingTime()
- Gantt data from result.getProcessTimeline()
- Details from result.getProcessList()
```

### 3. Results Tab ← Compare Tab
```
Compare All Tab:
    ↓
For each of 7 algorithms:
    └─→ Run scheduler (gets SchedulingResult)
    └─→ Extract metrics
    └─→ Add to comparison table
    └─→ Track best algorithm
    ↓
Display comparison table
    ↓
User can see which algorithm is best
```

---

## 🔐 Thread Safety

### Problem
Long-running algorithms freeze UI

### Solution
```
Controller.java:
    new Thread(() -> {
        // Run scheduler (takes time)
        SchedulingResult result = scheduler.schedule(processes);
        
        // Update UI from UI thread
        Platform.runLater(() -> {
            // This runs on JavaFX UI thread
            updateUIWithResults(result);
        });
    }).start();
```

### Result
- UI stays responsive
- Progress bar updates
- User can switch tabs while running
- Results update when ready

---

## 📊 Metrics Calculation Flow

```
Algorithm executes:
    ├─→ Each process: Start Time determined
    ├─→ Each process: Completion Time calculated
    ├─→ Each process: Waiting Time = Start - Arrival
    ├─→ Each process: Turnaround Time = Completion - Arrival
    ↓
After all processes scheduled:
    ├─→ Sum all waiting times / number of processes = Avg WT
    ├─→ Sum all turnaround times / number of processes = Avg TAT
    ├─→ CPU time / total time * 100 = CPU Utilization %
    └─→ Number of processes / total time = Throughput
    ↓
Store in SchedulingResult:
    ├─→ averageWaitingTime
    ├─→ averageTurnaroundTime
    ├─→ cpuUtilization
    └─→ throughput
```

---

## 🎨 FXML Structure

```
resources/main.fxml:
    ↓
<BorderPane>
    <top>
        HBox (Title and description)
    </top>
    <center>
        TabPane (4 tabs)
            ├─ Tab 1: Input
            │   └─ VBox
            │       ├─ ComboBox (sample selection)
            │       ├─ Button (Load)
            │       └─ TableView (processes)
            │
            ├─ Tab 2: Scheduler
            │   └─ VBox
            │       ├─ ComboBox (algorithm selection)
            │       ├─ Spinner (time quantum)
            │       ├─ Button (Run Scheduler)
            │       └─ ProgressBar
            │
            ├─ Tab 3: Results
            │   └─ VBox
            │       ├─ HBox (3 metric cards)
            │       ├─ TextArea (Gantt chart)
            │       └─ TableView (process details)
            │
            └─ Tab 4: Compare All
                └─ VBox
                    ├─ Button (Run All)
                    ├─ TableView (comparison)
                    └─ TextArea (analysis)
    </center>
    <bottom>
        HBox (Footer with version)
    </bottom>
</BorderPane>

Connected to: Controller.java (fx:controller="ui.Controller")
Styled by: style.css
```

---

## 🔌 Maven Dependencies (Optional)

If using Maven instead of manual classpath:

```xml
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-graphics</artifactId>
        <version>21</version>
    </dependency>
</dependencies>
```

Then run: `mvn clean javafx:run`

---

## 🎯 Entry Points

### GUI Entry Point
```
ui.MainApp.java
    └─ public static void main(String[] args)
        └─ launch(args)
            └─ Calls: start(Stage primaryStage)
                └─ Loads main.fxml
                └─ Shows scene
```

### Console Entry Point (Still Works)
```
ProcessPilot.java
    └─ public static void main(String[] args)
        └─ Displays menu
        └─ Gets user input
        └─ Calls schedulers directly
        └─ Displays ASCII Gantt chart
```

Both coexist! Choose which to run.

---

## 📈 Extension Points

### To Add New Algorithm
1. Create `src/algorithms/NewScheduler.java`
2. Implement `Scheduler` interface
3. Implement `schedule()` method
4. Add to `Controller.java` getScheduler() method
5. Update ComboBox in main.fxml
6. Done!

### To Modify UI
1. Edit `resources/main.fxml`
2. Add/remove controls
3. Edit `Controller.java` to handle new controls
4. Update `resources/style.css` for styling
5. Recompile and run

### To Add Features
1. Extend `Controller.java` with new methods
2. Add event handlers (@FXML annotations)
3. Update FXML with new controls
4. Test thoroughly

---

## 🚀 Deployment Pipeline

```
Source Code (src/, resources/)
    ↓
Compile with JavaFX modules
    └─→ javac --module-path "$JAVAFX_SDK/lib" ...
    ↓
Generate .class files (build/)
    ↓
Copy resources to build/resources/
    ↓
Package for distribution
    ↓
Run with JavaFX modules
    └─→ java --module-path "$JAVAFX_SDK/lib" ...
    ↓
Application launches
```

---

## ✨ Summary

ProcessPilot uses:
- **Clean MVC Architecture** (Models, Views, Controller)
- **Strategy Pattern** (Scheduler interface, multiple implementations)
- **Observer Pattern** (JavaFX bindings for UI updates)
- **Threading** (Background execution, UI updates on UI thread)
- **FXML + CSS** (Modern declarative UI)

Result: **Professional, scalable, maintainable application** 🎯

---

For more details, see [JAVAFX_IMPLEMENTATION.md](JAVAFX_IMPLEMENTATION.md)
