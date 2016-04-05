package yahtzeeGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class YahtzeeMain extends JFrame {

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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YahtzeeMain frame = new YahtzeeMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public YahtzeeMain() {
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
				
				game.dice[0].setRollEnabled(true);
			}
		});
		dieOne.setBounds(6, 6, 50, 50);
		contentPane.add(dieOne);
		dieButtons[0] = dieOne;
		
		dieTwo = new JButton("?");
		dieTwo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				game.dice[1].setRollEnabled(true);
			}
		});
		dieTwo.setBounds(6, 55, 50, 50);
		contentPane.add(dieTwo);
		dieButtons[1] = dieTwo;
		
		dieThree = new JButton("?");
		dieThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				game.dice[2].setRollEnabled(true);
			}
		});
		dieThree.setBounds(6, 104, 50, 50);
		contentPane.add(dieThree);
		dieButtons[2] = dieThree;
		
		dieFour = new JButton("?");
		dieFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				game.dice[3].setRollEnabled(true);
			}
		});
		dieFour.setBounds(6, 153, 50, 50);
		contentPane.add(dieFour);
		dieButtons[3] = dieFour;
		
		dieFive = new JButton("?");
		dieFive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				game.dice[4].setRollEnabled(true);
			}
		});
		dieFive.setBounds(6, 201, 50, 50);
		contentPane.add(dieFive);
		dieButtons[4] = dieFive;
		
		JButton rollDieBtn = new JButton("Roll Dice");
		rollDieBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				
//				if(firstRoll){
//					firstRoll = false;
//				}else{
//					//Do nothing
//				}
				game.rollDice();
				displayDice();
			}
		});
		rollDieBtn.setBounds(6, 263, 117, 40);
		contentPane.add(rollDieBtn);
		
		JButton btnSetScore = new JButton("Set Score");
		btnSetScore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//When the dice values are as desired
				//Reset to first roll(Only three rolls each iteration)
				//and set scores
				//firstRoll = true;
				
				resetDice();
			}
		});
		btnSetScore.setBounds(312, 263, 89, 40);
		contentPane.add(btnSetScore);
	}
	
	protected void resetDice() {
		for(int i = 0; i < 5; i++){			
			dieButtons[i].setText("?");			
		}
	}	

	private void displayDice(){
		
		for(int i = 0; i < 5; i++){
			
			dieButtons[i].setText(Integer.toString(game.dice[i].getRollValue()));
			
		}
	}
}
