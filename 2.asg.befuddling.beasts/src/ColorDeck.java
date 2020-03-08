import java.util.Random;

/**
 * A "deck" of colors (really just "R"s, "G"s, and "B"s.
 *
 */
public class ColorDeck {

    private static final int NUM_COLORS = 3;

    private static final String RED = "R";
    private static final String GREEN = "G";
    private static final String BLUE = "B";

    private Random randGen = new Random();

    /**
     * Returns a "R", "G", or "B" with equal probability (1 in 3).
     *
     * @return an "R", "G", or "B"
     */
    public String draw() {
	int randNum = randGen.nextInt(NUM_COLORS);
	switch (randNum) {
	    case 0: return RED;
	    case 1: return GREEN;
	    default: return BLUE;
	}
    }

}
