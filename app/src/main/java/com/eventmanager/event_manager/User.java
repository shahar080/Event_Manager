package com.eventmanager.event_manager;

/**
 * Created by User on 11/10/2017.
 */

public class User {
    public final String fullName;
    public final String Email;
    public final String userID;
    public User(String fullName, String Email, String userID) {
        this.fullName = fullName;
        this.Email = Email;
        this.userID = userID;
    }

    public String getFullName(){
        return this.fullName;
    }

    public String getEmail(){
        return this.Email;
    }

    public String getUserID(){
        return this.userID;
    }
}
