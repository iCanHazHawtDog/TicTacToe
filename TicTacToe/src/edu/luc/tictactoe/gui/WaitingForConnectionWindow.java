package edu.luc.tictactoe.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * @author matt
 *
 */
@SuppressWarnings("serial")
public class WaitingForConnectionWindow extends JPanel {

	static JFrame frame = new JFrame("Waiting for Connection");
	
    public WaitingForConnectionWindow() {
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(200, 300));
    }
    
    public void addComponentsToPane(Container pane) {
    	pane.setLayout(new GridLayout(1,1));
    	pane.add(new JLabel("Waiting For Connection..."));
    }
    
    public static void destroy(){
    	frame.dispose();
    }
    
}