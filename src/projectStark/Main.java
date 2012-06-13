package projectStark;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.network.Client;
import com.jme3.network.Server;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import com.jme3.effect.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.mySQL.MySQLAccess;


/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication { 
    
    //TODO Clean Up
    private Entity player;
    private Entity enemy;  
    CapsuleCollisionShape capsuleShape;
    CharacterControl shipPhys;
    private BulletAppState bulletAppState;    
    //private Node enemyShipNode;  // TESTING ENEMY
    private ChaseCamera chaseCam;
    private boolean thrusting = false;
    private boolean left = false, right = false, up = false, down = false;
    
    //World Veriables
    private Node selectablesNode = new Node("Selectables");
    private Node playerNode = new Node("Player");
    private Node friendliesNode = new Node("Friendlies");
    private Node enemiesNode = new Node("Enemies");
    
    // Sub-Systems
    NiftyJmeDisplay niftyDisplay;
    Nifty nifty;
    // Server Stuff!!
    Server myServer = null;
    Client myClient = null;
    
    //CollisionResults results = new CollisionResults();
    private CollisionResults lastResults;
    private boolean haveTarget = false;
    Geometry mark;
    // TESTING!!!
    Vector3f shipHeading = new Vector3f();
    Vector3f enemyHeading = new Vector3f();
    
    public static void main(String[] args) {
        Main app = new Main();
        AppSettings s = new AppSettings(true);
        s.setFrameRate(100);    // set framerate to a constant value
        app.setSettings(s);
        app.start();            // update, draw, update, draw, update, draw, update, etc...
    }
    
    @Override
    public void simpleInitApp() {
        
        //TODO impliment or build an RTS cam like this Cam some time?
        /*final RtsCam rtsCam = new RtsCam(cam, rootNode);
        rtsCam.registerWithInput(inputManager);
        rtsCam.setCenter(new Vector3f(20,0.5f,20));*/
        //possibly detaching default fly cam with inputManager.removeListener(flyCam);
        
        // #### NIFTY GUI testing stuff
        //TODO omg! ship should be made of blocks, as blocks get blown off, 
        //hp goes down, so ship is actuly blown to peices when it has 0 health!!!
        
        // CURSOR STUFF?
        // registerMouseCursor("myPointer", "interface/pointer.png", 5, 4);
        // defining screens, main, HUD, shipCustomise/create, Gameplay, etc...
        
        //#NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        nifty = niftyDisplay.getNifty();
        /** Read your XML and initialize your custom ScreenController */
        //nifty.fromXml("Interface/screenHUD.xml", "screenHUD", new ScreenStart("screenHUD", this));
        
        //nifty.fromXml(String filename, String startScreen,ScreenController controllers);
        // nifty.addXml(String filename);
        //nifty.fromXml("Interface/screenMultiplayer.xml", "screenMultiplayer", new ScreenMultiplayer("screenMultiplayer"));
        
        nifty.fromXml("Interface/screenHUD.xml", "screenHUD", new ScreenHUD("screenHUD"));
        nifty.fromXml("Interface/screenMultiplayer.xml", "screenMultiplayer", new ScreenMultiplayer("screenMultiplayer"));
        nifty.fromXml("Interface/screenSelectProfile.xml", "screenSelectProfile", new ScreenSelectProfile("screenSelectProfile"));
        nifty.fromXml("Interface/screenStart.xml", "screenStart", new ScreenStart("screenStart", this));
        //nifty.fromXml("Interface/LOADING.xml", "start", new ScreenStart("start", this));
        
        guiViewPort.addProcessor(niftyDisplay);
        
        // TODO use a loading screen to do this stuff
        
        // #### Set Controls ####
        //PlayerControls PCcontrols = new PlayerControls();
        //PCcontrols.InitializeControls();
        initializeControls();
        
        // TESTING STARFIELD!!!
        // Start the StarDust
        // 1500, 300
        // 1000 700
        StarDust starDust = new StarDust("StarDust", 1000, 700f, cam, assetManager);
        rootNode.attachChild(starDust);
        
        // #### TESTING #####
        // DATABASE STUFF
        
        /*MySQLAccess mySQL = new MySQLAccess();
        try {
            mySQL.connectToDB();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            mySQL.readHullFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        //END DATABASE STUFF
        
        
        rootNode.attachChild(selectablesNode);
        selectablesNode.attachChild(playerNode);
        selectablesNode.attachChild(friendliesNode);
        selectablesNode.attachChild(enemiesNode);
        initializeMark();
        
        // TODO: maby add like a load player ship? save ship, etc?
        // Make PlayerShip
        player = new Entity();
        //player.getShip().setShipNode ( new Node("Ship"));
        ShipFactory shipFactory = new ShipFactory();
        shipFactory.generateShip(assetManager, player.getShip().getShipNode(), player);
        //Ship.move(startingLocation);
        playerNode.attachChild(player.getShip().getShipNode());
        
        // PLAYER CONTROLL STUFFF!!!! #######################
        // Physics stuff for SPACE SHIP!
        /*bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        // TODO have CapsuleShape have be the size of the players Ship
        // Create a appropriate physical shape for it or have it sent in from the ship factory?
        capsuleShape = new CapsuleCollisionShape(1.2f, 0.5f, 2);
        shipPhys = new CharacterControl(capsuleShape, 0.01f);
        
        // Attach physical properties to model and PhysicsSpace
        player.getShip().getShipNode().addControl(shipPhys);
        shipPhys.setGravity(0f);
        bulletAppState.getPhysicsSpace().add(shipPhys);*/
        
        // Make a Test Enemy
        enemy = new Entity();
        //TODO: Make enemy ship use ShipFactory and replace PLAYER with link list of enemies or something!
        shipFactory.generateShip(assetManager, enemy.getShip().getShipNode(), enemy);
        enemy.getShip().getShipNode().move(10f, 0f, 10f);
        enemiesNode.attachChild(enemy.getShip().getShipNode());
        
        
        // Create a floor for refrence Full of Stars? or just white stuff... 
        //TODO: Fix ground into stars or something, can wait need for testing
       /* Material spaceMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Geometry space = new Geometry("space", new Quad(50, 50));
        space.setLocalRotation(new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X));
        space.setLocalTranslation(-35, -1, 35);
        space.setMaterial(spaceMat);
        rootNode.attachChild(space);*/
        
        // ADD A LIGHT SOURCE TO SEE STUFFFZZZ!!!!
        AmbientLight light = new AmbientLight();
        light.setColor(ColorRGBA.White.mult(2));
        rootNode.addLight(light);
        
        //##### Cammera Stuff ##### 
        
        flyCam.setEnabled(false);
        chaseCam = new ChaseCamera(cam, player.getShip().getShipNode(), inputManager);
        
        // #### H.U.D Stuff ####
        mouseInput.setCursorVisible(true);
        /*guiNode.detachAllChildren();
	guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
	BitmapText helloText = new BitmapText(guiFont, false);
	helloText.setSize(guiFont.getCharSet().getRenderedSize());
	helloText.setText("Hello Univurse");
	helloText.setLocalTranslation(300, helloText.getHeight(), 0);
	guiNode.attachChild(helloText);*/
        //TODO: add a targeting reticule, ship or camera face targeting reticule? 
    }
    
    private void initializeControls(){
        
        // Menu Controll
        inputManager.addMapping("TAB", new KeyTrigger (KeyInput.KEY_TAB));
        inputManager.addListener(actionListener, "TAB");
        
        // Ship Controls
        inputManager.addMapping("Pause Game", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("Esc", new KeyTrigger(KeyInput.KEY_ESCAPE));
        inputManager.addMapping("Thrust", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("DeAccelerate", new KeyTrigger(KeyInput.KEY_LSHIFT));
        
        inputManager.addMapping("ShipLeft", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("ShipRight", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("ShipForward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("ShipBackward", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("ShipJump", new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addMapping("ShipAttack", new KeyTrigger(KeyInput.KEY_SPACE));
        
        inputManager.addListener(actionListener, "ShipLeft", "ShipRight");
        inputManager.addListener(actionListener, "ShipForward", "ShipBackward");
        inputManager.addListener(actionListener, "ShipJump", "ShipAttack");
        
        inputManager.addListener(actionListener, new String[] {"Pause Game"});
        inputManager.addListener(actionListener, new String[] {"Esc"});
        inputManager.addListener(analogListener, new String[] {"Thrust"});
        inputManager.addListener(analogListener, new String[] {"DeAccelerate"});
        
        // Mouse Controls
        
        // Firing/Selecting Controls
        inputManager.addMapping("LMB", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, new String[] {"LMB"});
    }
    
    private ActionListener actionListener = new ActionListener() {
        private String numBullets = "0";

            public void onAction(String name, boolean keyPressed, float tpf) {
                if (name.equals("Pause Game") && !keyPressed) {
                    
                    System.out.println("Game Should Pause or Un-Pause");
                }
                
                if (name.equals ("Esc") && keyPressed) {
                    
                    System.out.println("Exit Game");
                    //TODO: enable exit via esc key not via default esc key controll.
                } 
                
                // TAB MENU CONTROLL FOR TESTING STUFF
                //TODO make a veriable to catch if menu has been created, and just call it instead of recreating it each time
                if (name.equals ("TAB") && keyPressed) {
                    System.out.println("TAB Menu Open!");
                    nifty.fromXml("Interface/screenOMGTAB.xml", "OMGTAB", new ScreenOMGTAB("OMGTAB", this)); //, myServer, myClient));
                    nifty.gotoScreen("OMGTAB");
                }
                
                
                // SHIP! CONTROLS
                if (name.equals("ShipLeft")) {
                    if (keyPressed) left = true;
                    else left = false;
                } 
                else if (name.equals("ShipRight")) {
                    if (keyPressed) right = true;
                    else right = false;
                } 
                else if (name.equals("ShipForward")) {
                    if (keyPressed) up = true;
                    else up = false;
                } 
                else if (name.equals("ShipBackward")) {
                    if (keyPressed) down = true;
                    else down = false;
                } 
                else if (name.equals("ShipJump")){
                    //character.jump();
                }

                if (name.equals("ShipAttack")){
                    //TODO: force leftEngine button? perhapse based on hull type selection is replaced with direct leftEngine controll
                    // This way the player can FLY AND SHOOT AAT THE SAME TIME OMFG!!!!
                    //Fire();
                    // CODE BELOW SHOULD BE IN THIS FIRE function, so that leftEngine and attack are the same
                    // maby this can be like a force leftEngine? or some other shit.. if in range and shipAttack is on, leftEngine
                    // etc etc.. LMB is just to select target, so get target, if in range, and this set to leftEngine, leftEngine... ?
                }
                
                
                // Targeting Mouse!!!!!
                // this should be target ship u select if targetable.
                // thats it? then if your turret is in range of the ship, leftEngine on ship!
                if (name.contains("LMB") && keyPressed){
                    
                    //Reset results List
                    CollisionResults results = new CollisionResults();
                    Ray ray = new Ray(cam.getLocation(), cam.getWorldCoordinates(inputManager.getCursorPosition(), 1.001f));
                    // collect intersections between ray and selectables in results list
                    selectablesNode.collideWith(ray, results);
                    // print the results
                    System.out.println("----- Collisions: " + results.size() + "-----");
                    for (int i = 0; i < results.size(); i++) {
                        // for each hit
                        float dist = results.getCollision(i).getDistance();
                        Vector3f pt = results.getCollision(i).getContactPoint();
                        String hit = results.getCollision(i).getGeometry().getName();
                        System.out.println("* Collision #" + i);
                        System.out.println(" You Targeted " + hit + " at " + pt + ", " + dist + " WU away.");
                        
                    }
                    // use results (hit with red dot)
                    if (results.size() > 0) {
                        //the closest collision point is what was truly hit
                        CollisionResult closest = results.getClosestCollision();
                        // lets interact - mark with red dot
                        mark.setLocalTranslation(closest.getContactPoint());
                        rootNode.attachChild(mark);
                        lastResults = results;
                        
                        haveTarget = true;
                        
                    }
                    else {
                        // no hits then remove red mark.
                        rootNode.detachChild(mark);
                        haveTarget = false;
                    }
                    
                }
                
            }
        };
    
    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog (String name, float value, float tpf) {
            
            // TODO: Make the engines spit out fire or stuff when thrusting!
            // ENGINES PARTICLE TEST STUFF
            // Create Emitter for Effect
            ParticleEmitter leftEngine = new ParticleEmitter("Engines Emitter", ParticleMesh.Type.Triangle, 1);
            ParticleEmitter rightEngine = new ParticleEmitter("Engines Emitter", ParticleMesh.Type.Triangle, 1);
            Material mat_fire = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
            mat_fire.setTexture("Texture", assetManager.loadTexture("Textures/Effects/flame.png"));
            leftEngine.setMaterial(mat_fire);
            rightEngine.setMaterial(mat_fire);
            leftEngine.setImagesX(2); 
            rightEngine.setImagesX(2); 
            leftEngine.setImagesY(2); // 2x2 texture animation
            rightEngine.setImagesY(2); // 2x2 texture animation
            leftEngine.setEndColor(  new ColorRGBA(1f, 0f, 0f, 1f));   // red
            rightEngine.setEndColor(  new ColorRGBA(1f, 0f, 0f, 1f));   // red
            leftEngine.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
            rightEngine.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
                                                                                        // -21 speed or something? at which particles fly
            leftEngine.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 0, -8));
            rightEngine.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 0, -8));
            leftEngine.setStartSize(1.8f);
            rightEngine.setStartSize(1.8f);
            leftEngine.setEndSize(0.1f);
            rightEngine.setEndSize(0.1f);
                                   // when i have player movement working again, make the gravity as oposite the players facing direction?
            leftEngine.setGravity(0, 0, 0);
            rightEngine.setGravity(0, 0, 0);
                                // low and high life are like the lengch it has to live or something?
            leftEngine.setLowLife(0.3f);
            rightEngine.setLowLife(0.3f);
            leftEngine.setHighLife(0.7f);
            rightEngine.setHighLife(0.7f);
            leftEngine.getParticleInfluencer().setVelocityVariation(0.25f);
            rightEngine.getParticleInfluencer().setVelocityVariation(0.25f);

            // Attach to root node and position at location
            rootNode.attachChild(leftEngine);
            rootNode.attachChild(rightEngine);
            leftEngine.setLocalTranslation(player.getShip().getShipEngine().getEngineNode().getLocalTranslation().add(1.5f, 0.2f, -9.5f));
            rightEngine.setLocalTranslation(player.getShip().getShipEngine().getEngineNode().getLocalTranslation().add(-1.5f, 0.2f, -9.5f));
            // END ENGINES PARTICLE TEST STUFF
            
            
            if (name.equals("Thrust")) {                     // test?
                //thrusting = !thrusting;                    // action!
                //TODO: Add ability to 'thrust' to increase speed.
                System.out.println("Engines On");
                
                
                // ENGINES PARTICLE TEST STUFF
                //Trigger the effect by calling
                leftEngine.emitAllParticles();
                rightEngine.emitAllParticles();
                
                // wont stop for some reason????
                leftEngine.killAllParticles();
                rightEngine.killAllParticles();
                // END ENGINES PARTICLE TEST STUFF
                
                //System.out.println("Thrust: "+thrust+" Facing: "+shipHeading+ "Speed: "+shipSpeed);
            }
            
            if (name.equals("DeAccelerate")) {
                //TODO: add the ability to 'deaccelerate' to decrease speed
                System.out.println("DeAccelerating... Backwards");
                
                // ENGINES PARTICLE TEST STUFF
                //End the effect by calling
                leftEngine.killAllParticles();
                rightEngine.killAllParticles();
                // END ENGINES PARTICLE TEST STUFF
                
            }
            
        }
        
    };
    
    

    @Override
    public void simpleUpdate(float tpf) {
        
        
        // TODO: 1 - AIMING MABY! FIX LATER?
        // just check between the two nodes? with a range of 10? WU i think?
        // if( Tower.myNode.getWorldLocation().subtract(Enemy.myNode.getWorldLocation()).length() < 10 ) Tower.Fire(Enemy);
        //player.getShip().getShipNode().getWorldTranslation().subtract(enemy.getShip().getShipNode().getWorldTranslation()()).Length() < 4
        
        
        
        // TODO: Fix Aiming on turrets!
        if (haveTarget == true){
            // Testing Stuff Bellow
            float tempRange = 25;
            //System.out.println(player.getShip().getShipNode().getWorldTranslation().subtract(enemy.getShip().getShipNode().getWorldTranslation()));
            /*Vector3f targeter = new Vector3f();
            Vector3f targetee = new Vector3f();
            targeter = player.getShip().getShipNode().getWorldTranslation();
            targetee = enemy.getShip().getShipNode().getWorldTranslation();*/
            if (player.getShip().getShipNode().getWorldTranslation().subtract(enemy.getShip().getShipNode().getWorldTranslation()).length() < tempRange){
                player.getShipTurret().faceTurretAt(lastResults.getClosestCollision().getContactPoint());
                player.getShipTurret2().faceTurretAt(lastResults.getClosestCollision().getContactPoint());
                player.getShipTurret3().faceTurretAt(lastResults.getClosestCollision().getContactPoint());
                
                
                // TODO: Make the Cannons FIRE When targetting
                // Cannons FIRING TEST STUFF

                /*ParticleEmitter leftEngine = 
                        new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);
                Material mat_fire = new Material(assetManager, 
                        "Common/MatDefs/Misc/Particle.j3md");
                mat_fire.setTexture("Texture", assetManager.loadTexture(
                        "Textures/Effects/flame.png"));
                leftEngine.setMaterial(mat_fire);
                leftEngine.setImagesX(2); 
                leftEngine.setImagesY(2); // 2x2 texture animation
                leftEngine.setEndColor(  new ColorRGBA(1f, 0f, 0f, 1f));   // red
                leftEngine.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
                leftEngine.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 2, 0));
                leftEngine.setStartSize(2.5f);
                leftEngine.setEndSize(0.2f);
                leftEngine.setGravity(0, 0, 0);
                leftEngine.setLowLife(1f);
                leftEngine.setHighLife(3f);
                leftEngine.getParticleInfluencer().setVelocityVariation(0.3f);
                //fire.setLocalTranslation(player.getShip().getShipEngine().getEngineNode().getWorldTranslation());
                rootNode.attachChild(leftEngine);*/

                // END Cannons FIRING PARTICLE TEST STUFF
                
            }
            
        }
        else{
            // TODO: reset turret direction to neutral position when no target available.
        }
        
      
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    // Testing for target Practice Creates a red dot on target
    protected void initializeMark() {
        
        Sphere sphere = new Sphere(30,30,0.2f);
        mark = new Geometry("click", sphere);
        Material markMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        markMat.setColor("Color", ColorRGBA.Red);
        mark.setMaterial(markMat);
        
    }
    

}

