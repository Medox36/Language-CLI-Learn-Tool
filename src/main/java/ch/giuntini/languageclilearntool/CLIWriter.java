package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiPrintStream;

public class CLIWriter {
    private AnsiPrintStream ansiPrintStream;

    public CLIWriter() {
        ansiPrintStream = AnsiConsole.out();
    }

    public void writeAnsi(String str) {

    }
}
