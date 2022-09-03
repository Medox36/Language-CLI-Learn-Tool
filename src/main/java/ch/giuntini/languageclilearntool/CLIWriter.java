package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiPrintStream;

public class CLIWriter {
    private final AnsiPrintStream ansiPrintStream;

    public CLIWriter() {
        ansiPrintStream = AnsiConsole.out();
    }

    public void writeAnsi(String str) {
        ansiPrintStream.println(Ansi.ansi().a(str).toString());
    }

    public void changeCurrFgColor(Ansi.Color color) {
        ansiPrintStream.print(Ansi.ansi().fg(color).toString());
    }

    public void changeCurrBgColor(Ansi.Color color) {
        ansiPrintStream.print(Ansi.ansi().bg(color).toString());
    }

}
