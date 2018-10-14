package com.example.emmalotopassy.imageswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberTypeActivity extends AppCompatActivity {

    public TextView infoText;
    public EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_type);

        infoText = findViewById(R.id.resultText);
        number = findViewById(R.id.numbInput);
    }

    public void checkNumber(View view){

        if(NumberUtils.isCreatable(number.getText().toString())) {
            int num = Integer.parseInt(number.getText().toString());

            // Triangular number formula: N = n(n + 1)/2
            int approx = (int)Math.sqrt(num*2);
            int num2 = (approx*(approx + 1))/2;

            // Check if num is perfect square
            double sqrt = Math.sqrt(num);
            int x = (int) sqrt;
            if(num2 == num && Math.pow(sqrt, 2) == Math.pow(x,2)){ // Check if both
                infoText.setText("Jackpot! This is a square triangular number!");
                Toast.makeText(this, "Jackpot! This is a square triangular number!", Toast.LENGTH_LONG).show();
            }else if(num2 == num) {                         // Check if just triangular (notice that if triangular,square properties are ignored)
                infoText.setText("This is a triangular number!");
                Toast.makeText(this, "This is a triangular number!", Toast.LENGTH_LONG).show();
            }else if(Math.pow(sqrt, 2) == Math.pow(x,2)) {  // Check if just square
                infoText.setText("This is a perfect square!");
                Toast.makeText(this, "This is a perfect square!", Toast.LENGTH_LONG).show();
            }else{
                infoText.setText("Nothing special.");
                Toast.makeText(this, "Nothing special.", Toast.LENGTH_LONG).show();
            }
        }
    }


}
