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

    private int waterTimes;
    private int fertTimes;
    private int minProduce;
    private int maxProduce;
    private int sellPrice;
    private float expYield;

    public int getWaterTimes() {
        return waterTimes;
    }

    public void setWaterTimes(int waterTimes) {
        if(waterTimes >= 0 && waterTimes <= getWaterLim())
            this.waterTimes = waterTimes;
    }

    public int getFertTimes() {
        return fertTimes;
    }

    public void setFertTimes(int fertTimes) {
        if(fertTimes >= 0 && fertTimes <= getFertLim())
            this.fertTimes = fertTimes;
    }

    public void setAge(int age) {
        if(age >= 0)
            this.age = age;
    }

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

    public boolean isWithered()
    {
        return harvestTime < age || (isMature() && !hasWaterNeeds());
    }

    public boolean isMature()
    {
        return getHarvestTime() == getAge();
    }
    public void growCrop()
    {
        setAge(getAge() + 1);
    }

    public void addWater()
    {
        setWaterTimes(getWaterTimes() + 1);
    }

    public void addFertilizer()
    {
        setFertTimes(getFertTimes() + 1);
    }
    public boolean hasWaterNeeds()
    {
        return getWaterTimes() >= getWaterNeed();
    }

    public boolean hasFertNeeds()
    {
        return getFertTimes() >= getFertNeed();
    }

    public Crop(String name, String type, int cost, int harvestTime, int waterNeed, int fertNeed, int waterLim, int fertLim, int minProduce, int maxProduce, int sellPrice, float expYield) {
        this.age = 0;
        this.waterTimes = 0;
        this.fertTimes = 0;
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
}
