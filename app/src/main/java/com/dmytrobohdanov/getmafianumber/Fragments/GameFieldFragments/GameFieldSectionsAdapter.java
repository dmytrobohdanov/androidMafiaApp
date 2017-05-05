package com.dmytrobohdanov.getmafianumber.Fragments.GameFieldFragments;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class GameFieldSectionsAdapter extends FragmentPagerAdapter {
    public GameFieldSectionsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimerAndMusicFragment();

            case 1:
                return new GameParticipantsFragment();

            case 2:
                return new CourtFragment();

            case 3:
                return new GameInfoFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return TimerAndMusicFragment.TITLE;

            case 1:
                return GameParticipantsFragment.TITLE;

            case 2:
                return CourtFragment.TITLE;

            case 3:
                return GameInfoFragment.TITLE;
        }
        return "error";
    }

    @Override
    public int getCount() {
        return 4;
    }
}
