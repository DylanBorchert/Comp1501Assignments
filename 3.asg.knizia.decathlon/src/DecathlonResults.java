import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class DecathlonResults {

    private static final int NUM_OF_EVENTS = 10;

    private ArrayList<Result> eventData = new ArrayList<>();
    private ArrayList<String> eventName = new ArrayList<>();

    private int totalScore = 0;


/**
 * This constructor initializes eventData and eventName ArrayLists.
 * <p>
 * Brings in all event names from "EventList.txt" and
 * colors WIP and NEW two diffrent colors
 * <p>
 * Creates a Result Object for each event
 * @return valid number.
 */
    public DecathlonResults() throws FileNotFoundException {
	initializeEventNames();
	initializeResult();
    }

    private void initializeResult() {
	for (int j = 1; j <= NUM_OF_EVENTS; j++) {
	    eventData.add(new Result(j));
	}
    }

    private void initializeEventNames() throws FileNotFoundException {
	String wipColored = ColorHelper.coloredString("PINK", "WIP");
	String newColored = ColorHelper.coloredString("GREEN", "NEW");
	File eventNames = new File("EventList.txt");
	Scanner fileScanner = new Scanner(eventNames);
	while (fileScanner.hasNextLine()) {
	    String event = fileScanner.nextLine();
	    event = event.replaceAll("WIP", wipColored);
	    event = event.replaceAll("NEW", newColored);
	    eventName.add(event);
	}
    }

/**
 * This Method updates the score of the a searched event and discards the old score.
 * <p>
 * @param searchedEventNum This is the event number that iss being serched for
 * to be updated with a new score
 * @param scoreToUpdate is the score that the event will be updated with
 */
    public void updateScore(int searchedEventNum, int scoreToUpdate) {
	for (Result result : eventData) {
	    if (result.eventNum() == searchedEventNum) {
		result.updateResult(scoreToUpdate);
	    }
	}
    }

/**
 * This Method Prints out the event score board, line by line.
 */
    public void displayEventScores() {
	totalScore = 0;
	String eventsList = "";
	for (int j = 0; j < NUM_OF_EVENTS; j++) {
	    String currentEvent = eventName.get(j);
	    int eventScore = eventData.get(j).currentScore();
	    totalScore += eventScore;
	    eventsList += String.format("|[%02d] %s |  %03d  |%n", j + 1,
					currentEvent, eventScore);

	}
	System.out.print(eventsList);
	eventScoreTotal();
    }

    private void eventScoreTotal() {
	String divider = "+---------------------------------------+-------+";
	String totalScoreFormat = String.format(
	    "%n|->->->->->->->Total-Score->->->->->->->|  %03d  |%n", totalScore);
	System.out.print(divider + totalScoreFormat + divider + "\n");
    }

    public int totalScore() {
	return totalScore;
    }

}
