package projectStark;

import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class ControlPlayer extends AbstractControl implements AnalogListener, ActionListener {
    //TODO Broken PLAYER CONTROLLS!, FIX LATER!!!!
    
    @Override
    protected void controlUpdate(float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onAnalog (String name, float value, float tpf) {

        if (name.equals("Thrust")) {                     // test?
            //thrusting = !thrusting;                    // action!
            //TODO: Add ability to 'thrust' to increase speed.
            System.out.println("Engines On");
        }

        if (name.equals("DeAccelerate")) {
            //TODO: add the ability to 'deaccelerate' to decrease speed
            System.out.println("DeAccelerating... Backwards");
        }
    }
    
    public void onAction(String name, boolean keyPressed, float tpf) {
        
    }
}


