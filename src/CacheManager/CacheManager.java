package CacheManager;

import Models.Board;
import Models.Solution;
import Models.Step;

import java.io.IOException;

public interface CacheManager<S> {


    /////////
    //Methods
    /////////

    String saveSolution(String id, Solution<S> solution) throws IOException;
    String loadSolution(String id) throws IOException;

}
