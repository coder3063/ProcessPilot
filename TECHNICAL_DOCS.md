# ProcessPilot - Technical Documentation

## 1. Architecture Overview

### Design Pattern
ProcessPilot uses the **Strategy Pattern** for implementing different scheduling algorithms. Each algorithm implements the `Scheduler` interface, allowing runtime selection and comparison.

### Package Structure
```
models/
  ├── Process.java
  └── SchedulingResult.java

algorithms/
  ├── Scheduler.java (Interface)
  ├── FCFSScheduler.java
  ├── SJFScheduler.java
  ├── PriorityScheduler.java
  ├── RoundRobinScheduler.java
  └── MultilevelQueueScheduler.java

visualization/
  └── GanttChart.java

utils/
  ├── ProcessGenerator.java
  └── SchedulingComparator.java

ProcessPilot.java (Main Application)
```

## 2. Class Documentation

### models/Process.java
Represents a process in the system with scheduling attributes.

**Key Attributes:**
- `processId`: Unique identifier (1, 2, 3, ...)
- `arrivalTime`: When process enters system
- `burstTime`: CPU time required
- `priority`: Priority level (0 = highest)
- `completionTime`: When process finishes
- `waitingTime`: Time spent waiting
- `turnaroundTime`: Total time from arrival to completion
- `remainingTime`: For preemptive algorithms
- `queue`: Queue assignment for multilevel queue

**Key Methods:**
- `copy()`: Creates independent copy for scheduler
- `getDetailedInfo()`: Formatted process information
- `compareTo()`: Sorts by arrival time

### models/SchedulingResult.java
Stores complete scheduling execution results.

**Key Attributes:**
- `algorithmName`: Name of scheduling algorithm
- `processes`: List of scheduled processes
- `ganttChart`: List of process IDs in execution order
- `ganttTime`: Time values for Gantt chart
- `averageWaitingTime`: Metric calculation
- `averageTurnaroundTime`: Metric calculation
- `cpuUtilization`: CPU usage percentage

### algorithms/Scheduler.java (Interface)
Defines contract for all scheduling algorithms.

```java
public interface Scheduler {
    SchedulingResult schedule(List<Process> processes);
    String getAlgorithmName();
}
```

### algorithms/FCFSScheduler.java
**Complexity:** O(n²) - for finding minimum
**Space:** O(n) - process copies

**Algorithm:**
```
1. Sort processes by arrival time
2. For each process in order:
   - Update current time if needed
   - Execute process completely
   - Calculate metrics
3. Return results
```

### algorithms/SJFScheduler.java
**Non-Preemptive Complexity:** O(n²)
**Preemptive Complexity:** O(n × max_burst)

**Non-Preemptive Algorithm:**
```
1. For each position:
   - Find ready process with shortest burst
   - Execute completely
   - Mark as completed
2. Calculate metrics
```

**Preemptive Algorithm (SRTF):**
```
1. For each time unit:
   - Find process with shortest remaining time
   - Execute 1 unit
   - If complete: calculate metrics
2. Continue until all done
```

### algorithms/PriorityScheduler.java
Similar to SJF but selects by priority instead of burst time.

**Priority Rules:**
- Lower priority number = higher priority
- Non-preemptive: Execute to completion
- Preemptive: Can be interrupted by higher priority

### algorithms/RoundRobinScheduler.java
**Complexity:** O(n × max_burst/quantum)
**Space:** O(n) - queue storage

**Algorithm:**
```
1. Initialize ready queue
2. While processes remain:
   - Add newly arrived processes
   - Take front process from queue
   - Execute for min(remaining_time, quantum)
   - If remaining_time > 0: add back to queue
   - If complete: calculate metrics
3. Return results
```

### algorithms/MultilevelQueueScheduler.java
**Complexity:** O(n × quantum_variations)
**Space:** O(n × num_queues)

**Queue Assignment:**
```
Priority 0-1  → Queue 0 (TQ=2)  [System]
Priority 2-4  → Queue 1 (TQ=4)  [Interactive]
Priority 5+   → Queue 2 (TQ=8)  [Batch]
```

**Algorithm:**
```
1. Assign processes to queues by priority
2. While processes remain:
   - Add newly arrived to respective queues
   - Find first non-empty queue
   - Execute front process with queue's quantum
   - If remaining > 0: add to queue end
   - If complete: calculate metrics
3. Return results
```

### visualization/GanttChart.java
Creates ASCII-based Gantt chart visualization.

**Methods:**
- `display()`: Print to console with formatting
- `exportAsASCII()`: Return formatted string
- `toString()`: Call exportAsASCII()

**ASCII Format:**
```
┌─────┬─────┬─────┐
│ P1  │ P2  │ P3  │
├─────┼─────┼─────┤
│0    │5    │8    │10
└─────┴─────┴─────┘
```

### utils/ProcessGenerator.java
Provides sample data for testing and demonstration.

**Methods:**
- `generateSampleProcesses()`: Default test set
- `generateExample2()`: Alternative test set
- `generateExample3()`: Priority-focused set
- `generateRandomProcesses()`: Configurable random set

### utils/SchedulingComparator.java
Analyzes and compares scheduling results.

**Methods:**
- `compareResults()`: Summary comparison table
- `rankByWaitingTime()`: Sort results
- `rankByTurnaroundTime()`: Sort results
- `rankByCPUUtilization()`: Sort results
- `printDetailedComparison()`: Comprehensive output

## 3. Key Algorithms Detailed

### FCFS Time Complexity Analysis
- Best Case: O(n)
- Average Case: O(n²)
- Worst Case: O(n²)

**Waiting Time:** Can be very high, especially for long processes arriving first

### SJF Preemptive Time Complexity
```
For each time unit: O(n)
Total units: O(sum of all burst times)
Total: O(n × average_burst)
```

**Optimality:** Minimizes average waiting time among non-preemptive algorithms

### Priority Scheduling Starvation Risk
```
If lower priority process arrives before higher:
- Non-preemptive: Will execute first
- Preemptive: Preempted by higher priority
Solution: Aging (increase priority over time)
```

### Round Robin Time Slicing
```
Context Switch Overhead = num_switches × overhead_per_switch
Too small quantum: Many switches, high overhead
Too large quantum: Behaves like FCFS
Optimal quantum: Depends on system characteristics
```

### Multilevel Queue Effectiveness
```
Throughput improvement: Batch processes don't block interactive
Response time improvement: High priority processes get quick access
Starvation: Possible in lower queues (aging recommended)
```

## 4. Performance Analysis

### Calculation Methods

**Waiting Time (WT):**
```
WT = Turnaround Time - Burst Time
```

**Turnaround Time (TAT):**
```
TAT = Completion Time - Arrival Time
```

**CPU Utilization:**
```
CPU Util = (Sum of all burst times) / Total completion time × 100%
```

**Average Waiting Time:**
```
Avg WT = Σ(WT_i) / n
```

**Average Turnaround Time:**
```
Avg TAT = Σ(TAT_i) / n
```

## 5. Data Flow

### Process Flow During Scheduling
```
Input Processes
    ↓
Sort by arrival time
    ↓
Apply scheduling algorithm
    ↓
Store execution order in ganttChart
    ↓
Store time points in ganttTime
    ↓
Calculate metrics (WT, TAT)
    ↓
Create SchedulingResult
    ↓
Display results & Gantt chart
```

### Algorithm Selection Flow
```
User selects algorithm
    ↓
Create Scheduler instance
    ↓
Copy processes (avoid modifying originals)
    ↓
Call scheduler.schedule()
    ↓
Receive SchedulingResult
    ↓
Display via GanttChart and comparison tools
```

## 6. Extension Points

### Adding New Scheduling Algorithm
1. Create new class implementing `Scheduler`
2. Implement `schedule()` method
3. Implement `getAlgorithmName()` method
4. Add to ProcessPilot main menu
5. Add to `runAllSchedulers()` method

**Example Template:**
```java
public class NewScheduler implements Scheduler {
    @Override
    public SchedulingResult schedule(List<Process> processes) {
        SchedulingResult result = new SchedulingResult("Algorithm Name");
        // Implement scheduling logic
        // Update ganttChart and ganttTime
        // Calculate metrics
        return result;
    }
    
    @Override
    public String getAlgorithmName() {
        return "Algorithm Name";
    }
}
```

### Enhancing Visualization
- Replace ASCII with GUI (JavaFX, Swing)
- Add graphs for metrics
- Real-time animation of scheduling
- Export to image formats

### Adding Features
- Process priorities with aging
- Multiple CPU cores
- I/O operations
- Context switching overhead
- Process states (waiting, ready, running)

## 7. Testing Scenarios

### Test Case 1: FCFS Convoy Effect
```
P1: arrival=0, burst=10
P2: arrival=1, burst=2
P3: arrival=2, burst=2

Expected: P1 executes first despite being long,
causing high waiting time for P2, P3
```

### Test Case 2: SJF Starvation
```
P1: arrival=0, burst=20
P2: arrival=5, burst=1
P3: arrival=6, burst=1

Expected: P1 might be indefinitely delayed in preemptive mode
```

### Test Case 3: Priority Inversion
```
P1: arrival=0, burst=5, priority=3 (low)
P2: arrival=1, burst=2, priority=1 (high)

Expected: P2 should run after P1 finishes (non-preemptive)
or preempt immediately (preemptive)
```

### Test Case 4: Round Robin Fairness
```
Multiple equal-length processes
All with same arrival time

Expected: Each process gets equal CPU time
```

## 8. Optimization Opportunities

### Current Implementation
- O(n²) for some algorithms
- No process caching
- ASCII visualization only
- Single-threaded execution

### Potential Optimizations
1. **Use Priority Queue:**
   - For SJF: O(n log n) instead of O(n²)
   - Use Java's PriorityQueue

2. **Lazy Evaluation:**
   - Calculate metrics only when requested

3. **Parallel Processing:**
   - Compare multiple algorithms simultaneously
   - Use Java's Stream API with parallelStream()

4. **Caching:**
   - Cache frequently accessed calculations
   - Memoize sorting results

## 9. Known Limitations

1. **Single Processor:** Cannot simulate multi-core systems
2. **No I/O:** Processes don't have I/O blocking
3. **No Context Switch Overhead:** Ignores switching costs
4. **No Aging:** Priority doesn't increase with wait time
5. **ASCII Only:** Limited visualization options
6. **No Real-Time:** Simulation only, not actual OS

## 10. Future Enhancements

### Phase 2
- [ ] GUI with JavaFX
- [ ] Process state visualization
- [ ] Graph-based metrics display
- [ ] Export to CSV/PDF

### Phase 3
- [ ] Multi-core simulation
- [ ] I/O blocking simulation
- [ ] Real-time OS features
- [ ] Neural network optimization

### Phase 4
- [ ] Kernel-level integration
- [ ] Live OS process monitoring
- [ ] Distributed scheduling
- [ ] Machine learning optimization

---

## Appendix: Quick Reference

### Common Terms
- **Arrival Time (AT):** When process enters system
- **Burst Time (BT):** CPU execution time needed
- **Completion Time (CT):** When process finishes
- **Turnaround Time (TAT):** CT - AT
- **Waiting Time (WT):** TAT - BT
- **Priority:** 0=highest, increasing=lower
- **Time Quantum:** Time slice for Round Robin
- **Preemption:** Interrupting current process
- **Context Switch:** Saving/restoring process state
- **CPU Utilization:** Percentage of time CPU is busy

### Priority Values
- 0-1: System processes (highest priority)
- 2-4: Interactive processes (medium priority)
- 5+: Batch processes (lowest priority)

### Default Parameters
- Round Robin Time Quantum: 4
- MLQ Queue Count: 3
- MLQ Time Quantums: [2, 4, 8]

---

**Last Updated:** December 2025
**Version:** 1.0
