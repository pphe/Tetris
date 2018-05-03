/*
 * Peterson Phe
 * Assignment 6 - Tetris
 * TCSS 305 - Winter 2016
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import listeners.GameKeys;

/**
 * Panel for displaying game information.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public class InfoPanel extends JPanel implements Observer
{
    /** A generated ID for object serialization. */
    private static final long serialVersionUID = 4691417946172572337L;

    /** Information panel width. */
    private static final int WIDTH = 150;

    /** Information panel height. */
    private static final int HEIGHT = 300;

    /** Holds reference to the game keys. */
    private final GameKeys myGameKeys;

    /** Label for current game score. */
    private JLabel myScoreLabel;

    /** Label for current level. */
    private JLabel myLevelLabel;
    
    /** Label for next level lines. */
    private JLabel myNextLevelLinesLabel;
    
    /** Label for lines cleared. */
    private JLabel myLineCountLabel;
    
    /** 
     * Constructor for panel.
     * 
     *  @param theGameKeys reference to game keys
     */
    public InfoPanel(final GameKeys theGameKeys)
    {
        super();
        myGameKeys = theGameKeys;

        // setup panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setBorder(new TitledBorder(null, "Game Information", TitledBorder.CENTER, 
                TitledBorder.CENTER, null, Color.WHITE));
        setToolTipText("shows game data");

        // add labels
        setupLabels();
    }

    /** Sets up the labels for game information. */
    private void setupLabels()
    {   
        myScoreLabel = new JLabel("Current Score: 0");
        myScoreLabel.setForeground(Color.WHITE);
        add(myScoreLabel);

        myLevelLabel = new JLabel("Current Level: 0");
        myLevelLabel.setForeground(Color.WHITE);
        add(myLevelLabel);

        myNextLevelLinesLabel = new JLabel("Next Level In: 0");
        myNextLevelLinesLabel.setForeground(Color.WHITE);
        add(myNextLevelLinesLabel);

        myLineCountLabel = new JLabel("Lines Cleared: 0");
        myLineCountLabel.setForeground(Color.WHITE);
        add(myLineCountLabel);

        createLabel("Pause Key: ", KeyEvent.getKeyText(myGameKeys.getPauseKey()));
        createLabel("Move Left: ", KeyEvent.getKeyText(myGameKeys.getLeftKey()));
        createLabel("Move Right: ", KeyEvent.getKeyText(myGameKeys.getRightKey()));
        createLabel("Move Down: ", KeyEvent.getKeyText(myGameKeys.getDownKey()));
        createLabel("Drop: ", KeyEvent.getKeyText(myGameKeys.getDropKey()));
        createLabel("Rotate CW: ", KeyEvent.getKeyText(myGameKeys.getRotateCWKey()));
        createLabel("Rotate CCW: ", KeyEvent.getKeyText(myGameKeys.getRotateCCWKey()));
    }

    /** 
     * Helper method to create and add a JLabel.
     * 
     * @param theText description of key
     * @param theKeyChar the character
     * @return new JLabel object
     */
    private JLabel createLabel(final String theText, final String theKeyChar)
    {
        final JLabel temp = new JLabel(theText + " " + theKeyChar);
        temp.setForeground(Color.WHITE);
        add(temp);
        return temp;
    }

    @Override
    public void update(final Observable theObs, final Object theArg)
    {   
        // update InfoPanel data
        if ((theArg != null) && (theArg instanceof GameStatistics))
        {
            final GameStatistics stats = (GameStatistics) theArg;
            
            myScoreLabel.setText("Current Score: " + stats.getScore());
            myLevelLabel.setText("Current Level: " + stats.getLevel());
            myNextLevelLinesLabel.setText("Next Level In: " + stats.getNextLevelLines());
            myLineCountLabel.setText("Lines Cleared: " + stats.getLinesCleared());

            repaint();
        }
    }
}
