package Server;
import ClientHandler.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class MyServer implements Server {
    static int port = 32;
    private ServerSocket serverSocket;
    private boolean stop = false;

    public MyServer(int port) {
        this.port = port;
    }

    @Override
    public void start(ClientHandler clientHandler) {
        new Thread(() -> {
            try {
                activate(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private void activate(ClientHandler clientHandler) throws IOException {
        serverSocket = new ServerSocket(MyServer.port);
        serverSocket.setSoTimeout(1000);
        System.out.println("The server is up.");

        while (!this.stop) {
            try {
                Socket aClient = serverSocket.accept();
                System.out.println("Accepted connection.");
                clientHandler.handle(aClient.getInputStream(), aClient.getOutputStream());
                aClient.close();
            } catch (SocketTimeoutException e) {
            }
        }
        serverSocket.close();
    }

    @Override
    public void stop() {
        stop = true;
    }
}