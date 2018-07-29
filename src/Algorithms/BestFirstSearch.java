package Algorithms;

import Models.Solution;
import Searchable.Searchable;
import Searcher.Searcher;
import State.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


// T is the searchable and S is the step, K is the state
public class BestFirstSearch<T, P> implements Searcher<T, P> {

    public Solution<State<T, P>> search(Searchable<T, P> searchable) {
        //Define Array list of all the visited States
        ArrayList<State<T, P>> visitedStates = new ArrayList<>();
        //Define the Comparator for the
        Comparator<State<T, P>> comparator = searchable.getComperator();
        //Define the Queue with the priorityQueue and use the comparator
        PriorityQueue<State<T, P>> queue = new PriorityQueue<State<T, P>>(10, comparator);
        //Add the State to the queue
        queue.add(searchable.getInitialState());
        while (!queue.isEmpty())
        {
            State<T, P> currentState;
            //Take the first State from the queue
            currentState = queue.poll();
            //Add the currentState to the visitedState list
            visitedStates.add(currentState);
            //Check if the current State is the Goal
            if (currentState.isGoal())
            {
                //return the backTrace of the current State
                return currentState.backTrace();
            }
            //Create array list to the possible States
            ArrayList<State<T, P>> possibleStates = searchable.getAllPossibleStates();
            for (State<T, P> state : possibleStates)
            {
                //Check if the State Neighbor is already in the queue
                if(!queue.contains(state))
                {
                    state.setCameFrom(currentState);
                    queue.add(state);
                    visitedStates.add(state);
                }
                //Check if the State Neighbor is already in the visited
                else if (!visitedStates.contains(state))
                {
                    visitedStates.add(state);
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
