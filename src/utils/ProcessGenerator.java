package utils;

import models.Process;
import java.util.*;

/**
 * Utility class for generating sample processes for testing
 */
public class ProcessGenerator {

    /**
     * Generate sample processes for demonstration
     */
    public static List<Process> generateSampleProcesses() {
        List<Process> processes = new ArrayList<>();
        
        // Sample dataset 1
        processes.add(new Process(1, 0, 8, 2));
        processes.add(new Process(2, 1, 4, 1));
        processes.add(new Process(3, 2, 2, 3));
        processes.add(new Process(4, 3, 1, 2));
        processes.add(new Process(5, 4, 3, 1));
        
        return processes;
    }

    /**
     * Generate random processes
     */
    public static List<Process> generateRandomProcesses(int count, int maxArrival, int maxBurst, int maxPriority) {
        List<Process> processes = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= count; i++) {
            int arrival = random.nextInt(maxArrival + 1);
            int burst = random.nextInt(maxBurst) + 1;
            int priority = random.nextInt(maxPriority + 1);

            processes.add(new Process(i, arrival, burst, priority));
        }

        return processes;
    }

    /**
     * Get example dataset 2
     */
    public static List<Process> generateExample2() {
        List<Process> processes = new ArrayList<>();
        
        processes.add(new Process(1, 0, 3, 1));
        processes.add(new Process(2, 1, 6, 2));
        processes.add(new Process(3, 2, 4, 1));
        processes.add(new Process(4, 3, 2, 3));
        
        return processes;
    }

    /**
     * Get example dataset 3 (priority based)
     */
    public static List<Process> generateExample3() {
        List<Process> processes = new ArrayList<>();
        
        processes.add(new Process(1, 0, 5, 3)); // Low priority
        processes.add(new Process(2, 0, 3, 1)); // High priority
        processes.add(new Process(3, 1, 4, 2)); // Medium priority
        processes.add(new Process(4, 2, 2, 1)); // High priority
        processes.add(new Process(5, 3, 3, 3)); // Low priority
        
        return processes;
    }
}
