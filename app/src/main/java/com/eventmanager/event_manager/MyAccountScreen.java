package com.eventmanager.event_manager;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by User on 11/10/2017.
 */

public class MyAccountScreen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "creation of MyAccountScreen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount_screen);
        setTitle("המשתמש שלי"); //Change title
        forceRTLIfSupported();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
