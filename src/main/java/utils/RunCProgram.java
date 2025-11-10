package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utility class to compile and run C programs from Java.
 * 
 * @author Bhavya Jain
 */

public class RunCProgram {
    public void run(String fileName) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            boolean isWindows = os.contains("win");

            String projectRoot = System.getProperty("user.dir");
            String separator = File.separator;
            String cFilePath = projectRoot + separator + "src" + separator + "main" + separator +
                    "java" + separator + "week3" + separator + fileName;

            String exeName = isWindows ? "program.exe" : "program";
            String exePath = projectRoot + separator + "target" + separator + "out" + separator + exeName;

            File cFile = new File(cFilePath);
            if (!cFile.exists()) {
                System.out.println("Error: C file not found at " + cFilePath);
                return;
            }

            System.out.println("Compiling C program...");

            ProcessBuilder compileBuilder = new ProcessBuilder("gcc", cFilePath, "-o", exePath);
            compileBuilder.redirectErrorStream(true);
            Process compile = compileBuilder.start();

            BufferedReader compileReader = new BufferedReader(new InputStreamReader(compile.getInputStream()));
            String line;
            while ((line = compileReader.readLine()) != null) {
                System.out.println(line);
            }

            int compileExitCode = compile.waitFor();
            if (compileExitCode != 0) {
                System.out.println("Compilation failed with exit code: " + compileExitCode);
                return;
            }

            System.out.println("Compilation successful!\n");

            ProcessBuilder runBuilder = new ProcessBuilder(exePath);
            runBuilder.inheritIO();
            Process run = runBuilder.start();
            run.waitFor();

            new File(exePath).delete();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Make sure GCC is installed and added to your PATH.");
        } catch (InterruptedException e) {
            System.out.println("Process was interrupted: " + e.getMessage());
        }
    }
}
