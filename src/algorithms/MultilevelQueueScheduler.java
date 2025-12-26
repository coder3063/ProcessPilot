package algorithms;

import models.Process;
import models.SchedulingResult;
import java.util.*;

/**
 * Multilevel Queue Scheduling Algorithm (Advanced)
 * Processes are divided into multiple priority queues:
 * Queue 0: System processes (Priority 0-1)
 * Queue 1: Interactive processes (Priority 2-4)
 * Queue 2: Batch processes (Priority 5+)
 */
public class MultilevelQueueScheduler implements Scheduler {
    private static final int NUM_QUEUES = 3;
    private int[] timeQuantums; // Time quantum for each queue

    public MultilevelQueueScheduler() {
        this.timeQuantums = new int[]{2, 4, 8}; // Increasing time quantum for lower priority queues
    }

    public MultilevelQueueScheduler(int[] timeQuantums) {
        this.timeQuantums = timeQuantums;
    }

    @Override
    public SchedulingResult schedule(List<Process> processes) {
        SchedulingResult result = new SchedulingResult("Multilevel Queue Scheduling");

        // Create copies to avoid modifying original list
        List<Process> processCopies = new ArrayList<>();
        for (Process p : processes) {
            processCopies.add(p.copy());
        }

        // Sort by arrival time
        processCopies.sort((a, b) -> Integer.compare(a.getArrivalTime(), b.getArrivalTime()));

        // Create queues based on priority
        @SuppressWarnings("unchecked")
        Queue<Process>[] queues = new Queue[NUM_QUEUES];
        for (int i = 0; i < NUM_QUEUES; i++) {
            queues[i] = new LinkedList<>();
        }

        int currentTime = 0;
        int index = 0;
        result.addToGanttTime(0);

        if (!processCopies.isEmpty()) {
            currentTime = processCopies.get(0).getArrivalTime();
        }

        // Assign processes to queues based on priority
        assignQueuesToProcesses(processCopies);

        boolean allProcessesComplete = false;
        while (!allProcessesComplete) {
            // Add newly arrived processes to their respective queues
            while (index < processCopies.size() && processCopies.get(index).getArrivalTime() <= currentTime) {
                int queueIndex = getQueueIndex(processCopies.get(index).getPriority());
                queues[queueIndex].add(processCopies.get(index));
                index++;
            }

            // Find the first non-empty queue (highest priority)
            Process nextProcess = null;
            int selectedQueue = -1;
            for (int i = 0; i < NUM_QUEUES; i++) {
                if (!queues[i].isEmpty()) {
                    nextProcess = queues[i].peek();
                    selectedQueue = i;
                    break;
                }
            }

            if (nextProcess == null) {
                // No process ready, jump to next arrival
                allProcessesComplete = true;
                for (int i = 0; i < processCopies.size(); i++) {
                    if (processCopies.get(i).getRemainingTime() > 0) {
                        allProcessesComplete = false;
                        break;
                    }
                }

                if (!allProcessesComplete && index < processCopies.size()) {
                    currentTime = processCopies.get(index).getArrivalTime();
                    int queueIndex = getQueueIndex(processCopies.get(index).getPriority());
                    queues[queueIndex].add(processCopies.get(index));
                    index++;
                }
                continue;
            }

            // Execute process
            nextProcess = queues[selectedQueue].poll();
            int timeToExecute = Math.min(nextProcess.getRemainingTime(), timeQuantums[selectedQueue]);

            result.addToGanttChart(nextProcess.getProcessId());
            currentTime += timeToExecute;
            nextProcess.setRemainingTime(nextProcess.getRemainingTime() - timeToExecute);

            // Add newly arrived processes
            while (index < processCopies.size() && processCopies.get(index).getArrivalTime() <= currentTime) {
                int queueIndex = getQueueIndex(processCopies.get(index).getPriority());
                queues[queueIndex].add(processCopies.get(index));
                index++;
            }

            if (nextProcess.getRemainingTime() > 0) {
                // Process not complete, add back to queue
                queues[selectedQueue].add(nextProcess);
            } else {
                // Process complete
                nextProcess.setCompletionTime(currentTime);
                nextProcess.setTurnaroundTime(nextProcess.getCompletionTime() - nextProcess.getArrivalTime());
                nextProcess.setWaitingTime(nextProcess.getTurnaroundTime() - nextProcess.getBurstTime());
            }

            result.addToGanttTime(currentTime);

            // Check if all processes are complete
            allProcessesComplete = true;
            for (Process p : processCopies) {
                if (p.getRemainingTime() > 0) {
                    allProcessesComplete = false;
                    break;
                }
            }
        }

        calculateMetrics(processCopies, result);
        result.setProcesses(processCopies);

        return result;
    }

    private void assignQueuesToProcesses(List<Process> processes) {
        for (Process p : processes) {
            p.setQueue(getQueueIndex(p.getPriority()));
        }
    }

    private int getQueueIndex(int priority) {
        if (priority >= 0 && priority <= 1) {
            return 0; // System processes - highest priority
        } else if (priority >= 2 && priority <= 4) {
            return 1; // Interactive processes - medium priority
        } else {
            return 2; // Batch processes - lowest priority
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Multilevel Queue Scheduling";
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

        if (result.getGanttTime().size() > 0) {
            int totalTime = result.getGanttTime().get(result.getGanttTime().size() - 1);
            result.setCpuUtilization((double) totalBurstTime / totalTime * 100);
        }
    }
}
