package State;
import Board.MatrixBoard;
import Models.Position;
import Models.Solution;

import java.util.ArrayList;

// T is the board, S is the child of the state
public abstract class State<T> {

    // Variables
    protected T state;
    private State<T> cameFrom;

    // Methods

    public void setCameFrom(State<T> cameFrom) {
        if (cameFrom != null) {
            this.cameFrom = cameFrom;
        }
    }

    public abstract void setState(T state);

    public T getState() {
        return state;
    }

    public State<T> getCameFrom() {
        return cameFrom;
    }

    public abstract double generateCost();

   /*
   Returns a backTrace of the states for the algorithms
    */
    public abstract Solution<State<T>> backTrace();

    public abstract ArrayList<State<T>> getAllNeighbors();

    public abstract  boolean isGoal();
}
