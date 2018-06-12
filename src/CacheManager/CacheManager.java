package CacheManager;

import java.io.IOException;

public interface CacheManager {


    /////////
    //Methods
    /////////

    public void save(String problem) throws IOException;
    public String load();


}
