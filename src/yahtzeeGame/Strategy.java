package yahtzeeGame;

/**
 * 
 * @author Jacob De La Rosa-Torres
 *
 */

public interface Strategy {
	
	public int[] pickDiceToRoll(Die[] dice);
	public int pickCategory(Die[] dice);
	public String getStrategyName();
}
