package edu.luc.tictactoe.businesslogic.implementation;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import edu.luc.tictactoe.businesslogic.IPlayer;

/**
 * @author Omowumi Adegbite, Akrem Osman
 * 
 * This abstract class typically conveys detailed information common to both
 * Person & Computer playing TicTacToe  
 *
 */

public  class Player implements IPlayer {
	 protected String playername; 
     public int numberOfPlays;    
     public int wins;
     //icons will be associated with players for display purposes
     public Icon icon;
     
     public void setIcon(Icon icon){
    	 this.icon = icon; 
     }
     
     public Icon getIcon(){
    	 return icon;
     }
     
     /**
      * Constructor that Initializes the object playername to null 
      */     
	public Player()
	{
		numberOfPlays = 0;
		wins = 0;
	}
	
	
	/**
     * Constructor that initializes the object playername to a given string value
     * 
     * @param name - the name of the player 
     */	
	public Player(String name){
		this.playername = name;
	}
	
	
	/**
	 * Retrieves the name of the player
	 * 
	 * @return String - the player name
	 */	
	public String getName() {
		return this.playername;
	}
	
	
	/**
	 * Sets the name of the player 
	 * 
	 * @param name - the name of the player 
	 */
	public void setName(String name) {
		this.playername = name;
	}
	
	
	/**
	 * Increments the number of times TicTacToe is played once the session begins
	 */
	public void incrementNumberOfPlays(){
		numberOfPlays++;
	}

	
	/**
	 * Increments the number of times a player wins TicTacToe in a particular session
	 */
	public void incrementNumberOfWins(){
		wins++;
	}
	
	/**
	 * Retrieves the number of times a player wins TicTacToe in a session
	 * 
	 * @return int - the number of wins
	 */
	public int getWins()
	{
		return wins;
	}
	
	
	/**
	 * Retrieves the number of times a player plays TicTacToe in a session
	 * 
	 * @return int - the number of times a player plays the game
	 */
	public int getNumberOfPlays(){
		return numberOfPlays;
	}
}
