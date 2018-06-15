package Searchable;
import Models.State;

import java.util.ArrayList;


public interface Searchable<T> {

    public boolean isGoal();
    public State<T> getState();


    public State<T> getInitialState();
    public State<T> getGoalState();

    // -*** Returns all the possible states in the current state (possible moves) ***-
    //TODO: Ask Igor
    public ArrayList<State<T>> getAllPossibleStates(State<T> state);

}
