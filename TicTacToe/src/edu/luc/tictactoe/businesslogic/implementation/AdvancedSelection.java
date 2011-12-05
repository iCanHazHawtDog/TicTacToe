package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPair;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.ISelectionStrategy;

/***
 * @author Akrem Osman
 *
 ***/

public class AdvancedSelection extends AbstractSelectionStrategy
{
	@Override
	public IPair<Integer, Integer> execute(Computer player) {
		IPair<Integer, Integer> position = super.otherPlayerWouldNotWinIfSelected(player);
		if(position == null)
			return randomSelect(player);
		return position;
	}
}
