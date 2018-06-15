package Models;

public class State<T> {

    // Variables
    private T state;
    private double cost; // cost to reach this state
    private T cameFrom; // the state we came from to this state


    // C-TOR
    public State(T state) {
        this.state = state;
    }

    public State(T state, double cost, T cameFrom) {
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

    public void setCameFrom(T cameFrom) {
        this.cameFrom = cameFrom;
    }

    public T getState() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    public T getCameFrom() {
        return cameFrom;
    }


    public boolean equals(State<T> state){
         return this.state.equals(state.state);
    }

}
