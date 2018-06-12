package Solver;
import Models.Searchable.Searchable;
import Models.Solution;
import Models.State;
import Searcher.Searcher;


public class CommonSolver<T, S> implements Solver<T, S>{

    // This searcher is injected into the common solver and solves the given problem
    private Searcher<T,S> searcher;


    // C-TOR

    public CommonSolver(Searcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public Solution<S> solve(Searchable<T> searchable) {
        return this.searcher.search(searchable);
    }

    @Override
    public void createProblem() {

    }
}
