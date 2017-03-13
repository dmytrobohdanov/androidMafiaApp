package com.dmytrobohdanov.getmafianumber.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.dmytrobohdanov.getmafianumber.Activities.MainActivity;

/**
 *
 */
public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setFragment(this);
        super.onResume();
    }

    public abstract String getFragmentTag();
}
