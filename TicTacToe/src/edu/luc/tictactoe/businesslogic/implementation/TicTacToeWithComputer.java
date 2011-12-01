package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.businesslogic.IPlayer;

public class TicTacToeWithComputer extends TicTacToePlay{

	public TicTacToeWithComputer() {
		playerOne = new Person();
		playerTwo = new Computer();
		// to set the name and icon for the computer player
		setupPlayerTwo("computer");
		randomStart();
		board = new Board();
		if(playerTurn == playerTwo)
			((Computer)playerTwo).selectPosition(board, levelOfDifficulty);
	}

	DifficultyLevel levelOfDifficulty = DifficultyLevel.Medium;
	
	public DifficultyLevel getLevelOfDifficulty()
	{
		return levelOfDifficulty;
	}
	
	public void setDifficultyLevel(DifficultyLevel difficultyLevel){
		this.levelOfDifficulty = difficultyLevel;
	}
	
	@Override
	public void switchPlayer(){
		super.switchPlayer();
		// if it's the computer turn, the computer needs to make a selection.
		if(playerTurn == playerTwo)
			((Computer)playerTwo).selectPosition(board, levelOfDifficulty);
			
	}

}
