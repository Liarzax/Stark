/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectStark;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Viorel Iliescu
 */
public class ShipFactoryTest {
    
    public ShipFactoryTest() {
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
     * Test of generateShip method, of class ShipFactory.
     */
    @Test
    public void testGenerateShip() {
        System.out.println("generateShip");
        AssetManager assetManager = null;
        Node nodeToAttachShipTo = null;
        Entity entity = null;
        ShipFactory instance = new ShipFactory();
        
        // Testing Code Here,
        // TODO: 1 - Crashes on assetManager testing, how to use it here???
        //assetManager
        nodeToAttachShipTo = new Node();
        entity = new Entity(0, 0);
        
        instance.generateShip(assetManager, nodeToAttachShipTo, entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
