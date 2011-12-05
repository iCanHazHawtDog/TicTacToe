package edu.luc.tictactoe.networking;

import java.io.BufferedReader;
import java.io.PrintWriter;

import edu.luc.tictactoe.gui.BoardNetworking;
import edu.luc.tictactoe.gui.BoardSameComputer;
import edu.luc.tictactoe.gui.NetworkWinResultsWindow;
import edu.luc.tictactoe.gui.WaitingForConnectionWindow;

/**
 * 
 * @author Paul Stasiuk
 * 
 * Networking communications handling class that handles all the communications from the server
 * 
 * Created on 11/25/2011
 *
 */
public class ClientComsHandling {
	//A boolean that corresponds this is player1 or not
	private boolean isPlayer1=false;
	private boolean verbose=true;
	private boolean winWindowOpen=false;
	private String yourName;
	static PrintWriter output;
	private String player1Name;
	private String player2Name;
	
	
	public ClientComsHandling(String name, PrintWriter output){
		this.yourName=name;
		this.output=output;
	}
	
	public String process(String message){
		String returnMessage=null;
		if(message.equals("go")){
			returnMessage="name:"+getName();
		}if(message.equals("nameSet")){
			returnMessage="waitingToPlay";
		}if(message.contains("player1Name:")){
			player1Name=message.substring(12,message.length());
			print("Setting player 1 name: "+ player1Name);
			isPlayer1=false;
			advanceToBoard(player1Name, yourName);
			lockBoard();
			
		}if(message.contains("player2Name:")){
			player2Name=message.substring(12,message.length());
			print("Setting player 2 name: "+ player2Name);
			isPlayer1=true;
			advanceToBoard(yourName,player2Name);
			lockBoard();
			
		}if(message.equals("yourTurn")){
			if(winWindowOpen){
				NetworkWinResultsWindow.frame.dispose();
				clearBoard();
				winWindowOpen=false;
				
			}
			unlockBoard();
		
		}if(message.equals("player1Turn")){
			if(winWindowOpen){
				NetworkWinResultsWindow.frame.dispose();
				clearBoard();
				winWindowOpen=false;
			}
			lockBoard();
			
		}if(message.equals("player2Turn")){
			if(winWindowOpen){
				NetworkWinResultsWindow.frame.dispose();
				clearBoard();
				winWindowOpen=false;
			}
			lockBoard();
			
		}if(message.contains("setPos:")){
			int position=Integer.valueOf(message.substring(7,message.length()));
			setBoardPositon(position);
			unlockBoard();
			
		}if(message.contains("posSet")){
			
		}if(message.contains("draw")){
			drawPrompt();
			
		}if(message.equals("player1win")){
			if(isPlayer1){
				winPrompt();
			}else{
				lossPrompt();
			}
			
		}if(message.equals("player2win")){
			if(isPlayer1){
				lossPrompt();
			}else{
				winPrompt();
			}
			
		}if(message.equals("replay")){
			clearBoard();
			promptForReplay();
			
		}
		return returnMessage;
		
	}
	
	private String getName(){
		return yourName;
	}
	
	private void unlockBoard(){
		print("Unlocking the board.");
		BoardNetworking.enableButtons();
	}
	
	private void lockBoard(){
		print("Locking the board");
		BoardNetworking.disableButtons();
	}
	
	private void clearBoard(){
		print("Clearning the board");
		BoardNetworking.resetBoard();
		
	}
	
	private void setOppositionName(){
		print("Setting the oppositions name to: ");
	}
	
	/**
	 * Advances the gameplay to the next window/to the board.
	 * 
	 */
	private void advanceToBoard(String player1Name, String player2Name){
		print("Advancing to gameplay..");
		WaitingForConnectionWindow.destroy();
		BoardNetworking netBoard = new BoardNetworking(player1Name,player2Name,yourName);
		netBoard.addComponentsToPane(BoardNetworking.frame.getContentPane());
		BoardNetworking.frame.pack();
		BoardNetworking.frame.setVisible(true);
		
		
	}
	
	/**
	 * Sets the physical board position
	 * 
	 * @param pos
	 */
	private void setBoardPositon(int pos){
		print("Setting the board position: "+pos);
		PositionInterpretor posCor = new PositionInterpretor(pos);
		BoardNetworking.setButton(posCor.x, posCor.y);
		
	}
	
	/**
	 * Prompts the user to select replay if they have won the game/if they are player1..
	 * 
	 */
	private void promptForReplay(){
		print("Prompting to replay the game.");
		
		
	}
	
	private void drawPrompt(){
		print("Informing the user that there has been a draw.");
		winWindowOpen=true;
		NetworkWinResultsWindow netWin = new NetworkWinResultsWindow(false,true,isPlayer1);
		netWin.addComponentsToPane(NetworkWinResultsWindow.frame.getContentPane());
		NetworkWinResultsWindow.frame.pack();
		NetworkWinResultsWindow.frame.setVisible(true);
		
		
	}
	
	private void winPrompt(){
		winWindowOpen=true;
		print("Informing the user that there has been a win.");
		NetworkWinResultsWindow netWin = new NetworkWinResultsWindow(true,false,isPlayer1);
		netWin.addComponentsToPane(NetworkWinResultsWindow.frame.getContentPane());
		NetworkWinResultsWindow.frame.pack();
		NetworkWinResultsWindow.frame.setVisible(true);
	}
	
	private void lossPrompt(){
		winWindowOpen=true;
		print("Informing the user that there has been a loss.");
		NetworkWinResultsWindow netWin = new NetworkWinResultsWindow(false,false,isPlayer1);
		netWin.addComponentsToPane(NetworkWinResultsWindow.frame.getContentPane());
		NetworkWinResultsWindow.frame.pack();
		NetworkWinResultsWindow.frame.setVisible(true);
	}
	
	private void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}
	
	public static PrintWriter getOutput(){
		return output;
	}
	
	
	
}
