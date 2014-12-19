package com.finalproject.cs4962.childcare;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


/**
 * Created by Braden on 12/9/2014.
 */
public class FriendOnTouchHandler implements View.OnTouchListener {

    Toast toast;
    private int padding = 0;
    private int initialx = 0;
    private int currentx = 0;
   // private  ViewHolder viewHolder;

    MotionEvent moEv;
    @Override
    public boolean onTouch(View view, MotionEvent moEv) {
        //nothing for now i think

        if(toast != null)
            toast.cancel();

       // view.getParent().bringChildToFront(view);
        if(moEv.getAction()==MotionEvent.ACTION_MOVE)
             toast = Toast.makeText(view.getContext(), "Touched Ac. Move: "+ view.toString(),Toast.LENGTH_SHORT);
        else if(moEv.getAction()==MotionEvent.ACTION_CANCEL) {

            toast = Toast.makeText(view.getContext(), "Touched Ac. Cancel: " + view.toString(), Toast.LENGTH_SHORT);
        }
        else if(moEv.getAction()==MotionEvent.ACTION_UP) {

            toast = Toast.makeText(view.getContext(), "Touched Ac. Up Delta X: " + (initialx-(int) moEv.getX()), Toast.LENGTH_SHORT);
        }
        else if(moEv.getAction()==MotionEvent.ACTION_DOWN) {
            initialx = (int) moEv.getX();

            toast = Toast.makeText(view.getContext(), "Touched Ac. Down init X: " + initialx, Toast.LENGTH_SHORT);
        }

        toast.show();

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
