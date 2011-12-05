package edu.luc.tictactoe.businesslogic;

import java.util.ArrayList;
import java.util.Map;

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
	public ArrayList<IPair> NotSelectedPositions();
	public IPair<Integer, Integer> otherPlayerWouldNotWinIfSelected(IPlayer player);
	public boolean canWin(IPlayer player, IPair<Integer, Integer> position);
}
