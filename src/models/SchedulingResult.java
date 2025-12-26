package models;

import java.util.*;

/**
 * Stores the results of scheduling algorithm execution
 */
public class SchedulingResult {
    private String algorithmName;
    private List<Process> processes;
    private List<Integer> ganttChart;
    private List<Integer> ganttTime;
    private double averageWaitingTime;
    private double averageTurnaroundTime;
    private double cpuUtilization;
    private long executionTime;

    public SchedulingResult(String algorithmName) {
        this.algorithmName = algorithmName;
        this.processes = new ArrayList<>();
        this.ganttChart = new ArrayList<>();
        this.ganttTime = new ArrayList<>();
        this.executionTime = 0;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public List<Integer> getGanttChart() {
        return ganttChart;
    }

    public void addToGanttChart(int processId) {
        this.ganttChart.add(processId);
    }

    public List<Integer> getGanttTime() {
        return ganttTime;
    }

    public void addToGanttTime(int time) {
        this.ganttTime.add(time);
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(double averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }

    public void setAverageTurnaroundTime(double averageTurnaroundTime) {
        this.averageTurnaroundTime = averageTurnaroundTime;
    }

    public double getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(double cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return String.format(
                "Algorithm: %s\nAverage Waiting Time: %.2f\nAverage Turnaround Time: %.2f\nCPU Utilization: %.2f%%\n",
                algorithmName, averageWaitingTime, averageTurnaroundTime, cpuUtilization
        );
    }
}
