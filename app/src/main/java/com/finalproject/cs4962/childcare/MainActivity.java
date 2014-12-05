package com.finalproject.cs4962.childcare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
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
        ContactCardArrayAdapter dataAdapter = new ContactCardArrayAdapter(this, R.id.sitterName, GetScheduledEvents());
        list.setAdapter(dataAdapter);
    }
    ///////////////////////////////////////////////////////////////////////////
    public ArrayList<ContactCardRowData> GetScheduledEvents() {
        ArrayList<ContactCardRowData> data = new ArrayList<ContactCardRowData>();
        ContactCardRowData item = new ContactCardRowData();
        item.date = "Today";
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
        /*for(View view : views){
            listview.addView(view);
        }*/
        //add the back button
        //TextView back = new TextView(this);
        //back.setText("Return to Main Menu");
        //views.(back);

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
    /*protected ListView addItemsToListView(ListView view, ArrayList<String> items) {
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < items.size(); ++i) {
            list.add(items.get(i));
        }

        //add the back button
        list.add("Return to Main Menu");

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        view.setAdapter(adapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                if (item.contains("Main Menu")) {
                    LoadMainMenu();
                    return;
                }
            }
        });

        return view;
    }*/
    ///////////////////////////////////////////////////////////////////////////
    protected void LoadMainMenu() {

    }
    ///////////////////////////////////////////////////////////////////////////
}

