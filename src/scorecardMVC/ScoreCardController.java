package scorecardMVC;

/**
 * 
 * @author Jacob De La Rosa-Torres
 *
 */

import gameMVC.Game;
import yahtzeeGame.Die;
import yahtzeeGame.Player;

public class ScoreCardController {

	Die[] dice;
	ScoreCard model;
	ScorecardGUI view;
	
	public ScoreCardController(ScorecardGUI v, ScoreCard m){
		Game game = Game.getGameSingleton();
		dice = game.getDice();
		view = v;
		model = m;
	
	}
	
	public void notifyBeenRolled(Die[] dice){
		this.dice = dice;
		view.updateGUI();
	}
	
	public boolean checkUpperSection(int index){
		return (model.upperNum(dice, index) > 0);
	}
	
	public boolean upperSectionHasValue(int index){
		System.out.println("index: " + model.getUpperSection()[index]);
		return (model.getUpperSection()[index] >= 0);
	}
	
	public boolean lowerSectionHasValue(int index){
		return (model.getLowerSection()[index] >= 0);
	}
	
	public boolean checkOfAKind(int kind){
			return (model.ofAKind(dice, kind));
	}
	
	public boolean checkFullHouse(){
		return model.isfullHouse(dice);
	}

	public boolean checkStraight(int n){
		return model.isStraight(dice, n);
	}
	
	public boolean checkYahtzee(){
		return model.yahtzee(dice);
	}
	
	public int totalOfDice(){
		return model.totalDice(dice);
	}
	
	public int upperNum(int index){
		return model.upperNum(dice, index);
	}
	
	public void setUpperSection(int index, int value){
		model.setUpperSection(index, value);
	}
	
	public void setLowerSection(int index, int value){
		model.setLowerSection(index, value);
	}
	
	public int getUpperScore(){
		return model.getUpperScore();
	}
	
	public int getTotalUpperScore(){
		return model.getTotalUpperScore();
	}
	
	public int getLowerScore(){
		return model.getLowerScore();
	}
	
	public int getGrandTotalScore(){
		return model.getTotalScore();
	}
	
	public int getBonus(){
		return model.getBonus();
	}
}
