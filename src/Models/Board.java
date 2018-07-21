package Models;


import java.security.MessageDigest;

public abstract class Board<T> {

    public Board() {
        this.id = null;
        this.board = null;
    }

    public Board(T board) {
        setBoard(board);
        setId(board.toString());
    }

    public Board(String board) {
        setBoard((toBoard(board)));
        setId(board);
    }

    protected String id;
    protected T board;

    public T getBoard() {
        return board;
    }

    public abstract void setBoard(T board);

    public String getId() {
        return id;
    }

    public void setId(String board) {
        try {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(board.getBytes());
        this.id = new String(messageDigest.digest());
        } catch (Exception ex) {
            System.out.println("Board.setId(): Error details: " + ex.getMessage());
        }
    }

    public void setId(Board<T> board) {
        this.id = board.getId();

    }
    protected abstract T toBoard(String strBoard);
}
