package Models;

import java.util.ArrayList;
import java.util.Collections;

public class PipeGameState<T> {

    // Variables
    private T state;
    private double cost; // cost to reach this state
    private PipeGameState<T> cameFrom;

    // C-TOR
    public PipeGameState(T state) {
        this.state = state;
    }

    public PipeGameState(T state, double cost, PipeGameState<T> cameFrom) {
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

    public void setCameFrom(PipeGameState<T> cameFrom) {
        this.cameFrom = cameFrom;
    }

    public T getState() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    public PipeGameState<T> getCameFrom() {
        return cameFrom;
    }

    public boolean equals(PipeGameState<T> state) {
         return this.state.equals(state.state);
    }

   /*
   Returns a backTrace of the states for the algorithms
    */
    public ArrayList<PipeGameState> backTrace() {
        ArrayList<PipeGameState> returnBackTrace = new ArrayList<>();
        PipeGameState temp = this;
        while (temp != null){
            returnBackTrace.add(temp);
        }
        Collections.reverse(returnBackTrace);
        return returnBackTrace;
    }
}
