package Searchable;

import Board.MatrixBoard;
import Models.Position;
import State.PipeGameState;
import State.State;
import java.util.ArrayList;
import java.util.Comparator;


public class PipeSearchable implements Searchable<MatrixBoard, Position> {

    // variables
    private PipeGameState currentState;
    private PipeGameState initialState;
    private PipeGameState  goalState;

    // C-TOR
    public PipeSearchable(State<MatrixBoard, Position> currentState,
                          State<MatrixBoard, Position> initialState,
                          State<MatrixBoard, Position> goalState) {
        this.currentState = new PipeGameState(currentState);
        this.initialState = new PipeGameState(initialState);
        this.goalState = new PipeGameState(goalState);
    }

    public PipeSearchable(State<MatrixBoard, Position> initialState) {
        this.initialState = new PipeGameState(initialState);
        this.currentState = new PipeGameState(initialState);
        this.goalState = null;
    }

    // Methods
    public boolean isGoal() {
        return this.currentState.equals(goalState);
    }

    @Override
    public State<MatrixBoard, Position> getCurrentState() {
        return this.currentState;
    }

    @Override
    public State<MatrixBoard, Position> getInitialState() {
        return this.initialState;
    }

    @Override
    public State<MatrixBoard, Position> getGoalState() {
        return this.goalState;
    }

    @Override
    public ArrayList<State<MatrixBoard, Position>> getAllPossibleStates() {
        return this.currentState.getAllNeighbors();
    }

    @Override
    public Comparator<State<MatrixBoard, Position>> getComperator() {
        return new StateComperator();
    }

    class StateComperator implements Comparator<State<MatrixBoard, Position>> {
        /**
         * This function will calculate which of the given states are closer to the goal
         *
         * @param state1
         * @param state2
         * @return : case state2 is closer return -1. case state1 is closer return 1. case of no difference return 0.
         */
        @Override
        public int compare(State<MatrixBoard, Position> state1, State<MatrixBoard, Position> state2) {
            if (state1.generateCost() > state2.generateCost())
                return -1;
            if (state1.generateCost() < state2.generateCost())
                return 1;
            return 0;
        }
    }

    @Override
    public void setCurrentState(State<MatrixBoard, Position> currentState) {
//        this.currentState = new PipeGameState(currentState);
    }

    @Override
    public void setInitialState(State<MatrixBoard, Position> initialState) {
        this.initialState = new PipeGameState(initialState);
    }

    @Override
    public void setGoalState(State<MatrixBoard, Position> goalState) {
        this.goalState = new PipeGameState(goalState);
    }
}
