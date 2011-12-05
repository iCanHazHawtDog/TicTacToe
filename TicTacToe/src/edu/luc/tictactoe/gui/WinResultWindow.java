
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

/**
 * @author matt
 *
 */
@SuppressWarnings("serial")
public class WinResultWindow extends JPanel {

	public static JFrame frame;
	private WindowAdapter windowAdapter=null;
	
    public WinResultWindow() {
    	frame= new JFrame();
    	BoardSameComputer.frame.setEnabled(false);
    	
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
    	if(MainApplication.ticTacToePlay.getWinner() == null){
    		JLabel draw = new JLabel("It is a Draw!");
    		draw.setAlignmentX(Component.CENTER_ALIGNMENT);
    		pane.add(draw);
    	} else {
    		JLabel winner = new JLabel(MainApplication.ticTacToePlay.getWinner().getName() +" Has Won!");
    		winner.setAlignmentX(Component.CENTER_ALIGNMENT);
    		pane.add(winner);
    	}
    	JLabel plays = new JLabel("Number of Plays: " + MainApplication.ticTacToePlay.getNumberOfPlays());
    	plays.setAlignmentX(Component.CENTER_ALIGNMENT);
    	pane.add(plays);
    	pane.add(new JLabel(""));
    	pane.add(new JButton(new AbstractAction("Continue") {
    		
            @Override
            public void actionPerformed(ActionEvent e) {
            	BoardSameComputer.resetBoard();
            	BoardSameComputer.frame.setEnabled(true);
            	frame.dispose();
            }
      	}));
    	pane.add(new JLabel(""));
    	
    }
    
    public void Dispose(){
    	BoardSameComputer.frame.setEnabled(true);
    }
}