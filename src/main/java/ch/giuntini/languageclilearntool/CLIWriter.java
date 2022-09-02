package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiPrintStream;

public class CLIWriter implements AutoCloseable {
    private final AnsiPrintStream ansiPrintStream;

    public CLIWriter() {
        ansiPrintStream = AnsiConsole.out();
    }

    public void writeAnsi(String str) {
        ansiPrintStream.println(str);
    }

    @Override
    public void close() {
        ansiPrintStream.close();
    }
}
