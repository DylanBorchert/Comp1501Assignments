import java.util.ArrayList;

/**
 * Represents a hand of BeastCard(s).
 */
public class Hand {

    private ArrayList<BeastCard> cards = new ArrayList<>();

    /**
     * Return the beast card at given position in this hand.
     * <p>
     * Numbering starts from 0.
     *
     * @param i the number (starting at 0) of the card you want from this hand
     * @return the beast card at that position
     */
    public BeastCard get(int i) {
	return cards.get(i);
    }

    /**
     * Adds a beast card to the "end" of this hand.
     *
     * @param card the beast card to add
     */
    public void add(BeastCard card) {
	cards.add(card);
    }

    /**
     * Returns the number of cards currently in this hand.
     *
     * @return the number of cards in this hand
     */
    public int size() {
	return cards.size();
    }

    /**
     * Returns true when a card that can be played exists in this hand,
     * based on a given pool of colors provided.
     *
     * @param colorPool the color pool being used to pay for a cards
     * @return true, if there is at least one card in this hand that can be played
     */
    public boolean hasPlayableCard(ColorPool colorPool) {
	for (int i = 0; i < size(); i++) {
	    if (canAfford(cards.get(i), colorPool)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Removes the card at a given position (0-based) in this hand.
     *
     * @param index the position to remove the card from
     */
    public void remove(int index) {
	cards.remove(index);
    }

    /**
     * Returns true if this hand is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty() {
	return size() == 0;
    }

    /**
     * Returns true when a given card can be paid for by a given pool.
     *
     * @param cardToPlay the card to examine for affordability
     * @param colorPool the color pool to use for affordability checking
     * @return true, if card can be paid for
     */
    private boolean canAfford(BeastCard cardToPlay, ColorPool colorPool) {
	return colorPool.canPayFor(cardToPlay);
    }



}
