package com.finalproject.cs4962.childcare;

import android.view.View;
import android.widget.CheckBox;

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
        view = (View) view.getParent();

        ConstructAvailability(view);

        if(_activity.InitialSetup) {
             //if its the initla setup, return back to the activity for amin.
            _activity.SaveInitialStartup();
            _activity.SetupMainView();
            _activity.InitialSetup = false;
            //show some alert, "Congrats! You're ready to start using ChildCare!"
        } else {
            _activity.AvailabilitySetForImportedContact(availability, _activity.contactFromPhone);
        }
    }

    private void ConstructAvailability(View view) {

        String s = "";
        s += isChecked((CheckBox)view.findViewById(R.id.mondayCheck)) + ":";
        s += isChecked((CheckBox)view.findViewById(R.id.tuesdayCheck))+ ":";
        s += isChecked((CheckBox)view.findViewById(R.id.wednesdayCheck))+ ":";
        s += isChecked((CheckBox)view.findViewById(R.id.thursdayCheck))+ ":";
        s += isChecked((CheckBox)view.findViewById(R.id.fridayCheck))+ ":";
        s += isChecked((CheckBox)view.findViewById(R.id.saturdayCheck))+ ":";
        s += isChecked((CheckBox)view.findViewById(R.id.sundayCheck));

        availability.startTimes = s;
        availability.endTimes = s;
    }

    private String isChecked(CheckBox box) {
        return box.isChecked() ? "1" : "0";
    }

}
