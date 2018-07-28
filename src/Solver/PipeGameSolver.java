package Solver;
import Board.MatrixBoard;
import Models.*;
import Searchable.PipeSearchable;
import Searcher.PipeGameSearcher;
import State.PipeGameState;


// common solver for the pipe game
public class PipeGameSolver implements Solver<PipeSearchable, Step>{

    // This searcher is injected into the common solver and solves the given problem
    private PipeGameSearcher searcher;

    // C-TOR
    public PipeGameSolver(PipeGameSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public Solution<Step> solve(PipeSearchable searchable) {
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
