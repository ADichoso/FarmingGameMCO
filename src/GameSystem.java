import java.util.ArrayList;

public class GameSystem {
    private static int currDay = 1;
    private static ArrayList<Tile> tileSet;
    private static ArrayList<FarmerType> farmerTypes;
    private static ArrayList<Crop> cropTypes;

    private static void advanceDay()
    {
        System.out.println("It is now day " + ++currDay);
    }

    private static void initializeFarmerTypes()
    {
        farmerTypes = new ArrayList<FarmerType>();
        farmerTypes.add(new FarmerType("Farmer", 0,0,0,0,0,0));
        farmerTypes.add(new FarmerType("Registered Farmer", 5,1,1,0,0,200));
        farmerTypes.add(new FarmerType("Distinguished Farmer", 10,2,2,1,0,300));
        farmerTypes.add(new FarmerType("Legendary Farmer", 15,4,3,2,1,400));
    }

    private static void initializeCropTypes()
    {
        cropTypes = new ArrayList<Crop>();
        cropTypes.add(new Crop("Turnip", "Root Crop", 2, 1, 0, 2, 1, 1, 2, 6, 5f));
        cropTypes.add(new Crop("Carrot", "Root Crop", 3, 1, 0, 2, 1, 1, 2, 9, 7.5f));
        cropTypes.add(new Crop("Potato", "Root Crop", 5, 3, 1, 2, 1, 1, 10, 3, 12.5f));
        cropTypes.add(new Crop("Rose", "Flower", 1, 1, 0, 2, 1, 1, 1, 5, 2.5f));
        cropTypes.add(new Crop("Tulip", "Flower", 2, 2, 0, 3, 1, 1, 1, 9, 5f));
        cropTypes.add(new Crop("Sunflower", "Flower", 3, 2, 1, 3, 2, 1, 1, 19, 7.5f));
        cropTypes.add(new Crop("Mango", "Fruit Tree", 10, 7, 4, 7, 4, 5, 15, 8, 25f));
        cropTypes.add(new Crop("Apple", "Fruit Tree", 10, 7, 5, 7, 5, 10, 15, 5, 25f));
    }

    public static void main(String[] args) {
        initializeFarmerTypes();
        System.out.println("Yo");
    }
}
