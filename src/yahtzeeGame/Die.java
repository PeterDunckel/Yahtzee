package yahtzeeGame;

import java.util.Random;

public class Die {

	private int amtSides;
	private int rollVal;
	
	//Default Constructor
	public Die(){};	
	
	//Set sides constructor
	public Die(int amtS) {
		setAmtSides(amtS);
	}

	//Roll a die, randomly roll an amount between the min and max of die
	public void rollDie() {
		Random rand = new Random();
		if(amtSides > 1)
			rollVal = rand.nextInt(amtSides) + 1;
	}
	
	//Get and set functions	
	public int getAmtSides() {
		return amtSides;
	}
	
	public int getRollVal() {
		return rollVal;
	}
	
	public void setAmtSides(int amtSides) {
		if(amtSides < 2)
			this.amtSides = 2;
		else
			this.amtSides = amtSides;
	}	

	
}
