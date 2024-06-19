package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.example.Process.Process;
import org.example.Scheduling.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SchedulerTest {
    @Test
    public void SFJBasic() {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 10));
        processes.add(new Process("P2", 5));

        Scheduler s = new SJF(processes, 1);
        s.run();

        assertEquals(2, s.getExecutionTimelineMap().get("CPU_1").size());
        assertEquals(15, s.getTotalTime());
    }

    @Test
    public void SFJMultiple() {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 10));
        processes.add(new Process("P2", 5));
        processes.add(new Process("P3", 15));
        processes.add(new Process("P4", 3));

        Scheduler s = new SJF(processes, 2);
        s.run();

        assertEquals(2, s.getExecutionTimelineMap().get("CPU_1").size());
        assertEquals(2, s.getExecutionTimelineMap().get("CPU_2").size());
        assertEquals(20, s.getTotalTime());
    }

    @Test
    public void SFJReverseBasic() {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 10));
        processes.add(new Process("P2", 5));

        Scheduler s = new SJFReverse(processes, 1);
        s.run();

        assertEquals(2, s.getExecutionTimelineMap().get("CPU_1").size());
        assertEquals(15, s.getTotalTime());
    }

    @Test
    public void SFJReverseMultiple() {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 10));
        processes.add(new Process("P2", 5));
        processes.add(new Process("P3", 15));
        processes.add(new Process("P4", 3));

        Scheduler s = new SJFReverse(processes, 2);
        s.run();

        assertEquals(2, s.getExecutionTimelineMap().get("CPU_1").size());
        assertEquals(2, s.getExecutionTimelineMap().get("CPU_2").size());
        assertEquals(20, s.getTotalTime());
    }

}
