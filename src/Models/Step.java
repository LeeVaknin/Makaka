package Models;

// This class contains the description of the steps we take in our solution to the problem

public class Step {

    // variables
    private Integer rotations;
    private Position position;

    // C-TOR
    public Step(Position position, int rotations) {
        this.position = position;
        this.rotations = rotations;
    }

    // Setters and getters
    public int getRotations() {
        return rotations;
    }

    public void setRotations(int rotations) {
        this.rotations = rotations;
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
                this.rotations.toString(),
                this.position.getCol().toString(),
                this.position.getRow().toString());
    }
}
