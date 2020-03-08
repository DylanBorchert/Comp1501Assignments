import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CurrentScoreViewTests {

    @Test
    @DisplayName("returns the score with 2 leading 0's")
    void test() {
	// this scorer always returns 1 for scoreFromCardsPlayed()
	Scorer fakeScorer = new FakeScorer();
	CurrentScoreView currentScoreView = new CurrentScoreView(fakeScorer);

	assertThat(currentScoreView.view()).isEqualTo("005");
    }

}

