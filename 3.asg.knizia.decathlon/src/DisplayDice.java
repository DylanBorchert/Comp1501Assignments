import java.util.ArrayList;

public class DisplayDice {

    private ArrayList<Dice> listOfDice;

    private static final String SPACE_DOT_SPACE = "|   O   | ";
    private static final String DOT_SPACE_DOT = "|O     O| ";
    private static final String DICE_BORDER = "+-------+ ";
    private static final String DOT_SPACE = "|O      | ";
    private static final String SPACE_DOT = "|      O| ";
    private static final String EMPTY = "|       | ";

    private static final int DIE_ONE = 1;
    private static final int DIE_TWO = 2;
    private static final int DIE_THREE = 3;
    private static final int DIE_FOUR = 4;
    private static final int DIE_FIVE = 5;
    private static final int DIE_SIX = 6;
    private static final int NUM_OF_DICE_ROWS = 7;

    private String diceColor = "WHITE";
    private String diceDisplay = "";

/**
 * This constructor initializes the color that the dice appears as.
 * @param diceColor is a Color name as as string that is used for
 * the ColorHelper.java class.
 */
    public DisplayDice(String diceColor) {
	this.diceColor = diceColor;
    }

/**
 *This method returns the dice to display as a String.
 * @param listOfDice is a a rraylidt of dice, this is used to
 * display the dice and the dice is made depending onthe is arraylist
 * @return This method returns the dice to display as a String
 */
    public String diceView(ArrayList<Dice> listOfDice) {
	return makeDice(listOfDice);
    }

    private String makeDice(ArrayList<Dice> listOfDice) {
	diceDisplay = "";
	this.listOfDice = listOfDice;
	int numOfDice = listOfDice.size();
	for (int i = 0; i < NUM_OF_DICE_ROWS; i++) {
	    switch (i) {
		case 1: diceNumbers(numOfDice);
		    break;
		case 2: diceBorder(numOfDice);
		    break;
		case 3: diceTop();
		    break;
		case 4: diceMid();
		    break;
		case 5: diceBottom();
		    break;
		case 6: diceBorder(numOfDice);
		    break;
		default:
		    break;
	    }
	}
	if (diceHasValues()) {
	    return ColorHelper.coloredString(diceColor, diceDisplay);
	} else {
	    return "";
	}
    }

    private void diceNumbers(int numOfDice) {
	diceDisplay = "   [1]";
	for (int i = 2; i <= numOfDice; i++) {
	    diceDisplay += String.format("       [%d]", i);
	}
	diceDisplay += "\n";
    }

    private void diceBorder(int numOfDice) {
	for (int i = 0; i < numOfDice; i++) {
	    diceDisplay += DICE_BORDER;
	}
	diceDisplay += "\n";
    }

    private void diceTop() {
	for (Dice dice : listOfDice) {
	    int value = dice.value();
	    if (value == DIE_SIX || value == DIE_FIVE || value == DIE_FOUR) {
		diceDisplay += DOT_SPACE_DOT;
	    } else if (value == DIE_THREE || value == DIE_TWO) {
		diceDisplay += DOT_SPACE;
	    } else {
		diceDisplay += EMPTY;
	    }
	}
	diceDisplay += "\n";
    }

    private void diceMid() {
	for (Dice dice : listOfDice) {
	    int value = dice.value();
	    if (value == DIE_SIX) {
		diceDisplay += DOT_SPACE_DOT;
	    } else if (value == DIE_FIVE || value == DIE_THREE || value == DIE_ONE) {
		diceDisplay += SPACE_DOT_SPACE;
	    } else {
		diceDisplay += EMPTY;
	    }
	}
	diceDisplay += "\n";

    }

    private void diceBottom() {
	for (Dice dice : listOfDice) {
	    int value = dice.value();
	    if (value == DIE_SIX || value == DIE_FIVE || value == DIE_FOUR) {
		diceDisplay += DOT_SPACE_DOT;
	    } else if (value == DIE_THREE || value == DIE_TWO) {
		diceDisplay += SPACE_DOT;
	    } else {
		diceDisplay += EMPTY;
	    }
	}
	diceDisplay += "\n";

    }

    private boolean diceHasValues() {
	for (Dice dice : listOfDice) {
	    int value = dice.value();
	    if (value == -1) {
		return false;
	    }
	}
	return true;
    }
}
