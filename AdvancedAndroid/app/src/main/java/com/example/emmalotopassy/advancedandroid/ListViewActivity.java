package com.example.emmalotopassy.advancedandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        myListView = findViewById(R.id.myList);
        String [] candies = {"Skittles", "Reece's", "M & M's", "Toblerone", "Hershey's", "Haribo Gummy Bears", "Starburst", "Kit-Kats"};
        int candyImages[] = {R.drawable.skittles, R.drawable.reeses, R.drawable.m_ms, R.drawable.toblerone, R.drawable.hersheys,
                R.drawable.haribo, R.drawable.starburst, R.drawable.kitkat};

        CustomListView customList = new CustomListView(this, candies, candyImages);
        myListView.setAdapter(customList);
/*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, candies);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
*/
    }
}
