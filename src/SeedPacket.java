/** SeedPacket class which contains the seeds that the player uses to plant crops
 * @author Aaron Dichoso & Andrei Martin
 * @version 1.1
 * @since 24/10/2022
 */
public class SeedPacket {
    private int amount;
    private int cost;
    private String cropName;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getCost() {
        return cost;
    }

    public String getCropName() {
        return cropName;
    }

    public SeedPacket(int amount, int cost, String cropName) {
        this.amount = amount;
        this.cost = cost;
        this.cropName = cropName;
    }
}
