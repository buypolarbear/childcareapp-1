package com.finalproject.cs4962.childcare;

import android.content.Context;
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
class ContactViewHolder {
    TextView sitterName, dateTimes, parentName, numKids;
    ImageView contactPicture;
    ImageView arrow;
}

public class ContactCardArrayAdapter extends ArrayAdapter<ContactCardRowData> {

    private ArrayList<ContactCardRowData> list;
    private MainActivity _activity;
    private View.OnTouchListener _handler;
    //this custom adapter receives an ArrayList of RowData objects.
    //RowData is my class that represents the data for a single row and could be anything.
    public ContactCardArrayAdapter(Context context,
                                   int textViewResourceId,
                                   ArrayList<ContactCardRowData> rowDataList,
                                   View.OnTouchListener handler,
                                   MainActivity activity)
    {
        //populate the local list with data.
        super(context, textViewResourceId, rowDataList);
        this.list = new ArrayList<ContactCardRowData>();
        this.list.addAll(rowDataList);

        this._activity = activity;
        this._handler = handler;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //creating the ViewHolder we defined earlier.
        ContactViewHolder holder = new ContactViewHolder();

        //creating LayoutInflator for inflating the row layout.
        LayoutInflater inflator = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the row layout we defined earlier.
        convertView = inflator.inflate(R.layout.contact_view, null);
        convertView.setOnTouchListener(this._handler);

        //setting the views into the ViewHolder.
        holder.dateTimes = (TextView) convertView.findViewById(R.id.dateTimes);
        holder.sitterName = (TextView) convertView.findViewById(R.id.sitterName);


        holder.parentName = (TextView) convertView.findViewById(R.id.parentName);
        holder.numKids = (TextView) convertView.findViewById(R.id.numKids);
        holder.contactPicture = (ImageView) convertView.findViewById(R.id.contactPicture);
        holder.arrow = (ImageView) convertView.findViewById(R.id.arrow);

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

        Random rand = new Random();
        switch(rand.nextInt(3)) {
            case 0:
                holder.contactPicture.setImageResource(R.drawable.profile_pic_1);
                break;
            case 1:
                holder.contactPicture.setImageResource(R.drawable.profile_pic_1);
                break;
            case 2:
                holder.contactPicture.setImageResource(R.drawable.profile_pic_1);
                break;
        }

        holder.dateTimes.setText(list.get(position).date + " " + list.get(position).startTime + " - " + list.get(position).endTime);
        holder.sitterName.setText(list.get(position).sittername);
        holder.parentName.setText(list.get(position).parentname);

        String append = "";
        if(Integer.parseInt(list.get(position).numKids) > 1)
            append = " Children";
        else
            append = " Child";


        holder.numKids.setText(list.get(position).numKids + append);


        holder.arrow.setImageResource(R.drawable.ic_action_name2);

        //return the row view.
        return convertView;
    }
}


