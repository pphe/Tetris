/*
 * Peterson Phe
 * Assignment 6 - Tetris
 * TCSS 305 - Winter 2016
 */

package sound;

/**
 * Wrapper class for mapping sound bites to different game events.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public class SoundClips
{
    /** Sound effect for piece movement. */
    private static final String MOVE_SOUND = "clips/move.wav";
    
    /** Sound effect for drop movement. */
    private static final String DROP_SOUND = "clips/drop.wav";
    
    /** Sound effect for rotational movement. */
    private static final String ROTATE_SOUND = "clips/rotate.wav";
    
    /** Sound effect for game over. */
    private static final String GAME_OVER = "clips/youstinkloser.wav";
    
    /** Sound effect for clearing rows. */
    private static final String CLEAR_SOUND = "clips/manwich.wav";
    
    /** Sound effect for getting a Tetris. */
    private static final String TETRIS_SOUND = "clips/frywhoo.wav";
    
    /** Sound effect for next level. */
    private static final String NEXT_LEVEL_SOUND = "clips/leelawhoo.wav";
    
    /** Sound Player for processing sound clips.. */
    private final SoundPlayer mySoundPlayer;
    
    /** Default constructor. */
    public SoundClips()
    {
        mySoundPlayer = new SoundPlayer();
        setupSoundBites();
    }
    
    /** Plays the sound bite for a movement key. */
    public void playMove()
    {
        mySoundPlayer.play(MOVE_SOUND);
    }
    
    /** Plays the sound bite for the drop key. */
    public void playDrop()
    {
        mySoundPlayer.play(DROP_SOUND);
    }
    
    /** Plays the sound bite for the rotational key. */
    public void playRotate()
    {
        mySoundPlayer.play(ROTATE_SOUND);
    }
    
    /** Plays the sound bite for game over. */
    public void playGameOver()
    {
        mySoundPlayer.play(GAME_OVER);
    }
    
    /** Plays the sound bite for clearing rows. */
    public void playRowClear()
    {
        mySoundPlayer.play(CLEAR_SOUND);
    }
    
    /** Plays the sound bite for a Tetris. */
    public void playTetrisClear()
    {
        mySoundPlayer.play(TETRIS_SOUND);
    }
    
    /** Plays the sound bite for advancing a level. */
    public void playNextLevel()
    {
        mySoundPlayer.play(NEXT_LEVEL_SOUND);
    }
    
    /** Helper method to preload sound bites to the sound player. */
    private void setupSoundBites()
    {
        mySoundPlayer.preLoad(MOVE_SOUND);
        mySoundPlayer.preLoad(DROP_SOUND);
        mySoundPlayer.preLoad(ROTATE_SOUND);
        mySoundPlayer.preLoad(GAME_OVER);
        mySoundPlayer.preLoad(CLEAR_SOUND);
        mySoundPlayer.preLoad(TETRIS_SOUND);
        mySoundPlayer.preLoad(NEXT_LEVEL_SOUND);
    }
}
