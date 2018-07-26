package Models;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

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

    // Returns the value of the pipe in the given position
    public Character getPipe(Position position) {
        int col = position.getCol();
        int row = position.getRow();
        return this.board[row][col].getPipeVal();
    }

    @Override
    void setPermitted() {
        HashMap<Character, String> top = new HashMap<>();
        HashMap<Character, String> bottom = new HashMap<>();
        HashMap<Character, String> right = new HashMap<>();
        HashMap<Character, String> left = new HashMap<>();

        // Map the from - to allowed steps
        top.put('|', "|");
        top.put('L', "JF");
        top.put('F', "7");
        top.put('J', "FL");
        top.put('7', "F");

        left.put('-', "-");
        left.put('L', "F");
        left.put('F', "L");
        left.put('J', "FL");
        left.put('7', "JF");

        right.put('-', "-");
        right.put('L', "JF");
        right.put('F', "7L");
        right.put('J', "7");
        right.put('7', "J");

        bottom.put('|', "|");
        bottom.put('L', "J");
        bottom.put('F', "7L");
        bottom.put('J', "L");
        bottom.put('7', "FJ");

        this.permittedSteps = new HashMap<>();

        // Save the allowed steps by direction
        this.permittedSteps.put("top", top);
        this.permittedSteps.put("bottom", bottom);
        this.permittedSteps.put("left", left);
        this.permittedSteps.put("right", right);
    }

    @Override
    public void setBoard(Pipe[][] tmpBoard) {
        try {
           int row = tmpBoard.length;
           int col = tmpBoard[0].length;
            board = new Pipe[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    board[i][j] = new Pipe(tmpBoard[i][j]);
                }
            }
        } catch (Exception ex) {
            System.out.println("MatrixBoard.toBoard(): Error details: " + ex.getMessage());
        }
    }

    @Override
    public boolean isValidMove(Board<Pipe[][]> board, Position from, Position to) {

        // First verify all the parameters are valid
        if (board.isValidBoard() || this.isValidPosition(from) || this.isValidPosition(to)) {
            // Check what is the direction of the given move, verify it's legal and return result.
            String direction = this.classifyMoveDirection(from, to);
            if (direction != null) {
                // Get the permitted steps in the taken direction
                String permittedStep = this.permittedSteps.get(direction).get(getPipe(from));
                if (permittedStep != null) {
                    // Validate that the 'to' position is allowed by the mapping
                    return permittedStep.contains(getPipe(to).toString());
                }
            }
        }

        return false;
    }

    @Nullable
    private String classifyMoveDirection(@NotNull Position from, @NotNull Position to) {
        // In case we move up or down
        if (from.getCol().equals(to.getCol())) {
            if (from.getRow() + 1 == to.getRow()) { return "bottom" ;}
            if (from.getRow() - 1 == to.getRow()) { return "top" ;}
            // In case we move right or left
        } else if (from.getRow().equals(to.getRow())) {
            if (from.getCol() + 1 == to.getCol()) { return "right" ;}
            if (from.getCol() - 1 == to.getCol()) { return "left" ;}
        }
        return null;
    }

    public int rows() {
        return board.length;
    }

    public int columns() {
        return board[0].length;
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

    @Override
    boolean isValidBoard() {
        return (this.board != null && this.rows() > 0 && this.columns() > 0);
    }

    @Override
    boolean isValidPosition(Position position) {
        return (position != null && position.getRow() < this.rows() && position.getCol() < this.columns());
    }

    public boolean equals(Pipe[][] pipes) {
        boolean isEqual = false;
        int row = pipes.length;
        int col = pipes[0].length;
        try {
            compare:
            {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
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
