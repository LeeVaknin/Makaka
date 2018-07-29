package Algorithms;

import Models.Solution;
import Searchable.Searchable;
import Searcher.Searcher;
import State.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


// T is the searchable and S is the step, K is the state
public class BestFirstSearch<T> implements Searcher<T> {

    public Solution<State<T>> search(Searchable<T> searchable) {
        //Define Array list of all the visited States
        ArrayList<State<T>> visitedStates = new ArrayList<>();
        //Define the Comparator for the
        Comparator<State<T>> comparator = searchable.getComperator();
        //Define the Queue with the priorityQueue and use the comparator
        PriorityQueue<State<T>> queue = new PriorityQueue<State<T>>(10, comparator);
        //Add the State to the queue
        queue.add(searchable.getInitialState());
        while (!queue.isEmpty())
        {
            State<T> currentState;
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
            ArrayList<State<T>> possibleStates = searchable.getAllPossibleStates();
            for (State<T> state : possibleStates)
            {
                //Check if the State Neighbor is already in visitedState and in the queue
                if(!queue.contains(state) || !visitedStates.contains(state))
                {
                    //Add to State that it came from the current state
                    state.setCameFrom(currentState);
                    //Add the state to the queue
                    queue.add(state);
                    //Add the state ti the visited States
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
