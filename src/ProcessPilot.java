import algorithms.*;
import java.util.*;
import models.Process;
import models.SchedulingResult;
import utils.ProcessGenerator;
import utils.SchedulingComparator;
import visualization.GanttChart;

/**
 * ProcessPilot - Advanced OS CPU Scheduling Simulator
 * 
 * Features:
 * ✓ Multiple scheduling algorithms (FCFS, SJF, Priority, Round Robin, Multilevel Queue)
 * ✓ Gantt chart visualization
 * ✓ Waiting time & turnaround time calculation
 * ✓ Algorithm comparison
 * ✓ Process arrival & priority support
 */
public class ProcessPilot {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Process> processes;

    public static void main(String[] args) {
        displayWelcome();
        
        while (true) {
            displayMainMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    inputProcesses();
                    break;
                case 2:
                    generateSampleData();
                    break;
                case 3:
                    runScheduler();
                    break;
                case 4:
                    runAllSchedulers();
                    break;
                case 5:
                    displayAbout();
                    break;
                case 6:
                    System.out.println("\n✓ Thank you for using ProcessPilot! Goodbye.\n");
                    System.exit(0);
                default:
                    System.out.println("✗ Invalid choice! Please try again.");
            }
        }
    }

    private static void displayWelcome() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           WELCOME TO PROCESSPILOT                               ║");
        System.out.println("║                  Advanced CPU Scheduling Process Simulator                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝\n");
    }

    private static void displayMainMenu() {
        System.out.println("\n┌─── MAIN MENU ────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. Input Processes Manually                                                     │");
        System.out.println("│ 2. Generate Sample Data                                                         │");
        System.out.println("│ 3. Run Single Scheduling Algorithm                                             │");
        System.out.println("│ 4. Compare All Scheduling Algorithms                                           │");
        System.out.println("│ 5. About ProcessPilot                                                           │");
        System.out.println("│ 6. Exit                                                                         │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────┘");
        System.out.print("Enter your choice (1-6): ");
    }

    private static void inputProcesses() {
        System.out.println("\n┌─── INPUT PROCESSES ───────────────────────────────────────────────────────────┐");
        System.out.print("│ Enter number of processes: ");
        int count = getIntInput();

        processes = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            System.out.println("│");
            System.out.print("│ Process " + i + " - Arrival Time: ");
            int arrival = getIntInput();

            System.out.print("│ Process " + i + " - Burst Time: ");
            int burst = getIntInput();

            System.out.print("│ Process " + i + " - Priority (0=highest): ");
            int priority = getIntInput();

            processes.add(new Process(i, arrival, burst, priority));
        }

        System.out.println("│");
        System.out.println("✓ Processes added successfully!");
        displayProcesses();
    }

    private static void generateSampleData() {
        System.out.println("\n┌─── GENERATE SAMPLE DATA ──────────────────────────────────────────────────────┐");
        System.out.println("│ 1. Sample Dataset 1 (5 processes)                                               │");
        System.out.println("│ 2. Sample Dataset 2 (4 processes)                                               │");
        System.out.println("│ 3. Sample Dataset 3 (5 processes - priority based)                              │");
        System.out.println("│ 4. Generate Random Processes                                                    │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────┘");
        System.out.print("Select dataset (1-4): ");
        
        int choice = getIntInput();
        switch (choice) {
            case 1:
                processes = ProcessGenerator.generateSampleProcesses();
                break;
            case 2:
                processes = ProcessGenerator.generateExample2();
                break;
            case 3:
                processes = ProcessGenerator.generateExample3();
                break;
            case 4:
                System.out.print("Enter number of processes: ");
                int count = getIntInput();
                processes = ProcessGenerator.generateRandomProcesses(count, 10, 10, 4);
                break;
            default:
                System.out.println("✗ Invalid choice!");
                return;
        }

        System.out.println("\n✓ Dataset loaded successfully!");
        displayProcesses();
    }

    private static void runScheduler() {
        if (processes == null || processes.isEmpty()) {
            System.out.println("\n✗ No processes loaded! Please load or input processes first.");
            return;
        }

        System.out.println("\n┌─── SELECT SCHEDULER ──────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. FCFS (First Come First Served)                                               │");
        System.out.println("│ 2. SJF Non-Preemptive (Shortest Job First)                                      │");
        System.out.println("│ 3. SJF Preemptive (Shortest Remaining Time First)                               │");
        System.out.println("│ 4. Priority Scheduling Non-Preemptive                                           │");
        System.out.println("│ 5. Priority Scheduling Preemptive                                               │");
        System.out.println("│ 6. Round Robin                                                                  │");
        System.out.println("│ 7. Multilevel Queue Scheduling (Advanced)                                       │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────┘");
        System.out.print("Select scheduler (1-7): ");
        
        int choice = getIntInput();
        Scheduler scheduler = null;

        switch (choice) {
            case 1:
                scheduler = new FCFSScheduler();
                break;
            case 2:
                scheduler = new SJFScheduler(false);
                break;
            case 3:
                scheduler = new SJFScheduler(true);
                break;
            case 4:
                scheduler = new PriorityScheduler(false);
                break;
            case 5:
                scheduler = new PriorityScheduler(true);
                break;
            case 6:
                System.out.print("Enter time quantum (default 4): ");
                int tq = getIntInput();
                scheduler = new RoundRobinScheduler(tq);
                break;
            case 7:
                scheduler = new MultilevelQueueScheduler();
                break;
            default:
                System.out.println("✗ Invalid choice!");
                return;
        }

        // Create copies of processes
        List<Process> processCopies = new ArrayList<>();
        for (Process p : processes) {
            processCopies.add(p.copy());
        }

        SchedulingResult result = scheduler.schedule(processCopies);
        displaySchedulingResult(result);
    }

    private static void runAllSchedulers() {
        if (processes == null || processes.isEmpty()) {
            System.out.println("\n✗ No processes loaded! Please load or input processes first.");
            return;
        }

        System.out.println("\nRunning all schedulers...\n");

        List<SchedulingResult> results = new ArrayList<>();

        // FCFS
        List<Process> processCopies = copyProcesses();
        results.add(new FCFSScheduler().schedule(processCopies));

        // SJF Non-Preemptive
        processCopies = copyProcesses();
        results.add(new SJFScheduler(false).schedule(processCopies));

        // SJF Preemptive
        processCopies = copyProcesses();
        results.add(new SJFScheduler(true).schedule(processCopies));

        // Priority Non-Preemptive
        processCopies = copyProcesses();
        results.add(new PriorityScheduler(false).schedule(processCopies));

        // Priority Preemptive
        processCopies = copyProcesses();
        results.add(new PriorityScheduler(true).schedule(processCopies));

        // Round Robin
        processCopies = copyProcesses();
        results.add(new RoundRobinScheduler(4).schedule(processCopies));

        // Multilevel Queue
        processCopies = copyProcesses();
        results.add(new MultilevelQueueScheduler().schedule(processCopies));

        // Display comparison
        SchedulingComparator.compareResults(results);

        System.out.println("\n┌─── VIEW OPTIONS ──────────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. View Detailed Comparison                                                     │");
        System.out.println("│ 2. View Gantt Charts for All Algorithms                                         │");
        System.out.println("│ 3. Return to Main Menu                                                          │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────┘");
        System.out.print("Select option (1-3): ");
        
        int choice = getIntInput();
        switch (choice) {
            case 1:
                SchedulingComparator.printDetailedComparison(results);
                break;
            case 2:
                for (SchedulingResult result : results) {
                    GanttChart chart = new GanttChart(result);
                    chart.display();
                }
                break;
            default:
                break;
        }
    }

    private static void displaySchedulingResult(SchedulingResult result) {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ SCHEDULING RESULT - " + result.getAlgorithmName());
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");

        // Display Gantt Chart
        GanttChart ganttChart = new GanttChart(result);
        ganttChart.display();

        // Display metrics
        System.out.println("\n┌─── METRICS ───────────────────────────────────────────────────────────────────┐");
        System.out.printf("│ Average Waiting Time:    %.2f%n", result.getAverageWaitingTime());
        System.out.printf("│ Average Turnaround Time: %.2f%n", result.getAverageTurnaroundTime());
        System.out.printf("│ CPU Utilization:         %.2f%%%n", result.getCpuUtilization());
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────┘");

        // Display process details
        System.out.println("\n┌─── PROCESS DETAILS ───────────────────────────────────────────────────────────┐");
        for (Process p : result.getProcesses()) {
            System.out.println("│ " + p.getDetailedInfo());
        }
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────┘");
    }

    private static void displayProcesses() {
        if (processes == null || processes.isEmpty()) {
            System.out.println("✗ No processes to display!");
            return;
        }

        System.out.println("\n┌─── LOADED PROCESSES ──────────────────────────────────────────────────────────┐");
        System.out.printf("│ %5s | %10s | %10s | %8s%n", "PID", "Arrival", "Burst", "Priority");
        System.out.println("├───────┼────────────┼────────────┼──────────┤");
        
        for (Process p : processes) {
            System.out.printf("│ P%-4d | %10d | %10d | %8d │%n", 
                    p.getProcessId(), p.getArrivalTime(), p.getBurstTime(), p.getPriority());
        }
        System.out.println("└───────┴────────────┴────────────┴──────────┘");
    }

    private static void displayAbout() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                             ABOUT PROCESSPILOT                                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("ProcessPilot is an advanced CPU Scheduling Simulator that demonstrates various");
        System.out.println("operating system scheduling algorithms.");
        System.out.println();
        System.out.println("📋 FEATURES:");
        System.out.println("  ✓ Multiple scheduling algorithms");
        System.out.println("  ✓ Gantt chart visualization");
        System.out.println("  ✓ Waiting time & turnaround time calculation");
        System.out.println("  ✓ Algorithm comparison and analysis");
        System.out.println("  ✓ Process arrival & priority support");
        System.out.println();
        System.out.println("🔧 SUPPORTED ALGORITHMS:");
        System.out.println("  1. FCFS (First Come First Served)");
        System.out.println("  2. SJF (Shortest Job First) - Non-preemptive & Preemptive");
        System.out.println("  3. Priority Scheduling - Non-preemptive & Preemptive");
        System.out.println("  4. Round Robin");
        System.out.println("  5. Multilevel Queue Scheduling (Advanced)");
        System.out.println();
        System.out.println("💡 USAGE:");
        System.out.println("  1. Load or generate process data");
        System.out.println("  2. Select a scheduling algorithm");
        System.out.println("  3. View the Gantt chart and metrics");
        System.out.println("  4. Compare different algorithms");
        System.out.println();
    }

    private static List<Process> copyProcesses() {
        List<Process> copies = new ArrayList<>();
        for (Process p : processes) {
            copies.add(p.copy());
        }
        return copies;
    }

    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
