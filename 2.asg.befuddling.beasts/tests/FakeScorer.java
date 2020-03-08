
public class FakeScorer extends Scorer {

    public FakeScorer() {
	this(1, null);
    }

    public FakeScorer(int targetEndingScore, Hand hand) {
	super(targetEndingScore, hand);
    }

    @Override
    public int scoreFromCardsPlayed() {
	return 5;
    }

    @Override
    public int numCardsLeft() {
	return 2;
    }

    @Override
    public int penaltyForCardsLeft() {
	return -14;
    }
}
