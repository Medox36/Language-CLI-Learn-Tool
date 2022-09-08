package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.Ansi;

public class Menu {

    private final CLIReader reader;
    private final CLIWriter writer;

    public Menu(CLIReader reader, CLIWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void mainMenu() {
        final int[] options = new int[]{0,1,2,3,4};
        int opt;
        do {
            printMainMenuOptions();
            opt = reader.readIntOption(options);
            switch (opt) {
                case 0: {
                    close();
                    break;
                }
                case 1: {
                    learnMenu();
                    break;
                }
                case 2: {
                    addMenu();
                    break;
                }
                case 3: {
                    infoMenu();
                    break;
                }
                case 4: {
                    credits();
                    break;
                }
            }

        } while (opt != 0);
    }

    public void close() {
        writer.writeAnsi(Ansi.ansi().fgBrightBlue().toString());
        writer.writeAnsi("\nProgram is now closing.\nGoodbye!");
    }

    public void learnMenu() {
        final int[] options = new int[]{0, 1, 2};
        int opt;

        writer.writeAnsi(Ansi.ansi().fgMagenta().a("\nYou're now in the learning mode").fgDefault().toString());

        do {
            printLearnMenuOptions();
            opt = reader.readIntOption(options);
            switch (opt) {
                case 0: {

                    break;
                }
                case 1: {

                    break;
                }
                case 2: {

                    break;
                }
                default: {}
            }

        } while (opt != 0);
    }

    public void addMenu() {

    }

    public void infoMenu() {

    }

    public void credits() {

    }

    public void printMainMenuOptions() {
        String lG = Ansi.ansi().fgBright(Ansi.Color.GREEN).toString();
        String w = Ansi.ansi().fg(Ansi.Color.WHITE).toString();

        writer.writeAnsi(Ansi.ansi().fgYellow().a("\nMain Menu").toString());
        writer.writeAnsi(w + "Please choose from the following options by entering a number:");
        writer.writeAnsi(w + "Exit: " + lG + "0" + w + " | Learn-mode: " + lG + "1" + w + " | Add Translations: "
                + lG + "2" + w + " | Show general Information: " + lG + "3" + w + " | Show credits: " + lG + "4");
    }

    public void printLearnMenuOptions() {
        String lG = Ansi.ansi().fgBright(Ansi.Color.GREEN).toString();
        String w = Ansi.ansi().fg(Ansi.Color.WHITE).toString();

        writer.writeAnsi(w + "Please choose from the following options by entering a number:");
        writer.writeAnsi(w + "Exit: " + lG + "0" + w + " | Start learning english: " + lG + "1" + w
                + " | Start learning German: " + lG + "2" + w);
    }
}
