package Algorithms;

import Models.State;
import Models.Solution;
import Searchable.Searchable;
import Searcher.Searcher;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFSSearcher<T,S> implements Searcher<T,S> {

    public Solution<S> search(Searchable<T> s)
    {
        //Define Array list of all the visited States
        ArrayList<T> visitedStates = new ArrayList<T>();
        //Define the Queue for the discover States
        LinkedList<State<T>> queue = new LinkedList<State<T>>();
        //The first state -Initial state
        State<T> rootSolution = s.getInitialState();
        //Add the first state to the visited States
        visitedStates.add(rootSolution.getState());
        //Add the first State to the queue
        queue.add(rootSolution);
        //Run the algorithm while the queue is not ampty
        while(!queue.isEmpty())
        {
            State<T> currentState;
            //get the first one in queue
            currentState = queue.poll();
            s.setCurrentState(currentState);
            //chekce if the current state is the goal
            if (currentState.getState() == s.getGoalState().getState())
            {
                //the return - need to add it!!!
                //TODO:need to change when Solution is finalize
                //  return currentState;
                return null;
            }
            //Create array list to the possible States
            ArrayList<State<T>> possibleStates = s.getAllPossibleStates();
            for (State<T> state : possibleStates)
            {
                //to have all the states that I came from them.
                state.setCameFrom(currentState);
                //check if the possible state is already in the visited states
                if(!visitedStates.contains(state.getState()))
                {
                    //add it to the visible state
                    visitedStates.add(state.getState());
                    //add it to the queue
                    queue.add(state);

                }
            }
        }
        return new Solution<S>();
    }

   //TODO: fix this and understand if needed
    public int getNumberOfNodesEvaluated()
    {
        return 0;
    }



}
