package Models;

// This class contains the description of the steps we take in our solution to the problem

public class Step {

    // variables

    private int rotations;
    private Position position;

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

    // C-TOR

    Step(Position position, int rotations) {
        this.position = position;
        this.rotations = rotations;
    }

}
