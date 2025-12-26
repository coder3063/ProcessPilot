# ProcessPilot - Installation & First Run Guide

## 🎉 Welcome to ProcessPilot!

Your advanced CPU Scheduling Simulator is ready. Follow these simple steps to get started.

## ⚡ Quick Start (Choose Your OS)

### Windows Users
```
1. Open Command Prompt or PowerShell
2. Navigate to: D:\ProcessPilot
3. Run: build.bat
4. Run: run.bat
5. Select from menu!
```

### Linux/Mac Users
```
1. Open Terminal
2. Navigate to: ProcessPilot directory
3. Run: chmod +x *.sh
4. Run: ./build.sh
5. Run: ./run.sh
6. Select from menu!
```

## 📋 What You Get

### 5 Scheduling Algorithms (7 Variants)
✅ FCFS - First Come First Served
✅ SJF - Shortest Job First (2 variants)
✅ Priority Scheduling (2 variants)
✅ Round Robin
✅ Multilevel Queue (Advanced)

### Features
✅ Gantt Chart Visualization
✅ Performance Metrics (WT, TAT, CPU%)
✅ Algorithm Comparison
✅ Sample Data Included
✅ Full Documentation

## 📚 Documentation Included

| Document | What For |
|----------|----------|
| README.md | Full project overview |
| QUICK_REFERENCE.md | 2-minute lookup guide |
| GETTING_STARTED.md | Step-by-step tutorial |
| TECHNICAL_DOCS.md | Implementation details |
| PROJECT_SUMMARY.md | What's included |
| FEATURES_CHECKLIST.md | Feature verification |
| INDEX.md | Documentation guide |
| This file | Quick start |

**Start with:** QUICK_REFERENCE.md (5 minutes to understand everything!)

## 🚀 First Time Running

### Step-by-Step
1. **Start Application** → Main menu appears
2. **Select Option 2** → Load sample data
3. **Select Option 4** → Compare all algorithms
4. **View Results** → See which algorithm is best
5. **Experiment** → Try different options

### Expected Output
```
Algorithm              | Avg WT | Avg TAT | CPU Util
─────────────────────────────────────────────────────
FCFS                   | 8.20   | 12.40   | 100%
SJF Non-Preemptive     | 5.60   | 9.80    | 100%
...and more
```

## 🎯 Menu Overview

```
1. Input Processes Manually     ← For custom data
2. Generate Sample Data         ← For quick testing
3. Run Single Algorithm         ← Test one scheduler
4. Compare All Algorithms       ← Find the best
5. About ProcessPilot           ← Learn more
6. Exit                         ← Quit
```

## 🔧 System Requirements

- Java 8 or higher
- 100MB disk space
- Windows, Linux, or Mac
- Any modern terminal/command prompt

## ✅ Verify Installation

### Windows
```
java -version
// Should show Java version 8 or higher
```

### Linux/Mac
```
java -version
// Should show Java version 8 or higher
```

## 🐛 Troubleshooting

### "Command not found"
→ Ensure you're in the ProcessPilot directory

### "Build failed"
→ Ensure Java is installed: `java -version`

### "Cannot find main class"
→ Run build script first before run

### Application runs but looks wrong
→ Ensure terminal window is wide enough

## 💡 Pro Tips

1. **First run:** Use Option 2 & 4 to see everything
2. **Learning:** Read GETTING_STARTED.md after first run
3. **Experimenting:** Use Option 1 to input custom processes
4. **Deep dive:** Read TECHNICAL_DOCS.md for implementation

## 📖 Recommended Reading Order

For **first 10 minutes:**
```
1. This file (Installation & First Run) ← You are here
2. QUICK_REFERENCE.md (5 minutes)
3. Run the application (5 minutes)
```

For **next 30 minutes:**
```
1. GETTING_STARTED.md (detailed guide)
2. Experiment with different options
3. Try custom process input
```

For **deeper understanding:**
```
1. README.md (full features)
2. TECHNICAL_DOCS.md (how it works)
3. Study the source code
```

## 🎓 What You'll Learn

After using ProcessPilot, you'll understand:
- How CPU scheduling works
- Trade-offs between algorithms
- Performance metrics (Waiting time, Turnaround time)
- When to use which algorithm
- Real OS scheduling complexity

## 📞 Quick Questions?

| Question | Answer Location |
|----------|-----------------|
| How do I start? | This file |
| How do I use it? | QUICK_REFERENCE.md |
| What's each algorithm? | GETTING_STARTED.md |
| How does it work? | TECHNICAL_DOCS.md |
| What's included? | PROJECT_SUMMARY.md |
| I'm lost! | INDEX.md |

## 🎯 First 5 Minutes

**Minute 0-1:** Read this file
**Minute 1-2:** Build and run the application
**Minute 2-3:** Select Option 2, choose sample data
**Minute 3-4:** Select Option 4, view comparison
**Minute 4-5:** Explore the menu options

## ✨ Key Features to Try

### Feature 1: Algorithm Comparison
```
Menu → Option 4 → Automatic comparison of all 7 algorithms
```
**Why:** See how different algorithms perform on same data

### Feature 2: Gantt Charts
```
Menu → Option 3 → Select algorithm → View visual timeline
```
**Why:** Understand process execution visually

### Feature 3: Custom Processes
```
Menu → Option 1 → Input your own processes
```
**Why:** Test algorithms on your specific scenarios

### Feature 4: Performance Metrics
```
All outputs show:
- Average Waiting Time (lower is better)
- Average Turnaround Time (lower is better)
- CPU Utilization (higher is better)
```
**Why:** Make informed decisions about which algorithm to use

## 🚀 Advanced Usage (Later)

After getting comfortable:
- Read TECHNICAL_DOCS.md
- Study the source code
- Try modifying algorithms
- Add new features
- Extend the simulation

## 📊 Sample Dataset

ProcessPilot includes sample datasets with:
- Different process arrival times
- Various burst times
- Multiple priority levels
- Realistic mix of workloads

All datasets are designed for educational purposes.

## 🎓 Learning Outcomes

By the end of your first session, you'll know:
1. ✓ What scheduling algorithms are
2. ✓ How FCFS differs from SJF
3. ✓ What preemption means
4. ✓ How priority affects scheduling
5. ✓ Why Round Robin is fair
6. ✓ When to use which algorithm

## 📈 Next Steps After First Run

1. **Understand:** Read GETTING_STARTED.md (15 min)
2. **Experiment:** Try Option 1 with custom data (10 min)
3. **Analyze:** Study metrics for different datasets (15 min)
4. **Learn:** Read TECHNICAL_DOCS.md (20 min)
5. **Master:** Explore source code (30+ min)

## 🏆 Success Checklist

After first run, you should be able to:
- [ ] Start ProcessPilot successfully
- [ ] Load sample data
- [ ] View Gantt charts
- [ ] Understand performance metrics
- [ ] Compare algorithms
- [ ] Identify best algorithm for data
- [ ] Explain differences between algorithms
- [ ] Input custom processes

## ⚠️ Common First-Time Issues

### Issue: Build fails
**Solution:** Check Java installation with `java -version`

### Issue: Menu doesn't align properly
**Solution:** Make terminal window wider

### Issue: Numbers look confusing
**Solution:** Read QUICK_REFERENCE.md - metrics section

### Issue: Want to understand algorithms more
**Solution:** Read GETTING_STARTED.md - algorithm section

## 🎉 You're Ready!

Everything is set up. Your ProcessPilot is:
- ✅ Complete
- ✅ Documented
- ✅ Ready to run
- ✅ Full-featured

**Choose your OS above and get started!**

## 📚 Document Quick Links

```
Quick Reference → QUICK_REFERENCE.md
Detailed Guide → GETTING_STARTED.md
All Documents → INDEX.md
Technical Info → TECHNICAL_DOCS.md
Full Overview → README.md
What's Done → PROJECT_SUMMARY.md
Features List → FEATURES_CHECKLIST.md
```

## 🌟 Final Notes

- ProcessPilot is **free and open**
- All source code is **clearly commented**
- Full documentation is **included**
- You can **extend and modify** it
- It's great for **learning OS concepts**

---

## Need Help?

1. **Right now?** → Read QUICK_REFERENCE.md
2. **Getting started?** → Read GETTING_STARTED.md
3. **Confused about output?** → Check QUICK_REFERENCE.md metrics section
4. **Want all answers?** → Read INDEX.md

## Ready? Let's Go! 🚀

**Pick your OS above and run the application!**

Happy Scheduling! 🎓

---

**ProcessPilot v1.0**
**Ready for Use**
**Fully Documented**
**Let's Begin!** 🎉
