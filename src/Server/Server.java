package Server;

import ClientHandler.ClientHandler;

import java.io.IOException;

// T - client handler
public interface Server <T> {

    /////////
    //Methods
    /////////

    void start();
    void stop();


}
