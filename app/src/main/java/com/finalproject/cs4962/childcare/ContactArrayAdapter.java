package com.finalproject.cs4962.childcare;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.net.Uri;
import java.util.ArrayList;

/**
 * Created by Braden on 12/9/2014.
 */
class ContactViewHolder {
    TextView firstName, lastName, phoneNumber, address, city, state, zip;
    Uri uri;
//    ImageView img;
}
class ContactRowData {
    String firstName, lastName, phoneNumber, address, city, state, zip;
    Uri uri;
//    Bitmap imgBit;
}

public class ContactArrayAdapter extends ArrayAdapter<ContactRowData> {
    private ArrayList<ContactRowData> list;
    private MainActivity _activity;
    private View.OnTouchListener _handler;
    //this custom adapter receives an ArrayList of RowData objects.
    //RowData is my class that represents the data for a single row and could be anything.
    public ContactArrayAdapter(Context context,
                            int textViewResourceId,
                            ArrayList<ContactRowData> rowDataList,
                            View.OnTouchListener handler,
                            MainActivity activity)
    {
        //populate the local list with data.
        super(context, textViewResourceId, rowDataList);
        this.list = new ArrayList<ContactRowData>();
        this.list.addAll(rowDataList);

        this._activity = activity;
        this._handler = handler;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ContactViewHolder holder = new ContactViewHolder();
        //creating LayoutInflator for inflating the row layout.
        LayoutInflater inflator = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the row layout we defined earlier.
        convertView = inflator.inflate(R.layout.contact_view, null);
        convertView.setOnTouchListener(this._handler);

        holder.firstName = (TextView) convertView.findViewById(R.id.contact_view_firstname);
        holder.lastName = (TextView) convertView.findViewById(R.id.contact_view_lastname);
        holder.phoneNumber = (TextView) convertView.findViewById(R.id.contact_view_phonenumber);
        holder.address = (TextView) convertView.findViewById(R.id.contact_view_address);
        holder.city = (TextView) convertView.findViewById(R.id.contact_view_city);
        holder.state = (TextView) convertView.findViewById(R.id.contact_view_state);
        holder.zip = (TextView) convertView.findViewById(R.id.contact_view_zip);
        //holder.img = (ImageView) convertView.findViewById(R.id.contact_view_imageView);

        ContactRowData data = list.get(position);
        holder.firstName.setText(data.firstName);
        holder.lastName.setText(data.lastName);
        holder.phoneNumber.setText(data.phoneNumber);
        holder.address.setText(data.address);
        holder.city.setText(data.city);
        holder.state.setText(data.state);
        holder.zip.setText(data.zip);

//        if(data.imgBit != null)
//            holder.img.setImageBitmap(data.imgBit);

        convertView.setTag(data);
        return convertView;
    }
}
