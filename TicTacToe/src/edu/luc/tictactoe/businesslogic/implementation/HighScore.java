package edu.luc.tictactoe.businesslogic.implementation;

import edu.luc.tictactoe.dao.DBInteraction;

/**
 * @author - Akrem Osman, Subhash Pant
 */

import edu.luc.tictactoe.businesslogic.IHighScore;
import edu.luc.tictactoe.businesslogic.IPlayer;

public class HighScore implements IHighScore {

	/***
	 * This method defines how scores for players is determined. For know it based on the number of wins
	 */
	public int getScore(IPlayer player){
		return player.getWins();
	}
	
	/***
	 * percentage of wins to number of plays
	 */
	public int getScorePercentage(IPlayer player){
		return (player.getWins()*100)/player.getNumberOfPlays();
	}
	
	/***
	 * returns i number of highest scorers
	 */
	
	public static Object[][] highScoreTable(int i){		
		IPlayer[] matt = new IPlayer[i];
		DBInteraction dbIter = new DBInteraction();
		matt = dbIter.getHighestScorers(i);
		HighScore hs = new HighScore();
    	Object[][] data = new Object[i][2];
    	for(int k = 0; k < data.length; k++){
    		if(matt[k]!=null){
    		data[k][0] = matt[k].getName();
    		data[k][1] = hs.getScore(matt[k]);
    		}
    	}
    	return data;
	}
}


