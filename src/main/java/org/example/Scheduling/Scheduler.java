package org.example.Scheduling;

import org.example.Process.Process;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class Scheduler {
    private final ArrayList<CPU> cpus;
    protected final ArrayList<Process> processes;

    public Scheduler(ArrayList<Process> processes, int cpus) {
        this.processes = processes;
        this.cpus = new ArrayList<CPU>();

        for (int i = 0; i < cpus; i++) {
            CPU cpu = new CPU(i + 1);
            this.cpus.add(cpu);
       }
    }

    public String getExecutionTimeline() {
        StringBuilder timeline = new StringBuilder();
        for (CPU cpu : this.cpus) {
            if (this.cpus.indexOf(cpu) == this.cpus.size() - 1) {
                timeline.append(cpu.toString());
                continue;
            }
            timeline.append(cpu.toString()).append("\n");
        }
        return timeline.toString();
    }

    public HashMap<String, ArrayList<Process>> getExecutionTimelineMap() {
        HashMap<String, ArrayList<Process>> timeline = new HashMap<>();
        for (CPU cpu : this.cpus) {
            timeline.put("CPU_" + cpu.getId(), cpu.getProcesses());
        }
        return timeline;
    }

    public int getTotalTime() {
        int maxTotal = 0;
        for (CPU cpu : this.cpus) {
            maxTotal = Math.max(maxTotal, cpu.getCurrentTime());
        }

        return maxTotal;
    }

    public CPU getFreeCPU() {
        CPU lowestTimeCPU = this.cpus.getFirst();
        for (CPU cpu : this.cpus) {
            if (cpu.getCurrentTime() < lowestTimeCPU.getCurrentTime()) {
                lowestTimeCPU = cpu;
            }
        }
        return lowestTimeCPU;
    }

    public void run() {
        this.sortProcesses();

        ArrayList<Process> cloneProcesses = new ArrayList<>(this.processes);

        while(!cloneProcesses.isEmpty()) {
            Process p = cloneProcesses.removeFirst();
            CPU freeCPU = this.getFreeCPU();
            freeCPU.runProcess(p);
        }
    }

    public abstract void sortProcesses();
}


