package com.finalproject.cs4962.childcare;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Braden on 12/6/2014.
 */
public class PageFragment extends Fragment {

    String[] menuItems;
    String selectedView;
    MainActivity activity;

    public void setArguments(Bundle args, String[] menuItems) {
        this.menuItems = menuItems;
        this.selectedView = args.getString("Page");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //{ "Events", "Friends", "Add Contact", "Add Event" };
        int layoutID = -1;
        View inflated;


        if(selectedView.contains("Events"))
        {
            activity.setEventsRowData();
            layoutID = R.layout.event_view;
            inflated = inflater.inflate(layoutID, container, false);
        }
        else if(selectedView.contains("Friends")) {
            activity.SetFriendsRowData();
            layoutID = R.layout.friends_layout;
            inflated = inflater.inflate(layoutID, container, false);
        }
        else if(selectedView.contains("ContactDetails")) {
            //activity.SetFriendsRowData();
            layoutID = R.layout.contact_view;
            inflated = inflater.inflate(layoutID, container, false);
        }
        else if(selectedView.contains("Add Contact"))
        {
//            final Context context = container.getContext();
//            final int ADD_CONTACT = 1;

//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setTitle("Title");
//            builder.setItems(new CharSequence[]
//                            {"Import Existing Contact", "Create and Import a New Contact", "Cancel"},
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//
//                           Intent importContact_intent;
//                            // The 'which' argument contains the index position
//                            // of the selected item
//                            switch (which) {
//                                case 0:
//                                    importContact_intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//                                    startActivityForResult(importContact_intent, ADD_CONTACT);
//                                    //Toast.makeText(context, "clicked 1", Toast.LENGTH_SHORT).show();
//                                    break;
//                                case 1:
//                                    importContact_intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
//                                    importContact_intent.putExtra(ContactsContract.Intents.EXTRA_FORCE_CREATE, true);
//                                    importContact_intent.putExtra("finishActivityOnSaveCompleted", true);
//                                    startActivityForResult(importContact_intent, ADD_CONTACT);
//                                    //Toast.makeText(context, "clicked 2", Toast.LENGTH_SHORT).show();
//                                    break;
//                                case 2:
//
//                                    //Toast.makeText(context, "clicked 3", Toast.LENGTH_SHORT).show();
//                                    break;
//                            }
//                        }
//                    });
//            builder.create().show();


//            layoutID = R.layout.add_contact_layout;
//            inflated = inflater.inflate(layoutID, container, false);

            activity.LoadAddContactsDialog();
            activity.SetFriendsRowData();
            layoutID = R.layout.friends_layout;
            inflated = inflater.inflate(layoutID, container, false);

//            activity.LoadAddContactsListeners(inflated);
        }
        else if(selectedView.contains("Add Event"))
        {
            layoutID = R.layout.add_event_view;
            inflated = inflater.inflate(layoutID, container, false);

            activity.LoadAddEventsListeners(inflated);
        }
        else if(selectedView.contains("Profile"))
        {
            layoutID = R.layout.profile_view;
            inflated = inflater.inflate(layoutID, container, false);
            activity.SetProfileView(inflated);
        }
        else
        {
            layoutID = R.layout.event_view;;
            inflated = inflater.inflate(layoutID, container, false);
            activity.setEventsRowData();
        }
        return inflated;
    }

}