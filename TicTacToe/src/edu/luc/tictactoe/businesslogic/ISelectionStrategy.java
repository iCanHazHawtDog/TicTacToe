package edu.luc.tictactoe.businesslogic;
/**
 * 
 * @author Omo W
 *
 */
public interface ISelectionStrategy {

	IPair<Integer, Integer> execute(IBoard board);

}
