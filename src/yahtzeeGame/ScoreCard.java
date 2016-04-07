package yahtzeeGame;

public class ScoreCard {

	private int[] upperSection = new int[6];
	private int upperScore;
	private final int BONUS = 35;
	
	private int[] lowerSection = new int[7];
	private int lowerScore;
	private int totalScore;
	
	public ScoreCard(){}
	
	public void addScoreToUpperSection(int score, int pos){
		upperSection[pos] = score;
	}
	
	public void addScoreToLowerSection(int score, int pos){
		lowerSection[pos] = score;
	}
	
	public int[] getUpperSection(){
		return upperSection;
	}
	
	public int[] getLowerSection(){
		return lowerSection;
	}
	
	public int getUpperScore(){
		return upperScore;
	}
	
	public int getLowerScore(){
		return lowerScore;
	}
	
	public int getTotalScore(){
		return totalScore;
	}
	
	public int getTotalUpperScore(){
		int sum = 0;
		
		for(int s : upperSection){
			sum += s;
		}
		
		if(sum >= 63){
			return upperScore + BONUS;
		}
		return upperScore;
	}
	
	//----------------------------------
	//	Mark - Check Rules
	//----------------------------------
	
	
	// CHECK UPPER SECTION ACE-6 
	
	public int upperNum(Die[] dice, int num){
		
		int total = 0;
		
		for(Die d : dice){
			if(d.getRollValue() == num){
				total += num;
			}
		}
		
		return total;
	}
	
	// CHECK 3 & 4 OF A KIND
	
	public boolean ofAKind(Die[] dice, int kind){
		
		int[] ofKind = new int[6];
		
		for(Die d : dice){
			
			if(d.getRollValue() == 1){
				ofKind[0]++;
			} else if(d.getRollValue() == 2){
				ofKind[1]++;
			} else if(d.getRollValue() == 3){
				ofKind[2]++;
			} else if(d.getRollValue() == 4){
				ofKind[3]++;
			} else if(d.getRollValue() == 5){
				ofKind[4]++;
			} else if(d.getRollValue() == 6){
				ofKind[5]++;
			}
		}
		
		return (ofKind[0] >= kind || ofKind[1] >= kind || ofKind[2] >= kind || ofKind[3] >= kind || ofKind[4] >= kind || ofKind[5] >= kind);
	}
	
	// CHECK FOR FULL HOUSE
	
	public boolean isfullHouse(Die[] dice){
		
		
		
		return false;
	}
	
	// CHECK FOR FULL HOUSE
	
	public boolean isStraight(Die[] dice, int n){
			
			
			
		return false;
	}
	
	
	
}
