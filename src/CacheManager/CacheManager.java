package CacheManager;

import data.Models.Puzzle;
import data.Models.PuzzleState;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CacheManager {


    /////////
    //Methods
    /////////

    public void save(String problem) throws IOException;
    public String load();


}
