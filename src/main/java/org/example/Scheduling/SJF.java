package org.example.Scheduling;

import org.example.Process.Process;

import java.util.ArrayList;
import java.util.Comparator;

public class SJF extends Scheduler {
    public SJF(ArrayList<Process> processes, int cpus) {
        super(processes, cpus);
    }

    @Override
    public void sortProcesses() {
        this.processes.sort(Comparator.comparingInt(Process::getDuration));
    }
}



