package Searcher;
import Board.MatrixBoard;
import Models.Step;
import Models.Solution;
import Searchable.Searchable;
import State.State;

import java.util.PriorityQueue;

public class PipeGameSearcher implements Searcher<MatrixBoard> {

    // Variables
    protected PriorityQueue<State<MatrixBoard>> openList;
    private int evaluatedNodes;

    // C-TOR
    public PipeGameSearcher() {
        openList = new PriorityQueue<State<MatrixBoard>>();
        evaluatedNodes = 0;
    }

    // Methods
    protected State<MatrixBoard> popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }


    @Override
    public Solution<State<MatrixBoard>> search(Searchable<MatrixBoard> s) {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {

        return 0;
    }
}
