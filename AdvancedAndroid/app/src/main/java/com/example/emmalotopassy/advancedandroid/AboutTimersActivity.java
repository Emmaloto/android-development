package com.example.emmalotopassy.advancedandroid;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class AboutTimersActivity extends AppCompatActivity {

    // Make sure this is in order
    private int laugh[] = {R.drawable.laugh1, R.drawable.laugh2};
    private int attack[] = {R.drawable.fight1, R.drawable.fight2, R.drawable.fight3, R.drawable.fight4};
    private int heal[] = {R.drawable.heal_xtra, R.drawable.heal, R.drawable.heal, R.drawable.heal};
    private int special[] = {R.drawable.special1, R.drawable.special2, R.drawable.special3, R.drawable.special4};
    private int dance[] = {R.drawable.dance1, R.drawable.dance2, R.drawable.dance3, R.drawable.dance4};

    private int[][] actions = {laugh, attack, heal, special, dance};
    private int playing[] = {R.drawable.cogwheel}; //Default
    private int soundFX[] = {R.raw.laughing, R.raw.attack, R.raw.heal,R.raw.fanfare, R.raw.dance};


    private SeekBar seekBar;
    private TextView label;
    private ImageView screen;
    private MediaPlayer mediaPlayer;

    private String labels[] = {"Laugh", "Attack", "Healing", "Special", "Dancing"};
    private int prevValue = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_timers);
        final Context t = this;

        // Get view elements and set up
        seekBar = findViewById(R.id.chooseType);
        label = findViewById(R.id.label);
        screen = findViewById(R.id.animationWindow);
        mediaPlayer = MediaPlayer.create(this, soundFX[0]);

        // Minimum goes all the way to zero, so may need to increment value by 1
        seekBar.setMax(4);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                //  Check helps to avoid IndexOutOfBounds and reloading same array and images
                if(seekBar.getProgress() < labels.length && prevValue != seekBar.getProgress()){

                    // Change display text and Toast Text
                    label.setText(labels[seekBar.getProgress()]);
                    Toast.makeText(t, " Switched to | " + labels[seekBar.getProgress()] + " |", Toast.LENGTH_SHORT).show();
                    playing = actions[seekBar.getProgress()];

                    // Play music
                    mediaPlayer.reset();
                    mediaPlayer = MediaPlayer.create(t, soundFX[seekBar.getProgress()]);
                    mediaPlayer.start();

                    setAnimation();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        label.setText(labels[seekBar.getProgress()]);
        mediaPlayer.start();
    }


    public void setAnimation(){
        final int noOfFrames = playing.length;

        new CountDownTimer(5000, 100) {
            int counter = 0;
            @Override
            public void onTick(long secsTillDone) { //When interval has been reached
                //Toast.makeText(t, " You got " + secsTillDone / 1000 + " seconds before the code is done!", Toast.LENGTH_SHORT).show();
                screen.setImageResource(playing[counter]);

                counter ++;
                if(counter >= noOfFrames) counter = 0;
            }

            @Override
            public void onFinish() {
                //Toast.makeText(t, " Animation finished!", Toast.LENGTH_SHORT).show();
            }
        }.start();

        prevValue = seekBar.getProgress();
    }


    {
        /*
        // ONE way to do a timer more useful in situations not just for timers
        // Handler runs runnable
        final Handler handler = new Handler();
        Runnable run = new Runnable() {

            @Override
            public void run() {
                Log.i("Our process runs!","A second passed");
                Toast.makeText(t, " Behold! Our process runs! ", Toast.LENGTH_SHORT).show();

                handler.postDelayed(this,1000);

            }
        };
        handler.post(run);




        // SECOND Way of doing timers
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long secsTillDone) { //When interval has been reached
                Toast.makeText(t, " You got " + secsTillDone / 1000 + " seconds before the code is done!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Toast.makeText(t, " We're done! Countdown Finished!", Toast.LENGTH_SHORT).show();
            }
        }.start();

        */



    }
}
