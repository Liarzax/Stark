package ship.types;

import ship.sections.ShipHull;
import ship.sections.ShipEngine;
import ship.sections.ShipBridge;
import ship.modules.Module1Turret;
import ship.modules.Module4Reactor;
import com.jme3.scene.Node;

/*      
 *      @author Viorel Iliescu      *
                                    */
// TODO This is temp for fiddling with stuff!! before impliment the Types of Shipz as later!
public class Ship {
    int owner               = 0; //<- who owns this ship? maby an enum and have player, enemy, neutral, empty?
    boolean shipSelected    = false;
    
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
}
