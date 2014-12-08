package com.finalproject.cs4962.childcare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Braden on 12/7/2014.
 */
public class AvailabilityFragment extends Fragment {

    public MainActivity _activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.set_availability, container, false);
        _activity.LoadAvailabilityHandlers(v);
        return v;
    }
}
