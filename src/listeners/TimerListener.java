/*
 * Peterson Phe
 * Assignment 6 - Tetris
 * TCSS 305 - Winter 2016
 */

package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Board;

/**
 * Listener for the game timer.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public class TimerListener implements ActionListener 
{
    /** Holds reference to the board. */
    private final Board myBoard;
    
    /**
     * Constructor that initializes instance fields to arguments.
     * 
     * @param theBoard reference to Tetris board
     */
    public TimerListener(final Board theBoard)
    {
        super();
        myBoard = theBoard;
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent)
    {
        myBoard.step();
    }
}
