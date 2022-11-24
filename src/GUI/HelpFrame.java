package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpFrame extends JFrame {


    public HelpFrame(ActionListener onQuitAction)
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)

        setName("HELP");
        setSize(640, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel helpLbl = new JLabel("Very long line of text in here yezzzerirenfefdffsfertdfgfgfb");

        JButton quitButton = new JButton("back");
        quitButton.addActionListener(onQuitAction);

        add(helpLbl, BorderLayout.NORTH);
        add(quitButton, BorderLayout.NORTH);
    }
}
