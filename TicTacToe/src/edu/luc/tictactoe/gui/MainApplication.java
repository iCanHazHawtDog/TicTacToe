
package edu.luc.tictactoe.gui;

import edu.luc.tictactoe.businesslogic.ITicTacToePlay;

/**
 * @author matt
 *
 */
public class MainApplication {

	public static ITicTacToePlay ticTacToePlay;
	
	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	               createAndShowGUI();
	            }
	        });
	}
	
	 private static void createAndShowGUI() {
	     MainMenu matt = new MainMenu();
	     matt.addComponentsToPane(MainMenu.frame.getContentPane());
	     MainMenu.frame.pack();
	     MainMenu.frame.setVisible(true);   
	    }
}
