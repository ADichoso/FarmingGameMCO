package GUI;

import javax.swing.*;
import java.awt.*;

/** A JFrame from which all other JFrames used in the application extend from. has the basic appearance of all other frames
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class ParentFrame extends JFrame{
    protected static final Color BG_COLOR = new Color(107, 255, 129);
    protected static final Color BG_COLOR_RED = new Color(255, 107, 107);

    /**
     * Initialize the frame with a specific design.
     */
    public ParentFrame()
    {
        getContentPane().setBackground(BG_COLOR);
    }
}
