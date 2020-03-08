import java.util.Scanner;

public class BetweenValidator {

    private Scanner kbd;
    private int lowerBound;
    private int upperBound;

    /*It takes in the Scanner it should be using to get input from the user,
      an integer representing a lower bound, and an integer representing an upper bound.
    */
    public BetweenValidator(Scanner kbd, int lowerBound, int upperBound) {
	this.kbd = kbd;
	this.lowerBound = lowerBound;
	this.upperBound = upperBound;
    }


    /*Takes in a prompt to display to the user, like
      "Please enter a number between 1 and 5:".
      This method keeps asking the user for a number
      between the lower and upper bound until such a number is entered.
      It then returns that valid number.
    */
    public int validInclusiveNumber(String prompt) {
	System.out.print(prompt);

	String response = kbd.nextLine();
	while (!NumberValidator.isInteger(response) || !inBetween(response)) {
	    System.out.println("That's not a number between " + lowerBound + " and " + upperBound + ".");
	    System.out.print(prompt);
	    response = kbd.nextLine();
	}

	return Integer.parseInt(response);
    }

    /*Takes in the number from the user,
      This method returns true if the number is in bounds
    */
    private boolean inBetween(String response) {
	int responseAsInt = Integer.parseInt(response);
	return responseAsInt >= lowerBound && responseAsInt <= upperBound;
    }

}
