/*
 * Peterson Phe
 * Assignment 6 - Tetris
 * TCSS 305 - Winter 2016
 */

package view;

import java.util.Observable;

/**
 * Tracks the game statistics such as level, score, lines cleared, etc.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public class GameStatistics extends Observable
{
    /** One line cleared. */
    private static final int ONE_LINE = 1;

    /** Two lines cleared. */
    private static final int TWO_LINES = 2;

    /** Three lines cleared. */
    private static final int THREE_LINES = 3;

    /** Four lines cleared (a Tetris). */
    private static final int FOUR_LINES = 4;

    /** Points awarded for each piece placed on the board. */
    private static final int PIECE_PLACED = 4;

    /** Points awarded for clearing one line. */
    private static final int ONE_LINE_MULTIPLIER = 40;

    /** Points awarded for clearing two lines. */
    private static final int TWO_LINES_MULTIPLIER = 100;

    /** Points awarded for clearing three lines. */
    private static final int THREE_LINES_MULTIPLIER = 300;

    /** Points awarded for clearing four lines (a Tetris). */
    private static final int FOUR_LINES_MULTIPLIER = 1200;

    /** Number of lines required before advancing the difficulty. */
    private static final int LINES_PER_LEVEL = 5;

    /** Reference for the frame. */
    private final Tetris myFrame;

    /** Tracks if game is over. */
    private boolean myGameOver;

    /** Tracks current level. */
    private int myLevel;

    /** Tracks the score. */
    private int myScore;

    /** Tracks number of lines cleared. */
    private int myLinesCleared;

    /** Tracks lines until next level. */
    private int myNextLevelLines;

    /** 
     * Default constructor that initializes game values.
     *
     * @param theFrame reference to game frame
     */
    public GameStatistics(final Tetris theFrame)
    {
        super();
        myFrame = theFrame;
        myGameOver = true; // no game in progress on startup
    }

    /**
     * Gets the current game score.
     * 
     * @return score
     */
    public int getScore()
    {
        return myScore;
    }

    /**
     * Gets the current level.
     * 
     * @return current level
     */
    public int getLevel()
    {
        return myLevel;
    }

    /**
     * Gets the total number of lines cleared.
     * 
     * @return total number of lines
     */
    public int getLinesCleared()
    {   
        return myLinesCleared;
    }

    /**
     * Gets the number of lines for next level.
     * 
     * @return number of lines
     */
    public int getNextLevelLines()
    {
        return myNextLevelLines;
    }

    /**
     * Calculates the game statistics.
     * 
     * @param theLineCount number of lines cleared
     */
    public void calculateScore(final int theLineCount)
    {
        // increment score based on CURRENT level
        switch (theLineCount)
        {
            case ONE_LINE:
                myScore += myLevel * ONE_LINE_MULTIPLIER;
                break;
            case TWO_LINES:
                myScore += myLevel * TWO_LINES_MULTIPLIER;
                break;
            case THREE_LINES:
                myScore += myLevel * THREE_LINES_MULTIPLIER;
                break;
            case FOUR_LINES:
                myScore += myLevel * FOUR_LINES_MULTIPLIER;
                break;
            default: // should never make it here
                break;
        }

        // increment line count, calculate # next level lines
        myLinesCleared += theLineCount;
        myNextLevelLines = LINES_PER_LEVEL - (myLinesCleared % LINES_PER_LEVEL);

        // check level and advance timer if necessary
        final int checkLevel = myLinesCleared / LINES_PER_LEVEL + 1;
        if (checkLevel > myLevel)
        {
//            myFrame.getSoundClips().playNextLevel(); // need sound state var
            myLevel = checkLevel;
            myFrame.advanceDifficulty();
        }

        setChanged();
        notifyObservers(this);
    }
    
    /** Increments the score by 4 points each time a piece is placed on the board. */
    public void incrementScore()
    {   
        myScore += PIECE_PLACED;

        setChanged();
        notifyObservers(this);
    }

    /** 
     * Gets the game state.
     * 
     * @return true if game is over, otherwise false
     */
    public boolean isGameOver()
    {
        return myGameOver;
    }

    /**
     * Sets the state for game over.
     * 
     * @param theGameState true if game is over, false if game in progress
     */
    public void setGameOver(final boolean theGameState)
    {
        myGameOver = theGameState;
    }

    /** Resets the game level, score and lines cleared. */
    public void reset()
    {
        myGameOver = false;
        myLevel = 1;
        myScore = 0;
        myLinesCleared = 0;
        myNextLevelLines = LINES_PER_LEVEL;

        setChanged();
        notifyObservers(this);
    }
}
