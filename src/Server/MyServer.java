package Server;

import ClientHandler.MyCHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer implements Server {

    private int port;
    private MyCHandler myCHandler;
    private volatile boolean stop;

    // C-TOR

    //TODO: should be MyCHandler or ClientHandler as DI?
    public MyServer(int port, MyCHandler myCHandler) {
        this.port = port;
        this.myCHandler = myCHandler;
        stop = false;
    }

    // Methods

    @Override
    public void start() throws IOException {

        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);
        while (!stop) {
            try {
                Socket aClient = server.accept(); // blocking call
                try {
                    myCHandler.handle(aClient.getInputStream(), aClient.getOutputStream());
                    aClient.getInputStream().close();
                    aClient.getOutputStream().close();
                    aClient.close();
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            } catch (SocketTimeoutException e) {
                System.out.println(e.toString());
            }
        }
        server.close();
    }



    @Override
    public void stop() {
        stop = true;
    }
}
