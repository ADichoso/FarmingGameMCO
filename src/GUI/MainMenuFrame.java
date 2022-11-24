package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame {
    private final String TITLE_IMAGE_NAME = "gathersun.png";

    private JTextField nameTextField;
    private JLabel namePromptLbl;

    public String getPlayerName(){ return nameTextField.getText(); }

    public MainMenuFrame(ActionListener onStartAction, ActionListener onHelpAction, ActionListener onQuitAction)
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)

        setName("GATHER SUN");
        setSize(640, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel imageLbl = new JLabel(new ImageIcon(Renderer.class.getResource(TITLE_IMAGE_NAME)));
        imageLbl.setSize(533, 400);

        JButton startButton = new JButton("Start!");
        startButton.addActionListener(onStartAction);

        JButton helpButton = new JButton("Help?");
        helpButton.addActionListener(onHelpAction);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(onQuitAction);

        JPanel buttonsPnl = new JPanel();
        buttonsPnl.add(startButton, BorderLayout.WEST);
        buttonsPnl.add(helpButton, BorderLayout.CENTER);
        buttonsPnl.add(quitButton, BorderLayout.EAST);

        nameTextField = new JTextField();
        nameTextField.setColumns(16);


        namePromptLbl = new JLabel("Enter your name:");
        JPanel nameInputPnl = new JPanel();

        nameInputPnl.add(namePromptLbl, BorderLayout.WEST);
        nameInputPnl.add(nameTextField, BorderLayout.EAST);

        add(imageLbl);
        add(nameInputPnl);
        add(buttonsPnl);
    }

    public void showEmptyNameMessage()
    {
        namePromptLbl.setText("Try Again! Enter your name:");
    }

    public void hideEmptyNameMessage()
    {
        namePromptLbl.setText("Enter your name:");
    }
    public boolean hasPlayerName()
    {
        return !(nameTextField.getText().equals(" ") || nameTextField.getText().equals(""));
    }
}
