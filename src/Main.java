
import ClientHandler.MyCHandler;
import Server.MyServer;

import java.io.IOException;

// TODO: add logger to the project
public class Main {
    public static void main(String[] args) {

        int port = 1993;
        MyCHandler myClientHandler = new MyCHandler();
        MyServer server = new MyServer(port, myClientHandler);

        Thread serverThread = new Thread(()-> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
