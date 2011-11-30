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
			((Computer)playerTwo).selectPosition(board);
	}

	@Override
	public boolean setPosition(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}

}
