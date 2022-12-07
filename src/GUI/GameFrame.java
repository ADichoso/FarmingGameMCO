package GUI;

import GameLogic.GameSystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** A JFrame that contains the main game area of the application
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.3
 * @since 07/12/2022
 */
public class GameFrame extends ParentFrame {

    private JLabel daysLbl;
    private JPanel tilesPanel;
    private JLabel messageLabel;
    private TileInfoPanel tilesInfoPanel;
    private ToolsPanel toolsPanel;
    private StatsPanel statsPanel;

    /**
     * Return the tiles Panel, which contains all of the planting tiles in the game
     * @return the tiles Panel, which contains all of the planting tiles in the game
     */
    public JPanel getTilesPanel() {
        return tilesPanel;
    }

    /**
     * Return the message label, which sends messages according to the player's actions
     * @return the message label, which sends messages according to the player's actions
     */
    public JLabel getMessageLabel(){return messageLabel;}

    /**
     * Get the days label, which shows the current day
     * @return the days label, which shows the current day
     */
    public JLabel getDaysLbl() {
        return daysLbl;
    }

    /**
     * get the tile info panel, which displays the tile information for a selected tile
     * @return the tile info panel, which displays the tile information for a selected tile
     */
    public TileInfoPanel getTilesInfoPanel() {
        return tilesInfoPanel;
    }

    /**
     * Get the tools panel, which contains all of the tools that the player can use
     * @return the tools panel, which contains all of the tools that the player can use
     */
    public ToolsPanel getToolsPanel() {
        return toolsPanel;
    }

    /**
     * Get the stats panel, which shows the player's current statistics and information.
     * @return the stats panel, which shows the player's current statistics and information.
     */
    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    /**
     * Initialize the Game Frame
     * @param storeFrame is the store frame used in the program
     * @param onOpenStoreAction is the action performed when needing to open the storeFrame
     * @param onAdvanceDayAction is the action performed when needing to advance the day
     * @param onRegisterAction is the action performed when needing to register for a farmer type
     * @param onQuitAction is the action performed when needing to close the game
     */
    public GameFrame(
            StoreFrame storeFrame,
            ActionListener onOpenStoreAction,
            ActionListener onAdvanceDayAction,
            ActionListener onRegisterAction,
            ActionListener onQuitAction)
    {
        super();

        //Basic JFrame attributes
        getContentPane().setBackground(BG_COLOR);
        setName("GATHER SUN");
        setLayout(new BorderLayout());
        setSize(1080, 520);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Displays current day
        daysLbl = new JLabel();

        //Buttons found on the top of the frame
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(onQuitAction);

        JButton storeButton = new JButton("Open Store");
        storeButton.addActionListener(onOpenStoreAction);


        JPanel topButtonPanel = new JPanel();
        topButtonPanel.add(storeButton, BorderLayout.WEST);
        topButtonPanel.add(daysLbl, BorderLayout.CENTER);
        topButtonPanel.add(exitButton, BorderLayout.EAST);
        topButtonPanel.setOpaque(false);

        add(topButtonPanel, BorderLayout.NORTH);

        //Components to the left
        initializeToolsPanel(storeFrame);
        tilesPanel = new JPanel();
        tilesPanel.setOpaque(false);

        //Components at the center
        add(tilesPanel, BorderLayout.CENTER);

        //Components at the right
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new FlowLayout());
        eastPanel.setPreferredSize(new Dimension(getWidth() / 4, getHeight() / 4));
        eastPanel.setOpaque(false);

        initializeStatsPanel(eastPanel);
        initializeTileInfoPanel(eastPanel);

        add(eastPanel, BorderLayout.EAST);

        //Buttons found on the bottom of the frame
        JButton advanceDayButton = new JButton("Advance Day");
        advanceDayButton.addActionListener(onAdvanceDayAction);

        JButton registerButton = new JButton("Register Farmer Type");
        registerButton.addActionListener(onRegisterAction);

        messageLabel = new JLabel();

        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.add(advanceDayButton, BorderLayout.SOUTH);
        bottomButtonPanel.add(registerButton, BorderLayout.SOUTH);
        bottomButtonPanel.setOpaque(false);

        add(bottomButtonPanel, BorderLayout.SOUTH);

        //Display the first day, default to 1
        displayDay(1);
    }

    /**
     * Initialize the tools panel
     * @param storeFrame is the store frame which allows the player to pick and choose a seed to plant on a tile
     */
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

        toolsPanel.setOpaque(false);
        add(toolsPanel, BorderLayout.WEST);
    }

    /**
     * Initialize the tile info panel, which hold sinformation for the selected tile
     * @param parent is the parent that the tiles info panel will be attached to
     */
    private void initializeTileInfoPanel(JPanel parent)
    {
        tilesInfoPanel = new TileInfoPanel();
        parent.add(tilesInfoPanel);
    }


    /**
     * Initialize the stats panel, which shows the player's current statistics, like exp, level, and coins
     * @param parent is the parent that the stats panel will be attached to
     */
    private void initializeStatsPanel(JPanel parent)
    {
        statsPanel = new StatsPanel();
        parent.add(statsPanel);
    }

    /**
     * Initialize the tile set.
     * @param tileSet is the set which holds all of hte tiles in the program
     * @param tile_row is the number of tiles in a row
     * @param tile_col is the number of columns in a row
     */
    public void initializeTiles(ArrayList<ArrayList<Tile>> tileSet, int tile_row, int tile_col)
    {
        //Set panel dimensions
        Dimension panelDim = new Dimension((int)((tile_row + 1) * tileSet.get(0).get(0).getPreferredSize().getWidth()), (int)((tile_col - 1) * tileSet.get(0).get(0).getPreferredSize().getHeight()));

        tilesPanel.setLayout(new FlowLayout());
        tilesPanel.setPreferredSize(panelDim);

        //Loop through tile set
        for(int i = 0; i < tileSet.size(); i++)
        {
            //Add tiles to a row, then add the row to the panel
            JPanel rowPanel = new JPanel();
            for(int j = 0; j < tileSet.get(i).size(); j++)
            {
                rowPanel.add(tileSet.get(i).get(j));
            }

            tilesPanel.add(rowPanel);
        }

        tilesPanel.add(messageLabel);
    }

    /**
     * Update the values shown in the stats panel
     * @param playerStats is the new stats of the player
     */
    public void updateStats(String[] playerStats)
    {
        statsPanel.updatePlayerTable(playerStats);
    }


    /**
     * Display the current day to the user
     * @param currDay is the current day
     */
    public void displayDay(int currDay)
    {
        daysLbl.setText("It is now Day: " + currDay);
    }

    /**
     * Show a message to the user after it performs an action
     * @param message is the message to display
     */
    public void showMessage(String message)
    {
        messageLabel.setText(message);
    }
}
