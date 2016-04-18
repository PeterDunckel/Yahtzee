package yahtzeeGame;

public class Player {

	private String name;
	private boolean isHuman;
	public ScoreCard scoreCard;
	public int[] selectedCategories = new int[13];
	
	public Player(String name){
		this.name = name;
		scoreCard = new ScoreCard();
	}

	public String getName(){
		return name;
	}

	public boolean getIsHuman() {
		return isHuman;
	}

	public void setIsHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}
}
