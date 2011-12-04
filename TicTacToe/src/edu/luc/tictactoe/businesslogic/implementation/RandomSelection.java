package edu.luc.tictactoe.businesslogic.implementation;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPair;
import edu.luc.tictactoe.businesslogic.ISelectionStrategy;

public class RandomSelection implements ISelectionStrategy {
	@Override
	public IPair<Integer, Integer> execute(IBoard board) {
		//get the unselected positions from the board
		ArrayList<IPair> positions = board.NotSelectedPositions();
		Random generator = new Random();
		// Randomly select a position 
		int n = generator.nextInt(positions.size());
		return positions.get(n);
	}
}
