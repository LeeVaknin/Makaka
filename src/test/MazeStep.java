package test;

import Board.Step;

import java.util.ArrayList;

public class MazeStep extends Step<Grid> {

    public MazeStep(Grid pos, ArrayList<String> dirs) {
        this.directions = dirs;
        this.position = pos;
    }
    public ArrayList<String> directions;

    @Override
    public void setPosition(Grid position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.join(",", this.directions);
    }
}
