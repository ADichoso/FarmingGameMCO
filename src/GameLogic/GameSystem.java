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
    private static Tile selectedTile;
    private static Crop selectedCrop;
    private static Renderer renderer;

    public static int getCurrDay() {
        return currDay;
    }

    public static ArrayList<FarmerType> getFarmerTypes() {
        return farmerTypes;
    }

    public static ArrayList<Crop> getCropTypes() {
        return cropTypes;
    }

    public static Tile getSelectedTile() {
        return selectedTile;
    }

    public static Crop getSelectedCrop() {
        return selectedCrop;
    }

    public static Renderer getRenderer() {
        return renderer;
    }

    public static void selectTile(Tile tile)
    {
        selectedTile = tile;
    }

    public static void selectCrop(String cropName)
    {
        for(Crop crop : cropTypes)
            if(crop.getName().equals(cropName))
                selectedCrop = crop;
    }
    public static Player getPlayer() {
        return player;
    }
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
        cropTypes.add(new Crop("Turnip", Crop.ROOT_CROP_TYPE, 5, 2, 1, 0, 2, 1, 1, 2, 6, 5f));
        cropTypes.add(new Crop("Carrot", Crop.ROOT_CROP_TYPE, 10,3, 1, 0, 2, 1, 1, 2, 9, 7.5f));
        cropTypes.add(new Crop("Potato", Crop.ROOT_CROP_TYPE, 20, 5, 3, 1, 4, 1, 1, 10, 3, 12.5f));
        cropTypes.add(new Crop("Rose", Crop.FLOWER_CROP_TYPE, 5, 1, 1, 0, 2, 1, 1, 1, 5, 2.5f));
        cropTypes.add(new Crop("Tulip", Crop.FLOWER_CROP_TYPE, 10,2, 2, 0, 3, 1, 1, 1, 9, 5f));
        cropTypes.add(new Crop("Sunflower", Crop.FLOWER_CROP_TYPE, 20, 3, 2, 1, 3, 2, 1, 1, 19, 7.5f));
        cropTypes.add(new Crop("Mango", Crop.FRUIT_TREE_CROP_TYPE, 100,10, 7, 4, 7, 4, 5, 15, 8, 25f));
        cropTypes.add(new Crop("Apple", Crop.FRUIT_TREE_CROP_TYPE, 200, 10, 7, 5, 7, 5, 10, 15, 5, 25f));
        selectCrop(cropTypes.get(0).getName());
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
                    String.valueOf(currCrop.getHarvestTime()),
                    String.valueOf(currCrop.getWaterNeed()),
                    String.valueOf(currCrop.getFertNeed()),
                    currCrop.getMinProduce() + "-" + currCrop.getMaxProduce(),
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

    public static String[] getCurrentFarmerTypeInfo()
    {
        return getFarmerTypeInfo(player.getFarmerType());
    }

    public static String[] getNextFarmerTypeInfo()
    {
        return getFarmerTypeInfo(getNextFarmerType());
    }

    private static String[] getFarmerTypeInfo(FarmerType farmerType)
    {
        String[] farmerTypeInfo =
                {
                    "<html>" + farmerType.getName() + "- on Level " + farmerType.getLevelReq() + "</html>",
                    "<html>Earns " + farmerType.getBonusEarn() + " bonus<br>Objectcoins per produce</html>",
                    "<html>Can water crops<br>" + farmerType.getWaterBonusLimInc() + " extra time/s!</html>",
                    "<html>Can fertilize crops<br>" + farmerType.getFertBonusLimInc() + " extra time/s!</html>",
                    "<html>All seeds are now<br>" + farmerType.getSeedCostReduct() + " Objectcoins cheaper!</html>",
                    "<html>Registration Fee:<br>" + farmerType.getRegFee() + " Objectcoins</html>"
                };

        return  farmerTypeInfo;
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

    public static String onPlow()
    {
        String message = player.plowTile(selectedTile);
        updateValues();

        return message;
    }

    public static String onShovel() {
        String message = player.useShovel(selectedTile);
        updateValues();

        return message;
    }

    public static String onPickaxe() {
        String message = player.removeRock(selectedTile);
        updateValues();

        return message;
    }

    public static String onPlant() {
        String message = player.plantCrop(selectedTile, selectedCrop);
        updateValues();
        return message;
    }

    public static String onWater() {
        String message = player.waterLand(selectedTile);
        updateValues();

        return message;
    }

    public static String onFert() {
        String message = player.fertilizeLand(selectedTile);
        updateValues();
        return message;
    }

    public static String onHarvest() {
        String message = player.harvestCrop(selectedTile);
        updateValues();
        return message;
    }

    public static String advancePlayerFarmerType()
    {
        String message = player.advanceFarmerType(getNextFarmerType());
        updateValues();
        return message;
    }

    public static void nextDay()
    {
        currDay++;
        renderer.advanceDay(currDay);
        updateValues();


    }
    private static String isGameOver()
    {
        /*End Game Conditions:
        1. Does not have any planted crops & does not have enough money to buy more seeds OR
        2. All tiles contain withered crops
         */

        int lowestSeedPrice = getLowestSeedPrice();

        if(!renderer.hasPlantedCrops() && player.getObjectCoins() < lowestSeedPrice)
            return "GAME OVER! You ran out of money to plant crops!";
        else if (renderer.isAllTilesWithered())
            return "GAME OVER! Due to your carelessness, all your crops died!";

        return "";
    }

    private static int getLowestSeedPrice()
    {
        int lowestSeedPrice = cropTypes.get(0).getCost();
        for(int i = 0; i < cropTypes.size(); i++)
            if(lowestSeedPrice > cropTypes.get(i).getCost())
                lowestSeedPrice = cropTypes.get(i).getCost();

        System.out.println("Lowest seed price is " + lowestSeedPrice);
        return lowestSeedPrice;
    }

    public static void updateValues()
    {
        renderer.updatePlayerStats(getPlayerInfo());
        renderer.initializeSeedStore(getSeedStore(), getSeedColumns());
        renderer.updateSelectedTileInfo(selectedTile);

        String gameOverMessage = isGameOver();
        if(!gameOverMessage.equals(""))
            renderer.showEndGameScreen(gameOverMessage);
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
        player = new Player(player.getName(), farmerTypes.get(0));
        quitGame();
    }

    public static void quitGame()
    {
        currDay = 1;
        renderer.resetGameFrames(WIDTH, HEIGHT);
        updateValues();
    }

    public static void main(String[] args) {
        initializeFarmerTypes();
        initializeCropTypes();

        renderer = new Renderer(WIDTH, HEIGHT);
    }
}