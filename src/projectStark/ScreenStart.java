package projectStark;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class ScreenStart extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Screen screen;
    private SimpleApplication App;
    
    public ScreenStart(String data, SimpleApplication OrigApp) {
        App = OrigApp;
    }
    
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
        System.out.println("bind( " + screen.getScreenId() + ")");
    }
    
    public void onStartScreen() {
        
    }
    
    public void onEndScreen() { 
        
    }
    
    public void startGame() {
        //TODO This needs to call the seperate screen not form same screen! xml
        //nifty.fromXml("Interface/screenSelectProfile.xml", "screenSelectProfile", new ScreenSelectProfile("screenSelectProfile", App));
        nifty.addXml("Interface/screenSelectProfile.xml");
        System.out.println("Going to Profile Selection!");
        nifty.gotoScreen("screenSelectProfile");
    }
    
    public void goToMLobby() {
        //TODO This needs to call the seperate screen not form same screen! xml
        //nifty.fromXml("Interface/screenMultiplayer.xml", "screenMultiplayer", new ScreenHUD("screenMultiplayer", App));
        nifty.addXml("Interface/screenMultiplayer.xml");
        System.out.println("Going to Multiplayer Lobby!");
        nifty.gotoScreen("screenMultiplayer");
    }
    
    public void quitGame() {
        App.stop();
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
    }
    
    @Override
    public void update(float tpf) {
        /** jME update loop! */
        
    }
}