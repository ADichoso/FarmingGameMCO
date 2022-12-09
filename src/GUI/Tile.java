package GUI;

import GameLogic.Crop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** Tiles for interaction for the farming game. Contains the crops and land the player will utilize.
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.4
 * @since 09/12/2022
 */
public class Tile extends JButton{
    public static final int PLOWED = 1;
    public static final int NOT_PLOWED = 2;
    public static final int ROCKY = 3;
    public static final int HAS_CROP = 4;

    private String state;
    private int stateID;
    private Crop crop;

    private int tileID;


    /**
     * Initialize the tile as not plowed and apply its attributes, like its icons and size
     * @param scalingFactor
     * @param tileID
     */
    public Tile(float scalingFactor, int tileID) {
        this.crop = new Crop();
        this.tileID = tileID;

        setStateID(NOT_PLOWED);
        setPreferredSize(new Dimension((int)(getIcon().getIconWidth() * scalingFactor), (int) (getIcon().getIconHeight() * scalingFactor)));
    }

    /**
     * Return the tile ID
     * @return the tile ID
     */
    public int getTileID() {
        return tileID;
    }

    /**
     * Return the current state of the tile
     * @return the current state of the tile
     */
    public String getState() {
        return state;
    }


    /**
     * Return the current state ID of the tile
     * @return the current state ID of the tile
     */
    public int getStateID() {
        return stateID;
    }


    /**
     * Return the current crop on the tile (If available)
     * @return the current crop on the tile
     */
    public Crop getCrop() {
        return crop;
    }

    /**
     * Set the state ID of the tile, as well as change the state according to the given ID.
     * @param stateID is the new state of the tile (If possible)
     */
    public void setStateID(int stateID) {
        /*
         * StateID and State can only have these possible values
         * '_' = "not plowed"
         * 'p' = "plowed"
         * '^' = "rocky"
         * 'c' = "has crop"
         * */

        //Set state & stateID
        //Update the icon of the tile
        switch (stateID) {
            case NOT_PLOWED:
                this.state = "not_plowed";
                break;
            case PLOWED:
                this.state = "plowed";
                break;
            case ROCKY:
                this.state = "rocky";
                break;
            case HAS_CROP:
                this.state = "has crop";
                break;
        }
        this.stateID = stateID;

        updateTileIcon();
    }

    /**
     * Set the crop that is on the tile (new instance of crop object. Same values, different object to avoid interning)
     * @param crop is the crop whose values will be copied on the new object
     */
    public void setCrop(Crop crop) {
        //Create a new crop object using the given crop as a parameter. Simply assigning the crop may cause interning.
        if(crop != null)
            this.crop = new Crop(crop);
        else
            this.crop = new Crop(); //Set to null if null
    }

    /**
     * Check if there is a crop on the tile
     * @return true if there's a crop, false if not
     */
    public boolean hasCrop() {
        return !crop.isNullCrop();
    }

    /**
     * Water the crop, if available
     */
    public void waterCrop()
    {
        if(crop != null) crop.addWater();
    }

    /**
     * Fertilize the crop, if available.
     */
    public void fertilizeCrop()
    {
        if(crop != null) crop.addFertilizer();
    }

    /**
     * Grow the crop if the day has advanced
     */
    public void onAdvanceDay()
    {
        if(hasCrop()) getCrop().growCrop();
        updateTileIcon();
    }

    /**
     * Update the tile's icon depending on its state
     */
    public void updateTileIcon()
    {
        String iconFileName = "";
        switch (getStateID()) {
            case HAS_CROP:
                if(getCrop().isReadyForHarvest())
                {
                    //If crop is harvestable, show the fruit
                    iconFileName = PictureLocations.getFruitIconFileName(getCrop().getName());
                }
                else if (getCrop().isWithered())
                {
                    //If crop is wither, show the withered object
                    iconFileName = PictureLocations.WITHERED_ICON_FILE_NAME;
                }
                else
                {
                    //If crop is not yet harvestable, just show the plant
                    iconFileName = PictureLocations.getCropIconFileName(getCrop().getName());
                }
                break;
            case NOT_PLOWED:
            case PLOWED:
            case ROCKY:
                iconFileName = PictureLocations.getTileIconFileName(getState());
                break;
        }

        try
        {
            setIcon(new ImageIcon(GUISystem.class.getResource(iconFileName))); //Set the icon
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    /**
     * Return the tile's information
     * @return the tile information as an array of strings
     */
    public String[] getTileInfo()
    {
        ArrayList<String> tileInfoList = new ArrayList<String>();
        tileInfoList.add("ID: " + getTileID());
        tileInfoList.add("State: " + getState());
        tileInfoList.add("Has Plant: " + hasCrop());
        if(hasCrop())
        {
            tileInfoList.add("\t>> Current Plant: " + getCrop().getName());
            tileInfoList.add("\t\t> Age: " + getCrop().getAge() + " / " + getCrop().getHarvestTime());
            tileInfoList.add("\t\t> Ready for Harvest: " + getCrop().isReadyForHarvest());
            tileInfoList.add("\t\t> Is Withered: " + getCrop().isWithered());
            tileInfoList.add("\t\t> Times Watered: " + getCrop().getWaterTimes() + " / " + getCrop().getWaterLim());
            tileInfoList.add("\t\t\t- Has Water Needs: " + getCrop().hasWaterNeeds());
            tileInfoList.add("\t\t> Times Fertilized: " + getCrop().getFertTimes() + " / " + getCrop().getFertLim());
            tileInfoList.add("\t\t\t- Has Fertilizer Needs: " + getCrop().hasFertNeeds());
        }

        String[] tileInfo = new String[tileInfoList.size()];

        for(int i = 0; i < tileInfo.length; i++)
            tileInfo[i] = tileInfoList.get(i);


        return tileInfo;
    }
}
