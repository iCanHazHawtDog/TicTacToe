package edu.luc.tictactoe.businesslogic;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @author Omowumi Adegbite, Akrem Osman
 * 
 * The player interface
 */

public interface IPlayer {
	public String getName();
	public void setName(String name);
	public void incrementNumberOfPlays();
	public void incrementNumberOfWins();
	public int getNumberOfPlays();
	public int getWins();
	public void setIcon(Icon icon);
	public Icon getIcon();
}
