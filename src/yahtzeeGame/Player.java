package yahtzeeGame;

import scorecardMVC.ScoreCard;
import scorecardMVC.ScoreCardController;
import scorecardMVC.ScorecardGUI;

public abstract class Player {

	protected String name;
	private Die[] hand;
	public ScoreCard scoreCard;
	private ScorecardGUI scGUI;
	
	public Player(){
		//scoreCard = new ScoreCard(new ScorecardGUI(name));
		
		scoreCard = new ScoreCard();
		//ScorecardGUI scoreCardGUI = new ScorecardGUI(name);
		
	}
	
	public void initGUI(){
		scGUI = new ScorecardGUI(name);
	}
	
	public void notifyDiceBeenRolled(){
		scGUI.updateGUI();
	}
	
	public String getName(){
		return name;
	}
	
	public void setHand(Die[] dice){
		hand = dice;
	}
	
	public Die[] getHand(){
		return hand;
	}
	
	public void whenWon(){
		
	}
}

