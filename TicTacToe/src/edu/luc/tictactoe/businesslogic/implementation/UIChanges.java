package edu.luc.tictactoe.businesslogic.implementation;

/**
 * @author Akrem Osman, Subhash Pant
 * Class has two static methods which pushes the changes to the UI.
 * @Matt needs to complete this class
 */
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.gui.BoardSameComputer;
import edu.luc.tictactoe.gui.MainMenu;
import edu.luc.tictactoe.gui.WinResultWindow;

public class UIChanges {
	//ToDo Matt
	public static void DisplayResult(){
		 WinResultWindow matt = new WinResultWindow();
	     matt.addComponentsToPane(WinResultWindow.frame.getContentPane());
	     WinResultWindow.frame.pack();
	     WinResultWindow.frame.setVisible(true);   
	}
	
	public static void computerAfterSelection(int i, int j){
		BoardSameComputer.setButton(i, j);
		BoardSameComputer.enableButtons();
		
	}
	
	public static void computerStartSelection(){
		BoardSameComputer.disableButtons();
		
	}
	
	
	
}
