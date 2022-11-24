package GUI;

import GameLogic.GameSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    private final String TITLE_IMAGE_NAME = "gathersun.png";
    private JLabel daysLbl;

    public GameFrame(
            ActionListener onOpenStoreAction,
            ActionListener onShowStatsAction,
            ActionListener onAdvanceDayAction,
            ActionListener onRegisterAction,
            ActionListener onQuitAction)
    {
        setName("GATHER SUN");
        setSize(640, 550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        daysLbl = new JLabel();

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(onQuitAction);

        JButton storeButton = new JButton("Open Store");
        storeButton.addActionListener(onOpenStoreAction);

        JButton statsButton = new JButton("Show Player Stats");
        statsButton.addActionListener(onShowStatsAction);


        JPanel topButtonPanel = new JPanel();
        topButtonPanel.add(statsButton, BorderLayout.WEST);
        topButtonPanel.add(storeButton, BorderLayout.WEST);
        topButtonPanel.add(daysLbl, BorderLayout.CENTER);
        topButtonPanel.add(exitButton, BorderLayout.EAST);

        JButton advanceDayButton = new JButton("Advance Day");
        advanceDayButton.addActionListener(onAdvanceDayAction);

        JButton registerButton = new JButton("Register Farmer Type");
        registerButton.addActionListener(onRegisterAction);

        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.add(advanceDayButton, BorderLayout.CENTER);
        bottomButtonPanel.add(registerButton, BorderLayout.CENTER);

        add(topButtonPanel, BorderLayout.NORTH);

        add(bottomButtonPanel, BorderLayout.SOUTH);
    }

    public void displayTilesSet(ArrayList<ArrayList<Tile>> tileSet)
    {
        JPanel tilesPanel = new JPanel();
        tilesPanel.setLayout(new FlowLayout());
        tilesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        for(int i = 0; i < tileSet.size(); i++)
        {
            JPanel rowPanel = new JPanel();
            rowPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            rowPanel.setPreferredSize(new Dimension((int) (getSize().width * 0.9), getSize().height / 10));
            for(int j = 0; j < tileSet.get(i).size(); j++)
            {
                rowPanel.add((JButton) tileSet.get(i).get(j));
            }

            tilesPanel.add(rowPanel);
        }
        add(tilesPanel, BorderLayout.CENTER);
    }

    public void displayDay(int currDay)
    {
        daysLbl.setText("It is now Day: " + currDay);
    }
}
