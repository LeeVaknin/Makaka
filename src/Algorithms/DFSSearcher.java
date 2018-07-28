package Algorithms;

import Models.Solution;
import State.State;
import Searchable.Searchable;
import Searcher.Searcher;

import java.util.ArrayList;
import java.util.Stack;

public class DFSSearcher <T,S> implements Searcher<T,S> {

    public Solution<S> search(Searchable<T> s)
    {
        ArrayList<T> visitedStates = new ArrayList<T>();
        Stack<State<T>> stack = new Stack<>();
        State<T> rootSolution = s.getInitialState();
        visitedStates.add(rootSolution.getState());
        stack.push(rootSolution);
        while(!stack.empty())
        {
            State<T> currentState;
            currentState = stack.pop();
            if (currentState.getState() == s.getGoalState().getState())
            {
                //TODO:need to change when Solution is finalize
                //  return currentState;
                return null;
            }
            ArrayList<State<T>> possibleStates = s.getAllPossibleStates();
            for (State<T> state : possibleStates)
            {
                state.setCameFrom(currentState);
                if(!visitedStates.contains(state.getState()))
                {
                    visitedStates.add(state.getState());
                    stack.push(state);

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
