package yahtzeeGame;

public class ScoreCard {

	private int[] upperSection = new int[6];
	private int upperScore;
	private final int BONUS = 35;
	
	private int[] lowerSection = new int[7];
	private int lowerScore;
	private int totalScore;
	
	public ScoreCard(){
		lowerSection[6] = -1;
	}
	
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
		
		int valueA = dice[0].getRollValue();
		int valueB = 0;
		int a = 0, b = 0;
		
		for(int i = 1; i < 5; i++){
			
			if(valueB == 0 && dice[i].getRollValue() != valueA){
				valueB = dice[i].getRollValue();
			} else if(dice[i].getRollValue() == valueA){
				a++;
			} else if(dice[i].getRollValue() == valueB){
				b++;
			}
			
		}
		return (a == 1 && b == 2) || (a == 2 && b == 1);
	}
	
	// CHECK FOR FULL HOUSE
	
	public boolean isStraight(Die[] dice, int n){
			
		int one = 0, two = 0, three = 0, four = 0, five = 0, six = 0;
		
		for (Die d : dice){
			if(d.getRollValue() == 1)
				one++;
			else if(d.getRollValue() == 2)
				two++;
			else if(d.getRollValue() == 3)
				three++;
			else if(d.getRollValue() == 4)
				four++;
			else if(d.getRollValue() == 5)
				five++;
			else if(d.getRollValue() == 6)
				six++;
		}
		
		if(n == 4){
			return (one >= 1 && two >= 1 && three >= 1 && four >= 1 ||
					two >= 1 && three >= 1 && four >= 1 && five >= 1 ||
					three >= 1 && four >= 1 && five >= 1 && six >= 1);
		} else if(n == 5){
			return (one >= 1 && two >= 1 && three >= 1 && four >= 1 && five >=1 ||
					two >= 1 && three >= 1 && four >= 1 && five >= 1 && six >= 1);
		}
		
		return false;
	}
	
	public boolean chance(){
		return lowerSection[6] == -1;
	}
	
}
