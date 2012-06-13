package ship.modules;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class Module1Launcher extends Module1Offensive {
    
    private final Node turretNode;
    private final Node nozzleNode;
    private final Node foundationNode;
    private final Geometry turret;
    private final Geometry nozzle;
    private final Geometry foundation;
    
    private final Vector3f blockOffset = new Vector3f(0,0.6f,0);
    private final Vector3f nozzleOffset = new Vector3f(0, 0.5f, 1);
    
    private String turretType = " ";
    
    //TODO Fix this so its Launcher Specs
    Module1Launcher(AssetManager assetManager, Node Hull, Vector3f turretPos) {
        
        this.setModuleType(1); // Offensive
        this.setTurretType("Laser"); // Laser
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
        turret.setMaterial(mat2);
        
        Box boxNozzle = new Box( nozzleOffset,0.2f,0.2f,0.6f);
        nozzle = new Geometry("nozzle", boxNozzle);
        Material mat3 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color", ColorRGBA.Red);
        nozzle.setMaterial(mat3);
        
        turretNode.attachChild(turret);
        nozzleNode.attachChild(nozzle);
        foundationNode.attachChild(foundation);
        
        turretNode.attachChild(nozzleNode);
        foundationNode.attachChild(turretNode);
        foundationNode.move(turretPos);
        Hull.attachChild(foundationNode);
        
    }

    public String getTurretType() {
        return turretType;
    }

    private void setTurretType(String turretType) {
        this.turretType = turretType;
    }
}
