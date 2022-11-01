/** Tiles for interaction for the farming game. Contains the crops and land the player will utilize.
 * @author Aaron Dichoso & Andrei Martin
 * @version 2.1
 * @since 01/11/2022
 */
public class Tile {
    private String state;
    private char stateID;
    private Crop crop;

    /**
     * Initialize the tile as not plowed, not having any tiles (No need for rocks right now)
     */
    public Tile() {
        this.state = "not plowed";
        this.stateID = '_';
        this.crop = null;
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
    public char getStateID() {
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
    public void setStateID(char stateID) {
        /*
         * StateID and State can only have these possible values
         * '_' = "not plowed"
         * 'p' = "plowed"
         * 'c' = "crop
         * 'r' = "rock"
         * */

        //Set state & stateID
        switch (stateID) {
            case '_':
                this.state = "not plowed";
                break;
            case '=':
                this.state = "plowed";
                break;
            case '^':
                this.state = "rocky";
                break;
        }
        this.stateID = stateID;
    }

    /**
     * Check if there is a crop on the tile
     * @return true if there's a crop, false if not
     */
    public boolean hasCrop() {
        return this.crop != null;
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
            this.crop = null; //Set to null if null
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
}
