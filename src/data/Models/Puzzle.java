package data.Models;

import java.util.ArrayList;

public class Puzzle implements Searchable{

    PuzzlePiece puzzlePiece;

    /////////
    //Methods
    /////////

    public PuzzlePiece getPuzzlePiece() {
        return puzzlePiece;
    }

    public void setPuzzlePiece(PuzzlePiece puzzlePiece) {
        this.puzzlePiece = puzzlePiece;
    }

    @Override
    public void initState() {

    }

    @Override
    public void isGoal() {

    }

    @Override
    public void getState() {

    }

    @Override
    public ArrayList<Level> getAllStates(Level level) {
        return null;
    }
}
