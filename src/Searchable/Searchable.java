package Searchable;
import Models.MatrixBoard;
import Models.State;

import java.util.ArrayList;


public interface Searchable<T> {

    boolean isGoal();
    State<T> getState();

    void setCurrentState(State<T> currentState);

    void setInitialState(State<T> initialState);
    void setGoalState(State<MatrixBoard> goalState);

    State<T> getInitialState();
    State<T> getGoalState();

    // -*** Returns all the possible states in the current state (possible moves) ***-
    ArrayList<State<T>> getAllPossibleStates();

}
