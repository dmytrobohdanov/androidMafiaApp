package com.dmytrobohdanov.getmafianumber.Fragments.PlayersListFragment;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.getmafianumber.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayersListRVAdapter extends RecyclerView.Adapter<PlayersListRVHolder> {

    private Context context;

    private ArrayList<String> namesList = new ArrayList<>(Arrays.asList("Name 0", "Name 1",
            "Name 2", "Name 3", "Name 4", "Name 5", "Name 6", "Name 7", "Name 8", "Name 9"));

    public PlayersListRVAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PlayersListRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.players_list_rv_holder, parent, false);
        return new PlayersListRVHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(PlayersListRVHolder holder, int position) {
        holder.textViewName.setText(namesList.get(position));
    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }
}
