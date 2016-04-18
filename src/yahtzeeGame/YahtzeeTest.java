package yahtzeeGame;

/**
 * 
 * @author Jacob De La Rosa-Torres
 *
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class YahtzeeTest {
	
	public OfAKinderStrategy ofAKinder = new OfAKinderStrategy();
	public FourAndUpStrategy fourAndUp = new FourAndUpStrategy();
	
	public ScoreCard scoreCard = new ScoreCard();
	
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
		
		int[] picked = {1,1,1,0,0};
		
		assertArrayEquals(picked, fourAndUp.pickDiceToRoll(dice));
		
	}
	
	@Test
	public void testScoreCard(){
		
		Die[] dice = new Die[5];
		Die die1 = new Die();
		Die die2 = new Die();
		Die die3 = new Die();
		Die die4 = new Die();
		Die die5 = new Die();
		die1.setRollValue(1);
		dice[0] = die1;
		die2.setRollValue(1);
		dice[1] = die2;
		die3.setRollValue(1);
		dice[2] = die3;
		die4.setRollValue(1);
		dice[3] = die4;
		die5.setRollValue(1);
		dice[4] = die5;
		
		assertEquals(5, scoreCard.upperNum(dice, 1));

		dice[0].setRollValue(1);
		dice[1].setRollValue(1);
		dice[2].setRollValue(3);
		dice[3].setRollValue(1);
		dice[4].setRollValue(1);
		
		assertEquals(true, scoreCard.ofAKind(dice, 4));
		
		dice[0].setRollValue(3);
		dice[1].setRollValue(1);
		dice[2].setRollValue(3);
		dice[3].setRollValue(3);
		dice[4].setRollValue(1);
		
		assertEquals(true, scoreCard.isfullHouse(dice));
		
		dice[0].setRollValue(4);
		dice[1].setRollValue(3);
		dice[2].setRollValue(5);
		dice[3].setRollValue(2);
		dice[4].setRollValue(1);
		
		assertEquals(true, scoreCard.isStraight(dice, 5));
		
		dice[0].setRollValue(4);
		dice[1].setRollValue(4);
		dice[2].setRollValue(5);
		dice[3].setRollValue(4);
		dice[4].setRollValue(4);
		
		assertEquals(false, scoreCard.yahtzee(dice));
		
	}

}
