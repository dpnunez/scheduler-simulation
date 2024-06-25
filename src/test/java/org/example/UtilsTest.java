package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Process.Process;
import org.example.Process.ProcessUtils;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.ArrayList;


public class UtilsTest {
    @Test
    public void testReadProcesses() {
        try {
            String filePath = "src/main/resources/exemplo.txt";
            ArrayList<Process> p = ProcessUtils.readProcesses(filePath);
            assertEquals(9, p.size(), "Should have 9 processes");

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void testGenerateResults() {
        try {
            String result = """
                    Processador_1
                    P1;0;2
                    P2;2;4
                    """;

            String filepath = "result_by_test" + System.currentTimeMillis() + ".txt";
            ProcessUtils.generateResults(result, filepath);

            FileReader file = new FileReader(filepath);
            assertNotNull(file, "File should be created");

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }
}