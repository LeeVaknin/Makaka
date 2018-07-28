package Models;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


public class Pipe {

    //TODO: add iterator

    // variables
    private Character pipeVal;
    private HashMap<Character, Character> rotationMapping;
    private Character left;
    private Character right;
    private Character up;
    private Character down;
    private Position position;


    // Setters and Getters
    public Character getPipeVal() {
        return pipeVal;
    }

    public void setPipeVal(Character value) {
        this.pipeVal = value;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = new Position(position);
    }

    // C-TOR

    public Pipe(Character pipe) {
        try {
        this.rotationMapping =  new HashMap<Character, Character>() {{
            put('F', '7');
            put('7', 'J');
            put('J', 'L');
            put('L', 'F');
            put('-', '|');
            put('|', '-');
        }};
        this.setPipeVal(pipe);
        } catch (Exception ex) {
           System.out.println("Pipe.Pipe(): Error details: " + ex.getMessage());
        }
    }

    public Pipe(@NotNull Pipe pipe) {
        this.setPipeVal(pipe.getPipeVal());
    }

    public Pipe(Position position) {
        this.setPosition(position);
    }

    public Pipe(Integer row, Integer col) {
        this.setPosition(new Position(row,col));
    }

    // Methods

    public Character rotate() {
        try {
            switch (pipeVal) {
                case 's':
                case 'g':
                case ' ':
                    break;
                default:
                    try {
                        setPipeVal(this.rotationMapping.get(pipeVal));
                    }
                    catch (NullPointerException exception) {
                        System.out.println( "Null pipe value, can't rotate.");
                    }
        }  } catch (Exception ex) {
            System.out.println("Pipe.rotate(): Error details: " + ex.getMessage());
        }
        return getPipeVal();
    }

    public boolean equals(Pipe pipe) {
        boolean isEqual = false;
        try {
            isEqual = this.getPipeVal().equals(pipe.getPipeVal());
        } catch (Exception ex) {
             System.out.println("Pipe.equals(): Error details: " + ex.getMessage());
        }
        return isEqual;
    }
}
