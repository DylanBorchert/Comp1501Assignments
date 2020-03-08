public class CurrentScoreView {

    private Scorer scorer;

    /*It has one Scorer parameter. It uses this to figure out how many
      points have been earned from playing beast cards,
      so that it can display that information properly.
    */
    public CurrentScoreView(Scorer scorer) {
	this.scorer = scorer;
    }

    //This method returns a specifically-formatted String
    public String view() {
	return String.format("%03d", scorer.scoreFromCardsPlayed());
    }

}
