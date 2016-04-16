package yahtzeeGame;

/*
 * Has the same behavior about which dice to keep/roll as an Of-a- Kinder. 
 * But once the hand is done, if there are any Upper Section categories left 
 * (such as Aces, Twos, etc.), he places it into the Upper Section category 
 * that provides the maximum score for that hand. If no Upper Section categories 
 * remain, he will choose the remaining category that provides the maximum score 
 * for that hand.
 */
public class UpperSectionerStrategy implements Strategy {

	@Override
	public int[] pickDiceToRoll(Die[] dice) {
		
		return null;
	}

	@Override
	public int pickCategory(Die[] dice) {
		
		return 0;
	}

}
