public class Rewarder {

    private int firstThres = 0;
    private int firstReward = 0;
    private int secondThres = 0;
    private int secondReward = 0;
    private int thirdThres = 0;
    private int thirdReward = 0;

    // Takes in the first threshold and reward at that threshold.
    // Sets the necessary instance variables; returns nothing.
    public void setThresholdOne(int firstThreshold, int firstThresholdReward) {
	this.firstThres = firstThreshold;
	this.firstReward =  firstThresholdReward;
    }

    // Takes in the second threshold and reward at that threshold.
    // Sets the necessary instance variables; returns nothing.
    public void setThresholdTwo(int secondThreshold, int secondThresholdReward) {
	this.secondThres = secondThreshold;
	this.secondReward = secondThresholdReward;
    }

    // Takes in the third threshold and reward at that threshold.
    // Sets the necessary instance variables; returns nothing.
    public void setThresholdThree(int thirdThreshold, int thirdThresholdReward) {
	this.thirdThres = thirdThreshold;
	this.thirdReward = thirdThresholdReward;
    }

    // Takes in the number of resources you have and returns the reward you get for that number of resources.
    public int reward(int totalNumResources) {

	if (totalNumResources >= thirdThres) {
	    return thirdReward;
	} else if (totalNumResources >= secondThres) {
	    return secondReward;
	} else if (totalNumResources >= firstThres) {
	    return firstReward;
	} else {
	    return 0;
	}
    }
}
