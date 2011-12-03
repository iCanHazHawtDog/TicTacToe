package edu.luc.tictactoe.businesslogic.implementation;

public class TicTacToeWithComputer extends TicTacToePlay{

	public TicTacToeWithComputer() {
		board = new Board();
		playerOne = new Person();
		playerTwo = new Computer(board, DifficultyLevel.Medium);
		// to set the name and icon for the computer player
		setupPlayerTwo("Computer");
		randomStart();
		if(playerTurn == playerTwo)
			computerMakeSelection();
	}
		
	public void setDifficultyLevel(DifficultyLevel difficultyLevel){
		((Computer)playerTwo).setSelectionStrategy(difficultyLevel);
	}
	
	@Override
	public void switchPlayer(){
		super.switchPlayer();
		// if it's the computer turn, the computer needs to make a selection.
		if(playerTurn == playerTwo)
			computerMakeSelection();
	}
	
	private void computerMakeSelection(){
		selectPosition(((Computer)playerTwo).selectPosition());
	}
}
