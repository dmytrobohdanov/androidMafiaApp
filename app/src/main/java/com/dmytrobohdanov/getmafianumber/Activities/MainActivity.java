package com.dmytrobohdanov.getmafianumber.Activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dmytrobohdanov.getmafianumber.Fragments.GameFieldFragments.GameFieldFragment;
import com.dmytrobohdanov.getmafianumber.Fragments.GetPlayersNumberFragment;
import com.dmytrobohdanov.getmafianumber.Fragments.PlayersListFragment.PlayersListFragment;
import com.dmytrobohdanov.getmafianumber.R;

public class MainActivity extends DrawerFragmentNavigationActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        drawerId = R.id.drawer_layout;
        fragmentContainersId = R.id.main_activity_container;

        showFragmentAddToBackStack(GetPlayersNumberFragment.TAG, null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    protected void processDrawerSelection(View drawersElement) {
        switch (drawersElement.getId()) {
            case R.id.drawer_item_get_players_number:
                showFragmentNoBackStack(GetPlayersNumberFragment.TAG, null);
                break;

            case R.id.drawer_item_game_field:
                showFragmentAddToBackStack(GameFieldFragment.TAG, null);
                break;

            case R.id.drawer_item_players_list:
                showFragmentAddToBackStack(PlayersListFragment.TAG, null);
                break;
        }
    }

    @Override
    protected Fragment getFragmentToShowByTag(String fragmentTag, Object data) {
        switch (fragmentTag) {
            case GameFieldFragment.TAG:
                return new GameFieldFragment();

            case PlayersListFragment.TAG:
                return new PlayersListFragment();

            default:
                return new GetPlayersNumberFragment();
        }
    }
}
