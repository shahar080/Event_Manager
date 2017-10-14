package com.eventmanager.event_manager;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    // TODO: Shahar 14/10/2017 dataBinding - move information to the xml
    // TODO: Shahar 14/10/2017 create map to choose a place then take the City it's in to show in the HomePageScreen for each list item
    // TODO: Shahar 14/10/2017 add participants option, find out how to take contacts from phone-book and invite


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
        this.eventOwnerImage = homePageScreen.getExtras().getString("event_ownerImage");
        setTitle("יצירת אירוע חדש"); //Change title

        forceRTLIfSupported(); //Right to left
        /**
         * Disable auto keyboard pop-up
         */
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);




        TextView event_name = (TextView) findViewById(R.id.eventName);
        TextView event_information = (TextView) findViewById(R.id.eventInformation);
        TextView event_code = (TextView) findViewById(R.id.eventCode);
        TextView event_date = (TextView) findViewById(R.id.eventDate);
        TextView event_location = (TextView) findViewById(R.id.eventLocation);
        TextView event_phoneNumber = (TextView) findViewById(R.id.ownerPhoneNumber);
        TextView event_participants = (TextView) findViewById(R.id.eventParticipants);
        ImageView event_ownerImage = (ImageView) findViewById(R.id.ownerImage);

        event_name.setText(this.eventName);
        event_information.setText(this.eventInformation);
        event_code.setText(String.valueOf(this.eventCode));

        event_location.setText(this.eventLocation);
        event_phoneNumber.setText(this.ownerPhoneNumber);
        event_participants.setText(this.eventParticipants);

        /**
         * Setting the calendar choosing date
         */
        final Calendar myCalendar = Calendar.getInstance();

        final EditText edittext= (EditText) findViewById(R.id.eventDateAndTime);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                edittext.setText(sdf.format(myCalendar.getTime()));

            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateEventScreen.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



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
