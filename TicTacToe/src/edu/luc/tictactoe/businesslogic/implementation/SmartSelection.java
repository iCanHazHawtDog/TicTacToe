package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPair;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.ISelectionStrategy;

public class SmartSelection extends AbstractSelectionStrategy
{
	@Override
	public IPair<Integer, Integer> execute(Computer player) {
		IPair<Integer, Integer> winPos = positionForWin(player);
		if(winPos != null)
			return winPos;
		
		IPair<Integer, Integer> position = otherPlayerWouldNotWinIfSelected(player);
		if(position != null)
			return position;
		
		return super.randomSelect(player);
	}

}
