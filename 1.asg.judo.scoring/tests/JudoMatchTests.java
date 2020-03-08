import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Assignment 1: JudoMatch")
public class JudoMatchTests {

    private static final int NUM_SEC_IN_MATCH = 180;

    private JudoMatch judoMatch;

    @BeforeEach
    public void init() {

	judoMatch = new JudoMatch(NUM_SEC_IN_MATCH);
    }


    @Test
    @DisplayName("new match has given number of seconds left")
    public void new_match_has_given_number_of_seconds()  {

	assertThat(judoMatch.timeRemaining()).isEqualTo(NUM_SEC_IN_MATCH);
    }


    @Test
    @DisplayName("new match is not over yet")
    public void new_match_is_not_over()  {

	assertThat(judoMatch.over()).isFalse();
    }

    @Test
    @DisplayName("new match has 0's for all scoring types")
    public void new_match_has_all_scoring_types_0()  {

	assertThat(judoMatch.numIpponWhite()).isZero();
	assertThat(judoMatch.numWazaAriWhite()).isZero();
	assertThat(judoMatch.numYukoWhite()).isZero();

	assertThat(judoMatch.numIpponBlue()).isZero();
	assertThat(judoMatch.numWazaAriBlue()).isZero();
	assertThat(judoMatch.numYukoBlue()).isZero();
    }

    @Test
    @DisplayName("no ippons should say wonByIppon is false")
    public void no_ippon_for_either_player_then_wonByIppon_should_be_false()  {
	assertThat(judoMatch.wonByIppon()).isFalse();

	judoMatch.process("10");
	assertThat(judoMatch.wonByIppon()).isFalse();


	judoMatch.process("10 w w");
	assertThat(judoMatch.wonByIppon()).isFalse();

	judoMatch.process("10 b w");
	assertThat(judoMatch.wonByIppon()).isFalse();

	judoMatch.process("10 w y");
	assertThat(judoMatch.wonByIppon()).isFalse();
	judoMatch.process("10 w y");
	assertThat(judoMatch.wonByIppon()).isFalse();

	judoMatch.process("10 b y");
	assertThat(judoMatch.wonByIppon()).isFalse();
	judoMatch.process("10 b y");
	assertThat(judoMatch.wonByIppon()).isFalse();



    }


    @Test
    @DisplayName("an ippon for white says wonByIppon is true")
    public void ippon_for_white_sets_wonByIppon_true()  {
	judoMatch.process("10 w i");

	assertThat(judoMatch.wonByIppon()).isTrue();

    }


    @Test
    @DisplayName("an ippon for blue says wonByIppon is true")
    public void ippon_for_blue_sets_wonByIppon_true()  {
	judoMatch.process("10 b i");

	assertThat(judoMatch.wonByIppon()).isTrue();

    }


    @Test
    @DisplayName("two waza-ari for white says wonByAwasete is true")
    public void ippon_for_white_sets_wonByAwasete_true()  {
	judoMatch.process("10 w w");
	assertThat(judoMatch.wonByAwasete()).isFalse();
	judoMatch.process("10 w w");
	assertThat(judoMatch.wonByAwasete()).isTrue();

    }


    @Test
    @DisplayName("game should be over if white scores an ippon")
    public void game_over_if_white_ippon()  {
	judoMatch.process("10 w i");

	assertThat(judoMatch.over()).isTrue();
    }


    @Test
    @DisplayName("game should be over if blue scores an ippon")
    public void game_over_if_blue_ippon()  {
	judoMatch.process("10 b i");

	assertThat(judoMatch.over()).isTrue();
    }

    @Test
    @DisplayName("game should be over if white scores an awasete ippon")
    public void game_over_if_white_awasete_ippon()  {
	judoMatch.process("10 w w");
	judoMatch.process("10 w w");
	assertThat(judoMatch.over()).isTrue();
    }


    @Test
    @DisplayName("game should be over if blue scores an awasete ippon")
    public void game_over_if_blue_awasete_ippon()  {
	judoMatch.process("10 b w");
	judoMatch.process("10 b w");
	assertThat(judoMatch.over()).isTrue();
    }


    @Test
    @DisplayName("game should be over if time runs out")
    public void time_runs_out()  {
	judoMatch.process("181");
	assertThat(judoMatch.over()).isTrue();
    }



    @Test
    @DisplayName("blue wins on points")
    public void blue_wins_on_points()  {
	judoMatch.process("179 b y");
	judoMatch.process("2");

	assertThat(judoMatch.winner()).isEqualTo("blue");
    }

    @Test
    @DisplayName("blue wins by ippon")
    public void blue_wins_by_ippon()  {
	judoMatch.process("14 b i");

	assertThat(judoMatch.winner()).isEqualTo("blue");
    }

    @Test
    @DisplayName("blue wins by awasete ippon")
    public void blue_wins_by_awasete_ippon()  {
	judoMatch.process("14 b w");
	judoMatch.process("14 b w");

	assertThat(judoMatch.winner()).isEqualTo("blue");
    }

    @Test
    @DisplayName("white wins on points")
    public void white_wins_on_points()  {
	judoMatch.process("179 w y");
	judoMatch.process("2");

	assertThat(judoMatch.winner()).isEqualTo("white");
    }


    @Test
    @DisplayName("white wins by ippon")
    public void white_wins_by_ippon()  {
	judoMatch.process("14 w i");

	assertThat(judoMatch.winner()).isEqualTo("white");
    }

    @Test
    @DisplayName("white wins by awasete ippon")
    public void white_wins_by_awasete_ippon()  {
	judoMatch.process("14 w w");
	judoMatch.process("14 w w");

	assertThat(judoMatch.winner()).isEqualTo("white");
    }


    @Test
    @DisplayName("tie game")
    public void tie_game()  {
	judoMatch.process("14 w w");
	judoMatch.process("14 b w");

	assertThat(judoMatch.winner()).isEqualTo("tie");
    }


}

