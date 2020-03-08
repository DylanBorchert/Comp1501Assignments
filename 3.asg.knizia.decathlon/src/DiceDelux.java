public class DiceDelux extends Dice {

    private static final int MAX_DICE_NUM = 6;

/**
 * This method overrides the roll method in Dice.java.
 * This Dice roll allows the user to roll a custom dice
 * and once there is no more cutom dice to roll it will roll
 * a random dice.
 */
    @Override
    public void roll() {
	if (!rollRequest.isEmpty()) {
	    diceValue = rollRequest.remove(0);
	} else {
	    diceValue = ranGen.nextInt(MAX_DICE_NUM) + 1;
	}
    }

}
