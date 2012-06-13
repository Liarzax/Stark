/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refferenceStuff;

/**
 *
 * @author Viorel Iliescu
 */
import com.bulletphysics.collision.broadphase.Dbvt.Node;
import com.jme3.bullet.PhysicsSpace;
import java.util.logging.Logger;

//import com.jme.scene.Node;
//import com.jmedemos.stardust.core.Game;
//import com.jmedemos.stardust.scene.MissileCamera;
//import com.jmedemos.stardust.scene.PlayerShip;
//import com.jmex.jbullet.PhysicsSpace;

/**
 * factory class to create different projectils.
 *
 * the type of the projectile can either set directly as a parameter
 * to createProjectile(), or the currently in the factory set type can be used.
 */
public class ProjectileFactory {
    private static ProjectileFactory instance;
    /** reference to the missile cam. */
    //private MissileCamera missileCam;
    /** reference to logger. */
    private Logger log = Logger.getLogger(ProjectileFactory.class.getName());
    /** reference to root node. */
    private Node rootNode = null;
    /** reference to physics space. */
    private PhysicsSpace physics = null;
    //private BulletProjectilePool bulletProjectilePool;
    //private MissileProjectilePool missileProjectilePool;

    /**
     * Initialize the Projectile Factory.
     * @param rootNode the node to attach new projectiles to.
     * @param space reference to a physics space.
     * @param cam reference to the missile cam.
     */
    /*public static void create(final Node rootNode, final PhysicsSpace space,
                                final MissileCamera cam) {
        instance = new ProjectileFactory(rootNode, space, null, cam);
    }*/
   
    /**
     * @return the Singleton instance of the ProjectileFactory.
     */
    public static ProjectileFactory get() {
        if (instance == null) {
            Logger.getLogger(ProjectileFactory.class.getName()).severe(
                    "projectile factory not yet initialized");
            //Game.getInstance().quit();
        }
        return instance;
    }

    /**
     * projectil factory constructor.
     * the root-node and physics space are needed to spawn new projectiles.
     *
     * @param rootNode reference to root node.
     * @param physicsreference to physics space.
     * @param type projectile type.
     */
    /*private ProjectileFactory(final Node rootNode, final PhysicsSpace space,
            final ProjectileType type, final MissileCamera mc) {
        this.rootNode = rootNode;
        this.physics = space;
        missileCam = mc;
       
        this.bulletProjectilePool = new BulletProjectilePool(physics);
        this.missileProjectilePool = new MissileProjectilePool(physics);
    }*/

    /**
     * possible projectile types.
     */
    public enum ProjectileType {
        BULLET, MISSILE
    };

    /**
     * create a new projectile from a specific type.
     * The current internal type will be ignored.
     *
     * @param type projectil type to create.
     * @param direction direction in which the projectil should fly.
     * @param start spawnpoint of the projectile.
     * @param rotation rotation of the ship when firing.
     * @return reference to the newly created projectile.
     */
    /*public final Projectile createProjectile(final ProjectileType type) {
        Projectile p = null;
        switch (type) {
        case BULLET:
            p = bulletProjectilePool.get();
            break;
        case MISSILE:
            p = missileProjectilePool.get();
            break;
        default:
            break;
        }

        if (p != null) {
            // attach projectile to root node.
            rootNode.attachChild(p.getNode());
            p.getNode().updateRenderState();
        } else {
            log.severe("Unknown projektil type:" + type);
        }
        return p;
    }*/

    /*public final Projectile createHomingMissile(final PlayerShip player) {
        MissileProjectile p = missileProjectilePool.get();
       
        // the homing missile is propelled by force and needs high
        // speed values to compensate the friction callback
        p.setSpeed(200000);
        p.getNode().setFriction(10);
       
        p.setTarget(player.getTargetDevice().getCurrentTarget());
        p.getNode().attachChild(missileCam.getCameraNode());
       
        rootNode.attachChild(p.getNode());
        p.getNode().updateRenderState();
        return p;
    }*/
}


