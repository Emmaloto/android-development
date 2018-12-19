package com.example.emmalotopassy.visualmedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private ImageView image, image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void loadAnimate(View view){
        Intent intent = new Intent(this, AnimateActivity.class);
        startActivity(intent);
    }

    public void loadCardGame(View view){
         Intent intent = new Intent(this, CardActivity.class);
         startActivity(intent);

        //Toast.makeText(this, "This function is still in development. Sorry!", Toast.LENGTH_LONG).show();
    }

    public void loadVideoPlayer(View view){
        Intent intent = new Intent(this, VideoDemo.class);
        startActivity(intent);
    }

    public void loadMusicPlayer(View view){
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }
}
