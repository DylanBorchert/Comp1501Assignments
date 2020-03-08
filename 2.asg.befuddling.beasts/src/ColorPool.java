public class ColorPool {

    private int numRedInPool = 0;
    private int numGreenInPool = 0;
    private int numBlueInPool = 0;

    private static final String RED = "R";
    private static final String GREEN = "G";
    private static final String BLUE = "B";

    /*These methods return the number of red, green,
      and blue colors in the pool, respectfully and respectively.
    */
    public int numRed() {
	return numRedInPool;
    }

    public int numGreen() {
	return numGreenInPool;
    }

    public int numBlue() {
	return numBlueInPool;
    }

    /*Receives a color String (one of "R", "G", or "B"
      case insensitive) and increments the corresponding color in the pool.
      If any other String is received, nothing is incremented.
    */
    public void gain(String colorGainLetter) {
	String colorString = colorGainLetter.toUpperCase();

	switch (colorString) {
	case RED:
	    numRedInPool++;
	    break;
	case GREEN:
	    numGreenInPool++;
	    break;
	case BLUE:
	    numBlueInPool++;
	    break;
	default:
	    break;
	}
    }
    /*Receives a String of colors (like "RG" or "BBGGRR")
      and decrements the corresponding colors in the pool.
    */
    public void pay(String colorPayString) {
	int length = colorPayString.length();
	String colorsToPay = colorPayString.toUpperCase();

	int costRed = redCost(colorsToPay, length);
	int costGreen = greenCost(colorsToPay, length);
	int costBlue = blueCost(colorsToPay, length);

	numRedInPool = Math.max(0, numRedInPool - costRed);
	numGreenInPool = Math.max(0, numGreenInPool - costGreen);
	numBlueInPool = Math.max(0, numBlueInPool - costBlue);
    }

    /*Returns true if the current colors in this pool
      can pay for the incoming card.
    */
    public boolean canPayFor(BeastCard beastcard) {
	String cost = beastcard.cost().toUpperCase();
	int length = cost.length();

	int redCost = redCost(cost, length);
	int greenCost = greenCost(cost, length);
	int blueCost = blueCost(cost, length);

	return redCost <=  numRedInPool && greenCost <= numGreenInPool && blueCost <= numBlueInPool;
    }

    /*These methods take in a String of colors and each method
      counts how many of one color is in the string
    */
    private int redCost(String colorString, int length) {

	int redCost = 0;
	for (int i = 0; i < length; i++) {
	    String currentColor =  colorString.substring(i, i + 1);
	    if (currentColor.equals(RED)) {
		redCost++;
	    }
	}
	return redCost;
    }

    private int greenCost(String colorString, int length) {

	int greenCost = 0;
	for (int i = 0; i < length; i++) {
	    String currentColor =  colorString.substring(i, i + 1);
	    if (currentColor.equals(GREEN)) {
		greenCost++;
	    }
	}
	return greenCost;
    }

    private int blueCost(String colorString, int length) {

	int blueCost = 0;
	for (int i = 0; i < length; i++) {
	    String currentColor =  colorString.substring(i, i + 1);
	    if (currentColor.equals(BLUE)) {
		blueCost++;
	    }
	}
	return blueCost;
    }
}
