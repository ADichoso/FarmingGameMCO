package GameLogic;

import GUI.Renderer;
import GUI.Tile;

import java.util.ArrayList;

/** GameLogic.GameSystem used to run the farming game application. The farming game allows the player to plant and harvest crops throughout the days.
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.0
 * @since 24/11/2022
 */
public class GameSystem {
    private static int currDay = 1;

    private static final int WIDTH = 10;
    private static final int HEIGHT = 5;
    private static ArrayList<FarmerType> farmerTypes;
    private static ArrayList<Crop> cropTypes;
    private static Player player;
    private static Renderer renderer;


    public static void incrementDay() {currDay++;}

    /**
     * Initialize the farmer types that the player can register to
     */
    private static void initializeFarmerTypes()
    {
        farmerTypes = new ArrayList<FarmerType>();
        farmerTypes.add(new FarmerType("Farmer", 0,0,0,0,0,0));
        farmerTypes.add(new FarmerType("Registered Farmer", 5,1,1,0,0,200));
        farmerTypes.add(new FarmerType("Distinguished Farmer", 10,2,2,1,0,300));
        farmerTypes.add(new FarmerType("Legendary Farmer", 15,4,3,2,1,400));
    }

    /**
     * Initialize the crop types that the player can buy and plant during the game
     */
    private static void initializeCropTypes()
    {
        cropTypes = new ArrayList<Crop>();
        cropTypes.add(new Crop("Turnip", "Root Crop", 5, 2, 1, 0, 2, 1, 1, 2, 6, 5f));
        cropTypes.add(new Crop("Carrot", "Root Crop", 10,3, 1, 0, 2, 1, 1, 2, 9, 7.5f));
        cropTypes.add(new Crop("Potato", "Root Crop", 20, 5, 3, 1, 4, 1, 1, 10, 3, 12.5f));
        cropTypes.add(new Crop("Rose", "Flower", 5, 1, 1, 0, 2, 1, 1, 1, 5, 2.5f));
        cropTypes.add(new Crop("Tulip", "Flower", 10,2, 2, 0, 3, 1, 1, 1, 9, 5f));
        cropTypes.add(new Crop("Sunflower", "Flower", 20, 3, 2, 1, 3, 2, 1, 1, 19, 7.5f));
        cropTypes.add(new Crop("Mango", "Fruit Tree", 100,10, 7, 4, 7, 4, 5, 15, 8, 25f));
        cropTypes.add(new Crop("Apple", "Fruit Tree", 200, 10, 7, 5, 7, 5, 10, 15, 5, 25f));
    }


    /**
     * Get a specified crop given its name
     * @param cropName is the name of the crop
     * @return the GameLogic.Crop with the specified name
     */
    private static Crop getCropTypeFromName(String cropName)
    {
        for(int i = 0; i < cropTypes.size(); i++)
        {
            if(cropTypes.get(i).getName().equalsIgnoreCase(cropName))
                return cropTypes.get(i);
        }
        return null;
    }

    /**
     * get the next available farmer type to the player
     * @return the farmer type after the current player's farmer type
     */
    private static FarmerType getNextFarmerType()
    {
        //Get the player's current farmer type's name
        String currFarmerTypeName = player.getFarmerType().getName();

        for(int i = 0; i < farmerTypes.size() - 1; i++)
            if(player.getFarmerType().equals(farmerTypes.get(i)))
            {
                return farmerTypes.get(i + 1);
            }

        return null;
    }

    /**
     * Show the seed store, containing the crop's information
     */
    private static String[][] getSeedStore()
    {
        ArrayList<String[]> seedStoreList = new ArrayList<String[]>();
        for(int i = 0; i < cropTypes.size(); i++) {
            Crop currCrop = cropTypes.get(i);
            String[] seedInfo = {
                    currCrop.getName(),
                    currCrop.getType(),
                    String.valueOf(currCrop.getCost() - player.getFarmerType().getSeedCostReduct()),
                    String.valueOf(currCrop.getHarvestTime()), String.valueOf(currCrop.getWaterNeed()),
                    String.valueOf(currCrop.getWaterLim() + player.getFarmerType().getWaterBonusLimInc()),
                    String.valueOf(currCrop.getFertNeed()),
                    String.valueOf(currCrop.getFertLim() + player.getFarmerType().getFertBonusLimInc()),
                    String.valueOf(currCrop.getMinProduce()), String.valueOf(currCrop.getMaxProduce()),
                    String.valueOf(currCrop.getSellPrice() + player.getFarmerType().getBonusEarn()),
                    String.valueOf(currCrop.getExpYield())
            };
            seedStoreList.add(seedInfo);
        }

        String[][] seedStore = new String[seedStoreList.size()][seedStoreList.get(0).length];

        for(int i = 0; i < seedStore.length; i++)
        {
            seedStore[i] = new String[seedStoreList.get(i).length];
            for(int j = 0; j < seedStore[i].length; j++)
            {
                seedStore[i][j] = seedStoreList.get(i)[j];
            }
        }


        return seedStore;
    }

    private static String[] getSeedColumns()
    {
        String[] seedColumns = {"Name","Crop Type","Costs","Maturity","Water Requirements","Fert Requirements","Yield Amount","Sell Price per Produce","EXP Gain"};
        return seedColumns;
    }
    /**
     * Show all of the information of all of the tools that the player can use.
     */
    private static String[][] getToolsInfo()
    {
        ArrayList<String[]> toolsInfoList = new ArrayList<String[]>();
        for(int i = 0; i < player.getTools().size(); i++)
        {
            Tool currTool = player.getTools().get(i);
            String[] toolInfo = {
                    currTool.getName(),
                    String.valueOf(currTool.getID()),
                    String.valueOf(currTool.getUseCost()),
                    String.valueOf(currTool.getExpGain())
            };

            toolsInfoList.add(toolInfo);
        }
        System.out.println("-------------------------------------------------------------");

        String[][] toolsInfo = new String[toolsInfoList.size()][toolsInfoList.get(0).length];

        for(int i = 0; i < toolsInfo.length; i++)
        {
            toolsInfo[i] = new String[toolsInfoList.get(i).length];
            for(int j = 0; j < toolsInfo[i].length; j++)
            {
                toolsInfo[i][j] = toolsInfoList.get(i)[j];
            }
        }

        return toolsInfo;
    }

    private static String[] getToolColumns()
    {
        String[] toolColumns = {"Name","ID","Use Cost","Exp Gain"};
        return toolColumns;
    }

    /**
     * Show the player's information and statistics
     */
    private static String[] getPlayerInfo()
    {
        ArrayList<String> playerInfoList = new ArrayList<String>();
        playerInfoList.add("Name: " + player.getName());
        playerInfoList.add("Title: " + player.getFarmerType().getName());
        playerInfoList.add("Objectcoins: " + player.getObjectCoins());
        playerInfoList.add("Current Level: "+ player.getLevel());
        playerInfoList.add("Current EXP: " + player.getExperience());

        String[] playerInfo = new String[playerInfoList.size()];

        for(int i = 0; i < playerInfo.length; i++)
        {
            playerInfo[i] = playerInfoList.get(i);
        }

        return playerInfo;
    }

    /**
     * Starts the game
     * @param playerName is the name of the player
     */
    public static void startGame(String playerName)
    {
        player = new Player(playerName, farmerTypes.get(0));
        updateValues();
    }

    public static void resetGame()
    {
        currDay = 1;
    }

    public static void updateValues()
    {
        renderer.updatePlayerStats(getPlayerInfo());
        renderer.initializeSeedStore(getSeedStore(), getSeedColumns());
        renderer.advanceDay(currDay);
    }

    public static void main(String[] args) {
        initializeFarmerTypes();
        initializeCropTypes();

        renderer = new Renderer(WIDTH, HEIGHT);
    }
}
    /*
    private static void showLandActions(Scanner sc)
    {
        boolean backGame = false;
        do {
            System.out.println();
            System.out.println("=-=-=Land Actions=-=-=");
            System.out.println("Check Land: check");
            System.out.println("Plow Land: plow");
            System.out.println("Plant Seeds: plant");
            System.out.println("Remove Rock: mine");
            System.out.println("Use Shovel: dig");
            System.out.println("Water Land: water");
            System.out.println("Fertilize Land: fertilize");
            System.out.println("Harvest crop: harvest");
            System.out.println("Back to Actions: back");
            String userInput = sc.nextLine();

            System.out.println("");
            switch (userInput.toLowerCase()) {
                case "check":
                    showTileInfo();
                    break;
                case "plow":
                    System.out.println("=-=-=Trying to Plow Land=-=-=");
                    player.plowTile(tileSet.get(0));
                    break;
                case "plant":
                    System.out.println("=-=-=Trying to Plant=-=-=");
                    Crop crop = getCropTypeFromName(selectCropName(sc));
                    player.plantCrop(tileSet.get(0), crop);
                    break;
                case "mine":
                    System.out.println("=-=-=Trying to Mine=-=-=");
                    player.removeRock(tileSet.get(0));
                    break;
                case "dig":
                    System.out.println("=-=-=Trying to Dig=-=-=");
                    player.useShovel(tileSet.get(0));
                    break;
                case "water":
                    System.out.println("=-=-=Trying to Water=-=-=");
                    player.waterLand(tileSet.get(0));
                    break;
                case "fertilize":
                    System.out.println("=-=-=Trying to Fertilize=-=-=");
                    player.fertilizeLand(tileSet.get(0));
                    break;
                case "harvest":
                    System.out.println("=-=-=Trying to Harvest=-=-=");
                    player.harvestCrop(tileSet.get(0));
                    break;
                case "back":
                    System.out.println("=-=-=Going Back to Options=-=-=");
                    backGame = true;
                    break;
                default:
                    System.out.println("INVALID INPUT!!! Please Try Again!");
                    break;
            }
            System.out.println("");
        }while(!backGame);
    }
    private static void showActions(Scanner sc)
    {
        boolean quitGame = false;
        do {
            System.out.println("Day " + currDay + ". What would you like to do?");
            System.out.println("Perform Action -> Land: land");
            System.out.println("Check Seeds Store: store");
            System.out.println("Register Farmer Type: register");
            System.out.println("Check Tools: tool");
            System.out.println("Check Stats: stat");
            System.out.println("Go to Sleep: sleep");
            System.out.println("Quit Game: quit");
            String userInput = sc.nextLine();


            System.out.println("");
            switch (userInput.toLowerCase()) {
                case "land":
                    showLandActions(sc);
                    break;
                case "store":
                    showSeedStore();
                    break;
                case "register":
                    player.advanceFarmerType(getNextFarmerType());
                    break;
                case "tool":
                    showToolInfo();
                    break;
                case "stat":
                    showPlayerInfo();
                    break;
                case "sleep":
                    System.out.println("=-=-=Going To Sleep=-=-=");
                    advanceDay();
                    break;
                case "quit":
                    System.out.println("=-=-=Quitting Game=-=-=");
                    quitGame = true;
                    break;
                default:
                    System.out.println("INVALID INPUT!! Please Try Again!");
                    break;
            }
            System.out.println("");
        }while(!quitGame);
    }
    */