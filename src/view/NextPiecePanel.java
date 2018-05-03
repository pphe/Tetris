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
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Point;
import model.TetrisPiece;

/**
 * Panel for displaying the next Tetris piece.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public class NextPiecePanel extends JPanel implements Observer
{
    /** A generated ID for object serialization. */
    private static final long serialVersionUID = -3254087614079880441L;

    /** Panel size width. */
    private static final int PANEL_SIZE_X = 140;

    /** Panel size height. */
    private static final int PANEL_SIZE_Y = 160;

    /** Length and width for each box of panel's grid. */
    private static final int SIZE = 30;

    /** Border size. */
    private static final int BORDER_OFFSET = 14;

    /** Offset for X axis to center piece. */
    private static final int X_OFFSET = 5;

    /** Next tetris piece. */
    private TetrisPiece myNextPiece;

    /** Enables sketch mode or not. */
    private boolean mySketchState;

    /** Constructor for panel. */
    public NextPiecePanel()
    {
        super();
        setPreferredSize(new Dimension(PANEL_SIZE_X, PANEL_SIZE_Y));
        setBackground(Color.BLACK);
        setBorder(new TitledBorder(null, "Next Piece", TitledBorder.CENTER, 
                TitledBorder.CENTER, null, Color.WHITE));
        setToolTipText("next tetris piece");
        mySketchState = false;
    }

    @Override
    protected void paintComponent(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (myNextPiece != null)
        {   
            final Point[] points = myNextPiece.getPoints();

            // traverse Block point array
            for (int i = 0; i < points.length; i++)
            {
                // set piece color
                BlockColor.setColor(g2d, myNextPiece.getBlock());


                if (!mySketchState)
                {
                    // fill the shape
                    g2d.fillRect(BORDER_OFFSET + (points[i].x() * SIZE) + X_OFFSET,
                            -((BORDER_OFFSET * 2) + (points[i].y() * SIZE)) + PANEL_SIZE_X,
                            SIZE, SIZE);

                    g2d.setPaint(Color.BLACK);

                }
                
                // draws border
                g2d.drawRect(BORDER_OFFSET + (points[i].x() * SIZE) + X_OFFSET, 
                        -((BORDER_OFFSET * 2) + (points[i].y() * SIZE)) + PANEL_SIZE_X, 
                        SIZE, SIZE);
            }
        }   
    }

    @Override
    public void update(final Observable theObs, final Object theArg)
    {
        // update next piece component if new piece in progress
        if ((theArg != null) && (theArg instanceof TetrisPiece))
        {
            myNextPiece = (TetrisPiece) theArg;
            repaint();
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
}
