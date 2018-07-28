package Searcher;
import Board.MatrixBoard;
import Models.Step;
import Models.Solution;
import Searchable.Searchable;
import State.State;

import java.util.PriorityQueue;

public class PipeGameSearcher implements Searcher<MatrixBoard, Step> {

    // Variables
    protected PriorityQueue<State<MatrixBoard, Step>> openList;
    private int evaluatedNodes;

    // C-TOR
    public PipeGameSearcher() {
        openList = new PriorityQueue<State<MatrixBoard, Step>>();
        evaluatedNodes = 0;
    }

    // Methods
    protected State<MatrixBoard, Step> popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }

    @Override
    public Solution<Step> search(Searchable<MatrixBoard, Step> searchable) {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {

        return 0;
    }
}
