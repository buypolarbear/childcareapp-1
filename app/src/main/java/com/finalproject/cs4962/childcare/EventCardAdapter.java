package com.finalproject.cs4962.childcare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Braden on 12/4/2014.
 */



class EventViewHolder {
    TextView sitterName, dateTimes, parentName, numKids;
    ImageView contactPicture;
    ImageView arrow;
}

class EventCardRowData {


    /*holder.date.setText(list.get(position).date);
    holder.sitterName.setText(list.get(position).sittername);
    holder.startTime.setText(list.get(position).startTime);
    holder.endTime.setText(list.get(position).endTime);
    holder.parentName.setText(list.get(position).parentName);
    holder.numKids.setText(list.get(position).numKids);*/

    public String date="", sittername="", startTime="", endTime="", parentname="", numKids="";
}

public class EventCardAdapter extends ArrayAdapter<EventCardRowData> {

    private ArrayList<EventCardRowData> list;
    private MainActivity _activity;
    private View.OnTouchListener _handler;
    //this custom adapter receives an ArrayList of RowData objects.
    //RowData is my class that represents the data for a single row and could be anything.
    public EventCardAdapter(Context context,
                                   int textViewResourceId,
                                   ArrayList<EventCardRowData> rowDataList,
                                   View.OnTouchListener handler,
                                   MainActivity activity)
    {
        //populate the local list with data.
        super(context, textViewResourceId, rowDataList);
        this.list = new ArrayList<EventCardRowData>();
        this.list.addAll(rowDataList);

        this._activity = activity;
        this._handler = handler;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //creating the ViewHolder we defined earlier.
        EventViewHolder holder = new EventViewHolder();

        //creating LayoutInflater for inflating the row layout.
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the row layout we defined earlier.
        convertView = inflater.inflate(R.layout.event_view, null);
        convertView.setOnTouchListener(this._handler);

        //setting the views into the ViewHolder.
        holder.dateTimes = (TextView) convertView.findViewById(R.id.dateTimes);
        holder.sitterName = (TextView) convertView.findViewById(R.id.sitterName);
        holder.parentName = (TextView) convertView.findViewById(R.id.parentName);
//        holder.numKids = (TextView) convertView.findViewById(R.id.numKids);
//        holder.contactPicture = (ImageView) convertView.findViewById(R.id.contactPicture);
//        holder.arrow = (ImageView) convertView.findViewById(R.id.arrow);

        /*//define an onClickListener for the ImageView.
        /*holder.changeRowStatus.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(activity, "Image from row " + position + " was pressed", Toast.LENGTH_LONG).show();
            }
        });
        holder.checked = (CheckBox) convertView.findViewById(R.id.cbCheckListItem);
        holder.checked.setTag(position);

        //define an onClickListener for the CheckBox.
        holder.checked.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //assign check-box state to the corresponding object in list.
                CheckBox checkbox = (CheckBox) v;
                rowDataList.get(position).setChecked(checkbox.isChecked());
                Toast.makeText(activity, "CheckBox from row " + position + " was checked", Toast.LENGTH_LONG).show();
            }
        });

        //setting data into the the ViewHolder.*/
        //holder.title.setText(rowDataList.get(position).getName());
        //holder.checked.setChecked(rowDataList.get(position).isChecked());

//        Random rand = new Random();
//        switch(rand.nextInt(3)) {
//            case 0:
//                holder.contactPicture.setImageResource(R.drawable.profile_pic_1);
//                break;
//            case 1:
//                holder.contactPicture.setImageResource(R.drawable.profile_pic_1);
//                break;
//            case 2:
//                holder.contactPicture.setImageResource(R.drawable.profile_pic_1);
//                break;
//        }




        Log.i("EventCardAdapter","SitterName: "+position);
//        holder.dateTimes.setText(list.get(position).date + " " + list.get(position).startTime + " - " + list.get(position).endTime);
        holder.sitterName.setText("Santa");
        holder.dateTimes.setText("12/24/2014 6:00pm - 10:00pm");
        holder.parentName.setText("Mommy");
//        holder.parentName.setText(list.get(position).parentname);

//        String append = "";
//        if(Integer.parseInt(list.get(position).numKids) > 1)
//            append = " Children";
//        else
//            append = " Child";


 //       holder.numKids.setText(list.get(position).numKids + append);


//        holder.arrow.setImageResource(R.drawable.ic_action_name2);

        //return the row view.
        return convertView;
    }

}