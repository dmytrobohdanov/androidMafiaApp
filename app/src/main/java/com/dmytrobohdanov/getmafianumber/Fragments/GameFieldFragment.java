package com.dmytrobohdanov.getmafianumber.Fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dmytrobohdanov.getmafianumber.R;
import com.github.lzyzsd.circleprogress.DonutProgress;


public class GameFieldFragment extends PlayerFragment {
    public static final String TAG = "gameFieldFragmentTag";

    //constants
    private static final int ONE_SEC = 1000;
    private static final int ONE_MINUTE = 60000;
    private static final int HALF_MINUTE = 30000;


    //views
    Button start60;
    Button start30;
    Button cancel;

    //vars
    DonutProgress timerView;
    CountDownTimer counter;
    boolean isHalfMinute;

    View.OnClickListener buttonsClickHandler = view -> {
        int buttonId = view.getId();
        switch (buttonId) {
            case R.id.btnStart60:
                startTimerFor(ONE_MINUTE);
                break;

            case R.id.btnStart30:
                startTimerFor(HALF_MINUTE);
                break;

            case R.id.btnCancel:
                cancelTimer();
                break;
        }
    };


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
        //buttons
        start30 = (Button) rootView.findViewById(R.id.btnStart30);
        start60 = (Button) rootView.findViewById(R.id.btnStart60);
        cancel = (Button) rootView.findViewById(R.id.btnCancel);
        setOnClickListeners(start60, start30, cancel);

        initTimer(ONE_MINUTE);

        return rootView;
    }


    /**
     * Starting timer for specified interval of time
     *
     * @param millisec time in milli sec
     */
    private void startTimerFor(int millisec) {
        cancelTimer();
        initTimer(millisec);

        //saving time interval
//        timeInterval = millisec;
        isHalfMinute = (millisec == HALF_MINUTE);

        //display time
//        timerView.setText("" + millisec);

        //starting time count
        counter = new CountDownTimer(millisec, ONE_SEC) {
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
        //setting text
        timerView.setText("" + secondsUntilFinish);

        //setting progress
        //if half minute - progress will be twice faster
        if (isHalfMinute) {
            secondsUntilFinish *= 2;
        }
        timerView.setProgress(secondsUntilFinish);
    }

    /**
     * Initializing timer with starting values
     *
     * @param millisec target seconds for count down
     */
    private void initTimer(int millisec) {
        if (timerView == null) {
            throw new IllegalStateException("timer view have not been initialized");
        }
//        timerView.setMax(sec);
        timerView.setProgress(0);
        timerView.setText("" + millisec / 1000);
    }

    /**
     * Stops timer and init timer for one minute
     */
    private void cancelTimer() {
        if (counter != null) {
            counter.cancel();
            initTimer(ONE_MINUTE);
        }
    }

    /**
     * Setting listener to buttons
     *
     * @param buttons to set listener at
     */
    private void setOnClickListeners(Button... buttons) {
        for (Button button : buttons) {
            button.setOnClickListener(buttonsClickHandler);
        }
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
