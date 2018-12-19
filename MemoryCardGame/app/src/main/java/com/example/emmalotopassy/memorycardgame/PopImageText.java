package com.example.emmalotopassy.memorycardgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class PopImageText extends Activity{

    private ListView myListView;
    // Unnecessarily clunky. Arraylists may be preferred.
    private String [] backgrounds = {"Abstract", "Bokeh", "Colors", "Blue", "Forest", "Milky Way", "Forest Symmetry", "Sunrise", "Digital",
            "Photogenic Tree", "Tranquility", "Lines", "Triangles"};;
    private int bgImages[] = {R.drawable.background_abstract, R.drawable.background_bokeh, R.drawable.background_color, R.drawable.background_desktop_blue,
            R.drawable.background_forest, R.drawable.background_milky_way, R.drawable.background_pattern_trees, R.drawable.background_sunrise,
            R.drawable.background_the_background, R.drawable.background_tree,R.drawable.background_turkey_scape, R.drawable.background_wallpaper_lines,
            R.drawable.triangle_bg};;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_select);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8), (int)(height * .8));

        myListView = findViewById(R.id.cardNoList);

        if(backgrounds.length != bgImages.length)
            Log.i("Error","Ya done messed up! There are "+ backgrounds.length + " names & " +bgImages.length+ " photos!");

        CustomListView customList = new CustomListView(this, backgrounds, bgImages);
        myListView.setAdapter(customList);


        final Context c = this;
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Sets the card count and closes window
                Intent intent = new Intent();
                intent.putExtra("bg_photo", bgImages[i]);
                setResult(RESULT_OK, intent);
                Toast.makeText(c, backgrounds[i] + " background selected", Toast.LENGTH_LONG).show();

                finish();
            }
        });

    }
}
