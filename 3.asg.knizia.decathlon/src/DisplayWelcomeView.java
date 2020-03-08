import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DisplayWelcomeView {

    private static final String WELCOME_MESSAGE = "Welcome to Reiner Knizia's Decathlon Game!!!";

    private String diceColor = "WHITE";
    private String titleColor = "WHITE";

/**
 * This method initalizes the color the user wants to use for
 * the dice and title.
 */
    public DisplayWelcomeView(String titleColor, String diceColor) {
	this.titleColor = titleColor;
	this.diceColor = diceColor;
    }

/**
 * This method returns the welcom meassge in a specific color
 * and it calls the welcome graphic method
 */
    public void view() throws FileNotFoundException {
	System.out.println(heading());
	welcomeGraphic();
    }

    private String heading() {
	String divider = "+-----------------------------------------------+\n";
	String titleColored = ColorHelper.coloredString(titleColor, WELCOME_MESSAGE);
	String titleFormat = String.format("| %s  |%n", titleColored);
	return divider + titleFormat + divider;

    }

/**
 * This method brings in the welcome graphic that is in a text file
 * and displays it in a specific color that the user desires.
 * @exception this method throws this excpetion when the
 * dice graphic file can not be found
 */
    private  void welcomeGraphic() throws FileNotFoundException {
	File graphic = new File("DiceGraphic.txt");

	Scanner fileScanner = new Scanner(graphic);
	while (fileScanner.hasNextLine()) {
	    String colorFormatedDice = ColorHelper.coloredString(diceColor, fileScanner.nextLine());
	    System.out.println(colorFormatedDice);
	}
	System.out.println();
    }

}
