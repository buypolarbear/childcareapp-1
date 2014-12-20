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
        else if(selectedView.contains("Contact Details")) {
            //activity.SetFriendsRowData();
            layoutID = R.layout.contact_view;
            inflated = inflater.inflate(layoutID, container, false);
        }
        else if(selectedView.contains("Add Contact"))
        {
            activity.LoadAddContactsDialog();
            activity.SetFriendsRowData();
            layoutID = R.layout.friends_layout;
            inflated = inflater.inflate(layoutID, container, false);
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

            activity.setTitle("Events");
            layoutID = R.layout.event_view;;
            inflated = inflater.inflate(layoutID, container, false);
            activity.setEventsRowData();
        }
        return inflated;
    }

}