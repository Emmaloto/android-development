package com.example.emmalotopassy.visualmedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoDemo extends AppCompatActivity {

    VideoView videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_demo);

        videoPlayer = findViewById(R.id.videoView);
        videoPlayer.setVideoPath("android.resource://" + getPackageName() + "/"  + R.raw.master_video);

        // Video Controls
        MediaController mediaControl = new MediaController(this);
        mediaControl.setAnchorView(videoPlayer);
        videoPlayer.setMediaController(mediaControl);

        videoPlayer.start();
    }


}
