package com.example.emmalotopassy.imageswitch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageBox;
    private int[] images = {R.drawable.chicken, R.drawable.cow, R.drawable.fire, R.drawable.snail, R.drawable.mouse};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageBox = findViewById(R.id.imageView);
        imageBox.setContentDescription("");
        // images;
    }

    public void changeImage(View view){

        int newPhoto = images[generateRandom(0, images.length)];

        // If image is already displayed
        for(;imageBox.getContentDescription() == newPhoto + "";){
            newPhoto = images[generateRandom(0, images.length)];
            Log.i("message", "Image id selected is " + newPhoto );
        }

        for(int i = 0; i < images.length; i++)
            Log.i("info", "Image " + i + 1 + " is " + images[i] );



        imageBox.setImageResource(newPhoto);
        imageBox.setContentDescription(newPhoto + "");

/*
        // imageBox.setContentDescription("fire")
        .
        if(imageBox.getContentDescription() == newPhoto + ""){

        }

        if(imageBox.getContentDescription() == "fire"){
            imageBox.setImageResource(R.drawable.snail);
            imageBox.setContentDescription("snail");
        }else{
            imageBox.setImageResource(R.drawable.fire);
            imageBox.setContentDescription("fire");
        }
*/
    }

    private int generateRandom(int min_n, int max_n){
        int random = (int)(Math.random() * max_n + min_n);
        return random;
    }

    public void goBack(View view){
        Intent intent = new Intent(this, SelectActivityMain.class);
        startActivity(intent);
    }
}
