package GUI;

import GameLogic.GameSystem;

import java.awt.*;
import java.util.ArrayList;

public class Renderer {
    private static ArrayList<ArrayList<Tile>> tileSet;
    private GameFrame gameFrame;
    private HelpFrame helpFrame;
    private MainMenuFrame mainMenuFrame;
    private StatsFrame statsFrame;
    private StoreFrame storeFrame;

    private TileInfoFrame tileInfoFrame;

    public Renderer(int tile_width, int tile_height)
    {
        initializeFrames();
        initializeTileSet(tile_width, tile_height);
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
                Tile tile = new Tile(new Dimension(gameFrame.getSize().width / (tile_row + 1), gameFrame.getSize().width / (tile_row + 1)),i * tile_row + j);

                tile.addActionListener(e ->
                {
                    Tile source = (Tile) e.getSource();
                    tileInfoFrame.setVisible(true);
                    tileInfoFrame.updateTileInfoTable(source.getTileInfo());
                });

                tileRow.add(tile);
            }
            System.out.println("Row #" + i);
            tileSet.add(tileRow);
        }
        gameFrame.displayTilesSet(tileSet);
    }


    private void initializeFrames()
    {
        gameFrame = new GameFrame
                (
                        e ->
                        {
                            storeFrame.setVisible(true); //Open the store
                        }
                        ,
                        e ->
                        {
                            statsFrame.setVisible(true); //Show the stats
                        }
                        ,
                        e ->
                        {
                            GameSystem.updateValues(); //Advance the day
                        },
                        e ->
                        {
                            GameSystem.updateValues(); //Register Farmer Type
                        },
                        e ->
                        {
                            mainMenuFrame.setVisible(true); //Quit Game
                            gameFrame.setVisible(false);
                            GameSystem.resetGame();
                        }
                );
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
                        e ->
                        {
                            System.exit(0);
                        }
                );
        statsFrame = new StatsFrame
                (
                        e ->
                        {
                            statsFrame.setVisible(false);
                        }
                );
        storeFrame = new StoreFrame
                (
                        e ->
                        {
                            storeFrame.setVisible(false);
                        }
                );
        tileInfoFrame = new TileInfoFrame
                (
                    e ->
                    {
                        tileInfoFrame.setVisible(false);
                    }
                );
    }

    public void updatePlayerStats(String[] playerStats)
    {
        statsFrame.updatePlayerTable(playerStats);
    }


    public void advanceDay(int currDay)
    {
        GameSystem.incrementDay();

        for(int i = 0; i < tileSet.size(); i++)
            for(int j = 0; j < tileSet.get(i).size(); j++)
                if(!tileSet.get(i).get(j).getCrop().isNullCrop()) tileSet.get(i).get(j).getCrop().growCrop();

        gameFrame.displayDay(currDay);
    }

    public void updateSeedStore(String[][] seedStore)
    {
        storeFrame.updateStore(seedStore);
    }

    public void initializeSeedStore(String[][] seedStore, String[] seedColumns)
    {
        storeFrame.addStoreHeaders(seedColumns);
        updateSeedStore(seedStore);
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
