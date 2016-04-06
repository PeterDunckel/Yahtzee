package yahtzeeGame;

import java.awt.EventQueue;
import javax.swing.JButton;

public class YahtzeeMain {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI frame = new GameGUI();
					ScorecardGUI scFrame = new ScorecardGUI();
					frame.setVisible(true);
					scFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
