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


    @Override
    public boolean onTouch(View view, MotionEvent moEv) {
        //nothing for now i think

      //  _activity = new MainActivity();

        Log.i(TAG, "Touched: " +  ((ListView) view.getParent()).getPositionForView(view));

        _activity.LoadContactDetails();
//        if(toast != null)
//            toast.cancel();
//        toast = Toast.makeText(view.getContext(), "Touched: " +  ((ListView) view.getParent()).getPositionForView(view), Toast.LENGTH_SHORT);
//        toast.show();


//        view.setBackgroundColor(Color.RED);
       // view.get
//        view.
       // ((ListView) view.getParent()).getItemAtPosition(view.getBottom());
//        view.getId()
//        ((ListView) view.getParent()).getAdapter().getItem(((ListView) view.getParent()).getSelectedItemPosition()).toString()
//        ((ArrayAdapter<ContactRowData>) ((ListView) view.getParent()).getAdapter()).toString()
//        ((ListView) view.getParent()).getPositionForView((View) view.getParent());

//        view.invalidate();
//        int layoutID = -1;
//        layoutID = R.layout.profile_view;
////        View inflated = view.inflate(layoutID, view.getParent().getParent(), false);
//        _activity.SetProfileView(view);


        // TODO Auto-generated method stub
//        Intent userCreationIntent = new Intent(view.getContext(), UserCreation.class);
//        _activity.startActivityForResult(userCreationIntent, 0);

//        PageFragment fragment = new PageFragment();
//        fragment.activity = _activity;
//        _activity.getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

//        return inflated;

//        view.getRootView();
//        _activity.LoadFriendDetails();
//        PageFragment fragment = new PageFragment();
//        Bundle args = new Bundle();
//        args.putString("Page", "Events");
//        fragment.activity = _activity;
//        fragment.setArguments(args, _activity.mMenuItems);
//
////        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = _activity.getFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


//       // view.getParent().bringChildToFront(view);
//        if(moEv.getAction()==MotionEvent.ACTION_MOVE)
//             toast = Toast.makeText(view.getContext(), "Touched Ac. Move: "+ view.toString(),Toast.LENGTH_SHORT);
//        else if(moEv.getAction()==MotionEvent.ACTION_CANCEL) {
//
//            toast = Toast.makeText(view.getContext(), "Touched Ac. Cancel: " + view.toString(), Toast.LENGTH_SHORT);
//        }
//        else if(moEv.getAction()==MotionEvent.ACTION_UP) {
//
//            toast = Toast.makeText(view.getContext(), "Touched Ac. Up Delta X: " + (initialx-(int) moEv.getX()), Toast.LENGTH_SHORT);
//        }
//        else if(moEv.getAction()==MotionEvent.ACTION_DOWN) {
//            initialx = (int) moEv.getX();
//
//            toast = Toast.makeText(view.getContext(), "Touched Ac. Down init X: " + initialx, Toast.LENGTH_SHORT);
//        }



//        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//            Intent edit=new Intent(vie.getParent(),EditActivity.class);
//            TabGroupActivity parentActivity=(TabGroupActivity)getParent();
//            parentActivity.startChildActivity("EditActivity",edit);
//            return true;
//        }
//        view.getParent().showContextMenuForChild(view);

//        int x = Math.round(motionEvent.getX());
//        int y = Math.round(motionEvent.getY());
//        for (int i=0; i<getChildCount(); i++){
//            LinearLayout child = (LinearLayout)row.getChildAt(i);
//            if(x > child.getLeft() && x < child.getRight() && y > child.getTop() && y < child.getBottom()){
//                //touch is within this child
//                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
//                    //touch has ended
//                }
//            }
//        }

//MainActivity.Contact_Data.get(view.get)
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
