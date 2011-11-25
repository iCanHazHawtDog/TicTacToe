package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.businesslogic.IPlayer;

public class TicTacToeTwoPlayers extends TicTacToePlay{

	public TicTacToeTwoPlayers(GameType gameType) {
		super(gameType);	
	}

	public boolean setPosition(int i, int j) {
		
		return false;
	}

	public void setPlayerOne(IPlayer player) {
		playerOne = player;
	}


	public void setPlayerTwo(IPlayer player) {	
		playerTwo = player;
	}

}
