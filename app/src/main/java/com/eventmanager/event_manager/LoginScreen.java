package com.eventmanager.event_manager;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import static com.eventmanager.event_manager.R.id.userMail;
import static com.eventmanager.event_manager.R.id.userPassword;

public class LoginScreen extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    public static Bitmap user_picture;
    public static String user_first_name;
    public static String user_last_name;
    public static String user_email;
    public static String user_id;
    public static ProfilePictureView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "creation of LoginScreen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();

        try {
            if (!AccessToken.getCurrentAccessToken().getToken().isEmpty()) {
                LoginManager.getInstance().logOut();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        loginButton = (LoginButton)findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email","user_about_me");

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userid = loginResult.getAccessToken().getUserId();
                Log.d("onSuccess","Entered on Success");

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("onCompleted","Entered on Completed");
                        displayUserInfo(object);
                    }
                });
                Bundle parameters =new Bundle();
                parameters.putString("fields", "first_name,last_name,email,id,picture");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

            }

            @Override
            public void onCancel() {
                Log.d("onCancel", "User canceled Facebook login");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("onError", "Received Error Facebook login");
            }
        });
    }

    /**
     * Author : https://www.youtube.com/watch?v=MPSJ1Qo_ZxQ
     * Function Name : displayUserInfo(JSONObject object)
     * Purpose : Display logged in user information and moves to home screen.
     * Returns : -
     */
    public void displayUserInfo(JSONObject object){
        String first_name ="default", last_name="default", email="default", id="default";
        try {
            first_name = object.getString("first_name");
            last_name = object.getString("last_name");
            email =  object.getString("email");
            id =  object.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("first_name", first_name);
        Log.d("last_name", last_name);
        Log.d("email", email);
        Log.d("id", id);
        user_first_name = first_name;
        user_last_name = last_name;
        user_email = email;
        user_id = id;

        if(!first_name.equals("default") && !last_name.equals("default")
                && !id.equals("default") && !email.equals("default"))
        {
            Log.d("displayUserInfo", "move to home page using facebook");
            Intent intent = new Intent(LoginScreen.this, HomePageScreen.class);
            startActivity(intent);
        }
    }




    /**
     * Author : Shahar Azar
     * Function Name : login(View v)
     * Purpose : Checks credentials and moves to login screen.
     * Returns : Moves to user profile and logs in if user doesn't exist shows dialog regarding the problem.
     */
    public void login(View v){
        Log.d("login", "login Clicked");
        EditText _mail = (EditText) findViewById(userMail);
        EditText _password = (EditText) findViewById(userPassword);
        if(isValidEmail(_mail.getText()) && !isEditTextEmpty(_mail) && !isEditTextEmpty(_password))
        {
            Log.d("login", "validating email");
            //CHECK IN DATABASE HERE
            //MOVE TO LOGIN SCREEN
            //ELSE SHOW DIALOG NOT FOUND OR DOESN'T MATCH
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
     * Function Name : register(View v)
     * Purpose : Moves the user to the registration screen
     * Returns : -
     */
    public void register(View v){
        Log.d("register", "register Clicked");
        Intent intent = new Intent(LoginScreen.this, RegistrationScreen.class);
        startActivity(intent);
//        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vogella.com"));
//        startActivity(i);
    }

    /**
     * Author : Shahar Azar
     * Function Name : forgotPassword(View v)
     * Purpose : Opens a link for the user to reset their password
     * Returns : -
     */
    public void forgotPassword(View v){
        Log.d("forgotPassword", "forgotPassword Clicked");
        goToUrl("http://bit.ly/2yY8RYK");
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
        forceRTLIfSupported();
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
     * Author Name : https://stackoverflow.com/questions/6937782/android-how-to-put-a-dialog-box-after-pressing-a-back-button
     * Function Name : onKeyDown(int keyCode, KeyEvent event)
     * Purpose : Catch key event - DOWN
     * returns - True if succeeded, else false
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("onKeyDown", "Checking which key stroke");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Author Name : https://stackoverflow.com/questions/6937782/android-how-to-put-a-dialog-box-after-pressing-a-back-button
     * Function Name : exitByBackKey()
     * Purpose : Show dialog before exiting application and behave accordingly
     * returns - -
     */
    protected void exitByBackKey() {
    Log.d("exitByBackKey", "opening alert dialog for exiting application");
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("האם אתה בטוח שברצונך לצאת מהאפליקציה?")
                .setPositiveButton("כן", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        //close();
                    }
                })
                .setNegativeButton("לא", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

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
     * Author : Shahar Azar
     * Function Name : moveToHomePage(View V)
     * Purpose : Moves the user to the home page without entering credentials
     * Returns : -
     */
    public void moveToHomePage(View v){
        Log.d("moveToHomePage", "move to home page label Clicked");
        Intent intent = new Intent(LoginScreen.this, HomePageScreen.class);
        startActivity(intent);
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


    public static Bitmap getFacebookProfilePicture(String userID){
        Log.d("getFacebookProfilePictu", "started");
        URL imageURL = null;
        try {
            Log.d("getFacebookProfilePictu", "trying 1 ");
            imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
            Log.d("imageURL", imageURL.toString());
        } catch (MalformedURLException e) {
            Log.d("getFacebookProfilePictu", "catching 1 ");
            e.printStackTrace();
        }
        Bitmap bitmap = null;
        try {
            Log.d("getFacebookProfilePictu", "trying 2");
            bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
        } catch (IOException e) {
            Log.d("getFacebookProfilePictu", "catching 2");
            e.printStackTrace();
        }
        Log.d("Finished Getting pictur", bitmap.toString());
        return bitmap;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
