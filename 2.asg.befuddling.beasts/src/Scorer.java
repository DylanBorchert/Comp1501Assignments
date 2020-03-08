public class Scorer {


    private Hand hand;
    private Rewarder reward;
    private Rewarder penalty;
    private int scoreToEndGame;
    private int playersScore = 0;


    private static final int FIRST_REWARD_THESHOLD = 2;
    private static final int SECOND_REWARD_THESHOLD = 4;
    private static final int THIRD_REWARD_THESHOLD = 6;

    private static final int FIRST_REWARD = 2;
    private static final int SECOND_REWARD = 7;
    private static final int THIRD_REWARD = 10;

    private static final int FIRST_PENALTY_THRESHOLD = 1;
    private static final int SECOND_PENALTY_THRESHOLD = 2;
    private static final int THIRD_PENALTY_THRESHOLD = 3;

    private static final int FIRST_PENALTY = -5;
    private static final int SECOND_PENALTY = -14;
    private static final int THIRD_PENALTY_FACTOR = -10;

    /*It takes in the score at which the game should end and the Hand of the user.
      It needs the former to decide whether the game is over and the
      latter to figure out penalty points at the end of the game.
    */
    public Scorer(int scoreToEndGame, Hand hand) {
	this.reward = new Rewarder();
	this.penalty = new Rewarder();
	this.hand = hand;
	this.scoreToEndGame = scoreToEndGame;
    }

    //This method updates the player's current score based on the incoming cards cost.
    public void updateScore(BeastCard beastcard) {
	String cost = beastcard.cost();
	int costLength = cost.length();

	reward.setThresholdOne(FIRST_REWARD_THESHOLD, FIRST_REWARD);
	reward.setThresholdTwo(SECOND_REWARD_THESHOLD, SECOND_REWARD);
	reward.setThresholdThree(THIRD_REWARD_THESHOLD, THIRD_REWARD);

	playersScore += reward.reward(costLength);
    }

    /*This method returns the number of points the player
      has currently scored from playing beast cards.
    */
    public int scoreFromCardsPlayed() {
	return playersScore;
    }

    //This method returns the number of cards left in the player's Hand.
    public int numCardsLeft() {
	return hand.size();
    }

    //This method returns the size of the penalty awarded to the player at the end of the game.
    public int penaltyForCardsLeft() {
	int playerPenalty = 0;
	int numOfCardsInHand = hand.size();

	int calculatedThirdPenalty = numOfCardsInHand * THIRD_PENALTY_FACTOR;

	penalty.setThresholdOne(FIRST_PENALTY_THRESHOLD, FIRST_PENALTY);
	penalty.setThresholdTwo(SECOND_PENALTY_THRESHOLD, SECOND_PENALTY);
	penalty.setThresholdThree(THIRD_PENALTY_THRESHOLD, calculatedThirdPenalty);
	playerPenalty += penalty.reward(numOfCardsInHand);

	return playerPenalty;
    }

    /*This method returns true if the current score from cards played
      has met or exceeded the score that triggers the end of the game
    */
    public boolean gameEndingScoreReached() {
	return playersScore >= scoreToEndGame;
    }

}
