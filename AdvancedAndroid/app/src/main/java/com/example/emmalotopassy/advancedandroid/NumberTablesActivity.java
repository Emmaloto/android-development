package com.example.emmalotopassy.advancedandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberTablesActivity extends AppCompatActivity {

    private ListView listView;
    private SeekBar seekBar;
    private TextView selectDisplay;

    //private ArrayList<String> numbers = new ArrayList<String>();
    private String[] numbers = new String[12];
    private RadioGroup radioGroup;

    private ArrayAdapter<String> adapter;
    private String prev = ""; // Makes sure current button has not been selected already

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_tables);

        listView = findViewById(R.id.numbersList);
        seekBar = findViewById(R.id.seekBar);
        selectDisplay = findViewById(R.id.selected);
        radioGroup = findViewById(R.id.tableType);
        //String m = "MEGAn IS SO FRICKIN ANNOYING WITH HER MICROMANAGEMENT! ";

        //seekBar.setMin(1);
        seekBar.setMax(12);
        seekBar.setProgress(2);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Change number used to calculate
                selectDisplay.setText(seekBar.getProgress() + "");
                if(prev.equals("Times Tables")){
                    timesTable(seekBar.getProgress());
                }else if(prev.equals("Squares")){
                    squares(seekBar.getProgress());
                } else if (prev.equals("Powers")) {
                    powers(seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        // Set up arraylist for ListView and its actions
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(this, " " + view., Toast.LENGTH_LONG).show();
            }
        });

        // Sets values for when app is first opened
        selectDisplay.setText(seekBar.getProgress() + "");
        timesTable(seekBar.getProgress());
    }

    public void selectTable(View view){
        int selectedType = radioGroup.getCheckedRadioButtonId();
        RadioButton select = findViewById(selectedType);


        // Avoids doing the same operation twice in a row
        if(!prev.equals(select.getText().toString())){ // Issues with multiplication tables
            if(select.getText().toString().equals("Times Tables")){
                seekBar.setMax(12);
                timesTable(seekBar.getProgress());
                //Toast.makeText(this, "TIMES TABLES!!! ", Toast.LENGTH_SHORT).show();
            }else if(select.getText().toString().equals("Squares")){
                seekBar.setMax(12);
                squares(seekBar.getProgress());
            } else if (select.getText().toString().equals("Powers")) {
                seekBar.setMax(5);
                powers(seekBar.getProgress());
            }
        }



        prev = select.getText() + "";
    }

    // num parameter is from slider, i is enumerated through table

    private void timesTable(int num){
        //numbers.clear();
        for(int i = 1; i < 13; i++){
            //numbers.add(num * i + "");
            numbers[i - 1] = num * i + "";
        }
        //Toast.makeText(this, "TIMES TABLES CALCULATED!!! ", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    private void squares(int num){
        //numbers.clear();
        for(int i = 0; i < 12; i++){
            //numbers.add(num * i + "");
            String no = i + num * 10 + " : ";
            numbers[i] = no + (i + num * 10) * (i + num * 10) + "";
        }
        adapter.notifyDataSetChanged();

        //
    }

    private void powers(int num){
        for(int i = 0; i < 11; i++){
            //numbers.add(num * i + "");
            String no = "Power of " + i + " : ";
            numbers[i] = no + Math.pow(num, i) + "";
        }
        adapter.notifyDataSetChanged();
    }
}
