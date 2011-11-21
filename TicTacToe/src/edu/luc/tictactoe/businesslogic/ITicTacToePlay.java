package edu.luc.tictactoe.businesslogic;

public interface ITicTacToePlay {
	public boolean setPosition(int i, int j);
	public IPlayer setPlayerOne();
	public IPlayer setPlayerTwo();
	public void switchPlayer();
	public IPlayer randomStart();
	public IPlayer getPlayerOne();
	public IPlayer getPlayerTwo();
}
