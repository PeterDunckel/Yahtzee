package yahtzeeGame;

public class OfAKinderStrategy implements Strategy {
	
	static int rollCount = 0;
	ScoreCard scorecard = new ScoreCard();
	Game game = Game.getGameSingleton();
	
	@Override
	public int[] pickDiceToRoll(Die[] dice) {
		
		int[] picked = {1,1,1,1,1};
		
		int[] ofKind = new int[6];
		
		int index = 0;
		for(Die d : dice){
			
			if(d.getRollValue() == 1){
				ofKind[0]++;
				if(ofKind[0] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 2){
				ofKind[1]++;
				if(ofKind[1] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 3){
				ofKind[2]++;
				if(ofKind[2] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 4){
				ofKind[3]++;
				if(ofKind[3] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 5){
				ofKind[4]++;
				if(ofKind[4] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 6){
				ofKind[5]++;
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
		if(rollCount >= 3){
			
			for(int i=1; i<=6; i++, indexOfCategory++){
				if(scorecard.upperNum(dice, i)>=maxScore 
						&& game.players.get(game.currentTurn).selectedCategories[i-1] != 1){
					maxScore = scorecard.upperNum(dice, i);
					indexOfCategory = i-1;
				};
			}
			
			if(scorecard.ofAKind(dice, 3)){
				if(scorecard.totalDice(dice) >= maxScore
						&& game.players.get(game.currentTurn).selectedCategories[6] != 1){
					maxScore = scorecard.totalDice(dice);
					indexOfCategory = 6;
				}
			}
			
			if(scorecard.ofAKind(dice, 4)){
				if(scorecard.totalDice(dice) >= maxScore
						&& game.players.get(game.currentTurn).selectedCategories[7] != 1){
					maxScore = scorecard.totalDice(dice);
					indexOfCategory = 7;
				}
			}
			
			if(scorecard.isfullHouse(dice)){
				if(25 >= maxScore
						&& game.players.get(game.currentTurn).selectedCategories[8] != 1){
					maxScore = 25;
					indexOfCategory = 8;
				}
			}
			
			if(scorecard.isStraight(dice, 4)){
				if(30 >= maxScore
						&& game.players.get(game.currentTurn).selectedCategories[9] != 1){
					maxScore = 30;
					indexOfCategory = 9;
				}
			}
			
			if(scorecard.isStraight(dice, 5)){
				if(40 >= maxScore
						&& game.players.get(game.currentTurn).selectedCategories[10] != 1){
					maxScore = 40;
					indexOfCategory = 10;
				}
			}
			
			if(scorecard.yahtzee(dice)
					&& game.players.get(game.currentTurn).selectedCategories[11] != 1){
				if(40 >= maxScore
						&& game.players.get(game.currentTurn).selectedCategories[10] != 1){
					maxScore = 50;
				indexOfCategory = 11;
				}
			}
			
			if(scorecard.chance()){
				if(scorecard.totalDice(dice) >= maxScore
						&& game.players.get(game.currentTurn).selectedCategories[12] != 1){
					maxScore = scorecard.totalDice(dice);
					indexOfCategory = 12;
				}
			}
			
			rollCount = 0;
		}	
		return indexOfCategory;

	}
}
