package State;
import Board.MatrixBoard;
import Models.Position;
import Models.Solution;
import Models.Step;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class PipeGameState extends State<MatrixBoard> {

    private Step stepToState;
    protected Position currentPosition;

    // C-TOR

    public PipeGameState(MatrixBoard state) {
        this.setState(state);
        this.setCurrentPosition(null);
    }

    public PipeGameState(State<MatrixBoard> pipeGameState) {
        if (pipeGameState != null) {
            this.setState(pipeGameState.getState());
            this.setCameFrom(pipeGameState.getCameFrom());
            this.setCurrentPosition(((PipeGameState)pipeGameState).getCurrentPosition());
        }
    }

    // Methods
    // Added method which sets the default currentPosition to be the start of the board.
    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        if (currentPosition != null) {
            this.currentPosition = new Position(currentPosition);
            return;
        }
        this.currentPosition = this.state.getStart();
    }

    public void setState(MatrixBoard state) {
        if (state != null) {
            this.state = new MatrixBoard(state);
        }
    }

    public boolean equals(State<MatrixBoard> state) {
        if (state == null) { return false; }
        return this.state.equals(state.state);
    }

    private ArrayList<Position> initializeFromDirections() {
        Position currentLocation = this.getCurrentPosition();
        Position up = new Position(currentLocation.getPositionUp());
        Position down = new Position(currentLocation.getPositionDown());
        Position left = new Position(currentLocation.getPositionLeft());
        Position right = new Position(currentLocation.getPositionRight());

        ArrayList<Position> directions = new ArrayList<Position>() {{
            add(up);
            add(down);
            add(right);
            add(left);
        }};
        return directions;
    }

//  Returns a backTrace of the states for the algorithms
    public Solution<State<MatrixBoard>> backTrace() {
        Solution<State<MatrixBoard>> returnBackTrace = new Solution<>();
        State<MatrixBoard> tmp = this.getCameFrom();
        Character pipeVal = ' ';
        // TODO : Do we need the first protection at the while loop ?
        while (!pipeVal.equals('s') || tmp != null) {
            returnBackTrace.add(tmp);
            tmp = tmp.getCameFrom();
            Position currentPosition = ((PipeGameState)tmp).getCurrentPosition();
            pipeVal = currentPosition != null ? tmp.getState().getPipe(currentPosition).getPipeVal() : null;
        }
        Collections.reverse(returnBackTrace);
        return returnBackTrace;
    }

    public ArrayList<State<MatrixBoard>> getAllNeighbors() {
        ArrayList<State<MatrixBoard>> allNeighbors = null;
        try {

            allNeighbors = new ArrayList<>();
            // Check what are my options of moves up, down, left and right
            Position currentLocation = this.getCurrentPosition();
            MatrixBoard tmpBoard = new MatrixBoard(this.state);

            // Creating all the possible move directions
            ArrayList<Position> directions = this.initializeFromDirections();
            // For each direction check if you can reach
            for (Position direction: directions) {
                // After 3 rotations everything comes back to the initial state
                int maxRotations = 3;
                for (int rotations = 0; rotations < maxRotations; rotations++ ) {
                    if (rotations > 0) {
                        // with each iteration rotate the pipe in the location of the direction
                        tmpBoard.getPipe(direction).rotate();
                    }
                    // Check if the move is valid, if so, no need to rotate anything, add this direction to the list.
                    if (tmpBoard.isValidMove(currentLocation, direction)) {
                        State<MatrixBoard> neighbor = new PipeGameState(this);
                        ((PipeGameState) neighbor).updateState(direction, rotations);
                        allNeighbors.add(neighbor);
                        break;
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(String.join(": ", "PipeGameState.getAllNeighbors(): Error details" , ex.getMessage()));
        }

        return allNeighbors;
    }

    @Override
    public boolean isGoal() {
        return this.state.getPipe(this.getCurrentPosition()).getPipeVal().equals('g');
    }

    public void updateState(Position to, int rotations) {
        if (this.state != null) {
            State<MatrixBoard> newCameFrom = new PipeGameState(this.state);
            this.state.getPipe(to).rotate(rotations);
            this.setCameFrom(newCameFrom);
            this.setCurrentPosition(to);
        }
    }

    @Override
    public double generateCost() {
        double cost = 0;
        try {
            Position endPosition = this.getState().findEndPosition();
            Position currentPosition = this.getCurrentPosition();
            // Calculate the absolute value of the way from current position to the goal
//            cost = Math.abs(currentPosition.getRow() - endPosition.getRow()) + Math.abs(currentPosition.getCol() - endPosition.getCol());
            cost = Math.abs(Point.distance(currentPosition.getRow(),currentPosition.getCol(),endPosition.getRow(),endPosition.getCol()));
        } catch (Exception ex) {
            System.out.println(String.join(": ", "PipeGameState.generateCost(): Error details" , ex.getMessage()));
        }
        return cost;
    }

    public Step getStepToState() {
        return stepToState;
    }

    public void setStepToState(Step step) {
        this.stepToState = step;
    }
}
