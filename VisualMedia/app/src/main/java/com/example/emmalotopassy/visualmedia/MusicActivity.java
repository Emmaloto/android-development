package com.example.emmalotopassy.visualmedia;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MusicActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private int songs[];

    private SeekBar progBar;
    private TextView duration, currPos;

    private ImageButton play, pause;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        getGUI();

        // Songs
        int s_temp[] =  {R.raw.love_life, R.raw.mirrorball, R.raw.up_your_street};
        songs = s_temp;

        // Player for song + songs
        mediaPlayer = MediaPlayer.create(this, songs[0]);

        // For volume control
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        // Seekbar for Volume
        SeekBar volumeControl = findViewById(R.id.seekBar);
        volumeControl.setMax(maxVol);
        volumeControl.setProgress(curVol);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                //Log.i("Seek: ", "And ye will increase to " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setupSeekBar();
    }

    private void getGUI(){
        // Buttons
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);

        // Seekbar
        progBar = findViewById(R.id.progBar);

        // Song Info Text
        duration = findViewById(R.id.duration);
        currPos = findViewById(R.id.currPos);

        // RadioButton GUI
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void setSong(View view){
        int selectedSong = radioGroup.getCheckedRadioButtonId();
        //RadioButton selected = findViewById(selectedSong);
        int index = radioGroup.indexOfChild(findViewById(selectedSong));
        if(songs.length > index) {
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this, songs[index]);
            setupSeekBar();
        }

        Toast.makeText(this, "Selected song ID: " + index, Toast.LENGTH_LONG).show();
    }

    private void setupSeekBar(){

        // Seekbar for Duration
        progBar.setMax(mediaPlayer.getDuration());
        progBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Change text and song play location
                currPos.setText(progBar.getProgress() + "");
                mediaPlayer.seekTo(progBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });




        // Duration Info
        duration.setText(mediaPlayer.getDuration() + "s");
        currPos.setText(mediaPlayer.getCurrentPosition() + "");

        // Update progress bar with  every - time period
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progBar.setProgress(mediaPlayer.getCurrentPosition());

                //if()
            }
        }, 0, 100);


    }


    public void play(View view){
        mediaPlayer.start();

        pause.setBackgroundColor(Color.LTGRAY);
        play.setBackgroundColor(Color.GRAY);

    }

    public void pause(View view){
        mediaPlayer.pause();

        play.setBackgroundColor(Color.LTGRAY);
        pause.setBackgroundColor(Color.GRAY);
    }


    protected void onPause (){
        super.onPause();

        mediaPlayer.pause();
    }


    /*
    public void play2(View view){
        mediaPlayer2.start();
    }

    public void pause2(View view){
        mediaPlayer2.pause();
    }

    public void play3(View view){
        mediaPlayer3.start();
    }

    public void pause3(View view){
        mediaPlayer3.pause();
    }

    public void play4(View view){
        mediaPlayer4.start();
    }

    public void pause4(View view){
        mediaPlayer4.pause();
    }

    */
}
