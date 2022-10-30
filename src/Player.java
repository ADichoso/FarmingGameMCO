import java.util.ArrayList;
import java.util.Random;

/** Player class which acts as a farmer for the farming game
 * @author Aaron Dichoso & Andrei Martin
 * @version 1.1
 * @since 24/10/2022
 */

//TODO: SeedsPackets is implemented wrong, Player should only buy seeds WHEN they will PLANT. THERE IS NO INVENTORY.
public class Player {
    private String name;
    private float experience;
    private int level;
    private int objectCoins;
    private ArrayList<Tool> tools;

    private FarmerType farmerType;

    public FarmerType getFarmerType() {
        return farmerType;
    }

    public void setFarmerType(FarmerType farmerType) {
        this.farmerType = farmerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        if(experience >= 0)
            this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level >= 0)
            this.level = level;
    }

    public int getObjectCoins() {
        return objectCoins;
    }

    public void setObjectCoins(int objectCoins) {
        if(objectCoins >= 0)
            this.objectCoins = objectCoins;
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }

    public void spendObjectCoins(int cost)
    {
        setObjectCoins(getObjectCoins() - cost);
    }

    public void gainObjectCoins(int cost)
    {
        setObjectCoins(getObjectCoins() + cost);
    }

    public void addExperience(float addExp)
    {
        setExperience(getExperience() + addExp);
    }

    public void levelUp()
    {
        setLevel(getLevel() + 1);
    }

    public Player(String name, FarmerType farmerType)
    {
        this.name = name;
        this.experience = 0;
        this.level = 0;
        this.objectCoins = 100;
        this.farmerType = farmerType;
        initializeTools();
    }

    private void initializeTools()
    {
        tools = new ArrayList<Tool>();
        tools.add(new Tool("Plow", 'p', 0, 0.5f));
        tools.add(new Tool("Watering Can", 'w', 0, 0.5f));
        tools.add(new Tool("Fertilizer", 'f', 10, 4));
        tools.add(new Tool("Pickaxe", 'x', 50, 15));
        tools.add(new Tool("Shovel", 's', 7, 2));
    }
    private int getTool(char toolID)
    {
        for(int i = 0; i < tools.size(); i++)
            if(tools.get(i).getID() == toolID)
                return i;
        return -1;
    }
    public void useTool(char toolID)
    {
        int toolIndex = getTool(toolID);
        Tool toolToUse = tools.get(toolIndex);

        //Check if player has enough objectcoins to use
        if(toolToUse.getUseCost() <= objectCoins)
        {
            spendObjectCoins(toolToUse.getUseCost());
            experience += toolToUse.getExpGain();

            if(toolToUse.getUseCost() > 0)
                System.out.println(name + "uses " + toolToUse.getUseCost() + " objectcoins to use the " + toolToUse.getName());

            System.out.println(name + " uses a " + toolToUse.getName() + "!");
        } else
            System.out.println("Whoops! Looks like you do not have enough Objectcoins for that tool!");

    }

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

    public void plantCrop(Tile tile, Crop crop)
    {
        //Check first if the tile is plowed
        char tileState = tile.getStateID();

        int cost = crop.getCost() - farmerType.getSeedCostReduct();

        if(cost < 0)
            cost = 0;

        if(tileState == '=') {
            //Is Plowed
            if(cost <= objectCoins) {
                tile.setCrop(crop); //Plant the crop
                spendObjectCoins(cost); //Spend the amount of coins it costs for the plant

                System.out.println(name + " has planted a " + crop.getName());
            }
            else
                System.out.println("Whoops! Looks like you do not have enough Objectcoins for that crop!");
        }
        else
            System.out.println("Whoops! Looks like that tile is not yet plowed!");
    }

    public void harvestCrop(Tile tile)
    {
        //Check first if tile is plowed
        char tileState = tile.getStateID();

        if(tileState == '=')
        {
            if(tile.getCrop() != null)
            {
                if(tile.getCrop().isMature()) {
                    //Sell the crop
                    Random rand = new Random();
                    int numProduce = rand.nextInt(tile.getCrop().getMinProduce(), tile.getCrop().getMaxProduce() + 1);
                    float harvestBonus = tile.getCrop().getSellPrice() + farmerType.getBonusEarn() * numProduce;
                    float waterBonus = numProduce * 0.2f * (tile.getCrop().getWaterTimes() - 1);
                    float fertBonus = numProduce * 0.5f * tile.getCrop().getFertTimes();


                    int earnings = (int) (harvestBonus + waterBonus + fertBonus);
                    System.out.println(name + " harvested " + numProduce + " " + tile.getCrop().getName() + "/s!");
                    System.out.println(name + " has earned " + earnings + " Objectcoins!");
                    gainObjectCoins(earnings);

                    float expGain = tile.getCrop().getExpYield();
                    System.out.println(name + " has gained " + expGain + " EXP!");
                    addExperience(expGain);


                    //Remove the crop
                    tile.setCrop(null);
                    tile.setStateID('_');
                }
                else
                    System.out.println("Whoops! Looks like the crop is not yet matured!");
            }
            else
                System.out.println("Whoops! Looks like that tile does not have a crop!");
        }
        else
            System.out.println("Whoops! Looks like that tile is not plowed!");
    }
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
    public void useShovel(Tile tile)
    {
        //You can use the shovel in any tile
        char tileState = tile.getStateID();

        useTool('s');

        if(tileState == '=' || tileState == '_') //Tile with Crop
        {
            //Has rock
            tile.setStateID('_'); //Unplowed Tile

            if(tile.hasCrop()) {
                tile.setCrop(null);
                System.out.println("You removed the crop!");
            }
            else
                System.out.println("The ground's water and fertilizer is gone!");
        } else
            System.out.println("You used your shovel! That did nothing!");
    }

    public void waterLand(Tile tile)
    {
        //Check first if the tile has a rock
        char tileState = tile.getStateID();

        if(tileState == '=') {
            tile.waterCrop();
            System.out.println(name + " has watered the land!");
        }
        else
            System.out.println("Whoops! Looks like you can't water that tile!");
    }

    public void fertilizeLand(Tile tile)
    {
        //Check first if the tile has a rock
        char tileState = tile.getStateID();

        if(tileState == '=') {
        tile.fertilizeCrop();
        System.out.println(name + " has put fertilizer!");
        }
        else
            System.out.println("Whoops! Looks like you can't fertilize that tile!");
    }
}
