import java.util.Scanner;

public class BetweenValidator {

    private Scanner kbd;
    private int lowerBound;
    private int upperBound;

    public BetweenValidator(Scanner kbd, int lowerBound, int upperBound) {
	this.kbd = kbd;
	this.lowerBound = lowerBound;
	this.upperBound = upperBound;
    }


/**
 * This method keeps asking the user until they enter a valid number.
 * @param prompt This is the prompt the user wants to be asked.
 * @return valid number.
 */
    public String validInclusiveNumber(String prompt) {
	System.out.print(prompt);

	String response = kbd.nextLine();
	while (!NumberValidator.isInteger(response) || !inBetween(response)) {
	    System.out.println("That's Not a Number Between "  + lowerBound + " and " + upperBound);
	    System.out.print(prompt);
	    response = kbd.nextLine();
	}

	return response.trim();
    }

    private boolean inBetween(String response) {
	int responseAsInt = Integer.parseInt(response);
	return responseAsInt >= lowerBound && responseAsInt <= upperBound;
    }

}
