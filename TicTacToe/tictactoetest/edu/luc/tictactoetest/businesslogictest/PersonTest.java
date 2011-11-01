package edu.luc.tictactoetest.businesslogictest;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.tictactoe.businesslogic.implementation.*;

public class PersonTest {

	Person person;
	
	@Before
	public void setUp() throws Exception {
		person = new Person();	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetName() {
		String name = "John";
		person.setName(name);
		assertEquals(person.getName(), name);
	}

	@Test
	public void testSetName() {
		String name = "Sam";
		person.setName(name);
	}

	@Test
	public void testGetWins() {
		person.wins = 4;		 
		 assertEquals(person.getWins(), person.wins);
		
	}
	
	@Test
	public void testGetscore() {
		/*person.wins = 4;	
		person.score = person.wins; 
		assertEquals(person.getscore(), person.wins);*/
	}

	@Test
	public void testSetScore() {
		person.wins = 4;	
		//person.setScore();
		
	}

	@Test
	public void testScorePercentage() {
		person.wins = 2;
		person.numberOfPlays = 6;
		int per = (100 * (person.wins)/(person.numberOfPlays));
	   // Assert.assertEquals(person.scorePercentage(), per);
	    }
}
