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
				if(scorecard.upperNum(dice, i)>=maxScore){
					indexOfCategory = i-1;
				};
			}
			
			if(scorecard.ofAKind(dice, 3)){
				if(scorecard.totalDice(dice) >= maxScore){
					indexOfCategory = 6;
				}
			}
			
			if(scorecard.ofAKind(dice, 4)){
				if(scorecard.totalDice(dice) >= maxScore){
					indexOfCategory = 7;
				}
			}
			
			if(scorecard.isfullHouse(dice)){
				if(scorecard.totalDice(dice) >= maxScore){
					indexOfCategory = 8;
				}
			}
			
			if(scorecard.isStraight(dice, 4)){
				if(scorecard.totalDice(dice) >= maxScore){
					indexOfCategory = 9;
				}
			}
			
			if(scorecard.isStraight(dice, 5)){
				if(scorecard.totalDice(dice) >= maxScore){
					indexOfCategory = 10;
				}
			}
			
			if(scorecard.yahtzee(dice)){
				indexOfCategory = 11;
			}
			
			rollCount =0;
		}	
		return indexOfCategory;
	}
	

}
