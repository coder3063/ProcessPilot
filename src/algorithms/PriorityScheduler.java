package algorithms;

import models.Process;
import models.SchedulingResult;
import java.util.*;

/**
 * Priority Scheduling Algorithm
 * Processes are scheduled based on priority (lower number = higher priority)
 */
public class PriorityScheduler implements Scheduler {
    private boolean preemptive;

    public PriorityScheduler(boolean preemptive) {
        this.preemptive = preemptive;
    }

    @Override
    public SchedulingResult schedule(List<Process> processes) {
        String algName = preemptive ? "Priority Scheduling (Preemptive)" : "Priority Scheduling (Non-Preemptive)";
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
            int highest = -1;
            int maxPriority = Integer.MAX_VALUE;

            // Find process with highest priority (lowest number) among arrived processes
            for (int j = 0; j < processes.size(); j++) {
                if (!completed[j] && processes.get(j).getArrivalTime() <= currentTime) {
                    if (processes.get(j).getPriority() < maxPriority) {
                        maxPriority = processes.get(j).getPriority();
                        highest = j;
                    }
                }
            }

            // If no process has arrived, get the next arriving process with highest priority
            if (highest == -1) {
                int minArrival = Integer.MAX_VALUE;
                for (int j = 0; j < processes.size(); j++) {
                    if (!completed[j]) {
                        if (processes.get(j).getArrivalTime() < minArrival) {
                            minArrival = processes.get(j).getArrivalTime();
                        }
                    }
                }
                
                maxPriority = Integer.MAX_VALUE;
                for (int j = 0; j < processes.size(); j++) {
                    if (!completed[j] && processes.get(j).getArrivalTime() == minArrival) {
                        if (processes.get(j).getPriority() < maxPriority) {
                            maxPriority = processes.get(j).getPriority();
                            highest = j;
                        }
                    }
                }
                currentTime = processes.get(highest).getArrivalTime();
            }

            // Execute the process
            Process p = processes.get(highest);
            result.addToGanttChart(p.getProcessId());
            currentTime += p.getBurstTime();
            result.addToGanttTime(currentTime);

            p.setCompletionTime(currentTime);
            p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
            p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
            
            completed[highest] = true;
        }
    }

    private void schedulePreemptive(List<Process> processes, SchedulingResult result) {
        int currentTime = 0;
        int completed = 0;
        int n = processes.size();
        boolean[] isCompleted = new boolean[n];
        result.addToGanttTime(0);
        int lastProcessId = -1;

        while (completed < n) {
            // Find process with highest priority (lowest number) among arrived processes
            int highest = -1;
            int minPriority = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && processes.get(i).getArrivalTime() <= currentTime) {
                    if (processes.get(i).getPriority() < minPriority) {
                        minPriority = processes.get(i).getPriority();
                        highest = i;
                    }
                }
            }

            // If no process has arrived, jump to next arrival
            if (highest == -1) {
                int minArrival = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!isCompleted[i] && processes.get(i).getArrivalTime() < minArrival) {
                        minArrival = processes.get(i).getArrivalTime();
                        highest = i;
                    }
                }
                currentTime = processes.get(highest).getArrivalTime();
                continue;
            }

            // Execute for 1 time unit
            Process p = processes.get(highest);
            
            if (lastProcessId != p.getProcessId()) {
                if (lastProcessId != -1) {
                    result.addToGanttTime(currentTime);
                }
                result.addToGanttChart(p.getProcessId());
                lastProcessId = p.getProcessId();
            }

            p.setRemainingTime(p.getRemainingTime() - 1);
            currentTime++;

            if (p.getRemainingTime() == 0) {
                p.setCompletionTime(currentTime);
                p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
                p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
                isCompleted[highest] = true;
                completed++;
                lastProcessId = -1;
            }
        }

        result.addToGanttTime(currentTime);
    }

    @Override
    public String getAlgorithmName() {
        return preemptive ? "Priority Scheduling (Preemptive)" : "Priority Scheduling (Non-Preemptive)";
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
