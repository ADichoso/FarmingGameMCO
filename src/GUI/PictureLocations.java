package GUI;
/** A File that contains all of the files used as icons and pictures in the application
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class PictureLocations {
    //Crops pictures
    private static final String RESOURCES_DIRECTORY = "Resources/";
    private static final String CROPS_DIRECTORY = RESOURCES_DIRECTORY + "Crops/";
    private static final String TILES_DIRECTORY = RESOURCES_DIRECTORY + "Tiles/";
    private static final String TOOLS_DIRECTORY = RESOURCES_DIRECTORY + "Tools/";

    public static final String WITHERED_ICON_FILE_NAME = CROPS_DIRECTORY + "withered.png";
    public static String getCropIconFileName(String cropName) {return CROPS_DIRECTORY + cropName.toLowerCase() + "_crop.png";}
    public static String getFruitIconFileName(String cropName){return CROPS_DIRECTORY + cropName.toLowerCase() + "_grown.png";}

    //Tiles pictures
    public static final String PLOWED_ICON_NAME = TILES_DIRECTORY + "plowed.png";
    public static final String NOT_PLOWED_ICON_NAME = TILES_DIRECTORY + "not_plowed.png";
    public static final String ROCKY_ICON_NAME = TILES_DIRECTORY + "rocky.png";

    //Tools pictures
    public static final String SHOVEL_ICON_NAME = TOOLS_DIRECTORY + "shovel.png";
    public static final String PICKAXE_ICON_NAME = TOOLS_DIRECTORY + "pickaxe.png";
    public static final String PLOW_ICON_NAME = TOOLS_DIRECTORY + "plow.png";
    public static final String PLANT_ICON_NAME = TOOLS_DIRECTORY + "plant.png";
    public static final String WATER_ICON_NAME = TOOLS_DIRECTORY + "water.png";
    public static final String FERTILIZER_ICON_NAME = TOOLS_DIRECTORY + "fertilizer.png";
    public static final String HARVEST_ICON_NAME = TOOLS_DIRECTORY + "harvest.png";

    //Main title splash screen
    public static final String TITLE_IMAGE_NAME = RESOURCES_DIRECTORY + "gathersun.png";
}
