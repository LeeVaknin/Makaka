package data.Models;
import java.util.ArrayList;


public interface Searchable {

    public void initState();
    public void isGoal();
    public void getState();
    public ArrayList<Level> getAllStates(Level level);

}
