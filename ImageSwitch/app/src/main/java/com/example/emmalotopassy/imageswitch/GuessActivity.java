package com.example.emmalotopassy.imageswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.math.NumberUtils;

public class GuessActivity extends AppCompatActivity {
    private TextView intervalInfo;
    private EditText guess;
    private TextView hint;
    private ImageView image;

    private String infoText;
    private int actualNumber;
    private int max = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        infoText = "Pick a number between 1 and ";
        actualNumber = (int)(Math.random() * ( max - 1 ));

        intervalInfo = findViewById(R.id.pickView);
        hint = findViewById(R.id.hintText);
        image = findViewById(R.id.imageView);

    }

    public void checkGuess(View view){
        guess = findViewById(R.id.editText);


        if(checkNumber(guess.getText().toString())){

            int value = Integer.parseInt(guess.getText().toString());
            if(value == actualNumber) {
                Toast.makeText(this, "You nailed it!", Toast.LENGTH_LONG).show();
                image.setImageResource(R.drawable.tick);
                // Toast.makeText(this,"Make sure input is a number.", Toast.LENGTH_SHORT).show();

            }else if(value < actualNumber) {
                if(value >= actualNumber - 10)
                    Toast.makeText(this, "So close! Try a higher number.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Too Low!", Toast.LENGTH_LONG).show();
                image.setImageResource(R.drawable.cross);
            }else if(value > actualNumber) {
                // no + 10 > value > no
                if(value <= actualNumber + 10)
                    Toast.makeText(this, "So close! Try a lower number.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Too High!", Toast.LENGTH_LONG).show();
                image.setImageResource(R.drawable.cross);
            }

        }else{
            Toast.makeText(this, "That is NOT a number!", Toast.LENGTH_LONG).show();
        }
    }

    public void generateNumber(View view){
        //max = (int)(Math.random() + 1) * ( 7 - 2 ) * 10;
        max = generateRandom(1, 5) * 10;

        //actualNumber = (int)(Math.random() * ( max - 1 ));
        actualNumber = generateRandom(1, max);
        intervalInfo.setText(infoText + max);
        image.setImageResource(R.drawable.question);
        placeHints();
    }

    private boolean checkNumber(String number){

        return NumberUtils.isCreatable(number);

    }

    private void placeHints(){
        // Check if we will give a useful hint
        boolean giveEasy = generateRandom(0, 2) == 1;

        // Check if num is perfect square
        double sqrt = Math.sqrt(actualNumber);
        int x = (int) sqrt;
        if(Math.pow(sqrt, 2) == Math.pow(x,2)){
            hint.setText("The number is a perfect square!");
        }else if(actualNumber % 3 == 0){
            hint.setText("The number is divisible by 3!");
        }else if(actualNumber % 10 == 0){
            hint.setText("The number is a multiple of 10!");
        }else if(actualNumber % 2 == 0 && giveEasy){
            hint.setText("The number is even.");
        }else if(actualNumber % 2 != 0 && giveEasy){
            hint.setText("The number is odd.");
        }else{
            hint.setText("No hints this time. Have fun guessing!");
        }
    }

    private int generateRandom(int min_n, int max_n){
        int random = (int )(Math.random() * max_n + min_n);
        return random;
    }
}
