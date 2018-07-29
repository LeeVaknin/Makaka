
import Algorithms.BestFirstSearch;
import Board.MatrixBoard;
import Board.Position;
import CacheManager.FileManager;
import ClientHandler.MyCHandler;
import Server.MyServer;
import Solver.PipeGameSolver;

import java.io.IOException;

// TODO: add logger to the project
public class Main {
    public static void main(String[] args) {

        int port = 1993;
        BestFirstSearch<MatrixBoard, Position> searcher = new BestFirstSearch<>();
        PipeGameSolver solver = new PipeGameSolver(searcher);
        FileManager cacheManager = new FileManager();
        MyCHandler myClientHandler = new MyCHandler(solver, cacheManager);
        MyServer server = new MyServer(port, myClientHandler);


        Thread serverThread = new Thread(()-> {
            server.start();
        });

    }
}
