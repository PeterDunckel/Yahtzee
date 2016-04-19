package yahtzeeGame;

import scorecardMVC.ScoreCard;

public class Computer extends Player{

	private static int numOfComputers = 0;
	private Strategy strategy;
	
	public Computer(Strategy strategy){
		
		super();
		this.strategy = strategy;
		numOfComputers++;
		this.name = "Computer " + numOfComputers;
	}
	
	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public int[] pickDiceToRoll(Die[] dice){
		return strategy.pickDiceToRoll(dice);
	};
	
	public int pickCategory(Die[] dice){
		return strategy.pickCategory(dice);
	};
	
}
