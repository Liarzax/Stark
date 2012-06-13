package ship.sections;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import projectStark.Entity;

/*      
 *      @author Viorel Iliescu      *
                                    */

public final class ShipEngine extends ShipSection {
    
    private Node engineNode;
    private final Geometry engine1;
    private final Geometry engine2;
    
    
    public ShipEngine(AssetManager assetManager, Node nodeToAttachSectionTo, Entity shipOwner) {
            //AssetManager assetManager, Node Hull, Vector3f enginePos, int engineType) {
        
        this.setSectionType(3);
        engineNode = new Node("engineNode");
        
                                        //  ??,Circlyness,Size,Length,Closed
        Cylinder boxNozzle = new Cylinder( 4, 12, 1.4f, 0.6f, true);
        engine1 = new Geometry("engine1", boxNozzle);
        engine2 = new Geometry("engine2", boxNozzle);
        Material mat1 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Blue);
        engine1.setMaterial(mat1);
        engine2.setMaterial(mat1);
        
        engine1.move(-1.5f, 0.2f, -9.1f);
        engine2.move(1.5f, 0.2f, -9.1f);
        engineNode.attachChild(engine1);
        engineNode.attachChild(engine2);
        
        this.setEngineType(shipOwner.getEngineType());
        nodeToAttachSectionTo.attachChild(engineNode);
    } 
    
    public Node getEngineNode() {
        return engineNode;
    }
    
    protected void setEngineType(int type) {
        switch (type){
            
            case 0:
                setEngineType0();
                break;
                
            case 1:
                setEngineType1();
                break;
                
            default:
                setErrorEngine();
                break;
        }
    }
    
    private void setErrorEngine() {
        this.setSectionName("Hectic Errorz Engine");
        this.setSectionDesc("This Engine is either for Debugging or an Error has occured during Engine creation...");
        
        this.setHealthBoost(999);
        this.setSectionWeight(9);
        
        this.setModuleSlotsOffensive(999);
        this.setModuleSlotsDeffensive(999);
        this.setModuleSlotsCritical(999);
        this.setModuleSlotsSupport(999);
    }    
    
    private void setEngineType0() {
        this.setSectionName("Testing Engine");
        this.setSectionDesc("This is a Debug and/or Testing Engine...");
        
        this.setHealthBoost(10);
        this.setSectionWeight(50);
        
        this.setModuleSlotsOffensive(2);
        this.setModuleSlotsDeffensive(1);
        this.setModuleSlotsCritical(1);
        this.setModuleSlotsSupport(0);
    } 
    
    private void setEngineType1() {
        this.setSectionName("Light Engine");
        this.setSectionDesc("This is a Light Testing Engine...");
        
        this.setHealthBoost(90);
        this.setSectionWeight(50);
        
        this.setModuleSlotsOffensive(2);
        this.setModuleSlotsDeffensive(1);
        this.setModuleSlotsCritical(1);
        this.setModuleSlotsSupport(0);
    }
}
