package yahtzeeGame;

import java.util.*;

/**
 * 
 * @author Jacob De La Rosa-Torres
 *
 */

public class Game {
	
	// Singleton implementation
	private static Game game = new Game();
	
	public Die[] dice = new Die[5];
	public ArrayList<Player> players = new ArrayList<Player>();
	public int currentTurn;
	
	private Game(){
		
		loadDice();
		currentTurn = 0;
	}
	
	// Singleton getter
	public static Game getGameSingleton(){
		return game;
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
		}
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

}
