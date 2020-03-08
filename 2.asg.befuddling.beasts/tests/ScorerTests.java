import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
class ScorerTests {

    private static final int TARGET_ENDING_SCORE = 30;

    private Hand hand;
    private Scorer scorer;

    @BeforeEach
    public void setUp() {
	this.hand = new Hand();
	this.scorer = new Scorer(TARGET_ENDING_SCORE, hand);
    }

    @Test
    @DisplayName("numCardsLeft() shows the size of the hand")
    void numCardsLeft_shows_size_of_hand() {
	assertThat(scorer.numCardsLeft()).isZero();

	hand.add(new BeastCard("blob", "RG"));
	assertThat(scorer.numCardsLeft()).isEqualTo(1);

	hand.add(new BeastCard("cat", "RG"));
	assertThat(scorer.numCardsLeft()).isEqualTo(2);
    }


    @Test
    @DisplayName("updateScore() + scoreFromCardsPlayed(): 1-color card is 0 points")
    void updateScore_scoreFromCardsPlayed_1_color_card() {
	scorer.updateScore(new BeastCard("0 points beast", "B"));
	assertThat(scorer.scoreFromCardsPlayed()).isZero();
    }

    @Test
    @DisplayName("updateScore() + scoreFromCardsPlayed(): 2-color card is 2 points")
    void updateScore_scoreFromCardsPlayed_2_color_card() {
	scorer.updateScore(new BeastCard("2 points beast", "BG"));
	assertThat(scorer.scoreFromCardsPlayed()).isEqualTo(2);
    }

    @Test
    @DisplayName("updateScore() + scoreFromCardsPlayed(): 3-color card is 2 points")
    void updateScore_scoreFromCardsPlayed_3_color_card() {
	scorer.updateScore(new BeastCard("2 points beast", "RGB"));
	assertThat(scorer.scoreFromCardsPlayed()).isEqualTo(2);
    }

    @Test
    @DisplayName("updateScore() + scoreFromCardsPlayed(): 4-color card is 7 points")
    void updateScore_scoreFromCardsPlayed_4_color_card() {
	scorer.updateScore(new BeastCard("7 points beast", "RRBG"));
	assertThat(scorer.scoreFromCardsPlayed()).isEqualTo(7);
    }

    @Test
    @DisplayName("updateScore() + scoreFromCardsPlayed(): 5-color card is 7 points")
    void updateScore_scoreFromCardsPlayed_5_color_card() {
	scorer.updateScore(new BeastCard("7 points beast", "RGGBB"));
	assertThat(scorer.scoreFromCardsPlayed()).isEqualTo(7);
    }

    @Test
    @DisplayName("updateScore() + scoreFromCardsPlayed(): 6-color card is 10 points")
    void updateScore_scoreFromCardsPlayed_6_color_card() {
	scorer.updateScore(new BeastCard("10 points beast", "RRGGBB"));
	assertThat(scorer.scoreFromCardsPlayed()).isEqualTo(10);
    }

    @Test
    @DisplayName("penaltyForCardsLeft(): 0 cards => penalty 0")
    void penaltyForCardsLeft_0_cards_penalty_0() {
	assertThat(scorer.penaltyForCardsLeft()).isZero();
    }

    @Test
    @DisplayName("penaltyForCardsLeft(): 1 cards => penalty -5")
    void penaltyForCardsLeft_1_cards_penalty_neg_5() {
	hand.add(new BeastCard("blob", "RG"));

	assertThat(scorer.penaltyForCardsLeft()).isEqualTo(-5);
    }

    @Test
    @DisplayName("penaltyForCardsLeft(): 2 cards => penalty -14")
    void penaltyForCardsLeft_2_cards_penalty_neg_14() {
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));

	assertThat(scorer.penaltyForCardsLeft()).isEqualTo(-14);
    }

    @Test
    @DisplayName("penaltyForCardsLeft(): 3 cards => penalty -30")
    void penaltyForCardsLeft_3_cards_penalty_neg_30() {
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));

	assertThat(scorer.penaltyForCardsLeft()).isEqualTo(-30);
    }

    @Test
    @DisplayName("penaltyForCardsLeft(): 8 cards => penalty -80")
    void penaltyForCardsLeft_8_cards_penalty_neg_80() {
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));
	hand.add(new BeastCard("blob", "RG"));

	assertThat(scorer.penaltyForCardsLeft()).isEqualTo(-80);
    }

    @Test
    @DisplayName("gameEndingScoreReached(): false when under ending score")
    void gameEndingScoreReached_false_when_under_ending_score() {
	this.scorer = new Scorer(3, hand);

	assertThat(scorer.gameEndingScoreReached()).isFalse();

	scorer.updateScore(new BeastCard("blob", "RG"));
	assertThat(scorer.gameEndingScoreReached()).isFalse();
    }

    @Test
    @DisplayName("gameEndingScoreReached(): true when target score just reached")
    void gameEndingScoreReached_true_when_just_reached() {
	this.scorer = new Scorer(2, hand);

	scorer.updateScore(new BeastCard("blob", "RG"));
	assertThat(scorer.gameEndingScoreReached()).isTrue();
    }

    @Test
    @DisplayName("gameEndingScoreReached(): true when target score passed")
    void gameEndingScoreReached_true_when_passed() {
	this.scorer = new Scorer(2, hand);

	scorer.updateScore(new BeastCard("blob", "RRG"));
	assertThat(scorer.gameEndingScoreReached()).isTrue();
    }
}
