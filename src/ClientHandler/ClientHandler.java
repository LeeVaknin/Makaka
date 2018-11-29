package ClientHandler;

import java.io.OutputStream;

public interface ClientHandler {

    void handle(OutputStream outToClient, String request);

}
