package com.finalproject.cs4962.childcare;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;

import model.Availability;
import model.Person;
import server.API;


public class MainActivity extends Activity implements ContactsFragment.OnContactsInteractionListener {
    ///////////////////////////////////////////////////////////////////////////
    API api = new API();
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    String[] mMenuItems = new String[] { "Events", "Friends", "Add Contact", "Add Event", "Profile" };
    String mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private AddContactButtonListener addContactListener;
    private AvailabilityButtonListener setAvailabilityListener;
    public boolean InitialSetup = true;
    File file;
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        file = new File(getFilesDir().getAbsolutePath() + "/childcarestartup.txt");
        //file.delete(); UNCOMMENT TO TEST STARTUP SCREEN

        SetupMainView();

        if(!file.exists()) {
            StartupView();
        } else {
            InitialSetup = false;
        }


    }
    ///////////////////////////////////////////////////////////////////////////
    private void StartupView() {
        setContentView(R.layout.initial_startup);
        ((Button)findViewById(R.id.beginStartup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                StartCustomContactFragment();
            }
        });
    }
    ///////////////////////////////////////////////////////////////////////////
    protected void SetupMainView() {
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mMenuItems));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.open, R.string.closed) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        /*ListView list = (ListView)findViewById(R.id.listView);
        ContactCardArrayAdapter dataAdapter = new ContactCardArrayAdapter(this, R.id.sitterName, GetScheduledEvents(), new ContactCardClicked(this), this);
        list.setAdapter(dataAdapter);*/

        addContactListener = new AddContactButtonListener(this);
        setAvailabilityListener = new AvailabilityButtonListener(this);
    }
    ///////////////////////////////////////////////////////////////////////////
    public void LoadDetailedEventView(View view) {
        //TODO;
        //setContentView(R.layout.detailed_event_view);
    }
    ///////////////////////////////////////////////////////////////////////////
    protected void LoadMainMenu() {

    }

    @Override
    public void onContactSelected(Uri contactUri) {

    }

    @Override
    public void onSelectionCleared() {

    }

    public void SetupManualContactView(View view) {

        final MainActivity _activity = this;
        ((Button)view.findViewById(R.id.addContactButton)).setOnClickListener(addContactListener);
    }


    ///////////////////////////////////////////////////////////////////////////

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString("Page", mMenuItems[position]);
        fragment.activity = this;
        fragment.setArguments(args, mMenuItems);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mMenuItems[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = (String)title;
        getActionBar().setTitle(mTitle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    public void LoadAddContactsListeners(View view) {
        final MainActivity _activity = this;
        // Gets the ListView from the View list of the parent activity
        // Sets the adapter for the ListView

        ((Button)view.findViewById(R.id.contactsButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                //findViewById(R.id.)
                //mContactsList = (ListView) view.findViewById(R.id.list);
                //mContactsList.setAdapter(mCursorAdapter);
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ContactsFragment fragment = new ContactsFragment();

                    _activity.getFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment).commit();
                }
                return true;
            }
        });

        ((Button)view.findViewById(R.id.customButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    StartCustomContactFragment();
                }
                return true;
            }
        });
    }
    public void LoadAvailabilityHandlers(View view) {
        ((Button)view.findViewById(R.id.setAvailabilityButton)).setOnClickListener(setAvailabilityListener);
    }
    protected void StartCustomContactFragment() {
        ContactManualFragment fragment = new ContactManualFragment();
        fragment._activity = this;
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    public void SaveInitialStartup() {
        Availability availability = setAvailabilityListener.availability;
        Person user = addContactListener.addedPerson;

        //save it to the file
        Save(user, availability);
    }

    private void Save(Person user, Availability availability) {
        if(file.exists()) return;
        try {
            file.createNewFile();

            FileWriter writer = new FileWriter(file);
            writer.write(new Gson().toJson(user));
            writer.write(new Gson().toJson(availability));
            writer.close();
        } catch(Exception e) { e.printStackTrace(); }

    }
}

