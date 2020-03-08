public class HighScoresView {

    private static final String DIVIDER = "+-----------------------------------------------+\n";
    private HighScores highScores;

/**
 * This constructor brings in the HighScore object to be used to
 * display the score baord.
 * @param highScores is an object that stores all the scores from past games
 */
    public HighScoresView(HighScores highScores) {
	this.highScores = highScores;
    }

/**
 * This method displays the highs once the user request to see it
 * in the DecathalonApp.java class
 */
    public void view() {
	int numScores = highScores.numScores();
	System.out.println(scoreHeading() + formatedScore(numScores) + DIVIDER);
    }

    private String scoreHeading() {
	String title = "|                 High Scores                   |\n";
	String heading = "| RANK  |             SCORE     |     NAME      |\n";
	return String.format("%s%s%s%s%s", DIVIDER, title, DIVIDER, heading, DIVIDER);
    }


    private String formatedScore(int numScores) {
	String formatedScore = "";
	for (int i = 1; i <= numScores; i++) {
	    PlayerScore player  = highScores.ranking(i);
	    int score = player.score();
	    String initials = player.initials();
	    formatedScore += scoreColoring(i, score, initials);
	}
	return formatedScore;
    }

    private String scoreColoring(int rank, int score, String initials) {
	String playerscore = String.format("|   %d   |            %06d     |      %s      |%n",
					   rank, score, initials);
	switch (rank) {
	    case 1:
		return ColorHelper.coloredString("GREEN", playerscore);
	    case 2:
		return ColorHelper.coloredString("PINK", playerscore);
	    case 3:
		return ColorHelper.coloredString("PURPLE", playerscore);
	    default:
		return ColorHelper.coloredString("YELLOW", playerscore);
	}
    }

}
