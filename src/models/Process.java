package models;

/**
 * Represents a process with scheduling attributes
 */
public class Process implements Comparable<Process> {
    private int processId;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int completionTime;
    private int waitingTime;
    private int turnaroundTime;
    private int remainingTime;
    private int queue; // For multilevel queue scheduling

    public Process(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = 0;
        this.remainingTime = burstTime;
        this.queue = 0;
    }

    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        this(processId, arrivalTime, burstTime);
        this.priority = priority;
    }

    // Getters and Setters
    public int getProcessId() {
        return processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    @Override
    public int compareTo(Process other) {
        return Integer.compare(this.arrivalTime, other.arrivalTime);
    }

    @Override
    public String toString() {
        return "P" + processId;
    }

    public String getDetailedInfo() {
        return String.format("P%d | Arrival: %d | Burst: %d | Priority: %d | WT: %d | TAT: %d",
                processId, arrivalTime, burstTime, priority, waitingTime, turnaroundTime);
    }

    // Create a copy of the process for independent scheduling
    public Process copy() {
        Process copy = new Process(this.processId, this.arrivalTime, this.burstTime, this.priority);
        copy.queue = this.queue;
        return copy;
    }
}
