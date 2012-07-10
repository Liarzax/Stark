package projectStark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/*      
 *      @author Viorel Iliescu          *
 * Debugging Screen for Ingame Testing  *
 *                                      */

public class ScreenSelectProfile extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Screen screen;
    private SimpleApplication App;
    
    // 0 = no profile, then 1, 2, 3, 4
    private int currentProfile = 0;
    
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
        // if profile is empty, create new profile
        //else if profile is not empty, display confirm create, then create
        
        // temp using this as a save class
        FileOutputStream outStream = null;
        ObjectOutputStream objectOutputFile = null;
        
        try {
            outStream = new FileOutputStream("savedProfile.dat");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScreenSelectProfile.class.getName()).log(Level.SEVERE, null, ex);
            // TODO: cant write file error handling!
        }
        
        try {
            objectOutputFile = new ObjectOutputStream(outStream);
        } catch (IOException ex) {
            Logger.getLogger(ScreenSelectProfile.class.getName()).log(Level.SEVERE, null, ex);
            // error again?
        }
        
        //test player
        Entity tester = new Entity(0, 0);
        try {
            objectOutputFile.writeObject(tester);
        } catch (IOException ex) {
            Logger.getLogger(ScreenSelectProfile.class.getName()).log(Level.SEVERE, null, ex);
            // error cant write stuff handling
        }
        
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
    
    public void selectProfile(int profile) {
        currentProfile = profile;
        
        System.out.println("Profile "+currentProfile+" Selected!");
    }
    
    /*public void selectProfile1() {
        currentProfile = 1;
        
        System.out.println("Profile "+currentProfile+" Selected!");
    } 
    
    public void selectProfile2() {
        currentProfile = 2;
        
        System.out.println("Profile "+currentProfile+" Selected!");
    } 
    
    public void selectProfile3() {
        currentProfile = 3;
        
        System.out.println("Profile "+currentProfile+" Selected!");
    } 
    
    public void selectProfile4() {
        currentProfile = 4;
        
        System.out.println("Profile "+currentProfile+" Selected!");
    } */
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
    }
    
    @Override
    public void update(float tpf) {
        /** jME update loop! */
        
    }
}