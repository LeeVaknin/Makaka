package Algorithms;

import Models.State;
import Models.Solution;
import Searchable.Searchable;
import Searcher.Searcher;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFSSearcher<T,S> implements Searcher<T,S> {

    public Solution<S> search(Searchable<T> s)
    {
        ArrayList<T> visitedStates = new ArrayList<T>();
        LinkedList<State<T>> queue = new LinkedList<State<T>>();
        State<T> rootSolution = s.getInitialState();
        visitedStates.add(rootSolution.getState());
        queue.add(rootSolution);
        while(!queue.isEmpty())
        {
            State<T> currentState;
            currentState = queue.poll();
            if (currentState.getState() == s.getGoalState().getState())
            {
                //TODO:need to change when Solution is finalize
                //  return currentState;
                return null;
            }
            ArrayList<State<T>> possibleStates = s.getAllPossibleStates(currentState);
            for (State<T> state : possibleStates)
            {
                if(!visitedStates.contains(state.getState()))
                {
                    visitedStates.add(state.getState());
                    queue.add(state);

                }
            }
        }
        return new Solution<S>();
    }

   //TODO: fix this and understand if needed
    public int getNumberOfNodesEvaluated()
    {
        return 0;
    }



}
