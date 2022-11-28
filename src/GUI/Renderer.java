package GUI;

import GameLogic.GameSystem;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Renderer {
    private static final Color UNSELECTED_TILE_BG = new Color(124, 179, 66);
    private static final Color SELECTED_TILE_BG = new Color(80, 134, 24);
    private static ArrayList<ArrayList<Tile>> tileSet;
    private GameFrame gameFrame;
    private HelpFrame helpFrame;
    private MainMenuFrame mainMenuFrame;
    private SeedsFrame seedsFrame;
    private StoreFrame storeFrame;
    private FarmerRegistrationFrame farmerRegistrationFrame;
    private GameOverFrame gameOverFrame;

    public ArrayList<ArrayList<Tile>> getTileSet() {
        return tileSet;
    }

    public HelpFrame getHelpFrame() {
        return helpFrame;
    }

    public MainMenuFrame getMainMenuFrame() {
        return mainMenuFrame;
    }

    public SeedsFrame getSeedsFrame() {
        return seedsFrame;
    }

    public StoreFrame getStoreFrame() {
        return storeFrame;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public Renderer(int tile_width, int tile_height)
    {
        initializeGameFrames();
        initializeTileSet(tile_width, tile_height);
        initializeFrames();
        mainMenuFrame.setVisible(true);
    }

    /**
     * Initialize the tile set that contains the tiles in which the player can plant crops
     */
    private void initializeTileSet(int tile_row, int tile_col) {
        tileSet = new ArrayList<ArrayList<Tile>>();

        for (int i = 0; i < tile_col; i++)
        {
            ArrayList<Tile> tileRow = new ArrayList<Tile>();
            for (int j = 0; j < tile_row; j++) {
                Tile tile = new Tile(3,i * tile_row + j);

                tile.addActionListener(e ->
                    {
                        Tile source = (Tile) e.getSource();
                        GameSystem.selectTile(source);
                        updateSelectedTileInfo(source);

                        gameFrame.getToolsPanel().performSelectedButtonAction();
                    }
                );

                tileRow.add(tile);
            }
            System.out.println("Row #" + i);
            tileSet.add(tileRow);
        }

        generateRockMap();

        gameFrame.displayTilesSet(tileSet, tile_row, tile_col);
        GameSystem.selectTile(tileSet.get(0).get(0));
    }

    private void generateRockMap()
    {
        FileInputStream in = null;

        try
        {
            in = new FileInputStream(RockMapGenerator.ROCK_MAP_FILE_NAME);

            int c;
            while((c = in.read()) != -1)
            {
                Tile tile = getTileWithID(c);
                if(!tile.equals(null))
                    tile.setStateID(Tile.ROCKY);
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    private void initializeGameFrames()
    {
        storeFrame = new StoreFrame(e -> storeFrame.setVisible(false));
        farmerRegistrationFrame = new FarmerRegistrationFrame
                (
                        e ->
                            {
                            String message = GameSystem.advancePlayerFarmerType();
                            gameFrame.showMessage(message);
                            farmerRegistrationFrame.setVisible(false);
                            },
                        e -> farmerRegistrationFrame.setVisible(false)
                );
        gameFrame = new GameFrame
                (
                        storeFrame,
                        e -> seedsFrame.setVisible(true), //Open the store
                        e -> GameSystem.nextDay(), //Advance the day
                        e ->
                            {
                                farmerRegistrationFrame.setVisible(true);
                                farmerRegistrationFrame.displayFarmerTypeStats(GameSystem.getCurrentFarmerTypeInfo(), GameSystem.getNextFarmerTypeInfo());
                            }, //Register Farmer Type
                        e -> exitGameFrames() //Quit Game
                );
    }
    private void initializeFrames()
    {
        helpFrame = new HelpFrame
                (
                        e ->
                        {
                            mainMenuFrame.setVisible(true);
                            helpFrame.setVisible(false);
                        }
                );
        mainMenuFrame = new MainMenuFrame
                (
                        e ->
                        {
                            if(mainMenuFrame.hasPlayerName()) {
                                GameSystem.startGame(mainMenuFrame.getPlayerName());

                                gameFrame.setVisible(true);
                                mainMenuFrame.setVisible(false);
                                mainMenuFrame.hideEmptyNameMessage();
                            } else
                            {
                                mainMenuFrame.showEmptyNameMessage();
                            }
                        }
                        ,
                        e ->
                        {
                            helpFrame.setVisible(true);
                            mainMenuFrame.setVisible(false);
                        }
                        ,
                        e -> System.exit(0)
                );
        seedsFrame = new SeedsFrame(e -> seedsFrame.setVisible(false));
        gameOverFrame = new GameOverFrame
                (
                    e ->
                    {
                        restartGameFrames();
                        gameOverFrame.setVisible(false);
                    },
                    e ->
                    {
                        exitGameFrames();
                        gameOverFrame.setVisible(false);
                    }
                );

    }

    private void restartGameFrames()
    {
        gameFrame.setVisible(false);
        tileSet.clear();
        GameSystem.resetGame();
        gameFrame.setVisible(true);
    }

    private void exitGameFrames()
    {
        gameFrame.setVisible(false);
        mainMenuFrame.setVisible(true); //Quit Game
        tileSet.clear();
        GameSystem.quitGame();
    }

    public void showEndGameScreen(String message)
    {
        gameOverFrame.showGameOverMessage(message);
    }
    public void updatePlayerStats(String[] playerStats)
    {
        gameFrame.updateStats(playerStats);
    }


    public void advanceDay(int currDay)
    {
        for(int i = 0; i < tileSet.size(); i++) {
            for (int j = 0; j < tileSet.get(i).size(); j++) {
                tileSet.get(i).get(j).onAdvanceDay();
            }
        }

        gameFrame.displayDay(currDay);
    }

    public void updateSeedStore(String[][] seedStore)
    {
        seedsFrame.updateStore(seedStore);
        storeFrame.updateStore(seedStore);
    }

    public void initializeSeedStore(String[][] seedStore, String[] seedColumns)
    {
        seedsFrame.addStoreHeaders(seedColumns);
        storeFrame.addStoreHeaders(seedColumns);

        String[] seedNames = new String[seedStore.length];
        for(int i = 0; i < seedNames.length; i++)
        {
            seedNames[i] = seedStore[i][0];
        }

        storeFrame.initializeCropSelectionButtons(seedNames, gameFrame.getMessageLabel());

        updateSeedStore(seedStore);
    }

    public void updateSelectedTileInfo(Tile selectedTile)
    {
        gameFrame.getTilesInfoPanel().updateTileInfoTable(selectedTile.getTileInfo());

        for(int i = 0; i < tileSet.size(); i++) {
            for (int j = 0; j < tileSet.get(i).size(); j++) {
                tileSet.get(i).get(j).setBackground(UNSELECTED_TILE_BG);
            }
        }

        selectedTile.setBackground(SELECTED_TILE_BG);
    }

    public void resetGameFrames(int tile_row, int tile_col)
    {
        initializeGameFrames();
        initializeTileSet(tile_row, tile_col);
    }
    public boolean isSelectedTilesAdjacentEmpty(Tile selectedTile)
    {
        int tileID = selectedTile.getTileID();
        int topTileID = tileID - 10;
        int bottomTileID = tileID + 10;

        int[] surroundingTileIDs = {topTileID - 1, topTileID, topTileID + 1, tileID - 1, tileID + 1, bottomTileID - 1, bottomTileID, bottomTileID + 1};

        //check if any of the surroundingTileIDs is negative / over the number of tiles
        int tiles_amt = tileSet.size() * tileSet.get(0).size();

        boolean isOutsideRange = false;
        for(int ID : surroundingTileIDs)
        {
            if(ID < 0 || ID > tiles_amt)
            {
                isOutsideRange = true;
                break;
            }
        }

        //If not outside range, check each tiles if all of them are empty
        if(isOutsideRange)
            return false;
        else
        {
            for(int ID : surroundingTileIDs)
            {
                //Get tile with corresponding ID
                Tile tile = getTileWithID(ID);
                if(tile.hasCrop() || tile.getStateID() == Tile.ROCKY)
                    return false;
            }

            return true;
        }
    }

    public Tile getTileWithID(int tileID)
    {
        for(int i = 0; i < tileSet.size(); i++)
        {
            for(int j = 0; j < tileSet.get(i).size(); j++)
            {
                if(tileSet.get(i).get(j).getTileID() == tileID)
                    return tileSet.get(i).get(j);
            }
        }

        return null;
    }

    public boolean isAllTilesWithered()
    {
        for(int i = 0; i < tileSet.size(); i++)
        {
            for(int j = 0; j < tileSet.get(i).size(); j++)
            {
                if(tileSet.get(i).get(j).hasCrop()) {
                    if (!tileSet.get(i).get(j).getCrop().isWithered())
                        return false;
                } else
                    return false;
            }
        }

        return true;
    }

    public boolean hasPlantedCrops()
    {
        for(int i = 0; i < tileSet.size(); i++)
        {
            for(int j = 0; j < tileSet.get(i).size(); j++)
            {
                if(tileSet.get(i).get(j).hasCrop())
                    return true;
            }
        }

        return false;
    }
}

/*
*
private static void getTileInfo()
{
    System.out.println("=-=-=CURRENT LAND INFO=-=-=");
    System.out.println("State: " + tileSet.get(0).getState());
    System.out.println("Has Plant: " + tileSet.get(0).hasCrop());
    if(tileSet.get(0).hasCrop())
    {
        System.out.println("\t>> Current Plant: " + tileSet.get(0).getCrop().getName());
        System.out.println("\t\t> Age: " + tileSet.get(0).getCrop().getAge());
        System.out.println("\t\t> Ready for Harvest: " + tileSet.get(0).getCrop().isReadyForHarvest());
        System.out.println("\t\t> Is Withered: " + tileSet.get(0).getCrop().isWithered());
        System.out.println("\t\t> Times Watered: " + tileSet.get(0).getCrop().getWaterTimes());
        System.out.println("\t\t\t- Has Water Needs: " + tileSet.get(0).getCrop().hasWaterNeeds());
        System.out.println("\t\t> Times Fertilized: " + tileSet.get(0).getCrop().getFertTimes());
        System.out.println("\t\t\t- Has Fertilizer Needs: " + tileSet.get(0).getCrop().hasFertNeeds());
    }
}

* */
