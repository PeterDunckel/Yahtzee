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
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class GameGUI extends JFrame {

	private JButton dieOne;
	private JButton dieTwo;
	private JButton dieThree;
	private JButton dieFour;
	private JButton dieFive;
	private JButton[] dieButtons = new JButton[5];
	
	private Game game = new Game();
	private JPanel contentPane;
	private JButton addPlayerBtn;
	private JLabel MessageLbl;
	private JButton btnRestartGame;
	private int rollNum;

	/**
	 * Create the frame.
	 */
	public GameGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rollNum = 0;
		
		dieOne = new JButton("?");
		dieOne.setBackground(Color.WHITE);
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
		rollDieBtn.setBounds(16, 139, 298, 50);
		contentPane.add(rollDieBtn);
		dieOne.setBounds(16, 81, 50, 50);
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
		dieTwo.setBounds(78, 81, 50, 50);
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
		dieThree.setBounds(140, 81, 50, 50);
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
		dieFour.setBounds(202, 81, 50, 50);
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
		dieFive.setBounds(264, 81, 50, 50);
		dieFive.setFocusPainted(false);
		contentPane.add(dieFive);
		dieButtons[4] = dieFive;
		
		addPlayerBtn = new JButton("Add Player");
		addPlayerBtn.setBounds(202, 6, 117, 29);
		contentPane.add(addPlayerBtn);
		
		MessageLbl = new JLabel("Message Label");
		MessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		MessageLbl.setForeground(Color.BLACK);
		MessageLbl.setBackground(Color.WHITE);
		MessageLbl.setBounds(16, 40, 298, 29);
		contentPane.add(MessageLbl);
		
		btnRestartGame = new JButton("Restart Game");
		btnRestartGame.setBounds(11, 6, 117, 29);
		contentPane.add(btnRestartGame);
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

	private void displayDice(){
		for(int i = 0; i < 5; i++){
			dieButtons[i].setText(Integer.toString(game.dice[i].getRollValue()));
			dieButtons[i].setForeground(Color.black);
		}
	}
}
