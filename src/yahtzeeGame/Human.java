package yahtzeeGame;

public class Human extends Player{

	public ScoreCard scoreCard;
	private String name;
	
	public Human(String name){
		this.name = name;
		scoreCard = new ScoreCard();
	}


	public String getName(){
		return name;
	}
	
}
