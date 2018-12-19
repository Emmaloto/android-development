package com.example.emmalotopassy.memorycardgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PopText extends Activity{

    private ArrayAdapter<String> adapter;
    private ListView numberList;
    private String numbers[] = {"6 Cards", "8 Cards", "10 Cards", "12 Cards"};
    private int [] count = {6, 8, 10, 12};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_select);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8), (int)(height * .8));

        numberList = findViewById(R.id.cardNoList);
        // Set up arraylist for ListView and its actions
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers);
        numberList.setAdapter(adapter);

        final Context c = this;
        numberList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Sets the card count and closes window
                Intent intent = new Intent();
                intent.putExtra("noOfCards", count[i]);
                setResult(RESULT_OK, intent);
                Toast.makeText(c, numbers[i] + " game selected", Toast.LENGTH_LONG).show();

                finish();
            }
        });

    }
}
