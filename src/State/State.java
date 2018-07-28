package State;
import java.util.ArrayList;

// T is the board, S is the child of the state
public abstract class State<T, S> {

    // Variables
    protected T state;
    protected double cost; // cost to reach this state
    protected S cameFrom;

    // Methods

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCameFrom(S cameFrom) {
        this.cameFrom = cameFrom;
    }

    public abstract void setState(T state);

    public T getState() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    public S getCameFrom() {
        return cameFrom;
    }

    public abstract int generateCost();

   /*
   Returns a backTrace of the states for the algorithms
    */
    public abstract ArrayList<S> backTrace();
}
