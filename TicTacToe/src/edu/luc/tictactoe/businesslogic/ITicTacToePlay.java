package edu.luc.tictactoe.businesslogic;

public interface ITicTacToePlay {
	public void selectPosition(IPlayer player, int i, int j);
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
