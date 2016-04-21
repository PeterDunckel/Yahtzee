package yahtzeeGame;

/**
 * 
 * @author Jacob De La Rosa-Torres
 *
 */

import scorecardMVC.ScoreCard;
import gameMVC.Game;

public class OfAKinderStrategy implements Strategy {
	
	ScoreCard scorecard = new ScoreCard();
	Game game = Game.getGameSingleton();
	
	@Override
	public int[] pickDiceToRoll(Die[] dice) {
		
		int[] picked = new int[5];
		picked[0] = 1;
		picked[1] = 1;
		picked[2] = 1;
		picked[3] = 1;
		picked[4] = 1;
		
		int[] ofKind = new int[6];
		
		
		for(Die d : dice){
			
			if(d.getRollValue() == 1){
				ofKind[0]++;
			} else if(d.getRollValue() == 2){
				ofKind[1]++;
			} else if(d.getRollValue() == 3){
				ofKind[2]++;
			} else if(d.getRollValue() == 4){
				ofKind[3]++;
			} else if(d.getRollValue() == 5){
				ofKind[4]++;
			} else if(d.getRollValue() == 6){
				ofKind[5]++;
			}
			
		}
		
		int index = 0;
		for(Die d : dice){
			
			if(d.getRollValue() == 1){
				if(ofKind[0] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 2){
				if(ofKind[1] >= 2)
					picked[index] = 0;
			}else if(d.getRollValue() == 3){
				if(ofKind[2] >= 2)
					picked[index] = 0;
			}else if(d.getRollValue() == 4){
				if(ofKind[3] >= 2)
					picked[index] = 0;
			}else if(d.getRollValue() == 5){
				if(ofKind[4] >= 2)
					picked[index] = 0;
			}else if(d.getRollValue() == 6){
				if(ofKind[5] >= 2)
				picked[index] = 0;
			}
			index++;
		}

		return picked;

	}

	@Override
	public int pickCategory(Die[] dice) {
		
		int indexOfCategory = 0;
		int maxScore = 0;
		boolean categoryIsSelected =false;
		if(game.getRollCount() >= 3){
			
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
		
		}	
		if(indexOfCategory == 0 && !categoryIsSelected){
			//Set index of category to a upper category that has not been chosen
			indexOfCategory = setIdxCtgryToLwstSec();
		}
		System.out.println("Of a Kinder Category: "+ indexOfCategory);
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
		return "Of a Kinder Strategy";
	}

}
