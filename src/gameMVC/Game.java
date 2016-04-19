package gameMVC;

import java.awt.Color;
import java.util.*;

import javax.swing.JOptionPane;

import yahtzeeGame.Computer;
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
			
			if (d.getRollEnabled()){
				d.rollDie();
				System.out.println(d.getRollValue());
			}
			
			d.setRollEnabled(true);
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
		
		if (dice[d].getRollEnabled()){
			dice[d].setRollEnabled(false);
		} else {
			dice[d].setRollEnabled(true);
		}
		
		return dice[d].getRollEnabled();
	}
	
	public int getRollCount(){
		return rollCount;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public void addHuman(String name){
		
		players.add(new Human(name));
		players.get(numOfPlayers).initGUI();
		numOfPlayers++;
	}
	
	public void addComputer(){
		
		players.add(new Computer(new RandomStrategy()));
		numOfPlayers++;
	}
	
	public int getNumOfPlayers(){
		return numOfPlayers;
	}
	
	public void changeTurn(){
		
		if(currentRound == 13 && currentTurn == players.size()){
			checkForWinner();
		} else {
			currentTurn++;

			if(currentTurn >= players.size()){
				currentTurn = 0;
				currentRound++;
			}
		}
		
		rollCount = 0;
		gameController.resetGUI();
		
		if(gameController.getCurrentPlayer().getClass() == Computer.class){
			
			((Computer) gameController.getCurrentPlayer()).notifyTurn();
			
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
			
			JOptionPane.showMessageDialog(null, "Congratulations "+winner.getName()+"! You won!");
		} 
	}

}
