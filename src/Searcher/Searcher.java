package Searcher;

import Searchable.Searchable;
import Models.Solution;

public interface Searcher<T, S> {

    public Solution<S> search(Searchable<T> s);

    // get how many nodes were evaluated by the algorithm
    public int getNumberOfNodesEvaluated();

}
