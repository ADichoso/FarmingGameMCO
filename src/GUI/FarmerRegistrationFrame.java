package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FarmerRegistrationFrame extends JFrame {
    private FarmerTypePanel currentFarmerTypePanel;
    private FarmerTypePanel nextFarmerTypePanel;
    public FarmerRegistrationFrame(ActionListener onRegisterAction, ActionListener onQuitAction)
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setSize(480, 480);
        setResizable(false);
        setLayout(new FlowLayout());

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

    public void displayFarmerTypeStats(String[] currTypeStats, String[] nextTypeStats)
    {
        currentFarmerTypePanel.updateFarmerStatsTable(currTypeStats);
        nextFarmerTypePanel.updateFarmerStatsTable(nextTypeStats);
    }
}
