package Models;

import Models.Board;
import Models.State;

import java.util.ArrayList;

public class MatrixBoard extends Board<Pipe[][]> {

    public MatrixBoard(Pipe[][] board) {
        this.setBoard(board);
    }


}
