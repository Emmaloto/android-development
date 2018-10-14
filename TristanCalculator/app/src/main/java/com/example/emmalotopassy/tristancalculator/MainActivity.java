package com.example.emmalotopassy.tristancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.math.NumberUtils;

public class MainActivity extends AppCompatActivity {

    private EditText sciText;
    private EditText mathText, engText, readText;

    private TextView points;
    private TextView average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sciText = findViewById(R.id.sciValue);
        mathText = findViewById(R.id.mathVal);
        engText = findViewById(R.id.engVal);
        readText = findViewById(R.id.readVal);

        points = findViewById(R.id.compPredict);
        average = findViewById(R.id.scoreVal);

    }

    public void predictGrowth(View view){
        averageScores(view);

        String sci = sciText.getText().toString();
        if(checkNumber(sci)){
            int sciScore = Integer.parseInt(sci);
            int approx = (int)(0.342 * sciScore - 5.62);
            points.setText(approx + "");
        }else{
            points.setText("N/A");
        }
    }

    public void averageScores(View view){
        String sci = sciText.getText().toString();
        String math = sciText.getText().toString();
        String read = mathText.getText().toString();
        String eng = engText.getText().toString();
        if(checkNumber(sci) && checkNumber(math)&& checkNumber(read) && checkNumber(eng)){
            int avg = (Integer.parseInt(sci) + Integer.parseInt(math) + Integer.parseInt(read) + Integer.parseInt(eng))/4;

            average.setText(avg + "");
        }else{
            average.setText("N/A");
        }
    }

    private boolean checkNumber(String number){

        return NumberUtils.isCreatable(number);

    }
}
