package edu.luc.tictactoe.gui.controller;

import java.awt.Event;

import javax.swing.JButton;

import edu.luc.tictactoe.businesslogic.IPlayer;

/***
 * @author Paul Stasiuk
 * 
 * This is a class that bridges the UI controller/controls to the Networking class(es).
 * The point of this is to act as an intermediary and only allow certain actions that
 * the UI can perform be accessed from the networking class(es).
 * 
 * For example; All I want the networking code to be able to change is the game
 * board that is actually displayed or lock out a player from making any changes from game board
 * to prevent any 
 * 
 */

public class NetworkUIBridge {
	private MainBoard mainBoard;
	
	JButton[] buttons={mainBoard.grid0Btn, mainBoard.grid1Btn ,mainBoard.grid2Btn, mainBoard.grid3Btn, mainBoard.grid4Btn, mainBoard.grid5Btn,
			mainBoard.grid6Btn, mainBoard.grid7Btn, mainBoard.grid8Btn};
	
	boolean[] buttonsToEnable={false,false,false,false,false,false,false,false,false};
	
	/**
	 * The constructor for the NetworkUIBridge. Takes in a MainBoard as a parameter.
	 * 
	 * @param mainBoard
	 */
	public NetworkUIBridge(MainBoard mainBoard){
		this.mainBoard=mainBoard;
		
	}
	
	
	/**
	 * Pauses the game.
	 * 
	 */
	public void pauseGame(){
		
		mainBoard.jPanel1.setVisible(false);
		mainBoard.jPanel2.setVisible(false);
		mainBoard.resumeGameBtn.setVisible(false);
		mainBoard.resumeGameBtn.setEnabled(false);
		mainBoard.jPanel3.setVisible(true);
	}
	
	
	/**
	 * Resumes the game.
	 * 
	 */
	public void resumeGame(){
		mainBoard.resume();		
	}
	
	
	/**
	 * Sets the physical position on the board by marking the position in the MainBoard and allowing the positions to be set.
	 * 
	 * @param pos
	 * @return boolean
	 */
	public boolean setPhysicalPosition(int pos){
		allowPositionSet();
		mainBoard.markPosition(pos, buttons[pos], false);
		return true;
	}
	
	
	/**
	 * Allows the positions on the board to be let.
	 * 
	 */
	public void allowPositionSet(){
		
		for(int i=0; i<buttons.length; i++){
			buttons[i].setEnabled(mainBoard.selectable[i]);
			mainBoard.networkSelectable[i]=true;
		}
		
	}
	
	
	/**
	 * Disallows the positions on the board to be set.
	 */
	public void disAllowPositionSet(){
		//mainBoard.waitForMove=true;
		for(int i=0; i<buttons.length; i++){
			buttons[i].setEnabled(false);
			mainBoard.networkSelectable[i]=false;
		}
	}
	
	
	/**
	 * Terminates the game and takes the user back to the main menu.
	 * 
	 */
	public void gameTerminated(){
		mainBoard.terminateGame();
	}
	
	
	/**
	 * Enables the play button on the host machine
	 * 
	 */
	public void fireClientconnected(){
		IfHostMachine.playBtn.setEnabled(true);
		IfHostMachine.playBtn.setVisible(true);
		IfHostMachine.waitingText.setText("Player2 has joined! Play!");
	}
	
	
	/**
	 * Updates the second players name and type.
	 * 
	 * @param name
	 */
	public void updateNames(String name){
		MainBoard.player2Label.setText(name + " Is: O");
		
	}
	
	
	/**
	 * Updates the position of the client machine- Used when the host machine makes a move.
	 * 
	 * @param pos
	 */
	public void updateClientPositon(int pos){
		mainBoard.markOpposition(pos, buttons[pos]);
		mainBoard.selectable[pos]=false;
	}
	
	
	/**
	 * Displays a host's win.
	 * 
	 */
	public void hostWin(){
		mainBoard.winForm(false);
		mainBoard.resetButtons();
		mainBoard.setNoneClickable();
		disAllowPositionSet();
	}
	
	
	/**
	 * Displays a clients win.
	 * 
	 */
	public void clientWin(){
		mainBoard.winForm(true);
		mainBoard.resetButtons();
		mainBoard.setNoneClickable();
		disAllowPositionSet();
		
	}
	
	
	/**
	 * Displays a draw.
	 * 
	 */
	public void draw(){
		mainBoard.drawForm(false);
		mainBoard.resetButtons();
		mainBoard.setNoneClickable();
		disAllowPositionSet();
	}
	
	
	/**
	 * Sets who turn it currently is.
	 * 
	 * @param whosTurn
	 */
	public void setPlayerTurn(String whosTurn){
		MainBoard.currentTurnLabel.setText(whosTurn);
	}
	

	
}
