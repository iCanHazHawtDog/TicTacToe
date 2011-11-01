package edu.luc.tictactoe.businesslogic.implementation;

/**
 * @author - Akrem Osman
 * This subclass is for the implementation of the human/person TicTacToe players
 * Its base class is the Player class
 * 
 */
public class Person extends Player {
	/**
	 * Initializing constructor with the base class
	 */
	public  Person ()
	{
		super("");
	}
	
	public void setWins(int wins){
		this.wins = wins;
	}
		
	public void setNumberOfPlays(int numberOfPlays){
		this.numberOfPlays = numberOfPlays;
	}
}
