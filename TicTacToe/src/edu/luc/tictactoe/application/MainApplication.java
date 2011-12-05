package edu.luc.tictactoe.application;

import edu.luc.tictactoe.businesslogic.ITicTacToePlay;
import edu.luc.tictactoe.businesslogic.implementation.TicTacToePlay;

import edu.luc.tictactoe.gui.controllerOLD.TicTacToeUIApp;

public class MainApplication {

	public static ITicTacToePlay ticTacToePlay;
	/**
	 * 
	 * @author Matt, Paul, Akrem
	 *The main application that runs when the application actually starts
	 *
	 */
	public static void main(String[] args) {
		//We create a new instance of the TicTacToeUIApp, which is a swing application that starts when we run the Main method
		TicTacToeUIApp app= new TicTacToeUIApp();
		app.main(args);
		
//this is a test of branches
		
	}

}

