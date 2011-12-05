
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

import edu.luc.tictactoe.networking.ClientMessages;


/**
 * @author matt
 *
 */

@SuppressWarnings("serial")
public class BoardNetworking extends JPanel{
	
	public static JFrame frame = new JFrame();
	static JFrame frame2 = new JFrame();
	JTextField text = new JTextField(18);
	JLabel turn;
	private String player1Name;
	private String player2Name;
	private ClientMessages clientMessage;
	private String yourName;
	private static ImageIcon player1Icon;
	private static ImageIcon player2Icon;
	private static boolean isPlayer1=false;
	
	static JLabel numberOfPlays;
		
	public BoardNetworking(String player1Name, String player2Name, String yourName) {
		this.player1Name=player1Name;
		this.player2Name=player2Name;
		this.yourName= yourName;
		setPlayerIcons();
		clientMessage= new ClientMessages();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1200, 800));
		
	}
	
	public void setPlayerIcons(){
		if(player1Name.equals(yourName)){
			isPlayer1=true;
			player1Icon= new ImageIcon(this.getClass().getClassLoader().getResource("edu/luc/tictactoe/gui/resources/images/TicTacToeXIcon.png"));
			player2Icon= new ImageIcon(this.getClass().getClassLoader().getResource("edu/luc/tictactoe/gui/resources/images/TicTacToeOIcon.png"));
		}else{
			isPlayer1=false;
			player2Icon= new ImageIcon(this.getClass().getClassLoader().getResource("edu/luc/tictactoe/gui/resources/images/TicTacToeOIcon.png"));
			player1Icon= new ImageIcon(this.getClass().getClassLoader().getResource("edu/luc/tictactoe/gui/resources/images/TicTacToeXIcon.png"));
		}
		
	}
		
	public void addComponentsToPane(Container pane) {
		JPanel gui = new JPanel(new BorderLayout());
        gui.setBorder(new BevelBorder(BevelBorder.RAISED));
        gui.setLayout(new GridLayout(1,2, 5, 5));
		JPanel labels = new JPanel();
		labels.setLayout(new GridLayout(10,1));
		numberOfPlays= new JLabel("Hello");
		JLabel x = new JLabel(player1Name+ " is X");
		JLabel o = new JLabel(player2Name+ " is O");
		labels.add(new JLabel(""));
//		labels.add(numberOfPlays);
		labels.add(x);
		labels.add(o);

		for (int i = 1; i < 2; i++ ){
    		labels.add(new JLabel(""));
    	}
		turn= new JLabel("Testing1234");
//		labels.add(turn);
//		labels.add(new JButton(new AbstractAction("Reset") {
//    		@Override
//            public void actionPerformed(ActionEvent e) {
//    			
//    			resetBoard();
//    			
//            }
//      	}));
		labels.add(new JLabel(""));
		labels.add(new JLabel(""));
		labels.add(new JLabel(""));
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
		if(isPlayer1){
			buttons.get(getIndex(i,j)).setIcon(player2Icon);
		}else{
			buttons.get(getIndex(i,j)).setIcon(player1Icon);
			
		}
		
	}
	
	
	private JButton createTTTButton(final int i, final int j) {
		JButton btn0 = new JButton();
		buttons.add(btn0);
		btn0.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton)e.getSource();
				if(isPlayer1){
					source.setIcon(player1Icon);
				}else{
					source.setIcon(player2Icon);
				}
				
				clientMessage.setPosition(i*3+j);
				source.setEnabled(false);
				
				updateTurn();
			}
		});
		return btn0;
	}
	
	public static void resetBoard(){
		for(JButton b : buttons){
			b.setEnabled(true);
			b.setIcon(null);
			//numberOfPlays.setText("Number of games Played: " + MainApplication.ticTacToePlay.getNumberOfPlays());
		}
	}
	
	public void updateTurn(){
	
		
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
