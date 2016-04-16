package yahtzeeGame;

import java.util.*;

/**
 * 
 * @author Jacob De La Rosa-Torres
 *
 */

public class Game {
	
	private static Game game = new Game();
	
	public Die[] dice = new Die[5];
	public ArrayList<Player> players = new ArrayList<Player>();
	public int currentTurn;
	
	private Game(){
		
		loadDice();
		currentTurn = 0;
	}
	
	public static Game getGameSingleton(){
		return game;
	}
	
	public void rollDice(){
		
		for(Die d: dice){
			
			if (d.getRollEnabled()){
				d.rollDie();
				System.out.println(d.getRollValue());
			}
		}
	}

	private void loadDice(){
		
		for(int i = 0; i < dice.length; i++){
			Die d = new Die();
			dice[i] = d;
		}
	}

}
