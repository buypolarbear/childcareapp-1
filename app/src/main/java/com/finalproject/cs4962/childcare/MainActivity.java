package com.finalproject.cs4962.childcare;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.Address;
import model.Availability;
import model.Person;
import server.API;



public class MainActivity extends Activity {

    ///////////////////////////////////////////////////////////////////////////
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    String[] mMenuItems = new String[] { "Events", "Friends", "Add Contact", "Add Event", "Profile" };
    String mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private AddContactButtonListener addContactListener;
    private AvailabilityButtonListener setAvailabilityListener;
    public boolean InitialSetup = true;
    File file, file1, file2;

    public static final String CONTACT_PREF = "MyContactFile";


    public static final ArrayList<ContactRowData> Contact_Data = new ArrayList<ContactRowData>();


    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        SetupMainView();


        file = new File(getFilesDir().getAbsolutePath() + "/user.txt");
        file1 = new File(getFilesDir().getAbsolutePath() + "/availability.txt");
        //file2 = new File(getFilesDir().getAbsolutePath() + "/friends_contacts.txt");
        //file.delete(); //UNCOMMENT TO TEST STARTUP SCREEN

        SetupMainView();

        if(!file.exists()) {
            StartupView();
        } else {
            InitialSetup = false;

            PageFragment fragment = new PageFragment();
            Bundle args = new Bundle();
            args.putString("Page", "Events");
            fragment.activity = this;
            fragment.setArguments(args, mMenuItems);

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//            setContentView(R.layout.event_view);
//            LayoutInflater inflater = this.getLayoutInflater();
//            //inflating the row layout we defined earlier.
//            inflater.inflate(R.layout.event_view, act);
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.ev, fragment).commit();
//            View inflated = inflater.inflate(R.layout.profile_view, container, false);
        }
    }
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences settings = getSharedPreferences(CONTACT_PREF, 0);

        Set<String> set = settings.getStringSet("Contact_Data_Set",new HashSet<String>());

        Contact_Data.clear();

        for( Iterator<String> i = set.iterator(); i.hasNext();)
        {
            try {
                Contact_Data.add(new Gson().fromJson(i.next(), ContactRowData.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveContactsList();

        return;
    }

    @Override
    protected void onStop() {
        super.onStop();
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

        //first call the API so it can load
        API.Get(); //we don't care about the response

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
    public void SetupManualContactView(View view) {
        ((Button)view.findViewById(R.id.addContactButton)).setOnClickListener(addContactListener);
    }



    //Todo: populate events
    /**
     *
     */
    public void setEventsRowData() {



    }



    //todo: populate spinner
    /**
     *
     * @param inflated
     */
    public void LoadAddEventsListeners(View inflated) {

//        Contact_Data.

//        Spinner spinner = (Spinner) findViewById(R.id.select_contacts_spinner);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                Contact_Data.toArray(), android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
    }

    ///////////////////////////////////////////////////////////////////////////
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
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

    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void setTitle(CharSequence title) {
        mTitle = (String)title;
        getActionBar().setTitle(mTitle);
    }
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    ///////////////////////////////////////////////////////////////////////////
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
    ///////////////////////////////////////////////////////////////////////////
    private final int ADD_CONTACT = 1;
  //  private final int CREATE_CONTACT = 2;
    ///////////////////////////////////////////////////////////////////////////
    public void LoadAddContactsListeners(View view) {
        final MainActivity _activity = this;
        // Gets the ListView from the View list of the parent activity
        // Sets the adapter for the ListView

        ((Button)view.findViewById(R.id.contactsButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, ADD_CONTACT);
            }
        });

//        ((Button)view.findViewById(R.id.customButton)).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    StartCustomContactFragment();
//                }
//                return true;
//            }
//        });

        ((Button)view.findViewById(R.id.customButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                AlertDialog.Builder builder = new AlertDialog.Builder(_activity);
//
//                builder.setTitle("Pick one from Contacts or create a new one?");
//                builder.create();
                Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                intent.putExtra(ContactsContract.Intents.EXTRA_FORCE_CREATE, true);
                intent.putExtra("finishActivityOnSaveCompleted", true);
                startActivityForResult(intent, ADD_CONTACT);

            }
        });

        ((Button)view.findViewById(R.id.fromServer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetContactRowData(); //this will push the fragment when it's done
            }
        });
    }
    ///////////////////////////////////////////////////////////////////////////
    Person contactFromPhone;
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);

        switch(reqCode)
        {
            case (ADD_CONTACT):
                if (resultCode == Activity.RESULT_OK)
                {
                    Uri contactData = data.getData();
                    //Toast.makeText(getApplicationContext(),"Hello: "+data.getData().toString(),Toast.LENGTH_LONG).show();
                    ContactHelper helper = new ContactHelper(getContentResolver(), contactData);
                    String name = helper.retrieveContactName();
                    String number = helper.retrieveContactNumber();

                    contactFromPhone = new Person();
                    if(name != null) {
                        contactFromPhone.firstname = (name.split(" ")[0] == null) ? "" : name.split(" ")[0];
                        if(name.split(" ").length >1)
                            contactFromPhone.lastname = (name.split(" ")[1] == null) ? "" : name.split(" ")[1];
                    }
                    contactFromPhone.phonenumber = number;
                    if(helper.retrieveContactAddress() != null)
                    {
                        Address addr = helper.retrieveContactAddress();
                        contactFromPhone.address = addr;
                    }


                    ContactRowData row = new ContactRowData();

                    row.firstName = contactFromPhone.firstname;
                    row.lastName = contactFromPhone.lastname;
                    row.address = contactFromPhone.address.addressStr;
                    row.city = contactFromPhone.address.city;
                    row.state = contactFromPhone.address.state;
                    row.zip = contactFromPhone.address.zip;
                    row.phoneNumber = contactFromPhone.phonenumber;

                    Contact_Data.add(row);

                    saveContactsList();

                    StartAPIContactsLoad(Contact_Data, new ContactRowDataClicked(this));

                    //switch to the availability
                    AvailabilityFragment fragment = new AvailabilityFragment();
                    fragment._activity = this;
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

                    return;
                }

        }
    }
    //////////////////////////////////////////////////////////////////////////
    public void AvailabilitySetForImportedContact(final Availability availability, final Person person) {
        final MainActivity _activity = this;
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait while we add your new friend!");
        progress.show();

        progress.dismiss();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //grab the availability and the contactAddedPerson and save them to the api and the friends
//
//                API api = API.Get();
//                api.AddPerson(person, person.address, null, availability);
//                api.AddFriend(GetUser(), person);
//                // To dismiss the dialog
//                progress.dismiss();
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        selectItem(0);
//                        //Toast.makeText(_activity, "Finished!", Toast.LENGTH_LONG);
//                    }
//                });
//            }
//        }).start();

                    //switch to Friends view

        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString("Page", "Friends");
        fragment.activity = this;
        fragment.setArguments(args, mMenuItems);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();



    }
    //////////////////////////////////////////////////////////////////////////
    public void LoadAvailabilityHandlers(View view) {
        ((Button)view.findViewById(R.id.setAvailabilityButton)).setOnClickListener(setAvailabilityListener);
    }
    //////////////////////////////////////////////////////////////////////////
    protected void StartCustomContactFragment() {
        ContactManualFragment fragment = new ContactManualFragment();
        fragment._activity = this;
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
    //////////////////////////////////////////////////////////////////////////
    public void SaveInitialStartup() {
        Availability availability = setAvailabilityListener.availability;
        Person user = addContactListener.addedPerson;

        //save it to the file
        Save(user, availability);

        API api = API.Get();
        api.AddPerson(user, user.address, null, availability);
    }
    //////////////////////////////////////////////////////////////////////////
    private void Save(Person user, Availability availability) {
        if(file.exists()) file.delete();
        if(file1.exists()) file1.delete();
        try {
            file.createNewFile();
            file1.createNewFile();

            FileWriter writer = new FileWriter(file);
            FileWriter writer1 = new FileWriter(file1);
            writer.write(new Gson().toJson(user));
            writer1.write(new Gson().toJson(availability));
            writer.close();
            writer1.close();

            System.err.println("Success while saving user & availability files!");

        } catch(Exception e) {
            System.err.println("Error while saving user & availability files!");
            e.printStackTrace();
        }

    }
    //////////////////////////////////////////////////////////////////////////
    private String ReadUserFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String val = "";
            while (reader.ready()) val += reader.readLine();

            return val;
        } catch(Exception e) {e.printStackTrace();}

        return null;
    }
    //////////////////////////////////////////////////////////////////////////
    private Person GetUser() {

        try {
            return new Gson().fromJson(ReadUserFile(file), Person.class);
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    //////////////////////////////////////////////////////////////////////////
    private Availability GetUserAvailability() {

        try {
            return new Gson().fromJson(ReadUserFile(file1), Availability.class);
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    //////////////////////////////////////////////////////////////////////////
    public void SetProfileView(View view) {
        final MainActivity activity = this;
        Person user = GetUser();
        ((EditText)view.findViewById(R.id.profile_firstname)).setText(user.firstname);
        ((EditText)view.findViewById(R.id.profile_lastname)).setText(user.lastname);
        ((EditText)view.findViewById(R.id.profile_phonenumber)).setText(user.phonenumber);
        ((EditText)view.findViewById(R.id.profile_address)).setText(user.address.addressStr);
        ((EditText)view.findViewById(R.id.profile_city)).setText(user.address.city);
        ((EditText)view.findViewById(R.id.profile_state)).setText(user.address.state);
        ((EditText)view.findViewById(R.id.profile_zip)).setText(user.address.zip);
        ((Button)view.findViewById(R.id.profile_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = (View)view.getParent().getParent();
                String firstname = ((EditText) view.findViewById(R.id.profile_firstname)).getText().toString();
                String lastname = ((EditText) view.findViewById(R.id.profile_lastname)).getText().toString();
                String phonenumber = ((EditText) view.findViewById(R.id.profile_phonenumber)).getText().toString();
                String address = ((EditText) view.findViewById(R.id.profile_address)).getText().toString();
                String city = ((EditText) view.findViewById(R.id.profile_city)).getText().toString();
                String state = ((EditText) view.findViewById(R.id.profile_state)).getText().toString();
                String zipcode = ((EditText) view.findViewById(R.id.profile_zip)).getText().toString();

                Person addedPerson = new Person();
                addedPerson.firstname = firstname;
                addedPerson.lastname = lastname;
                addedPerson.phonenumber = phonenumber;
                Address addr = new Address();
                addr.addressStr = address;
                addr.city = city;
                addr.state = state;
                addr.zip = zipcode;
                addedPerson.address = addr;

                activity.Save(addedPerson, activity.GetUserAvailability());

                Toast.makeText(activity, "User information saved!", Toast.LENGTH_LONG).show();
            }
        });
        ((Button)view.findViewById(R.id.profile_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.selectItem(0); //return to home
            }
        });
    }
    //////////////////////////////////////////////////////////////////////////
    public void StartAPIContactsLoad(ArrayList<ContactRowData> data, View.OnTouchListener listener) {

        ContactFragment fragment = new ContactFragment();
        fragment.activity = this;
        fragment.data = data;
        fragment.handler = listener;
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }
    ////////////////////////////////////////////////////////////////////////////
    public void StartAvailabilityFragment() {
        final MainActivity activity = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AvailabilityFragment fragment = new AvailabilityFragment();
                fragment._activity = activity;
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
        });

    }
    //////////////////////////////////////////////////////////////////////////
    private void SetContactRowData() {
        final ArrayList<ContactRowData> data = new ArrayList<ContactRowData>();
        final MainActivity activity = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ProgressDialog progress = new ProgressDialog(activity);
                progress.setTitle("Loading");
                progress.setMessage("Please wait while we grab potential new friends!");
                progress.show();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //grab the availability and the contactAddedPerson and save them to the api and the friends

//                        API api = API.Get();
//                        Person[] people = api.GetPeopleCached();
//
//                        for(Person p : people) {
//                            ContactRowData row = new ContactRowData();
//                            row.firstName = p.firstname;
//                            row.lastName = p.lastname;
//                            row.address = p.address.addressStr;
//                            row.city = p.address.city;
//                            row.state = p.address.state;
//                            row.zip = p.address.zip;
//                            row.phoneNumber = p.phonenumber;
//
//                            data.add(row);
//                        }
//                        // To dismiss the dialog
                        progress.dismiss();

                        activity.StartAPIContactsLoad(Contact_Data, new ContactRowDataClicked(activity));
                    }
                });

                thread.start();
            }
        });
    }
    //////////////////////////////////////////////////////////////////////////
    public void SetFriendsRowData() {
        final ArrayList<ContactRowData> data = new ArrayList<ContactRowData>();
        final MainActivity activity = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ProgressDialog progress = new ProgressDialog(activity);
                progress.setTitle("Loading");
                progress.setMessage("Please wait while we grab your friends!");
                progress.show();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //grab the availability and the contactAddedPerson and save them to the api and the friends

//                        API api = API.Get();
//                        Person person = GetUser();
//                        if(person.idperson == -1) {
//                            person.idperson = api.GetPersonID(person);
//                            Save(person, GetUserAvailability()); //prevent future loading
//                        }
//
//                        Person[] people = api.GetFriends(person);
//
//                        for(Person p : people) {
//                            ContactRowData row = new ContactRowData();
//                            row.firstName = p.firstname;
//                            row.lastName = p.lastname;
//                            row.address = p.address.addressStr;
//                            row.city = p.address.city;
//                            row.state = p.address.state;
//                            row.zip = p.address.zip;
//                            row.phoneNumber = p.phonenumber;
//
//                            data.add(row);
//                        }
                        // To dismiss the dialog
                        progress.dismiss();

                        activity.StartAPIContactsLoad(Contact_Data, new FriendOnTouchHandler());
                    }
                });

                thread.start();
            }
        });
    }


    public void saveContactsList()
    {
        SharedPreferences settings = getSharedPreferences(CONTACT_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        Set<String> set = new HashSet<String>();

        for( Iterator<ContactRowData> i = Contact_Data.iterator(); i.hasNext();)
        {
            set.add(new Gson().toJson(i.next()));
        }
        editor.putStringSet("Contact_Data_Set", set);
        editor.commit();
    }
}

