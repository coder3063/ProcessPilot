# ProcessPilot - Getting Started Guide

## 📋 Table of Contents
1. [Installation](#installation)
2. [Quick Start](#quick-start)
3. [Understanding the Algorithms](#understanding-the-algorithms)
4. [Using the Application](#using-the-application)
5. [Examples](#examples)
6. [Troubleshooting](#troubleshooting)

## Installation

### Requirements
- Java Development Kit (JDK) 8 or higher
- Windows, Linux, or macOS

### Setup Steps

#### Windows
1. Navigate to the ProcessPilot directory
2. Run `build.bat` to compile all source files
3. Run `run.bat` to start the application

#### Linux/macOS
1. Navigate to the ProcessPilot directory
2. Make scripts executable: `chmod +x build.sh run.sh`
3. Run `./build.sh` to compile all source files
4. Run `./run.sh` to start the application

### Manual Compilation
```bash
cd ProcessPilot
javac -d build src/**/*.java src/ProcessPilot.java
java -cp build ProcessPilot
```

## Quick Start

### Step 1: Launch the Application
Run the application using the build scripts or manual compilation

### Step 2: Generate Sample Data
- Select option 2 from main menu
- Choose a sample dataset (1-4)
- View the loaded processes

### Step 3: Run a Scheduler
- Select option 3 from main menu
- Choose a scheduling algorithm
- View the Gantt chart and metrics

### Step 4: Compare All Algorithms
- Select option 4 from main menu
- View comprehensive comparison
- Identify the best algorithm for your use case

## Understanding the Algorithms

### 1. FCFS (First Come First Served)
**Best for:** Simple, batch processing systems

**How it works:**
- Processes execute in the order they arrive
- Each process runs to completion without interruption
- No preemption

**Example:**
```
Processes: P1(arrival=0, burst=3), P2(arrival=1, burst=2), P3(arrival=2, burst=1)
Execution: P1 → P2 → P3
Gantt Chart:
│ P1  │ P2  │ P3  │
0     3     5     6

Metrics:
- P1: WT=0, TAT=3
- P2: WT=2, TAT=4
- P3: WT=3, TAT=4
Average WT = 1.67, Average TAT = 3.67
```

### 2. SJF (Shortest Job First)

#### Non-Preemptive
**Best for:** Systems where burst times are known in advance

**How it works:**
- Among ready processes, select the one with shortest burst time
- Process runs to completion
- No preemption

#### Preemptive (SRTF)
**Best for:** Minimizing average waiting time

**How it works:**
- Always execute the process with shortest remaining time
- Can be preempted by newly arrived process with shorter time
- Requires more context switching

**Example:**
```
Processes: P1(arrival=0, burst=8), P2(arrival=1, burst=4)
SRTF Execution:
│ P1  │ P2  │ P1  │
0     1     5     13

At time 1: P2 arrives with 4 units, less than P1's remaining 7
P2 executes first, then P1 resumes
```

### 3. Priority Scheduling

#### Non-Preemptive
- Select ready process with highest priority (lowest number)
- Process runs to completion

#### Preemptive
- Current process can be preempted by higher priority process

**Example:**
```
Process | Arrival | Burst | Priority
P1      | 0       | 3     | 3
P2      | 0       | 2     | 1 (highest)
P3      | 0       | 1     | 2

Non-Preemptive Execution: P2 → P3 → P1
Preemptive: Same in this case (all arrived at once)
```

### 4. Round Robin

**Best for:** Interactive systems, fair CPU sharing

**How it works:**
- Each process gets a fixed time quantum (default: 4)
- After using time quantum, process goes to end of queue
- Continues until all processes complete

**Example:**
```
Processes: P1(burst=8), P2(burst=4), P3(burst=2)
Time Quantum = 3

Execution: P1(3) → P2(3) → P3(2) → P1(3) → P2(1)

Gantt Chart:
│ P1  │ P2  │ P3  │ P1  │ P2  │
0     3     6     8     11    12
```

### 5. Multilevel Queue Scheduling

**Best for:** Complex systems with mixed workloads

**How it works:**
- Processes divided into 3 queues by priority
- Each queue has own time quantum
- System processes: Priority 0-1 (TQ=2)
- Interactive processes: Priority 2-4 (TQ=4)
- Batch processes: Priority 5+ (TQ=8)

**Example:**
```
Queue 0 (System): High priority processes
Queue 1 (Interactive): Medium priority
Queue 2 (Batch): Low priority background tasks

Scheduling: Always execute highest priority queue first
Within queue: Round Robin with queue's time quantum
```

## Using the Application

### Main Menu

```
┌─── MAIN MENU ────────────────────────────────────────────┐
│ 1. Input Processes Manually                              │
│ 2. Generate Sample Data                                  │
│ 3. Run Single Scheduling Algorithm                       │
│ 4. Compare All Scheduling Algorithms                     │
│ 5. About ProcessPilot                                    │
│ 6. Exit                                                  │
└───────────────────────────────────────────────────────────┘
```

### Option 1: Input Processes Manually

1. Enter number of processes
2. For each process, enter:
   - Arrival Time (when process arrives in the system)
   - Burst Time (how long process takes to execute)
   - Priority (0 = highest, increasing = lower priority)

### Option 2: Generate Sample Data

Four options available:
- **Dataset 1:** 5 mixed processes
- **Dataset 2:** 4 processes with different bursts
- **Dataset 3:** 5 processes with varied priorities
- **Dataset 4:** Random processes (specify count)

### Option 3: Run Single Algorithm

Select one algorithm and view:
- Gantt Chart (visual timeline)
- Performance Metrics (WT, TAT, CPU Util)
- Detailed Process Information

### Option 4: Compare All Algorithms

Automatically runs all 7 scheduling variants:
- FCFS
- SJF (Non-preemptive)
- SJF (Preemptive)
- Priority (Non-preemptive)
- Priority (Preemptive)
- Round Robin
- Multilevel Queue

View options after comparison:
- Detailed comparison table
- Gantt charts for all algorithms
- Best algorithm identification

## Examples

### Example 1: Simple FCFS

**Input:**
```
3 Processes
P1: Arrival=0, Burst=5, Priority=1
P2: Arrival=1, Burst=3, Priority=2
P3: Arrival=2, Burst=2, Priority=3
```

**FCFS Execution:**
```
Gantt Chart:
│ P1  │ P2  │ P3  │
0     5     8     10

Metrics:
- Average Waiting Time: 2.67
- Average Turnaround Time: 5.67
- CPU Utilization: 100%

Process Details:
P1 │ Arrival: 0 │ Burst: 5 │ Priority: 1 │ WT: 0 │ TAT: 5
P2 │ Arrival: 1 │ Burst: 3 │ Priority: 2 │ WT: 4 │ TAT: 7
P3 │ Arrival: 2 │ Burst: 2 │ Priority: 3 │ WT: 6 │ TAT: 8
```

### Example 2: Round Robin Comparison

**Comparing TQ=2 vs TQ=4:**

TQ=2:
```
│ P1  │ P2  │ P1  │ P2  │ P1  │
0     2     4     6     8     10

More context switches, more overhead
```

TQ=4:
```
│ P1  │ P2  │ P1  │ P2  │
0     4     8     12    16

Fewer context switches, potentially longer response time
```

## Troubleshooting

### Issue: "Build directory not found"
**Solution:** Run `build.bat` (Windows) or `./build.sh` (Linux/Mac) first

### Issue: "Cannot find main class ProcessPilot"
**Solution:** Ensure you're running from the ProcessPilot root directory, and the build succeeded

### Issue: "No processes loaded! Please load or input processes first."
**Solution:** Use Option 1 or 2 from main menu to load processes before running schedulers

### Issue: Compilation errors
**Solution:** 
- Verify Java version: `java -version`
- Ensure all source files are present
- Try cleaning build directory: `rm -rf build` (Linux/Mac) or `rmdir /s build` (Windows)
- Rebuild with scripts

### Issue: Application runs but shows incorrect results
**Solution:**
- Verify process data is correctly entered
- Check that priority values are non-negative
- Ensure burst times are positive integers

## Performance Tips

1. **For Large Process Sets:**
   - Generate random processes instead of manual input
   - Consider reducing comparison to specific algorithms
   - Use Dataset 1 or 2 for quick testing

2. **For Accurate Metrics:**
   - Ensure arrival times are realistic
   - Use appropriate priority levels
   - Verify burst times are accurate

3. **For Better Understanding:**
   - Start with small datasets (3-5 processes)
   - Run one algorithm at a time
   - Compare similar algorithms (e.g., SJF variants)

## Next Steps

1. **Experiment:**
   - Try different process combinations
   - Observe how algorithms behave differently
   - Understand trade-offs between metrics

2. **Learn:**
   - Study the source code
   - Understand algorithm implementation
   - Modify time quantums, priorities, and parameters

3. **Extend:**
   - Add new scheduling algorithms
   - Implement GUI interface
   - Add more visualization options

---

**Happy Learning! 🎓**
For more information, see README.md
