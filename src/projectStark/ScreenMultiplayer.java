package projectStark;

import networking.local.NetworkMessage;
import com.jme3.app.state.AbstractAppState;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.textfield.TextFieldControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.local.ClientListener;
import networking.local.ServerListener;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class ScreenMultiplayer extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Screen screen;
    
    // Server Stuff!!
    Server myServer = null;
    Client myClient = null;

    ScreenMultiplayer(String string) {
        Serializer.registerClass(NetworkMessage.class);
    }
    
    public void startServer(){
        System.out.println("Start Server Button!!!!");
        
        System.out.println("Setting up Server");
        try {
            myServer = Network.createServer(6143);
            // Add all Listeners and Register Messages BEFORE Starting Server.
            myServer.addMessageListener(new ServerListener(myServer), NetworkMessage.class);
            myServer.start();
            System.out.println("Server Set Up Successfuly");
        } 
        catch (IOException ex) {
            System.out.println("Starting Server Failed...");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void joinServer(){
        System.out.println("Join Server Button!!!!!");
        
        System.out.println("Setting up Client");
        try {
            myClient = Network.connectToServer("localhost", 6143);
            // Add All the Listeners for my types of message, movement, chat, etc? before Startihng
            myClient.addMessageListener(new ClientListener(nifty), NetworkMessage.class);
            myClient.start();
            System.out.println("Client Set Up Successfuly");
            Message message = new NetworkMessage("CLIENT " +myClient.getId()+ " IS HERE!!!");
            myClient.send(message);
        } 
        catch (IOException ex) {
            System.out.println("No Server to Join...");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnectFromServer() {
        //... // custom code
        System.out.println("Terminating Connection to Server!");
        myClient.close();
    }
    
    public void stopServer() {
        //TODO 2 - BOOT EVERYONE AND CLOSE SERVER TYPE THING!
        System.out.println("Terminating Server!");
        myServer.close();
        
    }
    
    public void backButton() {
        //TODO go back to the start screen
        //nifty.fromXml("Interface/screenStart.xml", "screenStart", new ScreenHUD("screenStart"));
        nifty.addXml("Interface/screenStart.xml");
        System.out.println("Going Back!");
        nifty.gotoScreen("screenStart");
    }
    
    public void sendMessage() {
        System.out.println("Go Send Message Button!!!!!");
        
        // find old text
        Element niftElement = nifty.getCurrentScreen().findElementByName("chatBar");
        // get stuff u typed send that
        String typed = niftElement.getControl(TextFieldControl.class).getText();
        
        System.out.println("Sending Message");
        Message message = new NetworkMessage(typed);
        myClient.send(message);
        
        System.out.println("Message Sent to Server");
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
}
