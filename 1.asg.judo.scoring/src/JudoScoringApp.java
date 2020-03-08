import java.util.Scanner;

public class JudoScoringApp {

    private Scanner kbd = new Scanner(System.in);

    private JudoScoreboard scoreboard;
    private JudoMatch judoMatch;

    public void run() {

	displayWelcome();

	initializeJudoMatch();

	displayStartMessage();

	do {
	    System.out.println(scoreboard.display());
	    System.out.print("event :> ");
	    String matchEvent = kbd.nextLine();

	    judoMatch.process(matchEvent);



	} while (!judoMatch.over());

	displayResults();

	displayExitMessage();
    }

    private void initializeJudoMatch() {
	System.out.println();
	System.out.print("How many minutes should the match be? ");
	int matchLength = kbd.nextInt();

	kbd.nextLine();

	judoMatch = new JudoMatch(matchLength * 60);
	scoreboard = new JudoScoreboard(judoMatch);
    }

    private void displayWelcome() {
	System.out.println();
	System.out.println("Welcome to the Really Adequate Judo Scoring Application");
	System.out.println();
    }

    private void displayStartMessage() {
	System.out.println("Hajime!");
	System.out.println();
    }

    private void displayResults() {
	System.out.println(scoreboard.display());
	if (judoMatch.wonByIppon()) {
	    System.out.println("Ippon!");
	} else if (judoMatch.wonByAwasete()) {
	    System.out.println("Waza-ari, awasete ippon!");
	} else {
	    System.out.println("Sore made!");
	}

	System.out.println("Winner: " + judoMatch.winner());
    }

    private void displayExitMessage() {
	System.out.println();
	System.out.println("Makoto ni arigatou gozaimasu!");
	System.out.println();
    }

}
