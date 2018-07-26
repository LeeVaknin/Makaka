package Models;

//TODO : Consider of having interface for this class

public class Position {

    // Variables
    private Integer row;
    private Integer col;

    // C-TOR
    public Position(Integer col, Integer row) {
        this.row = row;
        this.col = col;
    }

    public Position(Position position) {
        this.row = position.getRow();
        this.col = position.getCol();
    }

    // Setters and Getters
    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

}
