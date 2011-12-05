package edu.luc.tictactoe.businesslogic.implementation;

import java.util.ArrayList;
import java.util.Random;

import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPair;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.ISelectionStrategy;

public abstract class AbstractSelectionStrategy implements ISelectionStrategy
{
	@Override
	public abstract IPair<Integer, Integer> execute(Computer player);
	
	public IPair<Integer, Integer> randomSelect(Computer player) {
		//get the unselected positions from the board
		ArrayList<IPair<Integer, Integer>> positions = player.getBoard().NotSelectedPositions();
		Random generator = new Random();
		// Randomly select a position 
		int n = generator.nextInt(positions.size());
		return positions.get(n);
	}

	public IPair otherPlayerWouldNotWinIfSelected(Computer player){
		return player.getBoard().otherPlayerWouldNotWinIfSelected(player);
	}
	
	public IPair<Integer, Integer> positionForWin(Computer player){
		ArrayList<IPair<Integer, Integer>> positions = player.getBoard().NotSelectedPositions();
		for(IPair<Integer, Integer> position : positions){
			if(player.getBoard().canWin(player, position))
				return position;
		}
		return null;
	}
}
