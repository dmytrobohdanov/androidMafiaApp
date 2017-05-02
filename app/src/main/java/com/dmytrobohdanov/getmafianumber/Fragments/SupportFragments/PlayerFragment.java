package com.dmytrobohdanov.getmafianumber.Fragments.SupportFragments;


import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;

import com.dmytrobohdanov.getmafianumber.R;

import java.io.IOException;

abstract public class PlayerFragment extends BaseFragment {
    private static final boolean PLAY_NEXT = true;
    private static final boolean PLAY_PREV = false;

    //view's ids
    int playerPlay;
    int playerPause;
    int playerStop;
    int playerVolumeMax;
    int playerVolumeMute;
    int playerPrevious;
    int playerNext;

    //views
    ImageView playView;
    ImageView pauseView;
    ImageView stopView;
    ImageView volumeMaxView;
    ImageView volumeMuteView;
    ImageView playNextView;
    ImageView playPrevView;

    //player support var
    boolean playerOnPause = false;
    MediaPlayer mediaPlayer = null;

    //index of currently playing song
    int indexOfPlayingSong = 0;

    //array of song's files names
    private String[] songsArray = {"1.mp3", "2.mp3", "3.mp3", "4.mp3", "5.mp3"};

    //listener
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

            case R.id.player_next:
                play(PLAY_NEXT);
                break;

            case R.id.player_previous:
                play(PLAY_PREV);
                break;
        }
    };

    /**
     * Initializing views and theirs id
     */
    protected void initPlayerViews(View rootView, int player_play, int player_pause,
                                   int player_stop, int playerVolumeMax, int playerVolumeMute,
                                   int playerPrevious, int playerNext) {
        //view's id
        this.playerPlay = player_play;
        this.playerPause = player_pause;
        this.playerStop = player_stop;
        this.playerVolumeMax = playerVolumeMax;
        this.playerVolumeMute = playerVolumeMute;
        this.playerNext = playerNext;
        this.playerPrevious = playerPrevious;

        //views
        playView = (ImageView) rootView.findViewById(player_play);
        pauseView = (ImageView) rootView.findViewById(player_pause);
        stopView = (ImageView) rootView.findViewById(player_stop);
        volumeMuteView = (ImageView) rootView.findViewById(playerVolumeMute);
        volumeMaxView = (ImageView) rootView.findViewById(playerVolumeMax);
        playPrevView = (ImageView) rootView.findViewById(playerPrevious);
        playNextView = (ImageView) rootView.findViewById(playerNext);

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
        playNextView.setOnClickListener(playerClickListener);
        playPrevView.setOnClickListener(playerClickListener);
    }

    /**
     * Playing audio file with specified fileName
     *
     * @param fileName name of audio file in assets to play
     */
    public void play(String fileName) {
        MediaPlayer localMediaPlayer = new MediaPlayer();
        AssetFileDescriptor afd;
        try {
            afd = getContext().getAssets().openFd(fileName);

            localMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

            afd.close();
            localMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        localMediaPlayer.start();
        localMediaPlayer.setOnCompletionListener(mp -> localMediaPlayer.release());
    }

    /**
     * Handle play click
     */
    public void play() {
        if (!playerOnPause) {
            mediaPlayer = new MediaPlayer();

            mediaPlayer.setOnCompletionListener(mediaPlayerListener -> play(PLAY_NEXT));

            AssetFileDescriptor afd;
            try {
//                afd = getContext().getAssets().openFd("temp_audio.mp3");
                afd = getContext().getAssets().openFd(songsArray[indexOfPlayingSong]);

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
     * Playing next or previous song from playlist
     *
     * @param otherSong pass PLAY_NEXT or PLAY_PREV
     */
    protected void play(boolean otherSong) {
        if (otherSong == PLAY_NEXT) {
            if (indexOfPlayingSong >= (songsArray.length - 1)) {
                //playing last song in array
                indexOfPlayingSong = 0;
            } else {
                //playing not the last song
                indexOfPlayingSong++;
            }
        } else {
            if (indexOfPlayingSong == 0) {
                //playing the first song in array
                indexOfPlayingSong = songsArray.length - 1;
            } else {
                //playing not the first song
                indexOfPlayingSong--;
            }
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        play();
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
