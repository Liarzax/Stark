package ship.sections;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Dome;
import projectStark.Entity;

/*      
 *      @author Viorel Iliescu      *
                                    */

public final class ShipBridge extends ShipSection {
    
    private Node bridgeNode;
    private final Geometry bridge;
    
    public ShipBridge(AssetManager assetManager, Node nodeToAttachSectionTo, Entity shipOwner) {
            //AssetManager assetManager, Node Hull, Vector3f bridgePos, int bridgeType) {
        
        this.setSectionType(1);
        bridgeNode = new Node("bridgeNode");
        
        
        //Box boxBridge = new Box( bridgePos,1.4f,0.6f,2.6f);
        //                          Position, Pointyness, Roundyness, Size
        Dome domeBridge = new Dome(shipOwner.getShip().getShipHull().getBridgePosition(), 8, 8, 2, false);
        Box boxTurretSupport = new Box(shipOwner.getShip().getShipHull().getBridgePosition(), 1.6f, 1.2f, 1.2f);
        Box boxBridgeSupport = new Box(shipOwner.getShip().getShipHull().getBridgePosition(), 2, 2.6f, 2.0f);
        //bridge = new Geometry("bridge", boxBridge);
        bridge = new Geometry("bridge", domeBridge);
        Geometry bridgeSupport = new Geometry("bridgeSupport", boxBridgeSupport);
        Geometry turretSupport = new Geometry("bridgeSupport", boxTurretSupport);
        Material mat1 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Cyan);
        //mat1.setTexture("ColorMap", assetManager.loadTexture("Textures/test4.png"));
        mat2.setColor("Color", ColorRGBA.Gray);
        //mat2.setTexture("ColorMap", assetManager.loadTexture("Textures/thull3.png"));
        bridge.setMaterial(mat1);
        bridgeSupport.setMaterial(mat2);
        turretSupport.setMaterial(mat2);
  
        Node bridgeSupportBlock = new Node();
        Node turretSupportBlock = new Node();
        bridgeSupportBlock.attachChild(bridgeSupport);
        turretSupportBlock.attachChild(turretSupport);
        bridgeNode.attachChild(bridge);
        this.setBridgeType(shipOwner.getBridgeType());
        // Move Bridge Up a Bit
        bridgeNode.move(0f, 2.6f, 0f);
        bridgeSupportBlock.move(0f, 0f, -2.2f);
        turretSupportBlock.move(0f, 0f, 0.8f);
        bridgeSupportBlock.attachChild(bridgeNode);
        shipOwner.getShip().getShipHull().getHullNode().attachChild(turretSupportBlock);
        shipOwner.getShip().getShipHull().getHullNode().attachChild(bridgeSupportBlock);
    }  
    
    public Node getBridgeNode() {
        return bridgeNode;
    }
    
    protected void setBridgeType(int type) {
        switch (type){
            
            case 0:
                setBridgeType0();
            case 1:
                setBridgeType1();
                break;
                
            default:
                setErrorBridge();
                break;
            
        }
    }
    
    private void setErrorBridge() {
        this.setSectionName("Hectic Errorz Bridge");
        this.setSectionDesc("This Bridge is either for Debugging or an Error has occured during Bridge creation...");
        
        this.setHealthBoost(999);
        this.setSectionWeight(9);
        
        this.setModuleSlotsOffensive(999);
        this.setModuleSlotsDeffensive(999);
        this.setModuleSlotsCritical(999);
        this.setModuleSlotsSupport(999);
    }    
    
    private void setBridgeType0() {
        this.setSectionName("Testing Bridge");
        this.setSectionDesc("This is a Testing Brdidge used via Debuging");
        
        this.setHealthBoost(90);
        this.setSectionWeight(50);
        
        this.setModuleSlotsOffensive(2);
        this.setModuleSlotsDeffensive(1);
        this.setModuleSlotsCritical(1);
        this.setModuleSlotsSupport(1);
    }  
    
    private void setBridgeType1() {
        this.setSectionName("Light Bridge");
        this.setSectionDesc("This is a Light Testing Bridge...");
        
        this.setHealthBoost(90);
        this.setSectionWeight(50);
        
        this.setModuleSlotsOffensive(2);
        this.setModuleSlotsDeffensive(1);
        this.setModuleSlotsCritical(1);
        this.setModuleSlotsSupport(1);
    }
}
