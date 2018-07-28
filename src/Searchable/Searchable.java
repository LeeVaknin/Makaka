package Searchable;
import State.State;

import java.util.ArrayList;
import java.util.Comparator;

// T is the board type
public interface Searchable<T, S> {

    State<T, S> getCurrentState();
    void setCurrentState(State<T, S> currentState);

    State<T, S> getInitialState();
    void setInitialState(State<T, S> initialState);

    State<T, S> getGoalState();
    void setGoalState(State<T, S> goalState);

    // -*** Returns all the possible states in the current state (possible moves) ***-
    ArrayList<State<T, S>> getAllPossibleStates();

    Comparator<State<T, S>> getComperator();
}
