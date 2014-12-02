package com.finalproject.cs4962.childcare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView view = (ListView)findViewById(R.id.list);

    }
    ///////////////////////////////////////////////////////////////////////////
    protected ListView addItemsToListView(ListView view, ArrayList<String> items) {
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
    }
    ///////////////////////////////////////////////////////////////////////////
    protected void LoadMainMenu() {

    }
    ///////////////////////////////////////////////////////////////////////////
}
