package com.example.emmalotopassy.imageswitch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_main);
    }

    public void loadConverter(View view){
        Intent intent = new Intent(this, CurrencyActivity.class);
        startActivity(intent);
    }

    public void loadSwitcher(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loadNumberGame(View view){
        Intent intent = new Intent(this, GuessActivity.class);
        startActivity(intent);
    }

    public void loadNumberType(View view){
        Intent intent = new Intent(this, NumberTypeActivity.class);
        startActivity(intent);
    }
}
