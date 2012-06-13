package projectStark;

import networking.local.NetworkMessage;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.Server;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/*      
 *      @author Viorel Iliescu          *
 * Debugging Screen for Ingame Testing  *
 *                                      */

public class ScreenOMGTAB extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Screen screen;
    private SimpleApplication App;
    
    //TODO More functionality to this screen for testing and stuff
    ScreenOMGTAB(String string, ActionListener aThis) {
                
    }
    
    /** Nifty GUI ScreenControl methods */
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
        System.out.println("bind( " + screen.getScreenId() + ")");
    }
    
    public void onStartScreen() {
        //System.out.println("Opening Menu");
    }
    
    public void onEndScreen() { 
        //System.out.println("Closing Menu");
    }
    
    //TODO make menu buttons and stuff to use and test with here
    //TOD Allso make HUD load from seperate xml not from same as mainMenu.xml
    public void tempButton() {
        //temp or testing button
        // find old text
        Element niftElement = nifty.getCurrentScreen().findElementByName("text");
        //replace old text with new text
        niftElement.getRenderer(TextRenderer.class).setText("TEMP!");
        //nifty.gotoScreen(nextScreen);
        System.out.println("temp temp temp temp");
    }

    public void upButton() {
        //temp
        // find old text
        Element niftElement = nifty.getCurrentScreen().findElementByName("text");
        //replace old text with new text
        niftElement.getRenderer(TextRenderer.class).setText("UP!");
        
        System.out.println("UP");
    }
    
    public void downButton() {
        //temp
        // find old text
        Element niftElement = nifty.getCurrentScreen().findElementByName("text");
        //replace old text with new text
        niftElement.getRenderer(TextRenderer.class).setText("DOWN!");
        
        System.out.println("DOWN");
    }    
    
    
    public void closeMenu() {
        //TODO ADD WONT GO BACK TO HUD for some reason!!! FIX IT!.
        //nifty.fromXml("Interface/screenHUD.xml", "screenHUD", new ScreenHUD("screenHUD", App));
        nifty.addXml("Interface/screenHUD.xml");
        System.out.println("Opening Debug Menu! [TAB]");
        nifty.gotoScreen("screenHUD");
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
    }
    
    @Override
    public void update(float tpf) {
        /** jME update loop! */
        
    }
}