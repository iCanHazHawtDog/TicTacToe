
package edu.luc.tictactoe.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;


/**
 * @author matt
 *
 */

@SuppressWarnings("serial")
public class BoardSameComputer extends JPanel{
	
	static JFrame frame = new JFrame();
	static JFrame frame2 = new JFrame();
	JTextField text = new JTextField(18);
	JLabel turn;
	static JLabel numberOfPlays;
		
	public BoardSameComputer() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1200, 800));
	}
		
	public void addComponentsToPane(Container pane) {
		JPanel gui = new JPanel(new BorderLayout());
        gui.setBorder(new BevelBorder(BevelBorder.RAISED));
        gui.setLayout(new GridLayout(1,2, 5, 5));
		JPanel labels = new JPanel();
		labels.setLayout(new GridLayout(10,1));
		//pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		numberOfPlays = new JLabel("Number of games played: " + MainApplication.ticTacToePlay.getNumberOfPlays());
		JLabel x = new JLabel(MainApplication.ticTacToePlay.getPlayerOne().getName().toUpperCase() + " is X");
		JLabel o = new JLabel(MainApplication.ticTacToePlay.getPlayerTwo().getName().toUpperCase() + " is O");
		labels.add(new JLabel(""));
		labels.add(numberOfPlays);
		labels.add(x);
		labels.add(o);
//		playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
		for (int i = 1; i < 2; i++ ){
    		labels.add(new JLabel(""));
    	}
		turn = new JLabel(MainApplication.ticTacToePlay.whoseTurn().getName().toUpperCase());
		labels.add(turn);
		labels.add(new JButton(new AbstractAction("Reset") {
    		@Override
            public void actionPerformed(ActionEvent e) {
    			
    			resetBoard();
    			
            }
      	}));
		labels.add(new JButton(new AbstractAction("Main Menu") {
    		
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
    			MainMenu.frame.setVisible(true);
            }
      	}));
		labels.add(new JLabel(""));
		gui.add(labels);
		
		JPanel tiles = new JPanel();
		tiles.setLayout(new GridLayout(3,3));
		
		tiles.add(createTTTButton(0, 0));
		tiles.add(createTTTButton(0, 1));
		tiles.add(createTTTButton(0, 2));
		tiles.add(createTTTButton(1, 0));
		tiles.add(createTTTButton(1, 1));
		tiles.add(createTTTButton(1, 2));
		tiles.add(createTTTButton(2, 0));
		tiles.add(createTTTButton(2, 1));
		tiles.add(createTTTButton(2, 2));
		
		gui.add(tiles);
		pane.add(gui);
    }


	private static ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	private static int getIndex(int i, int j){
		return i*3+j;
	}
	
	public static void setButton(int i, int j){
		buttons.get(getIndex(i,j)).setEnabled(false);
		buttons.get(getIndex(i,j)).setIcon(MainApplication.ticTacToePlay.getPlayerTwo().getIcon());
	}
	
	
	private JButton createTTTButton(final int i, final int j) {
		JButton btn0 = new JButton();
		buttons.add(btn0);
		btn0.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton)e.getSource();
				source.setIcon(MainApplication.ticTacToePlay.whoseTurn().getIcon());
				source.setEnabled(false);
				MainApplication.ticTacToePlay.selectPosition(MainApplication.ticTacToePlay.whoseTurn(), i, j);
				updateTurn();
			}
		});
		return btn0;
	}
	
	public static void resetBoard(){
		for(JButton b : buttons){
			b.setEnabled(true);
			b.setIcon(null);
			numberOfPlays.setText("Number of games Played: " + MainApplication.ticTacToePlay.getNumberOfPlays());
		}
	}
	
	public void updateTurn(){
		turn.setText(MainApplication.ticTacToePlay.whoseTurn().getName().toUpperCase());
		
	}
	
	public static void disableButtons(){
		for(JButton b: buttons){
			b.setEnabled(false);
		}
	}
	
	public static void enableButtons(){
		for(JButton b: buttons){
			if(b.getIcon() == null){
				b.setEnabled(true);
			}
		}
	}
}
