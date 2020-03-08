import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Dice {

    private static final int MAX_DICE_NUM = 6;

    protected int diceValue = -1;

    protected Random ranGen = new Random();
    protected ArrayList<Integer> rollRequest = new ArrayList<>();
    protected Scanner requestedRoll = null;

    public Dice(String numToRoll) {
	requestedRoll = new Scanner(numToRoll);
	while (requestedRoll.hasNext()) {
	    rollRequest.add(requestedRoll.nextInt());
	}

    }

    public Dice() {
    }

    public void addRollRequest(String numToRoll) {
	requestedRoll = new Scanner(numToRoll);
	while (requestedRoll.hasNext()) {
	    rollRequest.add(requestedRoll.nextInt());
	}
    }

    public String rollRequest() {
	String request;
	if (rollRequest.size() > 0) {
	    request = String.valueOf(rollRequest);
	} else {
	    request = "No Data";
	}
	return request;
    }

    public int value() {
	return diceValue;
    }

    public void roll() {
	if (requestedRoll == null) {
	    diceValue = ranGen.nextInt(MAX_DICE_NUM) + 1;
	} else if (rollRequest.isEmpty()) {
	    diceValue = -1;
	} else if (!rollRequest.isEmpty()) {
	    diceValue = rollRequest.remove(0);
	} else {
	    diceValue = -1;
	}
    }

}
