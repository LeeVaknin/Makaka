package Searchable;

import Board.MatrixBoard;
import State.PipeGameState;
import Models.Position;

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
        startPosition = tmpBoard2.findStartPosition();


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

    /**
     * This function will calculate which of the given states are closer to the goal
     *
     * @param state1
     * @param state2
     * @return : case state2 is closer return -1. case state1 is closer return 1. case of no difference return 0.
     */
    @Override
    public int compare(PipeGameState state1, PipeGameState state2) {
            if (state1.generateCost() > state2.generateCost())
                return -1;
            if (state1.generateCost() < state2.generateCost())
                return 1;
            return 0;
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
