/*
 * Peterson Phe
 * Assignment 6 - Tetris
 * TCSS 305 - Winter 2016
 */

package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;

import model.Block;

/**
 * Holds a static method for setting a TetrisPiece block color based on 
 * the enumeration defined in Block.java.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public final class BlockColor 
{
    /** Private default constructor. */
    private BlockColor()
    {
        // prevent instantiation
    }

    /**
     * Sets the Graphics object context color based on the Block type.
     * 
     * @param theGraphics reference to graphics object
     * @param thePiece reference to the piece type
     */
    public static void setColor(final Graphics2D theGraphics, final Block thePiece)
    {
        if (Objects.nonNull(theGraphics) && Objects.nonNull(thePiece))
        {
            switch (thePiece)
            {
                case I:
                    theGraphics.setPaint(Color.BLUE);
                    break;
                case J:
                    theGraphics.setPaint(Color.RED);
                    break;
                case L:
                    theGraphics.setPaint(Color.CYAN);
                    break;
                case O:
                    theGraphics.setPaint(Color.YELLOW);
                    break;
                case S:
                    theGraphics.setPaint(Color.ORANGE);
                    break;
                case T:
                    theGraphics.setPaint(Color.GREEN);
                    break;
                case Z:
                    theGraphics.setPaint(Color.MAGENTA);
                    break;
                default:
                    theGraphics.setPaint(Color.LIGHT_GRAY);
                    break;
            }
        }
    }
}
