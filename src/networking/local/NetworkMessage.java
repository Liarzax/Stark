package networking.local;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import com.jme3.network.serializing.Serializer;

/*      
 *      @author Viorel Iliescu      *
                                    */

@Serializable
public class NetworkMessage extends AbstractMessage {
    
    // custom message data
    private String hello;       
    
    // empty constructor
    public NetworkMessage() {
        
     }    
    
    // custom constructor
    public NetworkMessage(String s) { 
        hello = s; 
    }

    public String getNetworkMessage() {
        return hello;
    }
}
