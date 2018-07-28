package Searchable;

import Models.MatrixBoard;
import State.PipeGameState;
import Models.Position;
import Models.State;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PipeSearchable implements Searchable<PipeGameState> {

    // variables
    private PipeGameState currentState;
    private PipeGameState initialState;
    private PipeGameState goalState;

    // C-TOR
    public PipeSearchable(PipeGameState currentState, PipeGameState initialState, PipeGameState goalState) {
        this.currentState = currentState;
        this.initialState = initialState;
        this.goalState = goalState;
    }

    public PipeSearchable(PipeGameState initialState) {
        this.initialState = initialState;
        this.currentState = initialState;
        this.goalState = null;
    }

    public PipeSearchable(PipeGameState initialState, PipeGameState currentState) {
        this.initialState = initialState;
        this.currentState = currentState;
        this.goalState = null;
    }

    // Methods
    public boolean isGoal() {
        return this.currentState.equals(goalState);
    }

    public boolean isGoal(PipeGameState state) {
        return state.equals(goalState);
    }

    @Override
    public PipeGameState getCurrentState() {
        return this.currentState;
    }

    @Override
    public PipeGameState getInitialState() {
        return this.initialState;
    }

    @Override
    public PipeGameState getGoalState() {
        return this.goalState;
    }

    @Override
    public ArrayList<PipeGameState> getAllPossibleStates() {
        Position startPosition = null;
        ArrayList<PipeGameState> stateArrayList = null;
        int row = this.currentState.getState().rows();
        int col = this.currentState.getState().columns();

        MatrixBoard tmpBoard2 = new MatrixBoard(this.currentState.getState());
        startPosition = tmpBoard2.findStartPosition(tmpBoard2.getBoard());


        try {
            stateArrayList = new ArrayList<>();
            PipeGameState tmpBoard = new PipeGameState(this.currentState.getState());
            stateArrayList = tmpBoard.getAllNeighbors();

            for (PipeGameState state : stateArrayList) {
                // Add validation of is legal move

            }

        } catch (Exception ex) {
            System.out.println("PipeSearchable.getAllPossibleStates(): Error details: " + ex.getMessage());
        }
        return stateArrayList;


//        ArrayList<State<MatrixBoard>> stateArrayList = null;
//        int row = this.currentState.getState().rows();
//        int col = this.currentState.getState().columns();
////        int rows = this.currentState.getCurrentState().rows();
//        try {
//            stateArrayList = new ArrayList<>();
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; j++) {
//                    MatrixBoard tmpBoard = new MatrixBoard(this.currentState.getState());
//                    tmpBoard.getBoard()[i][j].rotate();
//                    double tmpCost = this.currentState.getCost() + 1;
//                    State<MatrixBoard> tmpState = new State<>(tmpBoard, tmpCost, this.getCurrentState());
//
//                    stateArrayList.add(tmpState);
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println("PipeSearchable.getAllPossibleStates(): Error details: " + ex.getMessage());
//        }
//        return stateArrayList;
    }

    @Override
    public void setCurrentState(PipeGameState currentState) {
//        this.currentState = new PipeGameState(currentState);
    }

    @Override
    public void setInitialState(PipeGameState initialState) {
        this.initialState = initialState;
    }

    @Override
    public void setGoalState(PipeGameState goalState) {
        this.goalState = goalState;
    }
}
