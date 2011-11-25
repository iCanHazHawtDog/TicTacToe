package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.businesslogic.IBoard;

/**
 * This subclass is for the implementation of the Computer TicTacToe player
 * It's base class is the Player class
 * 
 */
public class Computer extends Player {

	int levelOfDifficulty;
	int compWin;
	/**
	 * Initializing constructor with the base class
	 */
	public Computer ()
	{
		super("");
	}

	public int getLevelOfDifficulty()
	{
		return levelOfDifficulty;
	}
	
	public void incrementDifficultylevel()
	{
			levelOfDifficulty++;
	}
	
	public void setLevelOfDifficulty(int newLevelOfDifficulty)
	{
		levelOfDifficulty = newLevelOfDifficulty;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public void setNumberOfPlays(int plays) {
		numberOfPlays = plays;		
	}	
	
	public int choosePosition (IBoard board)
	{
		return 0;		
	}	
}
