package visualization;

import models.SchedulingResult;
import java.util.*;

/**
 * Gantt Chart visualization for scheduling results
 */
public class GanttChart {
    private SchedulingResult result;

    public GanttChart(SchedulingResult result) {
        this.result = result;
    }

    /**
     * Display Gantt chart in console
     */
    public void display() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ GANTT CHART - " + result.getAlgorithmName());
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");

        List<Integer> ganttChart = result.getGanttChart();
        List<Integer> ganttTime = result.getGanttTime();

        if (ganttChart.isEmpty()) {
            System.out.println("No scheduling data available.");
            return;
        }

        // Print timeline
        System.out.print("│");
        for (int i = 0; i < ganttChart.size(); i++) {
            int processId = ganttChart.get(i);
            int startTime = ganttTime.get(i);
            int endTime = ganttTime.get(i + 1);
            int duration = endTime - startTime;
            
            // Print process box
            String processLabel = String.format(" P%-2d ", processId);
            System.out.print(processLabel);
            System.out.print("│");
        }
        System.out.println();

        // Print time axis
        System.out.print("0");
        for (int i = 0; i < ganttChart.size(); i++) {
            int endTime = ganttTime.get(i + 1);
            String timeStr = String.valueOf(endTime);
            // Align time under process box
            int spaces = 5 - timeStr.length();
            System.out.print(" ".repeat(Math.max(0, spaces)));
            System.out.print(timeStr);
        }
        System.out.println();
    }

    /**
     * Export Gantt chart as ASCII art
     */
    public String exportAsASCII() {
        StringBuilder sb = new StringBuilder();
        List<Integer> ganttChart = result.getGanttChart();
        List<Integer> ganttTime = result.getGanttTime();

        if (ganttChart.isEmpty()) {
            return "No scheduling data available.";
        }

        // Top border
        sb.append("┌");
        for (int i = 0; i < ganttChart.size(); i++) {
            sb.append("─────┬");
        }
        sb.append("─┐\n");

        // Process boxes
        sb.append("│");
        for (int processId : ganttChart) {
            sb.append(String.format(" P%-2d │", processId));
        }
        sb.append("\n");

        // Middle border
        sb.append("├");
        for (int i = 0; i < ganttChart.size(); i++) {
            sb.append("─────┼");
        }
        sb.append("─┤\n");

        // Time values
        sb.append("│");
        for (int i = 0; i <= ganttChart.size(); i++) {
            int time = ganttTime.get(i);
            sb.append(String.format("%5d│", time));
        }
        sb.append("\n");

        // Bottom border
        sb.append("└");
        for (int i = 0; i < ganttChart.size(); i++) {
            sb.append("─────┴");
        }
        sb.append("─┘\n");

        return sb.toString();
    }

    /**
     * Get Gantt chart data as formatted string
     */
    @Override
    public String toString() {
        return exportAsASCII();
    }
}
