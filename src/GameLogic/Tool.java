package GameLogic;

/** Class used to define the information of the tools that the player uses
 * @author Aaron Dichoso & Andrei Martin
 * @version 2.1
 * @since 01/11/2022
 */
public class Tool {
    private String name;
    private char ID;
    private int useCost;
    private float expGain;


    /**
     * Initializes a tool object with given parameters
     * @param name is the tool of the tool
     * @param ID is the ID of the tool
     * @param useCost is the cost of usage of the tool
     * @param expGain is the amount of EXP the player gains upon using the tool
     */
    public Tool(String name, char ID, int useCost, float expGain) {
        this.name = name;
        this.ID = ID;
        this.useCost = useCost;
        this.expGain = expGain;
    }

    /**
     * Return the name of the tool
     * @return the name of the tool
     */
    public String getName() {
        return name;
    }


    /**
     * Return the ID of the tool
     * @return the ID of the tool
     */
    public char getID() {
        return ID;
    }


    /**
     * Return the use cost of the tool
     * @return the use cost of the tool
     */
    public int getUseCost() {
        return useCost;
    }


    /**
     * Return the EXP gained upon using the tool
     * @return the EXP gained upon using the tool
     */
    public float getExpGain() {
        return expGain;
    }
}


