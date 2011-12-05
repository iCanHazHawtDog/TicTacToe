package edu.luc.tictactoe.businesslogic.implementation;


import java.util.Random;

import javax.swing.ImageIcon;

import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPair;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.ITicTacToePlay;
import edu.luc.tictactoe.dao.DBInteraction;

/**
 * @author Akrem Osman
 * 
 * TicTacToe play class is where all the game-play happens. We attempt to limit all functions calls to this class.
 * All moves are done in this class, we retrieve the scores, set them, return wins, draws etc..
 *
 */
public class TicTacToePlay implements ITicTacToePlay{
	protected IBoard board;
	public IPlayer playerOne;
	public IPlayer playerTwo;
	protected IPlayer playerTurn;
	private IPlayer nextPlayerTurn;
	protected IPlayer winner;
	
	
	/**
	 * Constructor for the TicTacToePlay- takes in the gameType
	 * 
	 * Creates a board, person or computer, sets up the start, then sets who's turn it is
	 * 
	 * @param gameType
	 */
	public TicTacToePlay(){
		this.board = new Board();
		this.playerOne = new Person();
		this.playerTwo = new Person();
		randomStart();
		nextPlayerTurn = (playerTurn.equals(playerOne)) ? playerTwo : playerOne;  
	}
	
	/**
	 * Randomly selects who turn it is
	 * @return 
	 * 
	 */
	public IPlayer randomStart(){
		Random random = new Random();
		if(random.nextInt() % 2 == 0)
			playerTurn = playerOne;
		else
			playerTurn = playerTwo;
		return playerTurn;
	}
	
	/**
	 * Sets the first players name and icon
	 * 
	 * @param name
	 */
	public void setupPlayerOne(String name){
//		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.luc.tictactoe.gui.controllerOLD.TicTacToeUIApp.class).getContext().getResourceMap(MainBoard.class);
		playerOne.setName(name);
//		playerOne.setIcon(resourceMap.getIcon("xIcon.icon"));
		ImageIcon icon = new ImageIcon("Images/TicTacToeXIcon.png");
		playerOne.setIcon(icon);
	}
	
	/**
	 * Sets the second players name and icon
	 * 
	 * @param name
	 */
	public void setupPlayerTwo(String name){
		playerTwo.setName(name);
		ImageIcon icon = new ImageIcon("Images/TicTacToeOIcon.png");
		playerTwo.setIcon(icon);
	}
	
	/**
	 * Returns the first IPlayer
	 * 
	 * @return IPlayer
	 */
	public IPlayer getPlayerOne(){
		return playerOne;
	}
	
	/**
	 * Returns the second IPlayer
	 * 
	 * @return IPlayer
	 */
	public IPlayer getPlayerTwo(){
		return playerTwo;
	}
	
	/**
	 * Sets the next players turn;
	 * 
	 */
	public void setNextTurnPlayer(){
		IPlayer tempPlayer = playerTurn;
		playerTurn = nextPlayerTurn;
		nextPlayerTurn = tempPlayer;
	}
	
	/**
	 * Sets the logical position of the board. Returns the selection result.
	 * 
	 * 
	 * @param i
	 * @param j
	 * @return SelectionResult
	 */
	public void selectPosition(IPlayer player, int i, int j){
		if(player == playerTurn){
			board.selectPosition(playerTurn, i, j);
			boolean won = board.checkWin(playerTurn);
			
			if(won){
				playerOne.incrementNumberOfPlays();
				playerTwo.incrementNumberOfPlays();
				playerTurn.incrementNumberOfWins();
				winner = playerTurn;
				UIChanges.DisplayResult();
				board.resetBoard();
				winner = null;
				setNextTurnPlayer();
				return;
			}
				
			if(board.isFull()){
				playerOne.incrementNumberOfPlays();
				playerTwo.incrementNumberOfPlays();
				UIChanges.DisplayResult();
				board.resetBoard();
				setNextTurnPlayer();
				return;
			}
			
			switchPlayer();
		}
	}
	
	public void selectPosition(IPlayer player, IPair<Integer, Integer> pair){
		selectPosition(player, pair.getKey(), pair.getValue());
	}
	
	public IPlayer getWinner(){
		return winner;
	}
		
	/**
	 * Returns who's turn it is.
	 * 
	 * @return IPlayer
	 */
	public IPlayer whoseTurn(){
		return playerTurn;
	}
	
	/**
	 * Switches the IPlayers
	 * 
	 */
	public void switchPlayer(){
		if(playerTurn == playerOne)
			playerTurn = playerTwo;
		else
			playerTurn = playerOne;
	}
	
	/**
	 * Resets the board
	 * 
	 */
	public void resetGame(){
		board.resetBoard();
	}
		
	public int getNumberOfPlays(){
		return playerOne.getNumberOfPlays();
	}
	/**
	 * stores players (only person) scores to Database
	 */
	public void registerPlayers(){
		DBInteraction dbInter = new DBInteraction();
		if(playerOne instanceof Person){
			dbInter.registerUser(playerOne);
		}
		if(playerTwo instanceof Person){
			dbInter.registerUser(playerTwo);
		}
	}
	
	public IPlayer getGameWinner(){
		if(playerOne.getWins() > playerTwo.getWins())
			return playerOne;
		else if(playerTwo.getWins() > playerOne.getWins())
			return playerTwo;
		return null;
	}
	
	public void canStartNow(){
	
	}
}
