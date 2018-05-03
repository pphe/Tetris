/*
 * Peterson Phe
 * Assignment 6 - Tetris
 * TCSS 305 - Winter 2016
 */

package view;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import listeners.GameKeys;
import listeners.TimerListener;
import model.Board;
import sound.MusicPlayer;
import sound.SoundClips;

/**
 * Front end for Tetris application.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public class Tetris extends JFrame
{
    /** A generated ID for object serialization. */
    private static final long serialVersionUID = -8300090515141116438L;

    /** Application icon path. */
    private static final String ICON_PATH = "images/icon_small.png";

    /** Icon path for game over. */
    private static final String ICON = "images/deadpiece.png";

    /** Initial delay before game timer starts. */
    private static final int STARTING_DELAY = 1000;

    /** Timer decrement value for increasing difficulty. */
    private static final int ADVANCE_DIFFICULTY = 100;

    /** MenuBar PropertyChangeListener new game constant. */
    private static final String START = "start";

    /** MenuBar PropertyChangeListener end game constant. */
    private static final String END = "end";

    /** Outer content panel. */
    private final JPanel myOuterPanel;

    /** Game board. */
    private final Board myBoard;

    /** Holds reference to game panel. */
    private final GamePanel myGamePanel;

    /** Holds reference to next piece panel. */
    private final NextPiecePanel myNextPiecePanel;

    /** Holds reference to info panel. */
    private final InfoPanel myInfoPanel;

    /** Holds game keyboard reference. */
    private final GameKeys myGameKeys;

    /** Game timer. */
    private final Timer myTimer;

    /** For tracking game statistics. */
    private final GameStatistics myStats;

    /** For music. */
    private MusicPlayer myMusicPlayer;
    
    /** For sound effects. */
    private SoundClips mySoundClips;

    /** Empty constructor. */
    public Tetris()
    {
        super("Tetris Time!");
        myOuterPanel = new JPanel(new BorderLayout());
        myBoard = new Board();
        myGameKeys = new GameKeys(this, myBoard);
        myGamePanel = new GamePanel(this, myBoard.getHeight(), myBoard.getWidth());
        myNextPiecePanel = new NextPiecePanel();
        myInfoPanel = new InfoPanel(myGameKeys);
        myTimer = new Timer(STARTING_DELAY, new TimerListener(myBoard));
        myStats = new GameStatistics(this);
        setupPlayers();
    }

    /** Starts the game. */
    public void setupGUI()
    {
        setupContainers();
        setupMenu();
        setupGamePanel();
        setupOtherPanels();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Sets up components for the game. */
    public void startGame()
    {
        // start game
        myBoard.newGame();
        
        // reset game statistics
        myStats.reset();

        // disable new game menu item, enable end game
        getJMenuBar().firePropertyChange(START, !myStats.isGameOver(), myStats.isGameOver());

        // enable keys
        myGameKeys.setGameKeyState(true);

        // start timer
        myTimer.setDelay(STARTING_DELAY);
        myTimer.start();
    }

    /** Stops components for the game. */
    public void endGame()
    {
        // set game status
        myStats.setGameOver(true);

        // enable new game menu item, disable end game
        getJMenuBar().firePropertyChange(END, !myStats.isGameOver(), myStats.isGameOver());

        // disable keys
        myGameKeys.setGameKeyState(false);

        // stop timer
        myTimer.stop();

        // show dialog with score
        displayGameOver();
    }

    /** Pauses game. */
    public void pauseGame()
    {
        // stop timer, disable keys, toggle music
        myTimer.stop();
        myGameKeys.setGameKeyState(false);
        myMusicPlayer.togglePause();

        // show paused dialog #2
        final String[] options = {"Resume"};
        JOptionPane.showOptionDialog(this, "Are your eyes dry or something?", "Game Paused",
                JOptionPane.INFORMATION_MESSAGE, 0, new ImageIcon(ICON_PATH), 
                options, options[0]);

        // resume game
        myTimer.start();
        myGameKeys.setGameKeyState(true);
        myMusicPlayer.togglePause();
    }

    /** Sets up the next piece panel. */
    private void setupOtherPanels()
    {   
        // add panels
        final JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(myNextPiecePanel, BorderLayout.NORTH);
        eastPanel.add(myInfoPanel, BorderLayout.CENTER);
        myOuterPanel.add(eastPanel, BorderLayout.EAST);

        // add observers
        myBoard.addObserver(myNextPiecePanel);
        myBoard.addObserver(myInfoPanel);

        // add other observers for keys and stats data
        myGameKeys.addObserver(myInfoPanel);
        myStats.addObserver(myInfoPanel);
        myBoard.addObserver(myInfoPanel); // test line
    }

    /** Sets up the game panel. */
    private void setupGamePanel()
    {
        myOuterPanel.add(myGamePanel, BorderLayout.CENTER);

        // for gamekey listener focus
        myGamePanel.addKeyListener(myGameKeys);
        super.addNotify();
        myGamePanel.requestFocus();

        // add observer
        myBoard.addObserver(myGamePanel);
    }

    /** Display indicating that the game is over. */
    private void displayGameOver()
    {        
        JOptionPane.showMessageDialog(this, "Your score: " + myStats.getScore() + "\n"
                + "Lines cleared: " + myStats.getLinesCleared(), "Game over!", 
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ICON));
    }

    /** Sets up the menu bar. */
    private void setupMenu()
    {
        setJMenuBar(new MenuBar(this, myGamePanel, myNextPiecePanel, myGameKeys));
    }

    /** Sets up the frame settings. */
    private void setupContainers()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(ICON_PATH).getImage());
        setLayout(new BorderLayout());
        setContentPane(myOuterPanel);
        setResizable(false);
    }

    /** Sets up music and sound players. */
    private void setupPlayers()
    {
        myMusicPlayer = new MusicPlayer();

        final File[] list =
        { 
            new File("mp3/Futurama - Theme (Extended2).mp3"),
            new File("mp3/tetris_theme.mp3")
        };

        myMusicPlayer.newList(list);
        
        mySoundClips = new SoundClips();
    }

    /**
     * Gets the handle for music player.
     * 
     * @return reference to music player
     */
    public MusicPlayer getMusicPlayer()
    {
        return myMusicPlayer;
    }

    /**
     * Gets the handle for the sound clip player.
     * 
     * @return reference to sound clip player
     */
    public SoundClips getSoundClips()
    {
        return mySoundClips;
    }

    /**
     * Gets the current game statistics.
     * 
     * @return reference to game statistics
     */
    public GameStatistics getStats()
    {
        return myStats;
    }

    /** Advances the game timer for increased difficulty. */
    public void advanceDifficulty()
    {
        // if delay currently at 100ms, reduce timer by half
        if (myTimer.getDelay() == ADVANCE_DIFFICULTY)
        {
            myTimer.setDelay(myTimer.getDelay() / 2);
        }
        else // reduce delay by 100ms
        {
            myTimer.setDelay(myTimer.getDelay() - ADVANCE_DIFFICULTY);
        }
    }
}
