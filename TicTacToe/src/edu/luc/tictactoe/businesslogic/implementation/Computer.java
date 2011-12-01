package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.businesslogic.IBoard;

/**
 * This subclass is for the implementation of the Computer TicTacToe player
 * It's base class is the Player class
 * 
 */
public class Computer extends Player {
	public void selectPosition(IBoard board, DifficultyLevel difficultyLevel)
	{
		if(difficultyLevel == DifficultyLevel.Easy)
			randomSelection(board);
		if(difficultyLevel == DifficultyLevel.Hard)
			smartSelection(board);
		else
			dontLetPlayerWin(board);
	}
	
	public void randomSelection(IBoard board){
		//Todo Akrem, Omo, Subash
	}
	
	public void dontLetPlayerWin(IBoard board){
	}
	
	public void smartSelection(IBoard board){
	}
}
