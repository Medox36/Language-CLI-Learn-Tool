package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.Ansi;

public class CLIWriter {

    public CLIWriter() {
    }

    public void writeAnsi(String str) {
        System.out.println(Ansi.ansi().a(str).toString());
    }

    public void changeCurrFgColor(Ansi.Color color) {
        System.out.print(Ansi.ansi().fg(color).toString());
    }

    public void changeCurrBgColor(Ansi.Color color) {
        System.out.print(Ansi.ansi().bg(color).toString());
    }

    public void clearCLI() {
        System.out.println(Ansi.ansi().eraseScreen(Ansi.Erase.BACKWARD).cursor(0, 0));
    }

    public void resetAnsi() {
        System.out.println(Ansi.ansi().reset().toString());
    }

}
