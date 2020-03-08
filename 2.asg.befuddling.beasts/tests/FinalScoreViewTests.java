import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FinalScoreViewTests {

    @Test
    @DisplayName("returns the points for cards played, number of cards left in hand, penalty, and final score - in that order")
    void test() {
	// this scorer always returns 5 for scoreFromCardsPlayed().
	// 2 for numCardsLeft(), and -14 for penaltyForCardsLeft()
	Scorer fakeScorer = new FakeScorer();
	FinalScoreView finalScoreView = new FinalScoreView(fakeScorer);

	assertThat(finalScoreView.view()).contains("5");
	assertThat(finalScoreView.view()).contains("2");
	assertThat(finalScoreView.view()).contains("-14");
	assertThat(finalScoreView.view()).contains("-9");
    }

}
