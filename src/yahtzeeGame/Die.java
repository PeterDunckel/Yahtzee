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
	
	//---------
	// Roll Die
	//---------
	public void rollDie() {
		Random rand = new Random();
		rollValue = rand.nextInt(SIDES) + 1;
		rollEnabled = false;
	}
	
	//---------------
	// Get Roll Value
	//---------------
	public int getRollValue() {
		return rollValue;
	}
	
	//-----------------
	// Set Roll Enabled
	//-----------------
	public void setRollEnabled(boolean r){
		rollEnabled = r;
	}
	
	//-----------------
	// Get Roll Enabled
	//-----------------
	public boolean getRollEnabled(){
		return rollEnabled;
	}
	
	public void setRollValue(int value){
		rollValue = value;
	}
}

