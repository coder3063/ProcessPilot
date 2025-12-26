package algorithms;

import models.Process;
import models.SchedulingResult;
import java.util.List;

/**
 * Base interface for all scheduling algorithms
 */
public interface Scheduler {
    /**
     * Schedule processes according to the algorithm
     * @param processes List of processes to schedule
     * @return SchedulingResult containing execution details
     */
    SchedulingResult schedule(List<Process> processes);

    /**
     * Get the name of the scheduling algorithm
     * @return Algorithm name
     */
    String getAlgorithmName();
}
