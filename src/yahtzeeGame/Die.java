package yahtzeeGame;

import java.util.Random;

/**
 * 
 * @author Jacob De La Rosa-Torres, Peter Dunckel
 *
 */

public class Die {

	private int SIDES = 6;
	private int rollValue;
	private boolean rollEnabled;
	
	//Default Constructor
	public Die(){
		rollEnabled = true;
	}

	public void rollDie() {
		Random rand = new Random();
		rollValue = rand.nextInt(SIDES) + 1;
		rollEnabled = false;
	}
	
	public int getRollValue() {
		return rollValue;
	}
	
	public void setRollEnabled(boolean r){
		rollEnabled = r;
	}
	
	public boolean getRollEnabled(){
		return rollEnabled;
	}
}

