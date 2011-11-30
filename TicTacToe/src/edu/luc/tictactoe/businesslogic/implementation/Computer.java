package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.businesslogic.IBoard;

/**
 * This subclass is for the implementation of the Computer TicTacToe player
 * It's base class is the Player class
 * 
 */
public class Computer extends Player {

	DifficultyLevel levelOfDifficulty;
	
	public DifficultyLevel getLevelOfDifficulty()
	{
		return levelOfDifficulty;
	}
	
	public void setDifficultyLevel(DifficultyLevel difficultyLevel){
		this.levelOfDifficulty = difficultyLevel;
	}
	
	public SelectionResult selectPosition(IBoard board)
	{
		if(levelOfDifficulty == DifficultyLevel.Easy)
			return randomSelection(board);
		if(levelOfDifficulty == DifficultyLevel.Medium)
			return dontLetPlayerWin(board);
		if(levelOfDifficulty == DifficultyLevel.Hard)
			return smartSelection(board);
		return null;
	}
	
	public SelectionResult randomSelection(IBoard board){
		//Todo Akrem, Omo, Subash
		return SelectionResult.Draw;
	}
	
	public SelectionResult dontLetPlayerWin(IBoard board){
		return null;
	}
	
	public SelectionResult smartSelection(IBoard board){
		return null;
	}

	@Override
	public void setWins(int wins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNumberOfPlays(int plays) {
		// TODO Auto-generated method stub
		
	}
}
