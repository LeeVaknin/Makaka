package State;
import Models.Position;

import java.util.ArrayList;

// T is the board, S is the child of the state
public abstract class State<T, S> {

    // Variables
    protected T state;
    private double cost; // cost to reach this state
    private State<T, S> cameFrom;
    protected Position from;
    private Position to;

    // Methods

    public Position getFrom() {
        return from;
    }

    public void setFrom(Position from) {
        if (from != null) {
            this.from = new Position(from);
            return;
        }
        this.from = null;
    }

    public Position getTo() {
        return to;
    }

    public void setTo(Position to) {
        if (to != null) {
            this.to = new Position(to);
        }
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCameFrom(State<T, S> cameFrom) {
        if (cameFrom != null) {
            this.cameFrom = cameFrom;
        }
    }

    public abstract void setState(T state);

    public T getState() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    public State<T, S> getCameFrom() {
        return cameFrom;
    }

    public abstract int generateCost();

   /*
   Returns a backTrace of the states for the algorithms
    */
    public abstract ArrayList<State<T, S>> backTrace();

    public abstract ArrayList<S> getAllNeighbors();
}
