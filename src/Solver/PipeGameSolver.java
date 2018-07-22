package Solver;
import Models.*;
import Searchable.Searchable;
import Searchable.PipeSearchable;
import Searcher.Searcher;


// common solver for the pipe game
public class PipeGameSolver implements Solver<MatrixBoard, Step>{

    // This searcher is injected into the common solver and solves the given problem
    private Searcher<MatrixBoard, Step>  searcher;

    // C-TOR
    public PipeGameSolver(Searcher<MatrixBoard, Step> searcher) {
        this.searcher = searcher;
    }

    @Override
    public Solution<Step> solve(Searchable<MatrixBoard> searchable) {
        return this.searcher.search(searchable);
    }

    @Override
    public Solution<Step> solve(String problem) {
        MatrixBoard board = new MatrixBoard(problem);
        State<MatrixBoard> state = new State<>(board);
        PipeSearchable searchable = new PipeSearchable(state);
        return this.solve(searchable);
    }

}
