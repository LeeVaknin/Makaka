package CacheManager;

import Models.Board;
import Models.Solution;
import Models.Step;

import java.io.IOException;

public interface CacheManager<T> {


    /////////
    //Methods
    /////////

    String saveSolution(Board<T> board, Solution<Step> solution) throws IOException;
    Solution<Step> loadSolution(String id) throws IOException;

}
