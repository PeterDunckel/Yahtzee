package yahtzeeGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ScorecardGUI extends JFrame {

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

	/**
	 * Create the frame.
	 */
	public ScorecardGUI(String playerName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		upperSecTable = new JTable();
//		upperSecTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		upperSecTable.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"Aces", null},
//				{"Twos", null},
//				{"Threes", null},
//				{"Fours", null},
//				{"Fives", null},
//				{"Sixes", null},
//				{"Total Score", null},
//				{"Bonus", null},
//				{"Total Upper Section", null},
//			},
//			new String[] {
//				"Button", "Score"
//			}
//		));
//		upperSecTable.getColumn("Button").setCellRenderer(new ButtonRenderer());
//		upperSecTable.getColumn("Button").setCellEditor(
//	        new ButtonEditor(new JCheckBox(), upperSecTable));
//		upperSecTable.setBounds(12, 84, 308, 144);
//		contentPane.add(upperSecTable);
		
		JLabel lblYahtzee = new JLabel("YAHTZEE");
		lblYahtzee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblYahtzee.setBounds(12, 13, 97, 28);
		contentPane.add(lblYahtzee);
		
		JLabel lblName = new JLabel("Name: __________________");
		lblName.setBounds(121, 22, 185, 16);
		contentPane.add(lblName);
		
//		lowerSecTable = new JTable();
//		lowerSecTable.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"3 of a Kind", null},
//				{"4 of a Kind", null},
//				{"Full House", null},
//				{"Sm. Straight", null},
//				{"Lg. Straight", null},
//				{"Yahtzee", null},
//				{"Chance", null},
//				{"Total Upper Section", null},
//				{"Total Lower Section", null},
//				{"Grand Total", null},
//			},
//			new Object[] {
//				"Button", "Score"
//			}
//		));
//		lowerSecTable.getColumn("Button").setCellRenderer(new ButtonRenderer());
//		lowerSecTable.getColumn("Button").setCellEditor(
//	        new ButtonEditor(new JCheckBox(), lowerSecTable));
//		lowerSecTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		lowerSecTable.setBounds(12, 270, 308, 160);
//		contentPane.add(lowerSecTable);
		
		JLabel lblUpperSection = new JLabel("Upper Section:");
		lblUpperSection.setBounds(12, 66, 97, 16);
		contentPane.add(lblUpperSection);
		
		JLabel lblLowerSection = new JLabel("Lower Section:");
		lblLowerSection.setBounds(12, 252, 97, 16);
		contentPane.add(lblLowerSection);
		
		JLabel lblPlayerName = new JLabel("Player");
		lblPlayerName.setBounds(203, 22, 72, 16);
		contentPane.add(lblPlayerName);
		lblPlayerName.setText(playerName);
				
		createArrayBtnList(upperSecNames,upperBtns, 12,83,125,25);
		createArrayLblList(upperBtns,upperLbls, 150,83,125,25);
		
		createArrayBtnList(lowerSecNames,lowerBtns, 12, 269, 125, 25);
		createArrayLblList(lowerBtns,lowerLbls, 150, 269, 125, 25);
				
	}
	
	void notifyScorecard(Die[] dice){
		
		
		for (int i = 0; i < dice.length; i++)
			System.out.println(dice[i].getRollValue());
		
		for (int i = 1; i <= upperBtns.size(); i++){
			if(scoreCard.upperNum(dice, i) > 0){
				upperBtns.get(i - 1).setEnabled(true);
				possibleScore[i - 1] = scoreCard.upperNum(dice, i);
			} else {
				upperBtns.get(i - 1).setEnabled(false);
			}
		}
		
		if(scoreCard.ofAKind(dice, 3)){
			lowerBtns.get(0).setEnabled(true);
			possibleScore[6] = scoreCard.totalDice(dice);
		}else{
			lowerBtns.get(0).setEnabled(false);
		}
		
		if(scoreCard.ofAKind(dice, 4)){
			lowerBtns.get(1).setEnabled(true);
			possibleScore[7] = scoreCard.totalDice(dice);
		}else{
			lowerBtns.get(1).setEnabled(false);
			//possibleScore[6] = 0;
		}
		if(scoreCard.isfullHouse(dice)){
			lowerBtns.get(2).setEnabled(true);
			possibleScore[8] = 25;
		}else
			lowerBtns.get(2).setEnabled(false);
		
		if(scoreCard.isStraight(dice, 4)){
			lowerBtns.get(3).setEnabled(true);
			possibleScore[9] = 30;
		}else
			lowerBtns.get(3).setEnabled(false);
		
		if(scoreCard.isStraight(dice, 5)){
			lowerBtns.get(4).setEnabled(true);
			possibleScore[10] = 40;
		}else
			lowerBtns.get(4).setEnabled(false);
		
		if(scoreCard.yahtzee(dice)){
			lowerBtns.get(5).setEnabled(true);
			possibleScore[11] = 50;
		}else
			lowerBtns.get(5).setEnabled(false);
		
		if(scoreCard.chance()){
			lowerBtns.get(6).setEnabled(true);
			possibleScore[12] = scoreCard.totalDice(dice);
		}else
			lowerBtns.get(6).setEnabled(false);
		
	}
	
	private void createArrayBtnList(String[] arrayOfNames, ArrayList<JButton> btnArrayList
			,int x, int y, int height, int width){
		for(int i=0; i<arrayOfNames.length; i++){
			JButton btn = new JButton(arrayOfNames[i]);
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if(btnArrayList.get(0).getText()== upperSecNames[0]){
						System.out.println(btnArrayList.indexOf(btn));
						upperLbls.get(btnArrayList.indexOf(btn)).setText(Integer.toString(possibleScore[btnArrayList.indexOf(btn)]));
					}else{
						lowerLbls.get(btnArrayList.indexOf(btn)).setText(Integer.toString(possibleScore[btnArrayList.indexOf(btn)-6]));
					}
				}
			});
			btnArrayList.add(btn);
			btnArrayList.get(i).setBounds(x,y+(width*i),height,width);
			contentPane.add(btnArrayList.get(i));
		}
	}
	
	private void createArrayLblList(ArrayList<JButton> btnArrayList, ArrayList<JLabel> lblArrayList
			,int x, int y, int height, int width){
		for(int i=0; i<btnArrayList.size(); i++){
			lblArrayList.add(new JLabel(""));
			lblArrayList.get(i).setBounds(x,y+(width*i),height,width);
			lblArrayList.get(i).setBorder(BorderFactory.createLineBorder(Color.black));
			contentPane.add(lblArrayList.get(i));
		}
	}
}

//class ButtonRenderer extends JButton implements TableCellRenderer {
//
//	  public ButtonRenderer() {
//	    setOpaque(true);
//	  }
//
//	  public Component getTableCellRendererComponent(JTable table, Object value,
//	      boolean isSelected, boolean hasFocus, int row, int column) {
//	    if (isSelected) {
//	      setForeground(table.getSelectionForeground());
//	      setBackground(table.getSelectionBackground());
//	    } else {
//	      setForeground(table.getForeground());
//	      setBackground(UIManager.getColor("Button.background"));
//	    }
//	    setText((value == null) ? "" : value.toString());
//	    return this;
//	  }
//}

//class ButtonEditor extends DefaultCellEditor {
//	  protected JButton button;	 
//	  
//	  private JTable btnTable;
//
//	  private String label;
//
//	  private boolean isPushed;
//	  
//	  private int rowPosition;
//
//	  public ButtonEditor(JCheckBox checkBox, JTable table) {
//	    super(checkBox);
//	    btnTable = table;
//	    button = new JButton();
//	    button.setOpaque(true);
//	    button.addActionListener(new ActionListener() {
//	      public void actionPerformed(ActionEvent e) {
//	        fireEditingStopped();
//	      }
//	    });
//	  }
//
//	  public Component getTableCellEditorComponent(JTable table, Object value,
//	      boolean isSelected, int row, int column) {
//	    if (isSelected) {
//	      button.setForeground(table.getSelectionForeground());
//	      button.setBackground(table.getSelectionBackground());
//	    } else {
//	      button.setForeground(table.getForeground());
//	      button.setBackground(table.getBackground());
//	    }
//	    rowPosition=row;	    
//	    label = (value == null) ? "" : value.toString();
//	    System.out.println(label + ": row ="+ rowPosition + ", col = "+ column);
//	    button.setText(label);
//	    isPushed = true;
//	    return button;
//	  }
//
//	  public Object getCellEditorValue() {
//	    if (isPushed) {
//	      // 
//	      // 
//	      //JOptionPane.showMessageDialog(button, label + ": Add Score!");
//	      // System.out.println(label + ": Add Here!");
////	      btnTable.setValueAt("2", rowPosition, 1);
////	      button.setEnabled(false);
//	    	setRowScore();
//	    }
//	    isPushed = false;
//	    return new String(label);
//	  }
//
//	  public boolean stopCellEditing() {
//	    isPushed = false;
//	    return super.stopCellEditing();
//	  }
//
//	  protected void fireEditingStopped() {
//	    super.fireEditingStopped();
//	  }
//	  
//	  private void setRowScore(){
//		  btnTable.setValueAt("Score", rowPosition, 1);
//		  //need to disable button here
//		  
//	  }
//}




