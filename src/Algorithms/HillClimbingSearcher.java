package Algorithms;

import Board.Solution;
import State.State;
import Searchable.Searchable;


import java.util.*;

// T represent the board. S represent the boars state
public class HillClimbingSearcher<T, P> implements Searcher<T, P> {

    public Solution<P> search(Searchable<T, P> searchable) {

        State<T,P> currentState = searchable.getInitialState();
        currentState.setCost(searchable.getCurrentState().generateCost());

        while (true) {

            if (currentState.isGoal()) {
                return new Solution<P>(currentState);
            }

            List<State<T,P>> states = currentState.getAllNeighbors();
            for (State<T, P> tmpState : states) {
                tmpState.setCameFrom(currentState);
            }

            if (Math.random() > 0.7) {
                Random r = new Random();
                int randomIndex = r.nextInt(states.size());
                currentState = states.get(randomIndex);
                continue;
            }

            double grade = Double.MAX_VALUE;
            for (State<T,P> tmpState : states) {
                tmpState.setCost(tmpState.generateCost());
                if (tmpState.getCost() < grade) {
                    grade = tmpState.getCost();
                    currentState = tmpState;
                }
            }
        }
    }


    @Override
    public int getNumberOfNodesEvaluated() {
        return 0;
    }

}
