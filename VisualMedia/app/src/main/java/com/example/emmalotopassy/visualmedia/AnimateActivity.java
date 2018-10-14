package com.example.emmalotopassy.visualmedia;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class AnimateActivity extends AppCompatActivity {

    private ImageView images[];
    private MediaPlayer mediaPlayer;
    private int animalSounds[];

    private int rot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        mediaPlayer = MediaPlayer.create(this, R.raw.instrumental);

        // Initialize array values
        ImageView tempImgArray[] = {findViewById(R.id.cowView), findViewById(R.id.chickenView),
                                 findViewById(R.id.dolphinView), findViewById(R.id.mouseView)};

        int tempSoundArray[] = {R.raw.moo, R.raw.chicken_bawk, R.raw.dolphin_robotic, R.raw.mouse_squeak};

        images = tempImgArray;
        animalSounds = tempSoundArray;

        //mediaPlayer.start();
    }

    public void animate(View view){
        //Indexes in array (should also correspond to sound array)
        int curr = 0, newImage;

        // Check which image view index is displayed
        for(int i = 0; i < images.length; i++){
            if(images[i].getAlpha() > 0)
                curr = i;
        }

        // Pick a new image + sound
        newImage = getPseudoRandom(0 , images.length, curr);


        // Switch Images, play sound
        images[newImage].animate().alpha(1).rotation(rot).setDuration(2000);
        images[curr].animate().alpha(0).setDuration(2000);

        MediaPlayer sound = MediaPlayer.create(this, animalSounds[newImage]);
        sound.start();

        rot += 30;


    }

    public void toggleAudio(View view){
        boolean checked = ((CheckBox) view).isChecked();

        if(checked)
            playAudio(view);
        else
            pauseAudio(view);

    }



    // Generate number that is different from supplied index
    private int getPseudoRandom(int min_n, int max_n, int barredIndex){
        int rand = generateRandom(min_n, max_n);
        while(rand == barredIndex)
            rand = generateRandom(min_n, max_n);

        // Log.i("info", "Our number is " + rand);
        // Log.i("info", "Max is " + max_n);
        return rand;
    }

    private int generateRandom(int min_n, int max_n){
        int random = (int)(Math.random() * max_n + min_n);
        return random;
    }

    // In case I need to access these directly
    public void playAudio(View view){
        mediaPlayer.start();
    }

    public void pauseAudio(View view){
        mediaPlayer.pause();
    }
}
