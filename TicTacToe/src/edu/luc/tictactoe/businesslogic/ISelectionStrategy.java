package edu.luc.tictactoe.businesslogic;

public interface ISelectionStrategy {

	IPair<Integer, Integer> execute(IBoard board);

}
