package com.finalproject.cs4962.childcare;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Braden on 12/6/2014.
 */
public class EventCardClicked implements View.OnTouchListener {

    public MainActivity activity;
    public EventCardClicked(MainActivity Activity) {
        this.activity = Activity;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            //show the details screen
            activity.LoadDetailedEventView(view);
            return true;
        }
        return false;
    }
}
