import java.util.Scanner;
import java.io.FileNotFoundException;

public class DecathlonApp {

    private static final int INITIAL_LENGTH = 3;
    private static final int NUM_SCORES_DISPLAYED = 8;
    private static final int MAX_NUM_EVENTS = 10;
    private static final int MIN_NUM_EVENTS = 1;

    private boolean hasUserQuitGame = true;

    private Scanner kbd = new Scanner(System.in);
    private DisplayWelcomeView displayWelcomeView;
    private DecathlonResults decathlonResults;
    private DisplayMainMenuView displayMainMenuView;
    private BetweenValidator eventValidator;
    private MenuValidator validMainMenu;
    private MenuValidator validRoll;
    private MenuValidator validEventMenu;
    private HighScoresPersistenator highScoresPersistenator;
    private HighScoresView highScoresView;
    private HighScores highScores;

    public DecathlonApp() throws FileNotFoundException {
	this.kbd = new Scanner(System.in);
	this.decathlonResults = new DecathlonResults();
	this.displayWelcomeView = new DisplayWelcomeView("GREEN", "PINK");
	this.displayMainMenuView = new DisplayMainMenuView(decathlonResults, "GREEN");
	this.eventValidator = new BetweenValidator(kbd, MIN_NUM_EVENTS, MAX_NUM_EVENTS);
	this.validMainMenu = new MenuValidator(kbd, "PHQ");
	this.validRoll = new MenuValidator(kbd, "YN");
	this.validEventMenu = new MenuValidator(kbd, "RCQ");
	this.highScoresPersistenator = new HighScoresPersistenator(NUM_SCORES_DISPLAYED, "HighScores.txt");
	this.highScoresView = new HighScoresView(highScores);


    }
/**
 * The Method the hub for all the events and it gets things started.
 * @exception throws File Not found exception to Main.java.
 */
    public void run() throws FileNotFoundException {

	clearConsole();

	initializeHighScore();
	displayWelcome();

	String response;

	do {
	    displayMainMenu();
    	    response = validMainMenu.validSelection("Your choice? ");
	    handle(response);

	} while (userWishesToContinue());

	clearConsole();

    }
/**
 * This method ends the program if the user wishes to quit the game.
 */
    private boolean userWishesToContinue() {
	return hasUserQuitGame;
    }

    private void displayWelcome() throws FileNotFoundException {
	displayWelcomeView.view();
    }

    private  void initializeHighScore() throws FileNotFoundException {
	highScores = highScoresPersistenator.load();
	highScoresView = new HighScoresView(highScores);
    }

    private void displayMainMenu() {
	displayMainMenuView.view();
    }

    private void handle(String response) throws FileNotFoundException {
	switch (response) {
	    case "P":
		handlePlayEvent();
		break;
	    case "H":
		handleHighScore();
		break;
	    case "Q":
		handleQuit();
		break;
	    default:
		handleUnknownResponce();
		break;
	}
    }

    private void handlePlayEvent() throws FileNotFoundException {
	String eventString = eventValidator.validInclusiveNumber("Choose an event? ");
	int eventNum = Integer.parseInt(eventString);
	System.out.println();
	switch (eventNum) {
	    case 4:
		clearConsole();
		new OneTenMeterHurdles(validRoll, validEventMenu, decathlonResults).play();
		clearConsole();
		break;
	    case 8:
		clearConsole();
		new HighJump(validRoll, validEventMenu, decathlonResults).play();
		clearConsole();
		break;
	    default:
		handleEventNotMade();
		break;
	}
    }

    private void handleHighScore() {
	clearConsole();
	highScoresView.view();
    }

    private void handleQuit() throws FileNotFoundException {
	int myScore = decathlonResults.totalScore();
	PlayerScore checkPlayer = new PlayerScore("", myScore);
	if (highScores.isEligible(checkPlayer)) {
	    System.out.println("Please Input Your 3 Initials to Display on the Scoreboard");
	    System.out.print("Your Initials: ");
	    String myInitials = kbd.nextLine().toUpperCase().trim();
	    while (myInitials.length() != INITIAL_LENGTH) {
		System.out.println("That's not 3 characters Long");
		System.out.print("Your Initials: ");
		myInitials = kbd.nextLine().toUpperCase().trim();
	    }
	    highScores.add(new PlayerScore(myInitials, myScore));
	    highScoresPersistenator.save(highScores);
	}
	hasUserQuitGame = false;
    }

    private void handleUnknownResponce() {
	System.out.println("Unknown Responce entered!");
    }

    private void handleEventNotMade() throws FileNotFoundException {
	System.out.println(ColorHelper.coloredString("PINK", "Event Work In Progress, Please choose another event!"));
	handlePlayEvent();
    }

    private void clearConsole() {
	System.out.println("\033[H\033[2J");
    }

}
