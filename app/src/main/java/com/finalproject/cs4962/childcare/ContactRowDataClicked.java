package com.finalproject.cs4962.childcare;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import model.Address;
import model.Person;

/**
 * Created by Braden on 12/9/2014.
 */
public class ContactRowDataClicked implements View.OnTouchListener {
    MainActivity activity;
    public ContactRowDataClicked(MainActivity activity) {
        this.activity = activity;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Person p = new Person();
            p.address = new Address();
            p.firstname = ((TextView)view.findViewById(R.id.contact_view_firstname)).getText().toString();
            p.lastname = ((TextView)view.findViewById(R.id.contact_view_lastname)).getText().toString();
            p.phonenumber = ((TextView)view.findViewById(R.id.contact_view_phonenumber)).getText().toString();
            p.address.addressStr = ((TextView)view.findViewById(R.id.contact_view_address)).getText().toString();
            p.address.city = ((TextView)view.findViewById(R.id.contact_view_city)).getText().toString();
            p.address.state = ((TextView)view.findViewById(R.id.contact_view_state)).getText().toString();
            p.address.zip = ((TextView)view.findViewById(R.id.contact_view_zip)).getText().toString();

            activity.contactFromPhone = p;


            activity.StartAvailabilityFragment();

            return true;
        }
        return false;
    }
}
