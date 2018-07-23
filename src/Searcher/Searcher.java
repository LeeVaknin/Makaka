package Searcher;

import Models.State;
import Searchable.Searchable;
import Models.Solution;

import java.util.ArrayList;

public interface Searcher<T, S> {

    public Solution<S> search(Searchable<T> s);

    // get how many nodes were evaluated by the algorithm
    public int getNumberOfNodesEvaluated();

}
