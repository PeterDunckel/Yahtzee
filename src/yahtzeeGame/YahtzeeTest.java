package yahtzeeGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class YahtzeeTest {
	
	public OfAKinderStrategy ofAKinder = new OfAKinderStrategy();
	
	@Test
	public void testPickDiceToRoll(){
		
		Die[] dice = new Die[5];
		Die die1 = new Die();
		Die die2 = new Die();
		Die die3 = new Die();
		Die die4 = new Die();
		Die die5 = new Die();
		die1.setRollValue(1);
		dice[0] = die1;
		die2.setRollValue(2);
		dice[1] = die2;
		die3.setRollValue(3);
		dice[2] = die3;
		die4.setRollValue(4);
		dice[3] = die4;
		die5.setRollValue(5);
		dice[4] = die5;
		
		int[] picked = {1,1,1,1,1};
		
		assertArrayEquals(picked, ofAKinder.pickDiceToRoll(dice));
		
	}

}