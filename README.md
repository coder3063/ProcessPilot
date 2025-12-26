# ProcessPilot - Advanced CPU Scheduling Process Simulator

## Overview
ProcessPilot is a comprehensive Java-based simulator for operating system CPU scheduling algorithms. It provides a realistic implementation of multiple scheduling strategies with visualization and comparative analysis.

## Features

### 🧩 Core Features
- ✅ **Multiple Scheduling Algorithms**
  - FCFS (First Come First Served)
  - SJF (Shortest Job First) - Non-preemptive & Preemptive
  - Priority Scheduling - Non-preemptive & Preemptive
  - Round Robin
  - Multilevel Queue Scheduling (Advanced)

- ✅ **Gantt Chart Visualization** - Visual representation of process execution timeline
- ✅ **Performance Metrics**
  - Waiting Time Calculation
  - Turnaround Time Calculation
  - CPU Utilization Analysis

- ✅ **Algorithm Comparison** - Side-by-side performance comparison
- ✅ **Process Management**
  - Process arrival time support
  - Priority levels support
  - Variable burst time

## Project Structure

```
ProcessPilot/
├── src/
│   ├── models/
│   │   ├── Process.java              # Process entity
│   │   └── SchedulingResult.java      # Result storage
│   ├── algorithms/
│   │   ├── Scheduler.java             # Interface
│   │   ├── FCFSScheduler.java         # FCFS implementation
│   │   ├── SJFScheduler.java          # SJF implementation
│   │   ├── PriorityScheduler.java     # Priority implementation
│   │   ├── RoundRobinScheduler.java   # Round Robin implementation
│   │   └── MultilevelQueueScheduler.java # MLQ implementation
│   ├── visualization/
│   │   └── GanttChart.java            # Gantt chart display
│   ├── utils/
│   │   ├── ProcessGenerator.java      # Sample data generation
│   │   └── SchedulingComparator.java  # Algorithm comparison
│   └── ProcessPilot.java              # Main application
├── build/                             # Compiled classes
└── README.md                          # This file
```

## Algorithms Explained

### 1. FCFS (First Come First Served)
- Simplest scheduling algorithm
- Processes execute in arrival order
- No preemption
- **Pros:** Simple to implement
- **Cons:** High average waiting time, convoy effect

### 2. SJF (Shortest Job First)
#### Non-Preemptive
- Process with shortest burst time executes completely
- **Pros:** Low average waiting time
- **Cons:** Can starve longer processes

#### Preemptive (SRTF - Shortest Remaining Time First)
- Process with shortest remaining time continues
- New process can preempt current process
- **Pros:** Optimal average waiting time
- **Cons:** More context switching overhead

### 3. Priority Scheduling
#### Non-Preemptive
- Process with highest priority (lowest number) executes completely
- **Pros:** Important processes handled first
- **Cons:** Starvation of low-priority processes

#### Preemptive
- Higher priority process can preempt current process
- **Pros:** Responsive to high-priority tasks
- **Cons:** Complex implementation, overhead

### 4. Round Robin
- Each process gets fixed time quantum (time slice)
- Processes rotate in queue after using time quantum
- **Pros:** Fair distribution, responsive
- **Cons:** Context switching overhead

### 5. Multilevel Queue Scheduling (Advanced)
- Processes divided into multiple queues by priority
- Each queue has its own scheduling algorithm
- **Queue Structure:**
  - Queue 0: System processes (Priority 0-1) - TQ: 2
  - Queue 1: Interactive processes (Priority 2-4) - TQ: 4
  - Queue 2: Batch processes (Priority 5+) - TQ: 8
- **Pros:** Efficient, flexible scheduling
- **Cons:** Complex configuration

## Compilation & Execution

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line/Terminal access

### Compile
```bash
cd D:\ProcessPilot
javac -d build src/**/*.java src/ProcessPilot.java
```

### Run
```bash
cd D:\ProcessPilot
java -cp build ProcessPilot
```

### Or using the provided batch file (Windows):
```bash
build.bat
run.bat
```

## Usage Guide

### Main Menu Options

1. **Input Processes Manually**
   - Define custom processes
   - Specify arrival time, burst time, and priority

2. **Generate Sample Data**
   - Load pre-configured datasets
   - Generate random processes

3. **Run Single Scheduling Algorithm**
   - Execute one scheduling algorithm
   - View Gantt chart and metrics

4. **Compare All Scheduling Algorithms**
   - Run all 7 scheduling algorithms
   - View comparative analysis
   - Identify best algorithm for your use case

5. **About ProcessPilot**
   - View project information and features

## Example Usage

### Input Example
```
Process 1: Arrival=0, Burst=8, Priority=2
Process 2: Arrival=1, Burst=4, Priority=1
Process 3: Arrival=2, Burst=2, Priority=3
Process 4: Arrival=3, Burst=1, Priority=2
Process 5: Arrival=4, Burst=3, Priority=1
```

### Output Example (Gantt Chart)
```
┌─────┬─────┬─────┬─────┬─────┐
│ P2  │ P5  │ P1  │ P3  │ P4  │
├─────┼─────┼─────┼─────┼─────┤
│0    │4    │7    │15   │17   │18   │
```

### Metrics Output
```
Algorithm: FCFS
Average Waiting Time: 6.20
Average Turnaround Time: 10.40
CPU Utilization: 100.00%
```

## Performance Comparison Guide

| Algorithm | Best For | Waiting Time | Turnaround Time | CPU Util |
|-----------|----------|--------------|-----------------|----------|
| FCFS | Simple systems | High | High | 100% |
| SJF (NP) | Known burst times | Low | Low | 100% |
| SJF (P) | Optimal for known bursts | Very Low | Very Low | 100% |
| Priority | Mixed workloads | Medium | Medium | 100% |
| Round Robin | Interactive systems | Medium | Medium | High |
| MLQ | Complex systems | Adaptive | Adaptive | High |

## Key Metrics Explained

- **Waiting Time (WT)** = Turnaround Time - Burst Time
- **Turnaround Time (TAT)** = Completion Time - Arrival Time
- **CPU Utilization** = Total Burst Time / Total Completion Time × 100%

## Educational Value

This project demonstrates:
1. Operating system concepts
2. CPU scheduling algorithms
3. Performance analysis
4. Data structure usage (queues, lists)
5. Object-oriented programming
6. Algorithm implementation and comparison

## Advanced Features

- Preemptive and non-preemptive scheduling
- Process priority levels
- Variable time quantum for Round Robin
- Multiple queue management
- Dynamic metrics calculation
- Comparative algorithm analysis

## Future Enhancements

- GUI interface with JavaFX
- File-based process input/output
- Real-time simulation
- Additional algorithms (Feedback scheduling, etc.)
- Performance graphs and charts
- Simulation speed control

## Notes

- Priority: 0 = Highest, 5+ = Lowest
- Time Quantum: Default 4 for Round Robin
- All times are in arbitrary units (time ticks)
- Processes are executed in the order determined by the algorithm

## Author
ProcessPilot Development Team

## License
Educational Purpose

## Version
1.0 - Initial Release

---

**Happy Scheduling! 🚀**
