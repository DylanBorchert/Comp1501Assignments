import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A deck of beast cards.
 *
 */
public class BeastDeck {

    // exclusive upper bound on number of a given color
    // that will be assigned as a cost to a beast
    private static final int MAX_GIVEN_COLOR_COUNT = 3;

    // the number of beast cards in this deck
    private static final int DECK_SIZE = 200;

    private LinkedList<BeastCard> cardsInDeck;

    private final Random randomGen = new Random();

    /**
     * Instantiates a new beast deck of 200 cards and shuffles it.
     */
    public BeastDeck() {
    	cardsInDeck = new LinkedList<>();
    	addCrittersToDeck(DECK_SIZE);
    	shuffleDeck();
    }


    /**
     * Returns the first card from the deck.
     *
     * @return the beast card drawn
     */
    public BeastCard drawnCard() {
        return cardsInDeck.removeFirst();
    }


    /**
     * Could use this for debugging.
     *
     * @return a reasonable String representation of this deck
     */
    @Override
    public String toString() {
        return cardsInDeck.toString();
    }


    /**
     * Adds the critters to the deck.
     *
     * @param numToAdd the num of critters to add
     */
    private void addCrittersToDeck(int numToAdd) {
	ArrayList<String> adjectives = new ArrayList<>();
	initList("words/adjectives.txt", adjectives);

	ArrayList<String> animals = new ArrayList<>();
	initList("words/animals.txt", animals);

	for (int i = 0; i < numToAdd; i++) {
	    String adjective = titleCasedString(randomFromList(adjectives));
	    String animal = titleCasedString(randomFromList(animals));
	    String cost = randomCost();
	    cardsInDeck.add(new BeastCard(adjective + " " + animal, cost));
	}
    }


    /**
     * Shuffle using the Fisher-Yates method.
     */
    private void shuffleDeck() {
    	int startSwapIndex = cardsInDeck.size() - 1;

        for (int i = startSwapIndex; i >= 1; i--) {
            int p = randomGen.nextInt(i + 1);
            swap(i, p);
        }
    }


    /**
     * Swaps two cards in the deck.
     *
     * @param i the index of the first card
     * @param j the index of the second card
     */
    private void swap(int i, int j) {
    	BeastCard temp = cardsInDeck.get(i);
        cardsInDeck.set(i, cardsInDeck.get(j));
        cardsInDeck.set(j, temp);
    }


    /**
     * Title cased String.
     *
     * @param s the String to title case
     * @return the title-cased String
     */
    private String titleCasedString(String s) {
	String firstLetter = s.substring(0, 1).toUpperCase();
	String rest = s.substring(1).toLowerCase();
	return firstLetter + rest;
    }


    /**
     * Choose random String from a list.
     *
     * @param list the list to choose from
     * @return the string chosen
     */
    private String randomFromList(ArrayList<String> list) {
	int listSize = list.size();
	int index = randomGen.nextInt(listSize);
	return list.get(index);
    }


    /**
     * Returns a random cost (of R, G, and B).
     *
     * @return the cost
     */
    private String randomCost() {
	int numRed = 1 + randomGen.nextInt(MAX_GIVEN_COLOR_COUNT - 1);
	int numGreen = 1 + randomGen.nextInt(MAX_GIVEN_COLOR_COUNT - 1);
	int numBlue = 1 + randomGen.nextInt(MAX_GIVEN_COLOR_COUNT - 1);

	return repeatedChar(numRed, "R") + repeatedChar(numGreen, "G") + repeatedChar(numBlue, "B");
    }

    /**
     * Returns a String composed of a given character repeated a given number of times.
     *
     * @param num the number of times you want to repeat the character
     * @param s the character (not char!) you want to repeat
     * @return the string with the repeated character
     */
    private String repeatedChar(int num, String s) {
	String result = "";
	for (int i = 0; i < num; i++) {
	    result += s;
	}
	return result;
    }


    /**
     * Reads in a file containing information to initialize a given list with and fills
     * the list with that info.
     *
     * We ignore lines that have spaces in them. (We're looking to populate our lists with
     * single words, not phrases.)
     *
     * @param fileName the file to find our words in
     * @param listToInit the list to fill with words
     */
    private void initList(String fileName, ArrayList<String> listToInit) {
    	List<String> lines = null;
    	try {
	    lines = Files.readAllLines(Paths.get(fileName));
	    for (String line : lines) {
		if (!line.contains(" ")) {
		    listToInit.add(line);
		}
	    }
    	} catch (IOException e) {
	    e.printStackTrace();
	}
    }




}
