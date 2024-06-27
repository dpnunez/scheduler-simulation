package org.example.Scheduling;

import org.example.Process.Process;

import java.util.ArrayList;

public class LJF extends Scheduler {
    public LJF(ArrayList<Process> processes, int cpus) {
        super(processes, cpus);
    }

    @Override
    public void sortProcesses() {
        this.processes.sort((p1, p2) -> p2.getDuration() - p1.getDuration());
    }
}
