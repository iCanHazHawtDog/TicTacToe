package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.businesslogic.ITicTacToePlay;

public class Factory {

	public static ITicTacToePlay CreateTicTacToePlay(GameType gameType){
		if(gameType == GameType.WithAnotherPersonInSameComputer)
			return new TicTacToePlay();
		if(gameType == GameType.WithComputer)
			return new TicTacToeWithComputer();
		if(gameType == GameType.WithAnotherPersonInNetwork)
			return new TicTacToeNetworking();
		return null;
	}
}
