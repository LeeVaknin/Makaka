package Board;

// P - the position type of the step
public abstract class Step<P> {

    protected P position;

    public P getPosition() {
        return position;
    }

    public abstract void setPosition(P position);
}
