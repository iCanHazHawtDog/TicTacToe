package edu.luc.tictactoe.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * The DB Connection class makes a connection to the MySQL server that houses our High Scores database
 * 
 * @author Subhash Pant
 *
 */
public class DBConnection {
	
	/**
	 * Creates the connection to the database
	 * 
	 * @return Connection
	 */
    public static Connection getSimpleConnection()
    {
    	String DB_CONN_STRING = "jdbc:mysql://ichhd.dyndns.org:3309/tikTakToe";
    	String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	    String USER_NAME = "subhash";
	    String PASSWORD = "subhash";    
	    Connection result = null;
	    
	    try 
	    {
	       Class.forName(DRIVER_CLASS_NAME).newInstance();
	    }
	    catch (Exception ex)
	    {
	       log("Check classpath. Cannot load db driver: " + DRIVER_CLASS_NAME);
	    }
	
	    try 
	    {
	      result = DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);
	    }
	    catch (SQLException e)
	    {
	       log( "Driver loaded, but cannot connect to db: " + DB_CONN_STRING);
	    }
		
	    return result;
	}
    private static void log(Object aObject) {
		System.out.println(aObject);
		
	}
}