import java.util.ArrayList;

/** Player class which acts as a farmer for the farming game
 * @author Aaron Dichoso & Andrei Martin
 * @version 1.1
 * @since 24/10/2022
 */
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
        {
            if(tools.get(i).getID() == toolID)
            {
                return i;
            }
        }
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
    public void plowTile(Tile tile)
    {
        //If tile is not yet plowed & does not have a rock on it, plow the tile.

        char tileState = tile.getStateID();

        //Tile is not yet plowed, nor does it have a rock
        if(tileState == '_')
        {
            useTool('p');
            tile.setStateID('p');
        }
    }
}
