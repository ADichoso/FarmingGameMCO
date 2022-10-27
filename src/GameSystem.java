import java.util.ArrayList;
import java.util.Scanner;

/** GameSystem used to run the farming game application
 * @author Aaron Dichoso & Andrei Martin
 * @version 1.1
 * @since 24/10/2022
 */
public class GameSystem {
    private static int currDay = 1;
    private static ArrayList<Tile> tileSet;
    private static final int WIDTH = 1;
    private static final int HEIGHT = 1;
    private static ArrayList<FarmerType> farmerTypes;
    private static ArrayList<Crop> cropTypes;
    private static Player player;
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
        cropTypes.add(new Crop("Turnip", "Root Crop", 5, 2, 1, 0, 2, 1, 1, 2, 6, 5f));
        cropTypes.add(new Crop("Carrot", "Root Crop", 10,3, 1, 0, 2, 1, 1, 2, 9, 7.5f));
        cropTypes.add(new Crop("Potato", "Root Crop", 20, 5, 3, 1, 2, 1, 1, 10, 3, 12.5f));
        cropTypes.add(new Crop("Rose", "Flower", 5, 1, 1, 0, 2, 1, 1, 1, 5, 2.5f));
        cropTypes.add(new Crop("Tulip", "Flower", 10,2, 2, 0, 3, 1, 1, 1, 9, 5f));
        cropTypes.add(new Crop("Sunflower", "Flower", 20, 3, 2, 1, 3, 2, 1, 1, 19, 7.5f));
        cropTypes.add(new Crop("Mango", "Fruit Tree", 100,10, 7, 4, 7, 4, 5, 15, 8, 25f));
        cropTypes.add(new Crop("Apple", "Fruit Tree", 200, 10, 7, 5, 7, 5, 10, 15, 5, 25f));
    }
    private static void initializeTileSet()
    {
        tileSet = new ArrayList<Tile>();

        for(int i = 0; i < WIDTH*HEIGHT; i++)
            tileSet.add(new Tile());
    }
    public static String selectCropName(Scanner sc)
    {
        String selectedCropName = "";
        boolean isValidInput = false;
        do
        {
            showSeedStore();
            System.out.println("=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Please input the name of the plant you wish to plant:");
            String userInput = sc.next();

            for(int i = 0; i < cropTypes.size(); i++)
            {
                if(cropTypes.get(i).getName().equalsIgnoreCase(userInput)) //Check if user put a name of a crop
                {
                    //Valid input
                    isValidInput = true;
                    selectedCropName = userInput;
                }
            }
        }while(!isValidInput);

        return selectedCropName;
    }

    public static Crop getCropTypeFromName(String cropName)
    {
        for(int i = 0; i < cropTypes.size(); i++)
        {
            if(cropTypes.get(i).getName().equalsIgnoreCase(cropName))
                return cropTypes.get(i);
        }
        return null;
    }

    public static void showSeedStore()
    {
        System.out.println("=-=-=-=SEED STORE=-=-=-=");
        for(int i = 0; i < cropTypes.size(); i++)
            System.out.printf("%d: %s\n", i, cropTypes.get(i).toString());
    }
    public static void showToolInfo()
    {
        System.out.println("=-=-=-=TOOL INFO=-=-=-=");
        for(int i = 0; i < player.getTools().size(); i++)
            System.out.printf("%d: %s\n", i, player.getTools().get(i).toString());
    }
    public static void showPlayerInfo()
    {
        System.out.println("=-=-=-=PLAYER INFO=-=-=-=");
        System.out.println("Name: " + player.getName());
        System.out.println("Title: " + player.getFarmerType());
        System.out.println("Objectcoins: " + player.getObjectCoins() + "@s");
        System.out.println("Current Level: "+ player.getLevel());
        System.out.println("Current EXP: " + player.getExperience());
    }
    public static void showTileInfo()
    {
        System.out.println("=-=-=CURRENT LAND INFO=-=-=");
        System.out.println("State: " + tileSet.get(0).getState());
        System.out.println("Watered: " + tileSet.get(0).isHasWater());
        System.out.println("Has Fertilizer: " + tileSet.get(0).isHasFert());
        System.out.println("Has Plant: " + tileSet.get(0).hasCrop());
        if(tileSet.get(0).hasCrop())
        {
            System.out.println("Current Plant: " + tileSet.get(0).getCrop().getName());
            System.out.println("Is Withered: " + tileSet.get(0).getCrop().isWithered());
        }
    }

    private static void showLandActions(Scanner sc)
    {
        boolean backGame = false;
        do {
            System.out.println("=-=-=Land Actions=-=-=");
            System.out.println("Check Land: check");
            System.out.println("Plow Land: plow");
            System.out.println("Plant Seeds: plant");
            System.out.println("Remove Rock: mine");
            System.out.println("Use Shovel: dig");
            System.out.println("Water Land: water");
            System.out.println("Fertilize Land: fert");
            System.out.println("Back to Actions: back");
            String userInput = sc.next();

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
                case "fert":
                    System.out.println("=-=-=Trying to Fertilize=-=-=");
                    player.fertilizeLand(tileSet.get(0));
                    break;
                case "back":
                    System.out.println("=-=-=Going back to options=-=-=");
                    backGame = true;
                default:
                    System.out.println("INVALID INPUT! Please Try Again!");
                    break;
            }
        }while(!backGame);
    }
    private static void showActions(Scanner sc)
    {
        boolean quitGame = false;
        do {
            System.out.println("Day " + currDay + ". What would you like to do?");
            System.out.println("Perform Action -> Land: land");
            System.out.println("Check Seeds Store: store");
            System.out.println("Check Tools: tool");
            System.out.println("Check Stats: stat");
            System.out.println("Go to Sleep: sleep");
            System.out.println("Quit Game: quit");
            String userInput = sc.next();

            switch (userInput.toLowerCase()) {
                case "land":
                    showLandActions(sc);
                    break;
                case "store":
                    showSeedStore();
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
                    System.out.println("INVALID INPUT! Please Try Again!");
                    break;
            }
        }while(!quitGame);
    }

    private static void showFarm()
    {
        for(int i = 0; i < tileSet.size(); i++)
        {
            System.out.printf("[%c]", tileSet.get(i).getStateID());

            if((i+1) % WIDTH == 0)
                System.out.println("");
        }
        System.out.println("");
    }

    private static void showMainMenu()
    {
        System.out.println("-=-=-=-=-=-=-=-=WELCOME TO GATHER SUN!=-=-=-=-=-=-=-=-=-");
        System.out.println("Play: p");
        System.out.println("Help: h");
        System.out.println("Exit: e");

        boolean quitGame = false;
        do
        {
            Scanner sc = new Scanner(System.in);

            String input = sc.nextLine();

            switch(input.toLowerCase()){
                case "p":
                    startGame();
                    break;
                case "h":
                    System.out.println("Its like harvest moon! :D");
                    break;
                case "q":
                    quitGame = true;
                    break;
                default:
                    System.out.println("INVALID INPUT! Please Try Again!");
                    break;
            }
        }while(!quitGame);
    }

    private static void startGame()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your name: ");
        String name = sc.nextLine();

        player = new Player(name, farmerTypes.get(0));

        System.out.println("Welcome " + player.getName() + " to your brand new farm!");

        showFarm();
        showActions(sc);
    }

    public static void main(String[] args) {
        initializeFarmerTypes();
        initializeCropTypes();
        initializeTileSet();

        showMainMenu();
    }
}
