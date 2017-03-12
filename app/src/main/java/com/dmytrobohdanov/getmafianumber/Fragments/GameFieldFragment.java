package com.dmytrobohdanov.getmafianumber.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dmytrobohdanov.getmafianumber.R;


public class GameFieldFragment extends BaseFragment {
    public static final String TAG = "gameFieldFragmentTag";

    //views
    ImageView playView;
    ImageView pauseView;
    ImageView stopView;
    ImageView volumeMaxView;
    ImageView volumeMuteView;

    public GameFieldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_field, container, false);

        //adding audio player fragment
        //player's views
        playView = (ImageView) rootView.findViewById(R.id.player_play);
        pauseView = (ImageView) rootView.findViewById(R.id.player_pause);
        stopView = (ImageView) rootView.findViewById(R.id.player_stop);
        volumeMuteView = (ImageView) rootView.findViewById(R.id.player_volume_mute);
        volumeMaxView = (ImageView) rootView.findViewById(R.id.player_volume_max);


        return rootView;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
