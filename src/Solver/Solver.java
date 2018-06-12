package Solver;

import Models.Searchable.Searchable;
import Models.Solution;
import Models.State;

public interface Solver<T, S> {

    /////////
    //Methods
    /////////

    public Solution<S> solve(Searchable<T> searchable);
    public void createProblem();


}
