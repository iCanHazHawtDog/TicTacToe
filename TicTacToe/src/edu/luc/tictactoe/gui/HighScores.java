/**
 * 
 */
package edu.luc.tictactoe.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;


import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author matt
 *
 */
public class HighScores {
	
	static JFrame frame = new JFrame("Highscores");
//	int numberOfHighScores = 10;
//	Object[][] data = {
//		    {"Kathy", "Smith"},
//		    {"John", "Doe"},
//		    {"Sue", "Black"},
//		    {"Jane", "White"},
//		    {"Joe", "Brown"}, 
//		    {"Joe", "Brown"}, 
//		    {"Joe", "Brown"}, 
//		    {"Joe", "Brown"}, 
//		    {"Joe", "Brown"}, 
//		    {"Joe", "Brown"}
//	};
//	Object[][] data = HighScore.highScoreTable(numberOfHighScores);
//	JScrollPane tableScrollPanel = new javax.swing.JScrollPane();
	
	public HighScores() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(300, 500));
	}
	
	@SuppressWarnings("serial")
	public void addComponentsToPane(Container pane) {
		 pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		 Dimension minSize = new Dimension(5, 25);
		 Dimension prefSize = new Dimension(5, 25);
		 Dimension maxSize = new Dimension(Short.MAX_VALUE, 25);
		 pane.add(new Box.Filler(minSize, prefSize, maxSize));
			
		 JLabel hs = new JLabel("High Scores");
		 hs.setAlignmentX(Component.CENTER_ALIGNMENT);
		 pane.add(hs);
	     JPanel north = new JPanel(new GridLayout(0,1,5,5));
	     JTable table = new JTable(new javax.swing.table.AbstractTableModel() {
	    	 private String[] columnNames = {"Name","Wins"};
	    	 private Object[][] data = {
	    			 {"Subhash", 100},
	    			 {"Matt", 90},
	    			 {"Paul", 80},
	    			 {"Manish", 70},
	    			{"Akrem", 60},
	    			{"Subhash", 100},
	    			 {"Matt", 90},
	    			 {"Paul", 80},
	    			 {"Manish", 70},
	    			{"Akrem", 60}
	    			 
	    	 };

	    	 public int getColumnCount() {
	    		 return columnNames.length;
	    	 }

	    	 public int getRowCount() {
	    		 return data.length;
	    	 }

	    	 public String getColumnName(int col) {
	    		 return columnNames[col];
	    	 }

	    	 public Object getValueAt(int row, int col) {
	    		 return data[row][col];
	    	 }

	     });
	     table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	     table.setFillsViewportHeight(true);
	     JScrollPane scrollPane = new JScrollPane(table);
	     north.add(scrollPane);
	     pane.add(north);
	     pane.add(Box.createVerticalGlue());
	     JPanel center = new JPanel(new GridLayout(0,1,1,1));
	     center.add(new JButton(new AbstractAction("Main Menu") {
	    		
	            @Override
	            public void actionPerformed(ActionEvent e) {
	    			frame.setVisible(false);
	    			MainMenu.frame.setVisible(true);
	            }
	      	}));
	     center.setBorder(new EmptyBorder(20,35,20,35));
	     pane.add(center);
	     pane.add(new JLabel(""));
	     
	 }
		 
}
