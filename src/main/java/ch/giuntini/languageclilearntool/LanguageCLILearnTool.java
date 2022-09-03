package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.Ansi;

public class LanguageCLILearnTool {

    private final CLIWriter writer;
    private CLIReader reader;

    public LanguageCLILearnTool() {
        writer = new CLIWriter();
        reader = new CLIReader();
        printStartSequence();
    }

    private void printStartSequence() {
        printTitle();
        printWelcome();
        printGeneralInfo();
    }

    private void printTitle() {
        String original = "\n" +
                "██╗      █████╗ ███╗   ██╗ ██████╗ ██╗   ██╗ █████╗  ██████╗ ███████╗     ██████╗██╗     ██╗    ██╗     ███████╗ █████╗ ██████╗ ███╗   ██╗      ████████╗ ██████╗  ██████╗ ██╗     \n" +
                "██║     ██╔══██╗████╗  ██║██╔════╝ ██║   ██║██╔══██╗██╔════╝ ██╔════╝    ██╔════╝██║     ██║    ██║     ██╔════╝██╔══██╗██╔══██╗████╗  ██║      ╚══██╔══╝██╔═══██╗██╔═══██╗██║     \n" +
                "██║     ███████║██╔██╗ ██║██║  ███╗██║   ██║███████║██║  ███╗█████╗      ██║     ██║     ██║    ██║     █████╗  ███████║██████╔╝██╔██╗ ██║ █████╗  ██║   ██║   ██║██║   ██║██║     \n" +
                "██║     ██╔══██║██║╚██╗██║██║   ██║██║   ██║██╔══██║██║   ██║██╔══╝      ██║     ██║     ██║    ██║     ██╔══╝  ██╔══██║██╔══██╗██║╚██╗██║ ╚════╝  ██║   ██║   ██║██║   ██║██║     \n" +
                "███████╗██║  ██║██║ ╚████║╚██████╔╝╚██████╔╝██║  ██║╚██████╔╝███████╗    ╚██████╗███████╗██║    ███████╗███████╗██║  ██║██║  ██║██║ ╚████║         ██║   ╚██████╔╝╚██████╔╝███████╗\n" +
                "╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ╚══════╝     ╚═════╝╚══════╝╚═╝    ╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝         ╚═╝    ╚═════╝  ╚═════╝ ╚══════╝\n" +
                "\n";
        String character = Ansi.ansi().fg(Ansi.Color.CYAN).toString();
        String shadow = Ansi.ansi().fg(Ansi.Color.YELLOW).toString();

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
        return (last == '╔' || last == '═' || last == '╗' || last == '║' || last == '╝' || last == '╚' || last == ' ' || last == '\n') && current == '█';
    }

    private void printWelcome() {

    }

    private void printGeneralInfo() {

    }
}
