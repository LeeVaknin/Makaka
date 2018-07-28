package Models;

import jdk.nashorn.internal.objects.annotations.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public abstract class State<T> {

    // Variables
    private T state;
    private double cost; // cost to reach this state
    private State<T> cameFrom;

    // Methods

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
    }

    public T getState() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    public State<T> getCameFrom() {
        return cameFrom;
    }

    public abstract int generateCost();

    public boolean equals(State<T> state) {
         return this.state.equals(state.state);
    }


  //Returns a backTrace of the states for the algorithms
    public ArrayList<State> backTrace() {
        ArrayList<State> returnBackTrace = new ArrayList<>();
        State temp = this.getCameFrom();
        while (temp != null) {
            returnBackTrace.add(temp);
            temp = temp.getCameFrom();
        }
        Collections.reverse(returnBackTrace);
        return returnBackTrace;
    }


}
