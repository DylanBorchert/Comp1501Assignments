/**
 * Provides a static method to allow you to create ANSI color code decorated Strings.
 */
public class ColorHelper {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_GRAY = "\u001B[38;5;102m";
    private static final String ANSI_LIGHT_BLUE = "\u001B[38;5;81m";
    private static final String ANSI_HOT_PINK = "\u001B[38;5;198m";


    /**
     * Returns a string that will print in color in a terminal that
     * can display ANSI colors.
     * <p>
     * For example, coloredString("green", "foo") returns a
     * green "foo".
     * <p>
     * Recognized colors (case insensitive) are:
     * <pre>
     * "BLACK"
     * "RED"
     * "GREEN"
     * "YELLOW"
     * "BLUE"
     * "PURPLE"
     * "CYAN"
     * "WHITE"
     * "GRAY"
     * "LIGHTBLUE"
     * "PINK"
     * </pre>
     * If an unknown color string is passed, WHITE is applied.
     *
     * @param colorName the name of the color to use for coloring
     * @param s the String being colored
     * @return the colored String
     */
    public static String coloredString(String colorName, String s) {
        return colorCode(colorName) + s + ANSI_RESET;
    }

    //
    // PRIVATE HELPER METHODS
    //

    /**
     * Color code.
     *
     * @param colorName the color name
     * @return the string
     */
    private static String colorCode(String colorName) {
        String upcasedColorName = colorName.toUpperCase();

        switch (upcasedColorName) {
            case "BLACK": return ANSI_BLACK;
            case "RED": return ANSI_RED;
            case "GREEN": return ANSI_GREEN;
            case "YELLOW": return ANSI_YELLOW;
            case "BLUE": return ANSI_BLUE;
            case "PURPLE": return ANSI_PURPLE;
            case "CYAN": return ANSI_CYAN;
            case "WHITE": return ANSI_WHITE;
            case "GRAY": return ANSI_GRAY;
            case "LIGHTBLUE": return ANSI_LIGHT_BLUE;
            case "PINK": return ANSI_HOT_PINK;
            default: return ANSI_WHITE;
        }

    }

    /**
     * Best practice for utility classes.
     */
    private ColorHelper() { }
}
