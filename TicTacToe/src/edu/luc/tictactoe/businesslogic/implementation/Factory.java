package edu.luc.tictactoe.businesslogic.implementation;
/**
 * @author Akrem Osman
 * Class handles initialization of objects.
 */
import java.io.IOException;

import edu.luc.tictactoe.businesslogic.ITicTacToePlay;

public class Factory {

	/*
	 * @param GameType
	 * @return Class to initalize
	 */
	public static ITicTacToePlay CreateTicTacToePlay(GameType gameType)
		throws IOException, ClassNotFoundException
	{
		if(gameType == GameType.WithAnotherPersonInSameComputer)
			return new TicTacToePlay();
		if(gameType == GameType.WithComputer)
			return new TicTacToeWithComputer();
		if(gameType == GameType.WithAnotherPersonInNetwork)
			return new TicTacToeNetworking();
		return null;
	}
}
