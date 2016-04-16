package yahtzeeGame;

public interface Strategy {

	public int[] pickDiceToRoll(Die[] dice);
	public int[] pickCategory(Die[] dice);
}
