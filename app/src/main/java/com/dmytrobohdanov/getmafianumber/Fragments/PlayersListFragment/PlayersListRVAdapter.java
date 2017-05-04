package com.dmytrobohdanov.getmafianumber.Fragments.PlayersListFragment;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.getmafianumber.R;
import com.dmytrobohdanov.getmafianumber.Utils.DataBaseUtils.DataModels.PlayerDataModel;

import java.util.ArrayList;

import io.reactivex.subjects.BehaviorSubject;


public class PlayersListRVAdapter extends RecyclerView.Adapter<PlayersListRVHolder> {

    private Context context;
    private BehaviorSubject subject;

    private ArrayList<PlayerDataModel> playersList = new ArrayList<>();

    public PlayersListRVAdapter(Context context, BehaviorSubject<ArrayList<PlayerDataModel>> subject) {
        this.context = context;
        this.subject = subject;
        subject.subscribe(this::handleDataUpdate);
    }

    public void handleDataUpdate(ArrayList<PlayerDataModel> playersList) {
        this.playersList = playersList;
        notifyDataSetChanged();
    }

    @Override
    public PlayersListRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.players_list_rv_holder, parent, false);
        return new PlayersListRVHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(PlayersListRVHolder holder, int position) {
        holder.textViewName.setText(playersList.get(position).getName());
        holder.textViewAlias.setText(playersList.get(position).getAlias());
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }
}
