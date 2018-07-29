package ClientHandler;
import Board.Solution;
import Board.Step;
import CacheManager.CacheManager;
import Solver.Solver;
import State.State;
import Utils.HashManager;
import org.jetbrains.annotations.Nullable;
import java.io.*;

// T is the board type, P is the position type
public class MyCHandler<T, P> implements ClientHandler {

    private Solver<T, P> solver;
    private CacheManager<P> cacheManager;

    public MyCHandler(Solver<T, P> solver, CacheManager<P> cacheManager) {
        this.solver = solver;
        this.cacheManager = cacheManager;
    }

    @Override
    public void handle(InputStream inFromClient, OutputStream outToClient) {
        try {
            String response;
            String request = this.readRequest(inFromClient);
            String problemId = HashManager.getId(request);
            response = cacheManager.loadSolution(problemId);
            // Check if we have saved solution to our problem
            if (response == null) {
                Solution<P> solution  = this.solver.solve(request);
                this.cacheManager.saveSolution(problemId, solution);
            }
            this.writeResponse(response, outToClient);

        } catch (IOException exception) {
            System.out.println(String.join("; ", "ERROR: Failed to close connection", exception.toString()));
        }
    }

    @Nullable
    private String readRequest(InputStream inFromClient) {

        String request = "";
        String tmpLine;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inFromClient))) {

            while (!(tmpLine = reader.readLine()).equals("done")) {
                request = request.concat(tmpLine);
            }
            return request;
        } catch (IOException exception) {
            System.out.println(exception.toString());
        }

        System.out.println("ERROR: Failed to read client request.");
        return null;

    }

    private void writeResponse(String response, OutputStream outFromClient) {
        try (OutputStreamWriter writer = new OutputStreamWriter(outFromClient)) {
            writer.write(response);
        } catch (IOException exception) {
            System.out.println(String.join("; ", "Failed to write response to client", exception.toString()));
        }
    }
}
