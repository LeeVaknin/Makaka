package Solver;

import Searchable.Searchable;
import Models.Solution;

// T is the searchable and S is the convention of the steps to the solution of the problem.
public interface Solver<T, S> {

    /////////
    //Methods
    /////////

    Solution<S> solve(T searchable);

    Solution<S> solve(String searchable);
}
