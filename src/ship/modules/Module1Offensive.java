package ship.modules;

/*      
 *      @author Viorel Iliescu      *
                                    */

public abstract class Module1Offensive extends ShipModule {
    
    private float firingRange = 0;
    private float visionRange = 0;

    public float getFiringRange() {
        return firingRange;
    }

    protected void setFiringRange(float firingRange) {
        this.firingRange = firingRange;
    }

    public float getVisionRange() {
        return visionRange;
    }

    protected void setVisionRange(float visionRange) {
        this.visionRange = visionRange;
    }
}
