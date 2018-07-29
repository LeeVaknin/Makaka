package Algorithms;

import Models.Solution;
import State.State;
import Searchable.Searchable;
import Searcher.Searcher;

import java.util.ArrayList;
import java.util.Stack;

public class DFSSearcher <T> implements Searcher<T> {

    public Solution<State<T>> search(Searchable<T> searchable) {

        // Array list with all the states we visited at
        ArrayList<T> visitedStates = new ArrayList<>();
        // Stack to manage which of the state we need to work on
        Stack<State<T>> stack = new Stack<>();
        // States that will be part of our solution
        State<T> rootSolution = searchable.getInitialState();
        // Add the
        visitedStates.add(rootSolution.getState());
        stack.push(rootSolution);
        while(!stack.empty()) {
            State<T> currentState;
            currentState = stack.pop();

            if (currentState.isGoal()) {
                return currentState.backTrace();
            }
            ArrayList<State<T>> possibleStates = searchable.getAllPossibleStates();

            for (State<T> state : possibleStates) {
                state.setCameFrom(currentState);

                if(!visitedStates.contains(state.getState())) {
                    visitedStates.add(state.getState());
                    stack.push(state);

                }
            }
        }
        return null;
    }

    //TODO: fix this and understand if needed
    public int getNumberOfNodesEvaluated()
    {
        return 0;
    }

}
