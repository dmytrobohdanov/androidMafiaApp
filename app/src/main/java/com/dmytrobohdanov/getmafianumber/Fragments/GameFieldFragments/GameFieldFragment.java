package com.dmytrobohdanov.getmafianumber.Fragments.GameFieldFragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.getmafianumber.Fragments.SupportFragments.BaseFragment;
import com.dmytrobohdanov.getmafianumber.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GameFieldFragment extends BaseFragment {
    public static final String TAG = "gameFieldFrTag";
    @BindView(R.id.game_field_viewPager)
    public ViewPager viewPager;
    private GameFieldSectionsAdapter gameFieldSectionsAdapter;


    public GameFieldFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_field, container, false);
        ButterKnife.bind(this, rootView);

//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.game_field_toolbar);
//        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        gameFieldSectionsAdapter = new GameFieldSectionsAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(gameFieldSectionsAdapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
