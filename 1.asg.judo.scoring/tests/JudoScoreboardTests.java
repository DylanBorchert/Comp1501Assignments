import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Assignment 1: JudoScoreboard")
public class JudoScoreboardTests {

    private static final int NUM_SEC_IN_MATCH = 180;

    private JudoScoreboard scoreboard;
    private JudoMatch judoMatch;

    @BeforeEach
    public void init() {

	judoMatch = new JudoMatch(NUM_SEC_IN_MATCH);
	scoreboard = new JudoScoreboard(judoMatch);
    }


    @Test
    @DisplayName("new one displays the expected header row")
    public void new_returns_expected_header()  {
	String displayResult = scoreboard.display();
	assertThat(displayResult).containsSubsequence("IPPON", "WAZA-ARI", "YUKO");
    }


    @Test
    @DisplayName("new one displays the expected row for WHITE")
    public void new_returns_expected_white_row()  {
	String displayResult = scoreboard.display();
	assertThat(displayResult).containsSubsequence("WHITE", "0", "0", "0");
    }


    @Test
    @DisplayName("new one displays the expected row for BLUE")
    public void new_returns_expected_blue_row()  {
	String displayResult = scoreboard.display();
	assertThat(displayResult).containsSubsequence("BLUE", "0", "0", "0");
    }


    @Test
    @DisplayName("new one displays the start time")
    public void returns_start_time()  {
	String displayResult = scoreboard.display();
	assertThat(displayResult)
	    .contains("03:00");
    }


    @Test
    @DisplayName("when time passes, time updates on scoreboard: time passed < 60 sec, time still over 2:00")
    public void shows_passing_time_less_60sec_time_still_over_2min()  {
	judoMatch.process("12");

	String displayResult = scoreboard.display();
	assertThat(displayResult).contains("02:48");
    }


    @Test
    @DisplayName("when time passes, time updates on scoreboard: time passed > 60 sec, time goes under 2:00")
    public void shows_passing_time_over_60sec_time_goes_under_2min()  {
	judoMatch.process("63");

	String displayResult = scoreboard.display();
	assertThat(displayResult).contains("01:57");
    }


    @Test
    @DisplayName("multiple passing of time, with and without points")
    public void multiple_passing_of_time()  {
	judoMatch.process("33");
	judoMatch.process("10 w y");
	judoMatch.process("14 b y");
	judoMatch.process("122 w y");

	String displayResult = scoreboard.display();
	assertThat(displayResult).contains("00:01");
    }


    @Test
    @DisplayName("white scores a yuko, time not up yet")
    public void white_scores_yuko_time_still_remaining()  {
	judoMatch.process("10 w y");

	String displayResult = scoreboard.display();
	assertThat(displayResult).containsPattern("WHITE\\s+0\\s+0\\s+1");
    }


    @Test
    @DisplayName("blue scores a yuko, time not up yet")
    public void blue_scores_yuko_time_still_remaining()  {
	judoMatch.process("10 b y");

	String displayResult = scoreboard.display();
	assertThat(displayResult).containsPattern("BLUE\\s+0\\s+0\\s+1");
	assertThat(displayResult).contains("2:50");
    }

    @Test
    @DisplayName("white scores first waza-ari, time not up yet")
    public void white_scores_waza_time_still_remaining()  {
	judoMatch.process("10 w w");

	String displayResult = scoreboard.display();
	assertThat(displayResult).containsPattern("WHITE\\s+0\\s+1\\s+0");
    }


    @Test
    @DisplayName("blue scores first waza-ari, time not up yet")
    public void blue_scores_waza_time_still_remaining()  {
	judoMatch.process("10 b w");

	String displayResult = scoreboard.display();
	assertThat(displayResult).containsPattern("BLUE\\s+0\\s+1\\s+0");
    }

    @Test
    @DisplayName("white scores first ippon, time not up yet")
    public void white_scores_ippon_time_still_remaining()  {
	judoMatch.process("10 w i");

	String displayResult = scoreboard.display();
	assertThat(displayResult).containsPattern("WHITE\\s+1\\s+0\\s+0");
    }


    @Test
    @DisplayName("blue scores first ippon, time not up yet")
    public void blue_scores_ippon_time_still_remaining()  {
	judoMatch.process("10 b i");

	String displayResult = scoreboard.display();
	assertThat(displayResult).containsPattern("BLUE\\s+1\\s+0\\s+0");
    }



}

