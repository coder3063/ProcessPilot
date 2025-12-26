package utils;

import models.SchedulingResult;
import java.util.*;

/**
 * Utility class for comparing different scheduling algorithms
 */
public class SchedulingComparator {

    /**
     * Compare multiple scheduling results
     */
    public static void compareResults(List<SchedulingResult> results) {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ SCHEDULING ALGORITHM COMPARISON");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝\n");

        // Header
        System.out.printf("%-40s | %15s | %15s | %12s\n", 
                "Algorithm", "Avg Waiting Time", "Avg Turnaround", "CPU Util %");
        System.out.println("─".repeat(90));

        // Results
        for (SchedulingResult result : results) {
            System.out.printf("%-40s | %15.2f | %15.2f | %12.2f\n",
                    result.getAlgorithmName(),
                    result.getAverageWaitingTime(),
                    result.getAverageTurnaroundTime(),
                    result.getCpuUtilization()
            );
        }

        // Find best algorithm
        System.out.println("\n" + "─".repeat(90));
        
        SchedulingResult bestWT = results.stream()
                .min(Comparator.comparingDouble(SchedulingResult::getAverageWaitingTime))
                .orElse(null);
        
        SchedulingResult bestTAT = results.stream()
                .min(Comparator.comparingDouble(SchedulingResult::getAverageTurnaroundTime))
                .orElse(null);
        
        SchedulingResult bestUtil = results.stream()
                .max(Comparator.comparingDouble(SchedulingResult::getCpuUtilization))
                .orElse(null);

        if (bestWT != null) {
            System.out.println("✓ Best Waiting Time: " + bestWT.getAlgorithmName() + 
                    " (" + String.format("%.2f", bestWT.getAverageWaitingTime()) + ")");
        }
        if (bestTAT != null) {
            System.out.println("✓ Best Turnaround Time: " + bestTAT.getAlgorithmName() + 
                    " (" + String.format("%.2f", bestTAT.getAverageTurnaroundTime()) + ")");
        }
        if (bestUtil != null) {
            System.out.println("✓ Best CPU Utilization: " + bestUtil.getAlgorithmName() + 
                    " (" + String.format("%.2f", bestUtil.getCpuUtilization()) + "%)");
        }
    }

    /**
     * Get ranked results by a specific metric
     */
    public static List<SchedulingResult> rankByWaitingTime(List<SchedulingResult> results) {
        List<SchedulingResult> ranked = new ArrayList<>(results);
        ranked.sort(Comparator.comparingDouble(SchedulingResult::getAverageWaitingTime));
        return ranked;
    }

    public static List<SchedulingResult> rankByTurnaroundTime(List<SchedulingResult> results) {
        List<SchedulingResult> ranked = new ArrayList<>(results);
        ranked.sort(Comparator.comparingDouble(SchedulingResult::getAverageTurnaroundTime));
        return ranked;
    }

    public static List<SchedulingResult> rankByCPUUtilization(List<SchedulingResult> results) {
        List<SchedulingResult> ranked = new ArrayList<>(results);
        ranked.sort((a, b) -> Double.compare(b.getCpuUtilization(), a.getCpuUtilization()));
        return ranked;
    }

    /**
     * Print detailed comparison
     */
    public static void printDetailedComparison(List<SchedulingResult> results) {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ DETAILED SCHEDULING COMPARISON");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝\n");

        for (SchedulingResult result : results) {
            System.out.println("┌─ " + result.getAlgorithmName() + " " + "─".repeat(70));
            System.out.println("│ Average Waiting Time:    " + String.format("%.2f", result.getAverageWaitingTime()));
            System.out.println("│ Average Turnaround Time: " + String.format("%.2f", result.getAverageTurnaroundTime()));
            System.out.println("│ CPU Utilization:         " + String.format("%.2f%%", result.getCpuUtilization()));
            
            System.out.println("│");
            System.out.println("│ Process Details:");
            for (var process : result.getProcesses()) {
                System.out.println("│   " + process.getDetailedInfo());
            }
            System.out.println("└" + "─".repeat(80));
            System.out.println();
        }
    }
}
