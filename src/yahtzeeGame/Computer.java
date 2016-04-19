package yahtzeeGame;

import java.util.Random;

import gameMVC.Game;
import scorecardMVC.ScoreCard;

public class Computer extends Player{

	private static int numOfComputers = 0;
	private Strategy strategy;
	
	private Game game = Game.getGameSingleton();
	
	public Computer(Strategy strategy){
		
		super();
		this.strategy = strategy;
		numOfComputers++;
		this.name = "Computer " + numOfComputers;
	}
	
	public void notifyTurn(){
		
		game.rollDice();
		hand = game.getDice();
		
		pickStrategy(hand);
		rollBasedOffStrategy();
	}
	
	
	
		//--------------
		// Pick Strategy
		//--------------
		private void pickStrategy(Die[] dice) {

			
			Strategy[] eitherOfAKinderOrUpperStrat = {new UpperSectionerStrategy(), new OfAKinderStrategy()};
			
			int rnd = new Random().nextInt(eitherOfAKinderOrUpperStrat.length);
			
			// roll once then determine which strategy is best to pick
			// four of a kind test
			int[] diceToKeep = new int[5];
			int index=0;
			
			boolean isOfKind = false;
			
			for(Die d : dice){
				if(d.getRollValue() >= 4){
					//Set values to indexes of dice to reroll to 1
					diceToKeep[index] = 1;
				}
				index++;
			}
			
			// ofAKindTest dice value one's - six's
			int[] ofKind = new int[6];
			
			for(Die d : dice){
				
				if(d.getRollValue() == 1){
					ofKind[0]++;
					if (ofKind[0] == 2) {
						isOfKind = true;
					}
				} else if(d.getRollValue() == 2){
					ofKind[1]++;
					if (ofKind[1] == 2) {
						isOfKind = true;
					}
				} else if(d.getRollValue() == 3){
					ofKind[2]++;
					if (ofKind[2] == 2) {
						isOfKind = true;
					}
				} else if(d.getRollValue() == 4){
					ofKind[3]++;
					if (ofKind[3] == 2) {
						isOfKind = true;
					}
				} else if(d.getRollValue() == 5){
					ofKind[4]++;
					if (ofKind[4] == 2) {
						isOfKind = true;
					}
				} else if(d.getRollValue() == 6){
					ofKind[5]++;
					if (ofKind[5] == 2) {
						isOfKind = true;
					}
				}
			}
			
			// if there are 2, use OfAKind or UpperSectioner strategy
			// if there is 3 or more for four of a kind, pick this strategy
			// else use random
			if (diceToKeep.length >= 3) {
				// do fourAndUp
				strategy = new FourAndUpStrategy();
				System.out.println("Computer is using Four and Up Strategy");
			} else if (isOfKind == true) {
				// do ofAKind or UpperSectioner
				strategy = eitherOfAKinderOrUpperStrat[rnd];
				
				if (eitherOfAKinderOrUpperStrat[rnd].getClass() == OfAKinderStrategy.class) {
					System.out.println("Computer used of-A-Kinder Strategy");
				} else {
					System.out.println("Computer used Upper Sectioner Strategy");
				}
			} else {
				// do random
				strategy = new RandomStrategy();
				System.out.println("Computer is using Random Strategy");
			}
			
			
		}
	
	private void rollBasedOffStrategy(){
		
		if(game.getRollCount() != 3){
		
			int[] picked = strategy.pickDiceToRoll(hand);

			for (int i = 0; i < picked.length; i++){
				if(picked[i] == 1){
					game.enableDice(i);
				}
			}

			game.rollDice();
			hand = game.getDice();
			rollBasedOffStrategy();
		} else {
			notifyScorecard(strategy.pickCategory(hand));
			//scoreCard.setUpperSection(strategy.pickCategory(hand), );
		}
	}
	
	private void notifyScorecard(int pos){
		if(pos <= 5){
			scGUI.setUpdateUpperLabels(pos);
			//scoreCard.setUpperSection(pos, scoreCard.upperNum(hand, pos + 1));
		} else {
			scGUI.setUpdateLowerLabels(pos);
		}
		
	}
		
	private Strategy getStrategy() {
		return strategy;
	}

	private void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public int[] pickDiceToRoll(){
		return strategy.pickDiceToRoll(hand);
	};
	
	public int pickCategory(Die[] dice){
		return strategy.pickCategory(dice);
	};
	
}
