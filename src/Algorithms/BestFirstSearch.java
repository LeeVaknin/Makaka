package Algorithms;

import Models.Solution;
import Searchable.Searchable;
import Searcher.Searcher;
import State.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


// T is the searchable and S is the step, K is the state
public class BestFirstSearch<T> implements Searcher<T> {

    public Solution<State<T>> search(Searchable<T> searchable) {
        ArrayList<T> visitedStates = new ArrayList<>();
        Comparator<State<T>> comparator = searchable.getComperator();
        PriorityQueue<State<T>> queue = new PriorityQueue<State<T>>(10, comparator);
        queue.add(searchable.getInitialState());
        while (!queue.isEmpty())
        {
            State<T> currentState;
            currentState = queue.poll();
            visitedStates.add(currentState.getState());
            if (currentState.isGoal())
                return currentState.backTrace();


            ArrayList<State<T>> possibleStates = searchable.getAllPossibleStates();
            for (State<T> state : possibleStates)
            {

                if(!visitedStates.contains(state.getState()) && !queue.contains(state))
                {
                    state.setCameFrom(currentState);
                    queue.add(state);
                    visitedStates.add(state.getState());

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
