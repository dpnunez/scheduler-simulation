package org.example;

import java.io.IOException;

import org.example.Process.*;

import org.example.Scheduling.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IOException("Please provide a file path and number of cpus as arguments");
        }
        int cpus = Integer.parseInt(args[1]);
        String filePath = args[0];
        String fileName = args[0].split("/")[args[0].split("/").length - 1].split("\\.")[0];
        String algorithm;

        if (args.length == 3) {
            algorithm = args[2];
        } else {
            algorithm = "SJF";
        }

        Scheduler s = switch (algorithm) {
            case "LJF" -> new LJF(ProcessUtils.readProcesses(filePath), cpus);
            default -> new SJF(ProcessUtils.readProcesses(filePath), cpus);
        };


        System.out.println("Running " + algorithm + " algorithm");
        s.run();
        ProcessUtils.generateResults(s.getExecutionTimeline(), "results_" + fileName + "_" + algorithm + "_" + cpus + ".txt");
    }
}