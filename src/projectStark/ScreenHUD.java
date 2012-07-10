package projectStark;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;


/*      
 *      @author Viorel Iliescu      *
                                    */

public class ScreenHUD extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Screen screen;
    private SimpleApplication App;
    
    // Debug Puropuses to test Score inc/dec
    private int score = 0;
    
    public ScreenHUD(String data, SimpleApplication OrigApp) {
        App = OrigApp;
    }

    ScreenHUD(String string) {
        
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
    
    //TODO ScreenHUD ripped from ScreenStart for reffrence, to add clickables, ship menu, etc
    //TODO Allso make HUD load from seperate xml not from same as mainMenu.xml
    public void startGame(String nextScreen) {
        //temp
        nifty.gotoScreen(nextScreen);
    }
    
    public void quitGame() {
        //temp
        App.stop();
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
    }
    
    @Override
    public void update(float tpf) {
        
    }
    
    // Testing to see if i get co-ordinates if clicked on top right squar ein HUD (Will be MINIMAP?
    public void clicked(int x, int y) {
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
    }
    
    public void testButton(){
        System.out.println("HUD Button Test");
    }
    
    public void incButton(){
        System.out.println("Debug Increase Score + 10");
        incScore(10);
        Element niftElement = nifty.getCurrentScreen().findElementByName("score");
        niftElement.getRenderer(TextRenderer.class).setText("Score "+score);
    }

    public int getScore() {
        return score;
    }

    public void incScore(int incAmount) {
        this.score += incAmount;
    }
    
    public void decScore(int decAmount) {
        this.score -= decAmount;
    }
    
}