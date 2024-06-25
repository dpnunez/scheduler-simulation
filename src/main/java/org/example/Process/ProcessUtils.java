package org.example.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProcessUtils {
    public static ArrayList<Process> readProcesses(String filePath) throws IOException {
        ArrayList<Process> processes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int burstTime = Integer.parseInt(parts[1].trim());
                    processes.add(new Process(name, burstTime));
                }
            }
        }
        return processes;
    }

    public static void generateResults(String results) {
        String filepath = "result_" + System.currentTimeMillis() + ".txt";

        ProcessUtils.generateResults(results, filepath);
    }

    public static void generateResults(String results, String filepath) {
        try (PrintWriter out = new PrintWriter(filepath)) {
            out.print(results);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }


}
