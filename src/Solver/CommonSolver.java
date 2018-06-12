package Solver;
import Models.MatrixBoard;
import Models.Pipe;
import Models.Step;
import Searchable.Searchable;
import Models.Solution;
import Searcher.Searcher;


// common solver for the pipe game
public class CommonSolver implements Solver<MatrixBoard, Step>{

    // This searcher is injected into the common solver and solves the given problem
    private Searcher<MatrixBoard, Step>  searcher;

    // C-TOR

    public CommonSolver(Searcher<MatrixBoard, Step> searcher) {
        this.searcher = searcher;
    }

    @Override
    public Solution<Step> solve(Searchable<MatrixBoard> searchable) {
        return this.searcher.search(searchable);
    }

    @Override
    // TODO: Ask Igor!!!!
    public void createProblem() {

    }
}
