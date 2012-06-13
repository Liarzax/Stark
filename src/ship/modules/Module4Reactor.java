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

public class Module4Reactor extends Module4Critical {
    
    private final Node reactorNode;
    private final Geometry reactor;
    
    private String reactorType = " ";
    
    public Module4Reactor(AssetManager assetManager, Node shipSection, Vector3f reactorPos) {
        
        this.setModuleType(4); // Critical
        this.setModuleSize(1); // Small Size
        
        this.setArmourBoost(0);
        this.setHealthBoost(0);
        this.setPowerDrain(0);
        this.setPowerGenerate(100);
        this.setWeight(10);
        
        reactorNode = new Node("reactorNode");
        
        Box reactorBox = new Box( reactorPos,0.6f,0.2f,0.4f);
        reactor = new Geometry("reactor", reactorBox);
        Material mat1 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Red);
        reactor.setMaterial(mat1);
        
        reactorNode.attachChild(reactor);
        shipSection.attachChild(reactorNode);
        
    }
    
    public Node getReactorNode() {
        return reactorNode;
    }

    public String getReactorType() {
        return reactorType;
    }
    
    public void setReactorType(String reactorType) {
        this.reactorType = reactorType;
    }
    
    public int generatePower(){
        // 4 is a temp number used for generating power, will eb replaced with power algorithm
        return 4;
    }
    
    
}
