package server;

import java.io.IOException;

// T - client handler
public interface Server <T> {

    /////////
    //Methods
    /////////

    public void start() throws IOException;
    public void stop();


}
