import java.util.ArrayList;
public class HighScores {

    private ArrayList<PlayerScore> playerScoreList = new ArrayList<>();

    private int numPlayersAllowed;

    public HighScores(int numPlayersAllowed) {
	this.numPlayersAllowed = numPlayersAllowed;
    }

    public boolean isEligible(PlayerScore myPlayersScore) {
	int myScore = myPlayersScore.score();
	int lastPlaceScore =  1;
	if (numScores() > 0) {
	    lastPlaceScore = playerScoreList.get(numScores() - 1).score();
	}
	boolean emptySpotsRemain =  numScores() < numPlayersAllowed;
	boolean canPlaceLast = myScore > lastPlaceScore || emptySpotsRemain;
	return myScore > 0 && canPlaceLast;
    }

    public void add(PlayerScore playerScore) {
	if (isEligible(playerScore)) {
	    addHighScore(playerScore);
	}
    }

    private void addHighScore(PlayerScore currentPlayer) {
	int myScore = currentPlayer.score();
	int posToAddAt = numScores();
	int currentPos = 0;
	for (PlayerScore player : playerScoreList) {
	    int rankedScore = player.score();
	    if (myScore > rankedScore) {
		posToAddAt = currentPos;
		break;
	    } else if (myScore == rankedScore) {
		posToAddAt = currentPos + 1;
	    }
	    currentPos++;
	}
	if (!playerScoreList.isEmpty()) {
	    playerScoreList.add(posToAddAt, currentPlayer);
	} else {
	    playerScoreList.add(currentPlayer);
	}
	removeLast();
    }
    private void removeLast() {
	while (numScores() > numPlayersAllowed) {
	    playerScoreList.remove(numScores() - 1);
	}
    }

    public PlayerScore ranking(int rankNumberSerched) {
	if (isValidRankPos(rankNumberSerched)) {
	    return playerScoreList.get(rankNumberSerched - 1);
	} else {
	    return null;
	}
    }

    private boolean isValidRankPos(int rankNumberSerched) {
	return rankNumberSerched <= numScores() && rankNumberSerched > 0;
    }

    public int numScores() {
	return playerScoreList.size();
    }

}
