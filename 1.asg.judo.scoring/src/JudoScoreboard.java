/**This class job is to create the scoreboard that you see in the sample runs.*/
public class JudoScoreboard {

    //constants variable
    private static final int CHART_LENGTH = 61;
    private static final int TIME_DIVISOR = 60;
    //updated points to be used in the methods
    private int whiteIppon;
    private int whiteWazaAri;
    private int whiteYuko;

    private int blueIppon;
    private int blueWazaAri;
    private int blueYuko;
    //updated time to be used in methods
    private int timeRemaining;
    //object instance to be used in updateScoreboard
    private JudoMatch judoMatch;

    //It has one JudoMatch parameter - it needs this because the match
    //"knows" much of what that the scoreboard needs to display:
    //the current scores for each athlete and the time remaining in the match.
    public JudoScoreboard(JudoMatch judoMatch) {
	this.judoMatch = judoMatch;
	updateScoreboard(); //update values
    }

    //When run it will update the instance variables to get updated values
    private void updateScoreboard() {

	whiteIppon = judoMatch.numIpponWhite();
	whiteWazaAri = judoMatch.numWazaAriWhite();
	whiteYuko = judoMatch.numYukoWhite();

	blueIppon = judoMatch.numIpponBlue();
	blueWazaAri = judoMatch.numWazaAriBlue();
	blueYuko = judoMatch.numYukoBlue();

	timeRemaining = judoMatch.timeRemaining();

    }

    //This method returns a String that is everything that you see between
    //the ========= lines in the sample output.
    //You will likely want to use helper methods for this method
    //if you don't things are going to be fuuuuugly!
    public String display() {

	updateScoreboard(); //update values
	return chartTopBottom() + header() + whiteScoreBoard() +
	    blueScoreBoard() + timeRemaining() + chartTopBottom();
    }

    //returns match header
    private String header() {
	return String.format("%n%27s%12s%12s%n", "IPPON", "WAZA-ARI", "YUKO");
    }

    //returns match the score for the white athlete in white text
    private String whiteScoreBoard() {
	String whiteScoreFormat = String.format("%15s%12d%12d%12d%n", "WHITE",
						whiteIppon, whiteWazaAri, whiteYuko);
	return ColorHelper.coloredString("WHITE", whiteScoreFormat);
    }

    //returns match the score for the blue athlete in blue text
    private String blueScoreBoard() {
	String blueScoreFormat = String.format("%15s%12d%12d%12d%n", "BLUE",
					       blueIppon, blueWazaAri, blueYuko);
	return ColorHelper.coloredString("LIGHTBLUE", blueScoreFormat);
    }

    //returns match the time remaining in green text
    private String timeRemaining() {
	String timeString = "Time Remaining:";
	int minutes = timeRemaining / TIME_DIVISOR;
	int seconds = timeRemaining % TIME_DIVISOR;
	String time = String.format("%02d:%02d", minutes, seconds);
	String formatTime = String.format("%n%15s%12s%n", timeString, time);
	return ColorHelper.coloredString("GREEN", formatTime);

    }

    //returns the chart top and bottom and makes the "=" 61 characters long
    private String chartTopBottom() {

	String chartTopBottom = "=";
	while (CHART_LENGTH >= chartTopBottom.length()) {
	    chartTopBottom = chartTopBottom + "=";
	}
	return chartTopBottom;
    }
}
