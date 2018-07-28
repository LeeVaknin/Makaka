package State;
import java.util.ArrayList;

// T is the board, S is the child of the state
public abstract class State<T> {

    // Variables
    protected T state;
    protected double cost; // cost to reach this state
    protected State<T> cameFrom;

    // Methods

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
    }

    public abstract void setState(T state);

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

   /*
   Returns a backTrace of the states for the algorithms
    */
    public abstract ArrayList<State<T>> backTrace();

    public abstract ArrayList<State<T>> getAllNeighbors();
}
