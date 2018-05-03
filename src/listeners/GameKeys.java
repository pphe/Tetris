/*
 * Peterson Phe
 * Assignment 6 - Tetris
 * TCSS 305 - Winter 2016
 */

package listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import model.Board;
import view.Tetris;

/**
 * Handles key presses for Tetris.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public class GameKeys extends Observable implements KeyListener
{   
    /** Default left key. */
    private static final int DEFAULT_LEFT = KeyEvent.VK_A;

    /** Default right key. */
    private static final int DEFAULT_RIGHT = KeyEvent.VK_D;

    /** Default down key. */
    private static final int DEFAULT_DOWN = KeyEvent.VK_S;

    /** Default drop key. */
    private static final int DEFAULT_DROP = KeyEvent.VK_SPACE;

    /** Default rotate clockwise key. */
    private static final int DEFAULT_ROTATE_CW = KeyEvent.VK_E;

    /** Default rotate counter-clockwise key. */
    private static final int DEFAULT_ROTATE_CCW = KeyEvent.VK_Q;

    /** Default pause key. */
    private static final int DEFAULT_PAUSE = KeyEvent.VK_P;

    /** Holds reference to the game board. */
    private final Board myBoard;

    /** Holds reference to the GUI frame. */
    private final Tetris myFrame;

    /** For setting current left key. */
    private int myLeft;

    /** For setting current right key. */
    private int myRight;

    /** For setting current down key. */
    private int myDown;

    /** For setting current drop key. */
    private int myDrop;

    /** For setting current rotate clockwise key. */
    private int myRotateCW;

    /** For setting current rotate counter-clockwise key. */
    private int myRotateCCW;

    /** For setting current pause key. */
    private int myPause;

    /** For enabling/disabling game key events if no game started. */
    private boolean myGameStarted;

    /** For enabling/disabling sound clips. */
    private boolean mySoundState;

    /**
     * Default constructor sets up default keys.
     * 
     * @param theFrame reference to GUI frame
     * @param theBoard reference to game board
     */
    public GameKeys(final Tetris theFrame, final Board theBoard)
    {
        super();
        myBoard = theBoard;
        myFrame = theFrame;
        myGameStarted = false;
        mySoundState = true;

        setDefaultKeys();
    }

    /** Helper method to initialize default game keys. */
    private void setDefaultKeys()
    {
        myLeft = DEFAULT_LEFT;
        myRight = DEFAULT_RIGHT;
        myDown = DEFAULT_DOWN;
        myDrop = DEFAULT_DROP;
        myRotateCW = DEFAULT_ROTATE_CW;
        myRotateCCW = DEFAULT_ROTATE_CCW;
        myPause = DEFAULT_PAUSE;
    }

    @Override
    public void keyPressed(final KeyEvent theEvent)
    {
        if (myGameStarted) // game started = true
        {   
            final int key = theEvent.getKeyCode();

            if (key == myLeft)
            {
                if (mySoundState)
                {
                    myFrame.getSoundClips().playMove();
                }
                
                myBoard.left();
            }
            else if (key == myRight)
            {
                if (mySoundState)
                {
                    myFrame.getSoundClips().playMove();
                }
                
                myBoard.right();
            }
            else if (key == myDown)
            {
                if (mySoundState)
                {
                    myFrame.getSoundClips().playMove();
                }
                
                myBoard.down();
            }
            else if (key == myDrop)
            {
                if (mySoundState)
                {
                    myFrame.getSoundClips().playDrop();
                }
                
                myBoard.drop();
            }
            else if (key == myRotateCW)
            {
                if (mySoundState)
                {
                    myFrame.getSoundClips().playRotate();
                }
                
                myBoard.rotateCW();
            }
            else if (key == myRotateCCW)
            {   
                if (mySoundState)
                {
                    myFrame.getSoundClips().playRotate();
                }
                
                myBoard.rotateCCW();
            }
            else if (key == myPause)
            {
                myFrame.pauseGame();
            }
        }
    }

    /**
     * Sets the sound state.
     * 
     * @param theState enabled if true, disabled if false
     */
    public void setSoundState(final boolean theState)
    {
        mySoundState = theState;
    }

    /**
     * Gets the current sound state.
     * 
     * @return true if enabled, otherwise false
     */
    public boolean isSoundEnabled()
    {
        return mySoundState;
    }

    /** 
     * Enables or disables keys depending on game state.
     *  
     * @param theState true if game started, otherwise false
     */
    public void setGameKeyState(final boolean theState)
    {
        myGameStarted = theState;
    }

    /**
     * Gets the current game key state.
     * 
     * @return true if enabled, otherwise false
     */
    public boolean isGameKeyEnabled()
    {
        return myGameStarted;
    }

    /**
     * Gets left key.
     * 
     * @return int value of assigned key
     */
    public int getLeftKey()
    {
        return myLeft;
    }

    /**
     * Gets right key.
     * 
     * @return int value of assigned key
     */
    public int getRightKey()
    {
        return myRight;
    }

    /**
     * Gets down key.
     * 
     * @return int value of assigned key
     */
    public int getDownKey()
    {
        return myDown;
    }

    /**
     * Gets drop key.
     * 
     * @return int value of assigned key
     */
    public int getDropKey()
    {
        return myDrop;
    }

    /**
     * Gets rotate clockwise key.
     * 
     * @return int value of assigned key
     */
    public int getRotateCWKey()
    {
        return myRotateCW;
    }

    /**
     * Gets rotate counter-clockwise key.
     * 
     * @return int value of assigned key
     */
    public int getRotateCCWKey()
    {
        return myRotateCCW;
    }

    /**
     * Gets pause key.
     * 
     * @return int value of assigned key
     */
    public int getPauseKey()
    {
        return myPause;
    }

    /**
     * Sets left key.
     * 
     * @param theKey int value of key
     */
    public void setLeftKey(final int theKey)
    {
        myLeft = theKey;
    }

    /**
     * Sets right key.
     * 
     * @param theKey int value of key
     */
    public void setRightKey(final int theKey)
    {
        myRight = theKey;
    }

    /**
     * Sets down key.
     * 
     * @param theKey int value of key
     */
    public void setDownKey(final int theKey)
    {
        myDown = theKey;
    }

    /**
     * Sets drop key.
     * 
     * @param theKey int value of key
     */
    public void setDropKey(final int theKey)
    {
        myDown = theKey;
    }

    /**
     * Sets rotate clockwise key.
     * 
     * @param theKey int value of key
     */
    public void setRotateCWKey(final int theKey)
    {
        myRotateCW = theKey;
    }

    /**
     * Sets rotate counter-clockwise key.
     * 
     * @param theKey int value of key
     */
    public void setRotateCCWKey(final int theKey)
    {
        myRotateCCW = theKey;
    }

    /**
     * Sets pause key.
     * 
     * @param theKey int value of key
     */
    public void setPauseKey(final int theKey)
    {
        myPause = theKey;
    }

    @Override
    public void keyReleased(final KeyEvent theEvent)
    {
        // empty
    }

    @Override
    public void keyTyped(final KeyEvent theEvent)
    {
        // empty
    }
}
