# ProcessPilot GUI - Quick Start Guide

## ⚡ 5-Minute Quick Start

### Step 1: Download JavaFX (2 minutes)
1. Go to [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
2. Click "Download"
3. Select "JavaFX SDK 21" (or latest version)
4. Download for your operating system
5. Extract to a folder (e.g., `C:\javafx-sdk-21` or `~/javafx-sdk-21`)

### Step 2: Set Up Your IDE (2 minutes)

#### **IntelliJ IDEA**
1. Open ProcessPilot project
2. File → Project Structure → Libraries
3. Click `+` → Java
4. Select your JavaFX SDK folder
5. File → Project Structure → Modules
6. Go to Dependencies tab
7. Click `+` → Library
8. Select JavaFX library
9. Done!

#### **Eclipse**
1. Window → Preferences → Java → Build Path → User Libraries
2. Click "New" → Type "JavaFX" → OK
3. Click "Add JARs" → Navigate to JavaFX `lib` folder → Select all JARs
4. Right-click project → Properties → Java Build Path → Libraries tab
5. Add Library → User Library → Select "JavaFX"
6. Done!

#### **VS Code**
1. Open the ProcessPilot folder
2. Create `.vscode/settings.json`:
```json
{
    "java.project.referencedLibraries": {
        "include": [
            "/path/to/javafx-sdk-21/lib/*.jar"
        ]
    }
}
```
3. Done!

### Step 3: Run the Application (1 minute)

#### **From IDE:**
- Click Run button → Select "ui.MainApp" → Click Run

#### **From Command Line:**

**Windows:**
```batch
build_gui.bat
```
(Make sure you edit the `JAVAFX_SDK` path in the script first!)

**Linux/Mac:**
```bash
chmod +x build_gui.sh
./build_gui.sh
```
(Make sure you edit the `JAVAFX_SDK` path in the script first!)

---

## 🎯 What You'll See

The ProcessPilot GUI window opens with 4 tabs:

### 📥 **Input Tab**
- Load sample datasets
- Add/edit processes manually
- Columns: ID, Arrival Time, Burst Time, Priority

### ⚙️ **Scheduler Tab**
- Select algorithm (FCFS, SJF, Priority, Round Robin, Multilevel Queue)
- Set time quantum (for Round Robin)
- Click "Run Scheduler"

### 📊 **Results Tab**
- Average Waiting Time
- Average Turnaround Time
- CPU Utilization
- Visual Gantt Chart
- Process Details Table

### 📊 **Compare All Tab**
- Runs all 7 algorithms at once
- Compares metrics side-by-side
- Shows which algorithm is best for your data

---

## 🔧 Environment Variables (Optional)

If you want to avoid editing the script each time, set an environment variable:

### Windows:
```batch
setx JAVAFX_SDK "C:\javafx-sdk-21"
```

### Linux/Mac:
```bash
export JAVAFX_SDK="~/javafx-sdk-21"
echo 'export JAVAFX_SDK="~/javafx-sdk-21"' >> ~/.bashrc
```

Then the scripts will find it automatically.

---

## ❓ Still Having Issues?

### Issue: "Could not find or load main class ui.MainApp"
**Solution:** 
- Make sure you have JavaFX SDK downloaded and extracted
- Update the path in build script or IDE settings
- Run `Build → Clean Project` and `Build → Rebuild Project`

### Issue: "javafx.fxml" not found
**Solution:**
- Make sure you're using the full JavaFX SDK, not just a portion
- All JAR files in the `lib` folder are needed

### Issue: Resources not loading
**Solution:**
- Make sure `resources/` folder exists with `main.fxml` and `style.css`
- In IDE, mark `resources` folder as "Resources Root"

### Issue: Window appears blank
**Solution:**
- Try Java 11 or 17 instead of newer versions
- Check console output for specific error messages
- Delete `build/` folder and rebuild

---

## 📚 Full Documentation

For more detailed setup instructions, see [JAVAFX_SETUP_GUIDE.md](JAVAFX_SETUP_GUIDE.md)

---

## 🎉 You're All Set!

Once running, try:
1. Click "Load Sample Dataset 1" in Input tab
2. Go to Results tab and click "Run Scheduler" for FCFS
3. See the Gantt chart and metrics
4. Go to Compare tab and see how other algorithms compare

**Enjoy your professional CPU Scheduling Simulator GUI!** 🚀
