package edu.luc.tictactoe.businesslogic;

/**
 * @author - Akrem Osman
 * 
 * High Score interface
 * 
 */

public interface IHighScore {
	int getScore(IPlayer player);
	int getScorePercentage(IPlayer player);
}
