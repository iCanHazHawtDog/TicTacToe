package edu.luc.tictactoe.businesslogic.implementation;

/**
 * @author Akrem Osman, Matt Kemnetz
 * 
 */

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPair;
import edu.luc.tictactoe.businesslogic.IPlayer;

/**
 * This class acts as the implementation of a Tic Tac Toe board.  The board is a 
 * standard 3x3 board.  
 * 
 */
public class Board implements IBoard {
	
	private IPlayer[][] playersPositions;
	
	/**
	 * Board constructor.  Populates the board with null to create an "empty"
	 * 3x3 game board.
	 */
	public Board(){
		playersPositions = new IPlayer[3][3];
		playersPositions[0][0] = null;
		resetBoard();
	}
	
	/**
	 * Checks to see whether or not a board is full.
	 * 
	 * @return boolean specifying whether or not the board is full
	 */
	public boolean isFull() {
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(playersPositions[i][j] == null) return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks the board to see if the player passed has won.  Cycles through 
	 * the board checking for the traditional "3 in a row" that is required
	 * to win tic tac toe
	 * 
	 * @param IPlayer the board is checked to see if this player has won.
	 * @return boolean indicating a win or a loss.
	 */
	public boolean checkWin(IPlayer player) {
		
		int numberOfMatches =0;
		
		for(int i=0;i<3;i++){
			for(numberOfMatches=0;numberOfMatches<3;numberOfMatches++)
				if(playersPositions[i][numberOfMatches] != player) break;
			if(numberOfMatches==3) 
				return true;
		}
		
		for(int j=0;j<3;j++){
			for(numberOfMatches=0;numberOfMatches<3;numberOfMatches++)
				if(playersPositions[numberOfMatches][j] != player) break;
			if(numberOfMatches==3) 
				return true;
		}
		
		for(numberOfMatches=0;numberOfMatches<3;numberOfMatches++)
			if(playersPositions[numberOfMatches][numberOfMatches] != player) 
				break;
			
		if(numberOfMatches==3) 
			return true;
				
		for(numberOfMatches=0;numberOfMatches<3;numberOfMatches++)
			if(playersPositions[numberOfMatches][2-numberOfMatches] != player) 
				break;
			
		if(numberOfMatches==3) 
			return true;
		
		return false;
	}

	/**
	 * Resets the board to the started "empty board" (null in each position).  
	 * the resetBoard method populates the board with a <code>null</code> in each 
	 * position.
	 * 
	 * @return boolean always returns true indicating that the board has been 
	 * 			reset.
	 */
	public boolean resetBoard() {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				playersPositions[i][j] = null;
		return true;
	}

	/**
	 * checks if position (i, j) in the board has already being selected
	 */
	public boolean isSelected(int i, int j) {
		if(playersPositions[i][j]==null){
			return false;
		}
		return true;
	}
/*
 * select position i, j for player if it's not already taken
 */
	public boolean selectPosition(IPlayer player, int i, int j) {
		
		if(i<0 || i>2 || j<0 || j>2) 
			return false;
		
		if(!isSelected(i, j)){
			playersPositions[i][j] = player;
			return true;
		}
		return false;
	}
	
	/*
	 * counts the number of positions already being selected
	 */
	public int countSelected(){
		int counter = 0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(playersPositions[i][j]!=null) counter++;
		
		return counter;
	}
	
	public ArrayList<IPair<Integer, Integer>> NotSelectedPositions(){
		//Map<Integer, Integer> positions = new HashMap<Integer, Integer>();
		ArrayList<IPair<Integer, Integer>> positions = new ArrayList();
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(!isSelected(i, j))
					positions.add(new Pair<Integer, Integer>(i, j));
			
		return positions;
	}
	
	public IPair otherPlayerWouldNotWinIfSelected(IPlayer player){
		
		int numberOfMatches = 0;
		IPair notSelected = null;
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(playersPositions[i][j] == player)
					break;
				if(playersPositions[i][j] != null && playersPositions[i][j]!=player)
					numberOfMatches ++;
				if(playersPositions[i][j] == null)
					notSelected = new Pair(i, j);
			}
			if(numberOfMatches == 2)
				return notSelected;
			notSelected = null;
			numberOfMatches =0;
		}
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(playersPositions[j][i] == player)
					break;
				if(playersPositions[j][i] != null && playersPositions[j][i]!=player)
					numberOfMatches ++;
				if(playersPositions[j][i] == null)
					notSelected = new Pair(j, i);
			}
			if(numberOfMatches == 2)
				return notSelected;
			notSelected = null;
			numberOfMatches =0;
		}
			
		for(int i=0;i<3;i++){
			if(playersPositions[i][i] == null)
				notSelected = new Pair(i, i);
			if(playersPositions[i][i] == player)
				break;
			if(playersPositions[i][i]!=null && playersPositions[i][i]!=player)
				numberOfMatches++;
		}
			
		if(numberOfMatches == 2)
			return notSelected;
		notSelected = null;
		numberOfMatches =0;
					
		for(int i=0;i<3;i++){
			if(playersPositions[i][2-i] == null)
				notSelected = new Pair(i, i);
			if(playersPositions[i][2-i] == player)
				break;
			if(playersPositions[i][2-i]!=null && playersPositions[i][2-i]!=player)
				numberOfMatches++;
		}
			
		if(numberOfMatches == 2)
			return notSelected;

		return null;
	}
	
	public boolean canWin(IPlayer player, IPair<Integer, Integer> position){
		
		if(playersPositions[position.getKey()][position.getValue()] != null)
			return false;
		
		int xMatches = 0;
		int yMatches = 0;
		int downDiagonalMatches = 0;
		int upDiagonalMatches = 0;
		
		for(int i=0;i<3;i++){
			if(playersPositions[position.getKey()][i] == player)
				xMatches++;
			if(playersPositions[i][position.getValue()] == player)
				yMatches++;
		}
				
		if(xMatches == 2 || yMatches == 2)
			return true;
		
		for(int i=0;i<3;i++){
			if(playersPositions[i][i] == player)
				downDiagonalMatches++;
			if(playersPositions[i][2-i] == player)
				upDiagonalMatches++;
		}
		
		if(downDiagonalMatches == 2 || upDiagonalMatches == 2)
			return true;
		
		return false;
	}
	
	/*public boolean canWin(IPlayer player, int i){
		for(int j=0;j<3;j++)
			if(playersPositions[i][j] == player)
				return && playersPositions[i][1] == player && playersPositions[i][2] == player)
	}*/
	
	@Test
	public void testDontLetHimWin(){
		//IBoard board = new Board();
		IPlayer player = new Player();
		this.selectPosition(player, 1, 1);
		this.selectPosition(player, 0, 0);
		IPair pair = otherPlayerWouldNotWinIfSelected(new Player());
		Assert.assertEquals(pair.getKey(), 2);
		Assert.assertEquals(pair.getValue(), 2);
	}
}