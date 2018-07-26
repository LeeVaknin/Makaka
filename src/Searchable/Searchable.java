package Searchable;
import Models.MatrixBoard;
import Models.State;

import java.util.ArrayList;


public interface Searchable<T> {

    State<T> getCurrentState();
    void setCurrentState(State<T> currentState);

    State<T> getInitialState();
    void setInitialState(State<T> initialState);

    State<T> getGoalState();
    void setGoalState(State<T> goalState);

    // -*** Returns all the possible states in the current state (possible moves) ***-
    ArrayList<State<T>> getAllPossibleStates();

}
