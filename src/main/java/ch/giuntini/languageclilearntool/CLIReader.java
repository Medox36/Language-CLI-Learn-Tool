package ch.giuntini.languageclilearntool;

import java.util.Scanner;

public class CLIReader implements AutoCloseable {
    private final Scanner scanner;

    public CLIReader() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
