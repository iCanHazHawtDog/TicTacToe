package edu.luc.tictactoe.businesslogic.implementation;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPair;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.ISelectionStrategy;
/***
 * @author Akrem Osman
 *
 ***/

public class RandomSelection extends AbstractSelectionStrategy {
	@Override
	public IPair<Integer, Integer> execute(Computer player) {
		return super.randomSelect(player);
	}
}
