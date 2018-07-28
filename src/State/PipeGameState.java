package State;
import Board.MatrixBoard;
import Models.Position;
import java.util.ArrayList;
import java.util.Collections;


public class PipeGameState extends State<MatrixBoard> {





    // C-TOR

    public PipeGameState(MatrixBoard state) {
        this.setState(state);
    }

    public PipeGameState(State<MatrixBoard> pipeGameState) {
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

    public boolean equals(State<MatrixBoard> state) {
         return this.state.equals(state.state);
    }

    /*
    Returns a backTrace of the states for the algorithms
     */
    public ArrayList<State<MatrixBoard>> backTrace() {
        ArrayList<State<MatrixBoard>> returnBackTrace = new ArrayList<>();
        State<MatrixBoard> temp = this.getCameFrom();
        while (temp != null) {
            returnBackTrace.add(temp);
            temp = temp.getCameFrom();
        }
        Collections.reverse(returnBackTrace);
        return returnBackTrace;
    }

    public ArrayList<State<MatrixBoard>> getAllNeighbors() {
        ArrayList<State<MatrixBoard>> allNeighbors = null;
        try {
            allNeighbors = new ArrayList<>();

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
            cost = Math.abs(currentPosition.getRow() - endPosition.getRow()) + Math.abs(currentPosition.getCol() - endPosition.getCol());
        } catch (Exception ex) {
            System.out.println(String.join(": ", "PipeGameState.generateCost(): Error details" , ex.getMessage()));
        }
        return cost;
    }
}
