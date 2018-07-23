package Models;

import jdk.nashorn.internal.objects.annotations.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class State<T> {

    // Variables
    private T state;
    private double cost; // cost to reach this state
    private State<T> cameFrom;

    // C-TOR
    public State(T state) {
        this.state = state;
    }

    public State(T state, double cost, State<T> cameFrom) {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }


    // Methods
    public void setState(T state) {
        this.state = state;
    }

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

    public boolean equals(State<T> state) {
         return this.state.equals(state.state);
    }

   /*
   Returns a backTrace of the states for the algorithms
    */
    public ArrayList<State> backTrace() {
        ArrayList<State> returnBackTrace = new ArrayList<>();
        State temp = this;
        while (temp != null){
            returnBackTrace.add(temp);
        }
        Collections.reverse(returnBackTrace);
        return returnBackTrace;
    }
}
