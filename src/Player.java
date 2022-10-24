import java.util.ArrayList;

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
    private ArrayList<SeedPacket> seeds;


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
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getObjectCoins() {
        return objectCoins;
    }

    public void setObjectCoins(int objectCoins) {
        this.objectCoins = objectCoins;
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }

    public ArrayList<SeedPacket> getSeeds() {
        return seeds;
    }

    public void setSeeds(ArrayList<SeedPacket> seeds) {
        this.seeds = seeds;
    }

    public Player(String name)
    {
        this.name = name;
        this.experience = 0;
        this.level = 0;
        this.objectCoins = 100;

        initializeTools();
        initializeSeeds();
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

    private void initializeSeeds()
    {
        seeds = new ArrayList<SeedPacket>();
        seeds.add(new SeedPacket(0, 5, "Turnip"));
        seeds.add(new SeedPacket(0, 10, "Carrot"));
        seeds.add(new SeedPacket(0, 20, "Potato"));
        seeds.add(new SeedPacket(0, 5, "Rose"));
        seeds.add(new SeedPacket(0, 10, "Tulip"));
        seeds.add(new SeedPacket(0, 20, "Sunflower"));
        seeds.add(new SeedPacket(0, 100, "Mango"));
        seeds.add(new SeedPacket(0, 10, "Apple"));
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
            objectCoins -= toolToUse.getUseCost();
            experience += toolToUse.getExpGain();

            if(toolToUse.getUseCost() > 0)
                System.out.println(name + "uses " + toolToUse.getUseCost() + " objectcoins to use the " + toolToUse.getName());

            System.out.println(name + " uses a " + toolToUse.getName() + "!");
        }
    }

    public boolean hasSeeds()
    {
        for(int i = 0; i < seeds.size(); i++)
            if(seeds.get(i).getAmount() != 0)
                return true;
        return false;
    }
    public void checkSeedInv()
    {
        System.out.println("=-=-=-=SEED INVENTORY=-=-=-=");
        for(int i = 0; i < seeds.size(); i++)
            System.out.printf("%d. %s : %d", i, seeds.get(i).getCropName(), seeds.get(i).getAmount());
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

        if(tileState == '=')//Is Plowed
            tile.setCrop(crop);
        else
            System.out.println("Whoops! Looks like that tile is not yet plowed!");
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
            tile.setHasFert(false);
            tile.setHasWater(false);

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

        if(tileState == '=')
            tile.setHasWater(true);
        else
            System.out.println("Whoops! Looks like you can't water that tile!");
    }

    public void fertilizeLand(Tile tile)
    {
        //Check first if the tile has a rock
        char tileState = tile.getStateID();

        if(tileState == '=')
            tile.setHasFert(true);
        else
            System.out.println("Whoops! Looks like you can't fertilize that tile!");
    }
}
