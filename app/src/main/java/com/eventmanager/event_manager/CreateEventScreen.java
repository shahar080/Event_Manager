package com.eventmanager.event_manager;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 14/10/2017.
 */

public class CreateEventScreen extends AppCompatActivity {

    private String eventName;
    private String eventInformation;
    private String ownerPhoneNumber;
    private int eventCode;
    private String eventParticipants;
    private Date eventDate;
    private String eventLocation;
    private String eventOwnerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d("onCreate", "creation of CreateEventScreen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createevent_screen);

        /**
         * Getting information from HomePageScreen
         */
        Intent homePageScreen = getIntent();
        this.eventName = homePageScreen.getExtras().getString("event_name");
        this.eventInformation = homePageScreen.getExtras().getString("event_information");
        this.eventCode = homePageScreen.getExtras().getInt("event_code");
        this.eventDate = (Date) homePageScreen.getSerializableExtra("event_date");
        this.eventLocation = homePageScreen.getExtras().getString("event_location");
        this.ownerPhoneNumber = homePageScreen.getExtras().getString("event_ownerPhoneNumber");
        //this.eventOwnerImage = intent.getExtras().getString("event_ownerImage");
        setTitle("יצירת אירוע חדש"); //Change title

        forceRTLIfSupported(); //Right to left




        TextView event_name = (TextView) findViewById(R.id.eventName);
        TextView event_information = (TextView) findViewById(R.id.eventInformation);
        TextView event_code = (TextView) findViewById(R.id.eventCode);
        TextView event_date = (TextView) findViewById(R.id.eventDate);
        TextView event_location = (TextView) findViewById(R.id.eventLocation);
        TextView event_phoneNumber = (TextView) findViewById(R.id.ownerPhoneNumber);
        TextView event_participants = (TextView) findViewById(R.id.eventParticipants);

        event_name.setText(this.eventName);
        event_information.setText(this.eventInformation);
        event_code.setText(String.valueOf(this.eventCode));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        String formattedDate = dateFormat.format(this.eventDate);

//        event_date.setText(formattedDate);
        event_location.setText(this.eventLocation);
        event_phoneNumber.setText(this.ownerPhoneNumber);
        event_participants.setText(this.eventParticipants);





    }










    @Override
    protected void onNewIntent (Intent intent) {
        setIntent(intent);
    }


    /**
     * Author : https://stackoverflow.com/questions/17512504/change-action-bar-direction-to-right-to-left
     * Function Name : forceRTLIfSupported()
     * Purpose : Change the page's direction to support hebrew direction (RTL)
     * Returns : -
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}
