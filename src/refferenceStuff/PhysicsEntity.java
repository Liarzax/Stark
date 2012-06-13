/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refferenceStuff;

/**
 *
 * @author Viorel Iliescu
 */
import com.bulletphysics.collision.shapes.CollisionShape;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
//import com.jmex.jbullet.nodes.PhysicsNode;

/**
 * Arrghhh ugly, need better class design.  :(
 * @author Christoph Luder
 */
public class PhysicsEntity {//extends Entity {
        
    public PhysicsEntity(PhysicsSpace space, String modelName, float scale, boolean dynamic, boolean trianglAccurate) {
        //super(modelName, scale);
        CollisionShape shape = null;
        
        // hack
        /*if (modelName != null && modelName.contains("xwing")) {
                //model.setModelBound(new BoundingBox());
                //model.updateModelBound();
                //model.updateWorldBound();
        }*/
        
        /*if (dynamic) {
                if (model.getWorldBound() instanceof BoundingBox) {
                        shape = new BoxCollisionShape((BoundingBox)model.getWorldBound());
                        node = new PhysicsNode(model, shape);
                } else {
                        node = new PhysicsNode(model);
                } 
        } else {
                node = new PhysicsNode(model);
                node.setMass(0);
        }*/
        //node.setName("physic node:" +modelName);
        //node.attachChild(model);
        
        //space.add(node);
        
        //initNode();
        
        //EntityManager.get().addEntity(this);
    }
    
    /*public PhysicsEntity(PhysicsSpace space, String modelName, float scale, boolean dynamic) {
        this(space, modelName, scale, dynamic, false);
    }
    
    protected void initNode () {
        
    }*/
        
}

