package edu.luc.tictactoe.businesslogic.implementation;

import java.security.KeyPair;

import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPair;
import edu.luc.tictactoe.businesslogic.ISelectionStrategy;

/**
 * This subclass is for the implementation of the Computer TicTacToe player
 * It's base class is the Player class
 * 
 */
public class Computer extends Player {
	
	private ISelectionStrategy selectionStrategy;
	private IBoard board;
	
	public Computer(IBoard board, DifficultyLevel difficultyLevel){
		this.board = board;
		setSelectionStrategy(difficultyLevel);
	}
	
	public void setSelectionStrategy(DifficultyLevel difficultyLevel){
		if(difficultyLevel == DifficultyLevel.Easy)
			this.selectionStrategy = new RandomSelection();
		else if(difficultyLevel == DifficultyLevel.Hard)
			this.selectionStrategy = new SmartSelection();
		else this.selectionStrategy = new AdvancedSelection();
	}
	
	public IPair<Integer, Integer> selectPosition(){
		return selectionStrategy.execute(this);
	}
	
	public IBoard getBoard(){
		return board;
	}
}
