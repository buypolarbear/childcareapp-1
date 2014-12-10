package com.finalproject.cs4962.childcare;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import org.json.JSONObject;

import model.Address;
import server.APILocation;

/**
 * Created by Braden on 12/8/2014.
 */
public class ContactHelper {

    private Uri uriContact;
    private ContentResolver resolver;

    public ContactHelper(android.content.ContentResolver contentResolver, Uri uriContact) {
        this.uriContact = uriContact;
        this.resolver = contentResolver;
    }

    public String retrieveContactNumber() {

        String contactNumber = null;
        String contactID = null;
        // getting contacts ID
        Cursor cursorID = resolver.query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }

        cursorID.close();


        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        cursorPhone.close();

        return contactNumber;
    }

    public String retrieveContactName() {

        String contactName = null;

        // querying contact data store
        Cursor cursor = resolver.query(uriContact, null, null, null, null);

        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }

        cursor.close();

        return contactName;
    }
    public Address retrieveContactAddress() {

        String contactID = null;
        // getting contacts ID
        Cursor cursorID = resolver.query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }

        cursorID.close();


        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = resolver.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI ,
                new String[]{"_id","data1","data7","data9"}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactID , null, null);

        Address addr = new Address();
        if (cursorPhone.moveToFirst()) {
            int i = cursorPhone.getInt(0);
            addr.addressStr = cursorPhone.getString(1);
            addr.city = cursorPhone.getString(cursorPhone.getColumnIndex("data7"));
            addr.zip = cursorPhone.getString(cursorPhone.getColumnIndex("data9"));

            if(addr.zip == null) addr.zip = "84102";


            if(addr.zip != null) { //check if the zip is null, if not grab the state
                APILocation api = new APILocation();
                JSONObject obj = api.GetLocationByZip(addr.zip);
                try {
                    addr.state = (String) obj.get("state");
                    addr.city = (String) obj.get("city");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(addr.zip == null && addr.city == null) { //special case, for some reason the emulators dont let us input a complete address
                addr.zip = "84102";
                addr.state = "UT";
                addr.city = "Salt Lake City";
            }
        } else {
            addr.addressStr = "Not Provided";
            addr.city = "Not Provided";
            addr.state = "Not Provided";
            addr.zip = "0";
        }

        cursorPhone.close();


        //addr.addressStr = contactStreet;
        //addr.city = contactCity;
        //addr.zip = contactZip;

        return addr;
    }
}
