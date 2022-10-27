/** Crop class which defines the attributes that each crop contains
 * @author Aaron Dichoso & Andrei Martin
 * @version 1.1
 * @since 24/10/2022
 */
public class Crop {
    private String name;
    private String type;
    private int cost;
    private int age;
    private int harvestTime;
    private int waterNeed;
    private int fertNeed;
    private int waterLim;
    private int fertLim;
    private int minProduce;
    private int maxProduce;
    private int sellPrice;
    private float expYield;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCost()
    {
        return cost;
    }
    public int getAge() {
        return age;
    }

    public int getHarvestTime() {
        return harvestTime;
    }

    public int getWaterNeed() {
        return waterNeed;
    }

    public int getFertNeed() {
        return fertNeed;
    }

    public int getWaterLim() {
        return waterLim;
    }

    public int getFertLim() {
        return fertLim;
    }

    public int getMinProduce() {
        return minProduce;
    }

    public int getMaxProduce() {
        return maxProduce;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public float getExpYield() {
        return expYield;
    }

    public Crop(String name, String type, int cost, int harvestTime, int waterNeed, int fertNeed, int waterLim, int fertLim, int minProduce, int maxProduce, int sellPrice, float expYield) {
        this.age = 0;
        this.cost = cost;
        this.name = name;
        this.type = type;
        this.harvestTime = harvestTime;
        this.waterNeed = waterNeed;
        this.fertNeed = fertNeed;
        this.waterLim = waterLim;
        this.fertLim = fertLim;
        this.minProduce = minProduce;
        this.maxProduce = maxProduce;
        this.sellPrice = sellPrice;
        this.expYield = expYield;
    }

    @Override
    public String toString()
    {
        return String.format("%-12s|\t%-12s\t|\t%d\t|\t%d Days\t|\t%d-%d Times\t|\t%d-%d Times\t|\tProduces %d-%d\t|\t%d Objectcoins\t|\t%.2f EXP\t|", name, type, cost, harvestTime, waterNeed, waterLim, fertNeed, fertLim, minProduce, maxProduce, sellPrice, expYield);
    }

    public boolean isWithered()
    {
        return harvestTime < age;
    }
}
