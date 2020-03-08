import java.util.ArrayList;

public class DiceCollection {

    private ArrayList<Dice> listOfDice;

    private static final int DIE_SIX = 6;

    public DiceCollection(ArrayList<Dice> listOfDice) {
	this.listOfDice = listOfDice;
    }

    public void initializeDice(int numDiceRoll) {
	for (int i = 0; i < numDiceRoll; i++) {
	    listOfDice.add(i, new DiceDelux());
	}
    }

    public int total() {
	if (listOfDice.isEmpty()) {
	    return -1;
	}
	int sumOfDice = 0;
	for (Dice dice : listOfDice) {
	    int diceValue = dice.value();
	    if (diceValue == -1) {
		return -1;
	    }
	    sumOfDice += diceValue;
	}
	return sumOfDice;
    }

    public int countSixes() {
	int numberOfSixes = 0;
	for (Dice dice : listOfDice) {
	    if (dice.value() == DIE_SIX) {
		numberOfSixes++;
	    }
	}
	return numberOfSixes;
    }

    public void roll() {
	for (Dice dice : listOfDice) {
	    dice.roll();
	}
    }

    public ArrayList<Integer> values() {
	ArrayList<Integer> diceValues = new ArrayList<>();
	for (Dice dice : listOfDice) {
	    diceValues.add(dice.value());
	}
	return diceValues;
    }

    public boolean contains(int searchedDiceNum) {
	for (Dice dice : listOfDice) {
	    if (dice.value() == searchedDiceNum) {
		return true;
	    }
	}
	return false;
    }

    public boolean containsEven() {
	for (Dice dice : listOfDice) {
	    int value = dice.value();
	    if (value % 2 == 0) {
		return true;
	    }
	}
	return false;
    }

}
