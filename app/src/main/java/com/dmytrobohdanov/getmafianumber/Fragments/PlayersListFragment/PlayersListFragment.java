package com.dmytrobohdanov.getmafianumber.Fragments.PlayersListFragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.getmafianumber.Fragments.AddNewPlayerDialogFragment;
import com.dmytrobohdanov.getmafianumber.Fragments.SupportFragments.BaseFragment;
import com.dmytrobohdanov.getmafianumber.R;
import com.dmytrobohdanov.getmafianumber.Utils.DataBaseUtils.DataModels.PlayerDataModel;
import com.dmytrobohdanov.getmafianumber.Utils.DataBaseUtils.DatabaseUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.ReplaySubject;


public class PlayersListFragment extends BaseFragment implements AddNewPlayerDialogFragment.AddNewPlayerDialogListener {
    public static final String TAG = "playersListFragment";

    @BindView(R.id.players_list_fragment_fab)
    FloatingActionButton fab;
    ReplaySubject<Void> subject;
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
        ButterKnife.bind(this, rootView);
        initRV(rootView);

//        subject = PublishSubject.create();
//        subject.subscribe(this::notifyUserAdded);
//
        DatabaseUtils.addNewUser(new PlayerDataModel("dbName1", "dbAlias1"));
        DatabaseUtils.addNewUser(new PlayerDataModel("dbName2", "dbAlias2"));
        DatabaseUtils.addNewUser(new PlayerDataModel("dbName3", "dbAlias3"));

        new Handler().postDelayed(() -> {
            DatabaseUtils.addNewUser(new PlayerDataModel("dbName4", "dbAlias5"));
            DatabaseUtils.addNewUser(new PlayerDataModel("dbName5", "dbAlias5"));
        }, 10000);

        fab.setOnClickListener(view -> showAddNewPlayerDialog());

        return rootView;
    }

//    public void notifyUserAdded(Void v) {
////        rvAdapter.setData(DatabaseUtils.getPlayersList());
//        rvAdapter.notifyDataSetChanged();
//    }

    private void showAddNewPlayerDialog() {
        AddNewPlayerDialogFragment dialog = new AddNewPlayerDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "AddNewPlayerDialog");
        DatabaseUtils.addNewUser(new PlayerDataModel("dbName6", "dbAlias6"));
        DatabaseUtils.addNewUser(new PlayerDataModel("dbName7", "dbAlias7"));
    }

    private void initRV(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_players_list_rv_players);

        RecyclerView.LayoutManager rvLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(rvLayoutManager);


        ReplaySubject<ArrayList<PlayerDataModel>> subject = ReplaySubject.create();

        DatabaseUtils.getPlayersList(subject);
        rvAdapter = new PlayersListRVAdapter(getContext(), subject);

        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //todo
    }
}
