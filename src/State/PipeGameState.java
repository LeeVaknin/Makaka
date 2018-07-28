package State;
import Models.MatrixBoard;
import Models.Position;
import java.util.ArrayList;
import java.util.Collections;


public class PipeGameState extends State<MatrixBoard, PipeGameState> {

    private Position from;
    private Position to;

    public Position getFrom() {
        return from;
    }

    public void setFrom(Position from) {
        this.from = new Position(from);
    }

    public Position getTo() {
        return to;
    }

    public void setTo(Position to) {
        this.to = new Position(to);
    }

    // C-TOR

    public PipeGameState(MatrixBoard state) {
        this.state = state;
    }

    public PipeGameState(MatrixBoard state, double cost, PipeGameState cameFrom) {
        this.setState(state);
        this.setCost(cost);
        this.setCameFrom(cameFrom);
    }

    public PipeGameState(PipeGameState pipeGameState) {
        if (pipeGameState != null) {
            this.setState(pipeGameState.getState());
            this.setFrom(pipeGameState.getFrom());
            this.setTo(pipeGameState.getTo());
            this.setCameFrom(pipeGameState.getCameFrom());
            this.setCost(pipeGameState.getCost());
        }
    }

    // Methods
    public void setState(MatrixBoard state) {
        this.state = new MatrixBoard(state);
    }

    public boolean equals(PipeGameState state) {
         return this.state.equals(state.state);
    }

    /*
    Returns a backTrace of the states for the algorithms
     */
    public ArrayList<PipeGameState> backTrace() {
        ArrayList<PipeGameState> returnBackTrace = new ArrayList<>();
        PipeGameState temp = this.getCameFrom();
        while (temp != null) {
            returnBackTrace.add(temp);
            temp = temp.getCameFrom();
        }
        Collections.reverse(returnBackTrace);
        return returnBackTrace;
    }

    public ArrayList<PipeGameState> getAllNeighbors() {
        ArrayList<PipeGameState> allNeighbors = null;

        try {
            allNeighbors = new ArrayList<>();
            PipeGameState myState = this.getCameFrom();
        } catch (Exception ex) {
            System.out.println(String.join(": ", "PipeGameState.getAllNeighbors(): Error details" , ex.getMessage()));
        }

        return null;
    }
    @Override
    public int generateCost() {
        int cost = 0;
        try {
            Position endPosition = ((MatrixBoard) this.getState()).findEndPosition();
            Position currentPosition = this.getFrom();
            // Calculate the absolute value of the way from current position to the goal
            cost = Math.abs(currentPosition.getRow() - endPosition.getRow()) + Math.abs(endPosition.getCol() - endPosition.getCol());
        } catch (Exception ex) {
            System.out.println(String.join(": ", "PipeGameState.generateCost(): Error details" , ex.getMessage()));
        }
        return cost;
    }
}
