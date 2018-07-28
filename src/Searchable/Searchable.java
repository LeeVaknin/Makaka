package Searchable;
import State.State;

import java.util.ArrayList;
import java.util.Comparator;


public interface Searchable<T> {

    T getCurrentState();
    void setCurrentState(T currentState);

    T getInitialState();
    void setInitialState(T initialState);

    T getGoalState();
    void setGoalState(T goalState);

    // -*** Returns all the possible states in the current state (possible moves) ***-
    ArrayList<T> getAllPossibleStates();

    public int compare(T state1, T state2);

}
