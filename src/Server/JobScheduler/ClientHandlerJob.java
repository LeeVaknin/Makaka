package Server.JobScheduler;

import ClientHandler.ClientHandler;
import ClientHandler.MyCHandler;

import java.io.*;
import java.net.Socket;


public class ClientHandlerJob implements Runnable {

    // Variables
    private Socket clientSocket;
    private InputStream input;
    private OutputStream output;
    private String request;
    private BufferedReader reader;


    // C-TOR
    public ClientHandlerJob(Socket cs) {
        this.clientSocket = cs;
        this.extractStreams();
        this.extractRequest();

    }

    // Methods

    public int getRequestSize() {
        String[] splitRequest = this.request.split("\n");
        return splitRequest[0].length() * splitRequest.length;
    }

    private void extractStreams() {
        try {
            this.input = this.clientSocket.getInputStream();
            this.output = this.clientSocket.getOutputStream();
        } catch (Exception ex) {
            System.out.println(String.join(": ", "ClientHandlerJob.extractStreams(): Error details", ex.getMessage()));
        }
    }

    private void extractRequest() {

        StringBuilder tmpRequest = new StringBuilder();
        String tmpLine;

        try {
            if (this.input != null) {
                this.reader = new BufferedReader(new InputStreamReader(this.input));
                while (!(tmpLine = this.reader.readLine()).equals("done")) {
                    tmpRequest = tmpRequest.append(tmpLine);
                    tmpRequest = tmpRequest.append(System.lineSeparator());
                }
                this.request = tmpRequest.toString();
            }
        } catch (IOException exception) {
            System.out.println(String.join(": ","ClientHandlerJob.extractRequest(): Failed to read client request. Error details" , exception.toString()));
        }
    }

    @Override
    public void run() {
        try {
            ClientHandler ch = new MyCHandler();
            ch.handle(this.output, this.request);
//            System.out.println("Closing socket: " + clientSocket);
            clientSocket.close();
            this.reader.close();

        } catch (Exception ex) {
            System.out.println(String.join(": ", "ClientHandlerJob.run(): Error details", ex.getMessage()));
        }

    }

}
