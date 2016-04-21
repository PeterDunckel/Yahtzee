package yahtzeeGame;

/**
 * 
 * @author Jacob De La Rosa-Torres
 *
 */

import gameMVC.Game;

import java.util.Random;

import scorecardMVC.ScoreCard;

public class RandomStrategy implements Strategy{
	
	ScoreCard scorecard = new ScoreCard();
	Game game = Game.getGameSingleton();

	@Override
	public int[] pickDiceToRoll(Die[] dice) {
		
		// use random number generator to choose dice to roll
		
		int[] picked = new int[5];
		
		for(int i = 0; i < dice.length; i++){
			
			Random rand = new Random();
			picked[i] = rand.nextInt(2);
		}
		
		return picked;
	}

	@Override
	public int pickCategory(Die[] dice) {
		
		Random rand = new Random();
		int num = rand.nextInt(13);
		boolean isCategoryTaken = true;
		boolean isCategoryScore = true;
		
		// check if category is taken		
		do{
			num = rand.nextInt(13);
			
			if(num <= 5){
				isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getUpperSection()[num] >= 0;
			}else{	
				if((num == 12)){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
				}
				
				if((num == 11) && scorecard.yahtzee(dice)){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
				}else if(num == 11){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
					isCategoryScore = false;					
				}
				
				if((num == 10) && scorecard.isStraight(dice, 5)){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
				}else if(num == 10){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
					isCategoryScore = false;					
				}
				
				if((num == 9) && scorecard.isStraight(dice, 4)){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
				}else if(num == 9){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
					isCategoryScore = false;					
				}
				
				if((num == 8) && scorecard.isfullHouse(dice)){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
				}else if(num == 8){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
					isCategoryScore = false;					
				}
				
				if((num == 7) && scorecard.ofAKind(dice, 4)){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
				}else if(num == 7){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
					isCategoryScore = false;					
				}
				
				if((num == 6) && scorecard.ofAKind(dice, 3)){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
				}else if(num == 6){
					isCategoryTaken = game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[num-6] >= 0;
					isCategoryScore = false;					
				}				
			}
			
			if(!isCategoryTaken && !isCategoryScore){
				num = setIdxCtgryToLwstSec();
			}
			
		}while(isCategoryTaken);
		System.out.println("Random Category: "+ num);
		return num;
	}
	
	private int setIdxCtgryToLwstSec() {
		for(int i=0; i<=5; i++){
			if(game.getPlayers().get(game.currentTurn).scoreCard.getUpperSection()[i] < 0){
				return -1*(i-1);
			}
		}
		for(int i=0; i<=6; i++){
			if(game.getPlayers().get(game.currentTurn).scoreCard.getLowerSection()[i] < 0){
				return (i+7)*-1;
			}
		}
		System.out.println("Error: Catagory not selected!");
		return 999;
	}

	@Override
	public String getStrategyName() {
		return "Random Strategy";
	}
}
