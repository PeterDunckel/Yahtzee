package yahtzeeGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private final String[] lowerSecNames ={"3 of a Kind","T4 of a Kind","Full House","Sm. Straight"
			,"Lg. Straight","Yahtzee","Chance"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScorecardGUI frame = new ScorecardGUI(playerName);
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
	
	private void createArrayBtnList(String[] arrayOfNames, ArrayList<JButton> btnArrayList
			,int x, int y, int height, int width){
		for(int i=0; i<arrayOfNames.length; i++){
			btnArrayList.add(new JButton(arrayOfNames[i]));
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




