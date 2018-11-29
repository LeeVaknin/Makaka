package Server;

import Server.JobScheduler.ClientHandlerJob;
import Server.JobScheduler.PriorityJobScheduler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class MyServer implements Server {

    // Variables
    static int port = 32;
    private ServerSocket serverSocket;
    private boolean stop = false;

    // C-TOR
    public MyServer(int port) {
        this.port = port;
    }


    // Methods

    @Override
    public void start() {

        new Thread(() -> {
            try {
                activate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void activate() throws IOException {
        Integer poolSize = 6;
        Integer queueSize = 100;
        serverSocket = new ServerSocket(MyServer.port);
        serverSocket.setSoTimeout(100000);
        System.out.println("The server is up.");

        PriorityJobScheduler jobScheduler = new PriorityJobScheduler(poolSize,queueSize);

        while (!this.stop) {
            try {
                Socket aClient = serverSocket.accept();
                ClientHandlerJob chJob = new ClientHandlerJob(aClient);
                jobScheduler.scheduleJob(chJob);
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
        jobScheduler.close();
        serverSocket.close();
    }

    @Override
    public void stop() {
        stop = true;
        System.out.println("The server is down.");
    }
}