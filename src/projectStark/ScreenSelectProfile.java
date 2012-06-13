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

public class ScreenSelectProfile extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Screen screen;
    private SimpleApplication App;
    
    //TODO More functionality to this select profile screen
    ScreenSelectProfile(String string, ActionListener aThis) {
                
    }

    ScreenSelectProfile(String string) {
        
    }
    
    public ScreenSelectProfile(String data, SimpleApplication OrigApp) {
        App = OrigApp;
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
    public void selectButton() {
        // TODO put in an if statement to see if a profile is selected, then if its valid, then OK, else NO!
        //nifty.fromXml("Interface/screenHUD.xml", "screenHUD", new ScreenHUD("screenHUD", App));
        nifty.addXml("Interface/screenHUD.xml");
        System.out.println("Profile Selected, Start Game!");
        nifty.gotoScreen("screenHUD");
    }

    public void newButton() {
        // find old text
        //Element niftElement = nifty.getCurrentScreen().findElementByName("text");
        //replace old text with new text
        //niftElement.getRenderer(TextRenderer.class).setText("UP!");
        
        System.out.println("Create New Profile!");
    }
    
    public void deleteButton() {
        //TODO: have an if statement to see if a profile is selected, then deletem else SELECT A PROFILE!
        
        System.out.println("Delete Selected Profile!");
    }    
    
    
    public void backButton() {
        //TODO go back to the start screen
        //nifty.fromXml("Interface/screenStart.xml", "screenStart", new ScreenHUD("screenStart"));
        nifty.addXml("Interface/screenStart.xml");
        System.out.println("Going Back!");
        nifty.gotoScreen("screenStart");
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
    }
    
    @Override
    public void update(float tpf) {
        /** jME update loop! */
        
    }
}