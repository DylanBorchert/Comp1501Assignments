public class DisplayMainMenuView {

    private static final String DIVIDER = "+-----------------------------------------------+";

    private String accentColor = "WHITE";

    private DecathlonResults decathlonresults;

/**
 * This contructor brings in the decathlon results to be displayed on the
 * main menu score board, and also initailzes the accent color to be
 * used throughout the main menu.
 * @param decathlonresults is a object the stores all
 * resualts for each event that is played
 * @pram accentColor is a color as a string that the user wants
 * to be the accent color through out the class
 */
    public DisplayMainMenuView(DecathlonResults decathlonresults, String accentColor) {
	this.decathlonresults = decathlonresults;
	this.accentColor = accentColor;
    }

/**
 * This Method prints out the whole main menu, including
 * the score board and menu options.
 */
    public void view() {
	System.out.println(heading());
	eventScore();
	selctionMenu();
    }

    private String heading() {
	String mainMenu = ColorHelper.coloredString(accentColor, "Main Menu");
	String event = ColorHelper.coloredString(accentColor, "Events");
	String score = ColorHelper.coloredString(accentColor, "Score");
	String mainMenuFormat = String.format("|%17s%s%21s|", "", mainMenu, "");
	String eventsAndScoreFormat = String.format("|%17s%s%16s| %s |", "",
						    event, "", score);

	return String.format("%s%n%s%n%s%n%s%n%s", DIVIDER, mainMenuFormat,
			     DIVIDER, eventsAndScoreFormat, DIVIDER);
    }

    private void eventScore() {
	decathlonresults.displayEventScores();
    }

    private void selctionMenu() {
	System.out.println(
               "|  Play Event[P] |  High Score[H]  |   Quit[Q]  |\n" +
               "+-----------------------------------------------+\n");
    }

}
