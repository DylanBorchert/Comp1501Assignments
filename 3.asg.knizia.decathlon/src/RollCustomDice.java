import java.util.Scanner;
import java.util.ArrayList;

public class RollCustomDice {

    private static final int  MAX_DIE_NUM = 6;
    private static final int  MIN_DIE_NUM = 1;

    private boolean userIsDone = true;
    private int lowerBound;
    private int upperBound;

    private Scanner kbd = new Scanner(System.in);
    private CustomRollMenu customRoll;
    private ArrayList<Dice> listOfDice;

/**
 * This method is the main menu functnaility for custom dice
 * and allows the user to selcect sepecific dicae and add it to the
 * die's future roll requests.
 * @param listOfDice is a arraylist fo dice that the usre wants
 * to customize.
 */
    public void setUpDice(ArrayList<Dice> listOfDice) {
	userIsDone = true;
	this.listOfDice = listOfDice;
	this.customRoll = new CustomRollMenu(listOfDice);
	this.lowerBound = 1;
	this.upperBound = listOfDice.size();
	customRoll.welcomeCustomDiceRoll();
	String response;
	do {
	    customRoll.customDiceMenu();
	    System.out.print("You Choose: ");
	    response = kbd.nextLine().toUpperCase().trim();
	    handle(response);
	} while (userIsDone);
	customRoll.endCustomDice();

    }

    private void handle(String response) {

	if (response.equals("L")) {
	    userIsDone = false;
	} else if (isValidDiceNumber(response)) {
	    rollRequestMenu(response);
	} else {
	    System.out.println("Thats not a valid option!");
	    System.out.println("Choose an number between " + lowerBound + " and " + upperBound + " or \"Q\" to Quit");
	    System.out.print("You Choose: ");
	    String newResponse = kbd.nextLine().toUpperCase().trim();
	    handle(newResponse);
	}

    }

    private boolean isValidDiceNumber(String response) {
	boolean validNumber = false;
	if (response.length() == 1 && NumberValidator.isInteger(response)) {
	    int numberResponse = Integer.parseInt(response);
	    validNumber = numberResponse <= upperBound && numberResponse >= lowerBound;
	}
	return validNumber;
    }

    private void rollRequestMenu(String response) {
	int numberResponse = Integer.parseInt(response);
	System.out.println();
	customRoll.rollRequest();
	System.out.print("Your Numbers: ");
	String rollRequest = kbd.nextLine().toUpperCase().trim();
	while (!isValidRollRequest(rollRequest)) {
	    System.out.println("Not a valid Roll Request");
	    System.out.print("Your Numbers: ");
	    rollRequest = kbd.nextLine().toUpperCase().trim();
	}
	Dice currentdice = listOfDice.get(numberResponse - 1);
	currentdice.addRollRequest(rollRequest);

    }

    private boolean isValidRollRequest(String rollRequest) {
	boolean validNumber = false;
	Scanner requestedRoll = new Scanner(rollRequest);
	while (requestedRoll.hasNext()) {
	    String number = requestedRoll.next().trim();
	    if (isValidRollNumber(number)) {
		validNumber = true;
	    } else {
		validNumber = false;
		return false;
	    }
	}
	return validNumber;
    }

    private boolean isValidRollNumber(String number) {
	boolean validNumber = false;
	if (NumberValidator.isInteger(number)) {
	    int numberResponse = Integer.parseInt(number);
	    validNumber = numberResponse <= MAX_DIE_NUM && numberResponse >= MIN_DIE_NUM;
	}
	return validNumber;
    }

}
