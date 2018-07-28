package State;
import Board.MatrixBoard;
import Models.Position;
import Models.Step;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class PipeGameState extends State<MatrixBoard, Step> {

    // C-TOR

    public PipeGameState(MatrixBoard state) {
        this.setState(state);
        this.setFrom(null);
    }

    public PipeGameState(State<MatrixBoard, Step> pipeGameState) {
        if (pipeGameState != null) {
            this.setState(pipeGameState.getState());
            this.setFrom(pipeGameState.getFrom());
            this.setTo(pipeGameState.getTo());
            this.setCameFrom(pipeGameState.getCameFrom());
            this.setCost(pipeGameState.getCost());
        }
    }

    // Methods
    // Added method which sets the default from to be the start of the board.
    @Override
    public void setFrom(Position from) {
        super.setFrom(from);
        if (this.getFrom() == null) {
            this.from = this.state.getStart();
        }
    }

    public void setState(MatrixBoard state) {
        if (state != null) {
            this.state = new MatrixBoard(state);
        }
    }

    public boolean equals(State<MatrixBoard, Step> state) {
        if (state == null) { return false; }
        return this.state.equals(state.state);
    }


//  Returns a backTrace of the states for the algorithms
    public ArrayList<State<MatrixBoard, Step>> backTrace() {
        ArrayList<State<MatrixBoard, Step>>returnBackTrace = new ArrayList<>();
        State<MatrixBoard, Step> tmp = this.getCameFrom();
        Character pipeVal = ' ';
        // TODO : Do we need the first protection at the while loop ?
        while (!pipeVal.equals('s') || tmp != null) {
            returnBackTrace.add(tmp);
            tmp = tmp.getCameFrom();
            pipeVal = tmp.getState().getPipe(tmp.getFrom());
        }
        Collections.reverse(returnBackTrace);
        return returnBackTrace;
    }

    public ArrayList<Step> getAllNeighbors() {
        ArrayList<Step> allNeighbors = null;
        try {
            allNeighbors = new ArrayList<>();
            // Check what are my options of moves up, down, left and right
            Position currentLocation = this.getFrom();
            // Creating all the possible move directions
            Position up = new Position(currentLocation.getPositionUp());
            Position down = new Position(currentLocation.getPositionDown());
            Position left = new Position(currentLocation.getPositionLeft());
            Position right = new Position(currentLocation.getPositionRight());



        } catch (Exception ex) {
            System.out.println(String.join(": ", "PipeGameState.getAllNeighbors(): Error details" , ex.getMessage()));
        }

        return null;
    }

    @Override
    public double generateCost() {
        double cost = 0;
        try {
            Position endPosition = ((MatrixBoard) this.getState()).findEndPosition();
            Position currentPosition = this.getFrom();
            // Calculate the absolute value of the way from current position to the goal
//            cost = Math.abs(currentPosition.getRow() - endPosition.getRow()) + Math.abs(currentPosition.getCol() - endPosition.getCol());
            cost = Math.abs(Point.distance(currentPosition.getRow(),currentPosition.getCol(),endPosition.getRow(),endPosition.getCol()));
        } catch (Exception ex) {
            System.out.println(String.join(": ", "PipeGameState.generateCost(): Error details" , ex.getMessage()));
        }
        return cost;
    }
}
