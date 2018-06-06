package data.Models;
import java.util.ArrayList;

 //TODO: what the fudge

public interface Searchable {

    public void initState();
    public void isGoal();
    public void getState();
    public ArrayList<Level> getAllStates(Level level);

}
