package yahtzeeGame;

import java.util.Random;

public class RandomStrategy implements Strategy{

	@Override
	public int[] pickDiceToRoll(Die[] dice) {
		
		// use random number generator to choose dice to roll
		
		int[] picked = new int[5];
		
		for(int i = 0; i < dice.length; i++){
			
			Random rand = new Random();
			picked[i] = rand.nextInt(2);
		}
		
		return picked;
	}

	@Override
	public int pickCategory(Die[] dice) {
		
		// randomly choose a category
		
		Random rand = new Random();
		return rand.nextInt(13);
	}
}
