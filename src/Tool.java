/** Class used to define the information of the tools that the player uses
 * @author Aaron Dichoso & Andrei Martin
 * @version 1.1
 * @since 24/10/2022
 */
public class Tool {
    private String name;

    public String getName() {
        return name;
    }

    public char getID() {
        return ID;
    }

    public int getUseCost() {
        return useCost;
    }

    public float getExpGain() {
        return expGain;
    }

    private char ID;
    private int useCost;
    private float expGain;

    public Tool(String name, char ID, int useCost, float expGain) {
        this.name = name;
        this.ID = ID;
        this.useCost = useCost;
        this.expGain = expGain;
    }

    @Override
    public String toString()
    {
        return String.format("%-12s|\t%c\t|\t%d per use\t|\t%f exp gained\t|", name, ID, useCost, expGain);
    }
}


