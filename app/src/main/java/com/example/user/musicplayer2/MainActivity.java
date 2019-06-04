package com.example.user.musicplayer2;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import static com.example.user.musicplayer2.R.*;
import static com.example.user.musicplayer2.R.id.*;
import static com.example.user.musicplayer2.R.id.btnPlay;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlay, btnBack,btnFor;
    private SeekBar seekbar;
    private MediaPlayer mediaPlayer;
    private Runnable runnable;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);



        btnPlay = findViewById(id.btnPlay);
        btnBack = findViewById(R.id.btnBack);
        btnFor = findViewById(R.id.btnFor);
        handler = new Handler();
        seekbar= findViewById(R.id.seekbar);
        mediaPlayer= MediaPlayer.create(this,R.raw.play);

        btnFor.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnPlay.setOnClickListener(this);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekbar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
                changeSeekbar();
            }
        });
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              if (b){
                  mediaPlayer.seekTo(i);
              }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changeSeekbar(){
        seekbar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()){
            runnable =new Runnable() {
                @Override
                public void run() {
                    changeSeekbar();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }


    @Override
    public void onClick(View v) {
        switch (view.getId()){
            case R.id.btnPlay:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setText(">");
                }
                else{
                    mediaPlayer.start();
                    btnPlay.setText("||");
                    changeSeekbar();
                }
                break;
            case R.id.btnFor:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
                break;
            case R.id.btnBack:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
                break;


        }
    }
}
