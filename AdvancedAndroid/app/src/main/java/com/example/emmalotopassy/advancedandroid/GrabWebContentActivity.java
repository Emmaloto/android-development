package com.example.emmalotopassy.advancedandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class GrabWebContentActivity extends AppCompatActivity {

    private ListView myListView;
    private ImageView imageView;
    private TextView text;

    // Data is obtained in separate thread to avoid slowing down program
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try { // Not all strings are URLs
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                int c = 0;

                // Takes characters and adds to string
                // Looks for sixth opening tag and stores it
                while(data != -1 && c < 7){
                    char current = (char) data;
                    if(c == 6) result += current;

                    data = reader.read();
                    if(current == '<') c++;
                }
            } catch (Exception e) {
                e.printStackTrace();

                result = "Failed";
            }

            return result;
        }


    }

    // For downloading an image
    public class ImageDownload extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();
                Bitmap myBitMap = BitmapFactory.decodeStream(in);

                return myBitMap;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grab_web_content);

        imageView = findViewById(R.id.webImage);
        myListView = findViewById(R.id.myList);
        text = findViewById(R.id.someHTML);
        //String [] strings = {"Skittles", "Reece's", "M & M's", "Toblerone", "Hershey's", "Haribo Gummy Bears", "Starburst", "Kit-Kats"};

        int webImages[] = {R.drawable.shapes};
        //CustomListView customList = new CustomListView(this, candies, candyImages);
       // myListView.setAdapter(customList);
    }

    public void setImage(View view){
        // Set Image
        ImageDownload imageTask = new ImageDownload();
        Bitmap myImage;
        try {
            myImage = imageTask.execute("https://static.wixstatic.com/media/acc231_1dbd6a95344e490f9608e871a9bb7ecc~mv2.png/v1/fill/w_1175,h_550,al_c,q_85,usm_0.66_1.00_0.01/turngame_PNG.webp").get();
            imageView.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setText(View view){

        String snippet = "";
        // Get web text
        DownloadTask task = new DownloadTask();
        try {
            snippet = task.execute("https://icyblaze16.wixsite.com/emmaloto/programming-projects").get();
            text.setText(snippet);
        }catch (Exception e){
            //Log.i("Error", e.printStackTrace());
            e.printStackTrace();
        }
    }

}
