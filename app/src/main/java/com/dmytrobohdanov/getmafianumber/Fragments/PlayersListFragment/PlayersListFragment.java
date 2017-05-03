package com.dmytrobohdanov.getmafianumber.Fragments.PlayersListFragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.getmafianumber.Fragments.SupportFragments.BaseFragment;
import com.dmytrobohdanov.getmafianumber.R;


public class PlayersListFragment extends BaseFragment {
    public static final String TAG = "playersListFragment";

    private RecyclerView recyclerView;
    private PlayersListRVAdapter rvAdapter;

    public PlayersListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_players_list, container, false);
        initRV(rootView);
        return rootView;
    }

    private void initRV(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_players_list_rv_players);

        RecyclerView.LayoutManager rvLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(rvLayoutManager);

        rvAdapter = new PlayersListRVAdapter(getContext());
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
