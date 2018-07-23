package Algorithms;

import Models.Solution;
import Models.State;
import Searchable.Searchable;
import Searcher.Searcher;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class BestFirstSearch <T,S> implements Searcher<T,S> {

    public Solution<S> search(Searchable<T> s) {
        ArrayList<T> visitedStates = new ArrayList<T>();
        Comparator<State> comparator = new StateComparator();
        PriorityQueue<State> queue = new PriorityQueue<State>(10, comparator);
        queue.add(s.getInitialState());
        while (!queue.isEmpty()) {
            State<T> currentState;
            currentState = queue.poll();
            visitedStates.add(currentState.getState());
            if (currentState.equals(s.getGoalState())) {
                //TODO:need to change when Solution is finalize
                return null;

            }
            ArrayList<State<T>> possibleStates = s.getAllPossibleStates();
            for (State<T> state : possibleStates)
            {
                if(!visitedStates.contains(state.getState()) && !queue.contains(state))
                {
                    state.setCameFrom(currentState);
                    queue.add(state);

                }
                //TODO:Check if need to do extra checks here (like the example in the presentation)
            }

        }
        return null;
    }

    public int getNumberOfNodesEvaluated()
    {
        return 0;
    }

}
