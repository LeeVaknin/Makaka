package Models;

// This class contains the description of the steps we take in our solution to the problem

public class Step {

    // variables
    private Integer numOfRotations;
    private Position position;

    // C-TOR
    public Step(Position position, int numOfRotations) {
        this.position = position;
        this.numOfRotations = numOfRotations;
    }

    // Setters and getters
    public int getNumOfRotations() {
        return numOfRotations;
    }

    public void setNumOfRotations(int numOfRotations) {
        this.numOfRotations = numOfRotations;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    @Override
    public String toString() {
        return String.join(" ",
                this.numOfRotations.toString(),
                this.position.getCol().toString(),
                this.position.getRow().toString());
    }
}
