
package edu.luc.tictactoe.gui;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.border.EmptyBorder;


import edu.luc.tictactoe.businesslogic.implementation.Factory;
import edu.luc.tictactoe.businesslogic.implementation.GameType;
import edu.luc.tictactoe.gui.resources.ImagePanel;
import edu.luc.tictactoe.networking.ClientMessages;

/**
 * @author matt
 *
 */
@SuppressWarnings("serial")
public class NetworkWinResultsWindow extends JPanel {

	public static JFrame frame;
	private WindowAdapter windowAdapter=null;
	private boolean win;
	private String winnerName;
	private boolean isPlayer1;
	private ClientMessages clientMessages;
	private boolean isDraw;
	
    public NetworkWinResultsWindow(boolean win, boolean isDraw, boolean isPlayer1) {
    	this.win=win;
    	this.isDraw=isDraw;
    	this.isPlayer1=isPlayer1;
    	frame= new JFrame();
    	BoardSameComputer.frame.setEnabled(false);
    	clientMessages=new ClientMessages();
    	
		// frame.setDefaultCloseOperation(JFrame.);
    	
    	windowAdapter= new WindowAdapter(){
    		
    		@Override
    		public void windowClosing(WindowEvent e){
    			super.windowClosing(e);
    			frame.dispose();
    			BoardSameComputer.frame.setEnabled(true);
    		}
    		
    	};
    	
    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(windowAdapter);
		
		 frame.setPreferredSize(new Dimension(500, 300));
    }
    
    public void addComponentsToPane(Container pane) {
    	pane.setLayout(new GridLayout(6, 1, 5, 5));
    	pane.add(new JLabel(""));
    	if(isDraw){
    		JLabel draw = new JLabel("It is a Draw!");
    		draw.setAlignmentX(Component.CENTER_ALIGNMENT);
    		pane.add(draw);
    		pane.add(new JLabel(""));
	    	if(isPlayer1){
		    	pane.add(new JButton(new AbstractAction("Continue") {
		    		
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	BoardSameComputer.resetBoard();
		            	clientMessages.replayGame();
		            	frame.dispose();
		            }
		      	}));
	    	}else{
	    		pane.add(new JLabel("Waiting for the first player to continue!"));
	    	}
	    	pane.add(new JLabel(""));
    	} if(win) {
    		JLabel winner = new JLabel("You have won!");
    		winner.setAlignmentX(Component.CENTER_ALIGNMENT);
    		pane.add(winner);
    		pane.add(new JLabel(""));
		    pane.add(new JButton(new AbstractAction("Continue") {
		    		
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	BoardSameComputer.resetBoard();
		            	clientMessages.replayGame();
		            	frame.dispose();
		            }
		      	}));
	    	
	    	pane.add(new JLabel(""));
    	}else{
    		JLabel loss = new JLabel("You have lost!");
    		loss.setAlignmentX(Component.CENTER_ALIGNMENT);
    		pane.add(loss);
    	}
    	
	    	
    	
    }
    
    public void Dispose(){
    	BoardSameComputer.frame.setEnabled(true);
    }
}