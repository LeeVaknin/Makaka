package Searchable;

import Models.MatrixBoard;
import Models.State;

import java.util.ArrayList;

public class PipeSearchable implements Searchable<MatrixBoard> {

    // variables
    private State<MatrixBoard> currentState;
    private State<MatrixBoard> initialState;
    private State<MatrixBoard> goalState;

    @Override
    public boolean isGoal() {
        return false;
    }

    @Override
    public State<MatrixBoard> getState() {
        return null;
    }

    @Override
    public State<MatrixBoard> getInitialState() {
        return null;
    }

    @Override
    public State<MatrixBoard> getGoalState() {
        return null;
    }

    @Override
    public ArrayList<State<MatrixBoard>> getAllPossibleStates(State<MatrixBoard> state) {
        return null;
    }
}
