package com.sangptit.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnPlay;
    SeekBar seekBarTime, seekBarVolume;
    TextView tvTime, tvDuration;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    Handler handler = new Handler();
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        seekBarTime = findViewById(R.id.seekBarTime);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        tvTime = findViewById(R.id.tvTime);
        tvDuration = findViewById(R.id.tvDuration);

        mediaPlayer = MediaPlayer.create(this, R.raw.cochangtraivietlencay);

        int duration = mediaPlayer.getDuration();
        tvDuration.setText(formatTime(duration));
        seekBarTime.setMax(duration);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBarVolume.setMax(maxVol);
        seekBarVolume.setProgress(curVol);

        btnPlay.setOnClickListener(v -> {
            if (isPlaying) {
                mediaPlayer.pause();
                btnPlay.setBackgroundResource(R.drawable.ic_play);
                isPlaying = false;
            } else {
                mediaPlayer.start();
                btnPlay.setBackgroundResource(R.drawable.ic_pause);
                isPlaying = true;
                updateSeekBar();
            }
        });

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar s, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    tvTime.setText(formatTime(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar s) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar s) {
            }
        });

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar s, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar s) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar s) {
            }
        });

        mediaPlayer.setOnCompletionListener(mp -> {
            isPlaying = false;
            btnPlay.setBackgroundResource(R.drawable.ic_play);
            seekBarTime.setProgress(0);
            tvTime.setText("00:00");
        });
    }

    private void updateSeekBar() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && isPlaying) {
                    int pos = mediaPlayer.getCurrentPosition();
                    seekBarTime.setProgress(pos);
                    tvTime.setText(formatTime(pos));
                    handler.postDelayed(this, 500);
                }
            }
        }, 500);
    }

    private String formatTime(int ms) {
        int s = (ms / 1000) % 60;
        int min = (ms / 1000) / 60;
        return String.format("%02d:%02d", min, s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }
}