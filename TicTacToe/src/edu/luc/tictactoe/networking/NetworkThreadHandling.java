package edu.luc.tictactoe.networking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * @author Paul Stasiuk
 * 
 * The ThreadHandlign class creates a connection to the server and initiates the parining sequence, and sets up the game..
 *
 *Created on 11/25/2011
 *
 */

public class NetworkThreadHandling extends Thread {
	private final String IPADDRESS= "paulstasiuk.dyndns.org";
	private final int PORT=6223;
	private boolean verbose=true;
	private String name;
	
	public NetworkThreadHandling(String name){
		this.name=name;
	}
	
	
	@Override
	public void run(){
		try {
			Socket socket= new Socket(IPADDRESS,PORT);
			PrintWriter output= new PrintWriter(socket.getOutputStream(),true);
			BufferedReader input= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inputline=null;
			String outputline=null;
			ClientComsHandling coms= new ClientComsHandling(name, output);
			
			
			while((inputline=input.readLine())!=null){
				print("From server: "+inputline);
				if(inputline.equals("bye")){
					break;
				}
				outputline= coms.process(inputline);
				if(outputline!=null){
					
					print("From client: "+outputline);
					output.println(outputline);
				}
				
				
			}
			
			input.close();
			output.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	private void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}
	
	
	

}
