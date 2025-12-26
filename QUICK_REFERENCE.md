# ProcessPilot - Quick Reference Card

## 🚀 Quick Start (30 seconds)

### Windows
```
1. cd D:\ProcessPilot
2. build.bat
3. run.bat
```

### Linux/Mac
```
1. cd ProcessPilot
2. chmod +x *.sh
3. ./build.sh && ./run.sh
```

## 📋 Main Menu Guide

| Option | Action | When to Use |
|--------|--------|-------------|
| 1 | Input Processes | When you have specific processes to test |
| 2 | Generate Sample Data | For quick testing and learning |
| 3 | Run Single Algorithm | To test one specific scheduler |
| 4 | Compare All Algorithms | To find the best algorithm for your data |
| 5 | About ProcessPilot | To learn more about the project |
| 6 | Exit | To quit the application |

## 📊 5 Core Scheduling Algorithms

### 1️⃣ FCFS (First Come First Served)
- **Easiest:** Yes
- **Best Waiting Time:** No (Often worst)
- **Preemption:** None
- **Use:** Simple batch systems
- **Formula:** Execute in arrival order

### 2️⃣ SJF (Shortest Job First)
**Non-Preemptive:**
- **Best Waiting Time:** Good (but not optimal)
- **Starvation Risk:** Yes (long jobs may wait)
- **Use:** Known burst times

**Preemptive (SRTF):**
- **Best Waiting Time:** Excellent (optimal)
- **Overhead:** Higher (frequent switches)
- **Use:** Optimal average waiting time needed

### 3️⃣ Priority Scheduling
**Non-Preemptive:**
- **Fair:** No (low priority waits)
- **Responsive:** Moderate
- **Use:** Mixed workloads

**Preemptive:**
- **Responsive:** High
- **Overhead:** Higher
- **Use:** Real-time systems

### 4️⃣ Round Robin
- **Fair:** Yes (all get equal time)
- **Overhead:** Medium (frequent switches)
- **Time Quantum:** Default 4, configurable
- **Use:** Time-sharing systems, interactive

### 5️⃣ Multilevel Queue (Advanced)
- **Flexible:** Yes
- **Complex:** High
- **Queues:** 3 (System, Interactive, Batch)
- **Time Quantums:** [2, 4, 8]
- **Use:** Mixed, complex workloads

## 🎯 How to Input a Process

**When prompted, enter:**
1. **Arrival Time:** When does process arrive? (0, 1, 2, ...)
2. **Burst Time:** How long does it need? (1, 2, 5, ...)
3. **Priority:** Importance level (0=highest, 7=lowest)

**Example:**
```
Process 1: Arrival Time: 0
Process 1: Burst Time: 5
Process 1: Priority: 1
```

## 📈 Understanding the Output

### Gantt Chart
```
│ P1  │ P2  │ P3  │
0     5     8     10
```
- Each box = one process executing
- Bottom numbers = time points
- Shows execution order visually

### Metrics
```
Average Waiting Time: 2.67
Average Turnaround Time: 5.67
CPU Utilization: 100%
```

**Formulas:**
- **Waiting Time:** Time waiting before execution
- **Turnaround Time:** Total time from arrival to completion
- **CPU Util:** How much of total time was CPU busy

## 🔍 Choosing an Algorithm

**For fastest average wait time:**
→ Use **SJF Preemptive (SRTF)**

**For fairness:**
→ Use **Round Robin**

**For system responsiveness:**
→ Use **Priority Preemptive**

**For simplicity:**
→ Use **FCFS**

**For complex systems:**
→ Use **Multilevel Queue**

## 💾 Sample Datasets

### Dataset 1 (5 processes)
Default mix of arrival times and burst lengths

### Dataset 2 (4 processes)
Different burst time distribution

### Dataset 3 (5 processes)
Priority-focused with varying priorities

### Dataset 4 (Random)
Generate custom random processes

## ⚙️ Configuration Tips

### Round Robin Time Quantum
- **Small (1-2):** Many context switches, high overhead
- **Medium (4-8):** Balanced (default: 4)
- **Large (10+):** Fewer switches, may behave like FCFS

### Priority Values
```
0-1   = System processes (highest priority)
2-4   = Interactive processes (medium)
5-7   = Batch processes (lowest)
```

### Process Arrival Times
- **All same (0):** Good for algorithm comparison
- **Varied:** More realistic scenario
- **Spread out:** Tests handling of arrivals

## 📊 Comparison Tips

### Best Metric for Each Goal
| Goal | Metric | Lower is Better |
|------|--------|-----------------|
| Quick turnaround | Avg Turnaround Time | Yes |
| Less waiting | Avg Waiting Time | Yes |
| CPU efficiency | CPU Utilization | No (Higher better) |
| Responsiveness | Avg Waiting Time | Yes |
| Fairness | Max Waiting Time | Yes |

## 🎓 Learning Path

1. **Start:** Run with Sample Data (Option 2)
2. **Understand:** Compare All (Option 4)
3. **Experiment:** Try different datasets
4. **Customize:** Input your own processes
5. **Analyze:** Study the metrics
6. **Extend:** Read source code

## 🐛 Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| Build fails | Run from ProjectPilot directory |
| Can't run | Ensure build succeeded first |
| Wrong results | Verify process input (arrival must be ≥0) |
| No processes | Use Option 2 to generate data |
| Low CPU util | Some gaps in schedule (normal) |

## ⏱️ Time Complexity Reference

| Algorithm | Complexity | Best Case |
|-----------|-----------|-----------|
| FCFS | O(n²) | O(n) |
| SJF | O(n²) | O(n²) |
| Priority | O(n²) | O(n²) |
| Round Robin | O(n × q) | O(n) |
| MLQ | O(n × q) | O(n) |

*q = quantum size, n = number of processes*

## 📚 Key Definitions

| Term | Meaning |
|------|---------|
| **Arrival Time** | When process enters system |
| **Burst Time** | How long process takes to complete |
| **Waiting Time** | Time spent waiting for CPU |
| **Turnaround Time** | Total time from arrival to completion |
| **Preemption** | Stopping current process to run another |
| **Context Switch** | Saving/loading process state |
| **Time Quantum** | Time slice in Round Robin |
| **Priority** | Importance level (0=highest) |
| **CPU Utilization** | % of time CPU is active |
| **Gantt Chart** | Visual timeline of execution |

## 🎯 Performance Goals

### Minimize (Lower is Better)
- ✓ Average Waiting Time
- ✓ Average Turnaround Time
- ✓ Context Switches

### Maximize (Higher is Better)
- ✓ CPU Utilization
- ✓ Throughput
- ✓ Responsiveness

## 🔧 Advanced Options

### Custom Time Quantum (Option 6)
```
Enter time quantum: 6
```
Larger quantum = more like FCFS
Smaller quantum = more fair but slower

### Detailed Comparison (Option 4, then 1)
Shows complete details for each algorithm:
- Each process's waiting time
- Each process's turnaround time
- Comprehensive metrics

## 📖 For More Information

- **Complete Guide:** See GETTING_STARTED.md
- **Technical Details:** See TECHNICAL_DOCS.md
- **Full Documentation:** See README.md
- **Project Info:** See PROJECT_SUMMARY.md

## 🚀 Keyboard Shortcuts

| Action | Method |
|--------|--------|
| Clear screen | Not needed (menu redraws) |
| Exit anywhere | Return to main menu or press 6 |
| Undo | Restart application |
| Back | Select different option from menu |

## 💡 Pro Tips

1. **Compare first:** Run Option 4 with samples to see differences
2. **Experiment:** Try different time quantums in RR
3. **Study metrics:** Compare WT vs TAT across algorithms
4. **Read code:** Source files are well-commented
5. **Create tests:** Design process sets that break algorithms

## 🎉 You're Ready!

- ✅ Installation: 1 minute
- ✅ First run: 30 seconds
- ✅ First comparison: 2 minutes
- ✅ Understanding basics: 30 minutes
- ✅ Advanced usage: 1 hour

**Start with Sample Data → Compare All → Experiment with Custom Data!**

---

**ProcessPilot Quick Reference v1.0**
**Happy Scheduling! 🎓**
