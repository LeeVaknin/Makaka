package server;

import data.Models.Puzzle;
import data.Models.PuzzleState;

public interface IRepositoryPuzzleService {

    public boolean add(Puzzle puzzle, PuzzleState puzzleState);
    public boolean update(int levelId, Puzzle puzzle, PuzzleState puzzleState);
    public Puzzle get(int levelId, PuzzleState puzzleState);
    public boolean remove(int levelId, PuzzleState puzzleState);





    //TODO: Consider adding MAX ID file manager.

}
