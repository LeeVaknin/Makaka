
import CacheManager.FileManager;
import ClientHandler.MyCHandler;
import Searcher.PipeGameSearcher;
import Server.MyServer;
import Solver.PipeGameSolver;

import java.io.IOException;

// TODO: add logger to the project
public class Main {
    public static void main(String[] args) {

        int port = 1993;
        PipeGameSearcher searcher = new PipeGameSearcher();
        PipeGameSolver solver = new PipeGameSolver(searcher);
        FileManager cacheManager = new FileManager();
        MyCHandler myClientHandler = new MyCHandler(solver, cacheManager);
        MyServer server = new MyServer(port, myClientHandler);


        Thread serverThread = new Thread(()-> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
