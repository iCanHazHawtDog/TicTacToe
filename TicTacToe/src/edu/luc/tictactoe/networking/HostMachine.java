package edu.luc.tictactoe.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


import edu.luc.tictactoe.application.MainApplication;
import edu.luc.tictactoe.businesslogic.IBoard;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.implementation.Person;
import edu.luc.tictactoe.businesslogic.implementation.TicTacToePlay;
import edu.luc.tictactoe.gui.controller.MainBoard;

import edu.luc.tictactoe.gui.controller.NetworkUIBridge;

/***
 * 
 * 
 * @author Paul Stasiuk
 * 
 * 
 * The HostMachine class implements Runnable because we do not want to always block the main
 * UI thread. Also implements INetworking because we want the guarantee that we have certain
 * methods such as sendMessage().
 * 
 * The typical logic of the networking is as follows:
 * 
 * 1. User selects that they wish to be host machine
 * 2. User retrieves IP address and port number of their machine instance
 * 3. User waits for client/another player to connect
 * 4. When the other player connects, the first message to be sent by the server is going to be "hello"-
 * this confirms that a connection with the server has been made, and starts off a "handshake process"
 * 5. The host sends its players name, the client receives and confirms that it has received the name
 * 6. The client sends its players name, the host received and confirms that is has received the name
 * 7. A player is chosen to start the game, the host machine will always choose which player has been chosen to
 * start the game
 * 8. The host sends the client who will be starting
 * 9. If the host is starting, the client waits for a move from the host
 * 10. If the client is starting, the host waits for a move from the client
 * 11. The moves are made via the sendMessage(String message) dialog. 
 * 12. If the connection is interrupted, an error is thrown and the game is ended- nothing is recorded as a win or loss
 * 13. If the game is paused, the pause sends a message to show that the game has been paused
 * 14. If the game has ended, the winner is recoded and the connection is terminated.
 *
 */

public class HostMachine implements Runnable, INetworking{
	private boolean verbose=true;
	private NetworkUIBridge uiBridge;
	private final int port=6262;
	private Socket hostSocket;
	private BufferedReader in;
	private ServerSocket serverSocket;
	private int turn=0;
	private PrintWriter out;
	private boolean turnSet=false;

	public HostMachine(NetworkUIBridge uiBridge){
		this.uiBridge= uiBridge;
		
		
	}
	
	@Override
	public void run() {
		createServer();
	}
	
	public String getIpAddress(){
		String ipAddress=null;
		
		try{
			InetAddress addr = InetAddress.getLocalHost();
			ipAddress=addr.getHostAddress().toString();
			//ipAddress=serverSocket.getLocalSocketAddress().toString();
			
		}catch(Exception e){
			ipAddress=getIpAddress();
		}
		
		return ipAddress;
		
		
	}
	
	
	/***
	 * Creates the server for the host machine. Note that it blocks the current thread
	 * until a connection is made, once the connection is made it goes ahead and starts
	 * reading whatever messages are coming from the network/machine that has connected
	 * to it.
	 * 
	 * 
	 */
	public boolean createServer() {
		
		print("Creating a server...");
		serverSocket=null;
		hostSocket=null;
		out = null;
	    in = null;
	    String fromPlayer=null;
	    String fromHost=null;
	    
		try{
			serverSocket= new ServerSocket(port);
			hostSocket=serverSocket.accept();
			uiBridge.fireClientconnected();
			out= new PrintWriter(hostSocket.getOutputStream(),true);
			in= new BufferedReader(new InputStreamReader(hostSocket.getInputStream()));
		}catch(IOException e){
			print("There was an IOException..."+e);
			//TODO handle the issue of another service listening on the same port here..
		}
		
		print("socket accepted. printing hello");
		out.println("hello");
		try{
		
			while((fromPlayer=in.readLine())!=null){
				if(!turnSet || gamePlayMessages(fromPlayer)){
					print("From player: "+fromPlayer);
					fromHost=processMessage(fromPlayer,in, out,hostSocket);
					print("From host: "+ fromHost);
					out.println(fromHost);
				}
				if(fromPlayer.equals("bye") || fromHost.equals("bye")){
	    			break;
	    		}
				
			}
			
			print("closing all the connections..");
			serverSocket.close();
			hostSocket.close();
			in.close();
			out.close();
			
		}catch(IOException e){
			destroyConnection();
			uiBridge.gameTerminated();
			print("There was an IOException..."+e);
			//TODO handle excpetion here
		}
		
		return true;
	}
	
	
	/***
	 * Processes whatever message is being sent in by the [in this case] client. Based on the message
	 * some action can be performed.
	 * 
	 * For example:
	 * 
	 * If the message "name:Paul" is sent over the network.. the second players name is going to be
	 * set to whatever the name is.
	 * 
	 * 
	 */
	public String processMessage(String message, BufferedReader in,PrintWriter out,Socket socket) {
		String returnMessage="unknown";
		
		if(message.equals("sendNameHost")){
			print("returning the name: "+ MainApplication.ticTacToePlay.getPlayerOne().getName() );
			//Send the name of the host player to the client machine
			returnMessage="nameHost:"+ MainApplication.ticTacToePlay.getPlayerOne().getName();
		}
		
		if(message.contains("nameClient:")){
			//The name of the client being set on the host machine
			String name=message.substring(11,message.length());
			print("Setting the name of player2 to: "+name);
			MainApplication.ticTacToePlay.setPlayerTwo(name);
			uiBridge.updateNames(name);
			returnMessage="gotNameClient";
		}
		
		if(message.equals("gotNameHost")){
			returnMessage="sendNameClient";
		}
		
		if(message.equals("turn")){
			//At this point, we have to determine who's turn it is/who goes first- we will refer to the turns as
			//host= turnHost = 0 and client=turnClient=1, the host determines whos turn it actually is

			if(MainApplication.ticTacToePlay.whoseTurn().equals(MainApplication.ticTacToePlay.getPlayerOne())){
				returnMessage="turnHost";
				uiBridge.setPlayerTurn("It is "+ MainApplication.ticTacToePlay.getPlayerOne().getName()+"'s turn!");
				uiBridge.allowPositionSet();
			}else{
				returnMessage="turnClient";
				uiBridge.setPlayerTurn("It is "+ MainApplication.ticTacToePlay.getPlayerTwo().getName()+"'s turn!");
				uiBridge.disAllowPositionSet();
				
				
			}
		
		}
		
		if(message.equals("turnGot")){
			//Client has received and knows who's turn it is.. we do a simple reply here..
			returnMessage="waitingForMove";
			turnSet=false;
			
		}
		
		if(message.contains("setPos:")){
			//We send a set position, which we then handle as if it were a normal set position
			//for the second player, we just interperate the coordinates and do that here
			int position= Integer.valueOf(message.substring(7,message.length()));
			print("Setting the position on the host machine: "+ position);;
			PositionInterpretor pos= new PositionInterpretor(position);
			uiBridge.setPhysicalPosition(position);
			returnMessage="posSet";
			uiBridge.allowPositionSet();
		
		}
		
		if(message.equals("posSet")){
			//If the position has been set, we get ready to send another position
			//uiBridge.allowPositionSet();
		}
		
		
		if(message.equals("pauseGame")){
			uiBridge.pauseGame();
		}
		
		if(message.equals("resumeGame")){
			uiBridge.resumeGame();
		}
		
		if(message.equals("bye")){
			uiBridge.gameTerminated();
			returnMessage="bye";
		}
		
		return returnMessage;
	}
	
	
	/**
	 * Sends a message over the network.
	 * 
	 * @param message
	 * 
	 */
	public boolean sendMessage(String message) {
		out.println(message);
		
		return false;
	}


	/**
	 * This is a method that will set the position of the actual client over the air, this will also
	 * send a message to the machine that this one is connected to indicating that a position has been
	 * set.
	 * 
	 */
	public boolean setPosition(int position) {
		uiBridge.disAllowPositionSet();
		sendMessage("setPos:"+position);
		
		return false;
	}


	/**
	 * Sends a message to paused the game to the client machine
	 * 
	 */
	public void pauseGame() {
		sendMessage("pauseGame");
	}

	
	/**
	 * Sends a message to resume the game on the client machine
	 * 
	 */
	public void resumeGame(){
		sendMessage("resumeGame");
		
	}
	
	
	/**
	 * Checks the messages coming in to ensure that we are only getting messages pertaining to a game, and not something else.
	 * 
	 * 
	 */
	public boolean gamePlayMessages(String message) {
		String[] messages={"pauseGame","resumeGame","setPos:"};
		
		int len= messages.length;
		for(int i=0; i<len; i++){
			if(messages[i].contains(message)){
				return true;
			}
		}
		
		return false;
	}
	
	private void print(String message){
		if(verbose){
			if(!message.contains("unknown")){
				System.out.println(message);
			}
		}
	}


	/**
	 * Destroys the connection- not very graceful.
	 * 
	 */
	public void destroyConnection() {
		try{
			out.close();
			in.close();
			hostSocket.close();
			serverSocket.close();
			
			
		}catch(IOException e){
			//I don't really care if there has been an exception, we are terminating the connection so it doesn't matter.
		}
		
	}
	
	
	/**
	 * Sends a message to terminate the game to the cleint machine
	 * 
	 */
	public void sendGameTerminate() {
		sendMessage("bye");
		
	}

	
	/**
	 * Sends a message that the host machine has won to the client machine
	 * 
	 */
	public void sendHostWin() {
		sendMessage("hostWin");
		uiBridge.hostWin();
		checkTurn();
		
	}

	
	/**
	 * Sends a message that the client machine has won to the client machine
	 * 
	 */
	public void sendClientWin() {
		sendMessage("clientWin");
		uiBridge.clientWin();
		checkTurn();
		
	}
	
	/**
	 * Checks who's turn it is, and sends it to the cleint machine
	 * 
	 * 
	 */
	public void checkTurn(){
		if(MainApplication.ticTacToePlay.whoseTurn().equals(MainApplication.ticTacToePlay.getPlayerOne())){
			sendMessage("turnHost");
			uiBridge.allowPositionSet();
		}else{
			sendMessage("turnClient");
			uiBridge.disAllowPositionSet();			
	
		}
	}
	
	/**
	 * Sends the client machine's score to the client machine.
	 * 
	 * 
	 */
	public void sendClientScore(){
		sendMessage("clientScore:"+MainApplication.ticTacToePlay.getPlayerTwo().getWins());
		
	}
	
	
	/**
	 * Sends the host machine's score the client machine
	 * 
	 * 
	 */
	public void sendHostScore(){
		sendMessage("hostScore:"+MainApplication.ticTacToePlay.getPlayerOne().getWins());
		
	}

	
	/**
	 * Alters the client machine that there has been a draw
	 * 
	 * 
	 */
	public void sendDraw() {
		sendMessage("draw");
		uiBridge.draw();
		checkTurn();
	}
	
	
	/**
	 * Not used in the HostMachine class
	 * 
	 */
	@Override
	public Socket makeConnection(String ipAddress, int port) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
