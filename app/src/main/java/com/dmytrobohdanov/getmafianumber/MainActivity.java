package com.dmytrobohdanov.getmafianumber;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.dmytrobohdanov.getmafianumber.Activities.DrawerFragmentNavigationActivity;
import com.dmytrobohdanov.getmafianumber.Fragments.GetPlayersNumberFragment;

public class MainActivity extends DrawerFragmentNavigationActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        drawerId = R.id.drawer_layout;
        fragmentContainersId = R.id.main_activity_container;

        showFragmentAddToBackStack(GetPlayersNumberFragment.TAG, null);
    }


    @Override
    protected void processDrawerSelection(View drawersElement) {

    }

    @Override
    protected Fragment getFragmentToShowByTag(String fragmentTag, Object data) {
        return new GetPlayersNumberFragment();
    }
}
