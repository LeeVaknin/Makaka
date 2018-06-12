package Searcher;

import Models.Searchable.Searchable;
import Models.Solution;
import Models.State;

import java.util.PriorityQueue;

public class CommonSearcher<T, S> implements Searcher<T, S> {

    // Variables

    protected PriorityQueue<State<T>> openList;
    private int evaluatedNodes;

    // C-TOR

    public CommonSearcher() {
        openList = new PriorityQueue<State<T>>();
        evaluatedNodes = 0;
    }

    // Methods

    protected State popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }

    @Override
    public Solution<S> search(Searchable<T> s) {

        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {

        return 0;
    }
}
