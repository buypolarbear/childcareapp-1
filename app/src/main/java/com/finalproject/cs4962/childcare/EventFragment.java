package com.finalproject.cs4962.childcare;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by AKD
 */
public class EventFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EventFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static EventFragment newInstance(String param1, String param2) {
//        EventFragment fragment = new EventFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public EventFragment() {
        // Required empty public constructor
    }


    public MainActivity activity;
    public ArrayList<EventCardRowData> data;
    public View.OnTouchListener handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        ArrayAdapter<EventCardRowData> dataArrayAdapter = new EventCardAdapter(activity,
                R.layout.contact_view,
                data,
                handler,
                activity);

        ListView listview = (ListView)inflater.inflate(R.layout.contact_view_list, null);

        listview.setAdapter(dataArrayAdapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        return listview;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_event, container, false);
    }








}
