package Solver;

import Searchable.Searchable;
import Models.Solution;

public interface Solver<T, S> {

    /////////
    //Methods
    /////////

    Solution<S> solve(Searchable<T> searchable);

    Solution<S> solve(String searchable);
}
