package Algorithms;

import Models.Solution;
import State.State;
import Searchable.Searchable;
import Searcher.Searcher;

import java.util.ArrayList;
import java.util.Stack;

public class DFSSearcher<T, P> implements Searcher<T, P> {

    public Solution<State<T, P>> search(Searchable<T, P> searchable) {

        // Array list with all the states we visited at
        ArrayList<State<T, P>> visitedStates = new ArrayList<>();
        // Stack to manage which of the state we need to work on
        Stack<State<T, P>> stack = new Stack<>();
        // States that will be part of our solution
        State<T, P> rootSolution = searchable.getInitialState();
        stack.push(rootSolution);
        // As long as we have states that we need to work on
        State<T, P> currentState;
        while (!stack.empty()) {
            currentState = stack.pop();
            visitedStates.add(currentState);

            if (currentState.isGoal()) {
                // Found the goal, return the back track from solution
                return currentState.backTrace();
            }
            ArrayList<State<T, P>> possibleStates = currentState.getAllNeighbors();

            for (State<T, P> state : possibleStates) {
                if (!visitedStates.contains(state) && !stack.contains(state)) {
                    state.setCameFrom(currentState);
                    visitedStates.add(state);
                    stack.push(state);
                }

            }
        }
        return null;
    }

    //TODO: fix this and understand if needed
    public int getNumberOfNodesEvaluated() {
        return 0;
    }

}
