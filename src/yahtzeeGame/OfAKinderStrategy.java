package yahtzeeGame;

public class OfAKinderStrategy implements Strategy {

	@Override
	public int[] pickDiceToRoll(Die[] dice) {
		
<<<<<<< Updated upstream
		return null;
=======
		int[] picked = new int[5];
		
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
		
		int index = 0;
		for(Die d : dice){
			
			if(d.getRollValue() == 1){
				if(ofKind[0] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 2){
				if(ofKind[1] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 3){
				if(ofKind[2] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 4){
				if(ofKind[3] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 5){
				if(ofKind[4] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 6){
				if(ofKind[5] >= 2)
					picked[index] = 0;
			} else {
				picked[index] = 1;
			}
			index++;
		}

		return picked;
>>>>>>> Stashed changes
	}

	@Override
	public int pickCategory(Die[] dice) {
		
		return 0;
	}

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
	
}
