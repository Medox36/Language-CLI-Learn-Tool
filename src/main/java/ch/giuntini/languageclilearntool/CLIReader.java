package ch.giuntini.languageclilearntool;

import java.util.Arrays;
import java.util.Scanner;

public class CLIReader implements AutoCloseable {
    private final Scanner scanner;

    public CLIReader() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public int readIntOption(String text, int... options) {
        int chosenOption;
        boolean inArray;
        do {
            chosenOption = readInt(text);
            int finalChosenOption = chosenOption;
            inArray = Arrays.stream(options).anyMatch(value -> value == finalChosenOption);
        } while (!inArray);

        return chosenOption;
    }

    public int readInt(String text) {
        System.out.println(text);
        System.out.print("  >");
        while (!scanner.hasNextInt()) {
            System.out.println(text);
            System.out.print("  >");
            scanner.next();
        }
        int chosenOption = scanner.nextInt();
        scanner.nextLine();

        return chosenOption;
    }

    @Override
    public void close() {
        scanner.close();
    }
}
