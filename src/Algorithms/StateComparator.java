package Algorithms;

import State.State;

import java.util.Comparator;

/*
The heuristic method for the BestFirstSearch algorithm
 */
public class StateComparator implements Comparator<State>
{
    /**
     * This function will calculate which of the given states are closer to the goal
     *
     * @param x
     * @param y
     * @return : case y is closer return -1. case x is closer return 1. case of no difference return 0.
     */
    @Override
    public int compare(State x, State y){
        if (x.generateCost() > y.generateCost())
            return -1;
        if (x.generateCost() < y.generateCost())
            return 1;
        return 0;
     }
}
