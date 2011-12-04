package edu.luc.tictactoe.businesslogic;

import edu.luc.tictactoe.businesslogic.implementation.Computer;

/**
 * 
 * @author Omo W
 *
 */
public interface ISelectionStrategy {

	IPair<Integer, Integer> execute(Computer player);

}
