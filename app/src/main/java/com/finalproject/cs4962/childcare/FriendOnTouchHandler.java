package com.finalproject.cs4962.childcare;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;


/**
 * Created by Braden on 12/9/2014.
 */
public class FriendOnTouchHandler implements View.OnTouchListener {





    private static final String TAG = "FriendsList";
    Toast toast;
    //String[] mMenuItems = new String[] { "Events", "Friends", "Add Contact", "Add Event", "Profile" };
    private MainActivity _activity;
//    private int padding = 0;
//    private int initialx = 0;
//    private int currentx = 0;
   // private  ViewHolder viewHolder;
    MotionEvent moEv;

    private static View lastView;
//    private static int lastViewPos;
    private static Boolean lastViewGone = false;
    private static Boolean dialogVisible = false;

    public  FriendOnTouchHandler(MainActivity activity) {

        this._activity = activity;
    }

    int touchX,touchY;

    @Override
    public boolean onTouch(View view, MotionEvent event) {


        if(event.getAction()  == MotionEvent.ACTION_DOWN) {
            Log.i(TAG, "Pressed Contact Row: " + ((ListView) view.getParent()).getPositionForView(view));
            touchX = (int) event.getX();
            touchY = (int) event.getY();

            dialogVisible = false;

            // Return rowView to original after swipe view is not used
            if(lastViewGone)
            {
//                ((View) ((ListView) view.getParent()).getChildAt(lastViewPos)).setVisibility(View.VISIBLE);
//                ((View) ((ListView) view.getParent()).getChildAt(lastViewPos)).invalidate();
                lastView.setVisibility(View.VISIBLE);
                lastView.invalidate();
                lastViewGone = false;
            }


            return true; // false: no further need for same event
        }
        else if(event.getAction()  == MotionEvent.ACTION_UP) {
            if((int)event.getX() == touchX && (int)event.getY()== touchY) {
                Log.i(TAG, "Clicked Contact Row: " + ((ListView) view.getParent()).getPositionForView(view));

                _activity.LoadContactDetails(view); // on click, show contact detail view
                return false; // false: no further need for same event
            }
        }
        else  if(event.getAction()  == MotionEvent.ACTION_MOVE && event.getAction() != MotionEvent.ACTION_SCROLL)
        {
            Log.i(TAG, "Event Touch Action_MOVE: " + event.getAction() + "  " + ((ContactRowData) view.getTag()).firstName);

//            ContentResolver contentResolver = view.getContext().getContentResolver();
            if(!dialogVisible) {
                dialogVisible = true;

                //ContactHelper helper = new ContactHelper(view.getContext().getContentResolver(), ((ContactRowData)view.getTag()).uri);
                String name = "";//helper.retrieveContactName();
//                String number = helper.retrieveContactNumber();

               // ContactsContract.Contacts.Photo.CONTENT_DIRECTORY;

//                ImageView profile  = (ImageView)view.findViewById(R.id.contactViewImageView);
//                Uri my_contact_Uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(((ContactRowData)view.getTag()).uri));
//                InputStream photo_stream = ContactsContract.Contacts.openContactPhotoInputStream(view.getContext().getContentResolver(),my_contact_Uri);
//                BufferedInputStream buf =new BufferedInputStream(photo_stream);
//                Bitmap my_btmp = BitmapFactory.decodeStream(buf);
//                profile.setImageBitmap(my_btmp);
                //profile.setImageURI(((ContactRowData)view.getTag()).uri);



                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Select An Option for "+((ContactRowData)view.getTag()).firstName+": "+name);
                //builder.setIcon(photo_stream);
                builder.setItems(new CharSequence[]
                                {"Create event with", "More about this contact", "Delete contact", "Cancel"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialogVisible = false;
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
//                                    importContact_intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                                        //startActivityForResult(importContact_intent, ADD_CONTACT);
                                        //Toast.makeText(context, "clicked 1", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
//                                    importContact_intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
//                                    importContact_intent.putExtra(ContactsContract.Intents.EXTRA_FORCE_CREATE, true);
//                                    importContact_intent.putExtra("finishActivityOnSaveCompleted", true);
                                        //startActivityForResult(importContact_intent, ADD_CONTACT);
                                        //Toast.makeText(context, "clicked 2", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        //setTitle("Friends");
                                        //Toast.makeText(context, "clicked 3", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }
//            int layoutID = R.layout.contact_view_swipe;

            //lastViewPos = ((ListView) view.getParent()).getPositionForView(view);
            /*
            lastViewGone = true;
            lastView = view;
            view.setVisibility(View.GONE);
            */


//            LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService( view.getContext().LAYOUT_INFLATER_SERVICE );
//            view = inflater.inflate( R.layout.contact_view_swipe, null );
//            view.layout();

//
//            view.inflate(view.getContext(),R.layout.contact_view_swipe, null);
//            Button btn = new Button(view.getContext());
//            btn.setText("hello");
////            view.addView(btn);
//            //view.setVisibility(View.VISIBLE);
//            view.invalidate();
//            LinearLayout rl_inflate = (LinearLayout)view.findViewById(R.layout.contact_view_swipe);
//            View child = getLayoutInflater().inflate(R.layout.contact_view_swipe);
//            rl_inflate.addView(child);
            //view.inflate();
//            ((ListView) view.getParent()).addView();
           // _activity.contactViewSwipe(view);

            return false;
        }
        else  if(event.getAction()  == MotionEvent.ACTION_CANCEL)
        {
           // view.setVisibility(View.VISIBLE);
        }
        else
        {
            Log.i(TAG, "Event Touch Action: "+event.getAction());
        }
//
//        if (event.getAction() == 0) {
//
//            Log.i(TAG, "Touch 0: "+event.getAction());
//            touchX = (int) event.getX();
//            touchY = (int) event.getY();
//        }
//        if(event.getAction() == 1) {
//            if(event.getX() == touchX && event.getY()== touchY){
//                Log.i(TAG, "Touch 1: " + ((ListView) view.getParent()).getPositionForView(view));
//
//                _activity.LoadContactDetails(view);
//            }
//        }
        return true; // True: Keep watching the same event (drag, swipe, or click release)
    }



    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}
