import java.util.Scanner;
import java.util.ArrayList;

public class MenuValidator {

    private Scanner kbd;
    private String validOptions;

    public MenuValidator(Scanner kbd, String validOptions) {
	this.kbd = kbd;
	this.validOptions = validOptions.toUpperCase();
    }

    /*Takes in a prompt to display to the user, like "Your choice (PDQ)? ".

      This method keeps asking the user for a valid letter until
      such a letter is entered. It then returns that valid letter.

      This method should be case insensitive - BUT it should always
      return the uppercased version of the valid letter entered.
    */
    public String validSelection(String prompt) {
	System.out.print(prompt);

	String response = kbd.nextLine().toUpperCase().trim();
	while (response.length() != 1 || !validOptions.contains(response)) {
	    System.out.println("That's not an option in " + commaJoiner(validOptions) + ".");
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
