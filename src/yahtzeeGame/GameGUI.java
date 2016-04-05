package yahtzeeGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameGUI extends JFrame {

	private JButton dieOne;
	private JButton dieTwo;
	private JButton dieThree;
	private JButton dieFour;
	private JButton dieFive;
	private JButton[] dieButtons = new JButton[5];
	
	private Game game = new Game();
	private static boolean firstRoll = true;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GameGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dieOne = new JButton("?");
		dieOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				enableDiceForRoll(0);
			}
		});
		
		JButton rollDieBtn = new JButton("Roll Dice");
		rollDieBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				game.rollDice();
				displayDice();
			}
		});
		rollDieBtn.setBounds(6, 263, 117, 40);
		contentPane.add(rollDieBtn);
		dieOne.setBounds(6, 6, 50, 50);
		dieOne.setFocusPainted(false);
		contentPane.add(dieOne);
		dieButtons[0] = dieOne;
		
		dieTwo = new JButton("?");
		dieTwo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				enableDiceForRoll(1);
			}
		});
		dieTwo.setBounds(6, 55, 50, 50);
		dieTwo.setFocusPainted(false);
		contentPane.add(dieTwo);
		dieButtons[1] = dieTwo;
		
		dieThree = new JButton("?");
		dieThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				enableDiceForRoll(2);
			}
		});
		dieThree.setBounds(6, 104, 50, 50);
		dieThree.setFocusPainted(false);
		contentPane.add(dieThree);
		dieButtons[2] = dieThree;
		
		dieFour = new JButton("?");
		dieFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				enableDiceForRoll(3);
			}
		});
		dieFour.setBounds(6, 153, 50, 50);
		dieFour.setFocusPainted(false);
		contentPane.add(dieFour);
		dieButtons[3] = dieFour;
		
		dieFive = new JButton("?");
		dieFive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				enableDiceForRoll(4);

			}
		});
		dieFive.setBounds(6, 201, 50, 50);
		dieFive.setFocusPainted(false);
		contentPane.add(dieFive);
		dieButtons[4] = dieFive;
		
		JButton btnSetScore = new JButton("Set Score");
		btnSetScore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//When the dice values are as desired
				//Reset to first roll(Only three rolls each iteration)
				//and set scores
				resetDice();
			}
		});
		btnSetScore.setBounds(312, 263, 89, 40);
		contentPane.add(btnSetScore);
	}
	
	private void enableDiceForRoll(int pos){
		if (game.dice[pos].getRollEnabled()){
			game.dice[pos].setRollEnabled(false);
			dieButtons[pos].setForeground(Color.black);
		} else {
			game.dice[pos].setRollEnabled(true);
			dieButtons[pos].setForeground(Color.red);
		}
	}
	
	private void resetDice() {
		for(int i = 0; i < 5; i++){			
			dieButtons[i].setText("?");			
		}
	}	

	private void displayDice(){
		for(int i = 0; i < 5; i++){
			dieButtons[i].setText(Integer.toString(game.dice[i].getRollValue()));
			dieButtons[i].setForeground(Color.black);
		}
	}
}
