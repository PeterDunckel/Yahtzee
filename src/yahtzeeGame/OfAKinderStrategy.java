package yahtzeeGame;

public class OfAKinderStrategy implements Strategy {

	@Override
	public int[] pickDiceToRoll(Die[] dice) {
		
		int[] picked = {1,1,1,1,1};
		
		int[] ofKind = new int[6];
		
		int index = 0;
		for(Die d : dice){
			
			if(d.getRollValue() == 1){
				ofKind[0]++;
				if(ofKind[0] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 2){
				ofKind[1]++;
				if(ofKind[1] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 3){
				ofKind[2]++;
				if(ofKind[2] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 4){
				ofKind[3]++;
				if(ofKind[3] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 5){
				ofKind[4]++;
				if(ofKind[4] >= 2)
					picked[index] = 0;
			} else if(d.getRollValue() == 6){
				ofKind[5]++;
				if(ofKind[5] >= 2)
					picked[index] = 0;
			}
			index++;
		}

		return picked;

	}

	@Override
	public int pickCategory(Die[] dice) {
		
		
		
		
		return 0;
	}
}
