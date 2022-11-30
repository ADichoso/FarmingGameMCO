package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/** A JFrame that contains the game over screen of the game
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class GameOverFrame extends ParentFrame {
    private JLabel txtLbl;

    /**
     * Initialize the game over screen
     * @param onResetAction is the reset action for the game
     * @param onQuitAction is the quit action for the game
     */
    public GameOverFrame(ActionListener onResetAction, ActionListener onQuitAction)
    {
        super();
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setSize(300, 125);
        setResizable(false);

        setLayout(new FlowLayout());

        JButton resetButton = new JButton("Restart Game");
        resetButton.addActionListener(onResetAction);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(onQuitAction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(resetButton, BorderLayout.WEST);
        buttonPanel.add(quitButton, BorderLayout.EAST);

        txtLbl = new JLabel();

        add(txtLbl);
        add(buttonPanel);
    }

    /**
     * Show the game over message
     * @param message is the message to be shown
     */
    public void showGameOverMessage(String message)
    {
        txtLbl.setText(message);
        setVisible(true);
    }

}
