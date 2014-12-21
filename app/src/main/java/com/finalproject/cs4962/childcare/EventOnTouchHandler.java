package com.finalproject.cs4962.childcare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;



/**
 * Created by akdPro on 12/20/14.
 */
public class EventOnTouchHandler implements View.OnTouchListener {





    private static final String TAG = "EventsList";
    Toast toast;
    private MainActivity _activity;
    MotionEvent moEv;

    private static View lastView;
    //    private static int lastViewPos;
    private static Boolean lastViewGone = false;
    private static Boolean dialogVisible = false;

    public  EventOnTouchHandler(MainActivity activity) {

        this._activity = activity;
    }

    int touchX,touchY;

    @Override
    public boolean onTouch(final View view, MotionEvent event) {


        final int viewPos = ((ListView) view.getParent()).getPositionForView(view);


        if(event.getAction()  == MotionEvent.ACTION_DOWN) {
            Log.i(TAG, "Pressed Event Row: " + viewPos);
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
                Log.i(TAG, "Clicked Event Row: " + viewPos);

                _activity.LoadDetailedEventView(view); // on click, show Event detail view
                return false; // false: no further need for same event
            }
        }
        else  if(event.getAction()  == MotionEvent.ACTION_MOVE && event.getAction() != MotionEvent.ACTION_SCROLL)
        {
//            Log.i(TAG, "Event Touch Action_MOVE: " + event.getAction() + "  " + ((EventCardRowData) view.getTag()).sittername);

//            ContentResolver contentResolver = view.getContext().getContentResolver();
            if(!dialogVisible) {
                dialogVisible = true;

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Select An Option for this event:");
                //builder.setIcon(photo_stream);
                builder.setItems(new CharSequence[]
                                {"More about the event", "Delete event", "Cancel"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialogVisible = false;
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        //Toast.makeText(context, "clicked 1", Toast.LENGTH_SHORT).show();
                                        _activity.LoadDetailedEventView(view);
//                                        _activity.selectItem(_activity.addEventPos);
                                        break;
                                    case 1:
                                        confirmAction(view, viewPos);
                                        //Toast.makeText(context, "clicked 2", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:

                                        break;
                                }
                            }
                        });
                builder.create().show();
            }



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

        return true; // True: Keep watching the same event (drag, swipe, or click release)
    }



    public boolean confirmAction(final View view, final int pos)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Remove Event?");
        builder.setMessage("Removing a event will remove all related information!")
                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        _activity.removeEvent(pos);
                        _activity.selectItem(_activity.eventsPos);
//                        ((ListView)view.getParent()).invalidate();
                        Toast.makeText(view.getContext(), "Event has been removed!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked Cancel button

                    }
                });

        builder.create().show();

        return false;
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
