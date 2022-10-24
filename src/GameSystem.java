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
        cropTypes.add(new Crop("Turnip", "Root Crop", 2, 1, 0, 2, 1, 1, 2, 6, 5f));
        cropTypes.add(new Crop("Carrot", "Root Crop", 3, 1, 0, 2, 1, 1, 2, 9, 7.5f));
        cropTypes.add(new Crop("Potato", "Root Crop", 5, 3, 1, 2, 1, 1, 10, 3, 12.5f));
        cropTypes.add(new Crop("Rose", "Flower", 1, 1, 0, 2, 1, 1, 1, 5, 2.5f));
        cropTypes.add(new Crop("Tulip", "Flower", 2, 2, 0, 3, 1, 1, 1, 9, 5f));
        cropTypes.add(new Crop("Sunflower", "Flower", 3, 2, 1, 3, 2, 1, 1, 19, 7.5f));
        cropTypes.add(new Crop("Mango", "Fruit Tree", 10, 7, 4, 7, 4, 5, 15, 8, 25f));
        cropTypes.add(new Crop("Apple", "Fruit Tree", 10, 7, 5, 7, 5, 10, 15, 5, 25f));
    }

    private static void initializeTileSet()
    {
        tileSet = new ArrayList<Tile>();

        for(int i = 0; i < WIDTH*HEIGHT; i++)
            tileSet.add(new Tile());
    }
    private static void showMainMenu()
    {
        System.out.println("-=-=-=-=-=-=-=-=WELCOME TO GATHER SUN!=-=-=-=-=-=-=-=-=-");
        System.out.println("Play: p");
        System.out.println("Help: h");
        System.out.println("Quit: quit");

        boolean isValidInput = false;
        boolean quitGame = false;
        do
        {
            Scanner sc = new Scanner(System.in);

            String input = sc.nextLine();

            switch(input.toLowerCase()){
                case "p":
                    startGame();
                    isValidInput = true;
                    break;
                case "h":
                    System.out.println("Its like harvest moon! :D");
                    isValidInput = true;
                    break;
                case "q":
                    isValidInput = true;
                    quitGame = true;
                    break;
                default:
                    System.out.println("INVALID INPUT! Please Try Again!");
                    break;
            }
        }while(!isValidInput);

        if(!quitGame)
            showMainMenu();
    }

    private static void startGame()
    {
        boolean isGameOver = false;
        Scanner sc = new Scanner(System.in);
        while(!isGameOver)
        {
            System.out.println("Please enter your name: ");
            String name = sc.nextLine();

            player = new Player(name);

            System.out.println("Welcome " + player.getName() + " to your brand new farm!");

            showFarm();
            showActions(sc);
        }
    }

    private static void showLandActions(Scanner sc)
    {
        boolean isValidInput = false;
        do {
            System.out.println("=-=-=Land Actions=-=-=");
            System.out.println("Check Land: land");
            System.out.println("Plow Land: plow");
            System.out.println("Plant Seeds: plant");
            System.out.println("Remove Rock: mine");
            System.out.println("Use shovel: dig");
            String userInput = sc.next();

            switch (userInput.toLowerCase()) {
                case "land":
                    System.out.println("=-=-=Current Land Info=-=-=");
                    System.out.println("State: " + tileSet.get(0).getState());
                    System.out.println("Watered: " + tileSet.get(0).isHasWater());
                    System.out.println("Has Fertilizer: " + tileSet.get(0).isHasFert());
                    isValidInput = true;
                    break;
                case "plow":
                    System.out.println("=-=-=Trying to Plow Land=-=-=");
                    player.plowTile(tileSet.get(0));
                    isValidInput = true;
                    break;
                case "plant":
                    System.out.println("=-=-=Trying to Plant=-=-=");
                    isValidInput = true;
                    break;
                case "mine":
                    System.out.println("=-=-=Trying to Mine=-=-=");
                    isValidInput = true;
                    break;
                case "dig":
                    System.out.println("=-=-=Trying to Dig=-=-=");
                    isValidInput = true;
                    break;
                default:
                    System.out.println("INVALID INPUT! Please Try Again!");
                    break;
            }
        }while(!isValidInput);
    }
    private static void showActions(Scanner sc)
    {
        boolean isValidInput = false;
        do {
            System.out.println("Day " + currDay + ". What would you like to do?");
            System.out.println("Perform Action -> Land: useLand");
            System.out.println("Check Seeds: seed");
            System.out.println("Check Tools: tool");
            System.out.println("Check Stats: stat");
            System.out.println("Go to Sleep: sleep");
            String userInput = sc.next();

            switch (userInput.toLowerCase()) {
                case "useLand":
                    showLandActions(sc);
                    isValidInput = true;
                    break;
                case "seed":
                    System.out.println("=-=-=Current Seed Info=-=-=");
                    isValidInput = true;
                    break;
                case "tool":
                    System.out.println("=-=-=Current Tool Info=-=-=");
                    isValidInput = true;
                    break;
                case "stat":
                    System.out.println("=-=-=Current Player Info=-=-=");
                    isValidInput = true;
                    break;
                case "sleep":
                    System.out.println("=-=-=Going To Sleep=-=-=");
                    isValidInput = true;
                    break;
                default:
                    System.out.println("INVALID INPUT! Please Try Again!");
                    break;
            }
        }while(!isValidInput);
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

    public static void main(String[] args) {
        initializeFarmerTypes();
        initializeCropTypes();
        initializeTileSet();

        showMainMenu();
    }
}
