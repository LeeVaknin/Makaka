package Models.Searchable;

import Models.State;

import java.util.ArrayList;

public class Board implements Searchable<Board> {


    @Override
    public void initState() {

    }

    @Override
    public boolean isGoal() {
        return false;
    }

    @Override
    public State<Board> getState() {

        return null;
    }

    @Override
    public State<Board> getAllState() {
        return null;
    }

    @Override
    public State<Board> getInitialState() {
        return null;
    }

    @Override
    public State<Board> getGoalState() {
        return null;
    }

    @Override
    public ArrayList<State<Board>> getAllPossibleStates(State<Board> state) {
        return null;
    }

    public boolean equals(Board board){
        //TODO: Implements method
        return true;
    }
}
