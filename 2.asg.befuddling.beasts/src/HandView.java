/**
 * Represents a view - we'll take this to mean "a complex formatted String representation" - of a Hand.
 *
 * For this assignment, we want this to look like this example:
 * <p>
 * <pre>
 * [1]  Newfangled Howler (RRGGBB)
 * [2]  Impromptu Duck (RRGB)
 * [3]  Waterless Chupacabra (RRGBB)
 * </pre>
 * Note the numbers shown are 1-based, not 0-based.
 *
 */
public class HandView {

    private Hand hand;

    /**
     * Instantiates a new hand view.
     *
     * @param hand the hand to display a view for
     */
    public HandView(Hand hand) {
	this.hand = hand;
    }

    /**
     * The formatted string representing a Hand.
     *
     * @return the formatted String
     */
    public String view() {
	int handSize = hand.size();

	String result = "";
	for (int i = 0; i < handSize; i++) {
	    BeastCard card = hand.get(i);
	    result += String.format("%-69s%n", oneRow(card, i + 1));
	}

	return result;
    }

    /**
     * One row in the view.
     *
     * @param card the beast card in this row
     * @param cardNum the number assigned to the beast card in this row
     * @return the formatted row String
     */
    private String oneRow(BeastCard card, int cardNum) {
	String bracketedNumber = numberPart(cardNum);
	String cardName = card.name();
	String coloredCardCost = coloredCost(card.cost());

	return String.format("%s %s (%s)", bracketedNumber, cardName, coloredCardCost);
    }

    /**
     * Colored cost of a card.
     *
     * @param cost the uncolored cost
     * @return the colored cost
     */
    private String coloredCost(String cost) {
	String result = "";
	for (int i = 0; i < cost.length(); i++) {
	    String currentLetter = cost.substring(i, i + 1);
	    result += coloredLetter(currentLetter);
	}
	return result;
    }

    /**
     * Colored letter.
     *
     * @param letter the uncolored letter
     * @return the colored letter
     */
    private String coloredLetter(String letter) {
	switch (letter) {
	    case "R": return ColorHelper.coloredString("red", letter);
	    case "G": return ColorHelper.coloredString("green", letter);
	    case "B": return ColorHelper.coloredString("lightblue", letter);
	    default: return letter;  // return uncolored letter if not R, G, or B
	}
    }

    /**
     * Returns the number part of the card display (the [x]) spaced so that when the
     * numbre of digits in x changes, so does the spacing.
     *
     * @param cardNum the card num
     * @return the properly spaced bracketed number
     */
    private String numberPart(int cardNum) {
	final int extraSpacing = 4;
	final int maxNumDigits = hand.size() / 10;

	String numberToDisplay = String.format("[%d]", cardNum);

	String formatString = "%-" + (maxNumDigits + extraSpacing) + "s";
	return String.format(formatString, numberToDisplay);
    }



}
