package ClientHandler;

import CacheManager.CacheManager;
import Models.*;
import Searchable.PipeSearchable;
import Searchable.Searchable;
import Solver.Solver;
import org.jetbrains.annotations.Nullable;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.util.ArrayList;

public class MyCHandler<T> implements ClientHandler{

    Solver<Board<T>, Step> solver;
    CacheManager<T> cacheManager;

    public MyCHandler(Solver<Board<T>, Step> solver, CacheManager<T> cacheManager) {
        this.solver = solver;
        this.cacheManager = cacheManager;
    }

    @Override
    public void handle(InputStream inFromClient, OutputStream outToClient) {
        try {
            OutputStreamWriter writer = null;
            Solution<Step> response;
            String request = this.readRequest(inFromClient);
            MatrixBoard tmpBoard = new MatrixBoard(request);

            response = cacheManager.loadSolution(tmpBoard.getId());
            // Check if we have saved solution to our problem
            if(response == null) {
               response = solve(tmpBoard);
            }
            this.writeResponse(response, outToClient);

        } catch (IOException exception) {
            System.out.println(String.join("; ", "ERROR: Failed to close connection", exception.toString()));
        }
    }

    private Solution<Step> solve(MatrixBoard gameBoard) {
        State<MatrixBoard> state = new State<>(gameBoard);
        Searchable<MatrixBoard> searchable = new PipeSearchable(state);
        Solution<Step> solution = this.solver.solve(searchable);
        this.cacheManager.saveSolution(gameBoard, solution);
        return solution;
    }

    @Nullable
    private String readRequest(InputStream inFromClient) throws IOException {

        BufferedReader reader = null;
        String request = "";
        String tmpLine = "";

        try {
            reader = new BufferedReader (new InputStreamReader(inFromClient));

           while (!(tmpLine =  reader.readLine()).equals("done")) {
               request = request.concat(tmpLine);
           }
            return request;
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
            System.out.println(String.join("; ", "Failed to write response to client", exception.toString()));
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
