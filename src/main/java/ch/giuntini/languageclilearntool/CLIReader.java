package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.Ansi;

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
            if (!inArray) {
                System.out.println(Ansi.ansi().fgRed().a("Choose an option form the list above!").fgDefault().toString());
            }
        } while (!inArray);

        return chosenOption;
    }

    public int readIntOption(int... options) {
        return readIntOption("Not a valid Option!", options);
    }

    public int readInt(String text) {
        System.out.print(Ansi.ansi().fgDefault().a("  >").toString());
        while (!scanner.hasNextInt()) {
            System.out.println(Ansi.ansi().fgRed().a(text).fgDefault().toString());
            System.out.print(Ansi.ansi().fgDefault().a("  >").toString());
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
