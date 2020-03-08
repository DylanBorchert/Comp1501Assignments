/**
 * Represents a view - we'll take this to mean "a complex formatted String
 * representation" - of the play area in the game.
 *
 * This view uses 3 other views to do its job: ColorPoolView, CurrentScoreView, and HandView.
 *
 * For this assignment, we want this to look like this example:
 * <pre>
 * +--------------------------------------------------+
 * |  R03 G00 B03  |   BEFUDDLING BEASTS    |   007   |
 * +--------------------------------------------------+
 * [1]  Reported Ki-rin (RGGBB)
 * [2]  Burghal Goose (RGGB)
 * [3]  Mirthful Scopikis (RRGGB)
 * [4]  Tripping Hydroloth (RRGGBB)
 * [5]  Emphasized Amnizu (RRGBB)
 * [6]  Untaxing Bobcat (RRGGBB)
 * +--------------------------------------------------+
 * | [P]lay a card      [D]raw a card       [Q]uit    |
 * +--------------------------------------------------+
 * </pre>
 * Top left: shows the current color pool
 * Top right: shows the current score
 * Middle: shows the hand
 * Bottom: available menu options
 *
 */
public class PlayAreaView {

    // a convenience for println
    private static final String EOLN = String.format("%n");

    private static final String DIVIDER = "+--------------------------------------------------+";


    private ColorPoolView colorPoolView;
    private CurrentScoreView scoreView;

    /** The hand view. */
    private HandView handView;

    /**
     * Instantiates a new play area view.
     *
     * @param colorPoolView the color pool view
     * @param scoreView the score view
     * @param handView the hand view
     */
    public PlayAreaView(ColorPoolView colorPoolView, CurrentScoreView scoreView, HandView handView) {
	this.colorPoolView = colorPoolView;
	this.scoreView = scoreView;
	this.handView = handView;
    }


    /**
     * The formatted String representation of the play area.
     *
     * @return the formatted view
     */
    public String view() {
	String result = "";
	result += DIVIDER + EOLN;
	result += "|  " + colorPoolView.view() + " |   BEFUDDLING BEASTS    |   " + scoreView.view() + "   |" + EOLN;
	result += DIVIDER + EOLN;
	result += handView.view();
	result += DIVIDER + EOLN;
	result += "| [P]lay a card      [D]raw a card       [Q]uit    |" + EOLN;
	result += DIVIDER;

	return result;
    }


}
