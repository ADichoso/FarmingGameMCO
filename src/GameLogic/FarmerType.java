package GameLogic;

import java.util.Objects;

/** GameLogic.FarmerType class which defines the different perks of farmer registration in the game
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class FarmerType {
    private String name;
    private int levelReq;
    private int bonusEarn;
    private int seedCostReduct;
    private int waterBonusLimInc;
    private int fertBonusLimInc;
    private int regFee;


    /**
     * Instantiate a farmerType object given the parameters
     * @param name is the name of the farmerType
     * @param levelReq is the level requirement for the player to register as that farmerType
     * @param bonusEarn is the bonus objectcoins the player earns per produce
     * @param seedCostReduct is the seed cost reduction the player gets for a farmerType
     * @param waterBonusLimInc is the increased amount of times the player can water a crop and get a water bonus
     * @param fertBonusLimInc is the increased amount of times the player can fertilize a crop and get a fertilizer bonus
     * @param regFee is the amount of objectcoins that the player needs to register for a farmerType
     */
    public FarmerType(String name, int levelReq, int bonusEarn, int seedCostReduct, int waterBonusLimInc, int fertBonusLimInc, int regFee) {
        this.name = name;
        this.levelReq = levelReq;
        this.bonusEarn = bonusEarn;
        this.seedCostReduct = seedCostReduct;
        this.waterBonusLimInc = waterBonusLimInc;
        this.fertBonusLimInc = fertBonusLimInc;
        this.regFee = regFee;
    }

    /**
     * Return the name of the GameLogic.FarmerType
     * @return the name of the GameLogic.FarmerType
     */
    public String getName() {
        return name;
    }


    /**
     * Return the Level Requirement of the GameLogic.FarmerType
     * @return the Level Requirement of the GameLogic.FarmerType
     */
    public int getLevelReq() {
        return levelReq;
    }

    /**
     * Return the Bonus Earnings received from the GameLogic.FarmerType
     * @return the Bonus Earnings received from of the GameLogic.FarmerType
     */
    public int getBonusEarn() {
        return bonusEarn;
    }

    /**
     * Return the Seed Cost Reduction of the GameLogic.FarmerType
     * @return the Seed Cost Reduction of the GameLogic.FarmerType
     */
    public int getSeedCostReduct() {
        return seedCostReduct;
    }

    /**
     * Return the Water Limit Bonus Increase of the GameLogic.FarmerType
     * @return the Water Limit Bonus Increase of the GameLogic.FarmerType
     */
    public int getWaterBonusLimInc() {
        return waterBonusLimInc;
    }

    /**
     * Return the Fertilizer Limit Bonus Increase of the GameLogic.FarmerType
     * @return the Fertilizer Limit Bonus Increase of the GameLogic.FarmerType
     */
    public int getFertBonusLimInc() {
        return fertBonusLimInc;
    }

    /**
     * Return the registration fee for the GameLogic.FarmerType
     * @return the v the GameLogic.FarmerType
     */
    public int getRegFee() {
        return regFee;
    }


    /**
     * Equality between two farmerTypes depends SOLELY on NAME
     * @param o is the object to be compared with a given class instance where this method is called
     * @return true if the same farmerType, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FarmerType that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
