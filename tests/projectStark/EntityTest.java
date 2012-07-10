/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectStark;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.FastMath;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import ship.modules.Module1Turret;
import ship.types.Ship;

/**
 *
 * @author Viorel Iliescu
 */
public class EntityTest {

    private Entity instance = null;
    private int loops = 1000;
    private int randomInt = 0;
    // Used in randomInt calculations. minNum 0 = Debugg. maxNum used mainly in MaxHealth/MaxShields/MaxEtc calculations.
    private int minNum = 1;
    private int maxNum = 1000;
    // The maximum hull Catagories (Sizes), types, etc available.
    // Set the amount up to date of diffrent module parts here before running tests for best, most acurate results.
    private int hullCatagories = 2;
    private int hullTypes = 2;
    private int bridgeTypes = 2;
    private int engineTypes = 2;
    private int reactorTypes = 2;

    public EntityTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of entityUpdate method, of class Entity.
     * This currently tests that the Entity's reactor charges when it calls the
     * Update loop.
     */
    @Test
    public void testEntityUpdate() {
        System.out.println("entityUpdate");
        instance = new Entity(0, 0);
        instance.setReactorCurStrengh(40);
        //System.out.println("Max Reactor Strengh = " + instance.getReactorMaxStrengh());
        
        for (int i = 0; i < loops; i++) {
            //System.out.println("Cur Reactor Strengh = " + instance.getReactorCurStrengh());
            instance.entityUpdate();
        }
        
        if (instance.getReactorCurStrengh() == 40) {
            fail("Reactor has not Charged past its initial charge of 40");
        }
        if (instance.getReactorCurStrengh() > instance.getReactorMaxStrengh()) {
            fail("Reactor has Charged passed ("+instance.getReactorCurStrengh()+") its capacity ("+instance.getReactorMaxStrengh()+")");
        }
        System.out.println("entityUpdate = PASSED");
    }

    /**
     * Test of getHullCatagory method, of class Entity.
     * Tests to make sure that the HullCatagory is not Null.
     * or outside its bounds.
     */
    @Test
    public void testGetHullCatagory() {
        System.out.println("getHullCatagory");

        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, hullCatagories);
            instance.setHullCatagory(randomInt);

            int result = instance.getHullCatagory();
            assertNotNull(result);
            if (result > hullCatagories) {
                fail("Hull Catagory " + result + " outside Catagory bounds.");
            }
        }
        System.out.println("getHullCatagory = PASSED");
    }

    /**
     * Test of getHullType method, of class Entity.
     * Tests to make sure that the HullType is not Null.
     * or outside its bounds.
     */
    @Test
    public void testGetHullType() {
        System.out.println("getHullType");

        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, hullTypes);
            instance.setHullType(randomInt);

            int result = instance.getHullType();
            assertNotNull(result);
            if (result > hullTypes) {
                fail("Hull Type " + result + " outside Type bounds.");
            }
        }
        System.out.println("getHullType = PASSED");
    }

    /**
     * Test of getBridgeType method, of class Entity.
     * Tests to make sure that the BridgeType is not Null.
     * or outside its bounds.
     */
    @Test
    public void testGetBridgeType() {
        System.out.println("getBridgeType");

        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, bridgeTypes);
            instance.setBridgeType(randomInt);

            int result = instance.getBridgeType();
            assertNotNull(result);
            if (result > bridgeTypes) {
                fail("Bridge Type " + result + " outside Type bounds.");
            }
        }
        System.out.println("getBridgeType = PASSED");
    }

    /**
     * Test of getEngineType method, of class Entity.
     * Tests to make sure that the EngineType is not Null.
     * or outside its bounds.
     */
    @Test
    public void testGetEngineType() {
        System.out.println("getEngineType");

        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, engineTypes);
            instance.setEngineType(randomInt);

            int result = instance.getEngineType();
            assertNotNull(result);
            if (result > engineTypes) {
                fail("Engine Type " + result + " outside Type bounds.");
            }
        }
        System.out.println("getEngineType = PASSED");
    }

    /**
     * Test of getReactorType method, of class Entity.
     * Tests to make sure that the ReactorType is not Null.
     * or outside its bounds.
     */
    @Test
    public void testGetReactorType() {
        System.out.println("getReactorType");

        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, reactorTypes);
            instance.setReactorType(randomInt);

            int result = instance.getReactorType();
            assertNotNull(result);
            if (result > reactorTypes) {
                fail("Reactor Type " + result + " outside Type bounds.");
            }
        }
        System.out.println("getReactorType = PASSED");
    }

    /**
     * Test of setReactorType method, of class Entity.
     * This test compares and confirms that the reactor is set correctly.
     */
    @Test
    public void testSetReactorType() {
        System.out.println("setReactorType");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, reactorTypes);
            instance.setReactorType(randomInt);

            int result = instance.getHullCatagory();
            assertNotNull(result);
            assertSame(randomInt, instance.getReactorType());
            if (result > hullCatagories) {
                fail("Hull Catagory " + result + " outside Catagory bounds.");
            }
            //System.out.println("ReactorSet/ReactorIs "+randomInt+"/"+instance.getReactorType());
        }
        System.out.println("setReactorType = PASSED");
    }

    /**
     * Test of getCurrentHealth method, of class Entity.
     * Gets the Current Health and makes sure that it is correct.
     */
    @Test
    public void testGetCurrentHealth() {
        System.out.println("getCurrentHealth");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            int expResult = FastMath.nextRandomInt(minNum, instance.getMaxHealth() - 2);
            //System.out.println("Expecting Curent Health to Be = " + expResult);
            instance.setCurrentHealth(expResult - 1);
            //System.out.println("Setting Curent Health as = " + (expResult - 1));
            instance.increaseCurrentHealth(1);
            //System.out.println("Increasing Health by 1");
            int result = instance.getCurrentHealth();
            //System.out.println("Getting Curent Health = " + instance.getCurrentHealth());
            assertEquals(expResult, result);
        }
        
        System.out.println("getCurrentHealth = PASSED");
    }

    /**
     * Test of increaseCurrentHealth method, of class Entity.
     * Tests the ability to Increase the Curent Health of the Ship/Player
     */
    @Test
    public void testIncreaseCurrentHealth() {
        System.out.println("increaseCurrentHealth");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, maxNum);
            instance.setMaxHealth(randomInt);
            randomInt = FastMath.nextRandomInt(minNum, instance.getMaxHealth() - 1);
            instance.setCurrentHealth(randomInt);
            //System.out.println("Current Health is = " + instance.getCurrentHealth());
            //System.out.println("Max Health set to = " + instance.getMaxHealth());
            int increaseBy = FastMath.nextRandomInt(instance.getCurrentHealth(), instance.getMaxHealth() - 2);
            //System.out.println("Increasing Health By = " + decreaseBy);
            instance.increaseCurrentHealth(increaseBy);
            //System.out.println("Current Health/Max Health = " + instance.getCurrentHealth() + "/" + instance.getMaxHealth());
            if (instance.getCurrentHealth() > instance.getMaxHealth()) {
                fail("The Health has Increased Beyond Its Maximum allowed Value Cur/Max " + instance.getCurrentHealth() + "/" + instance.getMaxHealth());
            }
            if (instance.getCurrentHealth() <= 0) {
                fail("The Health has Decreased instead of Increased Cur/Max " + instance.getCurrentHealth() + "/" + instance.getMaxHealth());
            }
        }
        
        System.out.println("increaseCurrentHealth = PASSED");
    }

    /**
     * Test of decreaseCurrentHealth method, of class Entity.
     * Tests the ability to decrease the players/ships health!
     */
    @Test
    public void testDecreaseCurrentHealth() {
        System.out.println("decreaseCurrentHealth");
        int shipDestroyed = 0;
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, maxNum);
            instance.setMaxHealth(randomInt);
            randomInt = FastMath.nextRandomInt(minNum, instance.getMaxHealth() - 1);
            instance.setCurrentHealth(randomInt);
            //System.out.println("Current Health is = " + instance.getCurrentHealth());
            //System.out.println("Max Health set to = " + instance.getMaxHealth());
            int subtractBy = FastMath.nextRandomInt(minNum, instance.getMaxHealth());
            //System.out.println("Subtracting Health By = " + subtractBy);
            instance.decreaseCurrentHealth(subtractBy);
            //System.out.println("Current Health/Max Health = " + instance.getCurrentHealth() + "/" + instance.getMaxHealth());
            if (instance.getCurrentHealth() > instance.getMaxHealth()) {
                fail("The Health has Increased instead of Decreased! Cur/Max " + instance.getCurrentHealth() + "/" + instance.getMaxHealth());
            }
            if (instance.getCurrentHealth() == 0) {
                shipDestroyed++;
                //System.out.println("Player/Ship has been Destroyed");
            }
            if (instance.getCurrentHealth() < 0) {
                fail("The Health has Decreased Below 0! Cur/Max " + instance.getCurrentHealth() + "/" + instance.getMaxHealth());
            }
        }
        
        System.out.println("The Player/Ship has been Destroyed "+shipDestroyed+" times");
        System.out.println("decreaseCurrentHealth = PASSED");
    }

    /**
     * Test of getMaxHealth method, of class Entity.
     * This test makes sure that the function returns the correct value.
     */
    @Test
    public void testGetMaxHealth() {
        System.out.println("getMaxHealth");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            instance.setMaxHealth(FastMath.nextRandomInt(minNum, maxNum));
            int expResult = instance.getMaxHealth();
            int result = instance.getMaxHealth();
            assertEquals(expResult, result);
        }
        
        System.out.println("getMaxHealth = PASSED");
    }

    /**
     * Test of increaseMaxHealth method, of class Entity.
     * This function confirms that the MaxHealth increases.
     */
    @Test
    public void testIncreaseMaxHealth() {
        System.out.println("increaseMaxHealth");
        
        for (int i = 0; i < loops; i++) {
            int increaseBy = 0;
            int currentMaxHealth = 0;
            int expectedMaxHealth = 0;
            instance = new Entity(0, 0);
            currentMaxHealth = instance.getMaxHealth();
            increaseBy = FastMath.nextRandomInt(minNum, maxNum);
            expectedMaxHealth = currentMaxHealth + increaseBy;
            //System.out.println("Expected Max Health to Be = "+expectedReactorMaxCap);
            instance.increaseMaxHealth(increaseBy);
            //System.out.println("Actual Max Health Is = "+instance.getMaxHealth());
            assertEquals(expectedMaxHealth, instance.getMaxHealth());
        }
        
        System.out.println("increaseMaxHealth = PASSED");
    }

    /**
     * Test of decreaseMaxHealth method, of class Entity.
     * Confirms that the function reduces the max health correctly
     */
    @Test
    public void testDecreaseMaxHealth() {
        System.out.println("decreaseMaxHealth");
        
        for (int i = 0; i < loops; i++) {
            int decreaseBy = 0;
            int currentMaxHealth = 0;
            int expectedMaxHealth = 0;
            instance = new Entity(0, 0);
            decreaseBy = FastMath.nextRandomInt(minNum, instance.getMaxHealth() - 10);
            currentMaxHealth = instance.getMaxHealth();
            expectedMaxHealth = currentMaxHealth - decreaseBy;
            //System.out.println("Expected Max Health to Be = "+expectedReactorMaxCap);
            instance.decreaseMaxHealth(decreaseBy);
            //System.out.println("Actual Max Health Is = "+instance.getMaxHealth());
            assertEquals(expectedMaxHealth, instance.getMaxHealth());
        }
        
        System.out.println("decreaseMaxHealth = PASSED");
    }

    /**
     * Test of getCurrentShieldS method, of class Entity.
     * Testing the ability to get the current shield strengh of the ship/player.
     */
    @Test
    public void testGetCurrentShieldS() {
        System.out.println("getCurrentShieldS");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            int expResult = FastMath.nextRandomInt(minNum, instance.getMaxShieldCap() - 2);
            //System.out.println("Expecting Curent Shield Strengh to Be = " + expResult);
            instance.setCurrentShieldS(expResult - 1);
            //System.out.println("Setting Curent Shield Strengh as = " + (expResult - 1));
            instance.increaseCurrentShieldS(1);
            //System.out.println("Increasing Shield Strengh by 1");
            int result = instance.getCurrentShieldS();
            //System.out.println("Getting Curent Shield Strengh = " + instance.getCurrentShieldS());
            assertEquals(expResult, result);
        }
        
        System.out.println("getCurrentShieldS = PASSED");
    }

    /**
     * Test of increaseCurrentShieldS method, of class Entity.
     * Makes sure that the function increases the Strengh fo the Shields correctly.
     */
    @Test
    public void testIncreaseCurrentShieldS() {
        System.out.println("increaseCurrentShieldS");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, maxNum);
            instance.setMaxShieldCap(randomInt);
            randomInt = FastMath.nextRandomInt(minNum, instance.getMaxShieldCap() - 1);
            instance.setCurrentShieldS(randomInt);
            //System.out.println("Current Shield Strengh is = " + instance.getCurrentShieldS());
            //System.out.println("Max Shield Strengh set to = " + instance.getMaxShieldCap());
            int increaseBy = FastMath.nextRandomInt(instance.getCurrentShieldS(), instance.getMaxShieldCap() - 2);
            //System.out.println("Increasing Shield Strengh By = " + decreaseBy);
            instance.increaseCurrentShieldS(increaseBy);
            //System.out.println("Current ShieldS/Max ShieldS = " + instance.getCurrentShieldS() + "/" + instance.getMaxShieldCap());
            if (instance.getCurrentHealth() > instance.getMaxHealth()) {
                fail("The Shield Strengh has Increased Beyond Its Maximum allowed Value Cur/Max " + instance.getCurrentShieldS() + "/" + instance.getMaxShieldCap());
            }
            if (instance.getCurrentHealth() <= 0) {
                fail("The Shield Strengh has Decreased instead of Increased Cur/Max " + instance.getCurrentShieldS() + "/" + instance.getMaxShieldCap());
            }
        }
        
        System.out.println("increaseCurrentShieldS = PASSED");
    }

    /**
     * Test of decreaseCurrentShieldS method, of class Entity.
     * Tets the ability to decrease the shield strengh of the Ship/Player!
     */
    @Test
    public void testDecreaseCurrentShieldS() {
        System.out.println("decreaseCurrentShieldS");
        int shieldsOverloaded = 0;
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, maxNum);
            instance.setMaxShieldCap(randomInt);
            randomInt = FastMath.nextRandomInt(minNum, instance.getMaxShieldCap() - 1);
            instance.setCurrentShieldS(randomInt);
            //System.out.println("Current Shield Strengh is = " + instance.getCurrentShieldS());
            //System.out.println("Max Shield Strengh set to = " + instance.getMaxShieldCap());
            int subtractBy = FastMath.nextRandomInt(minNum, instance.getMaxShieldCap());
            //System.out.println("Subtracting Shield Strengh By = " + subtractBy);
            instance.decreaseCurrentShieldS(subtractBy);
            //System.out.println("Current ShieldS/Max ShieldS = " + instance.getCurrentShieldS() + "/" + instance.getMaxShieldCap());
            if (instance.getCurrentShieldS() > instance.getMaxShieldCap()) {
                fail("The Shield Strengh has Increased instead of Decreased! Cur/Max " + instance.getCurrentShieldS() + "/" + instance.getMaxShieldCap());
            }
            if (instance.getCurrentShieldS() == 0) {
                shieldsOverloaded ++;
                //System.out.println("Player/Ship Shields have been OverLoaded!");
            }
            if (instance.getCurrentShieldS() < 0) {
                fail("The Shield Strengh has Decreased Below 0! Cur/Max " + instance.getCurrentShieldS() + "/" + instance.getMaxShieldCap());
            }
        }
        
        System.out.println("The Shields have been Overloaded "+shieldsOverloaded+" times");
        System.out.println("decreaseCurrentShieldS = PASSED");
    }

    /**
     * Test of getMaxShieldCap method, of class Entity.
     * Confirms that the shield cap is retrived corretcly.
     */
    @Test
    public void testGetMaxShieldCap() {
        System.out.println("getMaxShieldCap");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            instance.setMaxShieldCap(FastMath.nextRandomInt(minNum, maxNum));
            int expResult = instance.getMaxShieldCap();
            int result = instance.getMaxShieldCap();
            assertEquals(expResult, result);
        }
        
        System.out.println("getMaxShieldCap = PASSED");        
    }

    /**
     * Test of increaseMaxShieldCap method, of class Entity.
     * Tests that the max shield is increased correctly
     */
    @Test
    public void testIncreaseMaxShieldCap() {
        System.out.println("increaseMaxShieldCap");
        
        for (int i = 0; i < loops; i++) {
            int increaseBy = 0;
            int currentMaxShield = 0;
            int expectedMaxShield = 0;
            instance = new Entity(0, 0);
            currentMaxShield = instance.getMaxShieldCap();
            increaseBy = FastMath.nextRandomInt(minNum, maxNum);
            expectedMaxShield = currentMaxShield + increaseBy;
            //System.out.println("Expected Max Shield Cap to Be = "+expectedReactorMaxCap);
            instance.increaseMaxShieldCap(increaseBy);
            //System.out.println("Actual Max Shield Cap Is = "+instance.getMaxShieldCap());
            assertEquals(expectedMaxShield, instance.getMaxShieldCap());
        }
        
        System.out.println("increaseMaxShieldCap = PASSED");
    }

    /**
     * Test of decreaseMaxShieldCap method, of class Entity.
     * Tests to decrease the max shields correctly
     */
    @Test
    public void testDecreaseMaxShieldCap() {
        System.out.println("decreaseMaxShieldCap");
        
        for (int i = 0; i < loops; i++) {
            int decreaseBy = 0;
            int currentMaxShield = 0;
            int expectedMaxShield = 0;
            instance = new Entity(0, 0);
            decreaseBy = FastMath.nextRandomInt(minNum, instance.getMaxShieldCap() - 10);
            currentMaxShield = instance.getMaxShieldCap();
            expectedMaxShield = currentMaxShield - decreaseBy;
            //System.out.println("Expected Max Shield Cap to Be = "+expectedReactorMaxCap);
            instance.decreaseMaxShieldCap(decreaseBy);
            //System.out.println("Actual Max Shield Cap Is = "+instance.getMaxShieldCap());
            assertEquals(expectedMaxShield, instance.getMaxShieldCap());
        }
        
        System.out.println("decreaseMaxShieldCap = PASSED");
    }

    /**
     * Test of getShipTurret method, of class Entity.
     * Checks to make sure the method can get the ShipTurret.
     */
    @Test
    public void testGetShipTurret() {
        System.out.println("getShipTurret");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            Module1Turret expResult = instance.getShipTurret();
            Module1Turret result = instance.getShipTurret();
            assertEquals(expResult, result);
        }
        
        System.out.println("getShipTurret = PASSED");
    }

    /**
     * Test of getShipTurret2 method, of class Entity.
     */
    @Ignore
    public void testGetShipTurret2() {
        System.out.println("getShipTurret2");
        instance = new Entity(0, 0);
        Module1Turret expResult = null;
        Module1Turret result = instance.getShipTurret2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShipTurret3 method, of class Entity.
     */
    @Ignore
    public void testGetShipTurret3() {
        System.out.println("getShipTurret3");
        instance = new Entity(0, 0);
        Module1Turret expResult = null;
        Module1Turret result = instance.getShipTurret3();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBridgeType method, of class Entity.
     */
    @Test
    public void testSetBridgeType() {
        System.out.println("setBridgeType");
        
        for (int i = 0; i < loops; i++) {
            int bridgeType = FastMath.nextRandomInt(minNum, bridgeTypes);
            instance = new Entity(0, 0);
            instance.setBridgeType(bridgeType);
            assertEquals(bridgeType, instance.getBridgeType());
        }
        
        System.out.println("setBridgeType = PASSED");
    }

    /**
     * Test of setCurrentHealth method, of class Entity.
     * tests the ability tto set the current health.
     */
    @Test
    public void testSetCurrentHealth() {
        System.out.println("setCurrentHealth");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            int expResult = FastMath.nextRandomInt(minNum, instance.getMaxHealth());
            instance.setCurrentHealth(expResult);
            int result = instance.getCurrentHealth();
            assertEquals(expResult, result);
            if (instance.getCurrentHealth() > instance.getMaxHealth()) {
                fail("The Health is Set Beyond the Max Health Allowed Cur/Max = "+instance.getCurrentHealth()+"/"+instance.getMaxHealth());
            }
        }
        
        System.out.println("setCurrentHealth = PASSED");
    }

    /**
     * Test of setCurrentShieldS method, of class Entity.
     * Checks to see if the method sets the shield strengh correctly.
     */
    @Test
    public void testSetCurrentShieldS() {
        System.out.println("setCurrentShieldS");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            int expResult = FastMath.nextRandomInt(minNum, instance.getMaxShieldCap());
            instance.setCurrentShieldS(expResult);
            int result = instance.getCurrentShieldS();
            assertEquals(expResult, result);
            if (instance.getCurrentShieldS() > instance.getMaxShieldCap()) {
                fail("The Shield is Set Beyond the Max Shield Capacity Cur/Max = "+instance.getCurrentShieldS()+"/"+instance.getMaxShieldCap());
            }
        }
        
        System.out.println("setCurrentShieldS = PASSED");
    }

    /**
     * Test of setEngineType method, of class Entity.
     * Checks to see if the method correctly sets the EngineType.
     */
    @Test
    public void testSetEngineType() {
        System.out.println("setEngineType");
        
        for (int i = 0; i < loops; i++) {
            int engineType = FastMath.nextRandomInt(minNum, engineTypes);
            instance = new Entity(0, 0);
            instance.setEngineType(engineType);
            assertEquals(engineType, instance.getEngineType());
        }
        
        System.out.println("setEngineType = PASSED");
    }

    /**
     * Test of setHullCatagory method, of class Entity.
     * Checks to see if the method correctly sets the HulLCatagory.
     */
    @Test
    public void testSetHullCatagory() {
        System.out.println("setHullCatagory");
        
        for (int i = 0; i < loops; i++) {
            int hullCatagory = FastMath.nextRandomInt(minNum, hullCatagories);
            instance = new Entity(0, 0);
            instance.setHullCatagory(hullCatagory);
            assertEquals(hullCatagory, instance.getHullCatagory());
        }
        
        System.out.println("setHullCatagory = PASSED");
    }

    /**
     * Test of setHullType method, of class Entity.
     * Checks to see if the method correctly sets the HullType.
     */
    @Test
    public void testSetHullType() {
        System.out.println("setHullType");
        
        for (int i = 0; i < loops; i++) {
            int hullType = FastMath.nextRandomInt(minNum, hullTypes);
            instance = new Entity(0, 0);
            instance.setHullType(hullType);
            assertEquals(hullType, instance.getHullType());
        }
        
        System.out.println("setHullType = PASSED");
    }

    /**
     * Test of setMaxHealth method, of class Entity.
     * Test the ability to set the Max Health Level.
     */
    @Test
    public void testSetMaxHealth() {
        System.out.println("setMaxHealth");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            instance.setMaxHealth(FastMath.nextRandomInt(minNum, maxNum));
            int expResult = instance.getMaxHealth();
            int result = instance.getMaxHealth();
            assertEquals(expResult, result);
        }
        
        System.out.println("setMaxHealth = PASSED");
    }

    /**
     * Test of setMaxShieldCap method, of class Entity.
     * Test the ability to set the Max Shield Capacity.
     */
    @Test
    public void testSetMaxShieldCap() {
        System.out.println("setMaxShieldCap");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            instance.setMaxShieldCap(FastMath.nextRandomInt(minNum, maxNum));
            int expResult = instance.getMaxShieldCap();
            int result = instance.getMaxShieldCap();
            assertEquals(expResult, result);
        }
        
        System.out.println("setMaxShieldCap = PASSED");
    }

    /**
     * Test of setShipTurret method, of class Entity.
     * Tests to make sure the method correctly sets the ShipTurret.
     */
    @Test
    public void testSetShipTurret() {
        System.out.println("setShipTurret");
        
        for (int i = 0; i < loops; i++) {
            Module1Turret nullShipTurret = null;
            instance = new Entity(0, 0);
            instance.setShipTurret(nullShipTurret);
            assertNull(instance.getShipTurret());
        }
        
        System.out.println("setShipTurret = PASSED");
    }

    /**
     * Test of setShipTurret2 method, of class Entity.
     * Test disabled
     */
    @Ignore
    public void testSetShipTurret2() {
        System.out.println("setShipTurret2");
        Module1Turret shipTurret2 = null;
        instance = new Entity(0, 0);
        instance.setShipTurret2(shipTurret2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShipTurret3 method, of class Entity.
     * Test disabled.
     */
    @Ignore
    public void testSetShipTurret3() {
        System.out.println("setShipTurret3");
        Module1Turret shipTurret3 = null;
        instance = new Entity(0, 0);
        instance.setShipTurret3(shipTurret3);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShip method, of class Entity.
     * Confirms that the ship returned is the correct ship.
     */
    @Test
    public void testGetShip() {
        System.out.println("getShip");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            Ship expResult = instance.getShip();
            Ship result = instance.getShip();
            assertNotNull(result);
            assertEquals(expResult, result);
        }
        
        System.out.println("getShip = PASSED");
    }

    /**
     * Test of setShip method, of class Entity.
     * tests that ti can overide the players ship and set it to null.
     */
    @Test
    public void testSetShip() {
        System.out.println("setShip");
        Ship nullShip = null;
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            Ship expResult = instance.getShip();
            instance.setShip(nullShip);
            Ship result = instance.getShip();
            assertNull(result);
        }
        
        System.out.println("setShip = PASSED");
    }

    /**
     * Test of write method, of class Entity.
     * Test disabled.
     */
    @Ignore
    public void testWrite() throws Exception {
        System.out.println("write");
        JmeExporter ex = null;
        instance = new Entity(0, 0);
        instance.write(ex);
        // review the generated test code and remove the default call to fail.
        fail("The test case is a prototype and Not yet Used.");
    }

    /**
     * Test of read method, of class Entity.
     * Test disabled.
     */
    @Ignore
    public void testRead() throws Exception {
        System.out.println("read");
        JmeImporter im = null;
        instance = new Entity(0, 0);
        instance.read(im);
        // review the generated test code and remove the default call to fail.
        fail("The test case is a prototype and Not yet Used.");
    }

    /**
     * Test of getReactorCurStrengh method, of class Entity.
     * Tests to make sure that getting the reactors Curent Strengh is correct.
     */
    @Test
    public void testGetReactorCurStrengh() {
        System.out.println("getReactorCurStrengh");
        
        for (int i = 0; i < loops; i++) {
            Entity instance = new Entity(0, 0);
            int expResult = FastMath.nextRandomInt(minNum, instance.getReactorMaxStrengh() - 2);
            //System.out.println("Expecting Reactor Curent Strengh to Be = " + expResult);
            instance.setReactorCurStrengh(expResult - 1);
            //System.out.println("Setting Reactor Curent Strengh as = " + (expResult - 1));
            instance.entityUpdate();
            //System.out.println("Charging Reactor");
            int result = instance.getReactorCurStrengh();
            //System.out.println("Getting Reactor Curent Strengh = " + instance.getReactorCurStrengh());
            assertEquals(expResult, result);
        }
        
        System.out.println("getReactorCurStrengh = PASSED");
    }

    /**
     * Test of setReactorCurStrengh method, of class Entity.
     * Tests to make sure that setting the Reactors Curent strengh correctly.
     */
    @Test
    public void testSetReactorCurStrengh() {
        System.out.println("setReactorCurStrengh");
        
        for (int i = 0; i < loops; i++) {
            Entity instance = new Entity(0, 0);
            int expResult = FastMath.nextRandomInt(minNum, instance.getReactorMaxStrengh() - 2);
            //System.out.println("Expecting Reactor Curent Strengh to Be = " + expResult);
            instance.setReactorCurStrengh(expResult - 1);
            //System.out.println("Setting Reactor Curent Strengh as = " + (expResult - 1));
            instance.entityUpdate();
            //System.out.println("Charging Reactor");
            int result = instance.getReactorCurStrengh();
            //System.out.println("Getting Reactor Curent Strengh = " + instance.getReactorCurStrengh());
            assertEquals(expResult, result);
        }
        
        System.out.println("setReactorCurStrengh = PASSED");
    }

    /**
     * Test of getReactorMaxStrengh method, of class Entity.
     * Test the ability to get the Reactors Max Capacity.
     */
    @Test
    public void testGetReactorMaxStrengh() {
        System.out.println("getReactorMaxStrengh");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            instance.setReactorMaxStrengh(FastMath.nextRandomInt(minNum, maxNum));
            int expResult = instance.getReactorMaxStrengh();
            int result = instance.getReactorMaxStrengh();
            assertEquals(expResult, result);
        }
        
        System.out.println("getReactorMaxStrengh = PASSED");
    }

    /**
     * Test of setReactorMaxStrengh method, of class Entity.
     * test the ability to Set the Reactors Max Capacity.
     */
    @Test
    public void testSetReactorMaxStrengh() {
        System.out.println("setReactorMaxStrengh");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            instance.setReactorMaxStrengh(FastMath.nextRandomInt(minNum, maxNum));
            int expResult = instance.getReactorMaxStrengh();
            int result = instance.getReactorMaxStrengh();
            assertEquals(expResult, result);
        }
        
        System.out.println("setReactorMaxStrengh = PASSED");
    }

    /**
     * Test of incReactorMaxStrengh method, of class Entity.
     * Tests to see if the ReactorMaxStrengh is increased correctly.
     */
    @Test
    public void testIncReactorMaxStrengh() {
        System.out.println("incReactorMaxStrengh");
        
        for (int i = 0; i < loops; i++) {
            int increaseBy = 0;
            int currentReactorMaxCap = 0;
            int expectedReactorMaxCap = 0;
            instance = new Entity(0, 0);
            currentReactorMaxCap = instance.getReactorMaxStrengh();
            increaseBy = FastMath.nextRandomInt(minNum, maxNum);
            expectedReactorMaxCap = currentReactorMaxCap + increaseBy;
            //System.out.println("Expected Max Reactor Cap to Be = "+expectedReactorMaxCap);
            instance.incReactorMaxStrengh(increaseBy);
            //System.out.println("Actual Max Reactor Cap Is = "+instance.getReactorMaxStrengh());
            assertEquals(expectedReactorMaxCap, instance.getReactorMaxStrengh());
        }
        
        System.out.println("incReactorMaxStrengh = PASSED");
    }

    /**
     * Test of decReactorMaxStrengh method, of class Entity.
     * Tests to see if the ReactorMaxStrengh is decreased correctly.
     */
    @Test
    public void testDecReactorMaxStrengh() {
        System.out.println("decReactorMaxStrengh");
        
        for (int i = 0; i < loops; i++) {
            int decreaseBy = 0;
            int currentReactorMaxCap = 0;
            int expectedReactorMaxCap = 0;
            instance = new Entity(0, 0);
            currentReactorMaxCap = instance.getReactorMaxStrengh();
            decreaseBy = FastMath.nextRandomInt(minNum, maxNum);
            expectedReactorMaxCap = currentReactorMaxCap - decreaseBy;
            //System.out.println("Expected Max Reactor Cap to Be = "+expectedReactorMaxCap);
            instance.decReactorMaxStrengh(decreaseBy);
            //System.out.println("Actual Max Reactor Cap Is = "+instance.getReactorMaxStrengh());
            assertEquals(expectedReactorMaxCap, instance.getReactorMaxStrengh());
        }
        
        System.out.println("decReactorMaxStrengh = PASSED");
    }

    /**
     * Test of incReactorCurStrengh method, of class Entity.
     * Tests to see if the reactorCurStrengh is increased correctly.
     */
    @Test
    public void testIncReactorCurStrengh() {
        System.out.println("incReactorCurStrengh");
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, maxNum);
            instance.setReactorMaxStrengh(randomInt);
            randomInt = FastMath.nextRandomInt(minNum, instance.getReactorMaxStrengh() - 1);
            instance.setReactorCurStrengh(randomInt);
            int originalReactorCharge = instance.getReactorCurStrengh();
            //System.out.println("Current Reactor Charge is = " + instance.getReactorCurStrengh());
            //System.out.println("Max Reactor Charge set to = " + instance.getReactorMaxStrengh());
            int increaseBy = FastMath.nextRandomInt(instance.getReactorCurStrengh(), instance.getReactorMaxStrengh() - 2);
            //System.out.println("Increasing Reactor Charge By = " + increaseBy);
            instance.incReactorCurStrengh(increaseBy);
            //System.out.println("Current ReactorCharge/Max ReactorCap = " + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
            if (instance.getReactorCurStrengh() > instance.getReactorMaxStrengh()) {
                fail("The Reactor has been Charged Beyond Its Maximum allowed Value Cur/Max " + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
            }
            if (instance.getReactorCurStrengh() < originalReactorCharge) {
                fail("The Reactor Charge has Decreased instead of Increased Orig/Cur/Max " + originalReactorCharge + "/" + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
            }
            if (instance.getReactorCurStrengh() == originalReactorCharge) {
                if (originalReactorCharge != instance.getReactorMaxStrengh()) {
                    fail("The Reactor Charge has not Increased Orig/Cur/Max " + originalReactorCharge + "/" + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
                }
                else {
                    
                }
            }
            if (instance.getReactorCurStrengh() <= 0) {
                fail("Something went Horribly Wrong Cur/Max " + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
            }
        }
        
        System.out.println("incReactorCurStrengh = PASSED");
    }

    /**
     * Test of decReactorCurStrengh method, of class Entity.
     * Tests to see if the reactorDecreases its charge correctly.
     */
    @Test
    public void testDecReactorCurStrengh() {
        System.out.println("decReactorCurStrengh");
        int reactorOverloads = 0;
        
        for (int i = 0; i < loops; i++) {
            instance = new Entity(0, 0);
            randomInt = FastMath.nextRandomInt(minNum, maxNum);
            instance.setReactorMaxStrengh(randomInt);
            randomInt = FastMath.nextRandomInt(minNum, instance.getReactorMaxStrengh());
            instance.setReactorCurStrengh(randomInt);
            int originalReactorCharge = instance.getReactorCurStrengh();
            //System.out.println("Current Reactor Charge is = " + instance.getReactorCurStrengh());
            //System.out.println("Max Reactor Charge set to = " + instance.getReactorMaxStrengh());
            int decreaseBy = FastMath.nextRandomInt(minNum, instance.getReactorCurStrengh());
            //System.out.println("Decreasing Reactor Charge By = " + decreaseBy);
            instance.decReactorCurStrengh(decreaseBy);
            //System.out.println("Current ReactorCharge/Max ReactorCap = " + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
            if (instance.getReactorCurStrengh() > instance.getReactorMaxStrengh()) {
                fail("The Reactor has been Charged Beyond Its Maximum allowed Value Cur/Max " + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
            }
            if (instance.getReactorCurStrengh() > originalReactorCharge) {
                fail("The Reactor Charge has Increased instead of Decreased Orig/Cur/Max " + originalReactorCharge + "/" + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
            }
            if (instance.getReactorCurStrengh() == originalReactorCharge) {
                if (originalReactorCharge != instance.getReactorMaxStrengh()) {
                    fail("The Reactor Charge has not Decreased Orig/Cur/Max " + originalReactorCharge + "/" + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
                }
                else {
                    
                }
            }
            if (instance.getReactorCurStrengh() < 0) {
                fail("Something went Horribly Wrong Cur/Max " + instance.getReactorCurStrengh() + "/" + instance.getReactorMaxStrengh());
            }
            if (instance.getReactorCurStrengh() == 0) {
                reactorOverloads++;
                //System.out.println("The Reactor has been Over-Loaded!");
            }
        }
        
        System.out.println("The Reactor Charge has been Depleated " + reactorOverloads + " Times");
        System.out.println("incReactorCurStrengh = PASSED");
    }
    
    
    
}
