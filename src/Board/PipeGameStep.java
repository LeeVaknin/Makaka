package Board;

public class PipeGameStep extends Step<Position> {

    private int rotations;

    public PipeGameStep(Position position, int rotations) {
        this.setRotations(rotations);
        this.setPosition(position);
    }

    public int getRotations() {
        return rotations;
    }

    public void setRotations(int rotations) {
        this.rotations = rotations;
    }

    @Override
    public void setPosition(Position position) {
        if (position != null) {
            this.position = new Position(position);
        }
    }
}
