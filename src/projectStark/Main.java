package projectStark;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
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
import com.jme3.export.binary.BinaryExporter;
import com.jme3.export.binary.BinaryImporter;
import com.jme3.font.BitmapText;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.mySQL.MySQLAccess;


/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication { 
    
    //Player Veriables
    private Entity player;
    
    //World Veriables
    private Node selectablesNode    = new Node("Selectables");
    private Node playerNode         = new Node("Player");
    private Node friendliesNode     = new Node("Friendlies");
    private Node enemiesNode         = new Node("Enemies");
    
    // Sub-Systems
    BulletAppState          bulletAppState;
    CapsuleCollisionShape   capsuleShape;
    CharacterControl        shipPhys;
    NiftyJmeDisplay         niftyDisplay;
    Nifty                   nifty;
    // Server Stuff!!
    Server myServer = null;
    Client myClient = null;
    
    //CollisionResults results = new CollisionResults();
    private CollisionResults lastResults;
    private boolean haveTarget      = false;
    private boolean playerSelected  = false;
    Geometry mark;
    Geometry playerMarkSelected;
    Geometry playerMarkNotSelected;
    
    // TESTING!!!
    
    public static void main(String[] args) {
        Main mainApplication = new Main();
        AppSettings applicationSettings = new AppSettings(true);
        applicationSettings.setTitle("Project - Stark");
        applicationSettings.setFrameRate(100);                                  // set framerate to a constant value
        mainApplication.setSettings(applicationSettings);
        mainApplication.start();                                                // update, draw, update, draw, update, draw, update, etc...
    }
    
    @Override
    public void simpleInitApp() {
        
        //TODO impliment or build an RTS cam like this Cam some time?

        // #### NIFTY GUI testing stuff
        //TODO omg! ship should be made of blocks, as blocks get blown off, 
        //hp goes down, so ship is actuly blown to peices when it has 0 health!!!

        // CURSOR STUFF?
        // registerMouseCursor("myPointer", "interface/pointer.png", 5, 4);
        
        // defining screens, main, HUD, shipCustomise/create, Gameplay, etc...

        //#NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        nifty = niftyDisplay.getNifty();
        nifty.fromXml("Interface/screenHUD.xml", "screenHUD", new ScreenHUD("screenHUD"));
        nifty.fromXml("Interface/screenMultiplayer.xml", "screenMultiplayer", new ScreenMultiplayer("screenMultiplayer"));
        nifty.fromXml("Interface/screenStart.xml", "screenStart", new ScreenStart("screenStart", this));
        guiViewPort.addProcessor(niftyDisplay);

        // TODO use a loading screen to do this stuff

        // TODO SQL - maby have a quick check to make sure each table required is in the database? if not, create each one? or something?
        // temp SQL stuff, used for testing.
        /*MySQLAccess mySQL = new MySQLAccess();
        try {
            mySQL.connectToDB();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            mySQL.createTableShipSectionHullInDB();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        // #### Set Controls ####
        //PlayerControls PCcontrols = new PlayerControls();
        //PCcontrols.InitializeControls();
        initializeControls(); //- DISABLED WHILIST MAKEING SAVE/LOAD WORK!

        // TESTING STARFIELD!!!
        // Start the StarDust
        StarDust starDust = new StarDust("StarDust", 1000, 700f, cam, assetManager);
        rootNode.attachChild(starDust);

        // split the root node into multiple nodes
        rootNode.attachChild(selectablesNode);
        selectablesNode.attachChild(playerNode);
        selectablesNode.attachChild(friendliesNode);
        selectablesNode.attachChild(enemiesNode);
        initializeMark();

        // Start Serializing Stuff
        // TODO: add buttons to the TAB page to test, get a save and a load, check out the TAB controller
        // End Serialising Stuff

        // TODO: maby add like a load player ship? save ship, etc?
        // Make PlayerShip Note: entity = 0 empty, 1 player, 2 nuteral, 3 enemy
        /*player = new Entity(1, 0);
        //player.getShip().setShipNode ( new Node("Ship"));
        ShipFactory shipFactory = new ShipFactory();
        shipFactory.generateShip(assetManager, player.getShip().getShipNode(), player);
        System.out.println("Player Flag Ship Has Been Generated...");
        //Ship.move(startingLocation);
        playerNode.attachChild(player.getShip().getShipNode());*/

        // SAVE AND LOAD TESTING!!!!!!
        player = new Entity(1, 0);
        //player.getShip().setShipNode ( new Node("Ship"));
        ShipFactory shipFactory = new ShipFactory();

        String userHome = System.getProperty("user.home");
        BinaryImporter importer = BinaryImporter.getInstance();
        importer.setAssetManager(assetManager);
        File file = new File(userHome+"/Stark/"+"LastSavedGame.j3o");
        if(file.exists()) {
            try {
                Node loadedShipNode = (Node)importer.load(file);
                loadedShipNode.setName("loaded node");

                // load players ship
                player.getShip().setShipNode(loadedShipNode);
                System.out.println("Player Flag Ship Has Been Generated...");
                //Ship.move(savedLocation);
                playerNode.attachChild(player.getShip().getShipNode());
                System.out.println("Game has been LOADED!!!...");

            } 
            catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Could not load saved game.", ex);
            }
        }
        else {
            // Create new player if no load game available
            System.out.println("New GAME CREATED!!!...");
            shipFactory.generateShip(assetManager, player.getShip().getShipNode(), player);
            System.out.println("Player Flag Ship Has Been Generated...");
            //Ship.move(startingLocation);
            playerNode.attachChild(player.getShip().getShipNode());
        }

        // END SAVE AND LOAD TESTING!!!!!!

        // #### TESTING #####
        // Unit Selection STUFF

        initializePlayerMark();
        playerMarkNotSelected.setLocalTranslation(player.getShip().getShipNode().getWorldTranslation().add(0, 6.5f, -1f));
        playerMarkSelected.setLocalTranslation(player.getShip().getShipNode().getWorldTranslation().add(0, 6.5f, -1f));
        player.getShip().getShipNode().attachChild(playerMarkNotSelected);



        //END Unit Selection STUFF


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

        // ########## link list of enemeis ##########
        // attempt 3

        List<Entity> enemyList = new LinkedList<Entity>();

        int spawnEns = 13;
        int shipsCreated = 0;
        int tempDistanceX = FastMath.nextRandomInt(-10, 10);;
        int tempDistanceZ = FastMath.nextRandomInt(-10, 10);;

        for (int i = 0; i < spawnEns; i++){
            Entity newEnemy = new Entity(3, i);
            //enemyList.insert(i, newEnemy);
            enemyList.add(i, newEnemy);

            shipFactory.generateShip(assetManager, newEnemy.getShip().getShipNode(), newEnemy);
            //System.out.println("Created Ship ID "+enemyList.getID()+"");
            System.out.println("Created Ship ID "+enemyList.get(i).getShip().getShipID()+" - Ship #"+i);
            newEnemy.getShip().getShipNode().move(10f *tempDistanceX, 0f, 10f *tempDistanceZ);
            enemiesNode.attachChild(newEnemy.getShip().getShipNode());
            //enemyList.insert(i, newEnemy);

            tempDistanceX = FastMath.nextRandomInt(-10, 10);
            tempDistanceZ = FastMath.nextRandomInt(-10, 10);
            shipsCreated++;
        }

        System.out.println("Created a Total of "+shipsCreated+" Ships");

        // end attempt 3
        // ############# end enemy link list testing ###############

        // Create a floor for refrence Full of Stars? or just white stuff... 
        //TODO: Fix ground into stars or something, can wait need for testing
        Material spaceMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Geometry space = new Geometry("space", new Quad(150, 150));
        space.setLocalRotation(new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_X));
        space.setLocalTranslation(-35, -2, 35);
        space.setMaterial(spaceMat);
        selectablesNode.attachChild(space);

        // ADD A LIGHT SOURCE TO SEE STUFFFZZZ!!!!
        AmbientLight light = new AmbientLight();
        light.setColor(ColorRGBA.White.mult(2));
        rootNode.addLight(light);

        //##### Cammera Stuff ##### 

        //->flyCam.setEnabled(false);
        flyCam.setDragToRotate(true);
        flyCam.setUpVector(Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(8f);
        //->chaseCam = new ChaseCamera(cam, player.getShip().getShipNode(), inputManager);


        // #### H.U.D Stuff ####
        mouseInput.setCursorVisible(true);
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("Welcome to Stark");
        helloText.setLocalTranslation(300, helloText.getHeight(), 0);
        guiNode.attachChild(helloText);
        //TODO: add a targeting reticule, ship or camera face targeting reticule? 
            
    }
    
    private void initializeControls() {
        
        // Menu Controls
        inputManager.addMapping("TAB", new KeyTrigger (KeyInput.KEY_TAB));
        inputManager.addListener(actionListener, "TAB");
        inputManager.addMapping("Pause Game", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addListener(actionListener, new String[] {"Pause Game"});
        inputManager.addMapping("Esc", new KeyTrigger(KeyInput.KEY_ESCAPE));
        inputManager.addListener(actionListener, new String[] {"Esc"});
       
        // Mouse Controls
        inputManager.addMapping("LSHIFT", new KeyTrigger (KeyInput.KEY_LSHIFT));
        inputManager.addListener(actionListener, "LSHIFT");
        // - Firing/Selecting Controls
        inputManager.addMapping("LMB", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, new String[] {"LMB"});
        inputManager.addMapping("RMB", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.addListener(actionListener, new String[] {"RMB"});
        // - Ship Controls
        inputManager.addMapping("Thrust", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addListener(analogListener, new String[] {"Thrust"});
    }
    
    private ActionListener actionListener = new ActionListener() {

            public void onAction(String name, boolean keyPressed, float tpf) {
                if (name.equals("LSHIFT") && !keyPressed) {
                    //System.out.println("");
                    // toggles the mouse on or off?
                    if (flyCam.isDragToRotate()) {
                        System.out.println("Mouse Off");
                        flyCam.setDragToRotate(false);
                    }
                    else {
                        System.out.println("Mouse On");
                        flyCam.setDragToRotate(true);
                    }
                }
                    
                
                if (name.equals("Pause Game") && !keyPressed) {
                    // this should be done with game states!?
                    System.out.println("Game Should Pause or Un-Pause");
                }
                
                if (name.equals ("Esc") && keyPressed) {
                    
                    System.out.println("Exit Game");
                    //TODO: enable exit via esc key not via default esc key controll.
                } 
                
                // TAB MENU CONTROLL FOR TESTING STUFF
                //TODO make a veriable to catch if menu has been created, and just call it instead of recreating it each time
                if (name.equals ("TAB") && keyPressed) {
                    flyCam.setEnabled(false);
                    flyCam.setDragToRotate(false);
                    System.out.println("TAB Menu Open!");
                    nifty.fromXml("Interface/screenOMGTAB.xml", "OMGTAB", new ScreenOMGTAB("OMGTAB", this)); //, myServer, myClient));
                    nifty.gotoScreen("OMGTAB");
                }
                
                
                // SHIP! CONTROLS
                //?? in a controller attached to ship or something?
                
                // Targeting Mouse!!!!!
                if (name.contains("RMB") && keyPressed) {
                    // Right Mouse Button Clicked!
                    if (player.getShip().getShipNode().hasChild(playerMarkNotSelected)) {
                        System.out.println("RIGHT MOUSE BUTTON CLICKED!!!!!");
                        System.out.println("NO SHIP CURRENTLY SELECTED TO DE-SELECT!!!!!!!");
                    }
                    else {
                        player.getShip().getShipNode().detachChild(playerMarkSelected);
                        player.getShip().getShipNode().attachChild(playerMarkNotSelected);
                        System.out.println("RIGHT MOUSE BUTTON CLICKED!!!!!");
                        System.out.println("REMOVED SHIP SELECTION!!!!!!!");
                    }
                    playerSelected = false;
                    
                }
                
                // this should be target ship u select if targetable.
                // thats it? then if your turret is in range of the ship, leftEngine on ship!
                if (name.contains("LMB") && keyPressed) {
                    
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
                        if (closest.getGeometry().hasAncestor(playerNode) == true) {
                            // Player Selected!
                            if (player.getShip().getShipNode().hasChild(playerMarkNotSelected)) {
                                player.getShip().getShipNode().detachChild(playerMarkNotSelected);
                                player.getShip().getShipNode().attachChild(playerMarkSelected);
                            }
                            else {
                                
                            }
                            System.out.println("PLAYER CLICKED!!!!!");
                            System.out.println("PLAYER SELECTED!!!!!");
                            playerSelected = true;
                        }
                        else if (closest.getGeometry().hasAncestor(enemiesNode) == true) {
                            // Enemy Selected!
                            if (player.getShip().getShipNode().hasChild(playerMarkNotSelected)) {
                                System.out.println("ENEMY CLICKED!!!!!");
                                System.out.println("ENEMY NOT TARGETTED!!!!!");
                            }
                            else {
                                if (player.getShip().getShipNode().hasChild(playerMarkSelected)) {
                                    System.out.println("ENEMY CLICKED!!!!!");
                                    System.out.println("ENEMY TARGETTED!!!!!");
                                }
                                else {
                                    player.getShip().getShipNode().detachChild(playerMarkSelected);
                                    player.getShip().getShipNode().attachChild(playerMarkNotSelected);
                                    System.out.println("ENEMY CLICKED!!!!!");
                                    System.out.println("ENEMY NOT TARGETTED!!!!!");
                                }
                            }
                            
                        }
                        // TODO: MAKE MOVEMENT SMOOTHER!? use jmonkey steering library?
                        else if (closest.getGeometry().hasAncestor(selectablesNode) == true) {
                            if (player.getShip().getShipNode().hasChild(playerMarkSelected)) {
                                System.out.println("MOVE SHIP!!!!");
                                float value = 5;
                                float playerCurrentMaximumSpeed = 10;
                                player.getShip().getShipNode().lookAt(mark.getLocalTranslation(), new Vector3f(0, 1, 0));
                                // this returns the direction of the spatial
                                player.getShip().setShipHeading(player.getShip().getShipNode().getWorldRotation().getRotationColumn(2).normalizeLocal()); 
                                player.getShip().getShipNode().move( player.getShip().getShipHeading().mult(value).multLocal(playerCurrentMaximumSpeed));
                            }
                        }
                        else {
                            // Nothing Selected!
                            if (player.getShip().getShipNode().hasChild(playerMarkNotSelected)) {
                                
                            }
                            else {
                                player.getShip().getShipNode().detachChild(playerMarkSelected);
                                player.getShip().getShipNode().attachChild(playerMarkNotSelected);
                            }
                            System.out.println("PLAYER NOT CLICKED!!!!!");
                            System.out.println("PLAYER NOT SELECTED!!!!!");
                            playerSelected = false;
                            
                        }
                        
                    }
                    else {
                        // no hits then remove red mark.
                        rootNode.detachChild(mark);
                        haveTarget = false;
                        
                        // Nothing Selected!
                        if (player.getShip().getShipNode().hasChild(playerMarkNotSelected)) {
                            
                        }
                        else {
                             player.getShip().getShipNode().detachChild(playerMarkSelected);
                             player.getShip().getShipNode().attachChild(playerMarkNotSelected);   
                        }
                        System.out.println("NOTHING CLICKED!!!!!");
                        System.out.println("NOTHING SELECTED!!!!!!!");
                        playerSelected = false;
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
                                                                                        // -8 speed or something? at which particles fly
            leftEngine.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 0, -8));
            rightEngine.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 0, -8));
            leftEngine.setStartSize(2.4f);
            rightEngine.setStartSize(2.4f);
            leftEngine.setEndSize(0.1f);
            rightEngine.setEndSize(0.1f);
                                   // when i have player movement working again, make the gravity as oposite the players facing direction?
            leftEngine.setGravity(0, 0, 0);
            rightEngine.setGravity(0, 0, 0);
                                // low and high life are like the lengch it has to live or something?
            leftEngine.setLowLife(0.2f);
            rightEngine.setLowLife(0.2f);
            leftEngine.setHighLife(1.6f);
            rightEngine.setHighLife(1.6f);
            leftEngine.getParticleInfluencer().setVelocityVariation(0.25f);
            rightEngine.getParticleInfluencer().setVelocityVariation(0.25f);

            // Attach to root node and position at location
            rootNode.attachChild(leftEngine);
            rootNode.attachChild(rightEngine);
            leftEngine.setLocalTranslation(player.getShip().getShipEngine().getEngineNode().getLocalTranslation().add(1.5f, 0.2f, -9.5f));
            rightEngine.setLocalTranslation(player.getShip().getShipEngine().getEngineNode().getLocalTranslation().add(-1.5f, 0.2f, -9.5f));
            // END ENGINES PARTICLE TEST STUFF
            
            
            if (name.equals("Thrust")) {
                System.out.println("Engines On");
                
                //TODO: MAKE ENGINES OUTPUT FOLLOW SHIP
                //Trigger the effect by calling            
                leftEngine.emitAllParticles();
                rightEngine.emitAllParticles();
                
                leftEngine.setParticlesPerSec(0);
                rightEngine.setParticlesPerSec(0);
            }
            
        }
        
    };
    
    

    @Override
    public void simpleUpdate(float tpf) {
        
        
        // TODO: 1 - AIMING MABY! FIX LATER?
        // Reference stuff - just check between the two nodes? with a range of 10? WU i think?
        // if( Tower.myNode.getWorldLocation().subtract(Enemy.myNode.getWorldLocation()).length() < 10 ) Tower.Fire(Enemy);
        //player.getShip().getShipNode().getWorldTranslation().subtract(enemy.getShip().getShipNode().getWorldTranslation()()).Length() < 20
        
        
        
        // TODO: Fix Aiming on turrets!
        if (haveTarget == true){
            // Testing Stuff Bellow
            float tempRange = 25;
            //System.out.println(player.getShip().getShipNode().getWorldTranslation().subtract(enemy.getShip().getShipNode().getWorldTranslation()));
            /*Vector3f targeter = new Vector3f();
            Vector3f targetee = new Vector3f();
            targeter = player.getShip().getShipNode().getWorldTranslation();
            targetee = enemy.getShip().getShipNode().getWorldTranslation();*/
            //if (player.getShip().getShipNode().getWorldTranslation().subtract(enemy.getShip().getShipNode().getWorldTranslation()).length() < tempRange){
            //if (player.getShip().isShipSelected() == true) { 
            if(playerSelected == true){
                player.getShipTurret().faceTurretAt(lastResults.getClosestCollision().getContactPoint());
                player.getShipTurret2().faceTurretAt(lastResults.getClosestCollision().getContactPoint());
                player.getShipTurret3().faceTurretAt(lastResults.getClosestCollision().getContactPoint());
                
                // TODO: Make the Cannons FIRE When firing
                // Cannons FIRING TEST STUFF
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
    
    // Testing for player selection Practice Creates a white dot ubove player ship and changes green when player is selected
    protected void initializePlayerMark() {
        
        Sphere sphere = new Sphere(30,30,0.2f);
        playerMarkNotSelected = new Geometry("markNotSel", sphere);
        Material playerMatNS = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        playerMatNS.setColor("Color", ColorRGBA.White);
        playerMarkNotSelected.setMaterial(playerMatNS);
        
        playerMarkSelected = new Geometry("markSel", sphere);
        Material playerMatS = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        playerMatS.setColor("Color", ColorRGBA.Green);
        playerMarkSelected.setMaterial(playerMatS);
        
    }
    
    // TODO: MOD THIS INTO THE [TAB] DEBUG MENU!
    /* This is called when the user quits the mainApplication. */
    @Override
    public void destroy() {
        String userHome = System.getProperty("user.home");
        BinaryExporter exporter = BinaryExporter.getInstance();
        //long timeStamp = System.currentTimeMillis()/1000;
        File file = new File(userHome+"/Stark/"+"LastSavedGame.j3o");
        // time stamp can be used with a load game options for player to select?
        //File file = new File(userHome+"/Stark/"+"LastSavedGame-"+timeStamp+".j3o");

        try {
            exporter.save(player.getShip().getShipNode(), file);
            System.out.println("Game SAVED ON EXIT!");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error: Failed to save game!", ex);
        }
        super.destroy(); // continue quitting the game
    }

}
