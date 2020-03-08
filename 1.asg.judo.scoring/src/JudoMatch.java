/**This class job is to create the scoreboard that you see in the sample runs.
   This class has quite a few public methods you need to make,
   but most of them are small (like 1 line).*/
import java.util.Scanner;

public class JudoMatch {

    //constants variable
    private static final String WINNER_WHITE = "white";
    private static final String WINNER_BLUE = "blue";
    private static final String WINNER_TIE = "tie";
    private static final String ATHLETE_WHITE = "w";
    private static final String ATHLETE_BLUE = "b";
    private static final String POINT_IPPON = "i";
    private static final String POINT_WAZAARI = "w";
    private static final String POINT_YUKO = "y";

    //holds vaule of the points to be used in the JudoScoreboard
    private int ipponBlueScore;
    private int wazaAriBlueScore;
    private int yukoBlueScore;

    private int ipponWhiteScore;
    private int wazaAriWhiteScore;
    private int yukoWhiteScore;
    //holds value of total time in match
    private int totalTimeSec;
    //will be assigned a string name:"blue","white", and "tie" depending what the winner method calculates
    private String winnerState;

    //It has one int parameter - the amount of time, in seconds,
    //the given match is supposed to last.

    public JudoMatch(int totalTimeSec) {
	this.totalTimeSec = totalTimeSec;
    }

    //This method takes in an event String and changes
    //the match's state accordingly.
    public void process(String matchEvent) {

	Scanner scan = new Scanner(matchEvent);
	String matchTimeString = scan.next(); //gets the match time
	int matchTime  = Integer.parseInt(matchTimeString);
	totalTimeSec -= matchTime;

	if (scan.hasNext()) { //checks to see if there is points being awarded in the event
	    String athleteColor = scan.next();
	    String matchPoint = scan.next();
	    scorer(athleteColor, matchPoint);
	}
	scan.close();
    }

    //This method returns true if the match is over
    public boolean over() {
	return totalTimeSec <= 0 || wonByIppon() || wonByAwasete();
    }

    //This method returns true if an athlete has won by an ippon.
    public boolean wonByIppon() {
	return ipponWhiteScore == 1 || ipponBlueScore == 1;
    }

    //This method returns true if an athlete has won by a  waza-ari.
    public boolean wonByAwasete() {
	return wazaAriWhiteScore == 2 || wazaAriBlueScore == 2;
    }
    //Returns the number of integers remaining in the match.
    public int timeRemaining() {
	return Math.max(0, totalTimeSec);
    }

    //Returns "white" if the match has a winner and the winner is white.
    //Returns "blue" if the match has a winner and the winner is blue.
    //Return "tie" if the match has no winner.
    public String winner() {

	wonByIpponCal(); //goes through  the points to check for winner
	return winnerState;
    }

    //processes winnerState for Ippon
    private void wonByIpponCal() {

	if (ipponWhiteScore == 1) {
	    winnerState = WINNER_WHITE;
	} else if (ipponBlueScore == 1) {
	    winnerState = WINNER_BLUE;
	} else {
	    wonByWazaariCal();
	}
    }

    //processes winnerState for Waza-Ari
    private void wonByWazaariCal() {

	if (wazaAriWhiteScore == 1 && wazaAriBlueScore == 1) {
	    wonByYukoCal();
	} else if (wazaAriWhiteScore > wazaAriBlueScore) {
	    winnerState = WINNER_WHITE;
	} else if (wazaAriWhiteScore < wazaAriBlueScore) {
	    winnerState = WINNER_BLUE;
	} else {
	    wonByYukoCal();
	}
    }

    //processes winnerState for Yuko
    private void wonByYukoCal() {

	if (yukoWhiteScore == yukoBlueScore) {
	    winnerState = WINNER_TIE;
	} else if (yukoWhiteScore > yukoBlueScore) {
	    winnerState = WINNER_WHITE;
	} else if (yukoWhiteScore < yukoBlueScore) {
	    winnerState = WINNER_BLUE;
	}
    }

    //processes athlete color from user input
    private void scorer(String athleteColor, String point) {

	if (athleteColor.equals(ATHLETE_WHITE)) {
	    whiteScorer(point);
	} else if (athleteColor.equals(ATHLETE_BLUE)) {
	    blueScorer(point);
	}
    }

    /**White Athlete*/
    //Each of these methods return the number of X the athlete WHITE has

    //processes athlele points form user input
    private void whiteScorer(String point) {

       	if (point.equals(POINT_IPPON)) {
	    ipponWhiteScore++;
	} else if (point.equals(POINT_WAZAARI)) {
	    wazaAriWhiteScore++;
	} else if (point.equals(POINT_YUKO)) {
	    yukoWhiteScore++;
	}
    }

    public int numIpponWhite() {
	return ipponWhiteScore;
    }

    public int numWazaAriWhite() {
	return wazaAriWhiteScore;
    }

    public int numYukoWhite() {
	return yukoWhiteScore;
    }

    /**Blue Athlete*/
    //Each of these methods return the number of X the athlete BLUE has

    //processes athlele points form user input
    private void blueScorer(String point) {

       	if (point.equals(POINT_IPPON)) {
	    ipponBlueScore++;
	} else if (point.equals(POINT_WAZAARI)) {
	    wazaAriBlueScore++;
	} else if (point.equals(POINT_YUKO)) {
	    yukoBlueScore++;
	}
    }

    public int numIpponBlue() {
	return ipponBlueScore;
    }

    public int numWazaAriBlue() {
	return wazaAriBlueScore;
    }

    public int numYukoBlue() {
	return yukoBlueScore;
    }


}
