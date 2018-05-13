package data.Models;

import java.time.Duration;

public class Score {

    private int numOfSteps;
    private Duration duration;

    /////////
    //Methods
    /////////

    public void setNumOfSteps(int numOfSteps) {
        this.numOfSteps = numOfSteps;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public int getNumOfSteps() {
        return numOfSteps;
    }

    public Duration getDuration() {
        return duration;
    }
}
