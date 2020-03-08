import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class HighScoresPersistenator {

    private String highScorePath;
    private HighScores highScores;

    public HighScoresPersistenator(int numOfHighScores, String highScorePath) {
	this.highScorePath = highScorePath;
	highScores = new HighScores(numOfHighScores);
    }

    public HighScores load() throws FileNotFoundException {
	File scoresFile = new File(highScorePath);
	Scanner fileScanner = new Scanner(scoresFile);

	while (fileScanner.hasNext()) {
	    String playerInitials = fileScanner.next();
	    int playerTotalScore = fileScanner.nextInt();
	    PlayerScore playerScore = new PlayerScore(playerInitials, playerTotalScore);
	    highScores.add(playerScore);
	}

	fileScanner.close();
	return highScores;

    }

    public void save(HighScores highScores) throws FileNotFoundException {
	this.highScores = highScores;
	PrintWriter printWriter = new PrintWriter(highScorePath);
	int size = highScores.numScores();

	for (int i = 1; i <= size; i++) {
	    PlayerScore player = highScores.ranking(i);
	    String playerInitials = player.initials();
	    int playerScore = player.score();
	    printWriter.println(playerInitials + " " + playerScore);
	}

	printWriter.close();
    }

}
