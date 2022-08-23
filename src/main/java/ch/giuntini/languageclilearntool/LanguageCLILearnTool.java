package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.AnsiConsole;

public class LanguageCLILearnTool {

    private CLIWriter writer;
    private CLIReader reader;

    public LanguageCLILearnTool() {
        AnsiConsole.systemInstall();
        Runtime.getRuntime().addShutdownHook(new Thread(AnsiConsole::systemUninstall));
    }
}
