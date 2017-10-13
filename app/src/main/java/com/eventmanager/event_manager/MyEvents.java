package com.eventmanager.event_manager;

import android.icu.util.GregorianCalendar;
import android.location.LocationManager;

/**
 * Created by User on 12/10/2017.
 */

//Base class to hold information about the property
public class MyEvents {

    private String event_name;
    private String event_information;
    private String event_image;
    private int event_code;
    //private LocationManager event_location;
    //private GregorianCalendar event_time;

    //constructor
    public MyEvents(String event_name, String event_information, int event_code, String event_image
                   )
    {
        this.event_name = event_name;
        this.event_information = event_information;
        this.event_code = event_code;
        this.event_image = event_image;
        //this.event_location = event_location;
        //this.event_time = event_time;
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
/*
    public LocationManager getEvent_location() {
        return event_location;
    }

    public GregorianCalendar getEvent_time() {
        return event_time;
    }
*/
}
