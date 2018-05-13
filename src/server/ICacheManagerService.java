package server;

import data.Models.Puzzle;
import data.Models.PuzzleState;

public interface ICacheManagerService {


    /////////
    //Methods
    /////////

    public boolean saveState(int id, Puzzle puzzle, PuzzleState puzzleState);
    public Puzzle loadstate(int levelId, PuzzleState puzzleState);
    public boolean hasSolution(int id);


}
