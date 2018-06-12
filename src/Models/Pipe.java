package Models;

// TODO: ask igor about the pipeVal - use char or pipeVal in the board structure?
public class Pipe {

    // variables
    private char pipeVal;

    // setters and getters

    public char getPipeVal() {
        return pipeVal;
    }

    public void setPipeVal(char value) {
        this.pipeVal = value;
    }

    // C-TOR

    public Pipe(char pipe) {
        this.setPipeVal(pipe);
    }

    public char rotate() {
        // TODO: Add switch case for the rotation cases
        return pipeVal;
    }
}
