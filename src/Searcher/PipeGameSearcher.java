package Searcher;
import Board.MatrixBoard;
import Board.Position;
import Models.Solution;
import Searchable.Searchable;
import State.State;

import java.util.PriorityQueue;

public class PipeGameSearcher implements Searcher<MatrixBoard, Position> {

    // Variables
    protected PriorityQueue<State<MatrixBoard, Position>> openList;
    private int evaluatedNodes;

    // C-TOR
    public PipeGameSearcher() {
        openList = new PriorityQueue<State<MatrixBoard, Position>>();
        evaluatedNodes = 0;
    }

    // Methods
    protected State<MatrixBoard, Position> popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }


    @Override
    public Solution<State<MatrixBoard, Position>> search(Searchable<MatrixBoard, Position> s) {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {

        return 0;
    }
}
