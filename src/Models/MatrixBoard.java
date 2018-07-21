package Models;

import Models.Board;
import Models.State;
import com.oracle.webservices.internal.api.message.PropertySet;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Property;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.ArrayList;

public class MatrixBoard extends Board<Pipe[][]> {

    public MatrixBoard(Pipe[][] board) {
        super(board);
    }

    public MatrixBoard(MatrixBoard tmpBoard) {
        setBoard(tmpBoard.getBoard());
        setId(tmpBoard);
    }

    public MatrixBoard(String board) {
        super(board);
    }

    @Override
    public void setBoard(Pipe[][] tmpBoard) {
        try {
            int length = tmpBoard.length;
            board = new Pipe[length][length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    board[i][j] = new Pipe(tmpBoard[i][j]);
                }
            }
        } catch (Exception ex) {
            System.out.println("MatrixBoard.toBoard(): Error details: " + ex.getMessage());
        }
    }

    public int length() {
        return board.length;
    }

    @Override
    public String toString() {
        String result = null;
        try {
            result = "";
            for (Pipe[] pipes : this.board) {
                for (Pipe pipe : pipes) {
                    result.concat(pipe.getPipeVal().toString());
                }
            }
        } catch (Exception ex) {
            System.out.println("MatrixBoard.toBoard(): Error details: " + ex.getMessage());
        }
        return result;
    }

    @Override
    protected Pipe[][] toBoard(String strBoard) {
        Pipe[][] tmpBoard = null;
        try {
            int length = (int) Math.sqrt(strBoard.length());
            tmpBoard = new Pipe[length][length];
            int strLocation = 0;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    tmpBoard[i][j] = new Pipe(strBoard.charAt(strLocation));
                    strLocation++;
                }
            }
        } catch (Exception ex) {
            System.out.println("MatrixBoard.toBoard(): Error details: " + ex.getMessage());
        }
        return tmpBoard;
    }

    public boolean equals(Pipe[][] pipes) {
        boolean isEqual = false;
        int length = pipes.length;
        try {
            compare:
            {
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < length; j++) {
                        isEqual = this.board[i][j].equals(pipes[i][j]);
                        if (!isEqual) break compare;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("MatrixBoard.equals(): Error details: " + ex.getMessage());
        }
        return isEqual;
    }
}
