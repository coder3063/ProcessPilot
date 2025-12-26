package algorithms;

import models.Process;
import models.SchedulingResult;
import java.util.*;

/**
 * Shortest Job First (SJF) Scheduling Algorithm
 * Supports both preemptive (SRTF) and non-preemptive modes
 */
public class SJFScheduler implements Scheduler {
    private boolean preemptive;

    public SJFScheduler(boolean preemptive) {
        this.preemptive = preemptive;
    }

    @Override
    public SchedulingResult schedule(List<Process> processes) {
        String algName = preemptive ? "SJF (Preemptive - SRTF)" : "SJF (Non-Preemptive)";
        SchedulingResult result = new SchedulingResult(algName);

        // Create copies to avoid modifying original list
        List<Process> processCopies = new ArrayList<>();
        for (Process p : processes) {
            processCopies.add(p.copy());
        }

        if (preemptive) {
            schedulePreemptive(processCopies, result);
        } else {
            scheduleNonPreemptive(processCopies, result);
        }

        calculateMetrics(processCopies, result);
        result.setProcesses(processCopies);

        return result;
    }

    private void scheduleNonPreemptive(List<Process> processes, SchedulingResult result) {
        int currentTime = 0;
        boolean[] completed = new boolean[processes.size()];
        result.addToGanttTime(0);

        for (int i = 0; i < processes.size(); i++) {
            int shortest = -1;
            int minBurst = Integer.MAX_VALUE;

            // Find process with shortest burst time among arrived processes
            for (int j = 0; j < processes.size(); j++) {
                if (!completed[j] && processes.get(j).getArrivalTime() <= currentTime) {
                    if (processes.get(j).getBurstTime() < minBurst) {
                        minBurst = processes.get(j).getBurstTime();
                        shortest = j;
                    }
                }
            }

            // If no process has arrived, get the next arriving process
            if (shortest == -1) {
                int minArrival = Integer.MAX_VALUE;
                for (int j = 0; j < processes.size(); j++) {
                    if (!completed[j] && processes.get(j).getArrivalTime() < minArrival) {
                        minArrival = processes.get(j).getArrivalTime();
                        shortest = j;
                    }
                }
                currentTime = processes.get(shortest).getArrivalTime();
            }

            // Execute the process
            Process p = processes.get(shortest);
            result.addToGanttChart(p.getProcessId());
            currentTime += p.getBurstTime();
            result.addToGanttTime(currentTime);

            p.setCompletionTime(currentTime);
            p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
            p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
            
            completed[shortest] = true;
        }
    }

    private void schedulePreemptive(List<Process> processes, SchedulingResult result) {
        int currentTime = 0;
        int completed = 0;
        int n = processes.size();
        boolean[] isCompleted = new boolean[n];
        result.addToGanttTime(0);

        while (completed < n) {
            // Find process with shortest remaining time
            int shortest = -1;
            int minRemaining = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && processes.get(i).getArrivalTime() <= currentTime) {
                    if (processes.get(i).getRemainingTime() < minRemaining) {
                        minRemaining = processes.get(i).getRemainingTime();
                        shortest = i;
                    }
                }
            }

            // If no process has arrived, jump to next arrival
            if (shortest == -1) {
                int minArrival = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!isCompleted[i] && processes.get(i).getArrivalTime() < minArrival) {
                        minArrival = processes.get(i).getArrivalTime();
                        shortest = i;
                    }
                }
                currentTime = processes.get(shortest).getArrivalTime();
                continue;
            }

            // Execute for 1 time unit
            Process p = processes.get(shortest);
            
            if (p.getRemainingTime() == p.getBurstTime()) {
                result.addToGanttChart(p.getProcessId());
            }

            p.setRemainingTime(p.getRemainingTime() - 1);
            currentTime++;

            if (p.getRemainingTime() == 0) {
                result.addToGanttTime(currentTime);
                p.setCompletionTime(currentTime);
                p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
                p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
                isCompleted[shortest] = true;
                completed++;
            } else if (result.getGanttChart().isEmpty() || 
                       result.getGanttChart().get(result.getGanttChart().size() - 1) != p.getProcessId()) {
                // Gantt chart was updated, add time if needed
                if (result.getGanttTime().size() == result.getGanttChart().size()) {
                    result.addToGanttTime(currentTime);
                }
            }
        }

        if (result.getGanttTime().size() == result.getGanttChart().size()) {
            result.addToGanttTime(currentTime);
        }
    }

    @Override
    public String getAlgorithmName() {
        return preemptive ? "SJF (Preemptive)" : "SJF (Non-Preemptive)";
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

        int totalTime = result.getGanttTime().get(result.getGanttTime().size() - 1);
        result.setCpuUtilization((double) totalBurstTime / totalTime * 100);
    }
}
