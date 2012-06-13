package ship.sections;

/*      
 *      @author Viorel Iliescu      *
                                    */

public abstract class ShipSection {
    
    //parameters will normaly be sent in from the database based on ship specifications in the actual ship section itself
    private int sectionType             = 0;
    private String sectionName          = " ";
    private String sectionDesc          = " ";
    private int moduleSlotsOffensive    = 0;
    private int moduleSlotsDeffensive   = 0;
    private int moduleSlotsSupport      = 0;
    private int moduleSlotsCritical     = 0;
    private int moduleSlots             = 0;
    private float sectionWeight         = 0;
    private int healthBoost             = 0;
    private int armourBoost             = 0;

    public String getSectionDesc() {
        return sectionDesc;
    }

    public int getModuleSlotsDeffensive() {
        return moduleSlotsDeffensive;
    }

    protected void setModuleSlotsDeffensive(int moduleSlotsDeffensive) {
        this.moduleSlotsDeffensive = moduleSlotsDeffensive;
    }
    
    public int getModuleSlotsSupport() {
        return moduleSlotsSupport;
    }

    protected void setModuleSlotsSupport(int moduleSlotsSupport) {
        this.moduleSlotsSupport = moduleSlotsSupport;
    }    

    public int getModuleSlotsCritical() {
        return moduleSlotsCritical;
    }

    protected void setModuleSlotsCritical(int moduleSlotsCritical) {
        this.moduleSlotsCritical = moduleSlotsCritical;
    }

    public int getModuleSlotsOffensive() {
        return moduleSlotsOffensive;
    }

    protected void setModuleSlotsOffensive(int moduleSlotsOffensive) {
        this.moduleSlotsOffensive = moduleSlotsOffensive;
    }

    protected void setSectionDesc(String sectionDesc) {
        this.sectionDesc = sectionDesc;
    }

    public String getSectionName() {
        return sectionName;
    }

    protected void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getSectionType() {
        return sectionType;
    }

    protected void setSectionType(int sectionType) {
        this.sectionType = sectionType;
    }

    public int getHealthBoost() {
        return healthBoost;
    }

    protected void setHealthBoost(int healthBoost) {
        this.healthBoost = healthBoost;
    }

    public int getModuleSlots() {
        moduleSlots = 0;
        moduleSlots += this.getModuleSlotsOffensive();
        moduleSlots += this.getModuleSlotsDeffensive();
        moduleSlots += this.getModuleSlotsSupport();
        moduleSlots += this.getModuleSlotsCritical();
        return moduleSlots;
    }

    public float getSectionWeight() {
        return sectionWeight;
    }

    protected void setSectionWeight(float sectionWeight) {
        this.sectionWeight = sectionWeight;
    }

    public int getArmourBoost() {
        return armourBoost;
    }

    public void setArmourBoost(int armourBoost) {
        this.armourBoost = armourBoost;
    }
    
    
}
