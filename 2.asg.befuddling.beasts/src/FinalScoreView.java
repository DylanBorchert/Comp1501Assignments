public class FinalScoreView {

    private Scorer scorer;
    private static final String FINAL_SCORE_HEADING = "============== YOUR FINAL SCORE =============";

    /*It has one Scorer parameter. It uses this to figure out the points from cards played,
      how many cards were left in hand, what the penalty was for cards left in hand,
      and the final score, so that it can display that information properly.
    */
    public FinalScoreView(Scorer scorer) {
	this.scorer = scorer;
    }

    //This method returns a specifically-formatted String.
    public String view() {
	return String.format("%s%n%s%n%s%n%s", FINAL_SCORE_HEADING, pointsFromCards(),
			     penaltyFromCards(), finalScore());
    }

    //This method gets points from cards played and returns a formatted String
    private String pointsFromCards() {
	String pointsFromCardsPrompt = "Points from cards played:";
	return String.format("%s %19d", pointsFromCardsPrompt, scorer.scoreFromCardsPlayed());
    }

    //This method gets the penalty form cards left in hand and returns a formatted String
    private String penaltyFromCards() {
	String penaltyFromCardsPrompt = "Penalty for " + scorer.numCardsLeft() + " cards left in hand:";
	return String.format("%s %11d", penaltyFromCardsPrompt, scorer.penaltyForCardsLeft());
    }

    /*This method calcultes the final score from the
      penalties and from cards played and returns the
      calculted score in a formatted String
    */
    private String finalScore() {
	String finalScorePrompt = "Final score:";
	int finalScore = scorer.penaltyForCardsLeft() + scorer.scoreFromCardsPlayed();
	return String.format("%s%33d", finalScorePrompt, finalScore);
    }

}
