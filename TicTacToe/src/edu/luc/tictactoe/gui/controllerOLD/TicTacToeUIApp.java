/*
 * TicTacToeUIApp.java
 */

package edu.luc.tictactoe.gui.controllerOLD;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;



/**
 * @author Paul Stasiuk
 * 
 * The main class of UI aspect of the application.
 */
public class TicTacToeUIApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new TicTacToeUIView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of TicTacToeUIApp
     */
    public static TicTacToeUIApp getApplication() {
        return Application.getInstance(TicTacToeUIApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(TicTacToeUIApp.class, args);
    }
}
