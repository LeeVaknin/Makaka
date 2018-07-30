package test;

import Algorithms.BFSSearcher;
import Algorithms.BestFirstSearch;
import Board.Solution;
import Board.Step;
import CacheManager.CacheManager;
import CacheManager.FileManager;
import ClientHandler.ClientHandler;
import ClientHandler.MyCHandler;
import Algorithms.Searcher;
import Server.MyServer;
import Server.Server;
import Solver.PipeGameSolver;
import Solver.Solver;
import Searchable.Searchable;
import Searchable.PipeSearchable;

import java.util.ArrayList;
import java.util.List;


public class TestSetter {

    public static void setClasses(test.DesignTest dt) {

        // set the server's Interface, e.g., "Server.class"
        // don't forget to import the correct package e.g., "import server.Server"
        dt.setServerInteface(Server.class);
        // now fill in the other types according to their names
        // server's implementation
        dt.setServerClass(MyServer.class);
        // client handler interface
        dt.setClientHandlerInterface(ClientHandler.class);
        // client handler class
        dt.setClientHandlerClass(MyCHandler.class);
        // cache manager interface
        dt.setCacheManagerInterface(CacheManager.class);
        // cache manager class
        dt.setCacheManagerClass(FileManager.class);
        // solver interface
        dt.setSolverInterface(Solver.class);
        // solver class
        dt.setSolverClass(PipeGameSolver.class);
        // searchable interface
        dt.setSearchableInterface(Searchable.class);
        // searcher interface
        dt.setSearcherInterface(Searcher.class);
        // your searchable pipe game class
        dt.setPipeGameClass(PipeSearchable.class);
        // your Best First Search implementation
        dt.setBestFSClass(BestFirstSearch.class);
    }

    // run your server here
    static Server s;

    public static void runServer(int port) {
        s = new MyServer(port);
        s.start(new MyCHandler());
    }

    // stop your server here
    public static void stopServer() {
        s.stop();
    }

    /* ------------- Best First Search Test --------------
     * You are given a Maze
     * Create a new Searchable from the Maze
     * Solve the Maze
     * and return a list of moves from {UP,DOWN,RIGHT,LEFT}
     *
     */

    public static List<String> solveMaze(Maze m) {
        MazeSearchable s = new MazeSearchable(m);
        BFSSearcher searcher = new BFSSearcher();
        Solution solution = searcher.search(s);
        ArrayList<String> ret = new ArrayList<String>();
        for (Object mazeStep : solution.getSteps())
        {
            MazeStep move = (MazeStep)mazeStep;

            for(String step : move.directions) {
                ret.add(step);
            }

        }
        return ret;
    }

}
