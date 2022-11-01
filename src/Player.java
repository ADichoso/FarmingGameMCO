import java.util.ArrayList;
import java.util.Random;

/** Player class which acts as a farmer for the farming game
 * @author Aaron Dichoso & Andrei Martin
 * @version 2.1
 * @since 01/11/2022
 */

public class Player {
    private String name;
    private float experience;
    private int level;
    private int objectCoins;
    private ArrayList<Tool> tools;

    private FarmerType farmerType;

    /**
     * Initializes the starting stats of the player. Name is specified by user while farmer type is taken from a pre-defined set of farmertypes.
     * @param name
     * @param farmerType
     */
    public Player(String name, FarmerType farmerType)
    {
        this.name = name;
        this.experience = 0;
        this.level = 0;
        this.objectCoins = 100;
        this.farmerType = farmerType;
        initializeTools();
    }

    /**
     * Give the farmertype of the player
     * @return the farmer type of the player
     */
    public FarmerType getFarmerType() {
        return farmerType;
    }

    /**
     * Set the farmertype of the player
     * @param farmerType is the new farmertype of the player
     */
    public void setFarmerType(FarmerType farmerType) {
        if(farmerType != null)
            this.farmerType = farmerType;
        else
            this.farmerType = null; //No farmer type
    }


    /**
     * Return the name of the player
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Return the amount of EXP the player has
     * @return the amount of EXP the player has
     */
    public float getExperience() {
        return experience;
    }

    /**
     * Set the XP value of the player (Cannot be lower than 0)
     * @param experience is the new EXP value of the player
     */
    public void setExperience(float experience) {
        if(experience >= 0)
            this.experience = experience;
    }

    /**
     * Return the current level of the player
     * @return the current level of the palyer
     */
    public int getLevel() {
        return level;
    }


    /**
     * Set the level of the player (Cannot be lower than 0)
     * @param level is the new level of the player
     */
    public void setLevel(int level) {
        if(level >= 0)
            this.level = level;
    }

    /**
     * Return the amount of objectcoins that the player has
     * @return the amount of objectcoins that the player has
     */
    public int getObjectCoins() {
        return objectCoins;
    }

    /**
     * Set the amount of objectcoins that the player will have (Cannot be lower than 0)
     * @param objectCoins is the new amount of objectcoins that the player will have
     */
    public void setObjectCoins(int objectCoins) {
        if(objectCoins >= 0)
            this.objectCoins = objectCoins;
    }

    /**
     * Get the different tools that the player uses
     * @return the different tools that the player uses (In ArrayList format)
     */
    public ArrayList<Tool> getTools() {
        return tools;
    }

    /**
     * Initialize the pre-determined set of tools that the player uses
     */
    private void initializeTools()
    {
        tools = new ArrayList<Tool>();
        tools.add(new Tool("Plow", 'p', 0, 0.5f));
        tools.add(new Tool("Watering Can", 'w', 0, 0.5f));
        tools.add(new Tool("Fertilizer", 'f', 10, 4));
        tools.add(new Tool("Pickaxe", 'x', 50, 15));
        tools.add(new Tool("Shovel", 's', 7, 2));
    }


    /**
     * deduct the amount of objectcoins the player uses in performing an action
     * @param cost is the amount of objectcoins the player uses
     */
    public void spendObjectCoins(int cost)
    {
        setObjectCoins(getObjectCoins() - cost);
    }

    /**
     * Add some objectcoins for the player
     * @param cost is the additional amount of objectcoins the player has earned
     */
    public void gainObjectCoins(int cost)
    {
        setObjectCoins(getObjectCoins() + cost);
    }

    /**
     * Add EXP to the player's current EXP
     * @param addExp is the amount of EXP to add
     */
    public void gainExperience(float addExp)
    {
        setExperience(getExperience() + addExp); //Add some EXP

        //Check if the player's current experience allows them to level up
        /*
         * Level 0: 0 - 99 XP
         * Level 1: 100 - 199 XP
         * */
        while((getExperience() / 100.0f) >= (float) (getLevel() + 1)) //Level up if EXP cap is met
            levelUp();
    }

    /**
     * Increments the player's level by 1
     */
    public void levelUp()
    {
        setLevel(getLevel() + 1);
        System.out.println(name + " has advanced to level " + getLevel() + "!");
    }

    /**
     * Find a specific tool given its ID
     * @param toolID is the ID of the tool to find
     * @return the index in the tools array where the tool is found (-1 if none)
     */
        public int getToolByID(char toolID)
    {
        for(int i = 0; i < tools.size(); i++)
            if(tools.get(i).getID() == toolID)
                return i;
        return -1;
    }

    /**
     * Use a tool that has a specified toolID. Gain some EXP but may lose some objectcoins.
     * @param toolID is the toolID of the tool to use
     */
    public void useTool(char toolID)
    {
        int toolIndex = getToolByID(toolID);
        Tool toolToUse = tools.get(toolIndex);

        //Check if player has enough objectcoins to use
        if(toolToUse.getUseCost() <= objectCoins)
        {
            gainExperience(toolToUse.getExpGain());

            System.out.println(name + " uses " + toolToUse.getUseCost() + " Objectcoins to use the " + toolToUse.getName());
            spendObjectCoins(toolToUse.getUseCost());
        } else
            System.out.println("Whoops! Looks like you do not have enough Objectcoins for that tool!");

    }


    /**
     * Try to plow a specific tile
     * @param tile is the tile to plow
     */
    public void plowTile(Tile tile)
    {
        //If tile is not yet plowed & does not have a rock on it, plow the tile.

        char tileState = tile.getStateID();

        //Tile is not yet plowed, nor does it have a rock
        if(tileState == '_')
        {
            useTool('p');
            tile.setStateID('='); //Plowed Tile
        }
        else
            System.out.println("Whoops! Looks like you cannot plow on that tile!");
    }

    /**
     * Try to plant a crop on a tile
     * @param tile is the tile to plant on
     * @param crop is the crop to plant
     */
    public void plantCrop(Tile tile, Crop crop)
    {
        //Check first if the tile is plowed
        char tileState = tile.getStateID();

        int cost = crop.getCost() - farmerType.getSeedCostReduct(); //Get the cost of the crop

        if(cost < 0)
            cost = 0;


        if(tileState == '=') {
            //Is Plowed

            //Check if player has enough money to buy the crop
            if(cost <= objectCoins) {
                tile.setCrop(crop); //Plant the crop
                spendObjectCoins(cost); //Spend the amount of coins it costs for the plant

                System.out.println(name + " spends " + cost + " Objectcoins to plant a/n " + crop.getName() + " " + crop.getType() + "!");
            }
            else
                System.out.println("Whoops! Looks like you do not have enough Objectcoins for that crop!");
        }
        else
            System.out.println("Whoops! Looks like that tile is not yet plowed!");
    }

    /**
     * Try to harvest a crop on a tile
     * @param tile is the tile to harvest on
     */
    public void harvestCrop(Tile tile)
    {
        //Check first if tile is plowed
        char tileState = tile.getStateID();

        if(tileState == '=')
        {
            //Is Plowed

            //Check if tile has a crop
            if(tile.getCrop() != null)
            {

                //Check if tile's crop is not withered
                if(!tile.getCrop().isWithered()) {

                    //Check if crop is ready for harvest
                    if (tile.getCrop().isReadyForHarvest()) {


                        //Sell the crop
                        Random rand = new Random();
                        int numProduce = rand.nextInt(tile.getCrop().getMinProduce(), tile.getCrop().getMaxProduce() + 1); //Yield of crop

                        //Bonuses
                        float harvestBonus = tile.getCrop().getSellPrice() + farmerType.getBonusEarn() * numProduce;
                        float waterBonus = numProduce * 0.2f * (tile.getCrop().getWaterTimes() - 1);
                        float fertBonus = numProduce * 0.5f * tile.getCrop().getFertTimes();

                        //Show and get earnings
                        int earnings = (int) (harvestBonus + waterBonus + fertBonus);
                        System.out.println(name + " harvested " + numProduce + " " + tile.getCrop().getName() + "/s!");
                        System.out.println(name + " has earned " + earnings + " Objectcoins!");
                        gainObjectCoins(earnings);

                        //Show and get EXP
                        float expGain = tile.getCrop().getExpYield();
                        System.out.println(name + " has gained " + expGain + " EXP!");
                        gainExperience(expGain);


                        //Remove the crop
                        tile.setCrop(null);
                        tile.setStateID('_');
                    }
                    else
                        System.out.println("Whoops! Looks like the crop is not yet ready for harvest!");
                }
                else
                    System.out.println("Whoops! Looks like that crop is already withered!");
            }
            else
                System.out.println("Whoops! Looks like that tile does not have a crop!");
        }
        else
            System.out.println("Whoops! Looks like that tile is not plowed!");
    }

    /**
     * Try to remove a rock on a tile
     * @param tile is the tile tha tmay have a rock to remove with.
     */
    public void removeRock(Tile tile)
    {
        //Check first if the tile has a rock
        char tileState = tile.getStateID();

        if(tileState == '^')
        {
            //Has rock
            useTool('x');
            tile.setStateID('_'); //Unplowed Tile
            System.out.println(name + " has removed a rock!");
        } else
            System.out.println("Whoops! Looks like there's no rock on that tile!");
    }

    /**
     * Try to use a shovel on a tile
     * @param tile is the tile to dig in.
     */
    public void useShovel(Tile tile)
    {
        //You can use the shovel in any tile
        char tileState = tile.getStateID();

        useTool('s'); //Shovel will get used no matter what

        if(tileState == '=' || tileState == '_') //Tile not a rock
        {
            tile.setStateID('_'); //Unplow Tile

            //Remove the crop, if available
            if(tile.hasCrop()) {
                tile.setCrop(null);
                System.out.println("You removed the crop!");
            }
            else
                System.out.println("The ground's water and fertilizer is gone!");
        } else
            System.out.println("You used your shovel! That did nothing!"); //Tile is a rock
    }


    /**
     * try to water a tile
     * @param tile is the tile to water
     */
    public void waterLand(Tile tile)
    {
        //Check first if the tile is plowed
        char tileState = tile.getStateID();

        if(tileState == '=') {
            //Is Plowed


            //Check if tile has a crop
            if(tile.hasCrop()) {
                //Check if crop is not withered
                if(!tile.getCrop().isWithered()) {
                    //If not withered, water the crop
                    tile.waterCrop();
                    useTool('w');
                    System.out.println(name + " has watered the land!");
                } else
                    System.out.println("Whoops! Looks like that crop is already withered!");
            }
            else
                System.out.println("Whoops! Looks like you don't have a crop in that tile!");
        }
        else
            System.out.println("Whoops! Looks like you can't water that tile!");
    }

    /**
     * try to fertilize a tile
     * @param tile is the tile to fertilize
     */
    public void fertilizeLand(Tile tile)
    {
        //Check first if the tile has a rock
        char tileState = tile.getStateID();

        if(tileState == '=') { //Is plowed
            if(tile.hasCrop()) { //has a crop
                if(!tile.getCrop().isWithered()) { ///not withered
                    //Fertilize crop
                    tile.fertilizeCrop();
                    useTool('f');
                    System.out.println(name + " has put fertilizer!");
                }
                else
                    System.out.println("Whoops! Looks like that crop is already withered!");
            }
            else
                System.out.println("Whoops! Looks like you don't have a crop in that tile!");
        }
        else
            System.out.println("Whoops! Looks like you can't fertilize that tile!");
    }

    /**
     * Advance the player's crop type to the next level
     * @param newFarmerType is the new FarmerType of the player
     */
    public void advanceFarmerType(FarmerType newFarmerType)
    {
        //Check if player still has a next Farmer Type (not max FarmerType)
        if(!newFarmerType.equals(null)) {
            //Check if player has enough levels to advance
            if (newFarmerType.getLevelReq() == getLevel()) {
                //Can register, check if player has enough money to do so
                if (newFarmerType.getRegFee() <= getObjectCoins()) {
                    //Has enough objectcoins, proceed to register!
                    System.out.println(name + " has become a " + newFarmerType.getName() + "!");
                    spendObjectCoins(newFarmerType.getRegFee());
                    setFarmerType(newFarmerType);
                } else
                    System.out.println("Whoops! Looks like you don't have enough Objectcoins to register!");
            } else
                System.out.println("Whoops! Looks like you are not a high enough level to register!");
        } else
            System.out.println("You are already at the highest registry!");

    }
}
