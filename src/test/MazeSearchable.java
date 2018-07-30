package test;

import Searchable.Searchable;
import State.State;

import java.util.ArrayList;
import java.util.Comparator;

public class MazeSearchable implements Searchable<Maze,Grid>{

    private State<Maze, Grid> initialMaze;
    private State<Maze, Grid> currentMaze;
    private State<Maze, Grid> goalMaze;


    //CTOR
    public MazeSearchable(Maze initialMaze)
    {

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
