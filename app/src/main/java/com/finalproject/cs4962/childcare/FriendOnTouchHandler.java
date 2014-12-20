package com.finalproject.cs4962.childcare;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Braden on 12/9/2014.
 */
public class FriendOnTouchHandler implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //nothing for now i think
        Toast.makeText(view.getContext(), "Touched: "+ view.getParent().getParent().toString() ,Toast.LENGTH_SHORT).show();

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
}
