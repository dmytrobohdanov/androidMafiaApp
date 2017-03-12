package com.dmytrobohdanov.getmafianumber.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.getmafianumber.R;


public class GameFieldFragment extends PlayerFragment {
    public static final String TAG = "gameFieldFragmentTag";


    public GameFieldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_field, container, false);

        initPlayerViews(rootView, R.id.player_play, R.id.player_pause, R.id.player_stop, R.id.player_volume_max, R.id.player_volume_mute);

        return rootView;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
