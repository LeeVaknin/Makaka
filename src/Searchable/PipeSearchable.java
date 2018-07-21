package Searchable;

import Models.MatrixBoard;
import Models.Pipe;
import Models.State;

import java.util.ArrayList;

public class PipeSearchable implements Searchable<MatrixBoard> {

    // C-TOR
    public PipeSearchable(State<MatrixBoard> currentState, State<MatrixBoard> initialState, State<MatrixBoard> goalState) {
        this.currentState = currentState;
        this.initialState = initialState;
        this.goalState = goalState;
    }

    public PipeSearchable(State<MatrixBoard> initialState) {
        this.initialState = initialState;
    }

    public PipeSearchable(State<MatrixBoard> initialState, State<MatrixBoard> goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    // variables
    private State<MatrixBoard> currentState;
    private State<MatrixBoard> initialState;
    private State<MatrixBoard> goalState;

    @Override
    public boolean isGoal() {
        return this.currentState.equals(goalState);
    }

    @Override
    public State<MatrixBoard> getState() {
        return this.currentState;
    }

    @Override
    public State<MatrixBoard> getInitialState() {
        return this.initialState;
    }

    @Override
    public State<MatrixBoard> getGoalState() {
        return this.goalState;
    }

    @Override
    public ArrayList<State<MatrixBoard>> getAllPossibleStates() {
        ArrayList<State<MatrixBoard>> stateArrayList = null;
        int length = this.currentState.getState().length();
        try {
            stateArrayList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    MatrixBoard tmpBoard = new MatrixBoard(this.currentState.getState());
                    tmpBoard.getBoard()[i][j].rotate();
                    double tmpCost = this.currentState.getCost() + 1;
                    State<MatrixBoard> tmpState = new State<>(tmpBoard, tmpCost, this.getState());

                    stateArrayList.add(tmpState);
                }
            }
        } catch (Exception ex) {
            System.out.println("PipeSearchable.getAllPossibleStates(): Error details: " + ex.getMessage());
        }
        return stateArrayList;
    }

    @Override
    public void setCurrentState(State<MatrixBoard> currentState) {
        this.currentState = currentState;
    }

    @Override
    public void setInitialState(State<MatrixBoard> initialState) {
        this.initialState = initialState;
    }

    @Override
    public void setGoalState(State<MatrixBoard> goalState) {
        this.goalState = goalState;
    }
}
