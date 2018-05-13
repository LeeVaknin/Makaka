package server;

import data.Models.Puzzle;
import data.Models.PuzzleState;

public class RepositoryFilePuzzleService implements IRepositoryPuzzleService {




    /////////
    //Methods
    /////////

    @Override
    public boolean add(Puzzle puzzle, PuzzleState puzzleState) {
        return false;
    }

    @Override
    public boolean update(int levelId, Puzzle puzzle, PuzzleState puzzleState) {
        return false;
    }

    @Override
    public Puzzle get(int levelId, PuzzleState puzzleState) {
        return null;
    }

    @Override
    public boolean remove(int levelId, PuzzleState puzzleState) {
        return false;
    }

}
