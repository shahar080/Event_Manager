package com.eventmanager.event_manager;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static com.eventmanager.event_manager.R.id.userMail;
import static com.eventmanager.event_manager.R.id.userPassword;

/**
 * Created by User on 11/10/2017.
 */

public class RegistrationScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "creation of RegistrationScreen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        getSupportActionBar().hide();
        forceRTLIfSupported();
    }

    /**
     * Author : Shahar Azar
     * Function Name : register(View v)
     * Purpose : Moves the user to the registration screen
     * Returns : -
     */
    public void register(View v){
        Log.d("register", "register Clicked");
        EditText _mail = (EditText) findViewById(userMail);
        EditText _password = (EditText) findViewById(userPassword);
        if(isValidEmail(_mail.getText()) && !isEditTextEmpty(_mail) && !isEditTextEmpty(_password))
        {
            Log.d("register", "registering to DB");
            // TODO: Omer 14/10/2017 Send data to DB here
        }
        else
        {

            if(isEditTextEmpty(_mail)){
                Log.d("login", "email empty");
                AlertDialog alertbox = new AlertDialog.Builder(this)
                        .setMessage("שדה המייל אינו יכול להיות ריק")
                        .setPositiveButton("המשך", new DialogInterface.OnClickListener() {
                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        }).show();
            }
            else if(isEditTextEmpty(_password)){
                Log.d("login", "password empty");
                AlertDialog alertbox = new AlertDialog.Builder(this)
                        .setMessage("שדה הסיסמה אינו יכול להיות ריק")
                        .setPositiveButton("המשך", new DialogInterface.OnClickListener() {
                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        }).show();
            }
            else {
                Log.d("login", "email not in the right format");
                AlertDialog alertbox = new AlertDialog.Builder(this)
                        .setMessage("המייל שהכנסת אינו בפורמט הנכון")
                        .setPositiveButton("המשך", new DialogInterface.OnClickListener() {
                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        }).show();
            }
        }
    }

    /**
     * Author : Shahar Azar
     * Function Name : returnToLogin(View v)
     * Purpose : Return to login screen
     * Returns : -
     */
    public void returnToLogin(View v){
        Log.d("returnToLogin", "returning to the login screen");
        finish();
    }

    /**
     * Author : Shahar Azar
     * Function Name : contactUs(View v)
     * Purpose : Opens a link for the user to contact us
     * Returns : -
     */
    public void contactUs(View v){
        Log.d("contactUs", "contactUs Clicked");
        goToUrl("http://bit.ly/2i0vlW6");
    }

    /**
     * Author : https://stackoverflow.com/questions/5026349/how-to-open-a-website-when-a-button-is-clicked-in-android-application
     * Function Name : goToUrl(String url)
     * Purpose : Direct the user to a website using the link
     * Returns : -
     */
    private void goToUrl (String url) {
        Log.d("goToUrl", "Move to url : " + url);
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    /**
     * Author : https://stackoverflow.com/questions/24969894/android-email-validation-on-edittext
     * Function Name : isValidEmail(CharSequence email)
     * Purpose : Checks if the email is valid
     * Returns : true if email is valid, else false
     */
    public final static boolean isValidEmail(CharSequence target) {
        Log.d("isValidEmail", "checking if email is valid");
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Author : https://stackoverflow.com/questions/6290531/check-if-edittext-is-empty
     * Function Name : isEditTextEmpty(EditText text)
     * Purpose : Checks if the EditText we are getting is empty or not
     * Returns : True if empty, else false
     */
    private boolean isEditTextEmpty(EditText etText) {
        Log.d("isEditTextEmpty", "checking if the string we got is empty");
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
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
