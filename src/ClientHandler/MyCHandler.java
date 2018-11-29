package ClientHandler;

import Algorithms.BestFirstSearch;
import Algorithms.DFSSearcher;
import Board.MatrixBoard;
import Board.Position;
import Board.Solution;
import Board.Step;
import CacheManager.CacheManager;
import CacheManager.FileManager;
import Solver.PipeGameSolver;
import Solver.Solver;

import java.io.*;

// T is the board type, P is the position type
public class MyCHandler implements ClientHandler {

    //Variables
    private Solver<MatrixBoard, Position> solver;
    private CacheManager<Position> cacheManager;
    private PrintWriter writer;
    private String request;

    //C-TOR
//    public MyCHandler(Solver<T, P> solver, CacheManager<P> cacheManager) {
//        this.solver = solver;
//        this.cacheManager = cacheManager;
//    }

    public MyCHandler() {
        this.solver = new PipeGameSolver(new DFSSearcher<>());
        this.cacheManager = new FileManager<Position>();
    }

    // Methods

    @Override
    public void handle(OutputStream outToClient, String request) {

        this.writer = new PrintWriter(outToClient);

        if (request != null) {
//            System.out.println("Handle " + request);
            this.request = request;
            String problemId = String.valueOf(request.hashCode());
            try {
                // Check for existing solution
                Solution<Position> solution = this.cacheManager.loadSolution(problemId);
                // If doesn't exist, solve it and return the solution (and save for the next time it is requested)
                if (solution == null) {
                    solution = this.solver.solve(request);
                    this.cacheManager.saveSolution(problemId, solution);
                }
                this.writeResponse(solution);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.cleanUp();
    }

    private void writeResponse(Solution<Position> response) {
//        System.out.println("Started write");
        try {

            if (response != null && this.writer != null) {
                for (Step<Position> step : response.getSteps()) {
                    writer.println(step.toString());
                }
            }
        } catch (Exception ex) {
            System.out.println((String.join(": ", "MyCHandler.writeResponse(): Error details", ex.getMessage())));
        } finally {
            writer.println("done");
            writer.flush();
//            System.out.println("Done.");
        }
    }

    // Closes all open streams when finished handle
    private void cleanUp() {
        if (this.writer != null) {
            this.writer.close();
        }
    }
}
