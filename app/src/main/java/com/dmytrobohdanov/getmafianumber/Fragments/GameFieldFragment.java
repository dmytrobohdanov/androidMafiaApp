package com.dmytrobohdanov.getmafianumber.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dmytrobohdanov.getmafianumber.R;
import com.dmytrobohdanov.getmafianumber.Utils;
import com.github.lzyzsd.circleprogress.DonutProgress;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GameFieldFragment extends PlayerFragment {
    public static final String TAG = "gameFieldFragmentTag";
    private static final int HALF_MINUTE = 30000;
    //constants
    private final int ONE_SEC = 1000;
    private final int ONE_MINUTE = 60000;
    private final int FLAG_VIBRATE_PREFINISH = 3;
    private final int FLAG_VIBRATE_FINISH = 5;

    //views
    @BindView(R.id.btnStart60)
    Button start60;

    @BindView(R.id.btnStart30)
    Button start30;

    @BindView(R.id.btnCancel)
    Button cancel;

    @BindView(R.id.timerView)
    DonutProgress timerView;

    //vars
    CountDownTimer counter;
    boolean isHalfMinute;

    //vibrator
    Vibrator vibrator;

    View.OnClickListener onClickListener = view -> {
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

            case R.id.timerView:
                startTimerFor(ONE_MINUTE);
                break;
        }
    };


    public GameFieldFragment() {
        // Required empty public constructor
    }


    @Override
    public void onPause() {
        Utils.keepScreenOn(getActivity(), false);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_field, container, false);

        //init player's view
        initPlayerViews(rootView, R.id.player_play, R.id.player_pause, R.id.player_stop,
                R.id.player_volume_max, R.id.player_volume_mute, R.id.player_previous, R.id.player_next);

        ButterKnife.bind(this, rootView);

        setOnClickListeners(start60, start30, cancel, timerView);

        initTimer(ONE_MINUTE);

        //init vibrator
        vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        Utils.keepScreenOn(getActivity(), true);
        return rootView;
    }


    /**
     * Starting timer for specified interval of time
     *
     * @param millisec time in milli sec
     */
    private void startTimerFor(int millisec) {
        //canceling vibration
        vibrator.cancel();

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
        timerView.setText("thanks");
        timerView.setProgress(0);

        vibrate(FLAG_VIBRATE_FINISH);
    }

    /**
     * Make phone vibrate
     */
    private void vibrate(int flag) {
        long[] pattern;
        if (flag == FLAG_VIBRATE_FINISH) {
            // Start without a delay
            // Each element then alternates between vibrate, sleep, vibrate, sleep...
            pattern = new long[]{0, 400, 300, 500, 300, 800, 300};
        } else {
            pattern = new long[]{0, 600, 100};
        }

        //-1 means do not repeat
        vibrator.vibrate(pattern, -1);
    }

    /**
     * Updating progress data in timer view
     * setting progress and text to view
     */
    private void updateProgressOfTimer(long millisUntilFinished) {
        int secondsUntilFinish = (int) millisUntilFinished / 1000;
        //setting text
        timerView.setText("" + secondsUntilFinish);

        //changing color if few time left
        if (isHalfMinute) {
            if (secondsUntilFinish == 5) {
                timerView.setFinishedStrokeColor(getResources().getColor(R.color.few_time_left_color));
                vibrate(FLAG_VIBRATE_PREFINISH);
            }
        } else {
            if (secondsUntilFinish == 15) {
                timerView.setFinishedStrokeColor(getResources().getColor(R.color.few_time_left_color));
                vibrate(FLAG_VIBRATE_PREFINISH);
            }
        }

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
        timerView.setFinishedStrokeColor(getResources().getColor(R.color.timer_default_color));

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
    private void setOnClickListeners(View... buttons) {
        for (View button : buttons) {
            button.setOnClickListener(onClickListener);
        }
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
