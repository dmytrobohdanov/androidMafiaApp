package com.dmytrobohdanov.getmafianumber.Activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.dmytrobohdanov.getmafianumber.Fragments.GameFieldFragment;
import com.dmytrobohdanov.getmafianumber.Fragments.GetPlayersNumberFragment;
import com.dmytrobohdanov.getmafianumber.R;

public class MainActivity extends DrawerFragmentNavigationActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        drawerId = R.id.drawer_layout;
        fragmentContainersId = R.id.main_activity_container;

//        showFragmentAddToBackStack(GetPlayersNumberFragment.TAG, null);
        showFragmentAddToBackStack(GameFieldFragment.TAG, null);
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
        }
    }

    @Override
    protected Fragment getFragmentToShowByTag(String fragmentTag, Object data) {
        switch (fragmentTag) {
            case GameFieldFragment.TAG:
                return new GameFieldFragment();

            default:
                return new GetPlayersNumberFragment();
        }
    }
}
