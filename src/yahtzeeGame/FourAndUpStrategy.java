package yahtzeeGame;

import scorecardMVC.ScoreCard;
import gameMVC.Game;

public class FourAndUpStrategy implements Strategy{
	
	//When roll count(number of rolls)
	//equals 3, then we pick category 
	//that yields highest score 
	static int rollCount = 0;
	
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
		rollCount++;
		return diceToReroll;
	}

	@Override
	public int pickCategory(Die[] dice) {
		
		int indexOfCategory = 0;
		int maxScore = 0;
		if(rollCount >= 3){
			
			for(int i=1; i<=6; i++, indexOfCategory++){
				if(scorecard.upperNum(dice, i)>=maxScore 
						&& game.getPlayers().get(game.currentTurn).scoreCard.getUpperSection()[i-1] < 0){
					maxScore = scorecard.upperNum(dice, i);
					indexOfCategory = i-1;
				};
			}
			
			if(scorecard.ofAKind(dice, 3)){
				if(scorecard.totalDice(dice) >= maxScore
						&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[0] < 0){
					maxScore = scorecard.totalDice(dice);
					indexOfCategory = 6;
				}
			}
			
			if(scorecard.ofAKind(dice, 4)){
				if(scorecard.totalDice(dice) >= maxScore
						&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[1] < 0){
					maxScore = scorecard.totalDice(dice);
					indexOfCategory = 7;
				}
			}
			
			if(scorecard.isfullHouse(dice)){
				if(25 >= maxScore
						&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[2] < 0){
					maxScore = 25;
					indexOfCategory = 8;
				}
			}
			
			if(scorecard.isStraight(dice, 4)){
				if(30 >= maxScore
						&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[3] < 0){
					maxScore = 30;
					indexOfCategory = 9;
				}
			}
			
			if(scorecard.isStraight(dice, 5)){
				if(40 >= maxScore
						&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[4] < 0){
					maxScore = 40;
					indexOfCategory = 10;
				}
			}
			
			if(scorecard.yahtzee(dice) && game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[5] < 0){
				if(maxScore <= 50){
					maxScore = 50;
				indexOfCategory = 11;
				}
			}
			
			if(scorecard.chance()){
				if(scorecard.totalDice(dice) >= maxScore
						&& game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[6] < 0){
					maxScore = scorecard.totalDice(dice);
					indexOfCategory = 12;
				}
			}
			
			rollCount = 0;
		}	
		return indexOfCategory;

	}
	

}
