package yahtzeeGame;

/**
 * 
 * @author Peter Dunckel
 *
 */

import scorecardMVC.ScoreCard;
import gameMVC.Game;

public class FourAndUpStrategy implements Strategy{
	
	//When roll count(number of rolls)
	//equals 3, then we pick category 
	//that yields highest score 
	
	ScoreCard scorecard = new ScoreCard();
	Game game = Game.getGameSingleton();
	
	@Override
	public int[] pickDiceToRoll(Die[] dice) {
		int[] diceToReroll = new int[5];
		int index=0;
		for(Die d : dice){
			if(d.getRollValue() < 4){
				//Set values to indexes of dice to reroll to 1
				diceToReroll[index] = 1;
			}
			index++;
		}
		return diceToReroll;
	}

	@Override
	public int pickCategory(Die[] dice) {
		
		int indexOfCategory = 0;
		int maxScore = 0;
		boolean categoryIsSelected =false;
			
		if(scorecard.chance()){
			if(scorecard.totalDice(dice) >= maxScore
					&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[6] < 0){
				maxScore = scorecard.totalDice(dice);
				indexOfCategory = 12;
				categoryIsSelected =true;
			}
		}

		for(int i=6; i>=1; i--){
			if(scorecard.upperNum(dice, i)>=maxScore 
					&& game.getPlayers().get(game.currentTurn).scoreCard.getUpperSection()[i-1] < 0){
				maxScore = scorecard.upperNum(dice, i);
				indexOfCategory = i-1;
				categoryIsSelected =true;
			};
		}

		if(scorecard.ofAKind(dice, 3)){
			if(scorecard.totalDice(dice) >= maxScore
					&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[0] < 0){
				maxScore = scorecard.totalDice(dice);
				indexOfCategory = 6;
				categoryIsSelected =true;
			}
		}

		if(scorecard.ofAKind(dice, 4)){
			if(scorecard.totalDice(dice) >= maxScore
					&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[1] < 0){
				maxScore = scorecard.totalDice(dice);
				indexOfCategory = 7;
				categoryIsSelected =true;
			}
		}

		if(scorecard.isfullHouse(dice)){
			if(25 >= maxScore
					&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[2] < 0){
				maxScore = 25;
				indexOfCategory = 8;
				categoryIsSelected =true;
			}
		}

		if(scorecard.isStraight(dice, 4)){
			if(30 >= maxScore
					&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[3] < 0){
				maxScore = 30;
				indexOfCategory = 9;
				categoryIsSelected =true;
			}
		}

		if(scorecard.isStraight(dice, 5)){
			if(40 >= maxScore
					&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[4] < 0){
				maxScore = 40;
				indexOfCategory = 10;
				categoryIsSelected =true;
			}
		}

		if(scorecard.yahtzee(dice) && game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[5] < 0){
			if(maxScore <= 50){
				maxScore = 50;
				indexOfCategory = 11;
				categoryIsSelected =true;
			}
		}
		
			
		if(indexOfCategory == 0 && !categoryIsSelected){
			//Set index of category to a upper category that has not been chosen
			indexOfCategory = setIdxCtgryToLwstSec();
		}
		System.out.println("Four and Up Category: "+ indexOfCategory);
		return indexOfCategory;

	}
	
	private int setIdxCtgryToLwstSec() {
		for(int i=0; i<=5; i++){
			if(game.getPlayers().get(game.currentTurn).scoreCard.getUpperSection()[i] < 0){
				return -1*(i-1);
			}
		}
		for(int i=0; i<=6; i++){
			if(game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[i] < 0){
				return (i+7)*-1;
			}
		}
		System.out.println("Error: Catagory not selected!");
		return 999;
	}

	@Override
	public String getStrategyName() {
		return "Four and Up Strategy";
	}
	

}
