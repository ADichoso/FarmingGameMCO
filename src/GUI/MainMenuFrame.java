package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/** A JFrame that contains the main menu screen of the game
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class MainMenuFrame extends ParentFrame {

    private JTextField nameTextField;
    private JLabel namePromptLbl;

    /**
     * Get the player name inputted in the text field
     * @return the player name inputted in the text field
     */
    public String getPlayerName(){ return nameTextField.getText(); }

    /**
     * Get the name prompt label, which tells the player to input their name
     * @return the name prompt label, which tells the player to input their name
     */
    public JLabel getNamePromptLbl() {
        return namePromptLbl;
    }

    /**
     * Initialize the main menu frame
     * @param onStartAction starts the game
     * @param onQuitAction quits out  of the application
     */
    public MainMenuFrame(ActionListener onStartAction, ActionListener onQuitAction)
    {
        super();
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)

        setName("GATHER SUN");
        setSize(640, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel imageLbl = new JLabel(new ImageIcon(GUISystem.class.getResource(PictureLocations.TITLE_IMAGE_NAME)));
        imageLbl.setSize(533, 400);

        JButton startButton = new JButton("Start!");
        startButton.addActionListener(onStartAction);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(onQuitAction);

        JPanel buttonsPnl = new JPanel();
        buttonsPnl.add(startButton, BorderLayout.WEST);
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

    /**
     * Show a prompt when the player enters an empty name
     */
    public void showEmptyNameMessage()
    {
        namePromptLbl.setText("Try Again! Enter your name:");
    }

    /**
     * Hide the alert message for when the player enters an empty name
     */
    public void hideEmptyNameMessage()
    {
        namePromptLbl.setText("Enter your name:");
    }

    /**
     * Check if the player has actually entered a name
     * @return true if the player entered a name, false if not
     */
    public boolean hasPlayerName()
    {
        return !(nameTextField.getText().equals(" ") || nameTextField.getText().equals(""));
    }
}
