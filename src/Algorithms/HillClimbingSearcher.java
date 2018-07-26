package Algorithms;

import Models.Solution;
import Models.State;
import Searchable.Searchable;
import Searcher.Searcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class HillClimbingSearcher<T, S> implements Searcher<T, S> {



    Stack<State> open;
    HashSet<String> visitedStates; // states that already evaluated

    @Override
    public Solution<S> search(Searchable<T> searchable) {

        visitedStates = new HashSet<String>();
        open = new Stack<State>();
        open.push(searchable.getInitialState());

        while(!open.isEmpty())
        {
            State tmpState = open.pop();
            //visitedStates.add(v.toString());
            //System.out.println("adding to Visited list: " + v.toString());

            //System.out.println("This is V that we poped");

            // Validate we haven't handle this state already
            if(!visitedStates.contains(tmpState))
            {
                if(searchable.isGoal(tmpState))
                {
                    //System.out.println("done! goal");
                    return searchable.backTrack(tmpState);
                }
                ArrayList<State> list = new ArrayList<>();
                list = tmpState.getNeighbors();
                //System.out.println("Printing list before reorer");
                printList(list);
                //System.out.println("Printing list After reorer");
                list.sort(searchable.getComp());//diffrence between dfs to
                printList(list);
                //System.out.println("This is v's neighbors");

                for(State s:list)
                {
                    if(!visitedStates.contains(s.toString())){
                        s.setCameFrom(tmpState);
                        //System.out.println("p: " + v.getLocation().toString());
                        open.push(s);
                        //System.out.println("me:" + s.getLocation().toString());
                    }
                }
            }
        }

        return null;
    }












    /*



    @Override
    public Solution<S> search(Searchable<T> searchable) {


        Solution<S> resultPath = new Solution<>();

        State<T> rootSolution = searchable.getInitialState();
       // resultPath.add(rootSolution);


        State currentState = searchable.getState();
        boolean noStateFound = false;

        // Run until we find the goal or there is no more states
        while (!currentState.getState().equals(searchable.getGoalState()) || noStateFound) {
            noStateFound = true;
            // Initial list with all possible state from current state
            ArrayList<State<T>> nextStates = searchable.getAllPossibleStates();
            if (nextStates.size() > 0) {
                noStateFound = false;
                State<T> tmpState = nextStates.get(0);
                int minNumOfSteps = 0;
                // For each possible state, look for the best one according to heuristic function
                for (State<T> state : nextStates) {
                    if(state.getHeuristicsValue(state.getState()) < minNumOfSteps) {
                        minNumOfSteps = state.getHeuristicsValue();
                        state.setCameFrom(currentState); // Not sure if current state gets update
                        // tmpState will be the best state to go to from all nextStates (our possible states)
                        tmpState = state;
                    }
                }
                // Add the best choose to our result path
                resultPath.add(tmpState);
            }
        }
        return resultPath;


        return null;
    }

    public int getHeuristicsValue(State<T> currentState, State<T> goalStateStack) {

        Integer heuristicValue = 0;
        heuristicValue = currentState.stream()
                .mapToInt(stack -> {
                    return getHeuristicsValueForStack(
                            stack, currentState, goalStateStack);
                }).sum();

        return heuristicValue;
    }

    public int getHeuristicsValueForStack(State<T> state, ArrayList<State<T>> currentState, State<T> goalState) {

        int statekHeuristics = 0;
        boolean isPositioneCorrect = true;
        int goalStartIndex = 0;

        for (State<T> currentBlock : currentState) {
            if (isPositioneCorrect
                    && currentBlock.equals(goalState)) {
                statekHeuristics += goalStartIndex;
            } else {
                statekHeuristics -= goalStartIndex;
                isPositioneCorrect = false;
            }
            goalStartIndex++;
        }
        return statekHeuristics;
    }
*/
    @Override
    public int getNumberOfNodesEvaluated() {
        return 0;
    }

}
