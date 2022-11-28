package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameOverFrame extends JFrame {
    private JLabel txtLbl;
    public GameOverFrame(ActionListener onResetAction, ActionListener onQuitAction)
    {
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

    public void showGameOverMessage(String message)
    {
        txtLbl.setText(message);
        setVisible(true);
    }

}
