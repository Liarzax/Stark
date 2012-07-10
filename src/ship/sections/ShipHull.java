package ship.sections;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.mySQL.MySQLAccess;
import projectStark.Entity;

/*      
 *      @author Viorel Iliescu      *
                                    */

public final class ShipHull extends ShipSection {
    
    private Geometry hull = null;
    private Node hullNode = new Node("hullNode");
    private Vector3f enginePosition = new Vector3f(0, 0f, -8.8f);
    private Vector3f bridgePosition= new Vector3f(0, 1.6f, 1.2f);
    private Vector3f reactorPosition = new Vector3f(0, 1.8f, -7f);
    
    // Testing
    //TODO have some kind of way to know how many turrets per hull
    private Vector3f turretPosition = new Vector3f(2, 1.8f, 0);
    
    public ShipHull(AssetManager assetManager, Node nodeToAttachSectionTo, Entity shipOwner) {
            //AssetManager assetManager, Node Ship, int hullCatagory, int hullType) {
        
        // Section Type, 1 bridge, 2 hull, 3 engines
        this.setSectionType(2);
        
        // Create the Hull based on the type sent in
        this.assignHullStats(assetManager, nodeToAttachSectionTo, shipOwner.getHullCatagory(), shipOwner.getHullType());
    }
    
    public void setBlankHullType(AssetManager assetManager, Node nodeToAttachSectionTo, Entity shipOwner) {
        this.setSectionType(2);
        this.assignHullStats(assetManager, nodeToAttachSectionTo, 1, 0);
    }
    
    public Node getHullNode() {
        return hullNode;
    }
    
    public void setHullNode(Node hullNode) {
        this.hullNode = hullNode;
    }
    
    public Vector3f getBridgePosition() {
        return bridgePosition;
    }

    public Vector3f getEnginePosition() {
        return enginePosition;
    }

    public Vector3f getTurretPosition() {
        return turretPosition;
    }
    
    public Vector3f getReactorPosition() {
        return reactorPosition;
    }
    
    protected void assignHullStats(AssetManager assetManager, Node Ship, int hullCatagory, int type) {
        //TODO HAve like a hull catagory, Fighter, Destroyer, Carrier, BattleShip, MotherShip, OrbitalStation! then in each, have switch on type
        switch (hullCatagory) {
            // Drone/Fighter Class Ships
            case 1:
                switch (type) {

                    case 0:
                        setCat1Type0Stats(assetManager, Ship);
                        break;

                    case 1:
                        setCat1Type1Stats(assetManager, Ship);
                        break;

                    default:
                        setCat1TypeErrorStats(assetManager, Ship);
                        break;

                }
                break;
                
            // Small/Destroyer Class Ships    
            case 2:
                switch(type){
                    
                }                
                break;
            
            // Medium/Cruiser Class Ships    
            case 3:
                switch(type){
                    
                } 
                break;
                
            // Large/Battleship Class Ships    
            case 4:
                switch(type){
                    
                } 
                break;
                
            default:
                // Default Catagory
                switch(type){
                    // Default Type
                } 
                break;
        }
    }
    
    private void setCat1TypeErrorStats(AssetManager assetManager, Node Ship) {
        this.setSectionName("Hectic Errorz Hull");
        this.setSectionDesc("This hull is either for Debugging or an Error has occured during Hull creation...");
        
        this.setHealthBoost(999);
        this.setSectionWeight(9);
        
        this.setModuleSlotsOffensive(999);
        this.setModuleSlotsDeffensive(999);
        this.setModuleSlotsCritical(1);
        this.setModuleSlotsSupport(999);
        
        Box boxHull = new Box( new Vector3f(0,0,0),2.8f,1.0f,4.4f);
        hull = new Geometry("hull", boxHull);
        Material mat1 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        //mat1.setTexture("ColorMap", assetManager.loadTexture("Textures/test.png"));
        mat1.setColor("Color", ColorRGBA.Red);
        hull.setMaterial(mat1);

        hullNode.attachChild(hull);
        Ship.attachChild(hullNode);
        
    }   
    
    private void setCat1Type0Stats(AssetManager assetManager, Node Ship) {
        this.setSectionName("Testing Hull");
        this.setSectionDesc("This hull is used during Testing & Debugging...");
        
        this.setHealthBoost(100);
        this.setSectionWeight(50);
        
        this.setModuleSlotsOffensive(2);
        this.setModuleSlotsDeffensive(2);
        this.setModuleSlotsCritical(1);
        this.setModuleSlotsSupport(2);
        
        MySQLAccess mySQL = new MySQLAccess();
        try {
            mySQL.connectToDB();
        } catch (Exception ex) {
            Logger.getLogger(ShipHull.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // TODO: make a check here to see if there is a database created, if not create one!
            //mySQL.createTableShipSectionHullInDB();
            // TODO: make it look for the actual hull catagory, then section type, then load stats
            mySQL.readHullFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(ShipHull.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // **** TESTING HULL POWERSS!!!!! *****
        //                                        Hull Dimentions need to be specified, does not like a veriable
        Box boxHull = new Box( new Vector3f(0,0,0),2.8f,1.6f,8.8f);
        hull = new Geometry("hull", boxHull);
        Material mat1 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        //mat1.setTexture("ColorMap", assetManager.loadTexture("Textures/stest1.png"));
        mat1.setColor("Color", ColorRGBA.DarkGray);
        hull.setMaterial(mat1);

        hullNode.attachChild(hull);
        Ship.attachChild(hullNode);
    }   
    
    private void setCat1Type1Stats(AssetManager assetManager, Node Ship) {
        this.setSectionName("Light Hull");
        this.setSectionDesc("This is a Light Testing hull...");
        
        this.setHealthBoost(90);
        this.setSectionWeight(50);
        
        this.setModuleSlotsOffensive(2);
        this.setModuleSlotsDeffensive(1);
        this.setModuleSlotsCritical(1);
        this.setModuleSlotsSupport(1);
        
        Box boxHull = new Box( new Vector3f(0,0,0),2.8f,1.0f,4.4f);
        hull = new Geometry("hull", boxHull);
        Material mat1 = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        //mat1.setTexture("ColorMap", assetManager.loadTexture("Textures/test.png"));
        mat1.setColor("Color", ColorRGBA.Gray);
        hull.setMaterial(mat1);

        hullNode.attachChild(hull);
        Ship.attachChild(hullNode);
    }
}
