package com.dmytrobohdanov.getmafianumber.Fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.getmafianumber.R;
import com.github.lzyzsd.circleprogress.DonutProgress;


public class GameFieldFragment extends PlayerFragment {
    public static final String TAG = "gameFieldFragmentTag";

    private static final int ONE_SEC = 1000;
    private static final int ONE_MINUTE = 60000;
    private static final int HALF_MINUTE = 30000;


    DonutProgress timerView;
    CountDownTimer counter;
//    int timeInterval;

    public GameFieldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_field, container, false);

        initPlayerViews(rootView, R.id.player_play, R.id.player_pause, R.id.player_stop, R.id.player_volume_max, R.id.player_volume_mute);

        //initializing timer view
        timerView = (DonutProgress) rootView.findViewById(R.id.timerView);

//        initTimer(ONE_MINUTE);

        startTimerFor(ONE_MINUTE);
        return rootView;
    }

    private void startTimerFor(int sec) {
        initTimer(sec);

        //saving time interval
//        timeInterval = sec;

        //display time
        timerView.setText("" + sec);

        //starting time count
        counter = new CountDownTimer(sec, ONE_SEC) {
            public void onTick(long millisUntilFinished) {
                updateProgressOfTimer(millisUntilFinished);
            }

            public void onFinish() {
                countDownFinished();
            }
        }.start();
    }

    /**
     * Handle action after count down finished
     */
    private void countDownFinished() {
        timerView.setText("done");
        timerView.setProgress(0);
    }

    /**
     * Updating progress data in timer view
     * setting progress and text to view
     */
    private void updateProgressOfTimer(long millisUntilFinished) {
        int secondsUntilFinish = (int) millisUntilFinished / 1000;
        timerView.setProgress(secondsUntilFinish);

        timerView.setText("" + secondsUntilFinish);
    }

    /**
     * Initializing timer with starting values
     *
     * @param sec target seconds for count down
     */
    private void initTimer(int sec) {
        if (timerView == null) {
            throw new IllegalStateException("timer view have not been initialized");
        }
//        timerView.setMax(sec);
        timerView.setProgress(0);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
