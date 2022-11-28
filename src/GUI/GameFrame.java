package GUI;

import GameLogic.GameSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    private JLabel daysLbl;
    private JPanel tilesPanel;
    private JLabel messageLabel;
    private TileInfoPanel tilesInfoPanel;
    private ToolsPanel toolsPanel;
    private StatsPanel statsPanel;

    public JPanel getTilesPanel() {
        return tilesPanel;
    }
    public JLabel getMessageLabel(){return messageLabel;}

    public JLabel getDaysLbl() {
        return daysLbl;
    }

    public TileInfoPanel getTilesInfoPanel() {
        return tilesInfoPanel;
    }

    public ToolsPanel getToolsPanel() {
        return toolsPanel;
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    public GameFrame(
            StoreFrame storeFrame,
            ActionListener onOpenStoreAction,
            ActionListener onAdvanceDayAction,
            ActionListener onRegisterAction,
            ActionListener onQuitAction)
    {
        setName("GATHER SUN");
        setLayout(new BorderLayout());
        setSize(1080, 520);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        daysLbl = new JLabel();

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(onQuitAction);

        JButton storeButton = new JButton("Open Store");
        storeButton.addActionListener(onOpenStoreAction);


        JPanel topButtonPanel = new JPanel();
        topButtonPanel.add(storeButton, BorderLayout.WEST);
        topButtonPanel.add(daysLbl, BorderLayout.CENTER);
        topButtonPanel.add(exitButton, BorderLayout.EAST);

        JButton advanceDayButton = new JButton("Advance Day");
        advanceDayButton.addActionListener(onAdvanceDayAction);

        JButton registerButton = new JButton("Register Farmer Type");
        registerButton.addActionListener(onRegisterAction);

        messageLabel = new JLabel("HELLLLLLLLLLLLLLO");

        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.add(advanceDayButton, BorderLayout.SOUTH);
        bottomButtonPanel.add(registerButton, BorderLayout.SOUTH);

        add(topButtonPanel, BorderLayout.NORTH);

        initializeToolsPanel(storeFrame);
        tilesPanel = new JPanel();
        add(tilesPanel, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new FlowLayout());
        eastPanel.setPreferredSize(new Dimension(getWidth() / 4, getHeight() / 4));

        initializeStatsPanel(eastPanel);
        initializeTileInfoPanel(eastPanel);

        add(eastPanel, BorderLayout.EAST);
        add(bottomButtonPanel, BorderLayout.SOUTH);


        pack();
    }

    private void initializeToolsPanel(StoreFrame storeFrame)
    {
        toolsPanel = new ToolsPanel(
                e -> showMessage(GameSystem.onPlow()), //Plow
                e -> showMessage(GameSystem.onShovel()),//Shovel
                e -> showMessage(GameSystem.onPickaxe()), //Pickaxe
                e ->
                {
                    if(!storeFrame.isVisible()) storeFrame.setVisible(true); //Plant
                    else showMessage(GameSystem.onPlant());
                },
                e -> showMessage(GameSystem.onWater()), //Water
                e -> showMessage(GameSystem.onFert()), //Fert
                e -> showMessage(GameSystem.onHarvest()) //Harvest
                );

        add(toolsPanel, BorderLayout.WEST);
    }

    public void showMessage(String message)
    {
        messageLabel.setText(message);
    }

    private void initializeTileInfoPanel(JPanel parent)
    {
        tilesInfoPanel = new TileInfoPanel();
        parent.add(tilesInfoPanel);
    }

    private void initializeStatsPanel(JPanel parent)
    {
        statsPanel = new StatsPanel();
        parent.add(statsPanel);
    }

    public void updateStats(String[] playerStats)
    {
        statsPanel.updatePlayerTable(playerStats);
    }
    public void displayTilesSet(ArrayList<ArrayList<Tile>> tileSet, int tile_row, int tile_col)
    {
        Dimension panelDim = new Dimension((int)((tile_row + 1) * tileSet.get(0).get(0).getPreferredSize().getWidth()), (int)((tile_col - 1) * tileSet.get(0).get(0).getPreferredSize().getHeight()));

        tilesPanel.setLayout(new FlowLayout());
        tilesPanel.setPreferredSize(panelDim);
        for(int i = 0; i < tileSet.size(); i++)
        {
            JPanel rowPanel = new JPanel();
            for(int j = 0; j < tileSet.get(i).size(); j++)
            {
                rowPanel.add(tileSet.get(i).get(j));
            }

            tilesPanel.add(rowPanel);
        }

        tilesPanel.add(messageLabel);
        pack();
    }

    public void displayDay(int currDay)
    {
        daysLbl.setText("It is now Day: " + currDay);
    }
}
