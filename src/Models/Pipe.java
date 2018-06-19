package Models;
import java.util.HashMap;


public class Pipe {

    //TODO: add iterator

    // variables
    private Character pipeVal;
    private HashMap<Character, Character> rotationMapping;

    // Setters and Getters
    public Character getPipeVal() {
        return pipeVal;
    }

    public void setPipeVal(Character value) {
        this.pipeVal = value;
    }

    // C-TOR

    public Pipe(Character pipe) {
        this.rotationMapping =  new HashMap<Character, Character>() {{
            put('F', '7');
            put('7', 'J');
            put('J', 'L');
            put('L', 'F');
            put('-', '|');
            put('|', '-');
        }};

        this.setPipeVal(pipe);
    }

    // Methods

    public Character rotate() {
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
        }
        return getPipeVal();
    }
}
