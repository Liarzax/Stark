package networking.local;

import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.textfield.TextFieldControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class ClientListener implements MessageListener<Client>, ClientStateListener {
    Nifty nifty;
    
    public ClientListener(Nifty nifty) {
        // TODO Constructor, send in the Nifty, 
        //and that way i can use the nifty to find and get and update stuiff!! for the clients
        this.nifty = nifty;
    }
    
  public void messageReceived(Client source, Message message) {
    if (message instanceof NetworkMessage) {
      // do something with the message
      NetworkMessage helloMessage = (NetworkMessage) message;
      System.out.println("Client #"+source.getId()+" received: '"+helloMessage.getNetworkMessage()+"' from the Server");
      
      // TODO 1 - WDF? BROKEN CHATTING! - FIX HERE FIRST!!!!!!!!
      // find old text
      Element niftElement = nifty.getCurrentScreen().findElementByName("chatField");
      //String fromServer = niftElement.toString();
      // get stuff u typed send that
      //BROKEN! -> niftElement.getRenderer(TextRenderer.class).setText(message.toString());
      //niftElement.getRenderer(TextRenderer.class).setText(fromServer);
    } 
    // Other Messages here?, MovementMessage, UpdateMessage, etc or something?
  }

    public void clientConnected(Client c) {
        System.out.println("Welcome! to the Server...");
        c.addClientStateListener(this);
    }

    public void clientDisconnected(Client c, DisconnectInfo info) {
        //conn.close("We kick cheaters.");
        System.out.println("You have been Disconnected from the Server...");
    }
}
