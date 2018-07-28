package Searcher;
import Models.MatrixBoard;
import Models.Step;
import Searchable.PipeSearchable;
import Models.Solution;
import State.PipeGameState;

import java.util.PriorityQueue;

public class PipeGameSearcher implements Searcher<PipeSearchable, Step> {

    // Variables
    protected PriorityQueue<PipeGameState> openList;
    private int evaluatedNodes;

    // C-TOR
    public PipeGameSearcher() {
        openList = new PriorityQueue<PipeGameState>();
        evaluatedNodes = 0;
    }

    // Methods
    protected PipeGameState popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }

    @Override
    public Solution<Step> search(PipeSearchable searchable) {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {

        return 0;
    }
}
