package yahtzeeGame;

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
				diceToReroll[index] =1;
			}
			index++;
		}
		rollCount++;
		return diceToReroll;
	}

	@Override
	public int pickCategory(Die[] dice) {
		int indexOfCategory = 0;
		int maxScore =0;
		if(rollCount >= 3){
			
			for(int i=1; i<=6; i++, indexOfCategory++){
				if(scorecard.upperNum(dice, i)>=maxScore 
						&& game.selectedCategories[i-1] != 1){
					maxScore = scorecard.upperNum(dice, i);
					indexOfCategory = i-1;
				};
			}
			
			if(scorecard.ofAKind(dice, 3)){
				if(scorecard.totalDice(dice) >= maxScore
						&& game.selectedCategories[6] != 1){
					maxScore = scorecard.totalDice(dice);
					indexOfCategory = 6;
				}
			}
			
			if(scorecard.ofAKind(dice, 4)){
				if(scorecard.totalDice(dice) >= maxScore
						&& game.selectedCategories[7] != 1){
					maxScore = scorecard.totalDice(dice);
					indexOfCategory = 7;
				}
			}
			
			if(scorecard.isfullHouse(dice)){
				if(25 >= maxScore
						&& game.selectedCategories[8] != 1){
					maxScore = 25;
					indexOfCategory = 8;
				}
			}
			
			if(scorecard.isStraight(dice, 4)){
				if(30 >= maxScore
						&& game.selectedCategories[9] != 1){
					maxScore = 30;
					indexOfCategory = 9;
				}
			}
			
			if(scorecard.isStraight(dice, 5)){
				if(40 >= maxScore
						&& game.selectedCategories[10] != 1){
					maxScore = 40;
					indexOfCategory = 10;
				}
			}
			
			if(scorecard.yahtzee(dice)
					&& game.selectedCategories[11] != 1){
				if(40 >= maxScore
						&& game.selectedCategories[10] != 1){
					maxScore = 50;
				indexOfCategory = 11;
				}
			}
			
			if(scorecard.chance()){
				if(scorecard.totalDice(dice) >= maxScore
						&& game.selectedCategories[12] != 1){
					maxScore = scorecard.totalDice(dice);
					indexOfCategory = 12;
				}
			}
			
			rollCount =0;
		}	
		return indexOfCategory;
	}
	

}
