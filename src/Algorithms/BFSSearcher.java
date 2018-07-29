package Algorithms;

import State.State;
import Models.Solution;
import Searchable.Searchable;
import Searcher.Searcher;

import java.util.ArrayList;
import java.util.LinkedList;


public class BFSSearcher<T> implements Searcher<T> {

    public Solution<State<T>> search(Searchable<T> searchable)
    {
        //Define Array list of all the visited States
        ArrayList<State<T>> visitedStates = new ArrayList<>();
        //Define the Queue for the discover States
        LinkedList<State<T>> queue = new LinkedList<State<T>>();
        //The first state -Initial state
        State<T> rootSolution = searchable.getInitialState();
        //Add the first state to the visited States
        visitedStates.add(rootSolution);
        //Add the first State to the queue
        queue.add(rootSolution);
        //Run the algorithm while the queue is not empty
        while(!queue.isEmpty())
        {
            State<T> currentState;
            //get the first one in queue
            currentState = queue.poll();
            searchable.setCurrentState(currentState);
            //check if the current state is the goal
            if (currentState.isGoal())
            {
                //return the backTrace of the current State
                return currentState.backTrace();
            }
            //Create array list to the possible States
            ArrayList<State<T>> possibleStates = searchable.getAllPossibleStates();
            for (State<T> state : possibleStates)
            {
                //to have all the states that I came from them.
                state.setCameFrom(currentState);
                //check if the possible state is already in the visited states
                if(!queue.contains(state) || !visitedStates.contains(state))
                {
                    //add it to the visible state
                    visitedStates.add(state);
                    //add it to the queue
                    queue.add(state);

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
