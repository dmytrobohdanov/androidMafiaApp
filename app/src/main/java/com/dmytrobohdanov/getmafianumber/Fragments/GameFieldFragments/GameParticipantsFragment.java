package com.dmytrobohdanov.getmafianumber.Fragments.GameFieldFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.getmafianumber.R;


public class GameParticipantsFragment extends Fragment {


    public static final String TITLE = "Players";

    public GameParticipantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_participants, container, false);
    }

}
