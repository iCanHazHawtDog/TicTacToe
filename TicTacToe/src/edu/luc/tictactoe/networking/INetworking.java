package edu.luc.tictactoe.networking;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 
 * @author Paul Stasiuk
 *
 */
public interface INetworking {
	public Socket makeConnection(String ipAddress, int port);
	public String processMessage(String message,BufferedReader in,PrintWriter out, Socket socket);
	public boolean sendMessage(String message);
	public boolean createServer();	
	public String getIpAddress();
	public boolean setPosition(int position);
	public void pauseGame();
	public void resumeGame();
	public boolean gamePlayMessages(String message);
	public void destroyConnection();
	public void sendGameTerminate();
	public void sendHostWin();
	public void sendClientWin();
	public void sendDraw();
	public void checkTurn();
	public void sendClientScore();
	public void sendHostScore();
	
	

}
