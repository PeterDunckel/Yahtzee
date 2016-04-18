package yahtzeeGame;

import java.awt.EventQueue;

import javax.swing.JButton;

public class YahtzeeMain {

	//------------
	// Main Method
	//------------
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GameGUI frame = GameGUI.getGameSingleton();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
