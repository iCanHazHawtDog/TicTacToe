package edu.luc.tictactoe.networking;
import java.io.PrintWriter;

import edu.luc.tictactoe.gui.BoardNetworking;

/**
 * 
 * @author Paul Stasiuk
 * 
 * A class that fires off client messages to the 
 * 
 * Created on 11/25/2011
 *
 */


public class ClientMessages {
	private PrintWriter output;

	public ClientMessages(){
		output=ClientComsHandling.getOutput();
	}

	public void setPosition(int pos){
		output.println("setPos:"+pos);
		BoardNetworking.disableButtons();
	}
	
	public void endGame(){
		output.println("bye");
	}
	
	public void replayGame(){
		output.println("replay");
	}
	

}
