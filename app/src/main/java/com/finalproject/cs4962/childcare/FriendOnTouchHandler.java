package com.finalproject.cs4962.childcare;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


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



    public  FriendOnTouchHandler(MainActivity activity) {

        this._activity = activity;
    }

    int touchX,touchY;

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if(event.getAction()  == MotionEvent.ACTION_UP) {
            Log.i(TAG, "Clicked Contact Row: " + ((ListView) view.getParent()).getPositionForView(view));

            _activity.LoadContactDetails(view); // on click, show contact detail view

            return false; // false: no further need for same event
        }
        else  if(event.getAction()  == MotionEvent.ACTION_MOVE)
        {
            Log.i(TAG, "Event Touch Action_MOVE: "+event.getAction());
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
