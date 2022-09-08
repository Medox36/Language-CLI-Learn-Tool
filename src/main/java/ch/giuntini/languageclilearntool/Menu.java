package ch.giuntini.languageclilearntool;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Menu {

    private final CLIReader reader;
    private final CLIWriter writer;
    private final LearnUtil learnUtil;
    private Difficulty difficulty;

    public Menu(CLIReader reader, CLIWriter writer) {
        this.reader = reader;
        this.writer = writer;
        learnUtil = new LearnUtil();
        difficulty = Difficulty.NORMAL;
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
                    info();
                    break;
                }
                case 4: {
                    credits();
                    break;
                }
            }

        } while (opt != 0);
    }

    private void close() {
        writer.writeAnsi(CLIWriter.lB + "\nProgram is now closing.\nGoodbye!");
    }

    private void learnMenu() {
        final int[] options = new int[]{0, 1, 2, 3};
        int opt;
        do {
            printLearnMenuOptions();
            opt = reader.readIntOption(options);
            switch (opt) {
                case 0: {
                    break;
                }
                case 1: {
                    new LearnEnglish(learnUtil).startLearning();
                    break;
                }
                case 2: {
                    new LearnGerman(learnUtil).startLearning();
                    break;
                }
                case 3: {
                    changeDifficulty();
                    break;
                }
                default: {}
            }

        } while (opt != 0);
    }

    private void changeDifficulty() {
        String g = CLIWriter.lG;
        String y = CLIWriter.y;
        String r = CLIWriter.lR;
        String m = CLIWriter.lM;
        String w = CLIWriter.w;

        writer.writeAnsi(w + "Choose difficulty:");
        writer.writeAnsi(g + "Easy" + w + ": 0 | " + y + "Normal" + w + ": 1 | " + r + "Hard" + w + ": 2 | " + m + "Extreme" + w + ": 3");

        difficulty = Difficulty.values()[reader.readIntOption(0, 1, 2, 3)];
    }

    private void addMenu() {
        final int[] options = new int[]{0, 1, 2};
        int opt;
        do {
            printAddMeuOptions();
            opt = reader.readIntOption(options);
            switch (opt) {
                case 0: {
                    break;
                }
                case 1: {
                    learnUtil.reReadFile();
                    break;
                }
                case 2: {
                    try {
                        Desktop.getDesktop().open(new File("translations.txt"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                default: {}
            }

        } while (opt != 0);
    }

    private void info() {

    }

    private void credits() {

    }

    private void printMainMenuOptions() {
        String lG = CLIWriter.lG;
        String w = CLIWriter.w;

        writer.writeAnsi(CLIWriter.y + "\nMain Menu");
        writer.writeAnsi(w + "Please choose from the following options by entering a number:");
        writer.writeAnsi(w + "Exit: " + lG + "0" + w + " | Learn-mode: " + lG + "1" + w + " | Add Translations: "
                + lG + "2" + w + " | Show general Information: " + lG + "3" + w + " | Show credits: " + lG + "4");
    }

    private void printLearnMenuOptions() {
        String lG = CLIWriter.lG;
        String w = CLIWriter.w;
        String y;

        switch (difficulty) {
            case EASY: {
                y = CLIWriter.lG;
                break;
            }
            case NORMAL: {
                y = CLIWriter.y;
                break;
            }
            case HARD: {
                y = CLIWriter.lR;
                break;
            }
            case EXTREME: {
                y = CLIWriter.lM;
                break;
            }
            default: y = CLIWriter.w;
        }

        writer.writeAnsi( CLIWriter.m + "\nLearn Menu");
        writer.writeAnsi(w + "Please choose from the following options by entering a number:");
        writer.writeAnsi(w + "Back to main menu: " + lG + "0" + w + " | Start learning english: " + lG + "1" + w
                + " | Start learning German: " + lG + "2" + w + " | Change Difficulty: " + lG + "3" + w
                + " current: " + y + difficulty.name() + w);
    }

    private void printAddMeuOptions() {
        String lG = CLIWriter.lG;
        String w = CLIWriter.w;

        writer.writeAnsi(CLIWriter.lM + "\nAdd Translations Menu");
        writer.writeAnsi(w + "Please choose from the following options by entering a number:");
        writer.writeAnsi(w + "Back to learn menu: " + lG + "0" + w + " | Reload the file: " + lG + "1" + w
                + " | Open the file: " + lG + "2" + w);
    }
}
