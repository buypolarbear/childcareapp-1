package com.finalproject.cs4962.childcare;

import android.view.View;

import model.Availability;

/**
 * Created by Braden on 12/7/2014.
 */
public class AvailabilityButtonListener implements View.OnClickListener {
    private MainActivity _activity;
    public Availability availability = new Availability();
    public AvailabilityButtonListener(MainActivity activity) {
        this._activity = activity;
    }
    @Override
    public void onClick(View view) {
        //save the availabilities

        if(_activity.InitialSetup) {
             //if its the initla setup, return back to the activity for amin.
            _activity.SaveInitialStartup();
            _activity.SetupMainView();

            //show some alert, "Congrats! You're ready to start using ChildCare!"
        }
    }
}
