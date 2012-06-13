package projectStark;

import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.renderer.Camera;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.xml.xpp3.Attributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import jme3tools.converters.ImageToAwt;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.controls.ActionListener;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/*      
 *      @author Viorel Iliescu      *
                                    */

// TODO: Make loading screen work!
// Note: This whole class is not working and is unused. this class controlls the LOADING interface.
public class LOADING extends AbstractAppState implements ScreenController {
    
    private Screen screen;
    private NiftyJmeDisplay niftyDisplay;
    private Nifty nifty;
    private Element progressBarElement;
    private float frameCount = 0;
    private boolean load = false;
    private TextRenderer textRenderer;
 
    LOADING(String string, ActionListener aThis) {
                
    }
    
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }
    
    public void bind(Nifty nifty, Screen screenParam, Element element, Properties parameter, Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screenParam;
    }

    public void onStartScreen() {
        
    }

    public void onFocus(final boolean getFocus) {
        
    }
    
    public void onEndScreen() {
        
    }
    
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        
        return false;
    }
    
    public void init(Properties parameter, Attributes controlDefinitionAttributes) {
        
    }
    
    public void showLoadingMenu(String nextScreen){
        System.out.println("Next Screen!");
        nifty.gotoScreen(nextScreen);
    }
    
    
    // ALL REFFERENCE STUFF BELOW HERE, NONE SHOULD BE UN-COMMENTED OR USED
    /** Nifty GUI ScreenControl methods */
    /*public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
        progressBarElement = nifty.getScreen("loadlevel").findElementByName("progressbar");
    }
    
    public void bind(Nifty nifty, Screen screen, Element elmnt, Properties prprts, Attributes atrbts) {
        progressBarElement = elmnt.findElementByName("progressbar");
    }
    
    public void onStartScreen() {
        //System.out.println("Opening Menu");
    }
    
    public void onEndScreen() { 
        //System.out.println("Closing Menu");
    }
    
    public void loadingUpdate() {
 
        if (load) { //loading is done over many frames
                if (frameCount == 1) {
                    Element element = nifty.getScreen("loadlevel").findElementByName("loadingtext");
                    textRenderer = element.getRenderer(TextRenderer.class);
                    
                    System.out.println("Frame 1 - Loading Grass Texture");
                    setProgress(0.2f, "Loading grass");
                    
                } else if (frameCount == 2) {
                    System.out.println("Frame 2 - Loading Dirt Texture");
                    setProgress(0.4f, "Loading dirt");
                    
                } else if (frameCount == 3) {
                    System.out.println("Frame 3 - Loading Rocks Texture");
                    setProgress(0.5f, "Loading rocks");
                    
                } else if (frameCount == 4) {
                    System.out.println("Frame 4 - Generating Terrain");
                    setProgress(0.6f, "Creating terrain");
                    
                } else if (frameCount == 5) {
                    System.out.println("Frame 5 - Positioning Terrain (Using Height Map)");
                    setProgress(0.8f, "Positioning terrain");
                    
                } else if (frameCount == 6) {
                    System.out.println("Frame 6 - Loading Cammera Controlls");;
                    setProgress(0.9f, "Loading cameras");
                    
                } else if (frameCount == 7) {
                    System.out.println("Frame 7 - Loading Player, Enemies");
                    setProgress(1f, "Loading complete");
                    
                } else if (frameCount == 8) {
                    System.out.println("End - Loading Controlls?");
                    nifty.gotoScreen("hud");
                    //nifty.exit();
                    //guiViewPort.removeProcessor(niftyDisplay);
                    //flyCam.setMoveSpeed(50);
                }
                
                frameCount++;
            }
    }
 
    public void setProgress(final float progress, String loadingText) {
        final int MIN_WIDTH = 32;
        int pixelWidth = (int) (MIN_WIDTH + (progressBarElement.getParent().getWidth() - MIN_WIDTH) * progress);
        progressBarElement.setConstraintWidth(new SizeValue(pixelWidth + "px"));
        progressBarElement.getParent().layoutElements();
 
        textRenderer.setText(loadingText);
    }
 
    public void showLoadingMenu() {
        nifty.gotoScreen("loadlevel");
        load = true;
    }
    
    // methods for Controller
    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
 
    
 
    @Override
    public void init(Properties prprts, Attributes atrbts) {
    }
 
    public void onFocus(boolean getFocus) {
    }
     * 
     * 
     */
}
