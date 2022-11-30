package GameLogic;

import GUI.GUISystem;
import GUI.Tile;

import java.util.ArrayList;

/** GameLogic.GameSystem used to run the farming game application. The farming game allows the player to plant and harvest crops throughout the days.
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class GameSystem {
    private static int currDay = 1;
    private static ArrayList<FarmerType> farmerTypes;
    private static ArrayList<Crop> cropTypes;
    private static Player player;
    private static Tile selectedTile;
    private static Crop selectedCrop;
    private static GUISystem guiSystem;

    /**
     * return the current day value
     * @return the current day
     */
    public static int getCurrDay() {
        return currDay;
    }

    /**
     * Return the arraylist containing the Farmer Types the player can choose
     * @return the arraylist containing the Farmer Types the player can choose
     */
    public static ArrayList<FarmerType> getFarmerTypes() {
        return farmerTypes;
    }

    /**
     * Return the arraylist containing the Crop Types the player can buy
     * @return the arraylist containing the Crop Types the player can buy
     */
    public static ArrayList<Crop> getCropTypes() {
        return cropTypes;
    }

    /**
     * Return the currently selected tile of the user
     * @return the currently selected tile of the user
     */
    public static Tile getSelectedTile() {
        return selectedTile;
    }

    /**
     * Return the currently selected crop of the user
     * @return the currently selected crop of the user
     */
    public static Crop getSelectedCrop() {
        return selectedCrop;
    }

    /**
     * Get the renderer that the game uses to render GUI
     * @return renderer that the game uses to render GUI
     */
    public static GUISystem getGUISystem() {
        return guiSystem;
    }

    /**
     * Get the player object used in the application
     * @return player object used in the application
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * Select a different tile that will be highlighted by the program
     * @param tile is the tile to be selected
     */
    public static void selectTile(Tile tile)
    {
        if(tile != null)
            selectedTile = tile;
    }

    /**
     * Select a different crop that will be used for planting. Nothing will be performed if the cropName does not refer to a real crop
     * @param cropName is the name of the crop that was to be selected.
     */
    public static void selectCrop(String cropName)
    {
        for(Crop crop : cropTypes)
            if(crop.getName().equals(cropName))
                selectedCrop = crop;
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
        farmerTypes.add(new FarmerType("NULL", -1, -1, -1, -1, -1, -1));
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
     * Show the seed store, containing the crop's information
     * @return a 2D array of strings, with each row showing the information of 1 crop.
     */
    private static String[][] getSeedStore()
    {
        String[][] seedStore = new String[cropTypes.size()][];
        for(int i = 0; i < seedStore.length; i++) {
            Crop currCrop = cropTypes.get(i);
            seedStore[i] = new String[]{
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
        }

        return seedStore;
    }

    /**
     * Get the column headers of the croptypes to be used for organizing crop type information
     * @return an array of strings containing the column headers.
     */
    private static String[] getSeedColumns()
    {
        String[] seedColumns = {"Name","Crop Type","Costs","Maturity","Water Requirements","Fert Requirements","Yield Amount","Sell Price per Produce","EXP Gain"};
        return seedColumns;
    }

    /**
     * Get the lowest seed price available in the crop types array
     * @return the lowest seed price available.
     */
    private static int getLowestSeedPrice()
    {
        int lowestSeedPrice = cropTypes.get(0).getCost();
        for(int i = 0; i < cropTypes.size(); i++)
            if(lowestSeedPrice > cropTypes.get(i).getCost())
                lowestSeedPrice = cropTypes.get(i).getCost();

        return lowestSeedPrice;
    }

    /**
     * get the next available farmer type to the player
     * @return the farmer type after the current player's farmer type
     */
    private static FarmerType getNextFarmerType()
    {
        //Go through the farmer types and find the player's current farmer type
        for(int i = 0; i < farmerTypes.size() - 1; i++)
            if(player.getFarmerType().equals(farmerTypes.get(i)))
                return farmerTypes.get(i + 1); //return the next one

        return farmerTypes.get(farmerTypes.size() - 1); //return null if not found / player is already at highest tier
    }

    /**
     * Get the information about a farmer type.
     * @param farmerType is a farmer type
     * @return an array of strings, containing the farmer type's information
     */
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

        if(farmerType.getName().equals("NULL")) {
            String[] nullInfo = new String[]{};
            return nullInfo;
        } else
            return  farmerTypeInfo;
    }

    /**
     * Get the current farmer type of the player
     * @return the current farmer type of the player
     */
    public static String[] getCurrentFarmerTypeInfo()
    {
        return getFarmerTypeInfo(player.getFarmerType());
    }

    /**
     * Get the next farmer type of the player
     * @return the next farmer type of the player
     */
    public static String[] getNextFarmerTypeInfo()
    {
        return getFarmerTypeInfo(getNextFarmerType());
    }

    /**
     * Show the player's information and statistics
     */
    private static String[] getPlayerInfo()
    {
        String[] playerInfo = new String[]
                {
                        "Name: " + player.getName(),
                        "Title: " + player.getFarmerType().getName(),
                        "Objectcoins: " + player.getObjectCoins(),
                        "Current Level: "+ player.getLevel(),
                        "Current EXP: " + player.getExperience()
                };

        return playerInfo;
    }

    /**
     * displays the player's output message after advancing to a next farmer type
     * @return the player's message after advancing to a next farmer type.
     */
    public static String advancePlayerFarmerType()
    {
        String message = player.advanceFarmerType(getNextFarmerType());
        updateGUI();
        return message;
    }

    /**
     * displays the player's output message after performing a plow action.
     * @return the player's message after plowing the selected tile.
     */
    public static String onPlow()
    {
        String message = player.plowTile(selectedTile);
        updateGUI();

        return message;
    }

    /**
     * displays the player's output message after performing a shovel action.
     * @return the player's message after digging the selected tile.
     */
    public static String onShovel() {
        String message = player.useShovel(selectedTile);
        updateGUI();

        return message;
    }

    /**
     * displays the player's output message after performing a pickaxe action.
     * @return the player's message after using a pickaxe on the selected tile.
     */
    public static String onPickaxe() {
        String message = player.removeRock(selectedTile);
        updateGUI();

        return message;
    }

    /**
     * displays the player's output message after performing a plant action.
     * @return the player's message after planting the selected tile.
     */
    public static String onPlant() {
        String message = player.plantCrop(selectedTile, selectedCrop);
        updateGUI();

        return message;
    }

    /**
     * displays the player's output message after performing a water action.
     * @return the player's message after watering the selected tile.
     */
    public static String onWater() {
        String message = player.waterLand(selectedTile);
        updateGUI();

        return message;
    }

    /**
     * displays the player's output message after performing a fertilizer action.
     * @return the player's message after fertilizing the selected tile.
     */
    public static String onFert() {
        String message = player.fertilizeLand(selectedTile);
        updateGUI();

        return message;
    }

    /**
     * displays the player's output message after performing a harvest action.
     * @return the player's message after harvesting the selected tile.
     */
    public static String onHarvest() {
        String message = player.harvestCrop(selectedTile);
        updateGUI();

        return message;
    }

    /**
     * Update the renderer's GUI values according to the new values contained in the game system, such as an advanced day, new player info, etc.
     */
    public static void updateGUI()
    {
        guiSystem.updatePlayerStats(getPlayerInfo());
        guiSystem.updateSeedStore(getSeedStore());
        guiSystem.highlightTile(selectedTile);

        String gameOverMessage = isGameOver();
        if(!gameOverMessage.equals(""))
            guiSystem.showEndGameScreen(gameOverMessage);
    }

    /**
     * Initialize the renderer's GUI values at the start of the game
     */
    public static void initializeGUI()
    {
        guiSystem.updatePlayerStats(getPlayerInfo());
        guiSystem.initializeSeedStore(getSeedStore(), getSeedColumns());
        guiSystem.highlightTile(selectedTile);

        String gameOverMessage = isGameOver();
        if(!gameOverMessage.equals(""))
            guiSystem.showEndGameScreen(gameOverMessage);
    }
    /**
     * Advance to the next day and update values.
     */
    public static void nextDay()
    {
        currDay++;
        guiSystem.advanceDay(currDay);
        updateGUI();
    }

    /**
     * Check if the game has reached an end game condition
     * @return an end game message or an empty string if the game has not ended.
     */
    private static String isGameOver()
    {
        /*End Game Conditions:
        1. Does not have any planted crops & does not have enough money to buy more seeds OR
        2. All tiles contain withered crops
         */

        int lowestSeedPrice = getLowestSeedPrice();

        if(!guiSystem.hasPlantedCrops() && player.getObjectCoins() < lowestSeedPrice)
            return "GAME OVER! You ran out of money to plant crops!";
        else if (guiSystem.isAllTilesWithered())
            return "GAME OVER! Due to your carelessness, all your crops died!";

        return "";
    }

    /**
     * Starts the game
     * @param playerName is the name of the player
     */
    public static void startGame(String playerName)
    {
        player = new Player(playerName, farmerTypes.get(0));
        initializeGUI();
    }

    /**
     * Reset the game by reinitializing the player
     */
    public static void resetGame()
    {
        player = new Player(player.getName(), farmerTypes.get(0));
        quitGame();
    }

    /**
     * Reset values once the game has been quit out of.
     */
    public static void quitGame()
    {
        currDay = 1;
        guiSystem.resetFrames();
        updateGUI();
    }

    public static void main(String[] args) {
        initializeFarmerTypes();
        initializeCropTypes();

        guiSystem = new GUISystem(10, 5);
    }
}