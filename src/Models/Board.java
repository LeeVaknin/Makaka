package Models;


public abstract class Board<T> {

    public Board(Integer id, T board) {
        setBoard(board);
        setId(id);
    }

    public Board(Integer id, String board) {
        setBoard((toBoard(board)));
        setId(id);
    }

    protected Integer id;
    protected T board;

    public T getBoard() {
        return board;
    }

    public void setBoard(T board) {
        this.board = board;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected abstract T toBoard(String strBoard);

}
