/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refferenceStuff;

import com.bulletphysics.collision.broadphase.Dbvt.Node;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.input.KeyInput;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Renderer;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import net.java.games.input.Component.Identifier.Axis;

/**
 *
 * @author Viorel Iliescu
 */
//import com.jme.bounding.BoundingBox;
//import com.jme.bounding.BoundingSphere;
//import com.jme.bounding.BoundingVolume;
//import com.jme.image.Texture;
//import com.jme.input.KeyInput;
//import com.jme.input.controls.GameControl;
//import com.jme.input.controls.GameControlManager;
//import com.jme.input.controls.binding.KeyboardBinding;
//import com.jme.input.controls.binding.MouseButtonBinding;
//import com.jme.input.controls.controller.ActionController;
//import com.jme.input.controls.controller.ActionRepeatController;
//import com.jme.input.controls.controller.Axis;
//import com.jme.input.controls.controller.GameControlAction;
//import com.jme.math.Vector3f;
//import com.jme.renderer.Renderer;
//import com.jme.scene.Node;
//import com.jme.scene.Spatial.LightCombineMode;
//import com.jme.scene.shape.Quad;
//import com.jme.scene.state.BlendState;
//import com.jme.scene.state.LightState;
//import com.jme.scene.state.TextureState;
//import com.jme.system.DisplaySystem;
//import com.jme.util.TextureManager;
//import com.jme.util.resource.ResourceLocatorTool;
//import com.jmedemos.stardust.controls.ControlManager;
//import com.jmedemos.stardust.effects.ParticleEffectFactory;
//import com.jmedemos.stardust.scene.actions.ShipMissileAction;
//import com.jmedemos.stardust.scene.actions.ShipWeaponAction;
//import com.jmedemos.stardust.sound.SoundUtil;
//import com.jmedemos.stardust.util.PhysicsThrustController;
//import com.jmex.jbullet.PhysicsSpace;

/**
 * represents a player.
 * the constructor loads the 3d model and add it to a physics node,
 * and defines controls to steer the ship.
 */
public class PlayerShip extends PhysicsEntity {
    /** roll speed.*/
    private float rollSpeed = 0.4f;
   /** min. time between shots in ms. */
    private int fireRate = 100;
    /** Physics throttle controller. */
    //private PhysicsThrustController physicsThrustController = null;

    /**
     * Weapon positions.
     */
    private Node upperLeftWeapon = null;
    private Node upperRightWeapon = null;
    private Node lowerRightWeapon = null;
    private Node lowerLeftWeapon = null;

    //private TargetDevice targetDevice = null;
    
    /**
     * node of the crosshair.
     * the crosshair is actually a quad mounted to the ship,
     * pretty ugly !! but it works for now.
     */
    //private Node crosshair = null;

    /**
     * Constructor, init controls.
     * @param name node name
     * @param root reference to root node
     * @param physicsSpace reference to physics space.
     * @param model path to the model
     * @param scale model scaling
     */
    public PlayerShip(final Node root, final PhysicsSpace physicsSpace, final String modelString, final float scale) {
        super(physicsSpace, modelString, scale, true, false);

        //damage = 5;

        // set weapon positions
        // this is dependant on the loaded model !! (X-Wing == 4 Weapons)
        /*node.updateWorldBound();
        BoundingBox box = (BoundingBox) node.getWorldBound();
        upperRightWeapon = new Node("upperRightWeapon");
        upperLeftWeapon = new Node("upperLeftWeapon");
        lowerRightWeapon = new Node("lowerRightWeapon");
        lowerLeftWeapon = new Node("lowerLeftWeapon");

        node.attachChild(upperRightWeapon);
        node.attachChild(upperLeftWeapon);
        node.attachChild(lowerRightWeapon);
        node.attachChild(lowerLeftWeapon);
        
        upperRightWeapon.setLocalTranslation(-box.xExtent, box.yExtent,
                box.zExtent*3);
        upperLeftWeapon.setLocalTranslation(box.xExtent, box.yExtent,
                box.zExtent*3);
        lowerLeftWeapon.setLocalTranslation(box.xExtent, -box.yExtent,
                box.zExtent*3);
        lowerRightWeapon.setLocalTranslation(-box.xExtent, -box.yExtent,
                box.zExtent*3);

        setupCrosshair();

        initControls();

        targetDevice = new TargetDevice(node, root);
        node.addController(targetDevice);
        
        // refresh renderstates
        node.updateRenderState();
        
        node.setFriction(5);
        
        log.info("Player created: ");
        log.info("mass " +node.getMass());
        log.info("friction " +node.getFriction());
        */
        
    }
    
    /**
     * create a textured quad and attach it infront of the ship.
     */
    private void setupCrosshair() {
        /*DisplaySystem display = DisplaySystem.getDisplaySystem();
        crosshair = new Node("Crosshair");

        Quad q = new Quad("Crosshair quad", 50, 50);
        crosshair.attachChild(q);

        TextureState ts = display.getRenderer().createTextureState();
        ts.setTexture(TextureManager.loadTexture(
                        ResourceLocatorTool.locateResource(ResourceLocatorTool.TYPE_TEXTURE,"crosshair.png"),
                Texture.MinificationFilter.BilinearNoMipMaps, 
                Texture.MagnificationFilter.Bilinear));
        ts.setEnabled(true);
        q.setRenderState(ts);

        crosshair.setLightCombineMode(LightCombineMode.Replace);
        
        LightState ls = display.getRenderer().createLightState();
        ls.setEnabled(false);
        crosshair.setRenderState(ls);

        BlendState as = display.getRenderer().createBlendState();
        as.setBlendEnabled(true);
        as.setTestEnabled(false);
        as.setSourceFunction(BlendState.SourceFunction.SourceAlpha);
        as.setDestinationFunction(BlendState.DestinationFunction.OneMinusSourceAlpha);
        as.setEnabled(true);
        crosshair.setRenderState(as);
        crosshair.setRenderQueueMode(Renderer.QUEUE_TRANSPARENT);

        node.attachChild(crosshair);

        crosshair.setLocalTranslation(0, 0, 1000);
        crosshair.updateGeometricState(0, true);
        crosshair.updateRenderState();
    }

    @Override
    public void die() {
        node.updateWorldVectors();
        ParticleEffectFactory.get().spawnExplosion(node.getWorldTranslation());
        SoundUtil.get().playExplosion(node.getWorldTranslation());
        node.setLocalTranslation(new Vector3f());
        health = 100;*/
    }

    /**
     * create GameControls to maneuver, fire and lock targets.
     */
    private void initControls() {
        /*GameControlManager manager = ControlManager.get().getGcm();

        GameControl forward = manager.addControl("Forward");
        forward.addBinding(new KeyboardBinding(KeyInput.KEY_W));

        GameControl backward = manager.addControl("Backward");
        backward.addBinding(new KeyboardBinding(KeyInput.KEY_S));

        GameControl fireWeapon = manager.addControl("Fire Weapon");
        fireWeapon.addBinding(new MouseButtonBinding(
                MouseButtonBinding.LEFT_BUTTON));

        GameControl fireMissile = manager.addControl("Fire Missile");
        fireMissile.addBinding(new MouseButtonBinding(
                MouseButtonBinding.RIGHT_BUTTON));
        
        // Lock target
        manager.addControl("lockTarget").addBinding(new KeyboardBinding(KeyInput.KEY_L));
        ActionController lockTargetController = new ActionController (manager.getControl("lockTarget"), new GameControlAction() {
            public void pressed(GameControl control, float time) {
                targetDevice.toggleLock();
            }
            public void released(GameControl control, float time) {
            }
        });
        node.addController(lockTargetController);

        ActionRepeatController fireMissileController = new ActionRepeatController(
                fireMissile, 1000, new ShipMissileAction(this));
        node.addController(fireMissileController);
        
        ActionRepeatController fireWeaponController = new ActionRepeatController(
                fireWeapon, fireRate, new ShipWeaponAction(this));
        node.addController(fireWeaponController);

        node.addController(ControlManager.get().createRollControl(node, getRollSpeed()));
        node.addController(ControlManager.get().createYawControl(node, getRollSpeed()));
        node.addController(ControlManager.get().createPitchControl(node, getRollSpeed()));

        physicsThrustController = new PhysicsThrustController(node, Axis.Z, forward, backward, 1,
                        100, 2000, 500);
        node.addController(physicsThrustController);
        
        */
    }

    public final float getRollSpeed() {
        return rollSpeed;
    }

    public void setRollSpeed(float rollSpeed) {
        this.rollSpeed = rollSpeed;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public Node getLowerLeftWeapon() {
        return lowerLeftWeapon;
    }

    public void setLowerLeftWeapon(Node lowerLeftWeapon) {
        this.lowerLeftWeapon = lowerLeftWeapon;
    }

    public Node getLowerRightWeapon() {
        return lowerRightWeapon;
    }

    public void setLowerRightWeapon(Node lowerRightWeapon) {
        this.lowerRightWeapon = lowerRightWeapon;
    }

    public Node getUpperLeftWeapon() {
        return upperLeftWeapon;
    }

    public void setUpperLeftWeapon(Node upperLeftWeapon) {
        this.upperLeftWeapon = upperLeftWeapon;
    }

    public Node getUpperRightWeapon() {
        return upperRightWeapon;
    }

    public void setUpperRightWeapon(Node upperRightWeapon) {
        this.upperRightWeapon = upperRightWeapon;
    }

    /*public TargetDevice getTargetDevice() {
        return targetDevice;
    }*/

        /*public PhysicsThrustController getPhysicsThrustController() {
                return physicsThrustController;
        }*/
}

