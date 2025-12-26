package algorithms;

import models.Process;
import models.SchedulingResult;
import java.util.*;

/**
 * First Come First Served (FCFS) Scheduling Algorithm
 * Processes are scheduled in the order they arrive
 */
public class FCFSScheduler implements Scheduler {

    @Override
    public SchedulingResult schedule(List<Process> processes) {
        SchedulingResult result = new SchedulingResult("FCFS");
        
        // Create copies to avoid modifying original list
        List<Process> processCopies = new ArrayList<>();
        for (Process p : processes) {
            processCopies.add(p.copy());
        }
        
        // Sort by arrival time
        processCopies.sort((a, b) -> Integer.compare(a.getArrivalTime(), b.getArrivalTime()));

        int currentTime = 0;
        result.addToGanttTime(0);

        for (Process process : processCopies) {
            // If process hasn't arrived yet, wait
            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();
            }

            // Process executes
            result.addToGanttChart(process.getProcessId());
            currentTime += process.getBurstTime();
            result.addToGanttTime(currentTime);

            // Calculate metrics
            process.setCompletionTime(currentTime);
            process.setTurnaroundTime(process.getCompletionTime() - process.getArrivalTime());
            process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());
        }

        // Calculate averages
        calculateMetrics(processCopies, result);
        result.setProcesses(processCopies);

        return result;
    }

    @Override
    public String getAlgorithmName() {
        return "FCFS (First Come First Served)";
    }

    private void calculateMetrics(List<Process> processes, SchedulingResult result) {
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int totalBurstTime = 0;

        for (Process p : processes) {
            totalWaitingTime += p.getWaitingTime();
            totalTurnaroundTime += p.getTurnaroundTime();
            totalBurstTime += p.getBurstTime();
        }

        result.setAverageWaitingTime(totalWaitingTime / processes.size());
        result.setAverageTurnaroundTime(totalTurnaroundTime / processes.size());
        
        // CPU Utilization = Total Burst Time / Total Time
        int totalTime = result.getGanttTime().get(result.getGanttTime().size() - 1);
        result.setCpuUtilization((double) totalBurstTime / totalTime * 100);
    }
}
