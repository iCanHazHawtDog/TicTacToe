package edu.luc.tictactoe.businesslogic;

public interface ITicTacToePlay {
	public boolean setPosition(int i, int j);
	public void setPlayerOne(IPlayer player);
	public void setPlayerTwo(IPlayer player);
	public void switchPlayer();
	public IPlayer randomStart();
	public IPlayer getPlayerOne();
	public IPlayer getPlayerTwo();
}
