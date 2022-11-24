package GUI;

import GameLogic.GameSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsFrame extends JFrame {
    private final String TITLE_IMAGE_NAME = "gathersun.png";

    private DefaultTableModel playerTableModel;

    public StatsFrame(ActionListener onQuitAction)
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setName("GATHER SUN");
        setSize(300, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        playerTableModel = new DefaultTableModel();
        playerTableModel.addColumn("PlayerInfo");
        JTable playerStatsTbl = new JTable(playerTableModel);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(onQuitAction);

        add(playerStatsTbl, BorderLayout.CENTER);
        add(exitButton, BorderLayout.SOUTH);
    }


    public void updatePlayerTable(String[] playerStats)
    {
        int rows = playerTableModel.getRowCount();
        for(int i = 0; i < rows; i++)
            playerTableModel.removeRow(0);

        for (String stats: playerStats)
        {
            playerTableModel.insertRow(0, new Object[] {stats});
        }
    }
}
