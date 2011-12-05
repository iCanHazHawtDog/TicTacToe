package edu.luc.tictactoe.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.luc.tictactoe.application.MainApplication;
import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.implementation.Person;
import edu.luc.tictactoe.businesslogic.implementation.TicTacToePlay;
import edu.luc.tictactoe.gui.controllerOLD.NetworkUIBridge;

/***
 * 
 * @author Paul Stasiuk
 * 
 * This is the ClientMachine class- this is the class that deals with any communications that the client 
 * machine is sending or receiving.
 * 
 * Note that this class extends Runnable- we run the networking as a thread to keep us from interfearing directly with
 * the main UI thread. 
 * 
 * The typical logic of the networking for the client machine is as follows:
 * 
 * 1. User selects that they wish to be host machine
 * 2. User retrieves the IP address and port of the host machine that they wish to connect to.
 * 3. The user attempts to connect to the host..
 * 4. When the other player connects, the first message to be sent by the server is going to be "hello"-
 * this confirms that a connection with the server has been made, and starts off a "handshake process"
 * 5. The host sends its players name, the client receives and confirms that it has received the name
 * 6. The client sends its players name, the host received and confirms that is has received the name
 * 7. A player is chosen to start the game, the host machine will always choose which player has been chosen to
 * start the game
 * 8. The host sends the client who will be starting
 * 9. If the host is starting, the client waits for a move from the host
 * 10. If the client is starting, the host waits for a move from the client
 * 11. The moves are made via the various methods in this class, such as 
 * 12. If the connection is interrupted, an error is thrown and the game is ended- nothing is recorded as a win or loss
 * 13. If the game is paused, the pause sends a message to show that the game has been paused
 * 14. If the game has ended, the winner is recoded and the connection is terminated.
 * 
 */


public class ClientMachine implements Runnable, INetworking{
	//A verbose boolean, if set to true, then we log to the console
	private boolean verbose= true;
	//The instance of the NetworkUIBridge that is used to bridge this class to the user interface
	private NetworkUIBridge uiBridge;
	//The instance of the socket connection that is used to communicate with the server instance
	private Socket socket;
	//The BufferedReader used to read in the streams from the server and convert them to strings
	private BufferedReader in;
	//The PrintWriter that is used to send strings to the server
	private PrintWriter out;
	//An IP address that is available to this class for use in various methods
	private String ipAddress;
	//The port number of the connection that is available to the entire class
	private int port;
	//The turnSet boolean that is used to filter messages from the server
	private boolean turnSet=false;
	int clientWins=0;
	int hostWins=0;
	
	/***
	 * ClientMachine constructor
	 * 
	 * Note that we automatically dis-allow the setting of physical positions by the uiBridge when the networking first
	 * starts. We do this because we need to make sure that the "handshake" is done and that the client knows who's turn
	 * it is, everyone's names etc...
	 * 
	 * @param ipAddress
	 * @param port
	 * @param uiBridge
	 */
	public ClientMachine(String ipAddress, int port, NetworkUIBridge uiBridge){
		this.ipAddress=ipAddress;
		this.port=port;
		this.uiBridge=uiBridge;
		uiBridge.disAllowPositionSet();
	}
	
	
	
	
	@Override
	public void run(){
		//We check to see if the IP address and port are available 
		//Once we get past there, we are able to actually make the connection to the server, which is
		//going to be threaded. 
		makeConnection(ipAddress,port);
	}
	
	/***
	 * The make connection method creates a connection to the host machine and initiates the process of the network
	 * based game. We start with the initialization of the variables that we are going to need in the process of 
	 * the network based game, then we attempt to connect to the server. If the connection is not possible, we handle this
	 * in the UnknownHost and IOException exceptions.
	 * 
	 * Once the connection has been established at the link layer, we start to communicate with the host machine if the host
	 * machine has sent us a "hello" message. This kicks off the handshake process. The communication's are processed in the
	 * processMessage method which takes in a string and returns a string with the response to a particular message.
	 * 
	 * 
	 * @param ipAddress
	 * @param port
	 */
	public Socket makeConnection(String ipAddress, int port) {
		//The socket used to connect to the server
		socket=null;
		//The PrintWriter
		out = null;
		//The BufferedReader
		in = null;
		//The string that represents the messages coming from this class/from the cleint machine
		String fromPlayer=null;
		//The string that represents the messages coming from the host machine/from the machine that this class would be connecting to
		String fromHost=null;
		    
		try{
			//This is where we actually connect to the host machine
			socket= new Socket(ipAddress, port);
			//Initializing the PrintWriter
			out= new PrintWriter(socket.getOutputStream(),true);
			//Initializing the BufferedReader
		    in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}catch(UnknownHostException e){
			print("The host is unknown!");
		}catch(IOException e){
			print("There was an ioException");	
		}
		print("The connection was accepted, starting the communications");
		try{
			//Tricky. If the host is not responding or sending blank messages, we don't have to handle them, and we break out of this and close the connection
			//We set the fromHost variable to whatever the BufferedReader reads in from the connection
			while((fromHost=in.readLine())!=null){
				//We then proccess the message, but first we filter the message using the gamePlayMessages to make sure that it is not an erroneous message
				if(gamePlayMessages(fromHost) || !turnSet){
					print("From Host: "+fromHost);
					//If the message is not erroneous, then we can process it. Passing the socket, BufferedReader or PrintWriter may not not be necessary
					//The processMessage will respond to the message by interacting with the UI through the UIBridge and/or sending a message back to the
					//host machine.
			    	fromPlayer=processMessage(fromHost,in,out,socket);
			    	print("FromPlayer: "+fromPlayer);
			    	//We then write whatever if coming from the process message to the socket, which transmits it to the host machine.
			    	out.println(fromPlayer);
				}
				//If we say "bye" to the host, or the host says "bye" to us, we break from the while loop, which will close all of our socket connections.
		    	if(fromPlayer.equals("bye") || fromHost.equals("bye")){
		    		break;
		    	}
		    }
			//Closing the socket connection, the BufferedReader, and the PrintWriter
		    destroyConnection();
		    
			}catch(IOException e){
				//Close the connections, and then we tell the UI that the game has been terminated..
				destroyConnection();
				uiBridge.gameTerminated();
				print("IOException:"+e);
		  
		    }		    	
			return socket;
		}
		
		
		/**
		 * A generic sendMessage that can be used to send text messages to the host machine/any machine that the PrintWriter
		 * has been initialized with the outputStream of the socket
		 * 
		 * @param message
		 */
		public boolean sendMessage(String message){
			boolean messageSent=true;
			out.println(message);
			
			return messageSent;
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
			
			if(message.equals("hello")){
				returnMessage="sendNameHost";
			}
			
			if(message.equals("sendNameClient")){
				returnMessage="nameClient:"+ MainApplication.ticTacToePlay.getPlayerOne().getName();
			}
			
			if(message.contains("nameHost:")){
				//Get just the same of the person/prune out the "nameHost:" part of the message
				String name=message.substring(9,message.length());
				print("Setting the name of player 2 to: "+name);
				//Set the playerTwo name to the name of the host player. We are only using the instance of ticTacToePlay on the client machine
				//to keep track of the names.
				MainApplication.ticTacToePlay.setupPlayerTwo(name);
				//Update the UI to the correct names of both parties
				uiBridge.updateNames(name);
				//Return to the host that the client has received the name
				returnMessage="gotNameHost";
			}
		
			if(message.equals("gotNameClient")){
				//We ask the host for who's turn it is
				returnMessage="turn";
			}
			
			if(message.equals("turnHost")){
				
				//If it is the hosts turn, we do not allow any positions to be selected
				uiBridge.disAllowPositionSet();
				//We then set the player turn in the UI/we just set the text to who's turn it is..
				uiBridge.setPlayerTurn("It is "+ MainApplication.ticTacToePlay.getPlayerTwo().getName() + "'s turn!");
				//Toggle the turnSet boolean
				turnSet=true;
				returnMessage="turnGot";
			}
			
			if(message.equals("turnClient")){
				
				//If it is the clients turn, we allow positions to be selected and set
				uiBridge.allowPositionSet();
				//We then set the player turn in the UI/we just set the text to who's turn it is..
				uiBridge.setPlayerTurn("It is "+ MainApplication.ticTacToePlay.getPlayerOne().getName()+ "'s turn!");
				//Toggle the turnSet boolean
				turnSet=true;
				returnMessage="turnGot";
			}
						
			if(message.contains("setPos:")){
				//Position setting in the UI only for the client- we do not 
				int position= Integer.valueOf(message.substring(7,message.length()));
				print("Setting the position on the client machine: "+ position);
				uiBridge.updateClientPositon(position);
				uiBridge.allowPositionSet();
				returnMessage="posSet";
				
					
			}
			
			if(message.contains("clientScore:")){
				//The number of wins that the client has accumulated
				String clientScore=message.substring(12,message.length());
				print("The client's score is: "+clientScore);
				clientWins=Integer.valueOf(clientWins);
			
			
			}
			
			if(message.contains("hostScore:")){
				//The number of wins that the host as accumulated
				String hostScore=message.substring(10,message.length());
				print("The host's score is: "+hostScore);
				hostWins=Integer.valueOf(hostScore);

			}
			
			if(message.equals("posSet")){
				//Setst the players turn
				uiBridge.setPlayerTurn("It is "+ MainApplication.ticTacToePlay.getPlayerTwo().getName()+ "'s turn!");
				
			}
			
			if(message.equals("clientWin")){
				//The client has won
				uiBridge.clientWin();
				
			}
			
			if(message.equals("hostWin")){
				//The host has won
				uiBridge.hostWin();
			}
			
			if(message.equals("draw")){
				//The game has ended in a draw
				uiBridge.draw();
			}
			
			
			if(message.equals("pauseGame")){
				//The host wishes to pause the game
				uiBridge.pauseGame();
			}
			
			if(message.equals("resumeGame")){
				//The host wishes to resume the game
				uiBridge.resumeGame();
			}
			
			
			if(message.equals("bye")){
				//Saying goodbye
				returnMessage="bye";
			}
			
			return returnMessage;
		}
		
		
		
		/**
		 * Sends a message to set a position to the host machine
		 * Also disallows positions to be selectable
		 * 
		 * @param position
		 */
		public boolean setPosition(int position) {
			uiBridge.disAllowPositionSet();
			sendMessage("setPos:"+position);
			

			return false;
		}
		
		
		/**
		 * Sends a message to paused the game to the host machine
		 * 
		 */
		public void pauseGame() {
			sendMessage("pauseGame");
		}

		
		/**
		 * Sends a message to resume the game to the host machine
		 * 
		 */
		public void resumeGame() {
			sendMessage("resumeGame");
			
		}
		
		
		/**
		 * Destroys the connection- not very graceful
		 * 
		 */
		public void destroyConnection() {
			try{
				out.close();
				in.close();
				socket.close();
			}catch(IOException e){
				
			}
			
		}
		
		
		/**
		 * Sends a message to the host to terminate the connection.
		 * 
		 */
		public void sendGameTerminate() {
			sendMessage("bye");
			
		}
		
		
		/**
		 * Checks the messages to ensure that we are only receiving messages that have to do with the gameplay and not something else
		 * 
		 * 
		 */
		public boolean gamePlayMessages(String message) {
			print("checkign the message:"+message);
			String[] messages={"pauseGame","resumeGame","setPos:","hello","sendNameClient","nameHost:","gotNameClient","turnHost","turnClient","posSet","resetGame","pauseGame","resumeGame"
					,"clientScore","hostScore"};
			
			int len= messages.length;
			for(int i=0; i<len; i++){
				if(messages[i].equals(messages[i].substring(0,messages[i].length()))){
					return true;
				}
			}

			return false;
		}


		/**
		 * 
		 * Not implemented in the ClientMachine class
		 * 
		 */
		@Override
		public void sendHostWin() {
			// TODO Auto-generated method stub
			
		}



		/**
		 * 
		 * Not implemented in the ClientMachine class
		 * 
		 */
		@Override
		public void sendClientWin() {
			// TODO Auto-generated method stub
			
		}


		/**
		 * 
		 * Not implemented in the ClientMachine class
		 * 
		 */
		@Override
		public void checkTurn() {
			// TODO Auto-generated method stub
			
		}


		/**
		 * 
		 * Not implemented in the ClientMachine class
		 * 
		 */
		@Override
		public void sendDraw() {
			// TODO Auto-generated method stub
			
		}
		
		
		/**
		 * 
		 * Not implemented in the ClientMachine class
		 * 
		 */
		@Override
		public boolean createServer() {
			// TODO Auto-generated method stub
			return false;
		}

		
		/**
		 * 
		 * Not implemented in the ClientMachine class
		 * 
		 */
		@Override
		public String getIpAddress() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		/**
		 * 
		 * Not implemented in the ClientMachine class
		 * 
		 */
		@Override
		public void sendClientScore() {
			// TODO Auto-generated method stub
			
		}
		
		
		/**
		 * 
		 * Not implemented in the ClientMachine class
		 * 
		 */
		@Override
		public void sendHostScore() {
			// TODO Auto-generated method stub
			
		}
		
		
		/***
		 * 
		 * 
		 * 
		 */
		public void sendWinOrDraw(boolean win) {
			if(win){
				sendMessage("winner:");
			}else{
				sendMessage("draw");
			}
		}
		
		/***
		 * This is a purely diagnostic method. We only use this to print out to the console.
		 * If verbose is false(global variable here), this method does not print anything.
		 * 
		 * @param message
		 */
		private void print(String message){
			if(verbose){
				if(!message.contains("unknown")){
					System.out.println(message);
				}
			}
		}




		




		

}
