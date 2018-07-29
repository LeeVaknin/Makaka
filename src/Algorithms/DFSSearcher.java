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
        ArrayList<State<T>> visitedStates = new ArrayList<>();
        // Stack to manage which of the state we need to work on
        Stack<State<T>> stack = new Stack<>();
        // States that will be part of our solution
        State<T> rootSolution = searchable.getInitialState();
        // Add the first state to our solution
        visitedStates.add(rootSolution);
        // Add the first state to our stack
        stack.push(rootSolution);
        // As long as we have states that we need to work on
        State<T> currentState;
        while(!stack.empty()) {
            currentState = stack.pop();

            if (currentState.isGoal()) {
                return currentState.backTrace();
            }
            ArrayList<State<T>> possibleStates = currentState.getAllNeighbors();

            for (State<T> state : possibleStates) {
                state.setCameFrom(currentState);

                if(!visitedStates.contains(state) && !stack.contains(state)) {
                    visitedStates.add(state);
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
