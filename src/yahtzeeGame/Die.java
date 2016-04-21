package yahtzeeGame;

import java.util.Random;

/**
 * 
 * @author Jacob De La Rosa-Torres, Peter Dunckel, Forrest Collins
 *
 */

public class Die {

	private int SIDES = 6;
	private int rollValue;
	
	private DiceStatus status;
	
	//Default Constructor
	public Die(){
//		rollEnabled = true;
		status = DiceStatus.DICE_ENABLED;
	}
	
	//---------
	// Roll Die
	//---------
	public void rollDie() {
		Random rand = new Random();
		rollValue = rand.nextInt(SIDES) + 1;
//		rollEnabled = false;
		status = DiceStatus.DICE_DISABLED;
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
	public void setRollEnabled(DiceStatus status){
		this.status = status;
	}
	
	//-----------------
	// Get Roll Enabled
	//-----------------
	public DiceStatus getRollEnabled(){
		return status;
	}
	
	public void setRollValue(int value){
		rollValue = value;
	}
}

