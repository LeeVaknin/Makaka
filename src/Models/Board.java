package Models;

import Models.State;

import java.util.ArrayList;

public abstract class Board<T> {

    public T getBoard() {
        return board;
    }

    public void setBoard(T board) {
        this.board = board;
    }

    protected T board;

}
