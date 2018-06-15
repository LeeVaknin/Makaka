package Models;
import java.util.HashMap;


public class Pipe {

    // variables
    private char pipeVal;
    private HashMap<String, String> mapping;
    // setters and getters

    public char getPipeVal() {
        return pipeVal;
    }

    public void setPipeVal(char value) {
        this.pipeVal = value;
    }

    // C-TOR

    public Pipe(char pipe) {
        this.mapping =  new HashMap<String, String>() {{
            put("F", "7");
            put("7", "J");
            put("J", "L");
            put("L", "F");
            put("-", "|");
            put("|", "-");
        }};

        this.setPipeVal(pipe);
    }

    public char rotate() {
        switch (pipeVal) {
            case 's':
            case 'g':
            case ' ':
                break;
            default:
                final String strRotate = mapping.get(Character.toString(pipeVal));
                setPipeVal(strRotate.charAt(0));

        }
        return pipeVal;
    }
}
