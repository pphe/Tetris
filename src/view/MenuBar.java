/*
 * Peterson Phe
 * Assignment 6 - Tetris
 * TCSS 305 - Winter 2016
 */

package view;

import com.sun.glass.events.KeyEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import listeners.GameKeys;

/**
 * Menu Bar for frame in Tetris class.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public class MenuBar extends JMenuBar implements PropertyChangeListener
{
    /** A generated ID for object serialization. */
    private static final long serialVersionUID = 7111550242898007658L;

    /** About string for JOptionPane dialog. */
    private static final String ABOUT = "Tetris\nTCSS 305 - Winter 2016\nPeterson Phe";

    /** Application icon path. */
    private static final String ICON_PATH = "images/icon.png";

    /** MenuBar PropertyChangeListener new game constant. */
    private static final String START = "start";

    /** MenuBar PropertyChangeListener end game constant. */
    private static final String END = "end";

    /** Holds reference to the frame. */
    private final Tetris myFrame;

    /** Holds reference to the game panel. */
    private final GamePanel myGamePanel;

    /** Holds reference to the game keys. */
    private final GameKeys myGameKeys;

    /** Holds reference to the next piece panel. */
    private final NextPiecePanel myNextPiecePanel;

    /** New game menu item. */
    private JMenuItem myNewGameItem;

    /** End game menu item. */
    private JMenuItem myEndGameItem;

    /** 
     * Constructor for creating the menu bar.
     * 
     * @param theFrame Tetris JFrame reference
     * @param theGamePanel GamePanel reference
     * @param theNextPiecePanel NextPiecePanel reference
     * @param theKeys GameKeys reference
     */
    public MenuBar(final Tetris theFrame, final GamePanel theGamePanel, 
            final NextPiecePanel theNextPiecePanel, final GameKeys theKeys)
    {
        super();
        myFrame = theFrame;
        myGamePanel = theGamePanel;
        myNextPiecePanel = theNextPiecePanel;
        myGameKeys = theKeys;
        
        // sets up menu
        setupMenus();
    }

    /** Helper method for setting up all menu options. */
    private void setupMenus()
    {
        addFileMenu();
        addOptionMenu();
        addHelpMenu();

        addPropertyChangeListener(START, this);
        addPropertyChangeListener(END, this);
    }

    /** Adds the File menu. */
    private void addFileMenu()
    {
        // file (mnemonic F)
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);

        // new game (mnemonic N)
        myNewGameItem = new JMenuItem("New Game");
        myNewGameItem.setMnemonic(KeyEvent.VK_N);
        fileMenu.add(myNewGameItem);

        // end game
        myEndGameItem = new JMenuItem("End Game");
        myEndGameItem.setMnemonic(KeyEvent.VK_E);
        myEndGameItem.setEnabled(false);
        fileMenu.add(myEndGameItem);

        fileMenu.addSeparator();

        // file -> exit (mnemonic X)
        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        fileMenu.add(exitItem);

        // add action listeners
        myNewGameItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                myFrame.startGame();
            }
        });

        myEndGameItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                myFrame.endGame();
            }
        });

        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                if (myFrame.getMusicPlayer().isStarted())
                {
                    myFrame.getMusicPlayer().stopPlay();
                }

                myFrame.endGame();
                myFrame.dispose();
            }
        });
    }

    /** Adds the Option menu. */
    private void addOptionMenu()
    {
        // options (mnemonic O)
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        add(optionsMenu);

        // show grid lines
        final JCheckBoxMenuItem gridMenuItem = new JCheckBoxMenuItem("Show grid lines");
        optionsMenu.add(gridMenuItem);
        gridMenuItem.setMnemonic(KeyEvent.VK_G);
        gridMenuItem.setSelected(false);
        myGamePanel.setGridState(gridMenuItem.isSelected());

        optionsMenu.addSeparator();

        final JCheckBoxMenuItem sketchMenuItem = new JCheckBoxMenuItem("Sketch mode");
        optionsMenu.add(sketchMenuItem);
        sketchMenuItem.setSelected(false);
        sketchMenuItem.setMnemonic(KeyEvent.VK_K);

        optionsMenu.addSeparator();

        // enable sound
        final JCheckBoxMenuItem soundMenuItem = new JCheckBoxMenuItem("Enable sound");
        optionsMenu.add(soundMenuItem);
        soundMenuItem.setMnemonic(KeyEvent.VK_U);
        soundMenuItem.setSelected(true);

        // enable music
        final JCheckBoxMenuItem musicMenuItem = new JCheckBoxMenuItem("Enable music");
        optionsMenu.add(musicMenuItem);
        musicMenuItem.setSelected(true); // on by default
        musicMenuItem.setMnemonic(KeyEvent.VK_M);

        // add listeners
        gridMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                myGamePanel.setGridState(gridMenuItem.isSelected());
            }
        });

        sketchMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                myGamePanel.setSketchState(sketchMenuItem.isSelected());
                myNextPiecePanel.setSketchState(sketchMenuItem.isSelected());
                myGamePanel.repaint();
                myNextPiecePanel.repaint();
            }
        });

        soundMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                myGameKeys.setSoundState(soundMenuItem.isSelected());
                myGamePanel.setSoundState(soundMenuItem.isSelected());
            }
        });

        musicMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {   
                // enable/disable music
                if (musicMenuItem.isSelected())
                {
                    myFrame.getMusicPlayer().togglePause();   
                }
                else // menu item unchecked
                {
                    myFrame.getMusicPlayer().togglePause();
                }
            }
        });
    }

    /** Adds the Help menu. */
    private void addHelpMenu()
    {
        final String about = "About"; // satisfy check style

        // help (mnemonic H)
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        add(helpMenu);

        // game score details (mnemonic S)
        final JMenuItem scoreMenuItem = new JMenuItem("Score Info");
        scoreMenuItem.setMnemonic(KeyEvent.VK_S);
        helpMenu.add(scoreMenuItem);
        scoreMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                myGameKeys.setGameKeyState(false);

                JOptionPane.showMessageDialog(myFrame,
                        "You are awarded 4 points for each piece placed on the board.\n"
                                + "When lines are cleared the points are awarded as follows:\n"
                                + "1 line cleared: 40 * current level\n"
                                + "2 lines cleared: 100 * current level\n"
                                + "3 lines cleared: 300 * current level\n"
                                + "4 lines cleared: 1200 * current level\n",
                                "Score Information", JOptionPane.INFORMATION_MESSAGE, 
                                new ImageIcon(ICON_PATH));

                myGameKeys.setGameKeyState(true);
            }
        });

        // about (mnemonic A)
        final JMenuItem aboutMenuItem = new JMenuItem(about);
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        helpMenu.add(aboutMenuItem);
        aboutMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                myGameKeys.setGameKeyState(false);

                JOptionPane.showMessageDialog(myFrame, ABOUT, about, 
                        JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ICON_PATH));

                myGameKeys.setGameKeyState(true);
            }
        });
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent)
    {
        final String property = theEvent.getPropertyName();

        if (START.equals(property))
        {
            myNewGameItem.setEnabled(false);
            myEndGameItem.setEnabled(true);
        }
        else if (END.equals(property))
        {
            myNewGameItem.setEnabled(true);
            myEndGameItem.setEnabled(false);
        }
    }
}
