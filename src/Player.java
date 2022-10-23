import java.util.ArrayList;

public class Player {
    private int experience;
    private int level;
    private int objectCoins;
    private ArrayList<Tool> tools;
    private ArrayList<SeedPacket> seeds;

    public Player()
    {
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
}
