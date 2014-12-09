package com.finalproject.cs4962.childcare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Braden on 12/8/2014.
 */
public class ProfileFragment extends Fragment {

    public MainActivity _activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_view, container, false);
        _activity.SetProfileView(view);
        return view;
    }
}
