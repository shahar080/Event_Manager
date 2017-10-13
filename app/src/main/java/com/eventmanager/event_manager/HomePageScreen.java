package com.eventmanager.event_manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.login.LoginManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 11/10/2017.
 */

public class HomePageScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ArrayList<MyEvents> myEventsProperties = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "creation of HomePageScreen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_screen);
        setTitle("האירועים שלי"); //Change title
        //forceRTLIfSupported(); //Right to left

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setNavigationViewListener();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        TextView name = header.findViewById(R.id.fullName);
        TextView email = header.findViewById(R.id.email);


        ImageView userPic = header.findViewById(R.id.userPicture);
        String path = "https://graph.facebook.com/" + LoginScreen.user_id + "/picture?type=large";
        //userPic.setImageBitmap();
        //ProfilePictureView profilePicture = (ProfilePictureView) findViewById(R.id.profilePicture);
        //profilePicture.setProfileId(LoginScreen.user_id);



        name.setText(LoginScreen.user_first_name + " " + LoginScreen.user_last_name);
        email.setText(LoginScreen.user_email);


        /**
         * Filling the myEvents arraylist
         */
        myEventsProperties.add(
                new MyEvents("אירוע 1", "מידע על אירוע 1", "property_image_1", 1122, "050-1111111","בית1", new Date())
        );
        myEventsProperties.add(
                new MyEvents("אירוע 2", "מידע על אירוע 2", "property_image_2", 1123, "050-2222222", "בית2", new Date())
        );
        myEventsProperties.add(
                new MyEvents("אירוע 3", "מידע על אירוע 3", "property_image_3", 1124, "050-3333333", "בית3", new Date())
        );
        myEventsProperties.add(
                new MyEvents("אירוע 4", "מידע על אירוע 4", "property_image_4", 1125, "050-4444444", "בית4", new Date())
        );
        myEventsProperties.add(
                new MyEvents("אירוע 5", "מידע על אירוע 5", "property_image_1", 1126, "050-5555555", "בית5", new Date())
        );
        myEventsProperties.add(
                new MyEvents("אירוע 6", "מידע על אירוע 6", "property_image_2", 1127, "050-6666666", "בית6", new Date())
        );
        myEventsProperties.add(
                new MyEvents("אירוע 7", "מידע על אירוע 7", "property_image_3", 1128, "050-7777777", "בית7", new Date())
        );
        myEventsProperties.add(
                new MyEvents("אירוע 8", "מידע על אירוע 8", "property_image_4", 1129, "050-8888888", "בית8", new Date())
        );

        ArrayAdapter<MyEvents> adapter = new myEventsArrayAdapter(this, 0, myEventsProperties);

        ListView listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);

        /**
         * Settings click listeners
         */
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyEvents property = myEventsProperties.get(position);

                Intent intent = new Intent(HomePageScreen.this, MyEvents.class);
//                intent.putExtra("streetNumber", property.getStreetNumber());
//                intent.putExtra("streetName", property.getStreetName());
//                intent.putExtra("suburb", property.getSuburb());
//                intent.putExtra("state", property.getState());
//                intent.putExtra("image", property.getImage());
//                intent.putExtra("bedrooms", property.getBedrooms());
//                intent.putExtra("bathrooms", property.getBathrooms());
//                intent.putExtra("carspots", property.getCarspots());
//                intent.putExtra("description", property.getDescription());

//                startActivity(intent);
            }
        };
//set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);


    }






    //custom ArrayAdapter
    class myEventsArrayAdapter extends ArrayAdapter<MyEvents> {

        private Context context;
        private List<MyEvents> myEventsProperties;

        //constructor, call on creation
        public myEventsArrayAdapter(Context context, int resource, ArrayList<MyEvents> objects) {
            super(context, resource, objects);

            this.context = context;
            this.myEventsProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            MyEvents myEvents = myEventsProperties.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.myevents_layout, null);

            TextView eventName = (TextView) view.findViewById(R.id.eventName);
            TextView eventCode = (TextView) view.findViewById(R.id.eventCode);
            TextView eventInformation = (TextView) view.findViewById(R.id.description);
            TextView eventDate = (TextView) view.findViewById(R.id.eventDate);
            TextView eventLocation = (TextView) view.findViewById(R.id.eventLocation);
            TextView ownerPhoneNumber = (TextView) view.findViewById(R.id.ownerPhoneNumber);
            ImageView ownerImage = (ImageView) view.findViewById(R.id.image);

            //set address and description
            String completeAddress = myEvents.getEvent_name();
            eventName.setText(completeAddress);

            //display trimmed excerpt for description
            int descriptionLength = myEvents.getEvent_information().length();
            if(descriptionLength >= 100){
                String descriptionTrim = myEvents.getEvent_information().substring(0, 100) + "...";
                eventInformation.setText(descriptionTrim);
            }else{
                eventInformation.setText(myEvents.getEvent_information());
            }

            //set price and rental attributes
            eventCode.setText("קוד האירוע: " + String.valueOf(myEvents.getEvent_code()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
            String formattedDate = dateFormat.format(myEvents.getEvent_date());
            eventDate.setText("תאריך: " + formattedDate);
            eventLocation.setText("מיקום: " + myEvents.getEvent_locationName());
            ownerPhoneNumber.setText("" + myEvents.getOwner_phoneNumber());

            //get the image associated with this property
            int imageID = context.getResources().getIdentifier(myEvents.getEvent_image(), "drawable", context.getPackageName());
            ownerImage.setImageResource(imageID);

            return view;
        }
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
                .setMessage("האם אתה בטוח שברצונך לחזור למסך הראשי?")
                .setPositiveButton("כן", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        LoginManager.getInstance().logOut();
                        Log.d("exitByBackKey","Logging out of facebook");
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
     * Author https://developer.android.com/training/implementing-navigation/nav-drawer.html
     * Function Name : onOptionsItemSelected(MenuItem item)
     * Purpose : Enable clicking on drawer button
     * Returns : true if mToggle is enabled, else false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    /**
     * Author : https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer
     * Function Name: onNavigationItemSelcted(MenuItem item)
     * Purpose : Handle clicks on navigation drawer items
     * Returns : true after closing navigation drawer
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {


            case R.id.nav_publicEvents://CASE nav_publicEvents
            {
                Log.d("onNavigationItemSelecte", "clicked on public events");
                Intent intent = new Intent(this, PublicEventsScreen.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_settings://CASE nav_settings
            {
                Log.d("onNavigationItemSelecte", "clicked on settings");
                Intent intent = new Intent(this, SettingsScreen.class);
                startActivity(intent);
                break;
            }


            case R.id.nav_contactUs://CASE nav_contactUs
            {
                Log.d("onNavigationItemSelecte", "clicked on contact us");
                goToUrl("http://bit.ly/2i0vlW6");
                break;
            }

            case R.id.nav_account://CASE nav_account
            {
                Log.d("onNavigationItemSelecte", "clicked on my account");
                Intent intent = new Intent(this, MyAccountScreen.class);
                startActivity(intent);
                break;
            }


            case R.id.nav_logout://CASE nav_logout
            {
                Log.d("onNavigationItemSelecte", "clicked on log out");
                exitByBackKey();
                break;
            }

        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
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
     * Author : https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer
     * Function Name : setNavigationViewListener
     * Purpose : Set listener to the navigation drawer
     * Returns : -
     */
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


}
