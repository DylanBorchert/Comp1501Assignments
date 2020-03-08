import java.util.ArrayList;

public class CustomRollMenu {

    private static final String DIVIDER = "+-----------------------------------------------+\n";

    private int lowerBound;
    private int upperBound;

    private ArrayList<Dice> listOfDice;


/**
 * This Contrusctor initialzes the arraylist of dice to be used
 * thoughout this class and the upper and lower bounds for the dice.
 */
    public CustomRollMenu(ArrayList<Dice> listOfDice) {
	this.listOfDice = listOfDice;
	this.lowerBound = 1;
	this.upperBound = listOfDice.size();
    }
/**
 * This method welcomes the user to the custom roll menu.
 */
    public void welcomeCustomDiceRoll() {
	String welcomeMessage = "|     Welcome to the black market dice Shop!    |\n";
	welcomeMessage += "| Here you can program your super high tech die |\n";
	welcomeMessage += "| No one will ever know that your cheating HAHA |\n";
	System.out.printf("%s%s%s", DIVIDER, welcomeMessage, DIVIDER);
    }

/**
 * This method displays the options the user can choose.
 */
    public void customDiceMenu() {
	displayDiceData();
	String diceMenu = "|     Leave[L]     |     Dice Number [" + lowerBound + "-" + upperBound + "]      |\n";
	System.out.printf("%s%s%s", DIVIDER, diceMenu, DIVIDER);
    }

/**
 * This method diaplys the goodbye message.
 */
    public void endCustomDice() {
	System.out.println();
	System.out.println("Your Dice Rolls Look like this:");
	displayDiceData();
	String byeMessage = "|     Thanks for stoping by! See you Again!     |\n";
	byeMessage +=      "|   Don't worry your secret is safe with me     |\n";
	System.out.printf("%s%s%s", DIVIDER, byeMessage, DIVIDER);
    }

/**
 * This method prints out once the user has selected a die.
 */
    public void rollRequest() {
	String welcomeMessage = "|  Input a Set Of Numbers Seperated by Spaces   |\n";
	System.out.printf("%s%s%s", DIVIDER, welcomeMessage, DIVIDER);
    }

/**
 * This method gets the data that is stored on each die and prints
 * it out to the screen in color.
 */
    private void displayDiceData() {
	int currentNumber = 1;
	for (Dice dice : listOfDice) {
	    String rollRequest = "Dice [" + currentNumber + "] : " + dice.rollRequest();
	    System.out.println(ColorHelper.coloredString("GREEN", rollRequest));
	    currentNumber++;
	}
    }
}
