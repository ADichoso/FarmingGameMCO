package GameLogic;

/** GameLogic.Crop class which defines the attributes that each crop contains
 * @author Aaron Dichoso & Andrei Martin
 * @version 1.1
 * @since 24/10/2022
 */
public class Crop {
    private static final String WITHERED_ICON_FILE_NAME = "Resources/Crops/withered.png";
    public static final String ROOT_CROP_TYPE = "Root Crop";
    public static final String FLOWER_CROP_TYPE = "Flower";
    public static final String FRUIT_TREE_CROP_TYPE = "Fruit Tree";

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

    /**
     * Initialize a crop type given the paramaters
     * @param name is the name of the crop
     * @param type is the type of the crop
     * @param cost is the cost to buy a seed of the crop to plant
     * @param harvestTime is the number of days needed to pass from the planting of the crop for it to be harvestable
     * @param waterNeed is the number of times the crop needs to be watered before it can be harvestable
     * @param fertNeed is the number of times the crop needs to be fertilized before it can be harvestable
     * @param waterLim is the maximum number of times the crop can be watered for it to have an effect
     * @param fertLim is the maximum number of times the crop can be fertilized for it to have an effect
     * @param minProduce is the minimum yield number a crop can produce
     * @param maxProduce is the maximum yield number a crop can produce
     * @param sellPrice is the selling price of each unit the crop produces
     * @param expYield is the EXP yield a crop yields upon harvesting it
     */
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

        System.out.println("Crop with name " + this.name + " is created.");
        System.out.println("REMINDER: will require an associated picture file of " + this.name + ".png to be created and placed inside GUI/Crops folder");
    }

    /**
     * Instantiate a crop given the parameters of another existing crop
     * @param crop is the crop whose parameters will be copied
     */
    public Crop(Crop crop)
    {
        this(
                crop.getName(),
                crop.getType(),
                crop.getCost(),
                crop.getHarvestTime(),
                crop.getWaterNeed(),
                crop.getFertNeed(),
                crop.getWaterLim(),
                crop.getFertLim(),
                crop.getMinProduce(),
                crop.getMaxProduce(),
                crop.getSellPrice(),
                crop.getExpYield()
        );
    }

    public Crop()
    {
        this(
                "null",
                "null",
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0
        );
    }

    /**
     * Return the amount of times a crop has been watered
     * @return the amount of times a crop has been watered
     */
    public int getWaterTimes() {
        return waterTimes;
    }

    /**
     * Return the amount of times a crop has been fertilized
     * @return the amount of times a crop has been fertilized
     */
    public int getFertTimes() {
        return fertTimes;
    }

    /**
     * Return the name of the GameLogic.Crop
     * @return the name of the GameLogic.Crop
     */
    public String getName() {
        return name;
    }

    /**
     * Return the type of the GameLogic.Crop
     * @return the type of the GameLogic.Crop
     */
    public String getType() {
        return type;
    }

    /**
     * Return the cost of planting the GameLogic.Crop
     * @return the cost of planting the GameLogic.Crop
     */
    public int getCost()
    {
        return cost;
    }

    /**
     * Return the age of the GameLogic.Crop
     * @return the age of the GameLogic.Crop
     */
    public int getAge() {
        return age;
    }

    /**
     * Return the harvest time of the GameLogic.Crop
     * @return the harvest time of the GameLogic.Crop
     */
    public int getHarvestTime() {
        return harvestTime;
    }

    /**
     * Return the water requirement of the GameLogic.Crop
     * @return the water requirement of the GameLogic.Crop
     */
    public int getWaterNeed() {
        return waterNeed;
    }

    /**
     * Return the fertilizer requirement of the GameLogic.Crop
     * @return the fertilizer requirement of the GameLogic.Crop
     */
    public int getFertNeed() {
        return fertNeed;
    }

    /**
     * Return the water limit of the GameLogic.Crop
     * @return the water limit of the GameLogic.Crop
     */
    public int getWaterLim() {
        return waterLim;
    }

    /**
     * Return the fertilizer limit of the GameLogic.Crop
     * @return the fertilizer limit of the GameLogic.Crop
     */
    public int getFertLim() {
        return fertLim;
    }

    /**
     * Return the minimum possible yield of the GameLogic.Crop
     * @return the minimum possible yield of the GameLogic.Crop
     */
    public int getMinProduce() {
        return minProduce;
    }

    /**
     * Return the maximum possible yield of the GameLogic.Crop
     * @return the maximum possible yield of the GameLogic.Crop
     */
    public int getMaxProduce() {
        return maxProduce;
    }

    /**
     * Return the unit selling price of the GameLogic.Crop
     * @return the unit selling price of the GameLogic.Crop
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * Return the exp yield of the GameLogic.Crop
     * @return the exp yield of the GameLogic.Crop
     */
    public float getExpYield() {
        return expYield;
    }

    /**
     * Set the amount of times the crop has been watered (Cannot be lower than 0 & greater than the water limit)
     * @param waterTimes is the new amount of times the crop has been watered
     */
    public void setWaterTimes(int waterTimes) {
        if(waterTimes >= 0 && waterTimes <= getWaterLim())
            this.waterTimes = waterTimes;
    }

    /**
     * Set the amount of times the crop has been fertilized (Cannot be lower than 0 & greater than the fertilizer limit)
     * @param fertTimes is the new amount of times the crop has been fertilized
     */
    public void setFertTimes(int fertTimes) {
        if(fertTimes >= 0 && fertTimes <= getFertLim())
            this.fertTimes = fertTimes;
    }

    /**
     * Set the age of the crop (Cannot be lower than 0)
     * @param age is the new age of the crop
     */
    public void setAge(int age) {
        if(age >= 0)
            this.age = age;
    }

    /**
     * Check if the crop has already withered (Water needs not met by maturity OR left for a day after its harvestTime)
     * @return true is withered, false if not
     */
    public boolean isWithered()
    {
        return harvestTime < age || (isMature() && !hasWaterNeeds());
    }

    /**
     * Check if the crop is ready for harvest (GameLogic.Crop has matured and has met the water needs)
     * @return true if ready for harvest, false if not
     */
    public boolean isReadyForHarvest(){ return isMature() && hasWaterNeeds();}

    /**
     * Check if the crop is matured (HarvestTime is equal to its age)
     * @return true if mature, false if not
     */
    public boolean isMature()
    {
        return getHarvestTime() == getAge();
    }

    /**
     * GameLogic.Crop grows by incrementing age by 1
     */
    public void growCrop()
    {
        setAge(getAge() + 1);
    }

    /**
     * Add water by incrementing the number of times the crop has been watered by 1 if possible
     */
    public void addWater()
    {
        setWaterTimes(getWaterTimes() + 1);
    }

    /**
     * Add fertilizer by incrementing the number of times the crop has been fertilized by 1 if possible
     */
    public void addFertilizer()
    {
        setFertTimes(getFertTimes() + 1);
    }

    /**
     * Check if the water needs of the crop has been met
     * @return true if met, false if not
     */
    public boolean hasWaterNeeds()
    {
        return getWaterTimes() >= getWaterNeed();
    }


    /**
     * Check if the fertilizer needs of the crop has been met
     * @return true if met, false if not
     */
    public boolean hasFertNeeds()
    {
        return getFertTimes() >= getFertNeed();
    }

    public boolean isNullCrop(){return name.equals("null");}

}
