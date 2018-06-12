package Models.Searchable;

import Models.State;

import java.util.ArrayList;

public class Pipe implements Searchable<Pipe> {


    @Override
    public void initState() {

    }

    @Override
    public boolean isGoal() {
        return false;
    }

    @Override
    public State<Pipe> getState() {

        return null;
    }

    @Override
    public State<Pipe> getAllState() {
        return null;
    }

    @Override
    public State<Pipe> getInitialState() {
        return null;
    }

    @Override
    public State<Pipe> getGoalState() {
        return null;
    }

    @Override
    public ArrayList<State<Pipe>> getAllPossibleStates(State<Pipe> state) {
        return null;
    }

    public boolean equals(Pipe pipe){
        //TODO: Implements method
         return true;
    }
}
