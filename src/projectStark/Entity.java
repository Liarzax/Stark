package projectStark;

import com.jme3.asset.AssetManager;
import ship.sections.ShipHull;
import ship.sections.ShipEngine;
import ship.sections.ShipBridge;
import ship.modules.Module1Turret;
import ship.modules.Module4Reactor;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import ship.types.Ship;
import ship.types.ShipT2Corvette;

/*      
 *      @author Viorel Iliescu      *
                                    */

//TODO 1 - NEED TO REDO THIS WHOLE CLASS!
public class Entity { //TODO 1 - that controller stuff!! i need to use in here!
    
    // Ship
    private Ship ship = null;
    // Ship Sections
    private int hullCatagory    = 0;
    private int hullType        = 0;
    private int bridgeType      = 0;
    private int engineType      = 0;
    // Ship Modules
    private int reactorType     = 0;
    // Veriables
    private int maxHealth       = 0;
    private int currentHealth   = 0;
    private int maxShieldCap    = 0;
    private int currentShieldS  = 0;
    // Turrets
    private Module1Turret shipTurret    = null;
    private Module1Turret shipTurret2   = null;
    private Module1Turret shipTurret3   = null;
    private Module1Turret shipTurret4   = null;
    private Module1Turret shipTurret5   = null;
    private Module1Turret shipTurret6   = null;
    private Module1Turret shipTurret7   = null;
    
    public Entity() {
        //TODO impliment the size of the ships per hull
        // hullCatagory is the size of the Ship, 
        // Hull Catagory 0 = N/A, 1 = fighter, 2 = small, 3 = med, 4 = large, 5 = huge!
        hullCatagory    = 1;
        hullType        = 0;
        bridgeType      = 0;
        engineType      = 0;
        reactorType     = 0;
        
        //TODO - Get values from the database depending on the hull/section etc, then compile values into 1 value and send into function
        // Testing Values Used
        increaseMaxHealth(100);
        increaseCurrentHealth(100);
        increaseMaxShieldCap(100);
        increaseCurrentShieldS(100);
        
        ship = new Ship();
    }
    
    //TODO: Make player saveable
    
    void entityUpdate() {
     
        // TODO THE CONTROLL teacher taught us, DO IT!
        // UPDATE logical stuff in here?? like... somethign i havent thought of yet?
        // maby stuff like, check shields? or RECHARGE SHIELDS! and like, BASED ON REACTOR START MAKING POWER AGAIN, ETC!!!! AWSOME!
    }
    
    public int getHullCatagory() {
        return hullCatagory;
    }
    
    public int getHullType() {
        return hullType;
    }

    public int getBridgeType() {
        return bridgeType;
    }

    public int getEngineType() {
        return engineType;
    }

    public int getReactorType() {
        return reactorType;
    }

    public void setReactorType(int reactorType) {
        this.reactorType = reactorType;
    }
    
    public int getCurrentHealth() {
        return currentHealth;
    }
    
    public void increaseCurrentHealth(int increaseBy) {
        this.currentHealth += increaseBy;

        if (this.currentHealth >= this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
    }
    
    public void decreaseCurrentHealth(int subtractBy) {
        this.currentHealth -= subtractBy;
        
        if (this.currentHealth <= 0) {
            this.currentHealth = 0;
            //TODO Oh dear, insert the YOUR DEAD stuff here, explosions, etc.
        }
    }    
    
    public int getMaxHealth() {
        return maxHealth;
    }

    public void increaseMaxHealth(int increaseBy) {
        this.maxHealth += increaseBy;
        //TODO increase bar lengh to or something?
    }
    
    public void decreaseMaxHealth(int subtractBy) {
        this.maxHealth -= subtractBy;
        //TODO decrease bar lengh to or something?
    }
    
    public int getCurrentShieldS(){
        return currentShieldS;
    }
    
    public void increaseCurrentShieldS(int increaseBy) {
        this.currentShieldS += increaseBy;
        
        if (this.currentShieldS >= this.maxShieldCap) {
            this.currentShieldS = this.maxShieldCap;
        }
    }
    
    public void decreaseCurrentShieldS(int subtractBy) {
        this.currentShieldS -= subtractBy;
        
        if (this.currentShieldS <= 0) {
            this.currentShieldS = 0;
            //TODO run the shield overload function or something.
        }
    }    
    
    public int getMaxShieldCap() {
        return maxShieldCap;
    }

    public void increaseMaxShieldCap(int increaseBy) {
        this.maxShieldCap += increaseBy;
        //TODO increase bar lengh to or something?
    }
    
    public void decreaseMaxShieldCap(int subtractBy) {
        this.maxShieldCap -= subtractBy;
        //TODO decrease bar lengh to or something?
    }
    
    public Module1Turret getShipTurret() {
        return shipTurret;
    }

    public Module1Turret getShipTurret2() {
        return shipTurret2;
    }

    public Module1Turret getShipTurret3() {
        return shipTurret3;
    }

    public void setBridgeType(int bridgeType) {
        this.bridgeType = bridgeType;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setCurrentShieldS(int currentShieldS) {
        this.currentShieldS = currentShieldS;
    }

    public void setEngineType(int engineType) {
        this.engineType = engineType;
    }

    public void setHullCatagory(int hullCatagory) {
        this.hullCatagory = hullCatagory;
    }

    public void setHullType(int hullType) {
        this.hullType = hullType;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setMaxShieldCap(int maxShieldCap) {
        this.maxShieldCap = maxShieldCap;
    }

    public void setShipTurret(Module1Turret shipTurret) {
        this.shipTurret = shipTurret;
    }

    public void setShipTurret2(Module1Turret shipTurret2) {
        this.shipTurret2 = shipTurret2;
    }

    public void setShipTurret3(Module1Turret shipTurret3) {
        this.shipTurret3 = shipTurret3;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
    
    
    
}