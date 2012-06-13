package networking.local;

import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class NetworkManager  implements ClientStateListener, ConnectionListener {
    
    //TODO Add all the network stuff in here without it EXPLODING!
    
    public NetworkManager(){
        
    }

    public void clientConnected(Client c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clientDisconnected(Client c, DisconnectInfo info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void connectionAdded(Server server, HostedConnection conn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void connectionRemoved(Server server, HostedConnection conn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /* Notes:
     * public join server, m
     * try
     * client = network conecttoserver local host stuff
     * getclient.start
     * get client . add message listener
     * get client . add client state listener(this)
     * catch
     * logger exception thing
     * 
     */
    
    // create message object, then getClient().send(message);
    
}
