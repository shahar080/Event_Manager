package com.eventmanager.event_manager;

import android.location.LocationManager;

import java.util.Date;

/**
 * Created by User on 12/10/2017.
 */

//Base class to hold information about the property
public class MyEvents {

    private String event_name;
    private String event_information;
    private String event_image;
    private String owner_phoneNumber;
    private String event_locationName;
    private int event_code;
    //private LocationManager event_location;
    private Date event_date;

    //constructor
    public MyEvents(String event_name, String event_information, String event_image, int event_code,
                  String owner_phoneNumber, String event_locationName, Date event_date)
    {
        this.event_name = event_name;
        this.event_information = event_information;
        this.event_code = event_code;
        this.event_image = event_image;
        this.owner_phoneNumber = owner_phoneNumber;
        this.event_locationName = event_locationName;
        //this.event_location = event_location;
        this.event_date = event_date;
    }



    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_information() {
        return event_information;
    }

    public int getEvent_code() {
        return event_code;
    }

    public String getEvent_image() { return event_image; }

    public String getOwner_phoneNumber() { return owner_phoneNumber; }

    public String getEvent_locationName() { return event_locationName; }
/*
    public LocationManager getEvent_location() {
        return event_location;
    }
*/
    public Date getEvent_date() {
        return event_date;
    }

}
