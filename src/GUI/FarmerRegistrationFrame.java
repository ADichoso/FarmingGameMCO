package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/** A JFrame allowing the player to register to the next farmer type
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class FarmerRegistrationFrame extends ParentFrame {
    private FarmerTypePanel currentFarmerTypePanel;
    private FarmerTypePanel nextFarmerTypePanel;


    /**
     * Initialize the farmer registration frame
     * @param onRegisterAction is the action performed for the registering button
     * @param onQuitAction is the action performed when the quit button is pressed.
     */
    public FarmerRegistrationFrame(ActionListener onRegisterAction, ActionListener onQuitAction)
    {
        super();
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setSize(480, 480);
        setResizable(false);
        setLayout(new FlowLayout());

        //Message Label
        JLabel messageLbl = new JLabel("Register now and get these benefits!");
        JButton registerButton = new JButton("Register Now!");
        registerButton.addActionListener(onRegisterAction);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(onQuitAction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton, BorderLayout.WEST);
        buttonPanel.add(quitButton, BorderLayout.EAST);

        currentFarmerTypePanel = new FarmerTypePanel("Current Registry");
        nextFarmerTypePanel = new FarmerTypePanel("Next Registry");

        JPanel farmerTypesPanel = new JPanel();
        farmerTypesPanel.add(currentFarmerTypePanel, BorderLayout.WEST);
        farmerTypesPanel.add(nextFarmerTypePanel, BorderLayout.EAST);

        add(farmerTypesPanel);
        add(buttonPanel);
    }

    /**
     * Display the farmer types' information in the tables
     * @param currTypeStats is the player's current farmer type information
     * @param nextTypeStats is the player's next farmer type information
     */
    public void displayFarmerTypeStats(String[] currTypeStats, String[] nextTypeStats)
    {
        currentFarmerTypePanel.updateFarmerStatsTable(currTypeStats);
        nextFarmerTypePanel.updateFarmerStatsTable(nextTypeStats);
    }
}
