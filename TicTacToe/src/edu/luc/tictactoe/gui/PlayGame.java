/**
 * 
 */
package edu.luc.tictactoe.gui;

import javax.swing.Box;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.border.EmptyBorder;

import edu.luc.tictactoe.businesslogic.implementation.Factory;
import edu.luc.tictactoe.businesslogic.implementation.GameType;
import edu.luc.tictactoe.businesslogic.implementation.TicTacToeWithComputer;


/**
 * @author matt
 *
 */

@SuppressWarnings("serial")
public class PlayGame extends JPanel{
	
	static JFrame frame = new JFrame();
	JTextField text = new JTextField(18);
	
	public PlayGame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 300));
	}
	
	
	public void addComponentsToPane(Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		Dimension minSize = new Dimension(5, 25);
		Dimension prefSize = new Dimension(5, 25);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, 25);
		pane.add(new Box.Filler(minSize, prefSize, maxSize));
		
		JLabel playerName = new JLabel("Player Name");
		playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
    	pane.add(playerName);
    	
    	JPanel tfConstrain = new JPanel(new FlowLayout(FlowLayout.CENTER));
        text.addActionListener(new AbstractAction() {
        	
            public void actionPerformed(ActionEvent e) {
                System.out.println("Text=" + text.getText());
              }
        });
        tfConstrain.add(text);
        pane.add(tfConstrain);
        
        JPanel center = new JPanel(new GridLayout(0,1,10,10));
        center.add(new JButton(new AbstractAction("On This Machine vs. Human") {
    		
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
    			HumanPlayerTwoWindow matt = new HumanPlayerTwoWindow();
    			matt.addComponentsToPane(HumanPlayerTwoWindow.frame.getContentPane());
    			HumanPlayerTwoWindow.frame.pack();
    			HumanPlayerTwoWindow.frame.setVisible(true);
    			MainApplication.ticTacToePlay = Factory.CreateTicTacToePlay(GameType.WithAnotherPersonInSameComputer);
    			MainApplication.ticTacToePlay.setupPlayerOne(text.getText());
            }
      	}));
        center.add(new JButton(new AbstractAction("On This Machine vs. AI") {
    		
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
            	MainApplication.ticTacToePlay = Factory.CreateTicTacToePlay(GameType.WithComputer);
    			MainApplication.ticTacToePlay.setupPlayerOne(text.getText());
    			BoardSameComputer matt = new BoardSameComputer();
    			matt.addComponentsToPane(BoardSameComputer.frame.getContentPane());
    			BoardSameComputer.frame.pack();
    			BoardSameComputer.frame.setVisible(true);
    			
            }
      	}));
        center.add(new JButton(new AbstractAction("Network Based") {
    		
            @Override
            public void actionPerformed(ActionEvent e) {
          	  
            }
      	}));
        center.add(new JButton(new AbstractAction("Main Menu") {
    		
            @Override
            public void actionPerformed(ActionEvent e) {
    			frame.setVisible(false);
    			MainMenu.frame.setVisible(true);
            }
      	}));
        center.setBorder(new EmptyBorder(40,70,40,70));
        pane.add(center);
    	pane.add(new JLabel(""));

    }
    
}
