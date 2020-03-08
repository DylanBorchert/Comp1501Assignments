public class PlayerScore {

    private String playerInitials;
    private int playerTotalScore;

    public PlayerScore(String playerInitials, int playerTotalScore) {
	this.playerInitials = playerInitials;
	this.playerTotalScore = playerTotalScore;

    }

    public PlayerScore(int playerTotalScore) {
	this.playerTotalScore = playerTotalScore;
    }

    public int score() {
	return playerTotalScore;
    }

    public String initials() {
	return playerInitials;
    }
}
