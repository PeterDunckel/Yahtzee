package yahtzeeGame;

import gameMVC.Game;

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
		
		Game game = Game.getGameSingleton();
		
		Random rand = new Random();
		int num = rand.nextInt(13);
		
		// check if category is taken
		while(game.players.get(game.currentTurn).selectedCategories[num] == 1){
			num = rand.nextInt(13);
		}
		
		return num;
	}
}
