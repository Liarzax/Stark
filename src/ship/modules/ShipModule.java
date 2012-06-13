package ship.modules;

import com.jme3.math.FastMath;

/*      
 *      @author Viorel Iliescu      *
                                    */

public abstract class ShipModule {
    
    // static is belongs to 1 shipModule class of object not each instance independantly
    private static int moduleCount;
    
    // abstract prevents from instantiating shipModule.
    private float weight        = 0;
    private int healthBoost     = 0;
    private int armourBoost     = 0;
    private float powerGenerate = 0;
    private float powerDrain    = 0;
    // moduleType = 0(Nothing), 1(Offensive), 2(Defensive), 3(Support), 4(Critical)
    private int moduleType      = 0;
    // moduleSize = eg Laser's, 1(SmallLaser), 2(MedLaser), 3(PooLaser), 99(HUGELAZER)
    private int moduleSize      = 0;
    
    public int getArmourBoost() {
        return armourBoost;
    }

    protected void setArmourBoost(int armourBoost) {
        this.armourBoost = armourBoost;
    }

    public int getHealthBoost() {
        return healthBoost;
    }

    protected void setHealthBoost(int healthBoost) {
        this.healthBoost = healthBoost;
    }

    public int getModuleType() {
        return moduleType;
    }

    protected void setModuleType(int moduleType) {
        this.moduleType = moduleType;
    }

    public int getModuleSize() {
        return moduleSize;
    }

    protected void setModuleSize(int moduleSize) {
        this.moduleSize = moduleSize;
    }

    public float getPowerDrain() {
        return powerDrain;
    }

    protected void setPowerDrain(float powerDrain) {
        this.powerDrain = powerDrain;
    }

    public float getPowerGenerate() {
        return powerGenerate;
    }

    protected void setPowerGenerate(float powerGenerate) {
        this.powerGenerate = powerGenerate;
    }

    public float getWeight() {
        return weight;
    }

    protected void setWeight(float weight) {
        this.weight = weight;
    }
    
    
    
}
