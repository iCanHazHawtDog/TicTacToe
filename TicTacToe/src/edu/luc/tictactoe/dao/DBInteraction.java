package edu.luc.tictactoe.dao;
/*
 * @Author: Subhash Pant / ICanHazHatDawg! (spelling error!)
 */
//Import Statements
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import edu.luc.tictactoe.businesslogic.IHighScore;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.implementation.*;

/**
 * The DBInteraction class makes changes and interacts with the datbaase
 * to update the high scores and push out the 
 * 
 * @author Subhash Pant
 *
 */
public class DBInteraction{
	
	protected Connection conn = null;
	protected Statement smt = null;
	static final String url = "jdbc:mysql://ichhd.dyndns.org:3309/";
	static final String user = "subhash";
	static final String password = "subhash";
	static final String dbName = "tikTakToe";	
	private IHighScore highScoreInstance = new HighScore();
	
	/**
	 * Sets Connection
	 */
	public Connection getConnection()
	{
		try
		{
			conn = DriverManager.getConnection(url+dbName, user, password);
			
		}
		catch(CommunicationsException e)
		{
			
		}
		catch(SQLException ex)
		{
			System.out.println("Connection could not be established " + ex.toString());
			
		}
		return conn;
	}
	
	/**
	 * Allows users to register in the database
	 */
	public boolean registerUser(IPlayer playerOne)
	{
		Connection conn = getConnection();
		try {
			CallableStatement cs = conn.prepareCall("{CALL registerUsers(?, ?, ?, ?)}");			
			cs.setString(1, playerOne.getName());
			cs.setInt(2, playerOne.getWins());
			cs.setInt(3, playerOne.getNumberOfPlays());
			cs.setInt(4, highScoreInstance.getScore(playerOne));
			cs.executeQuery();	
									
		} 
		catch(NullPointerException e)
		{
			System.out.println(e.getMessage());
		}
		catch(CommunicationsException e)
		{
			System.out.println(e.getMessage());
		}
		catch (SQLException e) {
			System.out.println("Error occured while registering." + e.getMessage());
		}	
		return true;
	}
	/**
	 * Gets Highest Scorers of a given person
	 * param - @num takes the number of resultsets to return
	 * return - returns person object (array)
	 */
	//public Person[] getHighestScorers(int num)
	public Person[] getHighestScorers(int i)
	{ 
		DBInteraction d = new DBInteraction();
		Person[] p = new Person[i];
		//p[0] = new Person();
		
		int counter = 0;
		Connection conn = d.getConnection();
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("{CALL sp_returnUsers}");
			
			//for(int counter = 0; counter < i && counter < rs.; counter++)
			while (rs.next() && counter < i)
			{	
				
					p[counter] = new Person();
					p[counter].setName(rs.getString("NAME"));
					p[counter].setWins(rs.getInt("wins"));
					p[counter].setNumberOfPlays(rs.getInt("plays"));
					counter++;			
				
			}
			
		}
		catch(NullPointerException e)
		{
			System.out.println(e.getMessage());
		}
		catch(CommunicationsException e)
		{
			System.out.println(e.getMessage());
		}
		
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}		
		
		return p;
	}
	
	

}
	




