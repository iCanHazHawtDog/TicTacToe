package edu.luc.tictactoe.businesslogic.implementation;

/**
 * @author Akrem Osman, Subhash Pant
 * Class has two static methods which pushes the changes to the UI
 */
import edu.luc.tictactoe.businesslogic.IPlayer;

public class UIChanges {
	//ToDo Matt
	public static void DisplayResult(SelectionResult result, IPlayer playerOne, IPlayer playerTwo, IPlayer playerTurn){
		//Matt needs to complete this. This will display the result in the UI
		//This class displays results when the win happens
	}
	
	public static void DisplayResult(SelectionResult result, IPlayer playerOne, IPlayer playerTwo){
		//ToDo Matt
		//This class displays results when the draw happens
		DisplayResult(result, playerOne, playerTwo, null);
	}
}
