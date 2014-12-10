package com.finalproject.cs4962.childcare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Braden on 12/9/2014.
 */
public class ContactFragment extends Fragment {

    public MainActivity activity;
    public ArrayList<ContactRowData> data;
    public View.OnTouchListener handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ArrayAdapter<ContactRowData> dataArrayAdapter = new ContactArrayAdapter(activity,
                R.layout.contact_view,
                data,
                //GetSampleData(),
                handler,
                activity);
        //

        ListView listview = (ListView)inflater.inflate(R.layout.contact_view_list, null);

        //View header = (View)getLayoutInflater().inflate(R.layout.contact_view_list_header, null);
        //view.addHeaderView(header);
        listview.setAdapter(dataArrayAdapter);

        return listview;
    }
    private ArrayList<ContactRowData> GetSampleData() {
        ArrayList<ContactRowData> data = new ArrayList<ContactRowData>();
        ContactRowData row = new ContactRowData();
        row.firstName = "Braden";
        row.lastName = "Edmunds";
        row.phoneNumber = "8017922179";
        row.address = "220 South Elizabeth";
        row.city = "Salt Lake City";
        row.state = "UT";
        row.zip = "84102";

        data.add(row);
        return data;
    }
}
