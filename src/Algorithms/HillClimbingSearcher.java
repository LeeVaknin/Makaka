package Algorithms;

import Models.Solution;
import State.State;
import Searchable.Searchable;
import Searcher.Searcher;


import java.util.*;

// T represent the board. S represent the boars state
public class HillClimbingSearcher<T, P> implements Searcher<T, P> {

    public Solution<State<T, P>> search(Searchable<T, P> searchable) {

        // System.out.println("Using HillClimbing Searcher: ");
        State<T, P> currentState = searchable.getInitialState();
        State<T, P> bestNeighborState = null;

        while (true) {
            List<State<T, P>> neighbors = new ArrayList<>(currentState.getAllNeighbors());
            for (State<T, P> neighbor : neighbors) {
                neighbor.setCameFrom(currentState);
            }
            double grade = Double.MAX_VALUE;
            if (Math.random() < 0.7) {
                for (State<T, P> neighbor : neighbors) {
                    double g = neighbor.generateCost();
                    if (g < grade) {
                        bestNeighborState = neighbor;
                        grade = g;
                    }
                }

                if (bestNeighborState == null)
                    bestNeighborState = currentState;

                if (bestNeighborState.isGoal()) {
                    return bestNeighborState.backTrace();
                }

                if (currentState.generateCost() > bestNeighborState.generateCost()) {
                    currentState = bestNeighborState;
                }

            } else {
                if (neighbors.isEmpty()) {
                    break;
                }
                Random r = new Random();
                int randomIndex = r.nextInt(neighbors.size());
                currentState = neighbors.get(randomIndex);
            }
        }
        return null;
    }


    /*

    @Override
    public Solution<State<T, P>> search(Searchable<T, P> searchable) {

        // Array list with all the states we visited at
        ArrayList<State<T, P>> visitedStates = new ArrayList<>();

        Comparator<State<T, P>> comparator = searchable.getComperator();
        // PriorityQueue to manage which of the state we need to work on
        PriorityQueue<State<T, P>> queue = new PriorityQueue<>(10, comparator);
        queue.add(searchable.getInitialState());

        // States that will be part of our solution
        State<T, P> rootSolution = searchable.getInitialState();
        // Add the first state to our solution
        visitedStates.add(rootSolution);
        // Add the first state to our queue
        queue.add(rootSolution);
        // As long as we have states that we need to work on
        State<T, P> currentState;
        while(!queue.isEmpty()) {
            currentState = queue.poll();
            visitedStates.add(currentState);

            if (currentState.isGoal()) {
                return currentState.backTrace();
            }
            ArrayList<State<T, P>> possibleStates = currentState.getAllNeighbors();

            for (State<T, P> state : possibleStates) {
                state.setCameFrom(currentState);

                if(!visitedStates.contains(state)) {
                    visitedStates.add(state);
                    queue.add(state);
                }
            }
        }
        return null;
    }
    */
    @Override
    public int getNumberOfNodesEvaluated() {
        return 0;
    }

}
