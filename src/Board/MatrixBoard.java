package Board;

import Models.Pipe;
import Models.Position;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class MatrixBoard extends Board<Pipe[][]> {

    private Position end;
    private Position start;

    // C-TOR
    public MatrixBoard(Pipe[][] board) {
        super(board);
    }

    public MatrixBoard(MatrixBoard tmpBoard) {
        setBoard(tmpBoard.getBoard());
        setId(tmpBoard);
        setStart(tmpBoard.findStartPosition());
        setEnd(tmpBoard.findEndPosition());
    }

    public MatrixBoard(String board) {
        // super(board);
        setBoard(this.toBoard(board));
    }

    // Methods

    // Returns the value of the pipe in the given position
    public Character getPipe(Position position) {
        int col = position.getCol();
        int row = position.getRow();
        return this.board[row][col].getPipeVal();
    }

    @Override
    void setPermitted() {

        // Map the from - to allowed steps
        HashMap<Character, String> top = new HashMap<Character, String>() {{
            put('|', "|g");
            put('L', "JFg");
            put('F', "7");
            put('J', "FgL");
            put('7', "F");
            put('s', "7F|g");
        }};
        HashMap<Character, String> bottom = new HashMap<Character, String>() {{
            put('|', "|g");
            put('L', "J");
            put('F', "7gL");
            put('J', "L");
            put('7', "FJg");
            put('s', "LJ|g");
        }};
        HashMap<Character, String> right = new HashMap<Character, String> () {{
            put('L', "JFg");
            put('F', "7Lg");
            put('J', "7");
            put('7', "J");
            put('-', "-g");
            put('s', "7J-g");

        }};
        HashMap<Character, String> left = new HashMap<Character, String>() {{
            put('-', "-g");
            put('L', "F");
            put('F', "L");
            put('J', "FLg");
            put('7', "JFg");
            put('s', "7F|g");
        }};
        // Save the allowed steps by direction
        this.permittedSteps = new HashMap<String, HashMap<Character, String >>() {{
            put("top", top);
            put("bottom", bottom);
            put("left", left);
            put("right", right);
        }};
    }

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = new Position(start);
    }

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = new Position(end);
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
    public boolean isValidMove(Position from, Position to) {
        try {
            // First verify all the parameters are valid
            if (this.isValidBoard() && this.isValidPosition(from) && this.isValidPosition(to)) {
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
        } catch (Exception ex) {
            System.out.println(String.join(": ", "MatrixBoard.isValidMove(): Error details", ex.getMessage()));
        }
        return false;
    }

    @Nullable
    private String classifyMoveDirection(@NotNull Position from, @NotNull Position to) {
        // In case we move up or down
        if (from.getCol().equals(to.getCol())) {
            if (from.getRow() + 1 == to.getRow()) {
                return "bottom";
            }
            if (from.getRow() - 1 == to.getRow()) {
                return "top";
            }
            // In case we move right or left
        } else if (from.getRow().equals(to.getRow())) {
            if (from.getCol() + 1 == to.getCol()) {
                return "right";
            }
            if (from.getCol() - 1 == to.getCol()) {
                return "left";
            }
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
                result.concat(System.lineSeparator());
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
            if (strBoard == null)
                throw new Exception("Bord string is empty");

            // Get values for col and row according to the given string
            String[] splitterBord = strBoard.split(System.lineSeparator());
            int row = splitterBord.length;
            int col = splitterBord[0].length();

            if (row <= 0 || col <= 0)
                throw new Exception("Invalid length for building matrix bord ");

            // Initial bord with all strBoard values
            tmpBoard = new Pipe[row][col];
            for (Integer i = 0; i < row; i++) {
                String tmpLine = splitterBord[i];
                if (tmpLine == null)
                    throw new Exception(String.join(": ", "The following line is null at the matrix board", i.toString()));
                for (int j = 0; j < col; j++) {
                    tmpBoard[i][j] = new Pipe(tmpLine.charAt(j));
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

    public Position findStartPosition() {
        try {
            if (this.getBoard() == null)
                throw new Exception("Board is NULL");
            // Look for the pipe with the end sign
            for (Pipe[] pipes : this.getBoard()) {
                for (Pipe pipe : pipes) {
                    if (pipe.getPipeVal().equals("s"))
                        return new Position(pipe.getPosition());
                }
            }
        } catch (Exception ex) {
            System.out.println(String.join(": ", "Position.findStartPosition(): Error details", ex.getMessage()));
        }
        return null;
    }

    public Position findEndPosition() {
        try {
            if (this.getBoard() == null)
                throw new Exception("Board is NULL");
            // Look for the pipe with the end sign
            for (Pipe[] pipes : this.getBoard()) {
                for (Pipe pipe : pipes) {
                    if (pipe.getPipeVal().equals("g"))
                        return new Position(pipe.getPosition());
                }
            }
        } catch (Exception ex) {
            System.out.println(String.join(": ", "Position.findEndPosition(): Error details", ex.getMessage()));
        }
        return null;
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
