package Board;
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
        setBoard(this.toBoard(board));
        setId(board);
        setStart(this.findStartPosition());
        setEnd(this.findEndPosition());
    }

    // Methods

    // Returns the value of the pipe in the given position

    public Pipe getPipe(Position position) {
        if (position != null && isValidPosition(position)) {
            Integer col = position.getCol();
            Integer row = position.getRow();
            return this.board[row][col];
        }
        return null;
    }

    public void setPipe(Position position, Character pipe) {
        Integer col = position.getCol();
        Integer row = position.getRow();
        this.board[row][col].setPipeVal(pipe);
    }

    @Override
    void setPermitted() {

        // Map the currentPosition - to allowed steps
        HashMap<Character, String> top = new HashMap<Character, String>() {{
            put('|', "|gF7");
            put('L', "F7g|");
            put('J', "Fg|7");
            put('s', "7F|g");
        }};
        HashMap<Character, String> bottom = new HashMap<Character, String>() {{
            put('|', "|gLJ");
            put('F', "|JgL");
            put('7', "|JLg");
            put('s', "LJ|g");
        }};
        HashMap<Character, String> right = new HashMap<Character, String> () {{
            put('L', "J7g-");
            put('F', "J7g-");
            put('-', "J7g-");
            put('s', "J7g-");

        }};
        HashMap<Character, String> left = new HashMap<Character, String>() {{
            put('-', "-gFL");
            put('J', "-gFL");
            put('7', "-gFL");
            put('s', "-gFL");
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
            Integer row = tmpBoard.length;
            Integer col = tmpBoard[0].length;
            board = new Pipe[row][col];
            for (Integer i = 0; i < row; i++) {
                for (Integer j = 0; j < col; j++) {
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
                    String permittedStep = this.permittedSteps.get(direction).get(getPipe(from).getPipeVal());
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
        try {
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
        } catch (Exception ex) {
            System.out.println(String.join(": ", "MatrixBoard.classifyMoveDirection(): Error details", ex.getMessage()));
        }
        return null;
    }

    public Integer rows() {
        return board.length;
    }

    public Integer columns() {
        return board[0].length;
    }

    @Override
    public String toString() {
        String result = null;
        try {
            result = "";
            for (Pipe[] pipes : this.board) {
                for (Pipe pipe : pipes) {
                    result = result.concat(pipe.getPipeVal().toString());
                }
                result = result.concat(System.lineSeparator());
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
            Integer row = splitterBord.length;
            Integer col = splitterBord[0].length();

            if (row <= 0 || col <= 0)
                throw new Exception("Invalid length for building matrix bord ");

            // Initial bord with all strBoard values
            tmpBoard = new Pipe[row][col];
            for (Integer i = 0; i < row; i++) {
                String tmpLine = splitterBord[i];
                if (tmpLine == null)
                    throw new Exception(String.join(": ", "The following line is null at the matrix board", i.toString()));
                for (Integer j = 0; j < col; j++) {
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
                    if (pipe.getPipeVal().equals('s'))
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
                    if (pipe.getPipeVal().equals('g'))
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
        Integer row = pipes.length;
        Integer col = pipes[0].length;
        try {
            compare:
            {
                for (Integer i = 0; i < row; i++) {
                    for (Integer j = 0; j < col; j++) {
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
