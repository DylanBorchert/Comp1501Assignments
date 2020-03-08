public class ColorPoolView {

    private ColorPool colorpool;

    private static final String RED = "R";
    private static final String GREEN = "G";
    private static final String BLUE = "B";

    /*It has one "ColorPool" parameter. It uses this to figure out how many
      of each color there are so that it can display that information properly.
    */
    public ColorPoolView(ColorPool colorpool) {
	this.colorpool = colorpool;
    }


    //This method returns a specifically-formatted String
    public String view() {

	String redPool = RED + colorFormat(colorpool.numRed());
	String greenPool = GREEN + colorFormat(colorpool.numGreen());
	String bluePool = BLUE + colorFormat(colorpool.numBlue());

	String redPoolColored = ColorHelper.coloredString("RED", redPool);
	String greenPoolColored = ColorHelper.coloredString("GREEN", greenPool);
	String bluePoolColored = ColorHelper.coloredString("LIGHTBLUE", bluePool);

	return String.format("%s %s %s ", redPoolColored, greenPoolColored, bluePoolColored);
    }

    //This mehtod formats the number for each color to have a padded zero
    private String colorFormat(int colorNumber) {
	return String.format("%02d", colorNumber);
    }

}
