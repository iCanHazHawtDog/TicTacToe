package edu.luc.tictactoe.businesslogic.implementation;

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
