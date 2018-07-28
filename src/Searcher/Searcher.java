package Searcher;

import Searchable.Searchable;
import Models.Solution;

// T is the searchable, S is the steps to the solution of the problem
public interface Searcher<T, S> {

    public Solution<S> search(Searchable<T> s);

    // get how many nodes were evaluated by the algorithm
    public int getNumberOfNodesEvaluated();

}
