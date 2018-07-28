package Models;

//TODO : Consider of having interface for this class

public class Position {

    // Variables
    private Integer row;
    private Integer col;

    // C-TOR
    public Position(Integer col, Integer row) {
        this.setCol(col);
        this.setRow(row);
    }

    public Position(Position position) {
        if (position != null) {
            this.setCol(position.getCol());
            this.setRow(position.getRow());
        }
        this.setCol(0);
        this.setRow(0);
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

    public Position getPositionUp() {
        return new Position(this.col, (this.row - 1));
    }
    public Position getPositionRight() {
        return new Position((this.col + 1) , this.row);
    }
    public Position getPositionLeft() {
        return new Position((this.col - 1) , this.row);

    }
    public Position getPositionDown() {
        return new Position(this.col, (this.row + 1));
    }
}
