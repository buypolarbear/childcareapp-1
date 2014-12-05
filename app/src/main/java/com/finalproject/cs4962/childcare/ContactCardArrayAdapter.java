package com.finalproject.cs4962.childcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Braden on 12/4/2014.
 */
class ContactViewHolder {
    TextView sitterName, date, startTime, endTime, parentName, numKids;
}

public class ContactCardArrayAdapter extends ArrayAdapter<ContactCardRowData> {

    private ArrayList<ContactCardRowData> list;

    //this custom adapter receives an ArrayList of RowData objects.
    //RowData is my class that represents the data for a single row and could be anything.
    public ContactCardArrayAdapter(Context context, int textViewResourceId, ArrayList<ContactCardRowData> rowDataList)
    {
        //populate the local list with data.
        super(context, textViewResourceId, rowDataList);
        this.list = new ArrayList<ContactCardRowData>();
        this.list.addAll(rowDataList);
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //creating the ViewHolder we defined earlier.
        ContactViewHolder holder = new ContactViewHolder();

        //creating LayoutInflator for inflating the row layout.
        LayoutInflater inflator = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the row layout we defined earlier.
        convertView = inflator.inflate(R.layout.contact_view, null);

        //setting the views into the ViewHolder.
        holder.date = (TextView) convertView.findViewById(R.id.date);
        holder.sitterName = (TextView) convertView.findViewById(R.id.sitterName);
        holder.startTime = (TextView) convertView.findViewById(R.id.startTime);
        holder.endTime = (TextView) convertView.findViewById(R.id.endTime);
        holder.parentName = (TextView) convertView.findViewById(R.id.parentName);
        holder.numKids = (TextView) convertView.findViewById(R.id.numKids);


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

        holder.date.setText(list.get(position).date);
        holder.sitterName.setText(list.get(position).sittername);
        holder.startTime.setText(list.get(position).startTime);
        holder.endTime.setText(list.get(position).endTime);
        holder.parentName.setText(list.get(position).parentname);
        holder.numKids.setText(list.get(position).numKids);

        //return the row view.
        return convertView;
    }
}


