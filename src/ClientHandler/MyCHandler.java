package ClientHandler;

import CacheManager.CacheManager;
import Solver.Solver;
import org.jetbrains.annotations.Nullable;
import sun.nio.ch.IOUtil;

import java.io.*;

public class MyCHandler<T,S> implements ClientHandler <T,S> {

    Solver<T,S> solver;
    CacheManager<T> cacheManager;

    public MyCHandler(Solver<T, S> solver,
                      CacheManager<T> cacheManager) {

        this.solver = solver;
        this.cacheManager = cacheManager;
    }

    @Override
    public void handle(InputStream inFromClient, OutputStream outToClient) {

        try {
            OutputStreamWriter writer = null;
            String request = this.readRequest(inFromClient);
            String response = "";
            this.writeResponse(response, outToClient);

        } catch (IOException exception) {
            System.out.println(String.join("; ", "ERROR: Failed to close connection" , exception.toString()));
        }
    }


    @Nullable
    private String readRequest(InputStream inFromClient) throws IOException {

        InputStreamReader reader = null;
        char[] request = null;

        try {
            reader = new InputStreamReader(inFromClient);
            reader.read(request);
            return String.copyValueOf(request);

        } catch (IOException exception) {
            System.out.println(exception.toString());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        System.out.println("ERROR: Failed to read client request.");
        return null;

    }

    @Nullable
    private void writeResponse(String response, OutputStream outFromClient) throws IOException {

        OutputStreamWriter writer = null;

        try {
            writer = new OutputStreamWriter(outFromClient);
            writer.write(response);
        } catch (IOException exception) {
            System.out.println(String.join("; ", "Failed to write response to client", exception.toString()) );
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
