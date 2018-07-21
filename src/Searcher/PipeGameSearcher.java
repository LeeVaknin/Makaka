package Searcher;

import Models.MatrixBoard;
import Models.Step;
import Searchable.Searchable;
import Models.Solution;
import Models.State;

import java.util.PriorityQueue;

public class PipeGameSearcher implements Searcher<MatrixBoard, Step> {

    // Variables
    protected PriorityQueue<State<MatrixBoard>> openList;
    private int evaluatedNodes;

    // C-TOR
    public PipeGameSearcher() {
        openList = new PriorityQueue<State<MatrixBoard>>();
        evaluatedNodes = 0;
    }

    // Methods
    protected State popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }

    @Override
    public Solution<Step> search(Searchable<MatrixBoard> searchable) {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {

        return 0;
    }
}
