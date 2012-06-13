package ship.modules;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class Module1Turret extends Module1Offensive {
    
    private final Node turretNode;
    private final Node nozzleNode;
    private final Node foundationNode;
    private final Geometry turret;
    private final Geometry nozzle1;
    private final Geometry nozzle2;
    private final Geometry foundation;
    
    private final Vector3f blockOffset = new Vector3f(0,0.6f,0);
    private final Vector3f nozzleOffset = new Vector3f(0, 0.5f, 1);
    
    private String turretType = " ";
    
    
    public Module1Turret(AssetManager assetManager, Node Hull, Vector3f turretPos) {
        
        this.setModuleType(1); // Offensive
        this.setTurretType("Double"); // Cannons attached to turret
        this.setModuleSize(1); // Small Size
        
        this.setArmourBoost(0);
        this.setHealthBoost(0);
        this.setPowerDrain(10);
        this.setPowerGenerate(0);
        this.setWeight(10);
        this.setVisionRange(12);
        this.setFiringRange(this.getVisionRange() / 2);
        
        turretNode = new Node("turretNode");
        nozzleNode = new Node("nozzleNode");
        foundationNode = new Node("foundationNode");
        
        Box boxTurretFoundation = new Box( Vector3f.ZERO,0.8f,0.2f,0.8f);
        foundation = new Geometry("foundation", boxTurretFoundation);
        Material mat1 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Black);
        foundation.setMaterial(mat1);
        
        Box boxTurret = new Box( blockOffset,0.6f,0.4f,0.6f);
        turret = new Geometry("turret", boxTurret);
        Material mat2 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.DarkGray);
        //mat2.setTexture("ColorMap", assetManager.loadTexture("Textures/test7.png"));
        turret.setMaterial(mat2);
        
                                        //  ??,Circlyness,Size,Length,Closed
        Cylinder boxNozzle = new Cylinder( 4, 12, 0.2f, 1.6f, true);
        nozzle1 = new Geometry("nozzle1", boxNozzle);
        nozzle2 = new Geometry("nozzle2", boxNozzle);
        Material mat3 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color", ColorRGBA.Red);
        nozzle1.setMaterial(mat3);
        nozzle2.setMaterial(mat3);
        
        turretNode.attachChild(turret);
        nozzleNode.attachChild(nozzle1);
        nozzle1.move(-0.3f, 0.5f, 1.2f);
        nozzle2.move(0.3f, 0.5f, 1.2f);
        nozzleNode.attachChild(nozzle2);
        foundationNode.attachChild(foundation);
        
        turretNode.attachChild(nozzleNode);
        foundationNode.attachChild(turretNode);
        foundationNode.move(turretPos);
        Hull.attachChild(foundationNode);
        
    }

    public Node getTurretNode() {
        return turretNode;
    }

    public Node getFoundationNode() {
        return foundationNode;
    }

    public Node getNozzleNode() {
        return nozzleNode;
    }

    public String getTurretType() {
        return turretType;
    }

    private void setTurretType(String turretType) {
        this.turretType = turretType;
    }
    
    /*public void rotateTurret(float radiantYaw, float radiantRoll, float radiantPitch){
        getFoundationNode().rotate(radiantYaw, radiantRoll, radiantPitch);
        
    }*/
    
    public void faceTurretAt (Vector3f targetPosition){
        //TODO Polish: Add limiters to rotation hight, etc and stop it from colliding with the ship sections?
        getTurretNode().lookAt(targetPosition, Vector3f.UNIT_Y);
        
    }
    
    
}
