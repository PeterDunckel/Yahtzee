package yahtzeeGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameGUI extends JFrame implements WindowFocusListener{

	private JButton dieOne;
	private JButton dieTwo;
	private JButton dieThree;
	private JButton dieFour;
	private JButton dieFive;
	private JButton[] dieButtons = new JButton[5];
	private JButton rollDieBtn;
	private JButton btnStartGame;
	
	private Game game = Game.getGameSingleton();
	private JPanel contentPane;
	private JButton addHumanPlyrBtn;
	private JLabel MessageLbl;
	private static int rollCount;
	private ArrayList<ScorecardGUI> scoreCards = new ArrayList<ScorecardGUI>();
	private String playerName;
	private int playerCount;
	private static boolean canRoll = false;
	private JMenu mnAddPlayer;
	private JButton addComputerPlyrBtn;
	private JButton btnNewGame;
	private boolean hasGameStarted;
	
	private static GameGUI gameGUI = new GameGUI();

	/**
	 * Create the frame.
	 */
	public GameGUI() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowFocusListener(this);
		
		// initialize variables
		rollCount = 0;
		playerCount = 0;
		
		MessageLbl = new JLabel("Get started by adding players.");
		MessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		MessageLbl.setForeground(Color.BLACK);
		MessageLbl.setBackground(Color.WHITE);
		MessageLbl.setBounds(16, 40, 298, 29);
		contentPane.add(MessageLbl);
		
		//----------------------
		// Die One Button Tapped
		//----------------------
		dieOne = new JButton("?");
		dieOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				enableDiceForRoll(0);
			}
		});
		
		//-----------------------
		// Roll Die Button Tapped
		//-----------------------
		rollDieBtn = new JButton("Roll Dice");
		rollDieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("RollBtn was clicked");
				if(canRoll){
										
					game.rollDice();
					displayDice();
					rollCount++;
					if(rollCount == 3){
						canRoll = false;
					}
					
					if(game.currentTurn >= game.players.size()){
						game.currentTurn = 0;
					}
					scoreCards.get(game.currentTurn).notifyScorecard(game.dice);
					
					MessageLbl.setText(game.players.get(game.currentTurn).getName()+" has "+(3-rollCount)+" rolls left.");
				}else{
					MessageLbl.setText(game.players.get(game.currentTurn).getName()+" must select score!");
				}
			}
		});
		rollDieBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		rollDieBtn.setBounds(16, 139, 298, 50);
		rollDieBtn.setEnabled(false);
		contentPane.add(rollDieBtn);
		
		dieOne.setBounds(16, 81, 50, 50);
		dieOne.setFocusPainted(false);
		contentPane.add(dieOne);
		dieButtons[0] = dieOne;
		
		//-----------------------
		// Die Two Button Tapped
		//-----------------------
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
		
		//------------------------
		// Die Three Button Tapped
		//------------------------
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
		
		//-----------------------
		// Die Four Button Tapped
		//-----------------------
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
		
		//-----------------------
		// Die Five Button Tapped
		//-----------------------
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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 2, 335, 26);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		btnNewGame = new JButton("New Game");
		mnFile.add(btnNewGame);
		
		mnAddPlayer = new JMenu("Add Player");
		menuBar.add(mnAddPlayer);
		
		//-------------------------
		// Add Human Player Button Tapped
		//-------------------------
		addHumanPlyrBtn = new JButton("Human     ");
		addHumanPlyrBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								
				//Player Creation
				playerName = JOptionPane.showInputDialog(
                        "What is your name?", null);				
				Player newPlayer = new Player(playerName);
				newPlayer.setIsHuman(true);
				game.players.add(newPlayer);
				
				if(playerName != null){
					//Scorecard creation
					ScorecardGUI scFrame = new ScorecardGUI(playerName);
					scFrame.setVisible(true);
					scoreCards.add(scFrame);
					
					// increase player count when adding more players
					playerCount++;
					
					//Only four players allowed
					//Disable addButton after four players added
					if(playerCount >= 4){
						addHumanPlyrBtn.setEnabled(false);
					}				
					btnStartGame.setEnabled(true);
				}
			}
		});
		mnAddPlayer.add(addHumanPlyrBtn);
		
		//-------------------------
		// Add Computer Player Button Tapped
		//-------------------------
		
		addComputerPlyrBtn = new JButton("Computer");
		addComputerPlyrBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Player Creation
				playerName = JOptionPane.showInputDialog(
                        "What is your name?", null);				
				Player newPlayer = new Player(playerName+" A.I.");
				newPlayer.setIsHuman(false);
				game.players.add(newPlayer);
				
				if(playerName != null){
					//Scorecard creation
					ScorecardGUI scFrame = new ScorecardGUI(playerName+" A.I.");
					scFrame.setVisible(true);
					scoreCards.add(scFrame);
					
					// increase player count when adding more players
					playerCount++;
					
					//Only four players allowed
					//Disable addButton after four players added
					if(playerCount >= 4){
						addHumanPlyrBtn.setEnabled(false);
					}				
					btnStartGame.setEnabled(true);
				}
			}
		});
		mnAddPlayer.add(addComputerPlyrBtn);
		
		JLabel lblAfterAddingPlayers = new JLabel("After adding players press Start!");
		lblAfterAddingPlayers.setBounds(78, 237, 186, 16);
		contentPane.add(lblAfterAddingPlayers);
		
		//-------------------------
		// Start Game Button Tapped
		//-------------------------
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//if 1st Player is A.I. perform click
				if(!(game.players.get(0).getIsHuman())){
					rollDieBtn.doClick();
					rollDieBtn.setText("sgsfg");
				}
				
				//disable the ability to add human and computer players
				if(addHumanPlyrBtn.isEnabled() || addComputerPlyrBtn.isEnabled()){
					addHumanPlyrBtn.setEnabled(false);
					addComputerPlyrBtn.setEnabled(false);
				}
				
				//Allow players to roll
				rollDieBtn.setEnabled(true);
				canRoll=true;
				
				hasGameStarted=true;
			}
		});
		btnStartGame.setEnabled(false);
		btnStartGame.setBounds(78, 215, 186, 25);
		contentPane.add(btnStartGame);		
	}
	
	// Singleton getter
		public static GameGUI getGameSingleton(){
			return gameGUI;
		}
	
	//---------------------
	// Enable Dice for Roll
	//---------------------
	private void enableDiceForRoll(int pos){
		if (game.dice[pos].getRollEnabled()){
			game.dice[pos].setRollEnabled(false);
			dieButtons[pos].setForeground(Color.black);
		} else {
			game.dice[pos].setRollEnabled(true);
			dieButtons[pos].setForeground(Color.red);
		}
	}

	//-------------
	// Display Dice
	//-------------
	private void displayDice(){
		for(int i = 0; i < 5; i++){
			dieButtons[i].setText(Integer.toString(game.dice[i].getRollValue()));
			dieButtons[i].setForeground(Color.black);			
		}
	}
	
	//-----------
	// Reset Dice
	//-----------
	public void resetForNextPlayer(){
		canRoll= true;
		rollCount = 0;
		resetDice();
		this.setVisible(true);
	}
	
	//-----------
	// Reset Dice
	//-----------
	private void resetDice(){
		for(int i = 0; i < 5; i++){
			dieButtons[i].setText("?");
			game.dice[i].setRollEnabled(true);
		}
	}
	
	public int getRollCount() {
		return rollCount;
	}

	public void setRollCount(int rollCount) {
		this.rollCount = rollCount;
	}

	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		//On window focus if player is A.I. roll die
		if(game.players.size() > 0){
			if(!(game.players.get(0).getIsHuman()) && hasGameStarted){
				rollDieBtn.doClick();
			}
		}
		
		System.out.println("Gained focus");
	}

	@Override
	public void windowLostFocus(WindowEvent arg0) {
		
		System.out.println("Lost focus");
	}
}
