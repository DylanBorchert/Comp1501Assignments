import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class EventMenu {

    private static final String DIVIDER = "+-------------------------------------------+\n";

/**
 * This method displays the welcome message.
 * @param welcomeMessage is the message that the user wants to be displayed when
 * you enter an event.
 * <p>
 * @param instructionPath is the path to take to find the instructions foer the
 * event that is being played
 * @excpetion this  method throws a file not found excpetion if the instruction
 * path provided dosn't exsist
 */
    public void welcomeMainMenu(String welcomeMessage, String instructionPath) throws FileNotFoundException {
	File instructions = new File(instructionPath);
	Scanner fileScanner = new Scanner(instructions);
	String eventInstructions = "";
	while (fileScanner.hasNext()) {
	    eventInstructions += fileScanner.nextLine() + "\n";
	}
	System.out.printf("%s| %-41s |%n%s", DIVIDER, welcomeMessage, eventInstructions);

    }

/**
 * This method displays the options for a roll.
 */
    public void rollMenu() {
	String menuOptions = "|     Roll[R]    | Custom Roll[C] | Quit[Q] |\n";
	System.out.printf("%s%s%s", DIVIDER, menuOptions, DIVIDER);

    }

/**
 * This method displays the score and rolls left, aswell as the
 * options to roll again.
 * @param numRollsLeft is the number of rolls that the user has left
 * @param currentScore is the current score from the dice the was rolled
 */
    public void rollAgainMenu(int numRollsLeft, int currentScore) {
	String rollsLeftPrompt = String.format("|  You have %d Rolls Left! | Your Score: %02d  |%n",
					       numRollsLeft, currentScore);
	String menuOptions = "|        Yes[Y]                No[N]        |\n";
	System.out.printf("%s%s%s%s%s", DIVIDER, rollsLeftPrompt, DIVIDER,
			  menuOptions, DIVIDER);

    }

/**
 *Tthis method diasplays the menu for when the user runs out of rolls.
 */
    public void noMoreRolls() {
	String noMoreRollsPrompt = "|               No More Rolls               |\n";
	String menuOptions = "|        Yes[Y]                No[N]        |\n";
	System.out.printf("%s%s%s%s%s", DIVIDER, noMoreRollsPrompt, DIVIDER, menuOptions,  DIVIDER);
    }

/**
 * This method displays distance traveled, the number attempts left,
 * and the current score.
 * @param numOfAttempts is the number of rolls that the user has left.
 * @param distance is the current distance that the user is
 * is trying to pass in an event.
 * @param currentScore is the current score from the dice the was rolled.
 */
    public void currentHieght(int numOfAttempts, int distance, int currentScore) {
	String currentHieght = String.format("| Your Current Hieght is: %d                |%n", distance);
	String attemptsLeftPrompt = String.format("|  You have %d attempts Left! Your Score: %02d |%n",
						  numOfAttempts, currentScore);
	System.out.printf("%s%s%s%s", DIVIDER, currentHieght, DIVIDER, attemptsLeftPrompt);
    }
}
