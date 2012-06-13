package networking.local;

import com.jme3.network.ConnectionListener;
import com.jme3.network.Filters;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Server;

/*      
 *      @author Viorel Iliescu      *
                                    */

public class ServerListener implements MessageListener<HostedConnection>, ConnectionListener {
    Server server;
    
    public ServerListener (Server server) {
        this.server = server;
    }
    
    public void messageReceived(HostedConnection source, Message message) {
        if (message instanceof NetworkMessage) {
            // do something with the message
            NetworkMessage helloMessage = (NetworkMessage) message;
            System.out.println("Server received '" +helloMessage.getNetworkMessage() +"' from client #"+source.getId() );
            
            Message servMessage = new NetworkMessage("Message Recived...");
            server.broadcast( Filters.in( source ), servMessage );
            server.broadcast( Filters.notEqualTo( source ), helloMessage );
            
            // Notes:
            //server.broadcast(message);
            //server.broadcast(servMessage);
            
            //Or the server can send the message to a specific subset of clients (e.g. to HostedConnection conn1, conn2, and conn3): 
            //**server.broadcast( Filters.in( conn1, conn2, conn3 ), message );
            //Or the server can send the message to all but a few selected clients (e.g. to all HostedConnections but conn4): 
            //**myServer.broadcast( Filters.notEqualTo( conn4 ), message );
            
            
        } 
        // Other Messages....
    }

    public void connectionAdded(Server server, HostedConnection conn) {
        server.addConnectionListener(this);
    }

    public void connectionRemoved(Server server, HostedConnection conn) {
        
    }
}
