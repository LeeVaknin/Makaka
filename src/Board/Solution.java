package Board;
import State.State;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


// P is the position type of the step
public class Solution<P> implements Serializable {

    // Variables
    private ArrayList<Step<P>> steps;

    // C-TORS
    public Solution(State<?, P> goalState) {
        State<?, P> currentState = goalState;
        if (goalState != null) {
            while(goalState.getCameFrom() != null) {
                // add step
                this.addStep(currentState.getStep());
                currentState = currentState.getCameFrom();
            }
            Collections.reverse(this.steps);
        }
    }

    public Solution(Solution<P> solution) {
        this.setSteps(solution.getSteps());
    }

    public Solution() {
        super();
        this.setSteps(null);
    }

    // Methods

    public ArrayList<Step<P>> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step<P>> steps) {
        this.steps = new ArrayList<Step<P>>();
        if(steps != null) {
            this.steps.addAll(steps);
            return;
        }
    }

    public void addStep(Step<P> step) {
        if (step != null && this.steps != null) {
           this.steps.add(step);
        }
    }




}
