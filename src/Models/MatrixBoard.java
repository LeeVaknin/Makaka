package Models;

import Models.Board;
import Models.State;

import java.util.ArrayList;

public class MatrixBoard extends Board<Pipe[][]> {

    public MatrixBoard(Integer id, Pipe[][] board) {
        super(id, board);
    }

    public MatrixBoard(Integer id, String board) {
        super(id, board);
    }

    @Override
    public String toString() {
        String result = "";
        for (Pipe[] pipes : this.board) {
            for (Pipe pipe : pipes) {
                result.concat(pipe.getPipeVal().toString());
            }
        }
        return result;
    }

    @Override
    protected Pipe[][] toBoard(String strBoard) {

        return new Pipe[0][];
    }
}
