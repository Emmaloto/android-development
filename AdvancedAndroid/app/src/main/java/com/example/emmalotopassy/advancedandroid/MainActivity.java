package com.example.emmalotopassy.advancedandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void loadList(View view){
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }

    public void loadNumberList(View view){
        Intent intent = new Intent(this, NumberTablesActivity.class);
        startActivity(intent);
    }


    public void loadBasicTimer(View view){
        Intent intent = new Intent(this, AboutTimersActivity.class);
        startActivity(intent);
    }

    public void loadUIHideSeek(View view){
        Intent intent = new Intent(this, ShowTellActivity.class);
        startActivity(intent);
    }

    public void loadWebGrabber(View view){
        Intent intent = new Intent(this, GrabWebContentActivity.class);
        startActivity(intent);
    }
}
