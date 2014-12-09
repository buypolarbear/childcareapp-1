package com.finalproject.cs4962.childcare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        if(selectedView.contains("Events")) layoutID = R.layout.event_view;
        else if(selectedView.contains("Friends")) layoutID = R.layout.friends_layout;
        else if(selectedView.contains("Add Contact")) {

            layoutID = R.layout.add_contact_layout;
            View inflated = inflater.inflate(layoutID, container, false);

            activity.LoadAddContactsListeners(inflated);
            return inflated;
        }
        else if(selectedView.contains("Add Event")) layoutID = R.layout.add_event_view;
        else if(selectedView.contains("Profile")) {
            layoutID = R.layout.profile_view;
            View inflated = inflater.inflate(layoutID, container, false);
            activity.SetProfileView(inflated);
            return inflated;
        }

        return inflater.inflate(layoutID, container, false);
    }


}
