package edu.luc.tictactoe.businesslogic;

import edu.luc.tictactoe.businesslogic.implementation.SelectionResult;

public interface ITicTacToePlay {
	public SelectionResult selectPosition(int i, int j);
	public void setupPlayerOne(String name);
	public void setupPlayerTwo(String name);
	public void switchPlayer();
	public IPlayer randomStart();
	public IPlayer getPlayerOne();
	public IPlayer getPlayerTwo();
	public IPlayer whoseTurn();
	public void resetBoard();
	public void registerPlayers();
}
