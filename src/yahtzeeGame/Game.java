package yahtzeeGame;

import java.util.*;

/**
 * 
 * @author Jacob De La Rosa-Torres
 *
 */

public class Game {
	
	public Die[] dice = new Die[5];
	
	public Game(){
		
		loadDice();
		
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
