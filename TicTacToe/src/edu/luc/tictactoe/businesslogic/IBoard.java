package edu.luc.tictactoe.businesslogic;

/***
 * @author Akrem Osman
 * 
 * Board interface
 */
public interface IBoard {
	public boolean isFull();
	public boolean isSelected(int i, int j);
	public boolean selectPosition(IPlayer player, int i, int j);
	public boolean checkWin(IPlayer player);
	public boolean resetBoard();
}
