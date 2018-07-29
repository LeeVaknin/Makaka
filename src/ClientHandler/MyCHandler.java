package ClientHandler;

import Algorithms.BestFirstSearch;
import Board.MatrixBoard;
import Board.Position;
import Board.Solution;
import Board.Step;
import CacheManager.CacheManager;
import CacheManager.FileManager;
import Solver.PipeGameSolver;
import Solver.Solver;
import State.State;
import Utils.HashManager;
import org.jetbrains.annotations.Nullable;

import java.io.*;

// T is the board type, P is the position type
public class MyCHandler implements ClientHandler {

    private Solver<MatrixBoard, Position> solver;
    private CacheManager<Position> cacheManager;

    //    public MyCHandler(Solver<T, P> solver, CacheManager<P> cacheManager) {
//        this.solver = solver;
//        this.cacheManager = cacheManager;
//    }
//
    public MyCHandler() {
        this.solver = new PipeGameSolver(new BestFirstSearch<>());
        this.cacheManager = new FileManager<>();
    }

    @Override
    public void handle(InputStream inFromClient, OutputStream outToClient) {
        String response;
        String request = this.readRequest(inFromClient);
        if (request != null) {
            String problemId = String.valueOf(request.hashCode());
            try {
                response = cacheManager.loadSolution(problemId);
                if (response == null) {
                    Solution<Position> solution = this.solver.solve(request);
                    this.cacheManager.saveSolution(problemId, solution);
                }
                this.writeResponse(response, outToClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Check if we have saved solution to our problem


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
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(outFromClient))) {
            writer.println(response);
            writer.println("done");
        }
    }
}
