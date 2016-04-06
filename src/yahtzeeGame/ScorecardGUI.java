package yahtzeeGame;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScorecardGUI frame = new ScorecardGUI();
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
	public ScorecardGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		upperSecTable = new JTable();
		upperSecTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		upperSecTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Aces", null},
				{"Twos", null},
				{"Threes", null},
				{"Fours", null},
				{"Fives", null},
				{"Sixes", null},
				{"Total Score", null},
				{"Bonus", null},
				{"Total Upper Section", null},
			},
			new Object[] {
				"Button", "Score"
			}
		));
		upperSecTable.getColumn("Button").setCellRenderer(new ButtonRenderer());
		upperSecTable.getColumn("Button").setCellEditor(
	        new ButtonEditor(new JCheckBox()));
		upperSecTable.setBounds(12, 72, 308, 144);
		contentPane.add(upperSecTable);
		
		JLabel lblYahtzee = new JLabel("YAHTZEE");
		lblYahtzee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblYahtzee.setBounds(12, 13, 97, 28);
		contentPane.add(lblYahtzee);
		
		JLabel lblName = new JLabel("Name: __________________");
		lblName.setBounds(121, 22, 185, 16);
		contentPane.add(lblName);
		
		lowerSecTable = new JTable();
		lowerSecTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"3 of a Kind", null},
				{"4 of a Kind", null},
				{"Full House", null},
				{"Sm. Straight", null},
				{"Lg. Straight", null},
				{"Yahtzee", null},
				{"Chance", null},
				{"Yahtzee Bonus 1", null},
				{"Yahtzee Bonus 2", null},
				{"Total Upper Section", null},
				{"Total Lower Section", null},
				{"Grand Total", null},
			},
			new Object[] {
				"Button", "Score"
			}
		));
		lowerSecTable.getColumn("Button").setCellRenderer(new ButtonRenderer());
		lowerSecTable.getColumn("Button").setCellEditor(
	        new ButtonEditor(new JCheckBox()));
		lowerSecTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lowerSecTable.setBounds(12, 247, 308, 193);
		contentPane.add(lowerSecTable);
		
		JLabel lblUpperSection = new JLabel("Upper Section:");
		lblUpperSection.setBounds(12, 54, 97, 16);
		contentPane.add(lblUpperSection);
		
		JLabel lblLowerSection = new JLabel("Lower Section:");
		lblLowerSection.setBounds(12, 229, 97, 16);
		contentPane.add(lblLowerSection);
	}
}

class ButtonRenderer extends JButton implements TableCellRenderer {

	  public ButtonRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}

class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;

	  private String label;

	  private boolean isPushed;

	  public ButtonEditor(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        fireEditingStopped();
	      }
	    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value == null) ? "" : value.toString();
	    button.setText(label);
	    isPushed = true;
	    return button;
	  }

	  public Object getCellEditorValue() {
	    if (isPushed) {
	      // 
	      // 
	      JOptionPane.showMessageDialog(button, label + ": Add here!");
	      
	      // System.out.println(label + ": Add Here!");
	    }
	    isPushed = false;
	    return new String(label);
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	}