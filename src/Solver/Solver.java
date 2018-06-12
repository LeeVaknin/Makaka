package Solver;

import Searchable.Searchable;
import Models.Solution;

public interface Solver<T, S> {

    /////////
    //Methods
    /////////

    public Solution<S> solve(Searchable<T> searchable);
    public void createProblem();


}
