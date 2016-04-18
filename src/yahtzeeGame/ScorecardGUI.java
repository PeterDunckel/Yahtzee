package yahtzeeGame;

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

public class ScorecardGUI extends JFrame {

	private static final int BONUS = 35;
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
	private ScoreCard scoreCard = new ScoreCard();
	private int[] possibleScore = new int[13];
	GameGUI gameGUI = GameGUI.getGameSingleton();
	Game game = Game.getGameSingleton();
	private int rollCount;
	private String winner;
	private static int finalTurnsCounter=1;
	
	//These are the start position and specs for the different widgets
	private int strtBtnXpos =12;
	private int strtLblXpos =150;	
	private int strtWidgetYpos =83;
	private int widgetHeight = 125;
	private int widgetWidth =25;
	
	//for setting the score fields 
	private int upperTotalScore;
	private int lowerTotalScore;
	private boolean isBonusScore;
	
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
				
	}
	
	//------------------
	// Notify Score Card
	//------------------
	void notifyScorecard(Die[] dice){
		this.rollCount = gameGUI.getRollCount();
		
		for (int i = 0; i < dice.length; i++)
			System.out.println(dice[i].getRollValue());
		
		for (int i = 1; i <= upperBtns.size(); i++){
			if(scoreCard.upperNum(dice, i) > 0 && game.players.get(game.currentTurn).selectedCategories[i-1]==0){
				upperBtns.get(i - 1).setEnabled(true);
				possibleScore[i - 1] = scoreCard.upperNum(dice, i);
			}else if( rollCount == 3 && game.players.get(game.currentTurn).selectedCategories[i-1]==0){
				upperBtns.get(i-1).setBackground(new Color(200,50,50));
				upperBtns.get(i - 1).setEnabled(true);
				possibleScore[i - 1] = 0;
			}else {
				upperBtns.get(i - 1).setEnabled(false);
			}
		}
		
		if(scoreCard.ofAKind(dice, 3) && game.players.get(game.currentTurn).selectedCategories[6]==0){
			lowerBtns.get(0).setEnabled(true);
			possibleScore[6] = scoreCard.totalDice(dice);
		}else if( rollCount == 3 && game.players.get(game.currentTurn).selectedCategories[6]==0){
			lowerBtns.get(0).setBackground(new Color(200,50,50));
			lowerBtns.get(0).setEnabled(true);
			possibleScore[6] = 0;
		}else{
			lowerBtns.get(0).setEnabled(false);
		}
		
		if(scoreCard.ofAKind(dice, 4) && game.players.get(game.currentTurn).selectedCategories[7]==0){
			lowerBtns.get(1).setEnabled(true);
			possibleScore[7] = scoreCard.totalDice(dice);
		}else if( rollCount == 3 && game.players.get(game.currentTurn).selectedCategories[7]==0){
			lowerBtns.get(1).setBackground(new Color(200,50,50));
			lowerBtns.get(1).setEnabled(true);
			possibleScore[7] = 0;
		}else{
			lowerBtns.get(1).setEnabled(false);
		}
		if(scoreCard.isfullHouse(dice) && game.players.get(game.currentTurn).selectedCategories[8]==0){
			lowerBtns.get(2).setEnabled(true);
			possibleScore[8] = 25;
		}else if( rollCount == 3 && game.players.get(game.currentTurn).selectedCategories[8]==0){
			lowerBtns.get(2).setBackground(new Color(200,50,50));
			lowerBtns.get(2).setEnabled(true);
			possibleScore[8] = 0;
		}else
			lowerBtns.get(2).setEnabled(false);
		
		if(scoreCard.isStraight(dice, 4) && game.players.get(game.currentTurn).selectedCategories[9]==0){
			lowerBtns.get(3).setEnabled(true);
			possibleScore[9] = 30;
		}else if( rollCount == 3 && game.players.get(game.currentTurn).selectedCategories[9]==0){
			lowerBtns.get(3).setBackground(new Color(200,50,50));
			lowerBtns.get(3).setEnabled(true);
			possibleScore[9] = 0;
		}else
			lowerBtns.get(3).setEnabled(false);
		
		if(scoreCard.isStraight(dice, 5) && game.players.get(game.currentTurn).selectedCategories[10]==0){
			lowerBtns.get(4).setEnabled(true);
			possibleScore[10] = 40;
		}else if( rollCount == 3 && game.players.get(game.currentTurn).selectedCategories[10]==0){
			lowerBtns.get(4).setBackground(new Color(200,50,50));
			lowerBtns.get(4).setEnabled(true);
			possibleScore[10] = 0;
		}else{
			lowerBtns.get(4).setEnabled(false);
		}
		
		if(scoreCard.yahtzee(dice) && game.players.get(game.currentTurn).selectedCategories[11]==0){
			lowerBtns.get(5).setEnabled(true);
			possibleScore[11] = 50;
		}else if( rollCount == 3 && game.players.get(game.currentTurn).selectedCategories[11]==0){
			lowerBtns.get(5).setBackground(new Color(200,50,50));
			lowerBtns.get(5).setEnabled(true);
			possibleScore[11] = 0;
		}else{
			lowerBtns.get(5).setEnabled(false);
		}
		
		if(scoreCard.chance() && game.players.get(game.currentTurn).selectedCategories[12]==0){
			lowerBtns.get(6).setEnabled(true);
			possibleScore[12] = scoreCard.totalDice(dice);
		}else{
			lowerBtns.get(6).setEnabled(false);
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
						//Make sure the current turn of the players 
						//is not greater than the amt of players
						if(game.currentTurn >= game.players.size()){
							game.currentTurn = 0;
						}
	
						if(btnArrayList.get(0).getText()== upperSecNames[0]){
							upperLbls.get(btnArrayList.indexOf(btn)).setText(Integer.toString(possibleScore[btnArrayList.indexOf(btn)]));
	
							//set the categories that have been selected
							game.players.get(game.currentTurn).selectedCategories[btnArrayList.indexOf(btn)] = 1;
						}else{
							lowerLbls.get(btnArrayList.indexOf(btn)).setText(Integer.toString(possibleScore[btnArrayList.indexOf(btn)+6]));
							
							//set the categories that have been selected
							game.players.get(game.currentTurn).selectedCategories[btnArrayList.indexOf(btn)+6] = 1;
						}
						
						if(areAllBtnsSelected()){
							
							getUpperTotalScore();
							getLowerTotalScore();
							setAllScoreFields();
							game.players.get(game.currentTurn).scoreCard.setTotalScore(upperTotalScore+lowerTotalScore);
							
							if(game.players.size() <= finalTurnsCounter){
								determineWinner();
								JOptionPane.showMessageDialog(null, "Congratulations "+winner+"! You won!");
							}
							finalTurnsCounter++;
						}
						//reset rollCount
						gameGUI.resetForNextPlayer();
						
						
						//Disable all buttons and reset their formatting
						resetBtns();
						
						//Go to next players turn
						game.currentTurn++;
					}
				}
			});
			btnArrayList.add(btn);
			btn.setEnabled(false);
			btnArrayList.get(i).setBounds(x,y+(width*i),height,width);
			contentPane.add(btnArrayList.get(i));
		}
	}

	private void determineWinner() {
		int topScore = 0;
		for(int i=0; i<game.players.size(); i++){
			if(game.players.get(i).scoreCard.getTotalScore() > topScore){
				winner = game.players.get(i).getName();
			};
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
	
	protected void setAllScoreFields() {
		if(isBonusScore){
			upperLbls.get(6).setText(String.valueOf(upperTotalScore-BONUS));
			upperLbls.get(7).setText(String.valueOf(BONUS));
		}else{
			upperLbls.get(6).setText(String.valueOf(upperTotalScore));
			upperLbls.get(7).setText("0");
		}
		upperLbls.get(8).setText(String.valueOf(upperTotalScore));
		lowerLbls.get(7).setText(String.valueOf(lowerTotalScore));
		lowerLbls.get(8).setText(String.valueOf(upperTotalScore));
		lowerLbls.get(9).setText(String.valueOf(upperTotalScore+lowerTotalScore));		
	}

	protected void getLowerTotalScore() {
		for(JLabel lbl: lowerLbls ){
			if(lbl.getText() != "")
			lowerTotalScore += Integer.parseInt(lbl.getText());
		}
	}

	protected void getUpperTotalScore() {
		for(JLabel lbl: upperLbls ){
			if(lbl.getText() != "")
			upperTotalScore += Integer.parseInt(lbl.getText());
		}
		if(upperTotalScore >= 63){
			upperTotalScore += BONUS;
			isBonusScore = true;
		}
		
	}
	
	private boolean areAllBtnsSelected(){
		for(int i=0; i<game.players.get(game.currentTurn).selectedCategories.length; i++){
			if(game.players.get(game.currentTurn).selectedCategories[i] ==0){
				return false;
			}
		}
		return true;
	}
}

