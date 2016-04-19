package gameMVC;

import java.awt.Color;
import java.util.ArrayList;

import yahtzeeGame.Die;
import yahtzeeGame.Player;

public class GameController {

	//private static GameController gameController = null;
	
//	private Game model = Game.getGameSingleton();
//	private GameGUI view = new GameGUI();
	
	private Game model;
	private GameGUI view;
	
	public GameController(GameGUI v, Game m){
		
		// Present the view
		model = m;
		view = v;
		//view.setVisible(true);
		
		// Start new game
		
	}
	
//	public static GameController getInstance(){
//		
//		if(gameController == null){
//			gameController = new GameController();
//		} 
//		return gameController;
//	}
	
	public int getRollCount(){
		return model.getRollCount();
	}
	
	public void rollDice(){
		
		model.rollDice();
		updateView();
	}
	
	public Die[] getDice(){
		return model.getDice();
	}
	
	public void updateView(){
		view.displayDice(getDice());
	}
	
	public void resetGUI(){
		view.resetGUIAfterTurn();
	}
	
	public void addHuman(String name){
		model.addHuman(name);
		
	}
	
	public void addComputer(){
		model.addComputer();
	}
	
	public int getAmountOfPlayers(){
		return model.getNumOfPlayers();
	}
	
	public Player getCurrentPlayer(){
		return model.getPlayers().get(model.currentTurn);
	}
	
	public ArrayList<Player> getPlayers(){
		return model.getPlayers();
	}
	
	public void startGame(){
		
	}
	
	public boolean enableDice(int d){
		return model.enableDice(d);
	}
	
//	public void notifyPlayerDiceRolled(){
//		getCurrentPlayer().scoreCard.notifyController(getDice());
//	}
}
