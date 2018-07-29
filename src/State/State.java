package State;
import Models.Solution;
import java.util.ArrayList;


// T is the board, P is the position type
public abstract class State<T, P> {

    // Variables
    protected T state;
    private State<T, P> cameFrom;
    protected P currentPosition;

    public P getCurrentPosition() {
        return currentPosition;
    }

    public abstract void setCurrentPosition(P currentPosition);

    // Methods

    public void setCameFrom(State<T, P> cameFrom) {
        if (cameFrom != null) {
            this.cameFrom = cameFrom;
        }
    }

    public abstract void setState(T state);

    public T getState() {
        return state;
    }

    public State<T, P> getCameFrom() {
        return cameFrom;
    }

    public abstract double generateCost();

   /*
   Returns a backTrace of the states for the algorithms
    */
    public abstract Solution<State<T, P>> backTrace();

    public abstract ArrayList<State<T, P>> getAllNeighbors();

    public abstract  boolean isGoal();
}
