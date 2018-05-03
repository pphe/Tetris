package view;

import com.sun.media.codec.audio.mp3.JavaDecoder;

import java.awt.EventQueue;

import javax.media.Codec;
import javax.media.PlugInManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Driver class for the Tetris application.
 * 
 * @author Peterson Phe
 * @version 1.0
 */
public final class TetrisMain
{

    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private TetrisMain()
    {
        throw new IllegalStateException();
    }

    /**
     * The main method for instantiating the PowerPaint GUI.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs)
    {
        try
        {
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch (final UnsupportedLookAndFeelException ex)
        {
            ex.printStackTrace();
        }
        catch (final IllegalAccessException ex)
        {
            ex.printStackTrace();
        }
        catch (final InstantiationException ex)
        {
            ex.printStackTrace();
        }
        catch (final ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        // for music (mp3) support
        final Codec c = new JavaDecoder();
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder",
                                c.getSupportedInputFormats(),
                                c.getSupportedOutputFormats(null),
                                PlugInManager.CODEC);
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
                new Tetris().setupGUI();
            }
        });
    }
}
