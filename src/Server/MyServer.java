package Server;
import ClientHandler.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class MyServer implements Server {

    private int port;
    private volatile boolean stop;

    // C-TOR
    public MyServer(int port) {
        this.port = port;
        stop = false;
    }

    // Methods

    private void Activate(ClientHandler clientHandler) throws IOException {
        System.out.println("Starting server");
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(5000);
        System.out.println("Server started.");
        while (!stop) {
            try {
                Socket aClient = server.accept();
                System.out.println("Client connected:" + aClient.toString());
                clientHandler.handle(aClient.getInputStream(), aClient.getOutputStream());
                aClient.close();
                System.out.println("Connection was closed.");
            } catch (IOException e) {
            }
            try {
                server.close();
            } catch (IOException e) {
                System.out.println("Failed to close server" + e.toString());
            }
        }
    }

    @Override
    public void start(ClientHandler clientHandler) {
        new Thread(() -> {
            try {
                Activate(clientHandler);
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
