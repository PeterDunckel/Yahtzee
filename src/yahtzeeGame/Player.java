package yahtzeeGame;

public class Player {

	private String name;
	public ScoreCard scoreCard;
	
	public Player(String name){
		this.name = name;
		scoreCard = new ScoreCard();
	}

	public String getName(){
		return name;
	}
}
