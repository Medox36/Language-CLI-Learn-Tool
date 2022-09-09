package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.AnsiConsole;

public class LanguageCLILearnTool {

    private final CLIWriter writer;
    private final CLIReader reader;

    public LanguageCLILearnTool() {
        if (System.console() != null) {
            AnsiConsole.systemInstall();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(this::reset));
        writer = new CLIWriter();
        reader = new CLIReader();
        printStartSequence();
    }

    public void start() {
        new Menu(reader, writer).mainMenu();
    }

    private void printStartSequence() {
        writer.clearCLI();
        printTitle();
        printWelcome();
        printGeneralInfo();
    }

    private void printTitle() {
        String original = "\n" +
                "██╗      █████╗ ███╗   ██╗ ██████╗ ██╗   ██╗ █████╗  ██████╗ ███████╗    ██████╗██╗     ██╗\n" +
                "██║     ██╔══██╗████╗  ██║██╔════╝ ██║   ██║██╔══██╗██╔════╝ ██╔════╝   ██╔════╝██║     ██║\n" +
                "██║     ███████║██╔██╗ ██║██║  ███╗██║   ██║███████║██║  ███╗█████╗     ██║     ██║     ██║\n" +
                "██║     ██╔══██║██║╚██╗██║██║   ██║██║   ██║██╔══██║██║   ██║██╔══╝     ██║     ██║     ██║\n" +
                "███████╗██║  ██║██║ ╚████║╚██████╔╝╚██████╔╝██║  ██║╚██████╔╝███████╗   ╚██████╗███████╗██║\n" +
                "╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ╚══════╝    ╚═════╝╚══════╝╚═╝\n" +
                "██╗     ███████╗ █████╗ ██████╗ ███╗   ██╗      ████████╗ ██████╗  ██████╗ ██╗     \n" +
                "██║     ██╔════╝██╔══██╗██╔══██╗████╗  ██║      ╚══██╔══╝██╔═══██╗██╔═══██╗██║     \n" +
                "██║     █████╗  ███████║██████╔╝██╔██╗ ██║ █████╗  ██║   ██║   ██║██║   ██║██║     \n" +
                "██║     ██╔══╝  ██╔══██║██╔══██╗██║╚██╗██║ ╚════╝  ██║   ██║   ██║██║   ██║██║     \n" +
                "███████╗███████╗██║  ██║██║  ██║██║ ╚████║         ██║   ╚██████╔╝╚██████╔╝███████╗\n" +
                "╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝         ╚═╝    ╚═════╝  ╚═════╝ ╚══════╝\n";
        String character = CLIWriter.c;
        String shadow = CLIWriter.y;
        StringBuilder sb = new StringBuilder();
        char oldTemp;

        for (int i = 1; i < original.length(); i++) {
            char temp = original.charAt(i);
            if (temp == '\n' || temp == ' ') {
                sb.append(temp);
                continue;
            }
            oldTemp = original.charAt(i -1);
            if (!hasChanged(oldTemp, temp)) {
                sb.append(temp);
                continue;
            }
            if (changedToFullBlock(oldTemp, temp)) {
                sb.append(character);
            } else {
                sb.append(shadow);
            }
            sb.append(temp);
        }

        writer.writeAnsi(sb.toString());
    }

    private boolean hasChanged(char last, char current) {
        return (last != current)
                        && ((last != '╔' && last != '═' && last != '╗' && last != '║' && last != '╝' && last != '╚')
                                || (current != '╔' && current != '═' && current != '╗' && current != '║' && current != '╝' && current != '╚'));
    }

    private boolean changedToFullBlock(char last, char current) {
        return (last == '╔' || last == '═' || last == '╗' || last == '║' || last == '╝' || last == '╚' || last == ' ' || last == '\n')
                && current == '█';
    }

    private void printWelcome() {
        String welcome = "\n" +
                "╦ ╦┌─┐┬  ┌─┐┌─┐┌┬┐┌─┐   ┬ ┬┌─┐┬  ┬┌─┐  ┌─┐┬ ┬┌┐┌  ┬  ┌─┐┌─┐┬─┐┌┐┌┬┌┐┌┌─┐┬\n" +
                "║║║├┤ │  │  │ ││││├┤    ├─┤├─┤└┐┌┘├┤   ├┤ │ ││││  │  ├┤ ├─┤├┬┘││││││││ ┬│\n" +
                "╚╩╝└─┘┴─┘└─┘└─┘┴ ┴└─┘   ┴ ┴┴ ┴ └┘ └─┘  └  └─┘┘└┘  ┴─┘└─┘┴ ┴┴└─┘└┘┴┘└┘└─┘o\n";

        writer.writeAnsi(CLIWriter.lM + welcome);
    }

    private void printGeneralInfo() {
        writer.writeAnsi(CLIWriter.lC + "Version: 1.0.0-alpha");
    }

    public void reset() {
        writer.resetAnsi();
    }
}
