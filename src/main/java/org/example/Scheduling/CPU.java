package org.example.Scheduling;
import org.example.Process.Process;

import java.util.ArrayList;

public class CPU {
    private final int id;
    private int currentTime;
    ArrayList<Process> processes;

    public CPU(int id) {
        this.id = id;
        this.processes = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder timeline = new StringBuilder();
        timeline.append("Processador_").append(this.id).append("\n");
        for (Process process : this.processes) {
            String line = process.getLabel() + ";" + process.getStartTime() + ";" + process.getEndTime();
            timeline.append(line).append("\n");
        }
        return timeline.toString();
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void runProcess(Process process) {
        this.processes.add(process);

        process.setStartTime(this.currentTime);
        this.currentTime += process.getDuration();
        process.setEndTime(this.currentTime);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }
}