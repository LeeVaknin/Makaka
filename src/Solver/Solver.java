package Solver;

import Searchable.Searchable;
import Models.Solution;
import State.State;

// T is the searchable and S is the convention of the steps to the solution of the problem.
public interface Solver<T, P> {

    /////////
    //Methods
    /////////

    Solution<State<T, P>> solve(Searchable<T, P> searchable);

    Solution<State<T, P>> solve(String searchable);
}
