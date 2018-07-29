package Solver;
import Board.MatrixBoard;
import Models.*;
import Searchable.PipeSearchable;
import Searchable.Searchable;
import Searcher.Searcher;
import State.PipeGameState;
import State.State;


// common solver for the pipe game
public class PipeGameSolver implements Solver<MatrixBoard>{

    // This searcher is injected into the common solver and solves the given problem
    private Searcher<MatrixBoard> searcher;

    // C-TOR
    public PipeGameSolver(Searcher<MatrixBoard> searcher) {
        this.searcher = searcher;
    }

    @Override
    public Solution<State<MatrixBoard>> solve(Searchable<MatrixBoard> searchable) {
        return this.searcher.search(searchable);
    }

    @Override
    public Solution<State<MatrixBoard>> solve(String problem) {
        MatrixBoard board = new MatrixBoard(problem);
        PipeGameState state = new PipeGameState(board);
        PipeSearchable searchable = new PipeSearchable(state);
        return this.solve(searchable);
    }
}
