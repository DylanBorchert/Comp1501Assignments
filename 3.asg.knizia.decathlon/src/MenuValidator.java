import java.util.Scanner;
import java.util.ArrayList;

public class MenuValidator {

    private String validAction;

    private Scanner kbd;

    public MenuValidator(Scanner kbd, String validAction) {
	this.kbd = kbd;
	this.validAction = validAction.toUpperCase();
    }
/**
 * This Method checks to see if the a user input is a valid Menu option.
 * <p>
 * @param prompt is what the users wants to ask when aitinf for the user input
 * @return A valider menu option as a String
 */
    public String validSelection(String prompt) {
	System.out.print(prompt);

	String response = kbd.nextLine().toUpperCase().trim();
	while (response.length() != 1 || !validAction.contains(response)) {
	    System.out.println("That's not an option in " + commaJoiner(validAction) + ".");
	    System.out.print(prompt);
	    response = kbd.nextLine().toUpperCase().trim();
	}

	return response;
    }

    private String commaJoiner(String s) {
	ArrayList<String> flagList = new ArrayList<>();
	char[] flagChars = s.toCharArray();
	for (char c : flagChars) {
	    flagList.add("" + c);
	}
	return String.join(",", flagList);
    }

}
