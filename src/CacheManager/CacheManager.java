package CacheManager;

import Models.Solution;

import java.io.IOException;

public interface CacheManager<S> {


    /////////
    //Methods
    /////////

    String saveSolution(String id, S solution) throws IOException;
    String loadSolution(String id) throws IOException;

}
