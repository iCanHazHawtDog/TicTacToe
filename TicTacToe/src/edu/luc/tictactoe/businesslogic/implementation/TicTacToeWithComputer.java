package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.businesslogic.IPair;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.gui.BoardSameComputer;

/***
 * @author Akrem Osman
 *
 ***/

public class TicTacToeWithComputer extends TicTacToePlay{
	
	public TicTacToeWithComputer() {
		board = new Board();
		playerOne = new Person();
		playerTwo = new Computer(board, DifficultyLevel.Medium);
		// to set the name and icon for the computer player
		setupPlayerTwo("Computer");
		randomStart();
	}
		
	@Override
	public void setDifficultyLevel(DifficultyLevel difficultyLevel){
		((Computer)playerTwo).setSelectionStrategy(difficultyLevel);
	}
	
	@Override
	public void setupPlayerOne(String name){
		super.setupPlayerOne(name);
		if(playerTurn == playerTwo)
			computerMakeSelection();
		UIChanges.ticTacToeReady();
	}
	
	@Override
	public void switchPlayer(){
		super.switchPlayer();
		// if it's the computer turn, the computer needs to make a selection.
		if(playerTurn == playerTwo)
			computerMakeSelection();
			
	}
	
	@Override
	public void setNextTurnPlayer(){
		playerTurn = playerOne;
	}
	
	public void computerMakeSelection(){
		UIChanges.computerStartSelection();
		IPair<Integer, Integer> compSelection = ((Computer)playerTwo).selectPosition();
		selectPosition(playerTwo, compSelection);
		UIChanges.computerAfterSelection(compSelection.getKey(), compSelection.getValue());
	}
}
