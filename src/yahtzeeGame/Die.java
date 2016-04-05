package yahtzeeGame;

import java.util.Random;

public class Die {

	private int SIDES = 6;
	private int rollValue;
	
	//Default Constructor
	public Die(){};	

	public void rollDie() {
		Random rand = new Random();
		rollValue = rand.nextInt(SIDES) + 1;
	}
	
	public int getRollValue() {
		return rollValue;
	}
}

