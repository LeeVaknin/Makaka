package Searchable;

import Board.MatrixBoard;
import Models.Step;
import State.PipeGameState;
import Models.Position;
import State.State;
import java.util.ArrayList;
import java.util.Comparator;


public class PipeSearchable implements Searchable<MatrixBoard, Step> {

    // variables
    private PipeGameState currentState;
    private PipeGameState initialState;
    private PipeGameState  goalState;

    // C-TOR
    public PipeSearchable(State<MatrixBoard, Step> currentState,
                          State<MatrixBoard, Step> initialState,
                          State<MatrixBoard, Step> goalState) {
        this.currentState = new PipeGameState(currentState);
        this.initialState = new PipeGameState(initialState);
        this.goalState = new PipeGameState(goalState);
    }

    public PipeSearchable(State<MatrixBoard, Step> initialState) {
        this.initialState = new PipeGameState(initialState);
        this.currentState = new PipeGameState(initialState);
        this.goalState = null;
    }

    // Methods
    public boolean isGoal() {
        return this.currentState.equals(goalState);
    }

    public boolean isGoal(State<MatrixBoard, Step> state) {
        return state.equals(goalState);
    }

    @Override
    public State<MatrixBoard, Step> getCurrentState() {
        return this.currentState;
    }

    @Override
    public State<MatrixBoard, Step> getInitialState() {
        return this.initialState;
    }

    @Override
    public State<MatrixBoard, Step> getGoalState() {
        return this.goalState;
    }

    @Override
    public ArrayList<Step> getAllPossibleStates() {
        return this.currentState.getAllNeighbors();
    }

    @Override
    public Comparator<State<MatrixBoard, Step>> getComperator() {
        return new StateComperator();
    }

    class StateComperator implements Comparator<State<MatrixBoard, Step>> {
        /**
         * This function will calculate which of the given states are closer to the goal
         *
         * @param state1
         * @param state2
         * @return : case state2 is closer return -1. case state1 is closer return 1. case of no difference return 0.
         */
        @Override
        public int compare(State<MatrixBoard, Step> state1, State<MatrixBoard, Step> state2) {
            if (state1.generateCost() > state2.generateCost())
                return -1;
            if (state1.generateCost() < state2.generateCost())
                return 1;
            return 0;
        }
    }

    @Override
    public void setCurrentState(State<MatrixBoard, Step> currentState) {
//        this.currentState = new PipeGameState(currentState);
    }

    @Override
    public void setInitialState(State<MatrixBoard, Step> initialState) {
        this.initialState = new PipeGameState(initialState);
    }

    @Override
    public void setGoalState(State<MatrixBoard, Step> goalState) {
        this.goalState = new PipeGameState(goalState);
    }
}
