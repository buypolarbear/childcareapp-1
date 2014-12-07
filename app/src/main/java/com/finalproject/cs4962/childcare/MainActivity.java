package com.finalproject.cs4962.childcare;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.API;


public class MainActivity extends Activity {
    ///////////////////////////////////////////////////////////////////////////
    API api = new API();
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    String[] mMenuItems = new String[] { "Events", "Friends", "Add Contact", "Add Event" };
    String mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
    ///////////////////////////////////////////////////////////////////////////
    public ArrayList<ContactCardRowData> GetScheduledEvents() {

        ArrayList<ContactCardRowData> data = new ArrayList<ContactCardRowData>();
        ContactCardRowData item = new ContactCardRowData();
        item.date = DateFormat.getDateInstance().format(new Date());
        item.startTime = "Now";
        item.endTime = "Never";
        item.parentname = "KARISSA!";
        item.sittername = "BRADEN!";
        item.numKids = "1";

        data.add(item);

        item = new ContactCardRowData();
        item.date = DateFormat.getDateInstance().format(new Date());
        item.startTime = "Now";
        item.endTime = "Never";
        item.parentname = "KARISSA!";
        item.sittername = "BRADEN!";
        item.numKids = "1";

        data.add(item);
        return data;
    }
    ///////////////////////////////////////////////////////////////////////////
    protected ListView addItemsToListView(ListView listview, List<ViewGroup> views) {

        final ArrayAdapter<ViewGroup> adapter = new ArrayAdapter<ViewGroup>(this, 0);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                if (item.contains("Main Menu")) {
                    LoadMainMenu();
                    return;
                }
            }
        });

        return listview;
    }
    ///////////////////////////////////////////////////////////////////////////
    public void LoadDetailedEventView(View view) {
        //TODO;
        setContentView(R.layout.detailed_event_view);
    }
    ///////////////////////////////////////////////////////////////////////////
    protected void LoadMainMenu() {

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

    public void LoadAddContactsListeners() {
        ((Button)findViewById(R.id.contactsButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        ((Button)findViewById(R.id.customButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
    }
}

