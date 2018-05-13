package data.Models;

import java.util.AbstractCollection;

public class User {

    private String userName;
    private AbstractCollection<Result> results;

    /////////
    //Methods
    /////////

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
