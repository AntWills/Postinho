package entities.terminal;

public class ColorOut {
    public static final String reset = "\033[0m";
    public static final String black = "\033[0;30m";
    public static final String red = "\033[0;31m";
    public static final String green = "\033[0;32m";
    public static final String yellow = "\033[0;33m";
    public static final String blue = "\033[0;34m";
    public static final String purple = "\033[0;35m";
    public static final String cyan = "\033[0;36m";
    public static final String white = "\033[0;37m";

    public static final String bgBlack = "\033[40m";
    public static final String bgRed = "\033[41m";
    public static final String bgGreen = "\033[42m";
    public static final String bgYellow = "\033[43m";
    public static final String bgBlue = "\033[44m";
    public static final String bgPurple = "\033[45m";
    public static final String bgCyan = "\033[46m";
    public static final String bgWhite = "\033[47m";

    public static final String bold = "\033[1m";

    public static String getText(String text, String formt) {
        return formt + text + reset;
    }

}
