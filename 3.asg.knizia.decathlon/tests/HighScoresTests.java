import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;


class HighScoresTests {



    @Test
    @DisplayName("even an empty one won't accept a score if it's 0")
    void even_empty_one_wont_take_0() {
	HighScores scores = new HighScores(1);

	PlayerScore zeroScore = new PlayerScore("FNP", 0);

	assertThat(scores.isEligible(zeroScore)).isFalse();
    }


    @Test
    @DisplayName("an empty will take even a 1")
    void an_empty_one_will_take_even_a_1() {
	HighScores scores = new HighScores(1);

	PlayerScore scoreOne = new PlayerScore("FNP", 1);

	assertThat(scores.isEligible(scoreOne)).isTrue();
    }


    @Test
    @DisplayName("one with a single slot left will take even a 1")
    void one_with_a_single_slot_left_will_even_take_a_1() {
	HighScores scores = new HighScores(2);
	scores.add(new PlayerScore("AAA", 2));

	PlayerScore scoreOne = new PlayerScore("FNP", 1);

	assertThat(scores.isEligible(scoreOne)).isTrue();
    }

    @Test
    @DisplayName("a score is not eligible if no empty slots remain AND the score isn't better than the lowest - size 1 case")
    void reject_score_if_no_room_and_incoming_doesnt_beat_current_lowest_size_1_case() {
	HighScores scores = new HighScores(1);

	PlayerScore scoreToBeat = new PlayerScore("CCC", 2);

	scores.add(scoreToBeat);

	PlayerScore lowerThanScoreToBeat = new PlayerScore("BBB", 1);
	assertThat(scores.isEligible(lowerThanScoreToBeat)).isFalse();

	PlayerScore tiedWithScoreToBeat = new PlayerScore("AAA", 2);
	assertThat(scores.isEligible(tiedWithScoreToBeat)).isFalse();

    }


    @Test
    @DisplayName("a score that ties is lower than matching - different name case")
    void a_score_that_ties_is_lower_different_name_case() {
	HighScores scores = new HighScores(2);

	PlayerScore highest = new PlayerScore("AAA", 2);

	scores.add(highest);

	PlayerScore tiedForHighest = new PlayerScore("BBB", 2);
	assertThat(scores.isEligible(tiedForHighest)).isTrue();
	scores.add(tiedForHighest);

	assertThat(scores.ranking(1)).isSameAs(highest);
	assertThat(scores.ranking(2)).isSameAs(tiedForHighest);

    }

    @Test
    @DisplayName("a score that ties is lower than matching - same name case")
    void a_score_that_ties_is_lower_same_name_case() {
	HighScores scores = new HighScores(2);

	PlayerScore highest = new PlayerScore("AAA", 2);

	scores.add(highest);

	PlayerScore tiedForHighest = new PlayerScore("AAA", 2);
	assertThat(scores.isEligible(tiedForHighest)).isTrue();
	scores.add(tiedForHighest);

	assertThat(scores.ranking(1)).isSameAs(highest);
	assertThat(scores.ranking(2)).isSameAs(tiedForHighest);

    }


    @Test
    @DisplayName("a score that beats the lowest pushes lowest down - under capacity case")
    void a_score_that_beats_the_lowest_pushes_lowest_down_under_capacity_case() {
	HighScores scores = new HighScores(3);

	PlayerScore highest = new PlayerScore("AAA", 3);

	scores.add(highest);

	PlayerScore oldLowest = new PlayerScore("BBB", 1);
	scores.add(oldLowest);

	PlayerScore newLowest = new PlayerScore("CCC", 2);
	scores.add(newLowest);

	assertThat(scores.ranking(1)).isSameAs(highest);
	assertThat(scores.ranking(2)).isSameAs(newLowest);
	assertThat(scores.ranking(3)).isSameAs(oldLowest);

    }


    @Test
    @DisplayName("a score that beats the lowest pushes lowest down and off list - at capacity case")
    void a_score_that_beats_the_lowest_pushes_lowest_down_and_off_at_capacity_case() {
	HighScores scores = new HighScores(2);

	PlayerScore highest = new PlayerScore("AAA", 3);

	scores.add(highest);

	PlayerScore oldLowest = new PlayerScore("BBB", 1);
	scores.add(oldLowest);

	PlayerScore newLowest = new PlayerScore("CCC", 2);
	scores.add(newLowest);

	assertThat(scores.ranking(1)).isSameAs(highest);
	assertThat(scores.ranking(2)).isSameAs(newLowest);
	assertThat(scores.ranking(3)).isNull();

    }

    @Test
    @DisplayName("getting the rank of something not there returns null")
    void getting_rank_of_something_not_there_returns_null() {
	HighScores scores = new HighScores(2);

	PlayerScore highest = new PlayerScore("AAA", 2);

	scores.add(highest);

	assertThat(scores.ranking(-1)).isNull();
	assertThat(scores.ranking(0)).isNull();
	assertThat(scores.ranking(2)).isNull();
	assertThat(scores.ranking(3)).isNull();

    }


    @Test
    @DisplayName("number of scores in new one is zero")
    void number_of_scores_in_new_one_is_zero() {
	HighScores scores = new HighScores(2);

	assertThat(scores.numScores()).isZero();

    }


    @Test
    @DisplayName("number of scores is valid when you enter new scores")
    void number_of_scores_when_you_add_scores_is_valid() {
	HighScores scores = new HighScores(2);

	scores.add(new PlayerScore("AAA", 2));

	assertThat(scores.numScores()).isEqualTo(1);

	scores.add(new PlayerScore("BBB", 2));

	assertThat(scores.numScores()).isEqualTo(2);

    }



}
