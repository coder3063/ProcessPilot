# ProcessPilot GUI Features - Visual Guide

## 🎨 User Interface Overview

### Main Window
```
┌─────────────────────────────────────────────────────────────┐
│ ProcessPilot - Advanced CPU Scheduling Simulator       [_][□][X]
├─────────────────────────────────────────────────────────────┤
│ Professional CPU Scheduling & Process Management Tool       │
├─────────────────────────────────────────────────────────────┤
│ [Input]  [Scheduler]  [Results]  [Compare All]             │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  [Content of Selected Tab]                                 │
│                                                             │
│                                                             │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│ ProcessPilot v1.0 | Powered by JavaFX                     │
└─────────────────────────────────────────────────────────────┘
```

---

## 📥 Tab 1: Process Input

### Purpose
Add processes to simulate. Can load samples or enter manually.

### Components

```
┌─ Load Sample Data ───────────────────────────────────────┐
│ [Select Dataset ▼] [Load Sample]                         │
│                                                          │
│ Available Samples:                                       │
│ • Dataset 1: 5 processes (different arrival times)     │
│ • Dataset 2: 4 processes (various burst times)         │
│ • Dataset 3: 6 processes (with priorities)             │
│ • Random: Generate random dataset                       │
└──────────────────────────────────────────────────────────┘

┌─ Process Table ──────────────────────────────────────────┐
│ Columns: Process ID | Arrival Time | Burst Time | Priority
│                                                          │
│ ┌────┬──────────┬──────────┬──────────┐               │
│ │ P1 │    0     │    8     │    1     │               │
│ │ P2 │    1     │    4     │    2     │               │
│ │ P3 │    2     │    2     │    3     │               │
│ │ P4 │    3     │    1     │    2     │               │
│ └────┴──────────┴──────────┴──────────┘               │
│                                                          │
│ [Add Process] [Clear All]                               │
└──────────────────────────────────────────────────────────┘
```

### Features
- **Load Sample Data:** Dropdown with 4 pre-configured datasets
- **Process Table:** Editable table with 4 columns (ID, Arrival, Burst, Priority)
- **Add/Clear Buttons:** Add new processes or clear all
- **Validation:** Ensures valid numeric input

---

## ⚙️ Tab 2: Scheduler

### Purpose
Select scheduling algorithm and configure parameters.

### Components

```
┌─ Algorithm Selection ────────────────────────────────────┐
│ Algorithm: [Select Algorithm ▼]                          │
│                                                          │
│ Options:                                                │
│ • FCFS (First Come First Serve)                        │
│ • SJF Non-Preemptive (Shortest Job First)             │
│ • SJF Preemptive (SRTF - Shortest Remaining Time)     │
│ • Priority Non-Preemptive                             │
│ • Priority Preemptive                                 │
│ • Round Robin                                         │
│ • Multilevel Queue                                    │
└──────────────────────────────────────────────────────────┘

┌─ Configuration ──────────────────────────────────────────┐
│ Time Quantum (for Round Robin):                          │
│ [  3  ] ◄──────────► milliseconds                       │
│                                                          │
│ (Only enabled for Round Robin algorithm)                │
└──────────────────────────────────────────────────────────┘

┌─ Execution ──────────────────────────────────────────────┐
│ [Run Scheduler]  ⏳ Running...  [0%]                    │
│                                                          │
│ Progress: ████████░░░░░░░░░░░░░░ 40%                   │
└──────────────────────────────────────────────────────────┘
```

### Features
- **Algorithm Dropdown:** Choose from 7 scheduling algorithms
- **Time Quantum Spinner:** Configure for Round Robin (disabled for other algorithms)
- **Run Button:** Executes the selected algorithm
- **Progress Bar:** Shows execution progress
- **Status:** Displays running/completed/error messages
- **Threading:** Runs in background to keep UI responsive

---

## 📊 Tab 3: Results

### Purpose
Display scheduling results, metrics, and Gantt chart.

### Components

```
┌─ Performance Metrics ────────────────────────────────────┐
│ ┌─────────────────┬─────────────────┬──────────────────┐
│ │ Avg Waiting Time │ Avg Turnaround  │ CPU Utilization  │
│ │      4.5ms      │     12.2ms      │     92.5%        │
│ └─────────────────┴─────────────────┴──────────────────┘
└──────────────────────────────────────────────────────────┘

┌─ Gantt Chart ────────────────────────────────────────────┐
│                                                          │
│ P1    P2     P3   P1    P4    P1                        │
│ ├─────┼──────┼───┼────────┼──┼─────────────────┤     │
│ 0     5      10  12  13  18   20                25     │
│                                                          │
│ Time units on axis, processes shown in color blocks   │
└──────────────────────────────────────────────────────────┘

┌─ Process Details ────────────────────────────────────────┐
│ ┌─────┬─────────┬──────────┬──────────┬──────────┐     │
│ │  ID │ Arrival │  Burst   │ Waiting  │Turnaround│     │
│ ├─────┼─────────┼──────────┼──────────┼──────────┤     │
│ │ P1  │    0    │    8     │    2     │    10    │     │
│ │ P2  │    1    │    4     │    4     │     8    │     │
│ │ P3  │    2    │    2     │    6     │     8    │     │
│ │ P4  │    3    │    1     │    2     │     3    │     │
│ └─────┴─────────┴──────────┴──────────┴──────────┘     │
│                                                          │
│ Average Waiting Time: 3.5ms                             │
│ Average Turnaround Time: 7.25ms                         │
└──────────────────────────────────────────────────────────┘
```

### Features
- **Performance Metrics Cards:** Three main metrics displayed prominently
- **Gantt Chart:** Visual timeline with process execution blocks
- **Process Details Table:** Per-process statistics
- **Color Coding:** Different colors for different processes
- **Detailed Statistics:** Shows cumulative metrics

---

## 📊 Tab 4: Compare All

### Purpose
Compare all 7 algorithms simultaneously to find the best one.

### Components

```
┌─ Algorithm Comparison ───────────────────────────────────┐
│ [Run All Algorithms]  ⏳ Processing...                  │
│                                                          │
│ ████████████████████████████░░░░░░░░░░░░░░░░ 60%      │
│                                                          │
│ Processing FCFS...                                      │
└──────────────────────────────────────────────────────────┘

┌─ Comparison Results ────────────────────────────────────┐
│ ┌──────────────┬──────┬──────┬──────┬─────────────┐   │
│ │ Algorithm    │  WT  │ TAT  │ CPU% │ Ranking     │   │
│ ├──────────────┼──────┼──────┼──────┼─────────────┤   │
│ │ FCFS         │ 4.5  │ 12.2 │ 95%  │ 4th         │   │
│ │ SJF          │ 2.1  │  9.8 │ 96%  │ 1st ⭐     │   │
│ │ SJF Preempt  │ 2.3  │ 10.1 │ 95%  │ 2nd         │   │
│ │ Priority     │ 3.2  │ 10.9 │ 95%  │ 3rd         │   │
│ │ Priority Pre │ 2.9  │ 10.5 │ 95%  │ 3rd         │   │
│ │ RoundRobin   │ 5.1  │ 12.8 │ 94%  │ 5th         │   │
│ │ MLQ          │ 3.8  │ 11.5 │ 95%  │ 4th         │   │
│ └──────────────┴──────┴──────┴──────┴─────────────┘   │
└──────────────────────────────────────────────────────────┘

┌─ Detailed Analysis ─────────────────────────────────────┐
│                                                          │
│ RANKING BY AVERAGE WAITING TIME:                        │
│ 1. SJF Non-Preemptive: 2.1ms ⭐ BEST                   │
│ 2. SJF Preemptive: 2.3ms                               │
│ 3. Priority Preemptive: 2.9ms                          │
│ ... [rest of comparison data] ...                      │
│                                                          │
│ RECOMMENDATIONS:                                        │
│ For minimum waiting time: Use SJF                      │
│ For fairness: Use Round Robin                          │
│ For priority jobs: Use Priority Queue                  │
│                                                          │
└──────────────────────────────────────────────────────────┘
```

### Features
- **Run All Algorithms:** Execute 7 algorithms with one click
- **Comparison Table:** Side-by-side metrics for all algorithms
- **Star Rating:** Best algorithm marked with ⭐
- **Ranking:** Algorithms ranked by various metrics
- **Detailed Analysis:** Text-based comparison with recommendations
- **Progress Tracking:** Visual indication of processing
- **Downloadable Results:** Can copy/save results for reports

---

## 🎨 Design Features

### Color Scheme
- **Primary Colors:** Professional blue and white
- **Process Colors:** Different colors for each process (red, green, blue, yellow, purple, orange, cyan)
- **Accent Colors:** Green for success, red for errors, blue for info
- **Dark Hover:** Button hover effects for better UX

### Typography
- **Font:** System default (Segoe UI on Windows, SF Pro Display on Mac, Noto Sans on Linux)
- **Headers:** Bold 14pt for section headers
- **Body Text:** Regular 11pt for content
- **Monospace:** 10pt for Gantt chart and detailed output

### Layout
- **Responsive:** Adjusts to window size
- **Tabbed Interface:** Clean organization of features
- **Cards:** Grouped related information
- **Tables:** Clear, sortable data display
- **Charts:** ASCII-based Gantt chart with clear formatting

---

## ⌨️ Keyboard Shortcuts

- **Alt+I** - Go to Input tab
- **Alt+S** - Go to Scheduler tab
- **Alt+R** - Go to Results tab
- **Alt+C** - Go to Compare All tab
- **Enter** - Click focused button
- **Tab** - Navigate between controls

---

## 📱 Responsive Design

The GUI automatically adjusts to different screen sizes:

### Large Screen (1920x1080 and above)
- Full sized tables with all columns visible
- Large Gantt chart
- Detailed metrics display
- Side-by-side layout options

### Medium Screen (1400x900 - default)
- Optimized layout for standard monitors
- All features visible
- Comfortable spacing

### Small Screen (1000x600 and above)
- Scrollable tables
- Compact Gantt chart
- Stacked layout for metrics
- Mobile-friendly adjustments

---

## 🔄 Real-time Updates

The GUI provides:
- **Live Progress Bars** during algorithm execution
- **Status Messages** showing current operation
- **Instant Results** when algorithms complete
- **Table Updates** when processes are added/removed
- **Metric Recalculation** when parameters change

---

## 💾 Data Export

From the Results tab, you can:
- Copy Gantt chart to clipboard
- Save metrics as text
- Export comparison results
- Print results

---

## ✨ Advanced Features

### Input Validation
- Prevents negative numbers
- Checks for duplicate process IDs
- Validates time quantum for Round Robin
- Ensures at least one process exists

### Error Handling
- Catches and displays compilation errors
- Shows algorithm exceptions
- Validates file loading
- Provides helpful error messages

### Performance
- Multi-threaded execution (non-blocking UI)
- Efficient table rendering
- Optimized algorithm calculations
- Smooth animations and transitions

---

**The GUI is designed to be professional, user-friendly, and feature-rich!** 🚀
