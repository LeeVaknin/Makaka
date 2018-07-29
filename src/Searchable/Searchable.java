package Searchable;
import State.State;

import java.util.ArrayList;
import java.util.Comparator;

// T is the board type
public interface Searchable<T> {

    State<T> getCurrentState();
    void setCurrentState(State<T> currentState);

    State<T> getInitialState();
    void setInitialState(State<T> initialState);

    State<T> getGoalState();
    void setGoalState(State<T> goalState);

    // -*** Returns all the possible states in the current state (possible moves) ***-
    ArrayList<State<T>> getAllPossibleStates();

    Comparator<State<T>> getComperator();
}
