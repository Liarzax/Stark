package ship.types;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;
import com.jme3.math.Vector3f;
import java.io.IOException;
import ship.sections.ShipHull;
import ship.sections.ShipEngine;
import ship.sections.ShipBridge;
import ship.modules.Module1Turret;
import ship.modules.Module4Reactor;
import com.jme3.scene.Node;
import java.io.Serializable;

/*      
 *      @author Viorel Iliescu      *
                                    */
// TODO This is temp for fiddling with stuff!! before impliment the Types of Shipz as later!
// this should actuly be abstract and based on the hull size the preffered ship type is created 
// which should allso house the controlls of that spacific ship!
// TODO - should this implement saveable! or serializable!?!?!
public class Ship implements Savable {
    int owner               = 0; //<- who owns this ship? maby an enum and have empty 0, player 1, neutral 2,enemy 3,  ?
    int shipID              = 0; // Used incase player has more then 1 ship?
    boolean shipSelected    = false;
    
    private Vector3f shipHeading        = new Vector3f();
    private Node shipNode               = new Node("noShip");
    
    private ShipBridge shipBridge       = null;
    private ShipHull shipHull           = null;
    private ShipEngine shipEngine       = null;
    private Module4Reactor shipReactor  = null;
    private Module1Turret shipTurret    = null;
    
    // testing
    private Module1Turret shipTurret2   = null;
    private Module1Turret shipTurret3   = null;
    
    public Ship() {
        
    }
    
    public Node getShipNode() {
        return shipNode;
    }

    public void setShipNode(Node Ship) {
        this.shipNode = Ship;
    }

    public ShipBridge getShipBridge() {
        return shipBridge;
    }

    public void setShipBridge(ShipBridge shipBridge) {
        this.shipBridge = shipBridge;
    }

    public ShipEngine getShipEngine() {
        return shipEngine;
    }

    public void setShipEngine(ShipEngine shipEngine) {
        this.shipEngine = shipEngine;
    }

    public ShipHull getShipHull() {
        return shipHull;
    }

    public void setShipHull(ShipHull shipHull) {
        this.shipHull = shipHull;
    }

    public Module4Reactor getShipReactor() {
        return shipReactor;
    }

    public void setShipReactor(Module4Reactor shipReactor) {
        this.shipReactor = shipReactor;
    }

    public Module1Turret getShipTurret1() {
        return shipTurret;
    }

    public void setShipTurret1(Module1Turret shipTurret) {
        this.shipTurret = shipTurret;
    }

    public Module1Turret getShipTurret2() {
        return shipTurret2;
    }

    public void setShipTurret2(Module1Turret shipTurret2) {
        this.shipTurret2 = shipTurret2;
    }

    public Module1Turret getShipTurret3() {
        return shipTurret3;
    }

    public void setShipTurret3(Module1Turret shipTurret3) {
        this.shipTurret3 = shipTurret3;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public boolean isShipSelected() {
        return shipSelected;
    }

    public void setShipSelected(boolean shipSelected) {
        this.shipSelected = shipSelected;
    }

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }
    
    
    
    public void clearShipDesign() {
        this.shipNode.detachAllChildren();
        
        // TODO replace the null's with a blank shape or like a shadow shape or something?
        this.shipBridge = null;
        this.shipHull = null;
        this.shipEngine = null;
        this.shipReactor = null;
        this.shipTurret = null;

        // testing
        this.shipTurret2 = null;
        this.shipTurret3 = null;
    }

    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void faceShipAt(Vector3f direction) {
        // TODO: add limits such as rotation speed, turn to rotate? etc or something
        shipNode.lookAt(direction, Vector3f.UNIT_Y);
    }
    
    public void moveShipTo(Vector3f location) {
        // TODO: add other stuff here?
        int y = 0;
        float x = location.x;
        float z = location.z;
        faceShipAt(location);
        shipNode.move(x, y, z);
    }

    public Vector3f getShipHeading() {
        return shipHeading;
    }

    public void setShipHeading(Vector3f shipHeading) {
        this.shipHeading = shipHeading;
    }
    
    
}
