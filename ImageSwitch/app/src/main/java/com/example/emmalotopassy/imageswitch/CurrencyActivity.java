package com.example.emmalotopassy.imageswitch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.math.NumberUtils;

public class CurrencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
    }

    public void convert(View view){

        EditText text = findViewById(R.id.dollarText);
        TextView result = findViewById(R.id.nairaView);


        String dol = text.getText().toString();
        if(NumberUtils.isCreatable(dol)){
            double dollars = Double.parseDouble(dol);
            double naira = 359.8 * dollars;
            //       result.setText(naira+", "+ String.format("%.2f", naira));
            result.setText(String.format("%.2f", naira));
            Log.i("Result: ", "Your amount is " + naira);
        }else{
            result.setText("Invalid Value");
            Toast.makeText(this,"Make sure input is a number.", Toast.LENGTH_SHORT).show();
        }



    }

    public void goBack(View view){
        Intent intent = new Intent(this, SelectActivityMain.class);
        startActivity(intent);
    }
}
