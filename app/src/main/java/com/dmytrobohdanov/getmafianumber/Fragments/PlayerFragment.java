package com.dmytrobohdanov.getmafianumber.Fragments;


import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;

import com.dmytrobohdanov.getmafianumber.R;

import java.io.IOException;

abstract public class PlayerFragment extends BaseFragment {

    //view's ids
    int player_play;
    int player_pause;
    int player_stop;
    int playerVolumeMax;
    int playerVolumeMute;

    //views
    ImageView playView;
    ImageView pauseView;
    ImageView stopView;
    ImageView volumeMaxView;
    ImageView volumeMuteView;

    //player support var
    boolean playerOnPause = false;
    MediaPlayer mediaPlayer = null;

    View.OnClickListener playerClickListener = (view) -> {
        switch (view.getId()) {
            case R.id.player_play:
                play();
                break;

            case R.id.player_stop:
                stop();
                break;

            case R.id.player_pause:
                pause();
                break;

            case R.id.player_volume_mute:
                unMute();
                break;

            case R.id.player_volume_max:
                mute();
                break;

        }
    };

    /**
     * Initializing views and theirs id
     */
    protected void initPlayerViews(View rootView, int player_play, int player_pause, int player_stop, int playerVolumeMax, int playerVolumeMute) {
        //view's id
        this.player_play = player_play;
        this.player_pause = player_pause;
        this.player_stop = player_stop;
        this.playerVolumeMax = playerVolumeMax;
        this.playerVolumeMute = playerVolumeMute;

        //views
        playView = (ImageView) rootView.findViewById(player_play);
        pauseView = (ImageView) rootView.findViewById(player_pause);
        stopView = (ImageView) rootView.findViewById(player_stop);
        volumeMuteView = (ImageView) rootView.findViewById(playerVolumeMute);
        volumeMaxView = (ImageView) rootView.findViewById(playerVolumeMax);

        setListeners();
    }

    /**
     * setting listeners to views
     */
    private void setListeners() {
        playView.setOnClickListener(playerClickListener);
        pauseView.setOnClickListener(playerClickListener);
        stopView.setOnClickListener(playerClickListener);
        volumeMaxView.setOnClickListener(playerClickListener);
        volumeMuteView.setOnClickListener(playerClickListener);
    }

    /**
     * Handle play click
     */
    public void play() {
        if (!playerOnPause) {
            mediaPlayer = new MediaPlayer();
            AssetFileDescriptor afd;
            try {
                afd = getContext().getAssets().openFd("temp_audio.mp3");

                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            playerOnPause = false;
        }
        mediaPlayer.start();

        playView.setVisibility(View.GONE);
        pauseView.setVisibility(View.VISIBLE);
    }

    /**
     * un-Mute sound, change icon of sound
     */
    private void unMute() {
        if (mediaPlayer != null) {
            volumeMaxView.setVisibility(View.VISIBLE);
            volumeMuteView.setVisibility(View.GONE);
            mediaPlayer.setVolume(1, 1);
        }
    }

    /**
     * Mute sound, change icon of sound
     */
    private void mute() {
        if (mediaPlayer != null) {
            volumeMaxView.setVisibility(View.GONE);
            volumeMuteView.setVisibility(View.VISIBLE);
            mediaPlayer.setVolume(0, 0);
        }
    }

    /**
     * Handle stop click
     */
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            playerOnPause = false;

            playView.setVisibility(View.VISIBLE);
            pauseView.setVisibility(View.GONE);
        }
    }

    /**
     * Handle pause click
     */
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            playerOnPause = true;

            playView.setVisibility(View.VISIBLE);
            pauseView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
