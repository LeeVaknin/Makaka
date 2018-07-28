package Algorithms;

import State.State;

import java.util.Comparator;

/*
The huristic method for the BestFirstSearch algorithm
 */
public class StateComparator implements Comparator<State>
{
    @Override
    public int compare(State x, State y){
        if (x.getCost() > y.getCost())
            return -1;

        if (x.getCost() < y.getCost())
            return 1;

        return 0;
     }
}
