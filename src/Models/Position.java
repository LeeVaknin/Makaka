package Models;

public class Position {

    // Variables

    private int row;
    private int col;

    // Setters and Getters

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    // C-TOR

    Position(int col, int row) {
        this.row = row;
        this.col = col;
    }


}
