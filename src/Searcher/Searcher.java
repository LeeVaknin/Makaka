package Searcher;

import Searchable.Searchable;
import Models.Solution;
import State.State;

// T is the searchable, S is the steps to the solution of the problem
public interface Searcher<T> {

    public Solution<State<T>> search(Searchable<T> s);

    // get how many nodes were evaluated by the algorithm
    public int getNumberOfNodesEvaluated();

}
