package com.dmytrobohdanov.getmafianumber.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.getmafianumber.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetPlayersNumberFragment extends Fragment {


    public GetPlayersNumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_players_number, container, false);
    }

}
