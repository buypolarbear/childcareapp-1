package com.finalproject.cs4962.childcare;

import android.view.View;
import android.widget.EditText;

import model.Address;
import model.Person;

/**
 * Created by Braden on 12/7/2014.
 */
public class AddContactButtonListener implements View.OnClickListener {

    public Person addedPerson = null;
    private MainActivity _activity;
    public AddContactButtonListener(MainActivity activity) {
        this._activity = activity;
    }
    @Override
    public void onClick(View view) {

        view = (View)view.getParent();
        String firstname = ((EditText) view.findViewById(R.id.firstname)).getText().toString();
        String lastname = ((EditText) view.findViewById(R.id.lastname)).getText().toString();
        String address = ((EditText) view.findViewById(R.id.address)).getText().toString();
        String city = ((EditText) view.findViewById(R.id.city)).getText().toString();
        String state = ((EditText) view.findViewById(R.id.state)).getText().toString();
        String zipcode = ((EditText) view.findViewById(R.id.zip)).getText().toString();

        addedPerson = new Person();
        addedPerson.firstname = firstname;
        addedPerson.lastname = lastname;
        Address addr = new Address();
        addr.addressStr = address;
        addr.city = city;
        addr.state = state;
        addr.zip = zipcode;
        addedPerson.address = addr;

        AvailabilityFragment fragment = new AvailabilityFragment();
        fragment._activity = _activity;
        _activity.getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
}
