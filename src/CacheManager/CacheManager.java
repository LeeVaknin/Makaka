package CacheManager;

import Models.Board;

import java.io.IOException;

public interface CacheManager<T> {


    /////////
    //Methods
    /////////

    public Integer save(Board<T> board, FileType fileType) throws IOException;
    public String load(Integer id, FileType fileType) throws IOException;




}
