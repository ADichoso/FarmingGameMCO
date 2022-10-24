/** Tiles for interaction for the farming game. Contains the crops and land the player will utilize.
 * @author Aaron Dichoso & Andrei Martin
 * @version 1.1
 * @since 24/10/2022
 */
public class Tile {
    private String state;
    private char stateID;
    private boolean hasWater;
    private boolean hasFert;
    private Crop crop;

    public Tile() {
        this.state = "not plowed";
        this.stateID = '_';
        this.hasWater = false;
        this.hasFert = false;
        this.crop = null;
    }

    public String getState() {
        return state;
    }

    public char getStateID() {
        return stateID;
    }

    public boolean isHasWater() {
        return hasWater;
    }

    public boolean isHasFert() {
        return hasFert;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setStateID(char stateID) {
        /*
         * StateID and State can only have these possible values
         * '_' = "not plowed"
         * 'p' = "plowed"
         * 'c' = "crop
         * 'r' = "rock"
         * */
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

    public boolean hasCrop() {
        return this.crop != null;
    }
    public void setCrop(Crop crop) {
        this.crop = crop;
        setStateID('c');
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public void setHasFert(boolean hasFert) {
        this.hasFert = hasFert;
    }
}
