package projectStark;

import ship.sections.ShipHull;
import ship.sections.ShipEngine;
import ship.sections.ShipBridge;
import ship.modules.Module1Turret;
import ship.modules.Module4Reactor;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class ShipFactory {
    
    private Entity shipOwner        = null;
    private Node nodeToAttachShipTo = null;
    
    ShipFactory(){
        
    }
    
    public void generateShip(AssetManager assetManager, Node nodeToAttachShipTo, Entity entity) {
        this.shipOwner          = entity;
        this.nodeToAttachShipTo = nodeToAttachShipTo;
        
        // Create the Ship Hull 
        generateShipHull(assetManager);
        
        // Attach Peices
        generateShipBridge(assetManager);
        generateShipEngine(assetManager);
        
        //TODO fix the turret stuff
        shipOwner.setShipTurret( new Module1Turret(assetManager, shipOwner.getShip().getShipNode(), new Vector3f(0, 1.8f, 4.6f)));
        shipOwner.setShipTurret2( new Module1Turret(assetManager, shipOwner.getShip().getShipNode(), new Vector3f(0, 3f, 2f)));
        shipOwner.setShipTurret3( new Module1Turret(assetManager, shipOwner.getShip().getShipNode(), new Vector3f(0, 1.8f, -5.4f)));
        // These rotations are in radiants 3.14 is = 180 Degrees
        shipOwner.getShipTurret3().getTurretNode().rotate(0, 3.14f, 0);
        
        // attach ship reactor
        shipOwner.getShip().setShipReactor( new Module4Reactor(assetManager, shipOwner.getShip().getShipHull().getHullNode(), shipOwner.getShip().getShipHull().getReactorPosition()));
        
        // attach Ship to sent in node so it is visible.
        this.nodeToAttachShipTo.attachChild(shipOwner.getShip().getShipNode());
    }
    
    private int generateShipHull (AssetManager assetManager) {
        //TODO generate a hull or if a hull is allready created, bypass this.
        shipOwner.getShip().setShipHull( new ShipHull(assetManager, nodeToAttachShipTo, shipOwner));
        
        return 0;
    }
    
    private int generateShipBridge(AssetManager assetManager) {
        //TODO: based on hulltype, generate equivilent bridge peice.
        // if large hull get large ship peices or something
        // else get small peices, etc...
        shipOwner.getShip().setShipBridge( new ShipBridge(assetManager, nodeToAttachShipTo, shipOwner));
        
        return 0;
    }
    
    private int generateShipEngine(AssetManager assetManager) {
        //TODO: based on hull type, generate engine peice
        shipOwner.getShip().setShipEngine( new ShipEngine(assetManager, nodeToAttachShipTo, shipOwner));
        
        return 0;
    }
}
