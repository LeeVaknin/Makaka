package Models;

// This class contains the description of the steps we take in our solution to the problem

public class Step {

    // variables
    private Integer numOfRotations;
    private Position from;
    private Position to;

    // C-TOR
    public Step(Position from, Position to, int numOfRotations) {
        this.from = new Position(from);
        this.to = new Position(to);
        this.numOfRotations = numOfRotations;
    }

    // Setters and getters
    // The number of rotations is the number of rotations in the to position
    public int getNumOfRotations() {
        return numOfRotations;
    }

    public void setNumOfRotations(int numOfRotations) {
        this.numOfRotations = numOfRotations;
    }

    public void setTo(Position to) {
        this.to = to;
    }

    public Position getTo() {
        return to;

    }

    public Position getFrom() {

        return from;
    }

    public void setFrom(Position position) {
        this.from = position;
    }


    @Override
    public String toString() {
        return String.join(" ",
                this.numOfRotations.toString(),
                this.from.getCol().toString(),
                this.from.getRow().toString(),
                this.to.getCol().toString(),
                this.to.getRow().toString());
    }
}
