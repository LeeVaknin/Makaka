package Solver;
import Board.MatrixBoard;
import Models.*;
import Searchable.PipeSearchable;
import Searchable.Searchable;
import Searcher.Searcher;
import State.PipeGameState;


// common solver for the pipe game
public class PipeGameSolver implements Solver<MatrixBoard, Step>{

    // This searcher is injected into the common solver and solves the given problem
    private Searcher<MatrixBoard, Step> searcher;

    // C-TOR
    public PipeGameSolver(Searcher<MatrixBoard, Step> searcher) {
        this.searcher = searcher;
    }

    @Override
    public Solution<Step> solve(Searchable<MatrixBoard, Step> searchable) {
        return this.searcher.search(searchable);
    }

    @Override
    public Solution<Step> solve(String problem) {
        MatrixBoard board = new MatrixBoard(problem);
        PipeGameState state = new PipeGameState(board);
        PipeSearchable searchable = new PipeSearchable(state);
        return this.solve(searchable);
    }
}
