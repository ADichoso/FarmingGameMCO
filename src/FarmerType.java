public class FarmerType {
    private String name;
    private int levelReq;
    private int bonusEarn;
    private int seedCostReduct;
    private int waterBonusLimInc;
    private int fertBonusLimInc;
    private int regFee;

    public FarmerType(String name, int levelReq, int bonusEarn, int seedCostReduct, int waterBonusLimInc, int fertBonusLimInc, int regFee) {
        this.name = name;
        this.levelReq = levelReq;
        this.bonusEarn = bonusEarn;
        this.seedCostReduct = seedCostReduct;
        this.waterBonusLimInc = waterBonusLimInc;
        this.fertBonusLimInc = fertBonusLimInc;
        this.regFee = regFee;
    }

    public String getName() {
        return name;
    }

    public int getLevelReq() {
        return levelReq;
    }

    public int getBonusEarn() {
        return bonusEarn;
    }

    public int getSeedCostReduct() {
        return seedCostReduct;
    }

    public int getWaterBonusLimInc() {
        return waterBonusLimInc;
    }

    public int getFertBonusLimInc() {
        return fertBonusLimInc;
    }

    public int getRegFee() {
        return regFee;
    }
}
