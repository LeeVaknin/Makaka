package Server;

import ClientHandler.ClientHandler;
import ClientHandler.MyCHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer implements Server {

    private int port;
    private ClientHandler myCHandler;
    private volatile boolean stop;

    // C-TOR
    public MyServer(int port, ClientHandler myCHandler) {
        this.port = port;
        this.myCHandler = myCHandler;
        stop = false;
    }

    // Methods

    public void Activate() throws IOException {

        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);
        while (!stop) {
            try {
                Socket aClient = server.accept(); // blocking call
                try {
                    myCHandler.handle(aClient.getInputStream(), aClient.getOutputStream());
                    aClient.getInputStream().close();
                    aClient.getOutputStream().close();
                } catch (IOException e) {
                    System.out.println(e.toString());
                } finally {
                    if (aClient != null)
                        aClient.close();
                }
            } catch (SocketTimeoutException e) {
                System.out.println(e.toString());
            } finally {
                try {
                    server.close();
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    public void start() {
        new Thread(() -> {
            try {
                Activate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void stop() {
        stop = true;
    }
}
