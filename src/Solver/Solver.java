package Solver;

import Searchable.Searchable;
import Models.Solution;
import State.State;

// T is the searchable and S is the convention of the steps to the solution of the problem.
public interface Solver<T> {

    /////////
    //Methods
    /////////

    Solution<State<T>> solve(Searchable<T> searchable);

    Solution<State<T>> solve(String searchable);
}
