package test;

import Board.MatrixBoard;
import Board.Position;
import Board.Step;
import State.State;
import com.sun.org.apache.xpath.internal.operations.Equals;

import java.util.ArrayList;
import java.util.List;

public class MazeGameState extends State<Maze,Grid>{

    //CTOR
    public MazeGameState(Maze state)
    {
        this.setState(state);
        this.setCurrentPosition(null);
        this.setStep(null);
    }

    public MazeGameState(State<Maze, Grid> mazeGameState) {
        if (mazeGameState != null) {
            this.setState(mazeGameState.getState());
            this.setCameFrom(mazeGameState.getCameFrom());
            this.setCurrentPosition(mazeGameState.getCurrentPosition());
            this.setStep(mazeGameState.getStep());
        }
    }
    @Override
    public void setState(Maze state) {
        if (state != null) {
            this.state = state;
        }
    }
//TODO:go back to this one
    @Override
    public double generateCost() {
        return 0;
    }

    @Override
    public ArrayList<State<Maze, Grid>> getAllNeighbors() {
        ArrayList<State<Maze,Grid>> neighbors = new ArrayList<State<Maze, Grid>>();
        List<Grid> moves = this.state.getPossibleMoves(this.getCurrentPosition());
        for (Grid move : moves) {
            State<Maze,Grid> neighbor = new MazeGameState(this);
            ArrayList<String> dirs = new ArrayList<>();
            if (move.col > this.getCurrentPosition().col) {
                dirs.add("RIGHT");
            }
            if (move.col < this.getCurrentPosition().col) {
                dirs.add("LEFT");
            }
            if (move.row > this.getCurrentPosition().row) {
                dirs.add("DOWN");
            }
            if (move.row < this.getCurrentPosition().row) {
                dirs.add("UP");
            }

            Step<Grid> newStep = new MazeStep(move, dirs);
            neighbor.setStep(newStep);
            neighbor.setCurrentPosition(move);
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    @Override
    protected void updateState(Step<Grid> step) {
        this.setStep(step);
        this.setCurrentPosition(step.getPosition());
    }

    @Override
    public boolean isGoal() {
        return this.state.getExit().equals(this.getCurrentPosition());
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (state == null) { return false; }
        State<Maze, Grid> o = (State<Maze, Grid>)other;
        return this.getCurrentPosition().col == o.getCurrentPosition().col &&
                this.getCurrentPosition().row == o.getCurrentPosition().row;
    }
}
