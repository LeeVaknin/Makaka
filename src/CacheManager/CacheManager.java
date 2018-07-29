package CacheManager;

import Models.Solution;
import State.State;

import java.io.IOException;

public interface CacheManager<T> {


    /////////
    //Methods
    /////////

    String saveSolution(String id, Solution<T> solution) throws IOException;
    String loadSolution(String id) throws IOException;

}
