package Models;
import Utils.HashManager;

public abstract class Board<T> {

    protected String id;
    protected T board;
    protected Pipe from = new Pipe(new Position(0,0));
    protected Pipe to = new Pipe(new Position(0,0));


    // C-TOR
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


    // Setters and Getters
    public T getBoard() {
        return board;
    }

    public abstract void setBoard(T board);

    public String getId() {
        return id;
    }

    public void setId(String board) {
        this.id = HashManager.getId(board);
    }

    public void setId(Board<T> board) {
        this.id = board.getId();
    }

    public Pipe getFrom() {
        return from;
    }

    public Pipe getTo() {
        return to;
    }

    public void setFrom(Pipe from) {
        this.from = from;
    }

    public void setTo(Pipe to) {
        this.to = to;
    }

    protected abstract T toBoard(String strBoard);
}
