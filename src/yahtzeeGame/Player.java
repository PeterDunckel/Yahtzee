package yahtzeeGame;

public class Player {

	private String name;
	public ScoreCard scoreCard;
	public int[] selectedCategories = new int[13];
	
	public Player(String name){
		this.name = name;
		scoreCard = new ScoreCard();
	}

	public String getName(){
		return name;
	}
}
