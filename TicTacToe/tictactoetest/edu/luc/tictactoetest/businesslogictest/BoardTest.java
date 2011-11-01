package edu.luc.tictactoetest.businesslogictest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.implementation.Board;
import edu.luc.tictactoe.businesslogic.implementation.Person;

public class BoardTest{

	private IBoard board;
	@Before
	public void setup(){
		board = new Board();
	}
	
	@Test
	public void isSelectedTest() {
		board.selectPosition(new Person(), 1, 1);
		assertTrue(board.isSelected(1, 1));
		
		assertFalse(board.isSelected(2, 2));
	}
	
	@Test
	public void isFullTest(){
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				board.selectPosition(new Person(), i, j);
		assertTrue(board.isFull());
		
		board.resetBoard();
		assertFalse(board.isFull());
		
		board.selectPosition(new Person(), 1, 1);
		assertFalse(board.isFull());
	}
	
	@Test
	public void resetBoardTest(){
		board.selectPosition(new Person(), 1, 1);
		assertTrue(board.isSelected(1, 1));
		board.resetBoard();
		assertFalse(board.isSelected(1, 1));
	}
	
	@Test
	public void checkWinTest(){
		IPlayer player = new Person();
		for(int column=0;column<3;column++){
			board.selectPosition(player, 0, column);
			if(column<2) 
				assertFalse(board.checkWin(player));
		}
		assertTrue(board.checkWin(player));
		
		board.resetBoard();
		for(int row=0;row<3;row++)
			board.selectPosition(player, row, 1);
		assertTrue(board.checkWin(player));
		
		board.resetBoard();
		for(int diagonal=0;diagonal<3;diagonal++)
			board.selectPosition(player, diagonal, diagonal);
		assertTrue(board.checkWin(player));
		
		board.resetBoard();
		board.selectPosition(player, 0, 2);
		board.selectPosition(player, 1, 1);
		assertFalse(board.checkWin(player));
		
		board.selectPosition(player, 2, 0);
		assertTrue(board.checkWin(player));
		
	}

}
