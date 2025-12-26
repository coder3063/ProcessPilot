package algorithms;

import models.Process;
import models.SchedulingResult;
import java.util.*;

/**
 * Round Robin (RR) Scheduling Algorithm
 * Each process gets a fixed time quantum to execute
 */
public class RoundRobinScheduler implements Scheduler {
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public RoundRobinScheduler() {
        this.timeQuantum = 4; // Default time quantum
    }

    @Override
    public SchedulingResult schedule(List<Process> processes) {
        SchedulingResult result = new SchedulingResult("Round Robin (TQ=" + timeQuantum + ")");

        // Create copies to avoid modifying original list
        List<Process> processCopies = new ArrayList<>();
        for (Process p : processes) {
            processCopies.add(p.copy());
        }

        // Sort by arrival time
        processCopies.sort((a, b) -> Integer.compare(a.getArrivalTime(), b.getArrivalTime()));

        Queue<Process> readyQueue = new LinkedList<>();
        int currentTime = 0;
        int index = 0;
        result.addToGanttTime(0);

        // Add first arriving process
        if (!processCopies.isEmpty()) {
            currentTime = processCopies.get(0).getArrivalTime();
        }

        while (index < processCopies.size() || !readyQueue.isEmpty()) {
            // Add all processes that have arrived by current time
            while (index < processCopies.size() && processCopies.get(index).getArrivalTime() <= currentTime) {
                readyQueue.add(processCopies.get(index));
                index++;
            }

            if (readyQueue.isEmpty()) {
                // Jump to next arrival time
                if (index < processCopies.size()) {
                    currentTime = processCopies.get(index).getArrivalTime();
                    readyQueue.add(processCopies.get(index));
                    index++;
                }
            } else {
                Process p = readyQueue.poll();
                
                // Determine how much time this process will use
                int timeToExecute = Math.min(p.getRemainingTime(), timeQuantum);
                
                result.addToGanttChart(p.getProcessId());
                currentTime += timeToExecute;
                p.setRemainingTime(p.getRemainingTime() - timeToExecute);
                
                // Add any newly arrived processes
                while (index < processCopies.size() && processCopies.get(index).getArrivalTime() <= currentTime) {
                    readyQueue.add(processCopies.get(index));
                    index++;
                }

                // If process is not complete, add it back to queue
                if (p.getRemainingTime() > 0) {
                    readyQueue.add(p);
                } else {
                    p.setCompletionTime(currentTime);
                    p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
                    p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
                }

                result.addToGanttTime(currentTime);
            }
        }

        // Remove duplicate last time entry
        if (result.getGanttTime().size() > 0 && 
            result.getGanttChart().size() > 0 &&
            result.getGanttTime().size() > result.getGanttChart().size()) {
            // This is normal for our structure
        }

        calculateMetrics(processCopies, result);
        result.setProcesses(processCopies);

        return result;
    }

    @Override
    public String getAlgorithmName() {
        return "Round Robin (TQ=" + timeQuantum + ")";
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
