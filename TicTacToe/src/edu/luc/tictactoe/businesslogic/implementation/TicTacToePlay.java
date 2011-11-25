package edu.luc.tictactoe.businesslogic.implementation;

import java.util.Random;
import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.ITicTacToePlay;
import edu.luc.tictactoe.dao.DBInteraction;
import edu.luc.tictactoe.gui.controller.MainBoard;
import edu.luc.tictactoe.networking.INetworking;

/**
 * @author Akrem Osman
 * 
 * TicTacToe play class is where all the game-play happens. We attempt to limit all functions calls to this class.
 * All moves are done in this class, we retrieve the scores, set them, return wins, draws etc..
 *
 */
public abstract class TicTacToePlay implements ITicTacToePlay{
	private IBoard board;
	public IPlayer playerOne;
	public IPlayer playerTwo;
	private IPlayer playerTurn;
	private IPlayer nextPlayerTurn;
	
	/**
	 * Constructor for the TicTacToePlay- takes in the gameType
	 * 
	 * Creates a board, person or computer, sets up the start, then sets who's turn it is
	 * 
	 * @param gameType
	 */
	public TicTacToePlay(GameType gameType){
		
		this.board = new Board();
		this.playerOne = new Person();
		if(gameType == GameType.WithComputer){
			this.playerTwo = new Computer();
		}else{
			this.playerTwo = new Person();
		} 
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
	public void setPlayerOne(String name){
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.luc.tictactoe.gui.controller.TicTacToeUIApp.class).getContext().getResourceMap(MainBoard.class);
		
		playerOne.setName(name);
		playerOne.setIcon(resourceMap.getIcon("xIcon.icon"));
	}
	
	/**
	 * Sets the second players name and icon
	 * 
	 * @param name
	 */
	public void setPlayerTwo(String name){
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.luc.tictactoe.gui.controller.TicTacToeUIApp.class).getContext().getResourceMap(MainBoard.class);

		playerTwo.setName(name);
		playerTwo.setIcon(resourceMap.getIcon("oIcon.icon"));
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
	public SelectionResult selectPosition(int i, int j){
		board.selectPosition(playerTurn, i, j);
		boolean won = board.checkWin(playerTurn);
		
		if(won){
			playerOne.incrementNumberOfPlays();
			playerTwo.incrementNumberOfPlays();
			playerTurn.incrementNumberOfWins();
			board.resetBoard();
			setNextTurnPlayer();
			return SelectionResult.Win;
		}
			
		if(board.isFull()){
			playerOne.incrementNumberOfPlays();
			playerTwo.incrementNumberOfPlays();
			board.resetBoard();
			setNextTurnPlayer();
			return SelectionResult.Draw;
		}
		
		switchPlayer();
		return SelectionResult.Continue;
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
	public void resetBoard(){
		board.resetBoard();
	}
	
	/**
	 * Sets the number of games that have been played for both the players
	 * 
	 * @param plays
	 */
	public void setNumberOfPlays(int plays){
		playerOne.setNumberOfPlays(plays);
		playerTwo.setNumberOfPlays(plays);
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
	
}
