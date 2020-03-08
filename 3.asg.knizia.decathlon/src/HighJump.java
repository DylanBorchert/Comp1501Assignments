import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class HighJump {

    private static final int EVENT_NUMBER = 8;
    private static final int NUM_DICE_USED = 5;
    private static final int MAX_NUM_ROLLS = 3;
    private static final int MAX_HIEGHT = 30;
    private static final int MIN_HIEGHT = 10;

    private int totalNumRolls;
    private boolean eventIsOver = true;
    private int currentHieght;
    private int reachedHieght;

    private Scanner kbd = new Scanner(System.in);
    private MenuValidator validRoll;
    private MenuValidator validEventMenu;
    private DiceCollection diceCollection;
    private EventMenu eventMenu;
    private DisplayDice displayDice;
    private DecathlonResults decathlonResults;
    private RollCustomDice rollCustomDice;
    private ArrayList<Dice> listOfDice = new ArrayList<>();

/**
 * This the contructor it, intializes all objects and parameters.
 * @param validRoll is an object that is called and initialized in the
 * DecatholonApp class.
 * <p>
 * This parameter validates if the user has inputed "Y" for Yes
 * and "N" for No, if any other input is entered then it will
 * prompt the user again for a diffrent input
 * @param validEventMenu is an object that is called and initialized in the
 * DecathalonApp
 * <p>
 * This parameter validates if the user has inputed "Q" for Quit, "R" for
 * Roll, and "C" for custom roll
 * @param decathlonResults is an object that holds the score
 * history for all events
 * <p>
 * This parameter is brought in to update the DecathlonResults.java class
 * with the high score that is reached in this event
 */
    public HighJump(MenuValidator validRoll, MenuValidator validEventMenu, DecathlonResults decathlonResults) {
	totalNumRolls = MAX_NUM_ROLLS;
	currentHieght = MIN_HIEGHT;
	this.validRoll = validRoll;
	this.validEventMenu = validEventMenu;
	this.decathlonResults = decathlonResults;
	this.diceCollection = new DiceCollection(listOfDice);
	this.eventMenu = new EventMenu();
	this.displayDice = new DisplayDice("PINK");
	this.rollCustomDice = new RollCustomDice();
    }

/**
 * This the Method is in charge of this event ann what allows
 * the event to be played.
 * <p>
 * @excpetion the FileNotFoundException is for bringing in the events
 * instructions, if this exception is thrown from EventMenu.java
 * it will throw it to DecathalonApp.java
 */
    public void play() throws FileNotFoundException {

	eventMenu.welcomeMainMenu("Welcome to High Jump", "HighJump.txt");

	diceCollection.initializeDice(NUM_DICE_USED);
	currentHieght = startingHieghtNumber();
	rollEventMenu();
    }

    private int startingHieghtNumber() {
	System.out.println("What Hieght would you like to start at?");
	System.out.print("You Choose: ");
	String hieght = kbd.nextLine().trim();

	while (!isValidHieght(hieght)) {
	    System.out.println("That's not a even number between " + MIN_HIEGHT +
			       " and " + MAX_HIEGHT + "!");
	    System.out.print("You Choose: ");
	    hieght = kbd.nextLine().trim();
	}

	return Integer.parseInt(hieght);
    }

    private boolean isValidHieght(String hieght) {
	if (NumberValidator.isInteger(hieght)) {
	    int hieghtNum = Integer.parseInt(hieght);
	    if (hieghtNum <= MAX_HIEGHT && hieghtNum >= MIN_HIEGHT && hieghtNum % 2 == 0) {
		return true;
	    }
	}
	return false;
    }

    public void rollEventMenu() {

	String response;
	do {
	    eventMenu.rollMenu();
	    response = validEventMenu.validSelection("You Choose? ");
	    handle(response);
	    eventMenu.currentHieght(totalNumRolls, currentHieght, totalScore());
	    reachedHieght();
	} while (isTheEventOver());
	endEvent();
	listOfDice.clear();
    }

    private boolean isTheEventOver() {
	return eventIsOver;
    }

    private void reachedHieght() {
	if (totalScore() >= currentHieght) {
	    totalNumRolls = MAX_NUM_ROLLS;
	    reachedHieght = currentHieght;
	    currentHieght += 2;
	}
    }

    private int totalScore() {
	return Math.max(0, diceCollection.total());
    }

    private void handle(String response) {
	switch (response) {
	    case "R":
		handleRandomRoll();
		break;
	    case "C":
		handleCustomRoll();
		break;
	    case "Q":
		handleQuitEvent();
		break;
	    default:
		handleUnknownresponse();
		break;
	}
    }

    private void handleRandomRoll() {
	rollDice();
    }

    private void handleCustomRoll() {
	rollCustomDice.setUpDice(listOfDice);
	System.out.println();
	rollDice();
    }

    private void handleQuitEvent() {
	endEvent();
    }

    private void handleUnknownresponse() {
	System.out.println("Validation Error");
    }

    private void rollDice() {
	diceCollection.roll();
	System.out.println(displayDice.diceView(listOfDice));
	totalNumRolls--;
	canUserRollAgain();
    }

    private void canUserRollAgain() {
	int currentScore = diceCollection.total();
	if (totalNumRolls <= 0 && currentScore < currentHieght) {
	    cantRoll();
	}
    }

    private void cantRoll() {
	eventMenu.noMoreRolls();
	String response = validRoll.validSelection("End Event? ");
	if (response.equals("Y")) {
	    endEvent();
	} else {
	    cantRoll();
	}
    }

    private void endEvent() {
	decathlonResults.updateScore(EVENT_NUMBER, reachedHieght);
	eventIsOver = false;
    }
}
