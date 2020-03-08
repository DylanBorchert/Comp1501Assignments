import java.util.ArrayList;
import java.io.FileNotFoundException;

public class OneTenMeterHurdles {

    private static final int EVENT_NUMBER = 4;
    private static final int NUM_DICE_USED = 5;
    private static final int MAX_NUM_ROLLS = 5;

    private int totalNumRolls = 0;
    private boolean eventIsOver = true;

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
    public OneTenMeterHurdles(MenuValidator validRoll, MenuValidator validEventMenu,
			      DecathlonResults decathlonResults) {
	totalNumRolls = MAX_NUM_ROLLS;
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

	eventMenu.welcomeMainMenu("Welcome to 110 Meter Hurdles",
				  "OneTenMeterHurdles.txt");

	diceCollection.initializeDice(NUM_DICE_USED);
	rollEventMenu();
    }

    private void rollEventMenu() {

	String response;
	do {
	    eventMenu.rollMenu();
	    response = validEventMenu.validSelection("You Choose? ");
	    handle(response);
	} while (isTheEventOver());
	endEvent();
	listOfDice.clear();
    }

    private boolean isTheEventOver() {
	return eventIsOver && totalNumRolls > 0;
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

    private  void handleQuitEvent() {
	endEvent();
    }

    private void handleUnknownresponse() {
	System.out.println("Validation Error");
    }

    private void rollDice() {
	diceCollection.roll();
	System.out.println(displayDice.diceView(listOfDice));
	totalNumRolls--;
	rollAgainPrompt();
	canUserRollAgain();
    }

    private void rollAgainPrompt() {
	int currentScore = diceCollection.total();
	currentScore = Math.max(0, currentScore);
	eventMenu.rollAgainMenu(totalNumRolls, currentScore);
    }

    private void canUserRollAgain() {
	String response = validRoll.validSelection("Roll Again? ");
	if (response.equals("Y")) {
	    if (totalNumRolls <= 0) {
		cantRoll();
	    }
	} else if (response.equals("N")) {
	    endEvent();
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
	int diceScore = diceCollection.total();
	diceScore = Math.max(0, diceScore);
	decathlonResults.updateScore(EVENT_NUMBER, diceScore);
	eventIsOver = false;
    }

}
