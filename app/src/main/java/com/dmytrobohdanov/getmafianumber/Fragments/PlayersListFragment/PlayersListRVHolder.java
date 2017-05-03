package com.dmytrobohdanov.getmafianumber.Fragments.PlayersListFragment;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dmytrobohdanov.getmafianumber.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersListRVHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rv_holder_players_name)
    TextView textViewName;

    @BindView(R.id.rv_holder_players_alias)
    TextView textViewAlias;

    public PlayersListRVHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
