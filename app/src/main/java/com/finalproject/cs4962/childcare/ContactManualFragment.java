package com.finalproject.cs4962.childcare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Braden on 12/7/2014.
 */
public class ContactManualFragment extends Fragment {

    public MainActivity _activity;

    public ContactManualFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_contact_manually, container, false);
        _activity.SetupManualContactView(view);
        return view;
    }
}
