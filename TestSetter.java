package test;

//import test.Grid;

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
import State.State;
import Board.Step;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class TestSetter {

    public static void setClasses(DesignTest dt) {


        try {
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
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    static Server s;

    public static void runServer(int port) {
        System.out.println("Trying to run server.");
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

        class MazeStep extends Step<Grid> {

            public MazeStep(Grid pos, ArrayList<String> dirs) {
                this.directions = dirs;
                this.position = pos;
            }

            public ArrayList<String> directions;

            @Override
            public void setPosition(Grid position) {
                this.position = position;
            }

            @Override
            public String toString() {
                return String.join(",", this.directions);
            }
        }

        class MazeGameState extends State<Maze, Grid> {

            //CTOR
            public MazeGameState(Maze state) {
                this.setState(state);
                this.setCurrentPosition(null);
                this.setStep(null);
            }

            public MazeGameState(State<Maze, Grid> mazeGameState) {
                if (mazeGameState != null) {
                    this.setState(mazeGameState.getState());
                    this.setCameFrom(mazeGameState.getCameFrom());
                    this.setCurrentPosition(mazeGameState.getCurrentPosition());
                    this.setStep(mazeGameState.getStep());
                }
            }

            @Override
            public void setState(Maze state) {
                if (state != null) {
                    this.state = state;
                }
            }

            //TODO:go back to this one
            @Override
            public double generateCost() {
                return 0;
            }

            @Override
            public ArrayList<State<Maze, Grid>> getAllNeighbors() {
                ArrayList<State<Maze, Grid>> neighbors = new ArrayList<State<Maze, Grid>>();
                List<Grid> moves = this.state.getPossibleMoves(this.getCurrentPosition());
                for (Grid move : moves) {
                    State<Maze, Grid> neighbor = new MazeGameState(this);
                    ArrayList<String> dirs = new ArrayList<>();
                    if (move.col > this.getCurrentPosition().col) {
                        dirs.add("RIGHT");
                    }
                    if (move.col < this.getCurrentPosition().col) {
                        dirs.add("LEFT");
                    }
                    if (move.row > this.getCurrentPosition().row) {
                        dirs.add("DOWN");
                    }
                    if (move.row < this.getCurrentPosition().row) {
                        dirs.add("UP");
                    }

                    Step<Grid> newStep = new MazeStep(move, dirs);
                    neighbor.setStep(newStep);
                    neighbor.setCurrentPosition(move);
                    neighbors.add(neighbor);
                }
                return neighbors;
            }

            @Override
            protected void updateState(Step<Grid> step) {
                this.setStep(step);
                this.setCurrentPosition(step.getPosition());
            }

            @Override
            public boolean isGoal() {
                return this.state.getExit().equals(this.getCurrentPosition());
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public boolean equals(Object other) {
                if (state == null) {
                    return false;
                }
                State<Maze, Grid> o = (State<Maze, Grid>) other;
                return this.getCurrentPosition().col == o.getCurrentPosition().col &&
                        this.getCurrentPosition().row == o.getCurrentPosition().row;
            }
        }

        class MazeSearchable implements Searchable<Maze, Grid> {

            private State<Maze, Grid> initialMaze;
            private State<Maze, Grid> currentMaze;
            private State<Maze, Grid> goalMaze;


            //CTOR
            public MazeSearchable(Maze initialMaze) {

                this.currentMaze = new MazeGameState(initialMaze);
                this.currentMaze.setCurrentPosition(initialMaze.getEntrance());
                this.initialMaze = new MazeGameState(initialMaze);
                this.initialMaze.setCurrentPosition(initialMaze.getEntrance());
                this.goalMaze = new MazeGameState(initialMaze);
                this.goalMaze.setCurrentPosition(initialMaze.getExit());
            }

            @Override
            public State<Maze, Grid> getCurrentState() {

                return this.currentMaze;
            }

            @Override
            public State<Maze, Grid> getInitialState() {
                return this.initialMaze;
            }

            @Override
            public State<Maze, Grid> getGoalState() {
                return this.goalMaze;
            }

            @Override
            public ArrayList<State<Maze, Grid>> getAllPossibleStates() {
                return this.currentMaze.getAllNeighbors();
            }

            @Override
            public Comparator<State<Maze, Grid>> getComparator() {
                return new MazeSearchable.StateComparator();
            }

            class StateComparator implements Comparator<State<Maze, Grid>> {
                /**
                 * This function will calculate which of the given states are closer to the goal
                 *
                 * @param state1: the first state to compare
                 * @param state2: the second state to compare
                 * @return : case state2 is closer return -1. case state1 is closer return 1. case of no difference return 0.
                 */
                @Override
                public int compare(State<Maze, Grid> state1, State<Maze, Grid> state2) {
                    return Double.compare(state2.generateCost(), state1.generateCost());
                }
            }

            @Override
            public void setGoalState(State<Maze, Grid> goalState) {
                this.goalMaze = goalState;
            }

            @Override
            public void setInitialState(State<Maze, Grid> initialState) {
                this.initialMaze = initialState;
            }

            @Override
            public void setCurrentState(State<Maze, Grid> currentState) {
                this.currentMaze = currentState;
            }
        }

        MazeSearchable s = new MazeSearchable(m);
        BFSSearcher searcher = new BFSSearcher();
        Solution solution = searcher.search(s);
        ArrayList<String> ret = new ArrayList<String>();
        for (Object mazeStep : solution.getSteps()) {
            MazeStep move = (MazeStep) mazeStep;

            for (String step : move.directions) {
                ret.add(step);
            }

        }
        return ret;
    }
}
