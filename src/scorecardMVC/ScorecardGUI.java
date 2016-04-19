package scorecardMVC;

import gameMVC.Game;
import gameMVC.GameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import yahtzeeGame.Die;

public class ScorecardGUI extends JFrame {
	
	private Game game = Game.getGameSingleton();
	//private ScoreCardController scoreCardController = game.getPlayers().get(game.currentTurn).scoreCard;

	private ScoreCardController scoreCardController; 
	
	private JPanel contentPane;
	private JTable upperSecTable;
	private JTable lowerSecTable;
	private static String playerName;
	private ArrayList<JLabel> upperLbls = new ArrayList<JLabel>();
	private ArrayList<JLabel> lowerLbls = new ArrayList<JLabel>();
	private ArrayList<JButton> upperBtns = new ArrayList<JButton>();
	private ArrayList<JButton> lowerBtns = new ArrayList<JButton>();	
	private final String[] upperSecNames ={"Aces","Twos","Threes","Fours","Fives"
			,"Sixes"};
	private final String[] lowerSecNames ={"3 of a Kind","4 of a Kind","Full House","Sm. Straight"
			,"Lg. Straight","Yahtzee","Chance"};

	private int[] possibleScore = new int[13];
	
	//These are the start position and specs for the different widgets
	private int strtBtnXpos =12;
	private int strtLblXpos =150;	
	private int strtWidgetYpos =83;
	private int widgetHeight = 125;
	private int widgetWidth =25;

	//Score Labels
	private JLabel lblSubTotalScoreUpper;
	private JLabel lblBonus;
	private JLabel lblTotalUpper;
	private JLabel lblLowerSectionTotal;
	private JLabel lblUpperSectionTotal;
	private JLabel lblGrandTotal;

	/**
	 * Create the frame.
	 */
	public ScorecardGUI(String playerName) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JLabel lblYahtzee = new JLabel("YAHTZEE");
		lblYahtzee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblYahtzee.setBounds(12, 13, 97, 28);
		contentPane.add(lblYahtzee);
		
		JLabel lblName = new JLabel("Name: __________________");
		lblName.setBounds(121, 22, 185, 16);
		contentPane.add(lblName);
		
		JLabel lblUpperSection = new JLabel("Upper Section:");
		lblUpperSection.setBounds(12, 66, 97, 16);
		contentPane.add(lblUpperSection);
		
		JLabel lblLowerSection = new JLabel("Lower Section:");
		lblLowerSection.setBounds(12, strtWidgetYpos+250, 97, 16);
		contentPane.add(lblLowerSection);
		
		JLabel lblPlayerName = new JLabel("Player");
		lblPlayerName.setBounds(203, 22, 72, 16);
		contentPane.add(lblPlayerName);
		lblPlayerName.setText(playerName);
		
		lblSubTotalScoreUpper = new JLabel("TOTAL SCORE");
		lblSubTotalScoreUpper.setBounds(12, strtWidgetYpos+(widgetWidth*6), widgetHeight, widgetWidth);
		contentPane.add(lblSubTotalScoreUpper);
		
		lblBonus = new JLabel("BONUS");
		lblBonus.setBounds(12, strtWidgetYpos+(widgetWidth*7), widgetHeight, widgetWidth);
		contentPane.add(lblBonus);
		
		lblTotalUpper = new JLabel("TOTAL");
		lblTotalUpper.setBounds(12, strtWidgetYpos+(widgetWidth*8), widgetHeight, widgetWidth);
		contentPane.add(lblTotalUpper);
		
		lblLowerSectionTotal = new JLabel("LOWER SECT TOTAL");
		lblLowerSectionTotal.setBounds(12, strtWidgetYpos+(widgetWidth*7)+275, widgetHeight, widgetWidth);
		contentPane.add(lblLowerSectionTotal);
		
		lblUpperSectionTotal = new JLabel("UPPER SECT TOTAL");
		lblUpperSectionTotal.setBounds(12, strtWidgetYpos+(widgetWidth*8)+275, widgetHeight, widgetWidth);
		contentPane.add(lblUpperSectionTotal);
		
		lblGrandTotal = new JLabel("GRAND TOTAL");
		lblGrandTotal.setBounds(12, strtWidgetYpos+(widgetWidth*9)+275, widgetHeight, widgetWidth);
		contentPane.add(lblGrandTotal);
				
		createArrayBtnList(upperSecNames,upperBtns, strtBtnXpos,strtWidgetYpos,widgetHeight,widgetWidth);
		createArrayLblList(upperBtns,upperLbls, strtLblXpos,strtWidgetYpos,widgetHeight,widgetWidth);
		
		createArrayBtnList(lowerSecNames,lowerBtns, strtBtnXpos,strtWidgetYpos+275,widgetHeight,widgetWidth);
		createArrayLblList(lowerBtns,lowerLbls, strtLblXpos, strtWidgetYpos+275,widgetHeight,widgetWidth);
			
		this.setVisible(true);
		//System.out.println("Current Turn"+game.currentTurn+" Amt Players"+game.getPlayers().size());
		scoreCardController = new ScoreCardController(this, game.getPlayers().get(game.currentTurn).scoreCard);
	}
	
	//------------------
	// Notify Score Card
	//------------------
	public void updateGUI(){
		
		for (int i = 1; i <= upperBtns.size(); i++){
			updateButtonGUI(upperBtns.get(i-1), scoreCardController.checkUpperSection(i),
					i - 1, scoreCardController.upperNum(i), scoreCardController.upperSectionHasValue(i-1));
		}
		
		updateButtonGUI(lowerBtns.get(0), scoreCardController.checkOfAKind(3),
				6, scoreCardController.totalOfDice(), scoreCardController.lowerSectionHasValue(0));
		
		updateButtonGUI(lowerBtns.get(1), scoreCardController.checkOfAKind(4),
				7, scoreCardController.totalOfDice(), scoreCardController.lowerSectionHasValue(1));
		
		updateButtonGUI(lowerBtns.get(2), scoreCardController.checkFullHouse(),
				8, 25, scoreCardController.lowerSectionHasValue(2));
		
		updateButtonGUI(lowerBtns.get(3), scoreCardController.checkStraight(4),
				9, 30, scoreCardController.lowerSectionHasValue(3));
		
		updateButtonGUI(lowerBtns.get(4), scoreCardController.checkStraight(5),
				10, 40, scoreCardController.lowerSectionHasValue(4));
	
		updateButtonGUI(lowerBtns.get(5), scoreCardController.checkYahtzee(),
				11, 50, scoreCardController.lowerSectionHasValue(5));
		
		
		if(!scoreCardController.lowerSectionHasValue(6)){
			lowerBtns.get(6).setEnabled(true);
			possibleScore[12] = scoreCardController.totalOfDice();
		}else{
			lowerBtns.get(6).setEnabled(false);
		}
		
	}
	
	private void updateButtonGUI(JButton btn, boolean isValidScore, int index, int score, boolean sectionHasValue){

		int rollCount = game.getRollCount();
		
		if(isValidScore && !sectionHasValue){
			btn.setEnabled(true);
			possibleScore[index] = score;
		}else if( rollCount == 3 && !sectionHasValue){
			btn.setBackground(new Color(200,50,50));
			btn.setEnabled(true);
			possibleScore[index] = 0;
		}else if(sectionHasValue){
			btn.setEnabled(false);
		}
	}
	
	private void createArrayBtnList(String[] arrayOfNames, ArrayList<JButton> btnArrayList
			,int x, int y, int height, int width){
		
		//position buttons on frame
		for(int i=0; i<arrayOfNames.length; i++){
			JButton btn = new JButton(arrayOfNames[i]);
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(btn.isEnabled()){
	
						if(btnArrayList.get(0).getText()== upperSecNames[0]){
							// update the label
							upperLbls.get(btnArrayList.indexOf(btn)).setText(Integer.toString(possibleScore[btnArrayList.indexOf(btn)]));
							
							// update the score card upper section
							scoreCardController.setUpperSection(btnArrayList.indexOf(btn), possibleScore[btnArrayList.indexOf(btn)]);
							
							System.out.println("hosdf " + scoreCardController.checkUpperSection(btnArrayList.indexOf(btn)));
							
						}else{
							lowerLbls.get(btnArrayList.indexOf(btn)).setText(Integer.toString(possibleScore[btnArrayList.indexOf(btn)+6]));
							
							// update the score card upper section
							scoreCardController.setLowerSection(btnArrayList.indexOf(btn), possibleScore[btnArrayList.indexOf(btn) + 6]);
						}
					
						//Disable all buttons and reset their formatting
						resetBtns();
						setAllScoreFields();
						
						//Go to next players turn
						game.changeTurn();
					}
				}
			});
			btnArrayList.add(btn);
			btn.setEnabled(false);
			btnArrayList.get(i).setBounds(x,y+(width*i),height,width);
			contentPane.add(btnArrayList.get(i));
		}
	}

	private void resetBtns() {
		for(JButton btn: upperBtns){
			btn.setBackground(null);
			btn.setEnabled(false);
		}
		for(JButton btn: lowerBtns){
			btn.setBackground(null);
			btn.setEnabled(false);
		}
	}

	private void createArrayLblList(ArrayList<JButton> btnArrayList, ArrayList<JLabel> lblArrayList
			,int x, int y, int height, int width){
		//position labels on frame
		for(int i=0; i<btnArrayList.size()+3; i++){
			lblArrayList.add(new JLabel("", SwingConstants.CENTER));			
			lblArrayList.get(i).setBounds(x,y+(width*i),height,width);			
			lblArrayList.get(i).setBorder(BorderFactory.createLineBorder(Color.black));			
			contentPane.add(lblArrayList.get(i));
		}
	}
	
	private void setAllScoreFields() {
		
		upperLbls.get(6).setText(String.valueOf(scoreCardController.getUpperScore()));
		if(scoreCardController.getUpperScore() >= 63){
			upperLbls.get(7).setText("35");
		} else {
			upperLbls.get(7).setText("0");
		}
		upperLbls.get(8).setText(String.valueOf(scoreCardController.getTotalUpperScore()));
		
		lowerLbls.get(7).setText(String.valueOf(scoreCardController.getLowerScore()));
		lowerLbls.get(8).setText(String.valueOf(scoreCardController.getTotalUpperScore()));
		lowerLbls.get(9).setText(String.valueOf(scoreCardController.getGrandTotalScore()));	

	}



	
//	private boolean areAllBtnsSelected(){
//		for(int i=0; i<game.players.get(game.currentTurn).selectedCategories.length; i++){
//			if(game.players.get(game.currentTurn).selectedCategories[i] ==0){
//				return false;
//			}
//		}
//		return true;
//	}

}

