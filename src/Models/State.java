package Models;

public class State<T> {

    // Variables
    private T state;
    private double cost; // cost to reach this state
    private State<T> cameFrom;

    //TODO: I changed the cameFrom to be State<T< and not T cause the solution should be linkedList-Inbal
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


    public boolean equals(State<T> state){
         return this.state.equals(state.state);
    }

}
