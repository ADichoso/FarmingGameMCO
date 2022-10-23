public class Tile {
    private String state = "not plowed";
    private char stateID = 'n';
    private boolean hasWater = false;
    private boolean hasFert = false;
    private Crop crop;

    public void setStateID(char stateID) {
        /*
         * StateID and State can only have these possible values
         * 'n' = "not plowed"
         * 'p' = "plowed"
         * 'c' = "crop
         * 'r' = "rock"
         * */
        switch(stateID)
        {
            case 'n':
                this.state = "not plowed";
                break;
            case 'p':
                this.state = "plowed";
                break;
            case 'r':
                this.state = "rock";
                break;
            case 'c':
                this.state = "crop";
                break;
        }
        this.stateID = stateID;
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
