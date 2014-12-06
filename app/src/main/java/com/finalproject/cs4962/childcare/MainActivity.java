package com.finalproject.cs4962.childcare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.API;


public class MainActivity extends Activity {
    ///////////////////////////////////////////////////////////////////////////
    API api = new API();
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView)findViewById(R.id.listView);
        ContactCardArrayAdapter dataAdapter = new ContactCardArrayAdapter(this, R.id.sitterName, GetScheduledEvents(), new ContactCardClicked(this), this);
        list.setAdapter(dataAdapter);
    }
    ///////////////////////////////////////////////////////////////////////////
    public ArrayList<ContactCardRowData> GetScheduledEvents() {

        ArrayList<ContactCardRowData> data = new ArrayList<ContactCardRowData>();
        ContactCardRowData item = new ContactCardRowData();
        item.date = DateFormat.getDateInstance().format(new Date());
        item.startTime = "Now";
        item.endTime = "Never";
        item.parentname = "KARISSA!";
        item.sittername = "BRADEN!";
        item.numKids = "1";

        data.add(item);

        item = new ContactCardRowData();
        item.date = DateFormat.getDateInstance().format(new Date());
        item.startTime = "Now";
        item.endTime = "Never";
        item.parentname = "KARISSA!";
        item.sittername = "BRADEN!";
        item.numKids = "1";

        data.add(item);
        return data;
    }
    ///////////////////////////////////////////////////////////////////////////
    protected ListView addItemsToListView(ListView listview, List<ViewGroup> views) {

        final ArrayAdapter<ViewGroup> adapter = new ArrayAdapter<ViewGroup>(this, 0);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                if (item.contains("Main Menu")) {
                    LoadMainMenu();
                    return;
                }
            }
        });

        return listview;
    }
    ///////////////////////////////////////////////////////////////////////////
    public void LoadDetailedEventView(View view) {
        //TODO;
        setContentView(R.layout.detailed_event_view);
    }
    ///////////////////////////////////////////////////////////////////////////
    protected void LoadMainMenu() {

    }
    ///////////////////////////////////////////////////////////////////////////
}

