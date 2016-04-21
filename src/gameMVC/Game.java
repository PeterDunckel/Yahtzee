package gameMVC;

import java.awt.Color;
import java.util.*;

import javax.swing.JOptionPane;

import yahtzeeGame.Computer;
import yahtzeeGame.DiceStatus;
import yahtzeeGame.Die;
import yahtzeeGame.Human;
import yahtzeeGame.Player;
import yahtzeeGame.RandomStrategy;

/**
 * 
 * @author Jacob De La Rosa-Torres
 *
 */

public class Game {
	
	// Singleton implementation
	private static Game game = new Game();
	private static GameController gameController;
	private Die[] dice = new Die[5];
	private ArrayList<Player> players = new ArrayList<Player>();
	public int currentTurn;
	public static boolean isNextTurn; //for reseting the gameGUI when next players turn
	private int rollCount;
	private int currentRound;
	private int numOfPlayers = 0;
	
	private Game(){
		isNextTurn = false;
		loadDice();
		currentTurn = 0;
		rollCount = 0;
	}
	
	// Singleton getter
	public static Game getGameSingleton(){
		return game;
	}
	
	public void setController(GameController gc){
		gameController = gc;
	}
	
	//----------
	// Roll Dice
	//----------
	public void rollDice(){
		
		for(Die d: dice){
			
			if ((boolean)(d.getRollEnabled() == DiceStatus.DICE_ENABLED)){
				d.rollDie();
				System.out.println(d.getRollValue());
			}
			
			d.setRollEnabled(DiceStatus.DICE_ENABLED);
		}
		
		rollCount++;
	}

	//----------
	// Load Dice
	//----------
	private void loadDice(){
		
		for(int i = 0; i < dice.length; i++){
			Die d = new Die();
			dice[i] = d;
		}
	}
	
	public Die[] getDice(){
		return dice;
	}
	
	public boolean enableDice(int d){
		
		if ((boolean)(dice[d].getRollEnabled() == DiceStatus.DICE_ENABLED)){
			dice[d].setRollEnabled(DiceStatus.DICE_DISABLED);
		} else {
			dice[d].setRollEnabled(DiceStatus.DICE_ENABLED);
		}
		
		return (boolean)(dice[d].getRollEnabled() == DiceStatus.DICE_ENABLED);
	}
	
	public int getRollCount(){
		return rollCount;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public void addHuman(String name){
		
		Player human = new Human(name);
		players.add(human);
		human.initGUI();
		//players.get(numOfPlayers).initGUI();
		numOfPlayers++;
	}
	
	public void addComputer(){
		
		Player computer = new Computer(new RandomStrategy());
		players.add(computer);
		computer.initGUI();
		//players.get(numOfPlayers).initGUI();
		numOfPlayers++;
	}
	
	public int getNumOfPlayers(){
		return numOfPlayers;
	}
	
	public void changeTurn(){
		
		if(currentRound >= 13 && currentTurn == (players.size()-1)){
			checkForWinner();
		} else {
			currentTurn++;

			if(currentTurn >= players.size()){
				currentTurn = 0;
				currentRound++;
			}
			
			rollCount = 0;
			gameController.resetGUI();
			
			if(gameController.getCurrentPlayer().getClass() == Computer.class){
				
				((Computer) gameController.getCurrentPlayer()).notifyTurn();
				
			}
		}	
		
	}
	
	public void checkForWinner(){
		
		if(players.size() > 0){

			int highestScore = 0;
			Player winner = players.get(0);

			for(Player p : players){

				if(p.scoreCard.getTotalScore() > highestScore){
					highestScore = p.scoreCard.getTotalScore();
					winner = p;
				}
			}
			
			if(game.getPlayers().size() > 1){
				JOptionPane.showMessageDialog(null, "Congratulations "+winner.getName()+"! You won!");
			}else{
				JOptionPane.showMessageDialog(null, "GAME OVER!");
			}
		} 
	}

}
