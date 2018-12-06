package com.example.emmalotopassy.advancedandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowTellActivity extends AppCompatActivity {

    private ImageView image;
    private TextView text;

    private boolean textDisplayed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tell);

        image = findViewById(R.id.showImage);
        text = findViewById(R.id.textDesc);

        image.setVisibility(View.INVISIBLE);
    }

    public void showText(View v){
       // if(textDisplayed){
            image.setVisibility(View.INVISIBLE);
            text.setVisibility(View.VISIBLE);

            textDisplayed = true;

       // }
    }

    public void showImage(View v){
     //   if(!textDisplayed){
            image.setVisibility(View.VISIBLE);
            text.setVisibility(View.INVISIBLE);

            textDisplayed = false;

      //  }

    }
}
