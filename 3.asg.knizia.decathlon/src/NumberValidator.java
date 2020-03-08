import java.util.regex.Pattern;

/**
 * A utility class to provide a way to tell if a given String
 * represents an integer.
 */
public class NumberValidator {

    // regex pattern to see if something is an integer
    public static final Pattern PATTERN = Pattern.compile("^-?\\d+$");

    /**
     * Returns true if the given input is a valid integer.
     *
     * @param input the String to validate
     * @return true, if valid integer
     */
    public static boolean isInteger(String input) {
        return input != null && PATTERN.matcher(input).matches();
    }

    /**
     * Best practice for utility class.
     */
    private NumberValidator() {
    }
}
