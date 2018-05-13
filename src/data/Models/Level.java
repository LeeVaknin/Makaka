package data.Models;

public class Level {

    private int id;
    private Puzzle currentState;
    private Puzzle initialState;
    private Puzzle solution;
    private Result result;

    /////////
    //Methods
    /////////

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentState(Puzzle currentState) {
        this.currentState = currentState;
    }

    public void setInitialState(Puzzle initialState) {
        this.initialState = initialState;
    }

    public void setSolution(Puzzle solution) {
        this.solution = solution;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public Puzzle getCurrentState() {
        return currentState;
    }

    public Puzzle getInitialState() {
        return initialState;
    }

    public Puzzle getSolution() {
        return solution;
    }

    public Result getResult() {
        return result;
    }

    //_timer: ITimer
//IPuzzle -_difficulty:

}
