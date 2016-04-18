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

	static int rollCount = 0;
	ScoreCard scorecard = new ScoreCard();
	Game game = Game.getGameSingleton();
	
	@Override
	public int[] pickDiceToRoll(Die[] dice) {
		
		// these are the five dice
		int[] picked = new int[5];
		picked[0] = 1;
		picked[1] = 1;
		picked[2] = 1;
		picked[3] = 1;
		picked[4] = 1;
		
		// this show how much of each number dice is there
		// ex ofKind[1] = 4, means there are 4 two's
		int[] ofKind = new int[6];
		
		
		// increment ofKind when getting the roll value of dice
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
		
		// set the picked index if a dice value is repeated at least twice
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

		return picked; // returns the picked value and will reroll all others

	}

	@Override
	public int pickCategory(Die[] dice) {
		
		int indexOfCategory = 0;
		int maxScore = 0;
		
		// if you rolled 3 times, pick a category
		if(rollCount >= 3){
			
			// if there are any Upper Section categories left (such as Aces, Twos, etc.), 
			// place into the Upper Section category
			if(scorecard.upperNum(dice, 1)>=maxScore 
					&& game.players.get(game.currentTurn).selectedCategories[1-1] != 1){
				maxScore = scorecard.upperNum(dice, 1);
				indexOfCategory = 1-1;
			} else if(scorecard.upperNum(dice, 2)>=maxScore 
					&& game.players.get(game.currentTurn).selectedCategories[2-1] != 1){
				maxScore = scorecard.upperNum(dice, 2);
				indexOfCategory = 2-1;
			} else if(scorecard.upperNum(dice, 3)>=maxScore 
					&& game.players.get(game.currentTurn).selectedCategories[3-1] != 1){
				maxScore = scorecard.upperNum(dice, 3);
				indexOfCategory = 3-1;
			} else if(scorecard.upperNum(dice, 4)>=maxScore 
					&& game.players.get(game.currentTurn).selectedCategories[4-1] != 1){
				maxScore = scorecard.upperNum(dice, 4);
				indexOfCategory = 4-1;
			} else if(scorecard.upperNum(dice, 5)>=maxScore 
					&& game.players.get(game.currentTurn).selectedCategories [5-1] != 1){
				maxScore = scorecard.upperNum(dice, 5);
				indexOfCategory = 5-1;
			} else if(scorecard.upperNum(dice, 6)>=maxScore 
					&& game.players.get(game.currentTurn).selectedCategories[6-1] != 1){
				maxScore = scorecard.upperNum(dice, 6);
				indexOfCategory = 6-1;
			} else {
		
				// If no Upper Section categories remain, choose the remaining 
				// category that provides the maximum score for that hand.
			
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
			}
			
			rollCount = 0;
		}	
		return indexOfCategory;

	}
	
	public static void main(String[] args) {
		
		UpperSectionerStrategy upperSectioner = new UpperSectionerStrategy();
		
		Die[] dice = new Die[5];
		Die die1 = new Die();
		Die die2 = new Die();
		Die die3 = new Die();
		Die die4 = new Die();
		Die die5 = new Die();
		die1.setRollValue(1);
		dice[0] = die1;
		die2.setRollValue(2);
		dice[1] = die2;
		die3.setRollValue(6);
		dice[2] = die3;
		die4.setRollValue(2);
		dice[3] = die4;
		die5.setRollValue(5);
		dice[4] = die5;
		
		//int[] picked = {0,1,1,0,1};
		int[] picked = upperSectioner.pickDiceToRoll(dice);
		
		for(int val : picked){
			System.out.println(val);
		}
	}

}
