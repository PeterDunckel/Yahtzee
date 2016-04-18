package yahtzeeGame;

public class Computer extends Player{

	public ScoreCard scoreCard;
	private Strategy strategy;
	
	public Computer(Strategy strategy){
		this.strategy = strategy;	
		scoreCard = new ScoreCard();
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
