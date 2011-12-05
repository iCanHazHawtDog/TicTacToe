package edu.luc.tictactoe.businesslogic;

import java.awt.Component;

public interface ITicTacToePlay {
	public void selectPosition(IPlayer player, int i, int j);
	public void setupPlayerOne(String name);
	public void setupPlayerTwo(String name);
	public void switchPlayer();
	public IPlayer randomStart();
	public IPlayer getPlayerOne();
	public IPlayer getPlayerTwo();
	public IPlayer whoseTurn();
	public void resetGame();
	public void registerPlayers();
	public IPlayer getWinner();
	public int getNumberOfPlays();
}
