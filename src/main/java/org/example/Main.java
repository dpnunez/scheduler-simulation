package org.example;

import java.io.IOException;
import java.util.ArrayList;

import org.example.Process.*;
import org.example.Process.Process;

import org.example.Scheduling.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IOException("Please provide a file path and number of cpus as arguments");
        }

        String filePath = args[0];
        int cpus = Integer.parseInt(args[1]);

        ArrayList<Process> processes = ProcessUtils.readProcesses(filePath);

        Scheduler schedulingReverse = new SJFReverse(processes, cpus);
        Scheduler sjf = new SJF(processes, cpus);


        System.out.println("Running SJF");
        sjf.run();
        ProcessUtils.generateResults(sjf.getExecutionTimeline());
    }
}