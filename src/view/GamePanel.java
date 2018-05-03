/*
 * Peterson Phe
 * Assignment 6 - Tetris
 * TCSS 305 - Winter 2016
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Block;
import model.TetrisPiece;

/**
 * Main panel for handling game pieces.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public class GamePanel extends JPanel implements Observer
{
    /** A generated ID for object serialization. */
    private static final long serialVersionUID = -7464368437893874582L;

    /** Game Panel width. */
    private static final int WIDTH = 275;

    /** Game Panel height. */
    private static final int HEIGHT = 550;

    /** Number of lines for a Tetris. */
    private static final int TETRIS = 4;

    /** Holds reference to Tetris frame. */
    private final Tetris myFrame;

    /** The gap between vertical grid lines. */
    private final int myVertDiv;

    /** The gap between horizontal grid lines. */
    private final int myHorizDiv;

    /** Show grid lines or not. */
    private boolean myGridLines;

    /** Enables sound effects or not. */
    private boolean mySoundState;

    /** Enables sketch mode or not. */
    private boolean mySketchState;

    /** List of Tetris Block pieces. */
    private List<Block[]> myBlockList;

    /**
     * Constructor for game panel.
     * 
     * @param theFrame reference to frame
     * @param theHeight number of rows for the board
     * @param theWidth number of columns for the board
     */
    public GamePanel(final Tetris theFrame, final int theHeight, final int theWidth)
    {
        super();

        myFrame = theFrame;
        myBlockList = new ArrayList<>();
        myHorizDiv = HEIGHT / theHeight;
        myVertDiv = WIDTH / theWidth;
        mySoundState = true;
        mySketchState = false;

        // set up panel
        setupPanel();
    }

    /** Helper method to setup the panel. */
    private void setupPanel()
    {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

        try
        {
            g2d.drawImage(ImageIO.read(new File("images/bg.png")), 0, 0, null);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }

        // show grid lines?
        if (myGridLines)
        {
            showGridLines(g2d);
        }

        // traverse List
        for (int i = 0; i < myBlockList.size(); i++)
        {   
            // get current Block array in list
            final Block[] b = myBlockList.get(i);

            // traverse current Block array's Block enum
            for (int j = 0; j < b.length; j++)
            {
                if (b[j] != null)
                {   
                    // set piece color
                    BlockColor.setColor(g2d, b[j]);

                    if (!mySketchState)
                    {
                        // fill the shape
                        g2d.fillRect(j * myVertDiv, (HEIGHT - myHorizDiv) - (i * myHorizDiv),
                                myVertDiv, myHorizDiv);

                        g2d.setPaint(Color.BLACK);
                    }

                    // draws border
                    g2d.drawRect(j * myVertDiv, (HEIGHT - myHorizDiv) - (i * myHorizDiv),
                            myVertDiv, myHorizDiv);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(final Observable theObs, final Object theArg)
    {
        // get lines cleared via array length
        if ((theArg != null) && (theArg instanceof Integer[]))
        {
            final int rowsCleared = ((Integer[]) theArg).length;

            if (mySoundState)
            {
                if (rowsCleared == TETRIS)
                {
                    myFrame.getSoundClips().playTetrisClear();
                }
                else
                {
                    myFrame.getSoundClips().playRowClear();
                }
            }

            myFrame.getStats().calculateScore(rowsCleared);
        }

        // theArg is a List<Block[]> for pieces
        if ((theArg != null) && (theArg instanceof List<?>))
        {   
            myBlockList = (ArrayList<Block[]>) theArg;
            repaint();
        }

        // game over status from board
        if (theArg instanceof Boolean && (Boolean) theArg)
        {
            if (mySoundState)
            {
                myFrame.getSoundClips().playGameOver();
            }

            myFrame.endGame();
        }

        // increment score for each new piece
        if ((theArg != null) && (theArg instanceof TetrisPiece))
        {
            myFrame.getStats().incrementScore();
        }
    }

    /**
     * Sets the state for sketch mode.
     * 
     * @param theState enabled if true, false if disabled
     */
    public void setSketchState(final boolean theState)
    {
        mySketchState = theState;
    }

    /**
     * Sets the state for playing sound effects.
     * 
     * @param theState enabled if true, false if disabled
     */
    public void setSoundState(final boolean theState)
    {
        mySoundState = theState;
    }

    /**
     * Sets the state for showing grid lines.
     *
     * @param theState true if shown, otherwise false
     */
    public void setGridState(final boolean theState)
    {
        myGridLines = theState;
        repaint();
    }

    /** 
     * Shows board grid lines.
     *
     * @param theGraphics reference to graphics object
     */
    private void showGridLines(final Graphics theGraphics)
    {   
        // draw horizontal grid lines
        for (int i = 0; i < HEIGHT; i++)
        {
            theGraphics.drawLine(0, i * myHorizDiv, WIDTH, i * myHorizDiv);
        }

        // draw vertical grid lines
        for (int i = 0; i < WIDTH; i++)
        {
            theGraphics.drawLine(i * myVertDiv, 0, i * myVertDiv, HEIGHT);
        }
    }
}
