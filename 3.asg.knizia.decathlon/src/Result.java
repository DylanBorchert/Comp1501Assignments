public class Result {

    private int eventNum;
    private int eventScore = 0;


/**
 * This Method initializes an Event.
 * <p>
 * @param eventNum is the nuber as it apears on the eventList
 */
    public Result(int eventNum) {
	this.eventNum = eventNum;
    }

/**
 * This Method updates the Score.
 * <p>
 * @param scoredPoints is the score to be updated with
 */
    public void updateResult(int scoredPoints) {
	eventScore = scoredPoints;
    }

/**
 * This Method returns current score
 * <p>
 * @return the current score as Interger
 */
    public int currentScore() {
	return eventScore;
    }

/**
 * This Method returns the event numeber.
 * <p>
 * @return the current event number
 */
    public int eventNum() {
	return eventNum;
    }

}
