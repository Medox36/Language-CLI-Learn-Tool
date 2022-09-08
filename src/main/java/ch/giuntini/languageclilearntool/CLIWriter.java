package ch.giuntini.languageclilearntool;

import org.fusesource.jansi.Ansi;

public class CLIWriter {

    public static final String w = Ansi.ansi().fg(Ansi.Color.WHITE).toString();
    public static final String lG = Ansi.ansi().fgBright(Ansi.Color.GREEN).toString();
    public static final String g = Ansi.ansi().fg(Ansi.Color.GREEN).toString();
    public static final String lY = Ansi.ansi().fgBright(Ansi.Color.YELLOW).toString();
    public static final String y = Ansi.ansi().fg(Ansi.Color.YELLOW).toString();
    public static final String lR = Ansi.ansi().fgBright(Ansi.Color.RED).toString();
    public static final String r = Ansi.ansi().fg(Ansi.Color.RED).toString();
    public static final String lC = Ansi.ansi().fgBright(Ansi.Color.CYAN).toString();
    public static final String c = Ansi.ansi().fg(Ansi.Color.CYAN).toString();
    public static final String lM = Ansi.ansi().fgBright(Ansi.Color.MAGENTA).toString();
    public static final String m = Ansi.ansi().fg(Ansi.Color.MAGENTA).toString();
    public static final String lB = Ansi.ansi().fgBright(Ansi.Color.BLUE).toString();
    public static final String b = Ansi.ansi().fg(Ansi.Color.BLUE).toString();


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
