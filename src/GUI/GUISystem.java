package GUI;

import GameLogic.GameSystem;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/** The main renderer class used to render and handle all the GUI used in the program
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class GUISystem {
    private static final Color UNSELECTED_TILE_BG = new Color(124, 179, 66);
    private static final Color SELECTED_TILE_BG = new Color(80, 134, 24);

    private final int WIDTH;
    private final int HEIGHT;

    private ArrayList<ArrayList<Tile>> tileSet;
    private GameFrame gameFrame;
    private MainMenuFrame mainMenuFrame;
    private SeedsFrame seedsFrame;
    private StoreFrame storeFrame;
    private FarmerRegistrationFrame farmerRegistrationFrame;
    private GameOverFrame gameOverFrame;


    /**
     * Get the tile set
     * @return the tile set
     */
    public ArrayList<ArrayList<Tile>> getTileSet() {
        return tileSet;
    }

    /**
     * Get the main menu frame
     * @return the main menu frame
     */
    public MainMenuFrame getMainMenuFrame() {
        return mainMenuFrame;
    }

    /**
     * Get the seeds frame, which displays all the seed information to the player
     * @return the seeds frame, which displays all the seed information to the player
     */
    public SeedsFrame getSeedsFrame() {
        return seedsFrame;
    }

    /**
     * Get the store frame, which displays all the seed information to the player while allowing them to plant
     * @return the store frame, which displays all the seed information to the player while allowing them to plant
     */
    public StoreFrame getStoreFrame() {
        return storeFrame;
    }

    /**
     * Get the game frame, which is the main frame where the game is ran
     * @return the game frame, which is the main frame where the game is ran
     */
    public GameFrame getGameFrame() {
        return gameFrame;
    }

    /**
     * Initialize the GUI system
     * @param tile_cols is the number of columns which have tiles
     * @param tile_rows is the number of rows which have tiles
     */
    public GUISystem(int tile_cols, int tile_rows)
    {
        if(tile_cols > 0)
            this.WIDTH = tile_cols;
        else
            this.WIDTH = 10;

        if(tile_rows > 0)
            this.HEIGHT = tile_rows;
        else
            this.HEIGHT = 5;

        initializeGameFrames();
        initializeFrames();
        mainMenuFrame.setVisible(true);
    }

    /**
     * Initialize the tile set that contains the tiles in which the player can plant crops
     */
    private void initializeTileSet() {
        tileSet = new ArrayList<ArrayList<Tile>>();

        //Loop through width x height times
        for (int i = 0; i < WIDTH; i++)
        {
            ArrayList<Tile> tileRow = new ArrayList<Tile>(); //tile row

            //Instantiate a tile
            for (int j = 0; j < HEIGHT; j++) {
                Tile tile = new Tile(3,i * HEIGHT + j);

                tile.addActionListener(e ->
                    {
                        Tile source = (Tile) e.getSource();
                        GameSystem.selectTile(source);
                        highlightTile(source);

                        gameFrame.getToolsPanel().performSelectedButtonAction();
                    }
                ); //When a tile is clicked, select it, show tile info, and highlight the tile

                tileRow.add(tile);
            }
            tileSet.add(tileRow);
        }

        //Add the rocks
        generateRockMap();

        //Display the tile on the game frame
        gameFrame.initializeTiles(tileSet, WIDTH, HEIGHT);

        //select the very first tile as default
        GameSystem.selectTile(tileSet.get(0).get(0));
    }

    /**
     * Place rocks all over the tiles
     */
    private void generateRockMap()
    {
        FileInputStream in = null;

        //Try to read the generated rock config file, if possible
        try
        {
            in = new FileInputStream(RockMapGenerator.ROCK_MAP_FILE_NAME);

            int c;
            //Using the given tile ID, place a rock on there
            while((c = in.read()) != -1)
            {
                Tile tile = getTileWithID(c);
                if(!tile.equals(null))
                    tile.setStateID(Tile.ROCKY);
            }
        }
        catch (IOException e)
        {
            //Default rock if config not found
            Tile tile = getTileWithID(39);
            if(!tile.equals(null))
                tile.setStateID(Tile.ROCKY);

            System.out.println(e);
        }
    }

    /**
     * Initialize the frames that are used by the game frame. These have to be initialized BEFORE the tileset is initialized
     * Additionally, these frames need to be reinitialized again after the game is restarted,
     */
    private void initializeGameFrames()
    {
        //Store
        storeFrame = new StoreFrame(e -> storeFrame.setVisible(false));

        //Farmer Registration
        farmerRegistrationFrame = new FarmerRegistrationFrame
                (
                        e ->
                            {
                                //Player advances farmer type
                                String message = GameSystem.advancePlayerFarmerType();
                                gameFrame.showMessage(message);
                                farmerRegistrationFrame.setVisible(false);
                            },
                        e -> farmerRegistrationFrame.setVisible(false)
                );

        //Game Frame
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

        //Main menu
        mainMenuFrame = new MainMenuFrame
                (
                        e -> //On Game Start
                        {
                            if(mainMenuFrame.hasPlayerName()) {
                                GameSystem.startGame(mainMenuFrame.getPlayerName());

                                gameFrame.setVisible(true);
                                mainMenuFrame.setVisible(false);
                                mainMenuFrame.hideEmptyNameMessage();
                            } else
                                mainMenuFrame.showEmptyNameMessage();
                        }
                        ,
                        e -> System.exit(0) //On Quit
                );

        //Seeds
        seedsFrame = new SeedsFrame(e -> seedsFrame.setVisible(false));

        //Game Over
        gameOverFrame = new GameOverFrame
                (
                        e ->
                        {
                            restartGameFrames(); //Restart
                            gameOverFrame.setVisible(false);
                        },
                        e ->
                        {
                            exitGameFrames(); //Quit
                            gameOverFrame.setVisible(false);
                        }
                );
    }

    /**
     * Initialize the other frames that are used by the program. These do not need to be updated after the game is restarted
     */
    private void initializeFrames()
    {
        //Main menu
        mainMenuFrame = new MainMenuFrame
                (
                        e -> //On Game Start
                        {
                            if(mainMenuFrame.hasPlayerName()) {
                                GameSystem.startGame(mainMenuFrame.getPlayerName());

                                gameFrame.setVisible(true);
                                mainMenuFrame.setVisible(false);
                                mainMenuFrame.hideEmptyNameMessage();
                            } else
                                mainMenuFrame.showEmptyNameMessage();
                        }
                        ,
                        e -> System.exit(0) //On Quit
                );

        //Seeds
        seedsFrame = new SeedsFrame(e -> seedsFrame.setVisible(false));

        //Game Over
        gameOverFrame = new GameOverFrame
                (
                        e ->
                        {
                            restartGameFrames(); //Restart
                            gameOverFrame.setVisible(false);
                        },
                        e ->
                        {
                            exitGameFrames(); //Quit
                            gameOverFrame.setVisible(false);
                        }
                );
    }

    /**
     * Restart the game frames
     */
    private void restartGameFrames()
    {
        gameFrame.setVisible(false);
        tileSet.clear();
        GameSystem.resetGame();
        gameFrame.setVisible(true);
    }

    /**
     * Exit the game frames
     */
    private void exitGameFrames()
    {
        gameFrame.setVisible(false);
        mainMenuFrame.setVisible(true); //Quit Game
        tileSet.clear();
        GameSystem.quitGame();
    }

    /**
     * Show the end game screen to the player
     * @param message
     */
    public void showEndGameScreen(String message)
    {
        gameFrame.changeColorGameOver();
        gameOverFrame.showGameOverMessage(message);
    }
    public void updatePlayerStats(String[] playerStats)
    {
        gameFrame.updateStats(playerStats);
    }


    /**
     * update the crops in the tileset once a day has advanced and display the current day on the screen
     * @param currDay is the current day
     */
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

    public void highlightTile(Tile selectedTile)
    {
        gameFrame.getTilesInfoPanel().updateTileInfoTable(selectedTile.getTileInfo());

        for(int i = 0; i < tileSet.size(); i++) {
            for (int j = 0; j < tileSet.get(i).size(); j++) {
                tileSet.get(i).get(j).setBackground(UNSELECTED_TILE_BG);
            }
        }

        selectedTile.setBackground(SELECTED_TILE_BG);
    }

    public void resetGameFrames()
    {
        initializeGameFrames();
        initializeTileSet();
    }
    public boolean isSelectedTilesSurroundingEmpty(Tile selectedTile)
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
