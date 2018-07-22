package Server;

import java.io.IOException;

// T - client handler
public interface Server <T> {

    /////////
    //Methods
    /////////

    void start() throws IOException;
    void stop();


}
