package yahtzeeGame;

/**
 * 
 * @author Jacob De La Rosa-Torres, Forrest Collins
 *
 */

import gameMVC.Game;
import gameMVC.GameController;
import gameMVC.GameGUI;

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

					GameGUI gameGUI = new GameGUI();
					//gameGUI.setVisible(true);
					
					//Game game = Game.getGameSingleton();
					
					//GameController gameController = new GameController(gameGUI, game);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
